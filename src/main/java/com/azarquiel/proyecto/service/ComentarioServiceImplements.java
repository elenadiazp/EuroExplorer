package com.azarquiel.proyecto.service;

import com.azarquiel.proyecto.daos.ComentarioRepository;
import com.azarquiel.proyecto.dto.RutaDto;
import com.azarquiel.proyecto.entidades.Comentario;
import com.azarquiel.proyecto.entidades.Ruta;
import com.azarquiel.proyecto.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ComentarioServiceImplements implements ComentarioService{
    private ComentarioRepository comentarioRepository;
    private RutaService rutaService;
    @Autowired
  public ComentarioServiceImplements(ComentarioRepository comentarioRepository, RutaService rutaService) {
        this.comentarioRepository = comentarioRepository;
        this.rutaService = rutaService;
    }


    @Override
    public Comentario crearComentario(Usuario usuario, RutaDto rutaDto, String textoComentario) {
        Ruta ruta =  new Ruta();
        ruta.setId(rutaDto.getId());

        Optional<Comentario> existentes = comentarioRepository.findByIdUsuarioAndIdRuta(usuario, ruta );
        if (existentes.isPresent()) {
            throw new IllegalStateException("Ya has comentado esta ruta");
        }
        Comentario comentario = new Comentario();
        comentario.setIdUsuario(usuario);
        comentario.setIdRuta(ruta);
        comentario.setComentario(textoComentario);
        return comentarioRepository.save(comentario);
    }

    @Override
    public Comentario editarComentario(Usuario usuario, RutaDto rutaDto, String nuevoTexto) {
        Ruta ruta =  new Ruta();
        ruta.setId(rutaDto.getId());

        Comentario comentarionuevo= comentarioRepository.findByIdUsuarioAndIdRuta(usuario, ruta).orElseThrow(()-> new IllegalStateException("No se encontró un comentario para editar") );
        comentarionuevo.setComentario(nuevoTexto);
        comentarionuevo.setFecha(LocalDateTime.now());
        return comentarioRepository.save(comentarionuevo);
    }

    @Override
    public Comentario findByUsuarioAndRuta(Usuario usuario, RutaDto rutaDto) {
        Ruta ruta = new Ruta();
        ruta.setId(rutaDto.getId());

        return comentarioRepository.findByIdUsuarioAndIdRuta(usuario,ruta).orElse(null);
    }

    @Override
    public List<Comentario> findAllByRuta(RutaDto rutaDto) {
        Ruta ruta = new Ruta();
        ruta.setId(rutaDto.getId());
        return comentarioRepository.findAllByIdRuta(ruta);
    }

    @Override
    public void eliminarComentario(Usuario usuario, RutaDto rutaDto) {
        Ruta ruta = new Ruta();
        ruta.setId(rutaDto.getId());
        comentarioRepository.findByIdUsuarioAndIdRuta(usuario, ruta).ifPresent(comentarioRepository::delete);
    }

    @Override
    public Page<Comentario> findComentarioByRuta(Long rutaId, int pagina, int tamanio) {


        Pageable pageable = PageRequest.of(pagina, tamanio, Sort.by("fecha").descending());

        return comentarioRepository.findAllByIdRuta_IdOrderByFechaDesc(rutaId, pageable);
    }


}
