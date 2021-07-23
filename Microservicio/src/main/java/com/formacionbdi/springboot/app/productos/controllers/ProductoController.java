package com.formacionbdi.springboot.app.productos.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javassist.tools.web.BadHttpRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.formacionbdi.springboot.app.productos.models.entity.Producto;
import com.formacionbdi.springboot.app.productos.models.service.IProductoService;

import javax.validation.Valid;

@RestController
@RefreshScope
@Api(value = "MICROSERVICIO DE INTEGRACION")
public class ProductoController {

	private static final Log LOGGER = LogFactory.getLog(ProductoController.class.getName());
	public IProductoService productoService;
	public ProductoController(IProductoService productoService) {
		this.productoService = productoService;
	}

	@Autowired
	private Environment env;

	@Value("${configuracion.texto}")
	private String texto;

	@GetMapping("/listar")
	@ApiOperation(value = "findAll", notes = "Obtiene todos los registros")
	public List<Producto> listar() {
		LOGGER.info ("Se inicia metodo para buscar todo");
		return productoService.findAll();
	}
	
	@GetMapping("/ver/{id}")
	@ApiOperation(value = "findById", notes = "Obtiene un registro por su id")
	public Producto detalle(@PathVariable Long id) {
		LOGGER.info ("Se inicia metodo buscar por un id");
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			LOGGER.error("Ha superado el tiempo de espera" + e.getMessage());
		}
		return productoService.findById(id);
	}

	@PostMapping("/create")
	@ApiOperation(value = "create", notes = "Crear un producto")
	public Producto create(@Valid @RequestBody Producto producto) {
		LOGGER.info ("Se inicia metodo para crear producto");
		return productoService.create(producto);
	}

	@GetMapping("/obtener-config")
	public ResponseEntity<?> obtenerConfig(@Value("${server.port}") String puerto){

		LOGGER.info ("Se inicia metodo para obtener configuracion");
		Map<String,String> json = new HashMap<>();
		json.put("texto", texto);
		json.put("puerto",puerto);

		if (env.getActiveProfiles().length>0
				&& env.getActiveProfiles()[0].equals("desarrollo") || env.getActiveProfiles()[0].equals("ribbon-desarrollo")){
			json.put("author.nombre", env.getProperty("configuracion.autor.nombre"));
			json.put("author.correo", env.getProperty("configuracion.autor.correo"));
			json.put("url.microservicio", env.getProperty("configuracion.url.microservicio"));
		}

		if (env.getActiveProfiles().length>0 && env.getActiveProfiles()[0].equals("test") || env.getActiveProfiles()[0].equals("ribbon-test")){
			json.put("author.nombre", env.getProperty("configuracion.autor.nombre"));
			json.put("author.correo", env.getProperty("configuracion.autor.correo"));
			json.put("url.microservicio", env.getProperty("configuracion.url.microservicio"));

		}

		return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
	}
}
