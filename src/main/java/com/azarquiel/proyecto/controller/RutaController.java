package com.azarquiel.proyecto.controller;

import com.azarquiel.proyecto.dto.PaisDto;
import com.azarquiel.proyecto.dto.RutaDto;
import com.azarquiel.proyecto.entidades.*;
import com.azarquiel.proyecto.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Controller

public class RutaController {
    private RutaService rutaService;
    private PaisService paisService;
    private UsuarioService usuarioService;
    private FavoritoService favoritoService;
    private PuntuacionService puntuacionService;
    private ComentarioService comentarioService;

    @Autowired
    public RutaController(RutaService rutaService, PaisService paisService, UsuarioService usuarioService, FavoritoService favoritoService, PuntuacionService puntuacionService, ComentarioService comentarioService) {
        this.rutaService = rutaService;
        this.paisService = paisService;
        this.usuarioService = usuarioService;
        this.favoritoService = favoritoService;
        this.puntuacionService=puntuacionService;
        this.comentarioService = comentarioService;
    }
    private void añadirFavoritosAlModel(Model model, Principal principal){
        if(principal !=null){
            String correo = principal.getName();
            Usuario usuario = usuarioService.buscarPorCorreo(correo).orElse(null);
            if(usuario != null){
                List<Ruta> favoritas = favoritoService.obtenerFavoritos(usuario.getId());
                Set<Long> idsFavoritos =favoritas.stream().map(Ruta::getId).collect(Collectors.toSet());
                model.addAttribute("idsFavoritos", idsFavoritos);
            }
        }
    }
    private final Map<Long, String> mapaTiposRuta = Map.of(
            1L, "Costa",
            2L, "Naturaleza",
            3L, "Cultural"

    );
    @GetMapping("/rutas/pais/{id}")
    public  String rutasPorPais(@PathVariable Long id, @RequestParam(required = false) List<String> tiposRutaIds, @RequestParam(required = false) List<String> regionIds , Model model, Principal principal, HttpServletRequest request){

        List<Long> tiposRutaLong = tiposRutaIds !=null ? tiposRutaIds.stream().map(Long::valueOf).toList() : null;
        List<Long> regionesLong = regionIds !=null ? regionIds.stream().map(Long::valueOf).toList() : null;


        List<Ruta> rutas=rutaService.buscarRutas(id, tiposRutaLong, regionesLong);


        Map<Long, Double> medias = new HashMap<>();
        for(Ruta ruta : rutas){
            Double media = puntuacionService.calcularPuntuacionMedia(ruta.getId());
            medias.put(ruta.getId(), media != null ? media : 0.0);
        }


        PaisDto paisDto= paisService.findById(id);

        model.addAttribute("pais", paisDto);
        model.addAttribute("rutas", rutas);
        model.addAttribute("puntuacionesMedias", medias);
        model.addAttribute("paisId", id);
        model.addAttribute("tiposRutaIds", tiposRutaIds);
        model.addAttribute("regionIds", regionIds);

        if(regionIds != null && !regionIds.isEmpty()){
            List<String> nombreRegiones = paisDto.getRegions().stream().filter(r -> regionIds.contains(r.getId().toString())).map(Region::getNombre).toList();
            model.addAttribute("filtrosRegiones", nombreRegiones);
        }
        if(tiposRutaIds != null && !tiposRutaIds.isEmpty()){
            List<String> filtrosTexto = tiposRutaIds.stream().map(Long::valueOf).map(mapaTiposRuta::get).toList();
            model.addAttribute("filtrosTexto", filtrosTexto);
        }
        añadirFavoritosAlModel(model,principal);
        String url = request.getRequestURI();
        if(request.getQueryString() != null){
            url += "?" + request.getQueryString();
        }
        model.addAttribute("currentUrl", url);

        return "rutas";
    }
    @GetMapping("/rutas/{id}")
    public  String rutasPorRuta(@PathVariable Long id, Model model, Principal principal, HttpServletRequest request, @RequestParam(defaultValue = "0") int pagina, @RequestParam(defaultValue = "2") int tamanio){
        RutaDto ruta =rutaService.findById(id);
        model.addAttribute("ruta", ruta);

        if(principal != null){
            String correo = principal.getName();
            Usuario usuario = usuarioService.buscarPorCorreo(correo).orElse(null);
            model.addAttribute("usuarioAutenticado", usuario);
            if(usuario != null){
                boolean esFavorito= favoritoService.esFavorito(id, usuario.getId());
                model.addAttribute("esFavorito", esFavorito);

                Optional<Puntuacion> puntuacionOptional= puntuacionService.buscarPorUsuarioYRuta(usuario.getId(), ruta.getId());
                puntuacionOptional.ifPresent(p -> model.addAttribute("puntuacionUsuario", p));

                Comentario comentarioUsuario= comentarioService.findByUsuarioAndRuta(usuario,ruta);
                model.addAttribute("comentarioUsuario", comentarioUsuario);


            }}else{
            model.addAttribute("usuarioAutenticado", null);
        }

        Page<Comentario> paginaComentarios = comentarioService.findComentarioByRuta(id, pagina, tamanio);
        model.addAttribute("paginaComentarios", paginaComentarios);
        model.addAttribute("todosComentarios", paginaComentarios.getContent());


        List<Comentario>todosComentarios = comentarioService.findAllByRuta(ruta);
        model.addAttribute("todosComentarios", todosComentarios);

        Double media = puntuacionService.calcularPuntuacionMedia(ruta.getId());
       model.addAttribute("puntuacionMedia", media != null ? media : 0.0);

        String url = request.getRequestURI();
        if(request.getQueryString() != null){
            url += "?" + request.getQueryString();
        }
        model.addAttribute("currentUrl", url);


        return "detalleruta";
    }

    @GetMapping("/rutas/tipo/{tipo}")
    public String rutasPorTipo (@PathVariable String tipo,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "12") int size, Model model, Principal principal, HttpServletRequest request){
        Pageable pageable= PageRequest.of(page, size);
        Page<RutaDto> rutas=rutaService.findByTipo(tipo, pageable);
        Map<Long, Double> medias = new HashMap<>();
        for(RutaDto ruta : rutas.getContent()){
            Double media = puntuacionService.calcularPuntuacionMedia(ruta.getId());
            medias.put(ruta.getId(), media != null ? media : 0.0);
        }

        model.addAttribute("rutas", rutas.getContent());
        model.addAttribute("currentPage", rutas.getNumber());
        model.addAttribute("totalPages", rutas.getTotalPages());
        model.addAttribute("totalItems", rutas.getTotalElements());
        model.addAttribute("size", size);
        model.addAttribute("tipo",tipo);
        model.addAttribute("puntuacionesMedias", medias);

        añadirFavoritosAlModel(model, principal);
        String url = request.getRequestURI();
        if(request.getQueryString() != null){
            url += "?" + request.getQueryString();
        }
        model.addAttribute("currentUrl", url);
        return "rutasportipo";
    }

    @GetMapping("/rutas/aleatorias")
    public String rutasPorAleatorias (Model model, Principal principal, HttpServletRequest request){
        List<RutaDto>rutaCosta=rutaService.rutasAleatoriasPorTipo("costa");
        List<RutaDto>rutaNaturaleza=rutaService.rutasAleatoriasPorTipo("naturaleza");
        List<RutaDto>rutaCultural=rutaService.rutasAleatoriasPorTipo("cultural");

        Map<Long, Double> medias = new HashMap<>();
        List<RutaDto> todas = new ArrayList<>();
        todas.addAll(rutaCosta);
        todas.addAll(rutaNaturaleza);
        todas.addAll(rutaCultural);
        for(RutaDto ruta : todas){
            Double media = puntuacionService.calcularPuntuacionMedia(ruta.getId());
            medias.put(ruta.getId(), media != null ? media : 0.0);
        }

        model.addAttribute("rutaCosta", rutaCosta);
        model.addAttribute("rutaNaturaleza", rutaNaturaleza);
        model.addAttribute("rutaCultural", rutaCultural);
        model.addAttribute("puntuacionesMedias", medias);
        añadirFavoritosAlModel(model, principal);




        String url = request.getRequestURI();
        if(request.getQueryString() != null){
            url += "?" + request.getQueryString();
        }
        model.addAttribute("currentUrl", url);

        return "rutasaleatorias";
    }
}
