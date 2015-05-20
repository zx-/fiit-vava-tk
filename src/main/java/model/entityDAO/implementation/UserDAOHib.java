/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entityDAO.implementation;

import java.util.Collection;
import model.entity.User;
import model.entityDAO.UserDAO;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import model.entity.Attendance;
import model.entity.Grade;
import model.entity.Subject;
import org.apache.log4j.Logger;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
 
 
public class UserDAOHib implements UserDAO{

    private static final Logger logger = Logger.getLogger(UserDAOHib.class);
    
    @Autowired
    private SessionFactory sessionFactory;
 
    public UserDAOHib() {
         
    }
     
    public UserDAOHib(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
 
    @Override
    @Transactional
    public List<User> list() {
        @SuppressWarnings("unchecked")
        List<User> listUser = (List<User>) sessionFactory.getCurrentSession()
                .createCriteria(User.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listUser;
    }
 
    @Override
    @Transactional
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public void saveOrUpdate(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }
 
    @Override
    @Transactional
    public void delete(int id) {
        User userToDelete = new User();
        userToDelete.setUser_id(id);
        sessionFactory.getCurrentSession().delete(userToDelete);
    }
 
    @Override
    @Transactional
    public User get(int id) {
        String hql = "from User where user_id=:id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
         
        @SuppressWarnings("unchecked")
        List<User> listUser = (List<User>) query.list();
         
        if (listUser != null && !listUser.isEmpty()) {
            return listUser.get(0);
        }
         
        return null;
    }

    @Override
    @Transactional
    public User getUser(String login) {
        
        logger.debug("getUser called with parameter: "+login);
        
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        User u = (User) criteria.add(Restrictions.eq("username", login))
                             .uniqueResult();
        logger.debug("Result: "+u);
        return u;        
        
    }

    @Override
    public Collection<Attendance> getAttendanceBySubject(User user, Subject s) {
    
        
        Property student = Property.forName("student");
        Criteria criteria = 
                sessionFactory.getCurrentSession().createCriteria(Attendance.class);
        
        criteria.add(null);
        
        return null;
    
    }

    @Override
    public Collection<Grade> getGradesBySubject(User user, Subject s) {
    
        Property student = Property.forName("student");
        Property subject = Property.forName("subject");
        Criteria criteria = 
            sessionFactory.getCurrentSession().createCriteria(Grade.class);
        
        criteria.add(Restrictions.conjunction()
                .add(student.eq(user))
                .add(subject.eq(s)));
        
        return criteria.list();
    
    }

}