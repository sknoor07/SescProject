package Student_Portal.com.project.classes;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class Student {
	@Id
	@GeneratedValue
	private Long id;
	@Column(unique = true)
	private String studentId;
	
	private String name;
	@Transient
	private boolean hasOutstandingBalance;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Courses> Courses;
	
	public Student(Long id, String studentid, String name, boolean hasOutstandingBalance) {
		super();
		this.id = id;
		this.studentId = studentid;
		this.name = name;
		this.hasOutstandingBalance = hasOutstandingBalance;
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	
	public List<Courses> getCourses() {
		return Courses;
	}
	public void setCourses(List<Courses> courses) {
		Courses = courses;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
		
	}
	public boolean isHasOutstandingBalance() {
		return hasOutstandingBalance;
	}
	public void setHasOutstandingBalance(boolean hasOutstandingBalance) {
		this.hasOutstandingBalance = hasOutstandingBalance;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
