package com.formacionbdi.springboot.app.items.service;

import com.formacionbdi.springboot.app.items.models.Item;
import com.formacionbdi.springboot.app.items.models.Producto;

import java.util.List;

public interface ItemService {

    public List<Item> findAll();
    public Item finById(Long id, Integer cantidad) throws Exception;
    public Producto create(Producto producto) ;
}
