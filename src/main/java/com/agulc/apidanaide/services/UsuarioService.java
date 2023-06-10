package com.agulc.apidanaide.services;

import com.agulc.apidanaide.entities.Producto;
import com.agulc.apidanaide.entities.Usuario;

import java.util.ArrayList;
import java.util.List;

public interface UsuarioService {

    Usuario crearUsuario(Usuario usuario);

    Usuario getUsuarioById(Long idUsuario);

    List<Usuario> getAllUsuarios();

    Usuario updateUsuario(Usuario usuario);

    void deleteUsuario(Long idUsuario);

    ArrayList<Producto> getFourMostExpensiveProductos(Long dniUsuario);
}
