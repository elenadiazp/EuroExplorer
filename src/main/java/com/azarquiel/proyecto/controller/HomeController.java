package com.azarquiel.proyecto.controller;

import com.azarquiel.proyecto.dto.RutaDto;
import com.azarquiel.proyecto.entidades.Usuario;
import com.azarquiel.proyecto.service.FavoritoService;
import com.azarquiel.proyecto.service.PuntuacionService;
import com.azarquiel.proyecto.service.RutaService;
import com.azarquiel.proyecto.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.http.HttpRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    private final RutaService rutaService;
    private final UsuarioService usuarioService;
    private final PuntuacionService puntuacionService;
    private final FavoritoService favoritoService;

    public HomeController(RutaService rutaService, UsuarioService usuarioService, PuntuacionService puntuacionService, FavoritoService favoritoService) {
        this.rutaService = rutaService;
        this.usuarioService = usuarioService;
        this.puntuacionService = puntuacionService;
        this.favoritoService = favoritoService;
    }

    @GetMapping("/")
    public String home(Model model, Principal principal, HttpServletRequest request) {
      //comprobación autenticación del usuario
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            System.out.println("usuario  autenticado bien");
            String username = authentication.getName();
            model.addAttribute("username", username);
        }else{
            System.out.println("usuario no autenticado");
        }
//obtener las 3 rutas mejores valoradas
        List<RutaDto> mejoresRutas = rutaService.obtenerTop3MejorValoradas();
//preparar mapas para puntuaciones medias y favoritos
        Map<Long, Double> puntuacionesMedias = new HashMap<>();
        Map<Long, Boolean> favoritos = new HashMap<>();
//si hay usuario autenticado, obtener id
        Long usuarioId = null;
        if (principal != null) {
            String correo = principal.getName();
            Usuario usuario = usuarioService.buscarPorCorreo(correo).orElse(null);
            if (usuario != null) {
                usuarioId = usuario.getId();
            }
        }
        //para cada ruta mejor valorada, calcular punutación media y si es favorita del usuario
        for(RutaDto ruta : mejoresRutas){
            Double media = puntuacionService.calcularPuntuacionMedia(ruta.getId());
            puntuacionesMedias.put(ruta.getId(), media != null ? media : 0.0);

            if(usuarioId != null){
                boolean esFavorito = favoritoService.esFavorito(ruta.getId(), usuarioId);
                favoritos.put(ruta.getId(), esFavorito);
            }else{
                favoritos.put(ruta.getId(), false);
            }
        }
        model.addAttribute("mejoresRutas", mejoresRutas);
        model.addAttribute("puntuacionesMedias", puntuacionesMedias);
        model.addAttribute("favoritos", favoritos);
//obtener la URL actual
        String url = request.getRequestURI();
        if(request.getQueryString() != null){
            url += "?" + request.getQueryString();
        }
        model.addAttribute("currentUrl", url);
        return "home";
    }
}
