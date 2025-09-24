package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Emp;

@Repository
public interface Emprepository extends JpaRepository<Emp,Long> {

	//public abstract List<Emp> findByEmpemailidAndEmppassword(String email, String password);

	//public abstract Emp findByEmailAndPassword(String email, String password);

	Emp findByEmpemailidAndEmppassword(String empemailid, String emppassword);
	
}