package com.tavant.springboot.dao;

import java.util.List;
import java.util.Optional;

import com.tavant.springboot.model.ProductLines;

public interface ProductLinesDAO {

	public String addProductLines(ProductLines productLines);
	public String deleteProductLine(String productLine);
	public Optional<ProductLines> updateProductLine(String productLine, ProductLines productLines);
	public Optional<ProductLines> getProductLineById(String productLine);
	public Optional<List<ProductLines>> getProductLine();
	public boolean ProductLineExistById(String productLine);
	
}
