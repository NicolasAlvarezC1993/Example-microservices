package com.formacionbdi.springboot.app.items.clientes;

import com.formacionbdi.springboot.app.items.models.Producto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="servicio-productos")
public interface ProductoClienteRest {

    @GetMapping("/listar")
    List<Producto> listar ();

    @GetMapping("/ver/{id}")
    Producto detalle(@PathVariable Long id);

    @PostMapping("/create")
    Producto create (@RequestBody Producto producto);
}

