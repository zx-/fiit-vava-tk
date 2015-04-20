/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.utils;

import java.util.ArrayList;
import java.util.Collection;
import model.entity.ClassRoom;
import model.entity.Role;
import model.entity.Subject;
import model.entity.User;
import model.entityDAO.ClassRoomDAO;
import model.entityDAO.RoleDAO;
import model.entityDAO.UserDAO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Robert Cuprik <robertcuprik@hotmail.com>
 */
@Component
@Transactional()
public class DBPopulator {
    
    private static final Logger logger = Logger.getLogger(DBPopulator.class);
    
    @Autowired
    private UserDAO userDao;
    
    @Autowired
    private RoleDAO roleDao;
    
    @Autowired 
    private ClassRoomDAO classRoomDao;
    
    

    public boolean populate() {
  
        logger.info("DB population started");
        
        // INITIATE ROLES
        Role studentRole,teacherRole;
        ArrayList<User> students = new ArrayList<>();
        ArrayList<User> teachers = new ArrayList<>();
        ArrayList<ClassRoom> classrooms = new ArrayList<>();
        
        try{
        
            studentRole = new Role();
            studentRole.setRole("ROLE_STUDENT");   
            roleDao.saveOrUpdate(studentRole);

            teacherRole = new Role();
            teacherRole.setRole("ROLE_TEACHER");    
            roleDao.saveOrUpdate(teacherRole);
            
            logger.info("Roles created");
            
            for(int i = 0 ; i < 15; i++){
            
                User student = new User();
                student.setUsername("Student"+i);
                student.setPassword("Student"+i);
                student.setEmail("Student"+i+"@email.com");
                student.setRole(studentRole);
                students.add(student);
            
            }
            
            logger.info("Students created");
            
            for(int i = 0 ; i < 3; i++){
            
                User teacher = new User();
                teacher.setUsername("Teacher"+i);
                teacher.setPassword("Teacher"+i);
                teacher.setEmail("Teacher"+i+"@email.com");
                teacher.setRole(teacherRole);
                teachers.add(teacher);
            
            }
            
            logger.info("Teachers created");
            
            for(int i = 0; i< 3; i++){
            
                ClassRoom c = new ClassRoom();
                c.setName("ClassRoom"+i);
                c.setTeacher(teachers.get(i));
                
                for(int j = 0; j<5; j++){
                
                    c.addStudent(students.get(5*i + j));
                    students.get(5*i + j).setClassRoom(c);
                
                }
                classrooms.add(c);
            
            }
            
            for(int i = 0; i < 3; i++){
            
                for( int j = 0; j < 3; j++){                
                    
                    Subject sub = new Subject();
                    
                    sub.setSubjectOrder((j+i) % 3);
                    sub.setName("Subject"+i);
                    
                    sub.setTeacher(teachers.get(i));
                    teachers.get(i).addTeaching(sub); 
                    
                    sub.setClassRoom(classrooms.get(j));
                    classrooms.get(j).addSubject(sub);                    
                    
                    
                
                }                
            
            }
            
            logger.info("Classrooms created");
            
            for(User u : students){
            
                userDao.saveOrUpdate(u);
            
            }
            for(User u : teachers){
            
                userDao.saveOrUpdate(u);
            
            }
            
            for(ClassRoom c:classrooms){
            
                classRoomDao.saveOrUpdate(c);
            
            }
            
            logger.info("Entities written");
            
        
        } catch (Exception e){
        
            e.printStackTrace();
            logger.fatal(e);
            return false;
        
        }        
        
        
        
        return true;
    
    }
    
}
