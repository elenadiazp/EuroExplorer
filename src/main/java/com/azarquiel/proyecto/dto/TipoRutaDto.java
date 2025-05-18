package com.azarquiel.proyecto.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.azarquiel.proyecto.entidades.TipoRuta}
 */
public class TipoRutaDto implements Serializable {
    private final Long id;
    private final String nombre;
    private final Set<RutaDto> rutas;

    public TipoRutaDto(Long id, String nombre, Set<RutaDto> rutas) {
        this.id = id;
        this.nombre = nombre;
        this.rutas = rutas;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Set<RutaDto> getRutas() {
        return rutas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoRutaDto entity = (TipoRutaDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.nombre, entity.nombre) &&
                Objects.equals(this.rutas, entity.rutas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, rutas);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "nombre = " + nombre + ", " +
                "rutas = " + rutas + ")";
    }
}