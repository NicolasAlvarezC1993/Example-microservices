package com.formacionbdi.springboot.app.items.controller;

import com.formacionbdi.springboot.app.items.decoder.ServiceException;
import com.formacionbdi.springboot.app.items.models.Item;
import com.formacionbdi.springboot.app.items.models.Producto;
import com.formacionbdi.springboot.app.items.service.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
public class ItemController {

    private static final Log LOGGER = LogFactory.getLog(ItemController.class.getName());

    @Autowired
    @Qualifier("serviceFeign")
    private ItemService itemService;

    @Autowired
    private Environment env;

    @Value("${configuracion.texto}")
    private String texto;

    @GetMapping("/listar")
    public List<Item> listar () {
        return itemService.findAll();
    }

    @HystrixCommand (fallbackMethod = "metodoAlternativoFindById")
    @GetMapping("/ver/{id}/cantidad/{cantidad}")
    public Item detalle (@PathVariable Long id, @PathVariable Integer cantidad) throws Exception {
        LOGGER.info ("Se inicia metodo detalle");
        return itemService.finById(id, cantidad);
    }

    public Item metodoAlternativoFindById(Long id, Integer cantidad) {
        Item item = new Item();
        Producto producto = new Producto();
        item.setCantidad(cantidad);
        producto.setId(id);
        producto.setPrecio(100.0);
        producto.setNombre("Monitor Gamer");
        item.setProducto(producto);
        return item;
    }



    @HystrixCommand (fallbackMethod = "metodoAlternativo")
    @PostMapping ("/create")
    public Producto create (@RequestBody Producto producto) {
        try {
            return itemService.create(producto);
        } catch (Exception e) {
           throw new ServiceException("Error al consumir microservicio /producto | DETALLE: "+e.getMessage());
        }
    }



    public Producto metodoAlternativo(Producto producto) {
        producto.setId(producto.getId());
        producto.setPrecio(1000.0);
        producto.setNombre("Mouse");
        return producto;
    }

    @GetMapping("/obtener-config")
    public ResponseEntity<?> obtenerConfig(@Value("${server.port}") String puerto){

        Producto producto = new Producto();
        Map<String,String> json = new HashMap<>();
        json.put("texto", texto);
        json.put("puerto",puerto);

        if (env.getActiveProfiles().length>0
                && env.getActiveProfiles()[0].equals("desarrollo") || env.getActiveProfiles()[0].equals("ribbon-desarrollo")){
            json.put("author.nombre", env.getProperty("configuracion.autor.nombre"));
            json.put("author.correo", env.getProperty("configuracion.autor.correo"));
            json.put("url.microservicio", env.getProperty("configuracion.url.microservicio"));
        }

        if (env.getActiveProfiles().length>0
                && env.getActiveProfiles()[0].equals("test") || env.getActiveProfiles()[0].equals("ribbon-test")){
            json.put("author.nombre", env.getProperty("configuracion.autor.nombre"));
            json.put("author.correo", env.getProperty("configuracion.autor.correo"));
            json.put("url.microservicio", env.getProperty("configuracion.url.microservicio"));

        }

        return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
    }

}
