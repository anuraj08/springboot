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

import com.tavant.springboot.model.ProductLines;
import com.tavant.springboot.utils.DBUtils;
@Repository

public class ProductLinesDAOImpl implements ProductLinesDAO {

	@Autowired
	DBUtils dbUtils;
	
	@Override
	public String addProductLines(ProductLines productLines) {
		String query = "insert into productlines (productLine, textDescription, htmlDescription) values (?,?,?)";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		connection = dbUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, productLines.getProductLine());
			preparedStatement.setString(2, productLines.getTextDescription());
			preparedStatement.setString(3, productLines.getHtmlDescription());
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
	public String deleteProductLine(String productLine) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		connection = dbUtils.getConnection();
		String query = "delete from productlines where productLine = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, productLine);
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
	public Optional<ProductLines> updateProductLine(String productLine, ProductLines productLines) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "update productlines set productLine=?, textDescription=?, htmlDescription=? where productLine=?";
		String query1 = "select * from productlines where productLine = ?";
		ResultSet resultSet = null;
		connection = dbUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, productLines.getProductLine());
			preparedStatement.setString(2, productLines.getTextDescription());
			preparedStatement.setString(3, productLines.getHtmlDescription());
			preparedStatement.setString(4, productLine);
			int res = preparedStatement.executeUpdate();
			System.out.println(res);
			if(res>0) {
				preparedStatement = connection.prepareStatement(query1);
				preparedStatement.setString(1, productLines.getProductLine());
				resultSet = preparedStatement.executeQuery(); 
				if(resultSet.next()) {
					ProductLines pl = new ProductLines();
					pl.setProductLine(resultSet.getString("productLine"));
					pl.setTextDescription(resultSet.getString("textDescription"));
					pl.setHtmlDescription(resultSet.getString("htmlDescription"));
					return Optional.of(pl);
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
	public Optional<ProductLines> getProductLineById(String productLine) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		connection = dbUtils.getConnection();
		String query = "select * from productlines where productLine = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, productLine);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				ProductLines pl = new ProductLines();
				pl.setProductLine(resultSet.getString("productLine"));
				pl.setTextDescription(resultSet.getString("textDescription"));
				pl.setHtmlDescription(resultSet.getString("htmlDescription"));
				return Optional.of(pl);
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
	public Optional<List<ProductLines>> getProductLine() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<ProductLines> productlines = new ArrayList<ProductLines>();
		
		connection = dbUtils.getConnection();
		String query = "select * from productlines";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				ProductLines pl = new ProductLines();
				pl.setProductLine(resultSet.getString("productLine"));
				pl.setTextDescription(resultSet.getString("textDescription"));
				pl.setHtmlDescription(resultSet.getString("htmlDescription"));
				productlines.add(pl);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		if(productlines.isEmpty()) {
			return Optional.empty();
		}
		else {
			return Optional.of(productlines);
		}
	}

	@Override
	public boolean ProductLineExistById(String productLine) {
		Optional<ProductLines> opt = getProductLineById(productLine);
		if(opt.isEmpty()) {
			return false;
		}
		else
			return true;
	}

}
