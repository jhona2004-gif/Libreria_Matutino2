package com.distribuida.service;

import com.distribuida.dao.ClienteRepository;
import com.distribuida.dao.FacturaRepository;
import com.distribuida.model.Cliente;
import com.distribuida.model.Factura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaServiceImpl implements FacturaService{

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private ClienteRepository clienteRepository;


    @Override
    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    @Override
    public Factura findOne(int id) {
        Optional<Factura> factura = facturaRepository.findById(id);
        return factura.orElse(null);
    }

    @Override
    public Factura save(Factura factura) {

        return facturaRepository.save(factura);
    }

    @Override
    public Factura update(int id,int idCliente, Factura factura) {
        Factura facturaexistente = findOne(id);

        Optional<Cliente> clienteExistente = clienteRepository.findById(idCliente);

        if (facturaexistente == null){
            return null;
        }

        facturaexistente.setNumFactura(factura.getNumFactura());
        facturaexistente.setFecha(factura.getFecha());
        facturaexistente.setIva(factura.getIva());
        facturaexistente.setTotalNeto(factura.getTotalNeto());
        facturaexistente.setTotal(factura.getTotal());
        facturaexistente.setCliente(clienteExistente.orElse(null));

        return facturaRepository.save(facturaexistente);
    }

    @Override
    public void delete(int id) {
    if (facturaRepository.existsById(id)){
        facturaRepository.deleteById(id);
    }
    }


}
