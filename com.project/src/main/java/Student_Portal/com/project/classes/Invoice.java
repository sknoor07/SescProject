package Student_Portal.com.project.classes;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
@Entity
public class Invoice {
	@Id
	@GeneratedValue 
	long id;
	private String reference;
	private Double amount;
	private LocalDate dueDate;
	private Type type;
	private Status status;
	private String studentid;
	public Invoice(long id, String reference, Double amount,LocalDate dueDate,Type type, Status status,String studentid) {
		super();
		this.id = id;
		this.reference = reference;
		this.amount = amount;
		this.dueDate=dueDate;
		this.type=type;
		this.status = status;
		this.studentid=studentid;
	}
	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getStudentid() {
		return studentid;
	}
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	// this is the clone of the invoice class
}
