package com.example.demo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.Emprepository;
import com.example.demo.model.Emp;


@Service
public class Empservice {

	
	@Autowired
	private Emprepository sr;
	
	
	public Emp saveEmployee(Emp lm) {
		// TODO Auto-generated method stub
		return sr.save(lm);
	}
	
	/*
	 * public boolean validateLogin(String empemailid, String emppassword) { try {
	 * // Assuming sr is a repository with the findByEmpemailidAndEmppassword method
	 * List<Emp> employees = sr.findByEmpemailidAndEmppassword(empemailid,
	 * emppassword);
	 * 
	 * // If exactly one employee is found, login is successful if (employees.size()
	 * == 1) { return true; } return false; // Either no results or more than one
	 * result } catch (Exception e) { // Optionally log the exception for debugging
	 * System.err.println("Error during login validation: " + e.getMessage());
	 * return false; } }
	 */
	
	public List<Emp> getAllEmployee() {
		// TODO Auto-generated method stub
		 return sr.findAll(); 
	}

	public Emp getEmployeeById(Long id) {
	    // Fetch employee by ID or throw if not found
	    return sr.findById(id)
	        .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
	}

	public void deleteEmployee(Long id) {
	    // Delete employee if exists
	    if (!sr.existsById(id)) {
	        throw new RuntimeException("Employee not found with id: " + id);
	    }
	    sr.deleteById(id);
	}

	  public Emp validateLogin(String email, String password) {
	        return sr.findByEmpemailidAndEmppassword(email, password);
	    }

	    public Emp getEmployeeByEmailAndPassword(String email, String password) {
	        return sr.findByEmpemailidAndEmppassword(email, password);
	    }

	
	
}
