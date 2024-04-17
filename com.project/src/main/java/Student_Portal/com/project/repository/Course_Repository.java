package Student_Portal.com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Student_Portal.com.project.classes.Courses;

@Repository
public interface Course_Repository extends JpaRepository<Courses, Long>{
 List<Courses> findByStudentStudentId(String studentid);
}

