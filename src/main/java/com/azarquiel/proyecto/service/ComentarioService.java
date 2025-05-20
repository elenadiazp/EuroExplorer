package com.azarquiel.proyecto.service;

import com.azarquiel.proyecto.dto.RutaDto;
import com.azarquiel.proyecto.entidades.Comentario;
import com.azarquiel.proyecto.entidades.Ruta;
import com.azarquiel.proyecto.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ComentarioService {
    Comentario crearComentario(Usuario usuario, RutaDto ruta, String textoComentario);
    Comentario editarComentario(Usuario usuario, RutaDto ruta, String nuevoTexto);
    Comentario findByUsuarioAndRuta(Usuario usuario, RutaDto ruta);
    List<Comentario> findAllByRuta(RutaDto rutaDto);
    void eliminarComentario(Usuario usuario, RutaDto rutaDto);
    Page<Comentario>findComentarioByRuta(Long rutaId, int pagina, int tamanio);
}
