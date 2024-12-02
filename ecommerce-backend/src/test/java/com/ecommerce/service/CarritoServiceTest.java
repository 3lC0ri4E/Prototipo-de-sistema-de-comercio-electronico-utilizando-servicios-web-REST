package com.ecommerce.service;

import com.ecommerce.model.Articulo;
import com.ecommerce.model.CarritoCompra;
import com.ecommerce.repository.CarritoCompraRepository;
import com.ecommerce.repository.ArticuloRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class CarritoServiceTest {

    @Mock
    private CarritoCompraRepository carritoCompraRepository;

    @Mock
    private ArticuloRepository articuloRepository;

    @InjectMocks
    private CarritoService carritoService;

    private Articulo articulo;
    private CarritoCompra carritoCompra;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        articulo = new Articulo();
        articulo.setIdArticulo(1L);
        articulo.setNombre("Mayonesa");
        articulo.setCantidad(100);

        carritoCompra = new CarritoCompra();
        carritoCompra.setIdArticulo(1L);
        carritoCompra.setCantidad(2);
    }

    @Test
    void comprarArticulo() {
        when(articuloRepository.findById(1L)).thenReturn(java.util.Optional.of(articulo));
        when(carritoCompraRepository.save(any(CarritoCompra.class))).thenReturn(carritoCompra);

        String result = carritoService.comprarArticulo(1L, 2);

        assertEquals("Compra realizada con éxito", result);
        verify(carritoCompraRepository, times(1)).save(any(CarritoCompra.class));
        verify(articuloRepository, times(1)).save(any(Articulo.class));
    }
}
