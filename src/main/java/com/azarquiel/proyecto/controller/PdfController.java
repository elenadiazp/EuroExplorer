package com.azarquiel.proyecto.controller;

import com.azarquiel.proyecto.dto.PaisDto;
import com.azarquiel.proyecto.dto.RegionDto;
import com.azarquiel.proyecto.dto.RutaDto;
import com.azarquiel.proyecto.entidades.Comentario;
import com.azarquiel.proyecto.entidades.InfoPractica;
import com.azarquiel.proyecto.entidades.Pais;
import com.azarquiel.proyecto.service.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
public class PdfController {
    private final SpringTemplateEngine templateEngine;
    private RutaService rutaService;
    private PaisService paisService;

    private ComentarioService comentarioService;


    @Autowired
    public PdfController(RutaService rutaService, PaisService paisService, ComentarioService comentarioService, SpringTemplateEngine templateEngine) {
        this.rutaService = rutaService;
        this.paisService = paisService;

        this.comentarioService = comentarioService;
        this.templateEngine = templateEngine;
    }


    @GetMapping("/rutas/{id}/pdf")
    public void exportarPdf(@PathVariable Long id, HttpServletResponse response) throws Exception {
        RutaDto ruta=rutaService.findById(id);
        Long idPais= ruta.getIdregion().getIdPais().getId();
        PaisDto pais= paisService.findById(idPais);
        List<Comentario> comentarios = comentarioService.findAllByRuta(ruta);

        String logoRuta = new File("src/main/resources/static/img/logo.jpg").getAbsolutePath();
        String logoUrl = "file:///"+logoRuta.replace("\\", "/");

        Context context = new Context();
        context.setVariable("pais", pais);
        context.setVariable("comentarios", comentarios);
        context.setVariable("ruta", ruta);
        context.setVariable("logoUrl", logoUrl);


        String html = templateEngine.process("ruta-pdf", context);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=ruta-" + id +".pdf");
        try (OutputStream outputStream = response.getOutputStream()){
            renderer.createPDF(outputStream);
        }


    }
}
