package Student_Portal.com.project.service;

import java.util.List;

import org.springframework.stereotype.Component;

import Student_Portal.com.project.classes.Student;
import Student_Portal.com.project.repository.StudentRepository;

@Component
public class Student_Service {
	private StudentRepository  studentRepository;

	public Student_Service(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	public List<Student> findAllStudent() {
		List<Student> students=studentRepository.findAll();
		return students;
	}

	public Student findStudentById(String studentId) {
		Student student=studentRepository.findByStudentId(studentId);
		return student;
	}

	public Student addStudent(Student student) {
		Student savedstudent=studentRepository.save(student);
		return savedstudent;
	}
	
	
	
	
}
