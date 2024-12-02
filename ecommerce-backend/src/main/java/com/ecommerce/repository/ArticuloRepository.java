package com.ecommerce.repository;

import com.ecommerce.model.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Long> {
    List<Articulo> findByNombreContainingOrDescripcionContaining(String nombre, String descripcion);
}
