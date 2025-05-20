package com.azarquiel.proyecto.daos;

import com.azarquiel.proyecto.entidades.Ruta;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;


public interface RutaRepository extends JpaRepository<Ruta, Integer> {
    @Query("SELECT r FROM Ruta r WHERE r.idregion.idPais.id = :paisId")
    List<Ruta> findByPaisId(@Param("paisId") Long paisId);
    Optional<Ruta> findById(Long id);
    Page<Ruta> findByIdtiporutaNombreIgnoreCase(String nombre, Pageable pageable);//rutas por tipo paginacion
    List<Ruta>findByIdtiporutaNombreIgnoreCase(String nombre); //rutas sin apginación
    @Query("SELECT r FROM Ruta r WHERE r.idregion.idPais.id = :paisId"
            + " AND (:tiposRutaIds IS NULL OR r.idtiporuta.id IN :tiposRutaIds)"
            + " AND (:regionIds IS NULL OR r.idregion.id IN :regionIds)")
    List<Ruta> findByFiltros(
            @Param("paisId") Long paisId,
            @Param("tiposRutaIds") List<Long> tiposRutaIds,
            @Param("regionIds") List<Long> regionIds
    );

    @Query("SELECT r FROM Ruta r JOIN r.puntuacions p " +
            "GROUP BY r.id, r.direccion, r.foto1, r.foto2, r.idregion, r.idtiporuta, r.intinerario, r.nombre, r.otrosDatos " +
            "ORDER BY AVG(p.puntuacion) DESC")
    Page<Ruta> findTopRutasByPuntuacionMedia(Pageable pageable);




}
