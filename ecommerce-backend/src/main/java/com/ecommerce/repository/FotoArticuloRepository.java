package com.ecommerce.repository;

import com.ecommerce.model.FotoArticulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoArticuloRepository extends JpaRepository<FotoArticulo, Long> {
    FotoArticulo findByIdArticulo(Long idArticulo);
}
