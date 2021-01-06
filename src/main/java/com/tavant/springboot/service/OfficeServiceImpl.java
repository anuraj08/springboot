package com.tavant.springboot.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tavant.springboot.dao.OfficeDAO;
import com.tavant.springboot.model.Offices;

@Service
public class OfficeServiceImpl implements OfficeService {

	@Autowired
	OfficeDAO officeDAO;
	
	@Override
	public String addOffice(Offices office) {
		return officeDAO.addOffice(office);
	}

	@Override
	public Optional<Offices> updateOffice(String offID, Offices office) {
		return officeDAO.updateOffice(offID, office);
	}

	@Override
	public String deleteOffice(String offID) {
		return officeDAO.deleteOffice(offID);
	}

	@Override
	public Optional<Offices> getOfficeById(String offID) {
		return officeDAO.getOfficeById(offID);
	}

	@Override
	public Optional<List<Offices>> getOffices() {
		return officeDAO.getOffices();
	}

	@Override
	public boolean officeExistById(String offID) {
		return officeDAO.officeExistById(offID);
	}
	
	@Override
	public Optional<Set<String>> checkOfficeCode() {
		return officeDAO.checkOfficeCode();
	}

}
