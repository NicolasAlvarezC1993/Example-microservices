package com.formacionbdi.springboot.app.productos;

import com.formacionbdi.springboot.app.productos.controllers.ProductoController;
import com.formacionbdi.springboot.app.productos.models.entity.Producto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringbootServicioProductosApplicationTests {

	@Autowired
	private ProductoController productoController;

	@Autowired
	SpringbootServicioProductosApplication clasePrincipal;

	@Test
	public void pruebaClasePrincipal () {
		String[] arrayString = {""};
		clasePrincipal.main(arrayString);
	}
	
//	@Test
//	void pruebaGetById() {
//		Producto productoEsperado = new Producto();
//		Producto productoRetornado;
//		productoEsperado.setId((long) 1);
//		productoEsperado.setNombre("Nissan");
//		productoEsperado.setPrecio((double) 800);
//		productoRetornado = productoController.detalle((long) 1);
//		productoEsperado.setCreateAt(productoRetornado.getCreateAt());
//		assertEquals(productoEsperado.getId(),productoRetornado.getId());
//		assertEquals(productoEsperado.getCreateAt(),productoRetornado.getCreateAt());
//		assertEquals(productoEsperado.getNombre(),productoRetornado.getNombre());
//		assertEquals(productoEsperado.getPrecio(), productoRetornado.getPrecio());
//
//	}

	@Test
	public void pruebaFindAll() {
		List<Producto> listaProductos;
		listaProductos = productoController.listar();
		assertEquals(7,listaProductos.size());
	}
}
