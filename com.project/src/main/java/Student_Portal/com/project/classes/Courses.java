package Student_Portal.com.project.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Courses {
	@Id
	@GeneratedValue
	private long id;
	private String course_name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id",referencedColumnName = "studentId")
	@JsonIgnore
	private Student student;
	
	public Courses(long id, String course_name) {
		super();
		this.id = id;
		this.course_name = course_name;
	}
		
	public Courses() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
}
