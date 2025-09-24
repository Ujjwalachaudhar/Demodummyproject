package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Emp;
import com.example.demo.service.Empservice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
@Controller
@RequestMapping("/api")
public class EmpController {
	 @Autowired
	    private Empservice empservice;

	    @GetMapping("/employee-form")
	    public String showEmployeeForm(Model model) {
	    	model.addAttribute("emp", new Emp());
	        return "employee-form"; // Matches index.html
	    }

	    @GetMapping("/login")
	    public String loginData() {
	        return "index2"; // Matches index2.html
	    }
 
	    @GetMapping("/tasks")
	    public String tasks() {
	        return "tasks"; // Matches tasks.html
	    }
	    
	    @GetMapping("/reports")
	    public String reports() {
	        return "reports"; // Matches reports.html
	    }
	    
	    @GetMapping("/register")
	    public String showRegisterPage(Model model) {
	        model.addAttribute("emp", new Emp());
	        return "employee-form"; // registration page
	    }
	    
	 //Post method register employee data
	    @PostMapping("/register")
	    public String registerEmployee(@ModelAttribute("emp") Emp lm) {
	        System.out.println("Received: " + lm);
	        empservice.saveEmployee(lm);
	        return "redirect:/api/welcome"; // Correct redirection to /api/welcome
	    }
	
	    //Main employee porter
	@GetMapping("/welcome")
    public String welcome() {
        return "welcome"; 
    }

	@GetMapping("/employeewelcome")
    public String employeewelcome() {
        return "employeewelcome"; 
    }
	
	//login from email id and password
	@PostMapping("/login")
	public String login(@RequestParam("empemailid") String email,
	                    @RequestParam("emppassword") String password,
	                    Model model) {

	    Emp emp = empservice.validateLogin(email, password);

	    if (emp != null) {
	        model.addAttribute("emp", emp); // pass emp to view

	        // Check for admin email
	        if ("admin@gmail.com".equalsIgnoreCase(emp.getEmpemailid())) {
	            return "welcome";  // admin page
	        } else {
	            return "employeewelcome";    // normal employee page
	        }
	    } else {
	        model.addAttribute("error", "Invalid email or password.");
	        return "index2"; // login page
	    }
	}


	/*
	 * @PostMapping("/login") public String login(@RequestParam("empemailid") String
	 * email,
	 * 
	 * @RequestParam("emppassword") String password, Model model) {
	 * 
	 * Emp emp = empservice.validateLogin(email, password);
	 * 
	 * if (emp != null) { model.addAttribute("emp", emp); // emp added correctly
	 * return "employeewelcome"; // only show welcome if login success } else {
	 * model.addAttribute("error", "Invalid email or password."); return "index2";
	 * // login page } }
	 */


	@GetMapping("/employeedirectory")
	public String viewEmployeeDirectory(Model model) {
	    List<Emp> employees = empservice.getAllEmployee(); // Ensure this method is working
	    model.addAttribute("employees", employees);
	    return "employeedirectory"; // This should match the Thymeleaf template name
	}
 
	@GetMapping("/logout")
    public String logout(HttpServletRequest request) 
	{
		HttpSession s=request.getSession(false);
		if(s!=null)
		{
			s.invalidate();
		}
		
		return "redirect:/api/login";
	}

	// Show edit form
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable Long id, Model model) {
	    Emp emp = empservice.getEmployeeById(id);
	    model.addAttribute("emp", emp);
	    return "edit"; // a Thymeleaf page with an update form
	}

	// Handle delete
    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute("emp") Emp emp) {
        Emp existingEmp = empservice.getEmployeeById(id);
        existingEmp.setEmpname(emp.getEmpname());
        existingEmp.setEmpemailid(emp.getEmpemailid());
        existingEmp.setEmpsalary(emp.getEmpsalary());
        existingEmp.setEmpphoneno(emp.getEmpphoneno());
        existingEmp.setEmppassword(emp.getEmppassword());

        empservice.saveEmployee(existingEmp); // save changes to DB
        return "redirect:/api/employeedirectory"; // redirect to employee list
    }
    
	@PostMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		empservice.deleteEmployee(id);
	    return "redirect:/api/employeedirectory"; // reload list
	}
}
	

