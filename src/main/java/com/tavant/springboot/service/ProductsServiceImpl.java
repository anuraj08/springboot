package com.tavant.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tavant.springboot.dao.ProductsDAO;
import com.tavant.springboot.model.Products;
@Service

public class ProductsServiceImpl implements ProductsService {

	@Autowired
	ProductsDAO productsDAO;
	
	@Override
	public String addProducts(Products products) {
		return productsDAO.addProducts(products);
	}

	@Override
	public Optional<Products> updateProduct(String prodCode, Products products) {
		return productsDAO.updateProduct(prodCode, products);
	}

	@Override
	public String deleteProduct(String prodCode) {
		return productsDAO.deleteProduct(prodCode);
	}

	@Override
	public Optional<Products> getProductById(String prodCode) {
		return productsDAO.getProductById(prodCode);
	}

	@Override
	public Optional<List<Products>> getProducts() {
		return productsDAO.getProducts();
	}

	@Override
	public boolean ProductExistById(String prodCode) {
		return productsDAO.ProductExistById(prodCode);
	}

}
