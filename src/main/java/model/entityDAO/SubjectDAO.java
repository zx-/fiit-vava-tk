/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entityDAO;

import model.entity.Subject;

/**
 *
 * @author Robert Cuprik <robertcuprik@hotmail.com>
 */
public interface SubjectDAO {
    
    public Subject getById(int id);
    
    public void saveOrUpdate(Subject s);
    
}
