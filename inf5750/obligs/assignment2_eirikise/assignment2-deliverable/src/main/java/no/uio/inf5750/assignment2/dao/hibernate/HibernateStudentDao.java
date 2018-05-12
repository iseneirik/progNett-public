package no.uio.inf5750.assignment2.dao.hibernate;

import no.uio.inf5750.assignment2.dao.StudentDAO;
import no.uio.inf5750.assignment2.model.Student;
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
public class HibernateStudentDao implements StudentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int saveStudent(Student student) {
        return (int) sessionFactory.getCurrentSession().save(student);
    }

    @Override
    public Student getStudent(int id) {
        return sessionFactory.getCurrentSession().get(Student.class, id);
    }

    @Override
    public Student getStudentByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Student.class);
        criteria.add(Restrictions.eq("name", name));
        return (Student) criteria.uniqueResult();
    }

    @Override
    public Collection<Student> getAllStudents() {
        return sessionFactory.getCurrentSession().createQuery("from Student").list();
    }

    @Override
    public void delStudent(Student student) {
        sessionFactory.getCurrentSession().delete(student);
    }
}
