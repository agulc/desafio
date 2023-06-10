package com.agulc.apidanaide.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

//import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
//import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    @JsonIgnore
    private Long id;

    @Column(name = "dni", nullable = false, unique = true)
    //@Min(1000000)
    //@Max(999999999)
    private Long dni;

    @Column(name= "es_Vip", nullable = false)
    private Boolean esVip;

    @OneToMany(mappedBy = "usuario"/*, cascade = CascadeType.REMOVE, orphanRemoval = true*/)
    @JsonManagedReference
    private Set<Carrito> carritos;

}
