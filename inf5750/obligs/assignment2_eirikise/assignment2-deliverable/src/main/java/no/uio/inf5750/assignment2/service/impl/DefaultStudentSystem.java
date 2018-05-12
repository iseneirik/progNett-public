package no.uio.inf5750.assignment2.service.impl;

import no.uio.inf5750.assignment2.dao.CourseDAO;
import no.uio.inf5750.assignment2.dao.StudentDAO;
import no.uio.inf5750.assignment2.model.Course;
import no.uio.inf5750.assignment2.model.Student;
import no.uio.inf5750.assignment2.service.StudentSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by iseneirik on 9/15/16.
 */
@Component("defaultStudentSystem")
public class DefaultStudentSystem implements StudentSystem {

    @Autowired
    private StudentDAO studentDao;
    @Autowired
    private CourseDAO courseDao;

    @Override
    public void setStudentLocation(int studentId, String latitude, String longitude) {
        Student student = studentDao.getStudent(studentId);
        student.setLongitude(longitude);
        student.setLatitude(latitude);
    }

    @Override
    public int addCourse(String courseCode, String name) {
        return courseDao.saveCourse(new Course(courseCode, name));
    }

    @Override
    public void updateCourse(int courseId, String courseCode, String name) {
        Course course = courseDao.getCourse(courseId);
        course.setCourseCode(courseCode);
        course.setName(name);
    }

    @Override
    public Course getCourse(int courseId) {
        return courseDao.getCourse(courseId);
    }

    @Override
    public Course getCourseByCourseCode(String courseCode) {
        return courseDao.getCourseByCourseCode(courseCode);
    }

    @Override
    public Course getCourseByName(String name) {
        return courseDao.getCourseByName(name);
    }

    @Override
    public Collection<Course> getAllCourses() {
        return courseDao.getAllCourses();
    }

    @Override
    public void delCourse(int courseId) {
        Course course = courseDao.getCourse(courseId);
        for (Student attendant : course.getAttendants()) {
            attendant.getCourses().remove(course);
        }
        courseDao.delCourse(course);
    }

    @Override
    public void addAttendantToCourse(int courseId, int studentId) {
        Course course = courseDao.getCourse(courseId);
        Student student = studentDao.getStudent(studentId);
        course.getAttendants().add(student);
        student.getCourses().add(course);
    }

    @Override
    public void removeAttendantFromCourse(int courseId, int studentId) {
        Course course = courseDao.getCourse(courseId);
        Student student = studentDao.getStudent(studentId);
        course.getAttendants().remove(student);
        student.getCourses().remove(course);
    }

    @Override
    public int addStudent(String name) {
        return studentDao.saveStudent(new Student(name));
    }

    @Override
    public void updateStudent(int studentId, String name) {
        Student student = studentDao.getStudent(studentId);
        student.setName(name);
    }

    @Override
    public Student getStudent(int studentId) {
        return studentDao.getStudent(studentId);
    }

    @Override
    public Student getStudentByName(String name) {
        return studentDao.getStudentByName(name);
    }

    @Override
    public Collection<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    @Override
    public void delStudent(int studentId) {
        studentDao.delStudent(studentDao.getStudent(studentId));
    }
}
