/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import model.entityDAO.UserDAO;
import org.apache.log4j.Logger;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author Robert Cuprik <robertcuprik@hotmail.com>
 */
@Service
@Transactional(readOnly = true)
public class VavaUserDetailService implements UserDetailsService{
    
    private static final Logger logger = Logger.getLogger(VavaUserDetailService.class);


    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {

        logger.debug("LoadUserByUsername called with argument: "+login);
        
        model.entity.User domainUser = userDAO.getUser(login);
        if(domainUser!=null){
        
            logger.debug("LoadUserByUsername got user from userDAO: "+domainUser.toString());
        
        } else {
        
            logger.debug("LoadUserByUsername got user from userDAO: null");
            throw new UsernameNotFoundException("No user with name: "+login);
        
        }
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        
        logger.debug("Trying to create spring security User");
        UserDetails u = new User(
                domainUser.getUsername(),
                domainUser.getPassword(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                getAuthorities(domainUser.getRole().getId())
        );
        
        
        
        return u;
    }

    public Collection<GrantedAuthority> getAuthorities(Integer role) {
        List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
        return authList;
    }

    public List<String> getRoles(Integer role) {

        List<String> roles = new ArrayList<>();

        if(role >= 1)
            roles.add("ROLE_STUDENT");
        
        if(role >= 2)
            roles.add("ROLE_TEACHER");
        
        if(role >= 3)
            roles.add("ROLE_ADMIN");
        
        
        return roles;
    }

    public static List getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

}
