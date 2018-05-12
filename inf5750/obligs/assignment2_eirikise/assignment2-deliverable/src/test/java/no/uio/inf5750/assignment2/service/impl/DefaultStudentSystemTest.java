package no.uio.inf5750.assignment2.service.impl;

import no.uio.inf5750.assignment2.model.Course;
import no.uio.inf5750.assignment2.model.Student;
import no.uio.inf5750.assignment2.service.StudentSystem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by iseneirik on 9/20/16.
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/META-INF/assignment2/beans.xml")
public class DefaultStudentSystemTest {

    @Autowired
    private StudentSystem studentSystem;

    @Before
    public void setUp() throws Exception {
        assertNotNull("StudentSystem should be initialized!", studentSystem);
    }

    @Test
    public void setStudentLocationTest() throws Exception {
        int id = studentSystem.addStudent("Test Testesen");
        System.out.println(id);
        String latitude = "10.10";
        String longitude = "20.20";
        studentSystem.setStudentLocation(id, latitude, longitude);
        Student student = studentSystem.getStudent(id);

        assertEquals(latitude, student.getLatitude());
        assertEquals(longitude, student.getLongitude());
    }

    @Test
    public void addGetCourseTest() throws Exception {
        int id = studentSystem.addCourse("TEST1000", "Testing");
        Course course = studentSystem.getCourse(id);

        assertNotNull(course);
        assertEquals(id, course.getId());
        assertEquals("TEST1000", course.getCourseCode());
        assertEquals("Testing", course.getName());
    }

    @Test
    public void updateCourseTest() throws Exception {
        int id = studentSystem.addCourse("TEST1000", "Testing");
        studentSystem.updateCourse(id, "TEST1001", "Testing2");
        Course course = studentSystem.getCourse(id);

        assertNotNull(course);
        assertEquals(id, course.getId());
        assertEquals("TEST1001", course.getCourseCode());
        assertEquals("Testing2", course.getName());
    }

    @Test
    public void getCourseByCourseCodeTest() throws Exception {
        int id = studentSystem.addCourse("TEST1000", "Testing");
        Course course = studentSystem.getCourseByCourseCode("TEST1000");

        assertNotNull(course);
        assertEquals(id, course.getId());
        assertEquals("TEST1000", course.getCourseCode());
        assertEquals("Testing", course.getName());
    }

    @Test
    public void getCourseByNameTest() throws Exception {
        int id = studentSystem.addCourse("TEST1000", "Testing");
        Course course = studentSystem.getCourseByName("Testing");

        assertNotNull(course);
        assertEquals(id, course.getId());
        assertEquals("TEST1000", course.getCourseCode());
        assertEquals("Testing", course.getName());
    }

    @Test
    public void getAllCoursesTest() throws Exception {
        studentSystem.addCourse("TEST1000", "Testing0");
        studentSystem.addCourse("TEST1001", "Testing1");
        studentSystem.addCourse("TEST1002", "Testing2");
        Collection<Course> courses = studentSystem.getAllCourses();

        assertEquals(3, courses.size());
    }

    @Test
    public void delCourseTest() throws Exception {
        int id = studentSystem.addCourse("TEST1000", "Testing");
        studentSystem.delCourse(id);
        Course course = studentSystem.getCourse(id);

        assertNull(course);
    }

    @Test
    public void addAttendantToCourseTest() throws Exception {
        int courseId = studentSystem.addCourse("TEST1000", "Testing");
        int studentId = studentSystem.addStudent("Test Testesen");
        studentSystem.addAttendantToCourse(courseId, studentId);
        Course course = studentSystem.getCourse(courseId);
        Student student = studentSystem.getStudent(studentId);

        assertTrue(course.getAttendants().contains(student));
    }

    @Test
    public void removeAttendantFromCourseTest() throws Exception {
        int courseId = studentSystem.addCourse("TEST1000", "Testing");
        int studentId = studentSystem.addStudent("Test Testesen");

        studentSystem.addAttendantToCourse(courseId, studentId);
        Course course = studentSystem.getCourse(courseId);
        Student student = studentSystem.getStudent(studentId);

        assertTrue(course.getAttendants().contains(student));

        studentSystem.removeAttendantFromCourse(courseId, studentId);
        course = studentSystem.getCourse(courseId);
        student = studentSystem.getStudent(courseId);

        assertFalse(course.getAttendants().contains(student));
    }

    @Test
    public void addGetStudentTest() throws Exception {
        int id = studentSystem.addStudent("Test Testesen");
        Student student = studentSystem.getStudent(id);

        assertNotNull(student);
        assertEquals(id, student.getId());
        assertEquals("Test Testesen", student.getName());
    }

    @Test
    public void updateStudentTest() throws Exception {
        int id = studentSystem.addStudent("Test Testesen");
        studentSystem.updateStudent(id, "Testesen Test");
        Student student = studentSystem.getStudent(id);

        assertNotNull(student);
        assertEquals(id, student.getId());
        assertEquals("Testesen Test", student.getName());
    }

    @Test
    public void getStudentByNameTest() throws Exception {
        int id = studentSystem.addStudent("Test Testesen");
        Student student = studentSystem.getStudentByName("Test Testesen");

        assertNotNull(student);
        assertEquals(id, student.getId());
        assertEquals("Test Testesen", student.getName());
    }

    @Test
    public void getAllStudentsTest() throws Exception {
        studentSystem.addStudent("Test Testesen");
        studentSystem.addStudent("Test Testesto");
        studentSystem.addStudent("Test Testestre");
        Collection<Student> students = studentSystem.getAllStudents();

        assertEquals(3, students.size());
    }

    @Test
    public void delStudentTest() throws Exception {
        int id = studentSystem.addStudent("Test Testesen");
        studentSystem.delStudent(id);
        Student student = studentSystem.getStudent(id);

        assertNull(student);
    }

}