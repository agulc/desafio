package com.agulc.apidanaide.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agulc.apidanaide.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    Optional<Usuario> findFirstByDni(Long dniUsuario);
    
}
