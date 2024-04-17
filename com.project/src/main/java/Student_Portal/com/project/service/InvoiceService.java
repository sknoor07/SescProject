package Student_Portal.com.project.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import Student_Portal.com.project.classes.Invoice;
import Student_Portal.com.project.repository.InvoiceRepository;
@Component
public class InvoiceService {
	private InvoiceRepository invoiceRepository;

	public InvoiceService(InvoiceRepository invoiceRepository) {
		super();
		this.invoiceRepository = invoiceRepository;
	}
	
	public void saveAllInvoice(ResponseEntity<List<Invoice>> response) {
		List<Invoice> invoices = response.getBody();
		for (Invoice invoice : invoices) {
			invoiceRepository.save(invoice);
		}
		
	}
	public Invoice getInvoiceByReferenceId(String referenceid) {
	    String apiUrl = "http://localhost:8081/invoices";
	    //RestTemplate restTemplate = new RestTemplate();
	    HashMap<String, String> uriVariables= new HashMap<String, String>();
	    uriVariables.put("reference",referenceid);
	    ResponseEntity<Invoice> responseEntity=new RestTemplate().getForEntity("http://localhost:8081/invoices/reference/{reference}", Invoice.class,uriVariables);
	    if(responseEntity.getBody().getReference()=="OCDTC8OK") {
	    	System.out.println("ReferenceExists");
	    }
	    return responseEntity.getBody();
//	    ResponseEntity<CollectionModel<EntityModel<Invoice>>> responseEntity = 
//	            restTemplate.exchange(apiUrl, HttpMethod.GET, null, 
//	                                   new ParameterizedTypeReference<CollectionModel<EntityModel<Invoice>>>() {});
//
//	        if (responseEntity.getStatusCode().is2xxSuccessful()) {
//	            CollectionModel<EntityModel<Invoice>> invoiceResources = responseEntity.getBody();
//	            if (invoiceResources != null) {
//	                for (EntityModel<Invoice> invoiceResource : invoiceResources.getContent()) {
//	                    Invoice invoice = invoiceResource.getContent();
//	                    invoiceRepository.save(invoice); 
//	                }
//	            }
//	        } 
	}

//	public Invoice findInvoiceByStudentId(String studentid) {
//		Invoice invoice=invoiceRepository.findByStudentid(studentid);
//		return invoice;
//	}
	
	public Invoice findInvoiceByStudentId(String studentid) {
	    try {
	        Invoice invoice = invoiceRepository.findByStudentid(studentid);
	        if (invoice != null) {
	            System.out.println("Found invoice for student ID " + studentid + ": " + invoice.toString());
	            return invoice;
	        } else {
	            System.out.println("No invoice found for student ID: " + studentid);
	            return null;
	        }
	    } catch (Exception e) {
	        System.out.println("Error finding invoice by student ID: " + e.getMessage());
	        e.printStackTrace();
	        return null;
	    }
	}
	
}

class InvoiceResponse {
    private Embedded embedded;

	public Embedded getEmbedded() {
		return embedded;
	}

	public void setEmbedded(Embedded embedded) {
		this.embedded = embedded;
	}
    
}

class Embedded {
    private List<Invoice> invoiceList;

	public List<Invoice> getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(List<Invoice> invoiceList) {
		this.invoiceList = invoiceList;
	}
    
}