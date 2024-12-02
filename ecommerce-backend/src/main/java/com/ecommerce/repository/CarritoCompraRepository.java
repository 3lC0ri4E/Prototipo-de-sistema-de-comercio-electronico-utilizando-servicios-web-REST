package com.ecommerce.repository;

import com.ecommerce.model.CarritoCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoCompraRepository extends JpaRepository<CarritoCompra, Long> {
}
