package com.agulc.apidanaide.services;

import java.util.List;

import com.agulc.apidanaide.entities.Compra;

public interface CompraService {
    
    Compra crearCompra(Compra compra);

    Compra getCompraById(long idCompra);

    Compra getCompraByCarritoId(long idCompra);

    List<Compra> getAllCompras();

    Compra updateCompra(Compra compra);

    void deleteCompra(Long idCompra);
}
