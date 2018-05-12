package no.uio.inf5750.assignment2.dao.hibernate;

import no.uio.inf5750.assignment2.dao.CourseDAO;
import no.uio.inf5750.assignment2.model.Course;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by iseneirik on 9/15/16.
 */
@Transactional
public class HibernateCourseDao implements CourseDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int saveCourse(Course course) {
        return (int) sessionFactory.getCurrentSession().save(course);
    }

    @Override
    public Course getCourse(int id) {
        return sessionFactory.getCurrentSession().get(Course.class, id);
    }

    @Override
    public Course getCourseByCourseCode(String courseCode) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Course.class);
        criteria.add(Restrictions.eq("courseCode", courseCode));
        return (Course) criteria.uniqueResult();
    }

    @Override
    public Course getCourseByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Course.class);
        criteria.add(Restrictions.eq("name", name));
        return (Course) criteria.uniqueResult();
    }

    @Override
    public Collection<Course> getAllCourses() {
        return sessionFactory.getCurrentSession().createQuery("from Course").list();
    }

    @Override
    public void delCourse(Course course) {
        sessionFactory.getCurrentSession().delete(course);
    }
}
