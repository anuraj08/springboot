package com.tavant.springboot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tavant.springboot.model.Products;
import com.tavant.springboot.utils.DBUtils;
@Repository

public class ProductsDAOImpl implements ProductsDAO {

	@Autowired
	DBUtils dbUtils;
	
	@Override
	public String addProducts(Products products) {
		String query = "insert into products (productCode, productName, productLine, productScale, productVendor, productDescription, quantityInStocks, buyPrice, MSRP) values (?,?,?,?,?,?,?,?,?)";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		connection = dbUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, products.getProductCode());
			preparedStatement.setString(2, products.getProductName());
			preparedStatement.setString(3, products.getProductLine());
			preparedStatement.setString(4, products.getProductScale());
			preparedStatement.setString(5, products.getProductVendor());
			preparedStatement.setString(6, products.getProductDescription());
			preparedStatement.setInt(7, products.getQualityInStock());
			preparedStatement.setDouble(8, products.getBuyPrice());
			preparedStatement.setDouble(9, products.getMSRP());
			int result= preparedStatement.executeUpdate();
			if(result>0) {
				return "success";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		return "fail";
	}

	@Override
	public Optional<Products> updateProduct(String prodCode, Products products) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "update products set productCode=?, productName=?, productLine=?, productScale=?, productVendor=?, productDescription=?, quantityInStocks=?, buyPrice=?, MSRP=? where productCode=?";
		String query1 = "select * from products where productCode = ?";
		ResultSet resultSet = null;
		connection = dbUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, products.getProductCode());
			preparedStatement.setString(2, products.getProductName());
			preparedStatement.setString(3, products.getProductLine());
			preparedStatement.setString(4, products.getProductScale());
			preparedStatement.setString(5, products.getProductVendor());
			preparedStatement.setString(6, products.getProductDescription());
			preparedStatement.setInt(7, products.getQualityInStock());
			preparedStatement.setDouble(8, products.getBuyPrice());
			preparedStatement.setDouble(9, products.getMSRP());
			preparedStatement.setString(10, prodCode);
			int res = preparedStatement.executeUpdate();
			System.out.println(res);
			if(res>0) {
				preparedStatement = connection.prepareStatement(query1);
				preparedStatement.setString(1, products.getProductCode());
				resultSet = preparedStatement.executeQuery(); 
				if(resultSet.next()) {
					Products p = new Products();
					p.setProductCode(resultSet.getString("productCode"));
					p.setProductName(resultSet.getString("productName"));
					p.setProductLine(resultSet.getString("productLine"));
					p.setProductScale(resultSet.getString("productScale"));
					p.setProductVendor(resultSet.getString("productVendor"));
					p.setProductDescription(resultSet.getString("productDescription"));
					p.setQualityInStock(resultSet.getInt("qualityInStock"));
					p.setBuyPrice(resultSet.getDouble("buyPrice"));
					p.setMSRP(resultSet.getDouble("MSRP"));
					return Optional.of(p);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		return Optional.empty();
	}

	@Override
	public String deleteProduct(String prodCode) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		connection = dbUtils.getConnection();
		String query = "delete from products where productCode = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, prodCode);
			int res = preparedStatement.executeUpdate();
			if(res>0) {
				return "Success";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		return "fail";
	}

	@Override
	public Optional<Products> getProductById(String prodCode) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		connection = dbUtils.getConnection();
		String query = "select * from products where productCode = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, prodCode);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				Products p = new Products();
				p.setProductCode(resultSet.getString("productCode"));
				p.setProductName(resultSet.getString("productName"));
				p.setProductLine(resultSet.getString("productLine"));
				p.setProductScale(resultSet.getString("productScale"));
				p.setProductVendor(resultSet.getString("productVendor"));
				p.setProductDescription(resultSet.getString("productDescription"));
				p.setQualityInStock(resultSet.getInt("qualityInStock"));
				p.setBuyPrice(resultSet.getDouble("buyPrice"));
				p.setMSRP(resultSet.getDouble("MSRP"));
				return Optional.of(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		return Optional.empty();
	}

	@Override
	public Optional<List<Products>> getProducts() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Products> products = new ArrayList<Products>();
		
		connection = dbUtils.getConnection();
		String query = "select * from products";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Products p = new Products();
				p.setProductCode(resultSet.getString("productCode"));
				p.setProductName(resultSet.getString("productName"));
				p.setProductLine(resultSet.getString("productLine"));
				p.setProductScale(resultSet.getString("productScale"));
				p.setProductVendor(resultSet.getString("productVendor"));
				p.setProductDescription(resultSet.getString("productDescription"));
				p.setQualityInStock(resultSet.getInt("qualityInStock"));
				p.setBuyPrice(resultSet.getDouble("buyPrice"));
				p.setMSRP(resultSet.getDouble("MSRP"));
				products.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		if(products.isEmpty()) {
			return Optional.empty();
		}
		else {
			return Optional.of(products);
		}
	}

	@Override
	public boolean ProductExistById(String prodCode) {
		Optional<Products> opt = getProductById(prodCode);
		if(opt.isEmpty()) {
			return false;
		}
		else
			return true;
	}

}
