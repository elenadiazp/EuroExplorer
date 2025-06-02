package com.azarquiel.proyecto.controller;

import com.azarquiel.proyecto.daos.RutaRepository;
import com.azarquiel.proyecto.daos.UsuarioRespository;
import com.azarquiel.proyecto.entidades.Puntuacion;
import com.azarquiel.proyecto.entidades.Ruta;
import com.azarquiel.proyecto.entidades.Usuario;
import com.azarquiel.proyecto.service.PuntuacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/rutas")
public class PuntuacionController {
    @Autowired
    private PuntuacionService puntuacionService;

    @Autowired
    private RutaRepository rutaRepository;

    @Autowired
    private UsuarioRespository usuarioRespository;


    @PostMapping("/{idRuta}/puntuar")
    public String puntuar(@PathVariable Long idRuta, @RequestParam int estrellas, Principal principal, RedirectAttributes redirectAttributes) {
       //obtener usuario autenticado a partir del correo
        Optional<Usuario> usuarioOptional = usuarioRespository.findByCorreoElectronico(principal.getName());
        //buscar la ruta a puntuar por su id
        Ruta ruta = rutaRepository.findById(idRuta).orElseThrow(() -> new RuntimeException("Ruta no enontrada"));
//si no se encuentra usuairo redirigir al login
        if (!usuarioOptional.isPresent()) {
            redirectAttributes.addFlashAttribute("error", "Usuario no encontrado");
            return "redirect:/login";
        }
        Usuario usuario = usuarioOptional.get();

//crear objeto puntuacion
        Puntuacion puntuacion = new Puntuacion();
        puntuacion.setIdUsuario(usuario);
        puntuacion.setIdRuta(ruta);
        puntuacion.setPuntuacion((long) estrellas);
//guardar o actualizaar la puntuacion
        puntuacionService.guardarOPuntuar(puntuacion);
        redirectAttributes.addFlashAttribute("exito", "Puntuacion guardada correctamente");
        return "redirect:/rutas/" + idRuta;
    }

}
