package com.agulc.apidanaide.controllers;

import com.agulc.apidanaide.entities.Producto;
import com.agulc.apidanaide.entities.Usuario;
import com.agulc.apidanaide.services.UsuarioService;

import lombok.AllArgsConstructor;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/usuarios")
public class UsuarioController {
    
    private UsuarioService usuarioService;

    @GetMapping("{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable("id") Long id) {
        Usuario usuario = usuarioService.getUsuarioById(id); 
        return new ResponseEntity<>(usuario, HttpStatus.OK) ;
    }

    @GetMapping
    public ResponseEntity<ArrayList<Producto>> getMasCaros(@RequestBody Long dni){
        ArrayList<Producto> productos =  usuarioService.getFourMostExpensiveProductos(dni);
        return new ResponseEntity<ArrayList<Producto>>(productos, HttpStatus.OK);

    }
    
}
