package com.tavant.springboot.dao;

import java.util.List;
import java.util.Optional;

import com.tavant.springboot.model.Products;

public interface ProductsDAO {

	public String addProducts(Products products);
	public Optional<Products> updateProduct(String prodCode, Products products);
	public String deleteProduct(String prodCode);
	public Optional<Products> getProductById(String prodCode);
	public Optional<List<Products>> getProducts();
	public boolean ProductExistById(String prodCode);
}
