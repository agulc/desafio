package com.agulc.apidanaide.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agulc.apidanaide.entities.Producto;
import com.agulc.apidanaide.entities.Usuario;
import com.agulc.apidanaide.repositories.UsuarioRepository;
import com.agulc.apidanaide.services.UsuarioService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{

    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearUsuario'");
    }

    @Override
    public Usuario getUsuarioById(long idUsuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUsuarioById'");
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllUsuarios'");
    }

    @Override
    public Usuario updateUsuario(Usuario usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUsuario'");
    }

    @Override
    public void deleteUsuario(Long idUsuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUsuario'");
    }

    @Override
    public List<Producto> getFourMostExpensiveProductos(Long dniUsuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFourMostExpensiveProductos'");
    }
    
}
