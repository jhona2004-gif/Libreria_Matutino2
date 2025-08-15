package com.distribuida.service;

import com.distribuida.model.FacturaDetalle;

import java.util.List;

public interface FacturaDetalleService {
    public List<FacturaDetalle> findAll();
    public FacturaDetalle findOne(int id);
    public FacturaDetalle save(FacturaDetalle facturaDetalle);
    public FacturaDetalle update(int id, int idFactura,int idLibro, FacturaDetalle facturaDetalle);
    public void delete(int id);
}
