package com.azarquiel.proyecto.service;

import com.azarquiel.proyecto.entidades.Usuario;

import java.util.Optional;

public interface UsuarioService {
    Usuario registrar(Usuario usuario);
    Optional<Usuario> buscarPorCorreo(String correoElectronico);
}
