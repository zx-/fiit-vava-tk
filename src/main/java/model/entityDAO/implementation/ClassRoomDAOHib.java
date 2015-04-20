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
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Robert Cuprik <robertcuprik@hotmail.com>
 */
public class ClassRoomDAOHib implements ClassRoomDAO{
    
    private static final Logger logger = Logger.getLogger(ClassRoomDAOHib.class);

    @Autowired
    private SessionFactory sessionFactory;
    
    public ClassRoomDAOHib() {
         
    }
     
    public ClassRoomDAOHib(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    @Transactional
    public Collection<ClassRoom> getByTeacher(User u) {
    
        return sessionFactory.getCurrentSession()
                .createCriteria(ClassRoom.class)
                .add(Restrictions.eq("teacher", u))
                .list();
                
    
    }

    @Override
    @Transactional
    public Collection<ClassRoom> getAll() {
        
        @SuppressWarnings("unchecked")
        List<ClassRoom> listClassRoom = (List<ClassRoom>) sessionFactory.getCurrentSession()
             .createCriteria(ClassRoom.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listClassRoom; 
    
    }

    @Override
    @Transactional
    public void saveOrUpdate(ClassRoom c) {
    
        sessionFactory.getCurrentSession().saveOrUpdate(c);
    
    }

    @Override
    @Transactional
    public ClassRoom getByName(String name) {
  
        logger.debug("getByName called with parameter: "+name);
        
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ClassRoom.class);
        ClassRoom c = (ClassRoom) criteria.add(Restrictions.eq("name", name))
                             .uniqueResult();
        logger.debug("Result: "+c);
        
        return c;  
    
    }
    
}
