package com.azarquiel.proyecto.dto;

import com.azarquiel.proyecto.entidades.InfoPractica;
import com.azarquiel.proyecto.entidades.Region;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.azarquiel.proyecto.entidades.Pais}
 */
public class PaisDto implements Serializable {
    private final Long id;
    private final String nombre;
    private final String urlImagen;
    private final String info;
    private final Set<InfoPractica> infoPracticas;
    private final Set<Region> regions;

    public PaisDto(Long id, String nombre, String urlImagen, String info, Set<InfoPractica> infoPracticas, Set<Region> regions) {
        this.id = id;
        this.nombre = nombre;
        this.urlImagen = urlImagen;
        this.info = info;
        this.infoPracticas = infoPracticas;
        this.regions = regions;
    }

    public PaisDto(Long id, String nombre, String urlImagen ) {
        this.id = id;
        this.nombre = nombre;
        this.urlImagen = urlImagen;
        this.info = null;
        this.infoPracticas = null;
        this.regions = null;
    }

    public PaisDto(Long id, String nombre, String info, Set<InfoPractica> infoPracticas) {
        this.id = id;
        this.nombre = nombre;
        this.urlImagen = null;
        this.info = info;
        this.infoPracticas = infoPracticas;
        this.regions = null;
    }
    public PaisDto(Long id, String nombre, String info, Set<InfoPractica> infoPracticas, Set<Region> regions) {
        this.id = id;
        this.nombre = nombre;
        this.urlImagen = null;
        this.info = info;
        this.infoPracticas = infoPracticas;
        this.regions = regions;
    }



    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public String getInfo() {
        return info;
    }

    public Set<InfoPractica> getInfoPracticas() {
        return infoPracticas;
    }

    public Set<Region> getRegions() {
        return regions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaisDto entity = (PaisDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.nombre, entity.nombre) &&
                Objects.equals(this.urlImagen, entity.urlImagen) &&
                Objects.equals(this.info, entity.info) &&
                Objects.equals(this.infoPracticas, entity.infoPracticas) &&
                Objects.equals(this.regions, entity.regions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, urlImagen, info, infoPracticas, regions);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "nombre = " + nombre + ", " +
                "urlImagen = " + urlImagen + ", " +
                "info = " + info + ", " +
                "infoPracticas = " + infoPracticas + ", " +
                "regions = " + regions + ")";
    }
}