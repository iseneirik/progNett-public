package no.uio.inf5750.assignment2.gui.controller;

import no.uio.inf5750.assignment2.model.Course;
import no.uio.inf5750.assignment2.model.Student;
import no.uio.inf5750.assignment2.service.StudentSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by iseneirik on 9/16/16.
 */
@Controller
public class ApiController {

    @Autowired
    private StudentSystem studentSystem;

    @RequestMapping(value = "/api/student", method = RequestMethod.GET)
    @ResponseBody
    public Collection<Student> getStudents() {
        return studentSystem.getAllStudents();
    }


    @RequestMapping(value = "/api/course", method = RequestMethod.GET)
    @ResponseBody
    public Collection<Course> getCourses() {
        return studentSystem.getAllCourses();
    }

    @RequestMapping(value = "/api/student/{id}/location", method = RequestMethod.GET)
    @ResponseBody
    public Collection<Student> setLocation(
            @PathVariable("id") int id,
            @RequestParam("lat") String latitude,
            @RequestParam("long") String longitude) {
        studentSystem.setStudentLocation(id, latitude, longitude);
        return studentSystem.getAllStudents();
    }
}
