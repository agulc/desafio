package com.agulc.apidanaide.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.ArrayList;

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
    public ArrayList<Producto> getFourMostExpensiveProductos(Long dniUsuario) {
        // Este metodo es MUY ineficiente... pero está implementado.

        Optional<Usuario> existingUsuario = usuarioRepository.findFirstByDni(dniUsuario);
        if (!existingUsuario.isPresent()) return null;

        Usuario usuario = existingUsuario.get();
        Set<Carrito> carritos = usuario.getCarritos();
        Set<Compra> compras;
        ArrayList<Producto> productos = new ArrayList<Producto>();
        Producto masBarato;

        for (Carrito carrito : carritos){
           compras = carrito.getCompras();
           for (Compra compra : compras){
                if (productos.contains(compra.getProducto())) continue;
                if (productos.size() < 4){
                    productos.add(compra.getProducto());
                }
                else{
                    masBarato = this.getMasBarato(productos);
                    if (masBarato.getPrecio().compareTo(compra.getProducto().getPrecio()) < 0){
                        productos.remove(masBarato);
                        productos.add(compra.getProducto());
                    }
                }
           }
        }

        return productos;
    }

    private Producto getMasBarato(ArrayList<Producto> productos){
        Producto masBarato = productos.get(0);

        for (int i = 1; i < 4; i++){
            if (masBarato.getPrecio().compareTo(productos.get(i).getPrecio()) > 0){
                masBarato = productos.get(i);
            }
        }

        return masBarato;
    }
    
}
