package com.ecommerce.service;

import com.ecommerce.model.Articulo;
import com.ecommerce.repository.ArticuloRepository;
import com.ecommerce.repository.FotoArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticuloService {

    @Autowired
    private ArticuloRepository articuloRepository;

    @Autowired
    private FotoArticuloRepository fotoArticuloRepository;

    public Articulo registrarArticulo(Articulo articulo, byte[] foto) {
        articulo.setFoto(foto);
        return articuloRepository.save(articulo);
    }

    public List<Articulo> buscarArticulos(String keyword) {
        return articuloRepository.findByNombreContainingOrDescripcionContaining(keyword, keyword);
    }

    public Optional<Articulo> getArticuloById(Long id) {
        return articuloRepository.findById(id);
    }

    public Articulo actualizarArticulo(Articulo articulo) {
        return articuloRepository.save(articulo);
    }

    public void eliminarArticulo(Long id) {
        articuloRepository.deleteById(id);
    }
}
