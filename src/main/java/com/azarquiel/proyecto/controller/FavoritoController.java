package com.azarquiel.proyecto.controller;

import com.azarquiel.proyecto.entidades.Ruta;
import com.azarquiel.proyecto.entidades.Usuario;
import com.azarquiel.proyecto.service.FavoritoService;
import com.azarquiel.proyecto.service.PuntuacionService;
import com.azarquiel.proyecto.service.RutaService;
import com.azarquiel.proyecto.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FavoritoController {
    @Autowired
    private FavoritoService favoritoService;

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PuntuacionService puntuacionService;

    @GetMapping({"/favoritos", "/favoritos/"})
    public String favoritos(Model model, Principal principal, HttpServletRequest request){
       String correo = principal.getName();
       Usuario usuario = usuarioService.buscarPorCorreo(correo).orElseThrow(()-> new RuntimeException("Usuario no encontrado"));
       System.out.println(correo);
        List<Ruta> rutasFavoritas=favoritoService.obtenerFavoritos(usuario.getId());

        Map<Long, Double> medias = new HashMap<>();
        for(Ruta ruta : rutasFavoritas){
            Double media = puntuacionService.calcularPuntuacionMedia(ruta.getId());
            medias.put(ruta.getId(), media != null ? media : 0.0);
        }
        model.addAttribute("puntuacionesMedias", medias);
        model.addAttribute("rutasFavoritas", rutasFavoritas);
        String url = request.getRequestURI();
        if(request.getQueryString() != null){
            url += "?" + request.getQueryString();
        }
        model.addAttribute("currentUrl", url);
        return "favoritos";
    }
    @PostMapping("/favoritos/eliminar")
    public String eliminarFavorito(@RequestParam Long idRuta, @RequestParam String redirectUrl ,Principal principal, Model model){
        String correo = principal.getName();
        Usuario usuario = usuarioService.buscarPorCorreo(correo).orElseThrow(()-> new RuntimeException("Usuario  no encontrado"));
        favoritoService.guardarQuitarFavorito(idRuta, usuario.getId());
        if(redirectUrl.contains("/favoritos")){
            List<Ruta> rutasFavoritas=favoritoService.obtenerFavoritos(usuario.getId());
            model.addAttribute("rutasFavoritas", rutasFavoritas);
            return "favoritos";
        }


        return "redirect:" + redirectUrl;
    }
}
