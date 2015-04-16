/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entityDAO.implementation;

import java.util.Collection;
import java.util.List;
import model.entity.ClassRoom;
import model.entity.User;
import model.entityDAO.ClassRoomDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Robert Cuprik <robertcuprik@hotmail.com>
 */
public class ClassRoomDAOHib implements ClassRoomDAO{

    @Autowired
    private SessionFactory sessionFactory;
    
    public ClassRoomDAOHib() {
         
    }
     
    public ClassRoomDAOHib(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public Collection<ClassRoom> getByTeacher(User u) {
    
        return sessionFactory.getCurrentSession()
                .createCriteria(ClassRoom.class)
                .add(Restrictions.eq("teacher", u))
                .list();
                
    
    }

    @Override
    public Collection<ClassRoom> getAll() {
        
        @SuppressWarnings("unchecked")
        List<ClassRoom> listClassRoom = (List<ClassRoom>) sessionFactory.getCurrentSession()
             .createCriteria(ClassRoom.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listClassRoom; 
    
    }

    @Override
    public void saveOrUpdate(ClassRoom c) {
    
        sessionFactory.getCurrentSession().saveOrUpdate(c);
    
    }
    
}
