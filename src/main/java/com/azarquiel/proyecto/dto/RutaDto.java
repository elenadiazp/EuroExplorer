package com.azarquiel.proyecto.dto;

import com.azarquiel.proyecto.entidades.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.azarquiel.proyecto.entidades.Ruta}
 */
public class RutaDto implements Serializable {
    private final Long id;
    private final TipoRuta idtiporuta;
    private final Region idregion;
    private final String foto1;
    private final String foto2;
    private final String nombre;
    private final String intinerario;
    private final String otrosDatos;
    private final String direccion;
    private final Set<Comentario> comentarios;
    private final Set<Favorito> favoritos;
    private final Set<Puntuacion> puntuacions;
    private final String nombrePais;

    public RutaDto(Long id, TipoRuta idtiporuta, Region idregion, String foto1, String foto2, String nombre, String intinerario, String otrosDatos, String direccion, Set<Comentario> comentarios, Set<Favorito> favoritos, Set<Puntuacion> puntuacions) {
        this.id = id;
        this.idtiporuta = idtiporuta;
        this.idregion = idregion;
        this.foto1 = foto1;
        this.foto2 = foto2;
        this.nombre = nombre;
        this.intinerario = intinerario;
        this.otrosDatos = otrosDatos;
        this.direccion = direccion;
        this.comentarios = comentarios;
        this.favoritos = favoritos;
        this.puntuacions = puntuacions;
        this.nombrePais=null;
    }

    public RutaDto(Long id, TipoRuta idtiporuta, Region idregion, String foto1,  String nombre) {
        this.id = id;
        this.idtiporuta = idtiporuta;
        this.idregion = idregion;
        this.foto1 = foto1;
        this.foto2 = null;
        this.nombre = nombre;
        this.intinerario = null;
        this.otrosDatos = null;
        this.direccion = null;
        this.comentarios = null;
        this.favoritos = null;
        this.puntuacions = null;
        this.nombrePais=null;
    }
    public RutaDto(Long id, TipoRuta idtiporuta, Region idregion, String foto1,  String nombre, String nombrePais) {
        this.id = id;
        this.idtiporuta = idtiporuta;
        this.idregion = idregion;
        this.foto1 = foto1;
        this.foto2 = null;
        this.nombre = nombre;
        this.intinerario = null;
        this.otrosDatos = null;
        this.direccion = null;
        this.comentarios = null;
        this.favoritos = null;
        this.puntuacions = null;
        this.nombrePais=nombrePais;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public Long getId() {
        return id;
    }

    public TipoRuta getIdtiporuta() {
        return idtiporuta;
    }

    public Region getIdregion() {
        return idregion;
    }

    public String getFoto1() {
        return foto1;
    }

    public String getFoto2() {
        return foto2;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIntinerario() {
        return intinerario;
    }

    public String getOtrosDatos() {
        return otrosDatos;
    }

    public String getDireccion() {
        return direccion;
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
        RutaDto entity = (RutaDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.idtiporuta, entity.idtiporuta) &&
                Objects.equals(this.idregion, entity.idregion) &&
                Objects.equals(this.foto1, entity.foto1) &&
                Objects.equals(this.foto2, entity.foto2) &&
                Objects.equals(this.nombre, entity.nombre) &&
                Objects.equals(this.intinerario, entity.intinerario) &&
                Objects.equals(this.otrosDatos, entity.otrosDatos) &&
                Objects.equals(this.direccion, entity.direccion) &&
                Objects.equals(this.comentarios, entity.comentarios) &&
                Objects.equals(this.favoritos, entity.favoritos) &&
                Objects.equals(this.puntuacions, entity.puntuacions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idtiporuta, idregion, foto1, foto2, nombre, intinerario, otrosDatos, direccion, comentarios, favoritos, puntuacions);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "idtiporuta = " + idtiporuta + ", " +
                "idregion = " + idregion + ", " +
                "foto1 = " + foto1 + ", " +
                "foto2 = " + foto2 + ", " +
                "nombre = " + nombre + ", " +
                "intinerario = " + intinerario + ", " +
                "otrosDatos = " + otrosDatos + ", " +
                "direccion = " + direccion + ", " +
                "comentarios = " + comentarios + ", " +
                "favoritos = " + favoritos + ", " +
                "puntuacions = " + puntuacions + ")";
    }
}