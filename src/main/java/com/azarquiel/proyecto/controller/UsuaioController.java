package com.azarquiel.proyecto.controller;

import com.azarquiel.proyecto.entidades.Usuario;
import com.azarquiel.proyecto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsuaioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registrar")
    public String procesarRegistro(@ModelAttribute("usuario") Usuario usuario) {
        try{
            usuarioService.registrar(usuario);
            return "redirect:/registro?success=true";
        }catch(Exception e){
            return "redirect:/registro?error=true";
        }


    }
    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }


    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}
