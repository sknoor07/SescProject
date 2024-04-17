package Student_Portal.com.project.controller;

import java.util.List;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Student_Portal.com.project.classes.Courses;
import Student_Portal.com.project.classes.Student;
import Student_Portal.com.project.service.Course_Service;
import Student_Portal.com.project.service.Student_Service;

@RestController
public class Course_Controller {
	
	private Course_Service course_Service;
	private Student_Service student_Service;

	public Course_Controller(Course_Service course_Service,Student_Service student_Service) {
		super();
		this.course_Service = course_Service;
		this.student_Service= student_Service;
	}
	
	@GetMapping("/courses")
	public List<Courses>getAllCourse(){
		List<Courses> courses= course_Service.findallCourses();
		return courses;
	}
	
	@GetMapping("/course/{id}")
	public Courses getCourseById(@PathVariable Long id) {
		Courses course=course_Service.findCourseById(id);
		return course;
	}
	
	@GetMapping("/course/{student_id}/student")
	public List<Courses> getCourseByStudentId(@PathVariable String student_id){
		List<Courses> courses=course_Service.getCourseByStudentId(student_id);
		return courses;
	}
	
	@PostMapping("/courses")
	public void addCourse(@RequestBody Courses course) {
		course_Service.addCourse(course);
	}
	
	@GetMapping("/Course/delete/{id}")
	public void deleteByid(@PathVariable Long id) {
		course_Service.deleteCourse(id);
	}
	
	@GetMapping("/course/{course_name}/student/{student_id}")
	public void addCourseByStudentId(@PathVariable String course_name,@PathVariable String student_id){
		Student student= new Student();
		student=student_Service.findStudentById(student_id);
		Courses course= new Courses();
		course.setCourse_name(course_name);
		course.setStudent(student);
		course_Service.addCourse(course);
	}
	
	
}
