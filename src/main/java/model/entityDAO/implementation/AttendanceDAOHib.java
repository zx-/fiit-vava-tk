/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entityDAO.implementation;

import java.util.Collection;
import java.util.List;
import model.entity.Attendance;
import model.entityDAO.AttendanceDAO;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Robert Cuprik <robertcuprik@hotmail.com>
 */
public class AttendanceDAOHib implements AttendanceDAO{

    private static final Logger logger = Logger.getLogger(AttendanceDAOHib.class);

    @Autowired
    private SessionFactory sessionFactory;
    
    public AttendanceDAOHib() {
         
    }
     
    public AttendanceDAOHib(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public Collection<Attendance> getAttendancesByStudentAndSubject(
            int userId, 
            int subjectId
    ) {
        
        @SuppressWarnings("unchecked")
        List<Attendance> list = (List<Attendance>) sessionFactory.getCurrentSession()
             .createCriteria(Attendance.class)
                .add(Restrictions.eq("user.user_id", userId))
                .add(Restrictions.eq("subject.id", subjectId))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        
        
        
        return list;
        
    }
    
}
