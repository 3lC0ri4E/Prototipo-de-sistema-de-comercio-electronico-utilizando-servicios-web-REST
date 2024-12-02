package com.ecommerce.controller;

import com.ecommerce.model.Articulo;
import com.ecommerce.service.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articulos")
public class ArticuloController {

    @Autowired
    private ArticuloService articuloService;

    @PostMapping("/registrar")
    public Articulo registrarArticulo(@RequestBody Articulo articulo, @RequestParam("foto") byte[] foto) {
        return articuloService.registrarArticulo(articulo, foto);
    }

    @GetMapping("/buscar")
    public List<Articulo> buscarArticulos(@RequestParam("keyword") String keyword) {
        return articuloService.buscarArticulos(keyword);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarArticulo(@PathVariable Long id) {
        articuloService.eliminarArticulo(id);
    }
}
