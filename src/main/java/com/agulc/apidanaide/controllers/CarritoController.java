package com.agulc.apidanaide.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agulc.apidanaide.services.CarritoService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/carritos")
public class CarritoController {

    private CarritoService carritoService;

    @PostMapping
    public ResponseEntity<Long> createCarrito(@RequestBody Long dni){
        Long savedCarrito = carritoService.createCarrito(dni);
        return new ResponseEntity<Long>(savedCarrito, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCarrito(@RequestBody Long idCarrito){
        carritoService.deleteCarrito(idCarrito);
        return new ResponseEntity<String>("Carrito eliminado", HttpStatus.OK);
    }
    
}
