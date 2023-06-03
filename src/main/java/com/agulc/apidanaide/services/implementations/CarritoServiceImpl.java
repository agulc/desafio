package com.agulc.apidanaide.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.agulc.apidanaide.entities.Carrito;
import com.agulc.apidanaide.entities.Compra;
import com.agulc.apidanaide.entities.Producto;
import com.agulc.apidanaide.entities.Usuario;
import com.agulc.apidanaide.entities.types.TipoCarrito;
import com.agulc.apidanaide.repositories.CarritoRepository;
import com.agulc.apidanaide.repositories.CompraRepository;
import com.agulc.apidanaide.repositories.ProductoRepository;
import com.agulc.apidanaide.repositories.UsuarioRepository;
import com.agulc.apidanaide.services.CarritoService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarritoServiceImpl implements CarritoService {

    private CarritoRepository carritoRepository;
    private UsuarioRepository usuarioRepository;
    private ProductoRepository productoRepository;
    private CompraRepository compraRepository;

    @Override
    public Long createCarrito(Long dniUsuario) {
        // TODO Auto-generated method stub
        Optional<Usuario> optUsuario = usuarioRepository.findFirstByDni(dniUsuario);
        Carrito carrito;
        if (optUsuario.isPresent()){
            boolean fechaEspecial = false;
            Usuario usuario = optUsuario.get();
            if (usuario.getEsVip()){
                carrito = new Carrito(null, TipoCarrito.VIP, usuario, null);
            }
            else if (fechaEspecial){
                carrito = new Carrito(null,TipoCarrito.FECHAESPECIAL, usuario, null);
            }
            else {
                carrito = new Carrito(null,TipoCarrito.COMUN, usuario, null);

            }
            return carritoRepository.save(carrito).getId();

        }

        Long invalid = -1L;
        return invalid;
    }

    @Override
    public Carrito getCarritoById(Long idCarrito) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCarritoById'");
    }

    @Override
    public Carrito addProductoToCarrito(Long idCarrito, Long idProducto) {
        // TODO Auto-generated method stub
        Optional<Carrito> existingCarrito = carritoRepository.findById(idCarrito);
        Optional<Producto> existingProducto = productoRepository.findById(idProducto);
        Optional<Compra> existingCompra = compraRepository.findByCarritoAndProducto(existingCarrito.get(), existingProducto.get());
        
        if (existingCompra.isPresent() && existingCarrito.isPresent()){
            Compra compra = existingCompra.get();
            compra.setCantidad(compra.getCantidad() + 1);
            compraRepository.save(compra);
            //Carrito carrito = existingCarrito.get();
            return null;
        }

        if (existingCarrito.isPresent() && existingProducto.isPresent()){
            Compra compra = new Compra(null, 1L, existingCarrito.get(), existingProducto.get());
            compraRepository.save(compra);
            return null;
        }

        return null;
    }

    @Override
    public Carrito removeProductoFromCarrito(Long idCarrito, Long idProducto) {
        // TODO Auto-generated method stub
        Optional<Carrito> existingCarrito = carritoRepository.findById(idCarrito);
        Optional<Producto> existingProducto = productoRepository.findById(idProducto);
        Optional<Compra> existingCompra = compraRepository.findByCarritoAndProducto(existingCarrito.get(), existingProducto.get());
        
        if (existingCompra.isPresent() && existingCarrito.isPresent()){
            Compra compra = existingCompra.get();
            compra.setCantidad(compra.getCantidad() - 1);
            if (compra.getCantidad() <= 0){
                compraRepository.delete(compra);
            }
            else{
                compraRepository.save(compra);
            }
            //Carrito carrito = existingCarrito.get();
            return null;
        }

        return null;
    }

    @Override
    public List<Carrito> getAllCarritos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllCarritos'");
    }

    @Override
    public Carrito updateCarrito(Carrito carrito) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCarrito'");
    }

    @Override
    public void deleteCarrito(Long idCarrito) {
        // TODO Auto-generated method stub
        carritoRepository.deleteById(idCarrito);
    }
    
}
