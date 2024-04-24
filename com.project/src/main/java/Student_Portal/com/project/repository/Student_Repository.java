package Student_Portal.com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Student_Portal.com.project.classes.Student;

@Repository
public interface Student_Repository extends JpaRepository<Student, Long>{
	Student findByStudentId(String studentid);
}
