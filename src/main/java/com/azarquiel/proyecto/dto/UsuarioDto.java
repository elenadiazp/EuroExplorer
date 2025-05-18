package com.azarquiel.proyecto.dto;

import com.azarquiel.proyecto.entidades.Comentario;
import com.azarquiel.proyecto.entidades.Favorito;
import com.azarquiel.proyecto.entidades.Puntuacion;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.azarquiel.proyecto.entidades.Usuario}
 */
public class UsuarioDto implements Serializable {
    private final Long id;
    private final String nombre;
    private final String correoElectronico;
    private final String contrasena;
    private final Set<Comentario> comentarios;
    private final Set<Favorito> favoritos;
    private final Set<Puntuacion> puntuacions;

    public UsuarioDto(Long id, String nombre, String correoElectronico, String contrasena, Set<Comentario> comentarios, Set<Favorito> favoritos, Set<Puntuacion> puntuacions) {
        this.id = id;
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;

        this.comentarios = comentarios;
        this.favoritos = favoritos;
        this.puntuacions = puntuacions;
    }
    public UsuarioDto(Long id,String correoElectronico, String contrasena, String nombre) {
        this.id = id;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.comentarios = null;
        this.favoritos = null;
        this.puntuacions = null;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }



    public Set<Comentario> getComentarios() {
        return comentarios;
    }

    public Set<Favorito> getFavoritos() {
        return favoritos;
    }

    public Set<Puntuacion> getPuntuacions() {
        return puntuacions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioDto entity = (UsuarioDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.nombre, entity.nombre) &&
                Objects.equals(this.correoElectronico, entity.correoElectronico) &&
                Objects.equals(this.contrasena, entity.contrasena)  &&
                Objects.equals(this.comentarios, entity.comentarios) &&
                Objects.equals(this.favoritos, entity.favoritos) &&
                Objects.equals(this.puntuacions, entity.puntuacions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, correoElectronico, contrasena, comentarios, favoritos, puntuacions);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "nombre = " + nombre + ", " +
                "correoElectronico = " + correoElectronico + ", " +
                "contrasena = " + contrasena + ", " +
                "comentarios = " + comentarios + ", " +
                "favoritos = " + favoritos + ", " +
                "puntuacions = " + puntuacions + ")";
    }
}