package com.agulc.apidanaide.services.implementations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
            boolean fechaEspecial = true;
            Usuario usuario = optUsuario.get();
            if (usuario.getEsVip()){
                carrito = new Carrito(null, TipoCarrito.VIP, BigDecimal.valueOf(0.0f), BigDecimal.valueOf(0.0f), usuario, null);
            }
            else if (fechaEspecial){
                carrito = new Carrito(null,TipoCarrito.FECHAESPECIAL, BigDecimal.valueOf(0.0f), BigDecimal.valueOf(0.0f), usuario, null);
            }
            else {
                carrito = new Carrito(null,TipoCarrito.COMUN, BigDecimal.valueOf(0.0f), BigDecimal.valueOf(0.0f), usuario, null);

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
            Carrito carrito = existingCarrito.get();
            carrito.agregarPrecio(productoRepository.findById(idProducto).get().getPrecio());
            carrito = this.calcularDescuento(carrito);
            carritoRepository.save(carrito);
            return carrito;
        }

        if (existingCarrito.isPresent() && existingProducto.isPresent()){
            Compra compra = new Compra(null, 1L, existingCarrito.get(), existingProducto.get());
            compraRepository.save(compra);
            Carrito carrito = existingCarrito.get();
            carrito.agregarPrecio(existingProducto.get().getPrecio());
            carrito = this.calcularDescuento(carrito);
            carritoRepository.save(carrito);
            return carrito;
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
            Carrito carrito = existingCarrito.get();
            carrito.restarPrecio(existingProducto.get().getPrecio());
            carrito = this.calcularDescuento(carrito);
            carritoRepository.save(carrito);
            return carrito;
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

    private Carrito calcularDescuento(Carrito carrito){
        Set<Compra> compras = carrito.getCompras();
        if (compras.isEmpty()) return carrito;

        Producto productoMasBarato = compras.iterator().next().getProducto();
        Long items = 0l;
        Float total = carrito.getTotal().floatValue();

        for(Compra compra: compras){
            items = items + compra.getCantidad();
            if (productoMasBarato.getPrecio().compareTo(compra.getProducto().getPrecio()) >= 0){
                productoMasBarato = compra.getProducto();
            }
        }

        if (items == 5){
            carrito.guardarDescuento(BigDecimal.valueOf(total - total * 0.2f));
        }
        else if (items > 10){
            switch (carrito.getTipo()) {
                case COMUN:
                    if (total > 200.0f){
                        carrito.guardarDescuento(BigDecimal.valueOf(total - 200.0f));
                    }
                    break;
                
                case FECHAESPECIAL:
                    if (total > 500.0f){
                        carrito.guardarDescuento(BigDecimal.valueOf(total - 500.0f));
                    }
                    break;

                case VIP:
                    float precioBonificado = total - productoMasBarato.getPrecio().floatValue();
                    if (total > 700.0f){
                        carrito.guardarDescuento(BigDecimal.valueOf(precioBonificado - 700.0f));
                    }
                    else{
                        carrito.guardarDescuento(BigDecimal.valueOf(precioBonificado));
                    }
                    break;
                default:
                    break;
            }
        }
        else{
            carrito.guardarDescuento(BigDecimal.valueOf(total));
        }

        return carrito;

    }
    
}
