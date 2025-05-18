package com.azarquiel.proyecto.entidades;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "PAIS")
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PAIS", nullable = false)
    private Long id;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "URL_IMAGEN")
    private String urlImagen;

    @Lob
    @Column(name = "INFO")
    private String info;

    @OneToMany(mappedBy = "idPais")
    private Set<InfoPractica> infoPracticas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idPais")
    private Set<Region> regions = new LinkedHashSet<>();

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

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Set<InfoPractica> getInfoPracticas() {
        return infoPracticas;
    }

    public void setInfoPracticas(Set<InfoPractica> infoPracticas) {
        this.infoPracticas = infoPracticas;
    }

    public Set<Region> getRegions() {
        return regions;
    }

    public void setRegions(Set<Region> regions) {
        this.regions = regions;
    }

}