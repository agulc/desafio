package com.agulc.apidanaide.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agulc.apidanaide.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
}
