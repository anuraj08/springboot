package com.tavant.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tavant.springboot.dao.ProductLinesDAO;
import com.tavant.springboot.model.ProductLines;
@Service

public class ProductLinesServiceImpl implements ProductLinesService {

	@Autowired
	ProductLinesDAO productLinesDAO;
	
	@Override
	public String addProductLines(ProductLines productLines) {
		return productLinesDAO.addProductLines(productLines);
	}

	@Override
	public String deleteProductLine(String productLine) {
		return productLinesDAO.deleteProductLine(productLine);
	}

	@Override
	public Optional<ProductLines> updateProductLine(String productLine, ProductLines productLines) {
		return productLinesDAO.updateProductLine(productLine, productLines);
	}

	@Override
	public Optional<ProductLines> getProductLineById(String productLine) {
		return productLinesDAO.getProductLineById(productLine);
	}

	@Override
	public Optional<List<ProductLines>> getProductLine() {
		return productLinesDAO.getProductLine();
	}

	@Override
	public boolean ProductLineExistById(String productLine) {
		return productLinesDAO.ProductLineExistById(productLine);
	}

}
