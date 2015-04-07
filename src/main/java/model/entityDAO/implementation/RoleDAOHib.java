/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entityDAO.implementation;

import model.entity.Role;
import model.entityDAO.RoleDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Robert Cuprik <robertcuprik@hotmail.com>
 */
public class RoleDAOHib implements RoleDAO{
    
    @Autowired
    private SessionFactory sessionFactory;
 
    public RoleDAOHib() {
         
    }
     
    public RoleDAOHib(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Role getRole(int id) {
        
        Role role = 
            (Role) sessionFactory.getCurrentSession().load(Role.class, id);
	return role;
                
    }
    
}
