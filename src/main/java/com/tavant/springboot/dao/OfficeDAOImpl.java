package com.tavant.springboot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tavant.springboot.model.Offices;
import com.tavant.springboot.utils.DBUtils;

@Repository
public class OfficeDAOImpl implements OfficeDAO {

	@Autowired
	DBUtils dbUtils;
	
	@Override
	public String addOffice(Offices office) {
		String query = "insert into offices (officeCode, city, phone, addressLine1, addressLine2, state, country, postalCode, territory) values (?,?,?,?,?,?,?,?,?)";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		connection = dbUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, office.getOfficeCode());
			preparedStatement.setString(2, office.getCity());
			preparedStatement.setString(3, office.getPhone());
			preparedStatement.setString(4, office.getAddressLine1());
			preparedStatement.setString(5, office.getAddressLine2());
			preparedStatement.setString(6, office.getState());
			preparedStatement.setString(7, office.getCountry());
			preparedStatement.setString(8, office.getPostalCode());
			preparedStatement.setString(9, office.getTerritory());
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
	public Optional<Offices> updateOffice(String offID, Offices office) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "update offices set officeCode=?, city=?,phone=?,addressLine1=?,addressLine2=?,state=?,country=?,postalCode=?,territory=? where officeCode=?";
		String query1 = "select * from offices where officeCode = ?";
		ResultSet resultSet = null;
		connection = dbUtils.getConnection();
		try {
			System.out.println("Hi");
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, office.getOfficeCode());
			preparedStatement.setString(2, office.getCity());
			preparedStatement.setString(3, office.getPhone());
			preparedStatement.setString(4, office.getAddressLine1());
			preparedStatement.setString(5, office.getAddressLine2());
			preparedStatement.setString(6, office.getState());
			preparedStatement.setString(7, office.getCountry());
			preparedStatement.setString(8, office.getPostalCode());
			preparedStatement.setString(9, office.getTerritory());
			preparedStatement.setString(10, offID);
			int res = preparedStatement.executeUpdate();
			System.out.println(res);
			if(res>0) {
				preparedStatement = connection.prepareStatement(query1);
				preparedStatement.setString(1, office.getOfficeCode());
				resultSet = preparedStatement.executeQuery(); 
				if(resultSet.next()) {
					Offices off = new Offices();
					off.setOfficeCode(resultSet.getString("officeCode"));
					off.setCity(resultSet.getString("city"));
					off.setPhone(resultSet.getString("phone"));
					off.setAddressLine1(resultSet.getString("addressLine1"));
					off.setAddressLine2(resultSet.getString("addressLine2"));
					off.setState(resultSet.getString("state"));
					off.setCountry(resultSet.getString("country"));
					off.setPostalCode(resultSet.getString("postalCode"));
					off.setTerritory(resultSet.getString("territory"));
					return Optional.of(off);
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
	public String deleteOffice(String offID) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		connection = dbUtils.getConnection();
		String query = "delete from offices where officeCode = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, offID);
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
	public Optional<Offices> getOfficeById(String offID) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		connection = dbUtils.getConnection();
		String query = "select * from offices where officeCode = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, offID);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				Offices office = new Offices();
				office.setOfficeCode(resultSet.getString("officeCode"));
				office.setCity(resultSet.getString("city"));
				office.setPhone(resultSet.getString("phone"));
				office.setAddressLine1(resultSet.getString("addressLine1"));
				office.setAddressLine2(resultSet.getString("addressLine2"));
				office.setState(resultSet.getString("state"));
				office.setCountry(resultSet.getString("country"));
				office.setPostalCode(resultSet.getString("postalCode"));
				office.setTerritory(resultSet.getString("territory"));
				return Optional.of(office);
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
	public Optional<List<Offices>> getOffices() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Offices> offices = new ArrayList<Offices>();
		
		connection = dbUtils.getConnection();
		String query = "select * from offices";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Offices office = new Offices();
				office.setOfficeCode(resultSet.getString("officeCode"));
				office.setCity(resultSet.getString("city"));
				office.setPhone(resultSet.getString("phone"));
				office.setAddressLine1(resultSet.getString("addressLine1"));
				office.setAddressLine2(resultSet.getString("addressLine2"));
				office.setState(resultSet.getString("state"));
				office.setCountry(resultSet.getString("country"));
				office.setPostalCode(resultSet.getString("postalCode"));
				office.setTerritory(resultSet.getString("territory"));
				offices.add(office);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		if(offices.isEmpty()) {
			return Optional.empty();
		}
		else {
			return Optional.of(offices);
		}
	}

	@Override
	public boolean officeExistById(String offID) {
		Optional<Offices> opt = getOfficeById(offID);
		if(opt.isEmpty()) {
			return false;
		}
		else
			return true;
	}
	
	@Override
	public Optional<Set<String>> checkOfficeCode() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Set<String> oCode = new HashSet<String>();
		String query = "select officeCode from offices";
		connection = dbUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				oCode.add(resultSet.getString("officeCode"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		if(oCode.isEmpty()) {
			return Optional.empty();
		}
		else
			return Optional.of(oCode);
	}

}
