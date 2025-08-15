package com.distribuida.service;

import com.distribuida.dao.ClienteRepository;
import com.distribuida.dao.FacturaRepository;
import com.distribuida.model.Cliente;
import com.distribuida.model.Factura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FacturaServicioTestUnitaria {

    @Mock
    private FacturaRepository facturaRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private FacturaServiceImpl facturaService;
    private ClienteServiceImpl clienteService;



    private Factura factura;
    private Cliente cliente;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente(1,"1753","Juan","Pifo","Chupin","987","test@gmail.com");
        factura = new Factura(1,"FACC-001",new Date(),12.34,12.54,125.45, cliente);
    }

    @Test
    public void testFindAll(){
        when(facturaRepository.findAll()).thenReturn(Arrays.asList(factura));
        List<Factura>facturas = facturaService.findAll();
        assertNotNull(facturas);
        assertEquals(1,facturas.size());
        verify(facturaRepository,times(1)).findAll();
    }

    @Test
    public void testFindOne(){
        when(facturaRepository.findById(1)).thenReturn(Optional.of(factura));
        Factura factura = facturaService.findOne(1);
        assertNotNull(factura);
        assertEquals("FACC-001",factura.getNumFactura());
        verify(facturaRepository,times(1)).findById(1);
    }

    @Test
    public void save(){
        when(facturaRepository.save(factura)).thenReturn(factura);
        Factura factura1 = facturaService.save(factura);
        assertNotNull(factura1);
        assertEquals("FACC-001",factura.getNumFactura());
        verify(facturaRepository,times(1)).save(factura);
    }

    @Test
    public void update(){
        Factura facturaActualizada = new Factura(1,"FAC-002",new Date(),124.23,12.22,123.45,cliente);
        when(facturaRepository.findById(1)).thenReturn(Optional.of(factura));
        when(facturaRepository.save(any(Factura.class))).thenReturn(facturaActualizada);
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));
        Factura factura1 = facturaService.update(1,1,facturaActualizada);
        assertNotNull(factura1);
        assertEquals("FAC-002",factura1.getNumFactura());
        assertEquals(123.45,factura1.getTotal());
        verify(facturaRepository).save(any(Factura.class));
    }

    @Test
    public void testDelete(){
        when(facturaRepository.existsById(1)).thenReturn(false);
        facturaService.delete(1);
        verify(facturaRepository,times(0)).deleteById(1);
    }


}
