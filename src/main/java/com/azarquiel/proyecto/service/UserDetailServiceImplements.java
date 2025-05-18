package com.azarquiel.proyecto.service;

import com.azarquiel.proyecto.daos.UsuarioRespository;
import com.azarquiel.proyecto.dto.UsuarioDto;
import com.azarquiel.proyecto.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailServiceImplements implements UserDetailsService {

    @Autowired
    private UsuarioRespository usuarioRespository;




    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario usuario = usuarioRespository.findByCorreoElectronico(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado" + correo));
        //UsuarioDto usuarioDto= new UsuarioDto(usuario.getId(),usuario.getNombre(), usuario.getCorreoElectronico(), usuario.getContrasena());
        System.out.println("Correo recuperado " + usuario.getCorreoElectronico());
        System.out.println("Contraseña recuperada " +  usuario.getContrasena());

    return  new org.springframework.security.core.userdetails.User(
            usuario.getCorreoElectronico(), usuario.getContrasena(), AuthorityUtils.createAuthorityList("ROLE_USER"));

    }
}
