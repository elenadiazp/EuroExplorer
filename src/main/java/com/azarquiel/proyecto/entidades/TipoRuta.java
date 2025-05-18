package com.azarquiel.proyecto.entidades;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "TIPO_RUTA")
public class TipoRuta {
    @Id
    @Column(name = "ID_TIPO_RUTA", nullable = false)
    private Long id;

    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;

    @OneToMany(mappedBy = "idtiporuta")
    private Set<Ruta> rutas = new LinkedHashSet<>();

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

    public Set<Ruta> getRutas() {
        return rutas;
    }

    public void setRutas(Set<Ruta> rutas) {
        this.rutas = rutas;
    }

}