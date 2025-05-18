package com.azarquiel.proyecto.entidades;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "INFO_PRACTICA")
public class InfoPractica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_INFO_PRACTICA", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ID_PAIS", nullable = false)
    private Pais idPais;

    @Column(name = "IDIOMA", length = 200)
    private String idioma;

    @Column(name = "ZONA_HORARIA", length = 100)
    private String zonaHoraria;

    @Column(name = "ENCHUFE", length = 200)
    private String enchufe;

    @Column(name = "PREFIJO", length = 50)
    private String prefijo;

    @Column(name = "MONEDA", length = 200)
    private String moneda;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pais getIdPais() {
        return idPais;
    }

    public void setIdPais(Pais idPais) {
        this.idPais = idPais;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getZonaHoraria() {
        return zonaHoraria;
    }

    public void setZonaHoraria(String zonaHoraria) {
        this.zonaHoraria = zonaHoraria;
    }

    public String getEnchufe() {
        return enchufe;
    }

    public void setEnchufe(String enchufe) {
        this.enchufe = enchufe;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

}