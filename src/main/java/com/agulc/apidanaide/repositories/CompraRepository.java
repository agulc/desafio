package com.agulc.apidanaide.repositories;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agulc.apidanaide.entities.Carrito;
import com.agulc.apidanaide.entities.Compra;
import com.agulc.apidanaide.entities.Producto;

public interface CompraRepository extends JpaRepository<Compra, Long>{

    Optional<Compra> findByCarritoAndProducto(Carrito carrito, Producto producto);
    
    Optional<List<Compra>> findByCarrito(Carrito carrito);
}
