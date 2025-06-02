package com.azarquiel.proyecto.daos;


import com.azarquiel.proyecto.dto.UsuarioDto;
import com.azarquiel.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRespository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByCorreoElectronico(String correoElectronico);


}
