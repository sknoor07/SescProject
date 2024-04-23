package Student_Portal.com.project.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import Student_Portal.com.project.classes.Invoice;
import Student_Portal.com.project.classes.Status;
import Student_Portal.com.project.classes.Student;
import Student_Portal.com.project.repository.InvoiceRepository;
import Student_Portal.com.project.service.InvoiceService;
import Student_Portal.com.project.service.Student_Service;

@Configuration
class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

@RestController
public class Student_Controller {
	private Student_Service student_Service;
	@Autowired
	public RestTemplate restTemplate;
	
	private InvoiceService invoiceService;
	
	public Student_Controller(Student_Service student_Service, InvoiceService invoiceService) {
		super();
		this.student_Service = student_Service;
		this.invoiceService=invoiceService;
	}
	
	


	public Student_Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/student")
	public List<Student>getAllStudent() {
		List<Student> students=student_Service.findAllStudent();
		return students;
	}
	
	@GetMapping("/student/{studentId}")
	public Student getStudentById(@PathVariable String studentId) {
		Student student=student_Service.findStudentById(studentId);
		return student;
	}  
	
	@PostMapping("/student")
	public ResponseEntity<Object> createStudent(@RequestBody Student student) {
		Student savedstudent=student_Service.addStudent(student);
		ResponseEntity<String> response=null;
		
		if(savedstudent!=null) {
			String url = "http://localhost:8081/accounts/";
			String url_books="http://localhost:80/api/register";
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Content-Type", "application/json");
	        String requestBody = "{\"studentId\": \"" + savedstudent.getStudentId() + "\"}";
	        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
	        
	        response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class); 
	        ResponseEntity<String> response_books = restTemplate.exchange(url_books, HttpMethod.POST, requestEntity, String.class); 
		}	
		return ResponseEntity.created(response.getHeaders().getLocation()).build();
	}
	
	
	@GetMapping("/student/invoices/{referenceid}")
	public String getInvoiceStatus(@PathVariable String referenceid) {
		Invoice invoice=invoiceService.getInvoiceByReferenceId(referenceid);
		String balance = null;
		if(invoice.getStatus()==Status.OUTSTANDING) {
			balance="invoice Outstanding";
		}else if (invoice.getStatus()==Status.PAID) {
			balance="No Invoice pending";
		}
		return balance;
	}
	
	
	
}
