package com.ecommerce.service;

import com.ecommerce.model.Articulo;
import com.ecommerce.repository.ArticuloRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ArticuloServiceTest {

    @Mock
    private ArticuloRepository articuloRepository;

    @InjectMocks
    private ArticuloService articuloService;

    private Articulo articulo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        articulo = new Articulo();
        articulo.setNombre("Mayonesa");
        articulo.setDescripcion("Mayonesa tradicional");
        articulo.setPrecio(5.99);
        articulo.setCantidad(100);
    }

    @Test
    void registrarArticulo() {
        when(articuloRepository.save(any(Articulo.class))).thenReturn(articulo);

        Articulo result = articuloService.registrarArticulo(articulo, new byte[0]);

        assertNotNull(result);
        assertEquals("Mayonesa", result.getNombre());
        verify(articuloRepository, times(1)).save(any(Articulo.class));
    }
}
