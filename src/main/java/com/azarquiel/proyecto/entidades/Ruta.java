package com.azarquiel.proyecto.entidades;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "RUTA")
public class Ruta {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "IDTIPORUTA", nullable = false)
    private TipoRuta idtiporuta;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "IDREGION", nullable = false)
    private Region idregion;

    @Column(name = "FOTO1", length = 500)
    private String foto1;

    @Column(name = "FOTO2", length = 500)
    private String foto2;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "INTINERARIO", length = 2000)
    private String intinerario;

    @Column(name = "OTROS_DATOS", length = 2000)
    private String otrosDatos;

    @Column(name = "DIRECCION", length = 500)
    private String direccion;

    @OneToMany(mappedBy = "idRuta")
    private Set<Comentario> comentarios = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idRuta")
    private Set<Favorito> favoritos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idRuta")
    private Set<Puntuacion> puntuacions = new LinkedHashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoRuta getIdtiporuta() {
        return idtiporuta;
    }

    public void setIdtiporuta(TipoRuta idtiporuta) {
        this.idtiporuta = idtiporuta;
    }

    public Region getIdregion() {
        return idregion;
    }

    public void setIdregion(Region idregion) {
        this.idregion = idregion;
    }

    public String getFoto1() {
        return foto1;
    }

    public void setFoto1(String foto1) {
        this.foto1 = foto1;
    }

    public String getFoto2() {
        return foto2;
    }

    public void setFoto2(String foto2) {
        this.foto2 = foto2;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIntinerario() {
        return intinerario;
    }

    public void setIntinerario(String intinerario) {
        this.intinerario = intinerario;
    }

    public String getOtrosDatos() {
        return otrosDatos;
    }

    public void setOtrosDatos(String otrosDatos) {
        this.otrosDatos = otrosDatos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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