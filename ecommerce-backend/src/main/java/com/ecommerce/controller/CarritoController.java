package com.ecommerce.controller;

import com.ecommerce.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @PostMapping("/comprar/{idArticulo}")
    public String comprarArticulo(@PathVariable Long idArticulo, @RequestParam("cantidad") int cantidad) {
        return carritoService.comprarArticulo(idArticulo, cantidad);
    }

    @DeleteMapping("/eliminar/{idArticulo}")
    public String eliminarArticuloDelCarrito(@PathVariable Long idArticulo) {
        return carritoService.eliminarArticuloDelCarrito(idArticulo);
    }

    @DeleteMapping("/vaciar")
    public String vaciarCarrito() {
        return carritoService.vaciarCarrito();
    }
}
