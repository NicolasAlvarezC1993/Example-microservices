package com.formacionbdi.springboot.app.items.service;

import com.formacionbdi.springboot.app.items.clientes.ProductoClienteRest;
import com.formacionbdi.springboot.app.items.controller.ItemController;
import com.formacionbdi.springboot.app.items.models.Item;
import com.formacionbdi.springboot.app.items.models.Producto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service ("serviceFeign")
public class ItemServiceFeign implements ItemService {

    private static final Log LOGGER = LogFactory.getLog(ItemServiceFeign.class.getName());

    private final ProductoClienteRest clienteFeign;

    public ItemServiceFeign(ProductoClienteRest clienteFeign) {
        this.clienteFeign = clienteFeign;
    }

    @Override
    public List<Item> findAll() {

        return clienteFeign.listar().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());

    }

    @Override
    public Item finById(Long id, Integer cantidad) throws Exception {
        LOGGER.info ("Se inicia llamada a microservicio producto");
        Item item = new Item(clienteFeign.detalle(id), cantidad);
        return item;

    }

    @Override
    public Producto create(Producto producto)  {

        return clienteFeign.create(producto);

    }

}
