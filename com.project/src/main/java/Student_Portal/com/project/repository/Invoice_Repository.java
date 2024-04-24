package Student_Portal.com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Student_Portal.com.project.classes.Invoice;
@Repository
public interface Invoice_Repository extends JpaRepository<Invoice, Long>{
	Invoice findByStudentid(String studentid);
}
