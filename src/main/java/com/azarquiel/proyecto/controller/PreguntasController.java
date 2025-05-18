package com.azarquiel.proyecto.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PreguntasController {
    @GetMapping("/faq")
    public String faq() {
        return "preguntasfrecuentes";
    }
}
