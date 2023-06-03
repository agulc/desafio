package com.agulc.apidanaide.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agulc.apidanaide.entities.Carrito;

public interface CarritoRepository extends JpaRepository<Carrito, Long>{
    
}
