package com.agulc.apidanaide.entities;

import java.util.Set;

import com.agulc.apidanaide.entities.types.TipoCarrito;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import lombok.ToString;


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

    @ManyToOne
    @JoinColumn(name = "fk_usuario", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "carrito"/*, cascade = CascadeType.REMOVE, orphanRemoval = true*/)
    private Set<Compra> compras;

    public String toString(){
        return "{'id': 2, 'tipoCarrito': 'vip' }";
    }
}
