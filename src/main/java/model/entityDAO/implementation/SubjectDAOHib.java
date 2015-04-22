/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entityDAO.implementation;

import model.entity.Subject;
import model.entityDAO.SubjectDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Robert Cuprik <robertcuprik@hotmail.com>
 */
public class SubjectDAOHib implements SubjectDAO{

    @Autowired
    private SessionFactory sessionFactory;
    
    public SubjectDAOHib() {
         
    }
     
    public SubjectDAOHib(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    @Transactional
    public Subject getById(int id) {
    
        Subject s = 
            (Subject) sessionFactory.getCurrentSession().get(Subject.class, id);
	return s;
        
    }

    @Override
    @Transactional
    public void saveOrUpdate(Subject s) {
        sessionFactory.getCurrentSession().merge(s);
    }
    
}
