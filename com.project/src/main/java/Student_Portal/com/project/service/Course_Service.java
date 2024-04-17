package Student_Portal.com.project.service;

import java.util.List;

import org.springframework.stereotype.Component;

import Student_Portal.com.project.classes.Courses;
import Student_Portal.com.project.repository.Course_Repository;

@Component
public class Course_Service {
	private Course_Repository course_Repository;

	public Course_Service(Course_Repository course_Repository) {
		super();
		this.course_Repository = course_Repository;
	}
	
	public List<Courses> findallCourses(){
		List<Courses> courses=course_Repository.findAll();
		return courses;
	}
	
	public Courses findCourseById(Long id) {
		Courses course=course_Repository.findById(id).get();
		return course;
	}
	
	public Courses addCourse(Courses course) {
		Courses added_course=course_Repository.save(course);
		return added_course;
	}
	
	public void deleteCourse(Long id) {
		course_Repository.deleteById(id);
	}
	
	public List<Courses> getCourseByStudentId(String id){
		return course_Repository.findByStudentStudentId(id);
	}
	
	public Courses updateCourse(Courses course) {
		Courses added_course=course_Repository.save(course);
		return added_course;
	}
	
}
