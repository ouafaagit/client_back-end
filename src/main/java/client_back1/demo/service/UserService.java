package client_back1.demo.service;

import client_back1.demo.entity.Doctor;
import client_back1.demo.entity.Provider;
import client_back1.demo.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;


public interface UserService {
    User findOne(String email);

    User get(long id);

    Collection<User> findByRole(String role);

   // User save(User user);

    @Transactional
    Doctor save(Doctor user);


  /*  @Transactional
    Provider savepr(Provider user);

    @Transactional
    Provider savepr(String user) throws JsonProcessingException;*/


    Provider savprovdd(String user, String userr) throws JsonProcessingException;

    Provider savep(String userr) throws JsonProcessingException;

    @Transactional
    Provider savprovd(Provider user);
    // User update(User user);
}
