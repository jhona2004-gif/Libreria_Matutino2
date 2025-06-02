package com.distribuida.dao;

import com.distribuida.model.Cliente;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class ClienteRepositorioTestIntegracion {

    @Autowired //anotacion para inyectar dependencias
    private ClienteRepository clienteRepository;

    @Test
    public void findAll(){
        List<Cliente> clientes = clienteRepository.findAll();

        for (Cliente item: clientes){
            System.out.println(item.toString());
        }

    }
    @Test
    public void findOne(){
        Optional<Cliente> cliente = clienteRepository.findById(1);
        System.out.println(cliente.toString());
    }

    @Test
    public void save(){

        Cliente cliente = new Cliente(0,"1754399507","Juan","por all","Flores","0983701160","juan@gmail.com");
    }

@Test
    public void update(){

        Optional<Cliente> cliente = clienteRepository.findById(39);
        cliente.orElse(null).setCedula("175499507");
        cliente.orElse(null).setNombre("Juan3");
        cliente.orElse(null).setDireccion("aca");
        cliente.orElse(null).setApellido("flores");
        cliente.orElse(null).setTelefono("0989749845");
        cliente.orElse(null).setCorreo("juan2@gmail.com");



    }


}
