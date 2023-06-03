package com.agulc.apidanaide.services;

import java.util.List;

import com.agulc.apidanaide.entities.Carrito;

public interface CarritoService {
    
    Long crearCarrito(Long dniUsuario); // returns ID carrito

    Carrito getCarritoById(Long idCarrito);

    Carrito addProductoToCarrito(Long idCarrito, Long idProducto);

    Carrito removeProductoFromCarrito(Long idCarrito, Long idProducto);

    List<Carrito> getAllCarritos();

    Carrito updateCarrito(Carrito carrito);

    void deleteCarrito(Long idCarrito);
}
