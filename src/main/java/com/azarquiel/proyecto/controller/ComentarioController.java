package com.azarquiel.proyecto.controller;

import com.azarquiel.proyecto.dto.RutaDto;
import com.azarquiel.proyecto.entidades.Comentario;
import com.azarquiel.proyecto.entidades.Ruta;
import com.azarquiel.proyecto.entidades.Usuario;
import com.azarquiel.proyecto.service.ComentarioService;
import com.azarquiel.proyecto.service.RutaService;
import com.azarquiel.proyecto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Optional;

@Controller
public class ComentarioController {
    private ComentarioService comentarioService;
    private UsuarioService usuarioService;
    private RutaService rutaService;

    @Autowired
    public ComentarioController(ComentarioService comentarioService, UsuarioService usuarioService, RutaService rutaService) {
        this.comentarioService = comentarioService;
        this.usuarioService = usuarioService;
        this.rutaService = rutaService;
    }



    @PostMapping("/rutas/{idRuta}/comentar")
    public String comentarOEditarRuta(@PathVariable Long idRuta, @RequestParam String comentarioTexto, Principal principal, RedirectAttributes redirectAttributes) {



        if(principal == null){
            return "redirect:/login";
        }
        if(comentarioTexto == null || comentarioTexto.trim().isEmpty()){
            redirectAttributes.addFlashAttribute("mensaje", "El comentario no puede estar vaio");
            return "redirect:/rutas/" + idRuta;
        }

        Optional<Usuario> usuarioOptional= usuarioService.buscarPorCorreo(principal.getName());
        if(usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            RutaDto ruta = rutaService.findById(idRuta);

            try{
                comentarioService.crearComentario(usuario,ruta, comentarioTexto);

            }catch (IllegalStateException e){
                comentarioService.editarComentario(usuario, ruta, comentarioTexto);
            }

            redirectAttributes.addFlashAttribute("mensaje", "Comentario guardado correctamente");
            return "redirect:/rutas/" + idRuta;

        }else{
            return "redirect:/login";
        }



    }
@PostMapping("/rutas/{idRuta}/comentario/eliminar")
    public String eliminarComentario(@PathVariable Long idRuta, Principal principal, RedirectAttributes redirectAttributes) {

        Optional<Usuario> usuarioOptional= usuarioService.buscarPorCorreo(principal.getName());
        RutaDto ruta = rutaService.findById(idRuta);
        if(usuarioOptional.isPresent()) {
            comentarioService.eliminarComentario(usuarioOptional.get(), ruta);
            redirectAttributes.addFlashAttribute("mensaje", "Comentario eliminado correctamente");
        }
        return "redirect:/rutas/" + idRuta;
}

}
