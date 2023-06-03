package com.agulc.apidanaide.services;

import java.util.List;

import com.agulc.apidanaide.entities.Producto;

public interface ProductoService {
    
    Producto crearProducto(Producto producto);

    Producto getProductoById(long idProducto);

    List<Producto> getAllProductos();

    Producto updateProducto(Producto producto);

    void deleteProducto(Long idProducto);
}
