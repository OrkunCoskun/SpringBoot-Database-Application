package springbootwebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import springbootwebapp.dao.EmployeeRepository;
import springbootwebapp.model.Employee;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeRepository eRepo;

	@GetMapping({"/showEmployees", "/", "/list"})
	public ModelAndView showEmployees() {
		ModelAndView mv = new ModelAndView("list-employees");
		List<Employee> list = eRepo.findAll();
		mv.addObject("employees", list);
		return mv;
	}
	
	@GetMapping("/addEmployeeForm")
	public ModelAndView addEmployees() {
		ModelAndView mv = new ModelAndView("add-employees");
		Employee newEmployee = new Employee();
		mv.addObject("employee", newEmployee);
		return mv;
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute Employee employee) {
		eRepo.save(employee);
		return "redirect:/list";
	}
	
	@GetMapping("/showUpdateForm")
	public ModelAndView updateEmployees(@RequestParam Long employeeId) {
		ModelAndView mv = new ModelAndView("add-employees");
		Employee employee =  eRepo.findById(employeeId).get();
		mv.addObject("employee", employee);
		return mv;
	}
	
	@GetMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam Long employeeId) {
		eRepo.deleteById(employeeId);
		return "redirect:/list";
	}
}
