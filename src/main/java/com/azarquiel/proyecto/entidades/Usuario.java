package com.azarquiel.proyecto.entidades;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "USUARIO")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO", nullable = false)
    private Long id;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "CORREO_ELECTRONICO", nullable = false)
    private String correoElectronico;

    @Column(name = "CONTRASENA", nullable = false)
    private String contrasena;



    @OneToMany(mappedBy = "idUsuario")
    private Set<Comentario> comentarios = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUsuario")
    private Set<Favorito> favoritos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUsuario")
    private Set<Puntuacion> puntuacions = new LinkedHashSet<>();



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }



    public Set<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(Set<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Set<Favorito> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(Set<Favorito> favoritos) {
        this.favoritos = favoritos;
    }

    public Set<Puntuacion> getPuntuacions() {
        return puntuacions;
    }

    public void setPuntuacions(Set<Puntuacion> puntuacions) {
        this.puntuacions = puntuacions;
    }

}