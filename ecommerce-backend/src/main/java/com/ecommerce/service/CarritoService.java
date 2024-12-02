package com.ecommerce.service;

import com.ecommerce.model.CarritoCompra;
import com.ecommerce.model.Articulo;
import com.ecommerce.repository.CarritoCompraRepository;
import com.ecommerce.repository.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CarritoService {

    @Autowired
    private CarritoCompraRepository carritoCompraRepository;

    @Autowired
    private ArticuloRepository articuloRepository;

    @Transactional
    public String comprarArticulo(Long idArticulo, int cantidad) {
        Optional<Articulo> articuloOpt = articuloRepository.findById(idArticulo);
        if (articuloOpt.isPresent()) {
            Articulo articulo = articuloOpt.get();
            if (articulo.getCantidad() >= cantidad) {
                CarritoCompra carritoCompra = new CarritoCompra();
                carritoCompra.setIdArticulo(idArticulo);
                carritoCompra.setCantidad(cantidad);
                carritoCompraRepository.save(carritoCompra);

                articulo.setCantidad(articulo.getCantidad() - cantidad);
                articuloRepository.save(articulo);

                return "Compra realizada con éxito";
            } else {
                return "No hay suficientes artículos disponibles";
            }
        } else {
            return "Artículo no encontrado";
        }
    }

    @Transactional
    public String eliminarArticuloDelCarrito(Long idArticulo) {
        Optional<CarritoCompra> carritoCompraOpt = carritoCompraRepository.findById(idArticulo);
        if (carritoCompraOpt.isPresent()) {
            CarritoCompra carritoCompra = carritoCompraOpt.get();
            carritoCompraRepository.delete(carritoCompra);

            Optional<Articulo> articuloOpt = articuloRepository.findById(idArticulo);
            articuloOpt.ifPresent(articulo -> {
                articulo.setCantidad(articulo.getCantidad() + carritoCompra.getCantidad());
                articuloRepository.save(articulo);
            });

            return "Artículo eliminado del carrito";
        } else {
            return "Artículo no encontrado en el carrito";
        }
    }

    @Transactional
    public String vaciarCarrito() {
        carritoCompraRepository.findAll().forEach(carritoCompra -> {
            carritoCompraRepository.delete(carritoCompra);
            Optional<Articulo> articuloOpt = articuloRepository.findById(carritoCompra.getIdArticulo());
            articuloOpt.ifPresent(articulo -> {
                articulo.setCantidad(articulo.getCantidad() + carritoCompra.getCantidad());
                articuloRepository.save(articulo);
            });
        });
        return "Carrito vacío";
    }
}
