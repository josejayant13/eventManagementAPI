package com.josejayant.EventManagementAPI.services;

import com.josejayant.EventManagementAPI.model.User;
import com.josejayant.EventManagementAPI.model.UserPrincipal;
import com.josejayant.EventManagementAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
/*for fetching username, passwordfrom the database.
* i have customized it to fetch user object. user object has ID, username and password*/
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user= repo.findByUsername(username);

        if (user==null) {
            System.out.println("User 404");
            throw new UsernameNotFoundException("User 404");
        }
//        need to return UserDetails type object. Hence the User object must be converted.
//        to UserDetails. UserDetials in an interface and UserPrincipal is implementation.
//        In UserPrincipal class we will create the UserDetials object. First we need to pass the
//        User object as input.
        return new UserPrincipal(user);
    }

}
