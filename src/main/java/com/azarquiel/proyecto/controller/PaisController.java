package com.azarquiel.proyecto.controller;

import com.azarquiel.proyecto.dto.PaisDto;
import com.azarquiel.proyecto.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/paises")


public class PaisController {
    private PaisService paisService;
    @Autowired
    public PaisController(PaisService paisService) {
        this.paisService = paisService;
    }
    @GetMapping("/pais")
    public String pais(Model modelo) {
        List<PaisDto> paises=paisService.findAll();
        modelo.addAttribute("paises", paises);
        return "paises";
    }
    @GetMapping("/buscar")
    @ResponseBody
    public List<PaisDto> buscarPaises(@RequestParam(required = false) String query) {
    if(query==null || query.trim().isEmpty()) {
        return paisService.findAll();
    }

    return paisService.buscarPorNombre(query);
    }
}
