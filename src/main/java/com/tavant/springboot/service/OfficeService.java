package com.tavant.springboot.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.tavant.springboot.model.Offices;

public interface OfficeService {

	public String addOffice(Offices office);
	public Optional<Offices> updateOffice(String offID, Offices office);
	public String deleteOffice(String offID);
	public Optional<Offices> getOfficeById(String offID);
	public Optional<List<Offices>> getOffices();
	public boolean officeExistById(String offID);
	public Optional<Set<String>> checkOfficeCode();
}
