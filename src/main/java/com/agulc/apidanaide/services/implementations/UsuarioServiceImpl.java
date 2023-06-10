package com.agulc.apidanaide.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

import org.springframework.stereotype.Service;

import com.agulc.apidanaide.entities.Carrito;
import com.agulc.apidanaide.entities.Compra;
import com.agulc.apidanaide.entities.Producto;
import com.agulc.apidanaide.entities.Usuario;
import com.agulc.apidanaide.repositories.CarritoRepository;
import com.agulc.apidanaide.repositories.CompraRepository;
import com.agulc.apidanaide.repositories.ProductoRepository;
import com.agulc.apidanaide.repositories.UsuarioRepository;
import com.agulc.apidanaide.services.UsuarioService;
import com.fasterxml.jackson.core.io.SegmentedStringWriter;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{

    private CarritoRepository carritoRepository;
    private UsuarioRepository usuarioRepository;
    private ProductoRepository productoRepository;
    private CompraRepository compraRepository;

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearUsuario'");
    }

    @Override
    public Usuario getUsuarioById(Long idUsuario) {
        // TODO Auto-generated method stub
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(idUsuario);
        return optionalUsuario.get();
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
        // Este metodo es MUY ineficiente... pero está implementado.

        Optional<Usuario> existingUsuario = usuarioRepository.findFirstByDni(dniUsuario);
        Usuario usuario = existingUsuario.get();
        Set<Carrito> carritos = usuario.getCarritos();
        Set<Producto> productos = new HashSet<Producto>();

        for (Carrito carrito : carritos){
            Optional<List<Compra>> optAux = compraRepository.findByCarrito(carrito);
            if(optAux.isPresent()){
                List<Compra> aux = optAux.get();
                for(Compra compra: aux){
                    productos.add(compra.getProducto());
                }
            }

        }
        Producto primero = null;
        Producto segundo = null;
        Producto tercero = null;
        Producto cuarto = null;

        for(Producto producto: productos){
            if(primero == null){
                primero = producto;
                continue;
            }
            if(segundo == null){
                segundo = producto;
                continue;
            }
            if(tercero == null){
                tercero = producto;
                continue;
            }
            if(cuarto == null){
                cuarto = producto;
                continue;
            }

            if(producto.getPrecio().compareTo(primero.getPrecio()) >= 0){
                cuarto = tercero;
                tercero = segundo;
                segundo = primero;
                primero = producto;
                continue;
            }
            if(producto.getPrecio().compareTo(segundo.getPrecio()) >= 0 ){
                cuarto = tercero;
                tercero = segundo;
                segundo = producto;
                continue;
            }
            if(producto.getPrecio().compareTo(tercero.getPrecio()) >= 0 ){
                cuarto = tercero;
                tercero = producto;
                continue;
            }

            if(producto.getPrecio().compareTo(cuarto.getPrecio()) >= 0 ){
                cuarto = producto;
                continue;
            }

            List<Producto> los4MasCaros = new LinkedList<Producto>();

            los4MasCaros.add(primero);
            los4MasCaros.add(segundo);
            los4MasCaros.add(tercero);
            los4MasCaros.add(cuarto);
      
            return los4MasCaros;
        }
        
        return null;
    }
    
}
