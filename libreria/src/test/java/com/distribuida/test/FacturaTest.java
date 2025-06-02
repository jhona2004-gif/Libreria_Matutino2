package com.distribuida.test;

import org.distribuida.entities.Factura;
import org.distribuida.entities.cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FacturaTest {

    private  Factura factura;
    private  cliente cliente;

@BeforeEach
public void setUp () {

    factura = new Factura();

    cliente = new cliente(1, "1753523", "Aracely", "Chupin", "Tababela", "911", "correo@gmail.com");
    factura.setIdFactura(1);
    factura.setNumFactura("FAC-0001");
    factura.setFecha(new Date());
    factura.setTotalNeto(100.00);
    factura.setIva(15.00);
    factura.setTotal(115.00);
    // inyeccion de dependencias

    factura.setCliente(cliente);
}

    @Test
    public void testCLienteConstructorAndGetter(){

        assertAll("validar datos Cliente, Contructor y Getter",
                () -> assertEquals(1,factura.getIdFactura()),
                () -> assertEquals("FAC-0001",factura.getNumFactura()),
                //() -> assertEquals(new Date(), factura.getFecha()),
                () -> assertEquals(100.00, factura.getTotalNeto()),
                () -> assertEquals(15.00, factura.getIva()),
                () -> assertEquals(115.00, factura.getTotal()),
                () -> assertEquals("Aracely",factura.getCliente().getNombre())
        );



}
    @Test
    public void testFacturaToString(){

        String str = factura.toString();
        assertAll("Validacion Datos Fcatura",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("FAC-0001")),
                () -> assertTrue(str.contains("100.0")),
                () -> assertTrue(str.contains("15.0")),
                () -> assertTrue(str.contains("115.0")),
                () -> assertTrue(str.contains("Aracely")),// parte del objeto cliente
                () -> assertTrue(str.contains("911"))// parte del objeto cliente-inyeccion de dependencias
        );
    }


}


