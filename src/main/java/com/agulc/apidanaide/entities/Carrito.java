package com.agulc.apidanaide.entities;

import java.util.Set;
import java.math.BigDecimal;
import java.math.RoundingMode;


import com.agulc.apidanaide.entities.types.TipoCarrito;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "carritos")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrito", nullable = false)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipoCarrito")
    private TipoCarrito tipo;

    @Column(name = "total", precision = 10, scale = 2)
    private BigDecimal total;

    @Column(name = "total_descuento", precision = 10, scale = 2)
    private BigDecimal totalConDescuento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_usuario", nullable = false)
    @JsonBackReference
    private Usuario usuario;

    @OneToMany(mappedBy = "carrito"/*, cascade = CascadeType.REMOVE, orphanRemoval = true*/)
    @JsonManagedReference
    private Set<Compra> compras;

    public void agregarPrecio(BigDecimal precio){
        this.total = this.total.add(precio.setScale(2, RoundingMode.HALF_UP));
    }

    public void restarPrecio(BigDecimal precio){
        this.total = this.total.subtract(precio.setScale(2, RoundingMode.HALF_UP));
    }

    public void guardarDescuento(BigDecimal precio){
        this.totalConDescuento = precio.setScale(2, RoundingMode.HALF_UP);
    }
}
