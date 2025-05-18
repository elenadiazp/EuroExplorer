package com.azarquiel.proyecto.service;

import com.azarquiel.proyecto.daos.UsuarioRespository;
import com.azarquiel.proyecto.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class UsuarioServiceImplements implements UsuarioService{

    @Autowired
    private UsuarioRespository usuarioRespository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Usuario registrar(Usuario usuario) {
        if(usuarioRespository.findByCorreoElectronico(usuario.getCorreoElectronico()).isPresent()){
            throw new RuntimeException("El correo electronico ya existe");
        }
        System.out.println("antes cifrar" + usuario.getContrasena());
       //usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
String encode = passwordEncoder.encode(usuario.getContrasena());

        System.out.println("cifrado" + encode);
usuario.setContrasena(encode);
        return usuarioRespository.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorCorreo(String correoElectronico) {
        return usuarioRespository.findByCorreoElectronico(correoElectronico);
    }
}
