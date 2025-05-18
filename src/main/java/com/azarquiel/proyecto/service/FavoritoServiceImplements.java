package com.azarquiel.proyecto.service;

import com.azarquiel.proyecto.daos.FavoritoRepository;
import com.azarquiel.proyecto.daos.RutaRepository;
import com.azarquiel.proyecto.daos.UsuarioRespository;

import com.azarquiel.proyecto.entidades.Favorito;
import com.azarquiel.proyecto.entidades.Ruta;
import com.azarquiel.proyecto.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoritoServiceImplements implements FavoritoService{
    @Autowired
    private FavoritoRepository favoritoRepository;

    @Autowired
    private UsuarioRespository usuarioRespository;

    @Autowired
    private RutaRepository rutaRepository;


    @Override
    public void guardarQuitarFavorito(Long idRuta, Long idUsuario) {
        Usuario usuario = usuarioRespository.findById(idUsuario).orElseThrow();
        Ruta ruta = rutaRepository.findById(idRuta).orElseThrow();

        Optional<Favorito> favoritoOptional = favoritoRepository.findByIdUsuarioAndIdRuta(usuario, ruta);
        if(favoritoOptional.isPresent()){
            favoritoRepository.delete(favoritoOptional.get());

        }else{
            Favorito favorito = new Favorito();
            favorito.setIdUsuario(usuario);
            favorito.setIdRuta(ruta);
            favoritoRepository.save(favorito);
        }

    }

    @Override
    public boolean esFavorito(Long idRuta, Long idUsuario) {
        Usuario usuario = usuarioRespository.findById(idUsuario).orElseThrow();
        Ruta ruta = rutaRepository.findById(idRuta).orElseThrow();
        return favoritoRepository.findByIdUsuarioAndIdRuta(usuario, ruta).isPresent();
    }

    @Override
    public List<Ruta> obtenerFavoritos(Long idUsuario) {
        System.out.println("comprobando idusuario antes obtener favoritos " + idUsuario);
        List<Favorito>favoritos=favoritoRepository.findByIdUsuario_Id(idUsuario);
        System.out.println("numero favoritos encontrado en el service " + favoritos.size());
        return favoritos.stream().map(Favorito::getIdRuta).toList();
    }
}
