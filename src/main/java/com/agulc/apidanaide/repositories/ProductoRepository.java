package com.agulc.apidanaide.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agulc.apidanaide.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{
    
}
