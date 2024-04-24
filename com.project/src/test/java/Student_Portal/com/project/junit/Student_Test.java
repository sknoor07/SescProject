package Student_Portal.com.project.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import Student_Portal.com.project.classes.Courses;
import Student_Portal.com.project.classes.Invoice;
import Student_Portal.com.project.classes.Status;
import Student_Portal.com.project.classes.Student;
import Student_Portal.com.project.controller.Course_Controller;
import Student_Portal.com.project.controller.Student_Controller;
import Student_Portal.com.project.service.Course_Service;
import Student_Portal.com.project.service.Invoice_Service;
import Student_Portal.com.project.service.Student_Service;

class Student_Test {
	private Student_Controller studentController;
    private Student_Service studentServiceMock;
    private Invoice_Service invoiceServiceMock;
    private RestTemplate restTemplateMock;
    private Course_Controller courseController;
    private Course_Service courseServiceMock;
    
	@BeforeEach
    public void setUp() {
        studentServiceMock = mock(Student_Service.class);
        invoiceServiceMock = mock(Invoice_Service.class);
        restTemplateMock = mock(RestTemplate.class);
        courseServiceMock = mock(Course_Service.class);
        studentServiceMock = mock(Student_Service.class);
        
        studentController = new Student_Controller(studentServiceMock, invoiceServiceMock);
        studentController.restTemplate = restTemplateMock;
        
        courseController = new Course_Controller(courseServiceMock, studentServiceMock);
    }

    @Test
    public void testGetAllStudent() {
        List<Student> students = new ArrayList<>();
        when(studentServiceMock.findAllStudent()).thenReturn(students);

        assertEquals(students, studentController.getAllStudent());
    }

    @Test
    public void testGetStudentById() {
        Student student = new Student();
        student.setStudentId("1");
        when(studentServiceMock.findStudentById("1")).thenReturn(student);

        assertEquals(student, studentController.getStudentById("1"));
    }

    @Test
    public void testCreateStudent() {
    	Student student = new Student();
        student.setStudentId("1");

        when(studentServiceMock.addStudent(any(Student.class))).thenReturn(student);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("http://localhost:8080/student/1")); 

        ResponseEntity<String> responseEntity = new ResponseEntity<>(headers, HttpStatus.CREATED);
        
        when(restTemplateMock.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
                .thenReturn(responseEntity);

        ResponseEntity<Object> response = studentController.createStudent(student);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testGetInvoiceStatus_Outstanding() {
        Invoice invoice = new Invoice();
        invoice.setReference("123");
        invoice.setStatus(Status.OUTSTANDING);

        when(invoiceServiceMock.getInvoiceByReferenceId("123")).thenReturn(invoice);

        assertEquals("invoice Outstanding", studentController.getInvoiceStatus("123"));
    }

    @Test
    public void testGetInvoiceStatus_Paid() {
        Invoice invoice = new Invoice();
        invoice.setReference("123");
        invoice.setStatus(Status.PAID);

        when(invoiceServiceMock.getInvoiceByReferenceId("123")).thenReturn(invoice);

        assertEquals("No Invoice pending", studentController.getInvoiceStatus("123"));
    }
    
    @Test
    public void testGetAllCourse() {
        List<Courses> courses = new ArrayList<>();
        when(courseServiceMock.findallCourses()).thenReturn(courses);

        assertEquals(courses, courseController.getAllCourse());
    }

    @Test
    public void testGetCourseById() {
        Courses course = new Courses();
        course.setId(1L);
        when(courseServiceMock.findCourseById(1L)).thenReturn(course);

        assertEquals(course, courseController.getCourseById(1L));
    }

    @Test
    public void testGetCourseByStudentId() {
        String studentId = "1";
        List<Courses> courses = new ArrayList<>();
        when(courseServiceMock.getCourseByStudentId(studentId)).thenReturn(courses);

        assertEquals(courses, courseController.getCourseByStudentId(studentId));
    }

    @Test
    public void testAddCourse() {
        Courses course = new Courses();

        courseController.addCourse(course);

        verify(courseServiceMock, times(1)).addCourse(course);
    }

    @Test
    public void testDeleteByid() {
        courseController.deleteByid(1L);

        verify(courseServiceMock, times(1)).deleteCourse(1L);
    }

    @Test
    public void testAddCourseByStudentId() {
        String courseName = "Math";
        String studentId = "1";
        Student student = new Student();
        student.setStudentId(studentId);
        Courses course = new Courses();
        course.setCourse_name(courseName);
        course.setStudent(student);

        when(studentServiceMock.findStudentById(studentId)).thenReturn(student);

        courseController.addCourseByStudentId(courseName, studentId);

        ArgumentCaptor<Courses> courseCaptor = ArgumentCaptor.forClass(Courses.class);
        verify(courseServiceMock, times(1)).addCourse(courseCaptor.capture());
        
        Courses capturedCourse = courseCaptor.getValue();
        assertEquals(courseName, capturedCourse.getCourse_name());
        assertEquals(student, capturedCourse.getStudent());
    }    

}
