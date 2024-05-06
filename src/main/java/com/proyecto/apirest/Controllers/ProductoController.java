package com.proyecto.apirest.Controllers;

import com.proyecto.apirest.Entities.Producto;
import com.proyecto.apirest.Repository.ProductoRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> getAllProducts(){
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto getIdProducto(@PathVariable Integer id){
        return productoRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontro el producto con el id " + id));
    }

    @PostMapping
    public Producto createProduct(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }

    @PutMapping("/{id}")
    public Producto updateProduct(@PathVariable Integer id , @RequestBody Producto productoDetail){
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontro el producto con el id " + id));

        producto.setNombre(productoDetail.getNombre());
        producto.setMarca(productoDetail.getMarca());

        return productoRepository.save(producto);
    }

    @DeleteMapping("/{id}")
    public Producto deleteProduct(@PathVariable Integer id){
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontro el producto con el id " + id));
         productoRepository.deleteById(id);
         return producto;

    }



}
