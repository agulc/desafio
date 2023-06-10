package com.agulc.apidanaide.controllers;

import com.agulc.apidanaide.entities.Usuario;
import com.agulc.apidanaide.services.UsuarioService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/usuarios")
public class UsuarioController {
    
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<String> getMethodName(@RequestBody Long id) {
        Usuario usuario = usuarioService.getUsuarioById(id); 
        return new ResponseEntity<>(usuario.toString(), HttpStatus.OK) ;
    }
    
}
