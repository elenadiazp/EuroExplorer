package com.azarquiel.proyecto.dto;

import com.azarquiel.proyecto.entidades.Ruta;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.azarquiel.proyecto.entidades.Region}
 */
public class RegionDto implements Serializable {
    private final Long id;
    private final String nombre;
    private final PaisDto idPais;
    private final Set<Ruta> rutas;

    public RegionDto(Long id, String nombre, PaisDto idPais, Set<Ruta> rutas) {
        this.id = id;
        this.nombre = nombre;
        this.idPais = idPais;
        this.rutas = rutas;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public PaisDto getIdPais() {
        return idPais;
    }

    public Set<Ruta> getRutas() {
        return rutas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegionDto entity = (RegionDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.nombre, entity.nombre) &&
                Objects.equals(this.idPais, entity.idPais) &&
                Objects.equals(this.rutas, entity.rutas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, idPais, rutas);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "nombre = " + nombre + ", " +
                "idPais = " + idPais + ", " +
                "rutas = " + rutas + ")";
    }
}