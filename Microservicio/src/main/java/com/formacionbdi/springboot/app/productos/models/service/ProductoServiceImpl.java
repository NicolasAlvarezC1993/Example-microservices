package com.formacionbdi.springboot.app.productos.models.service;

import com.formacionbdi.springboot.app.productos.controllers.ProductoController;
import com.formacionbdi.springboot.app.productos.exception.BusinessException;
import com.formacionbdi.springboot.app.productos.models.dao.ProductoDao;
import com.formacionbdi.springboot.app.productos.models.entity.Producto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoServiceImpl implements IProductoService {

	private static final Log LOGGER = LogFactory.getLog(ProductoServiceImpl.class.getName());

	@Autowired
	private ProductoDao productoDao;
		
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {

		LOGGER.info ("Se buscan todos los elementos");
		return (List<Producto>) productoDao.findAll();

	}

	@Override
	@Transactional(readOnly = true)
	public Producto findById(Long id) {

		LOGGER.info ("Se busca elemento por id => "+ id);
		return productoDao.findById(id).orElse(null);
		
	}

	@Override
	public Producto create(Producto producto) {
		LOGGER.info ("Se validan los campos enviados segun el negocio, producto :"+producto.toString());
		if (producto.getPrecio() < 1) {
			throw new BusinessException("El precio debe ser mayor a cero");
		}
		LOGGER.info ("La validación ha sido exitosa");
		return productoDao.save(producto);
	}

}
