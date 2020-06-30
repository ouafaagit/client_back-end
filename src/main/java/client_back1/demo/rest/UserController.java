package client_back1.demo.rest;

import client_back1.demo.entity.*;
import client_back1.demo.security.JWT.JwtProvider;
import client_back1.demo.service.ContactService;
import client_back1.demo.service.DoctorService;
import client_back1.demo.service.ProviderService;
import client_back1.demo.service.UserService;
import client_back1.demo.vo.request.LoginForm;
import client_back1.demo.vo.response.JwtResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;
import javax.validation.Valid;

import static org.springframework.http.MediaType.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    DoctorService doctorService;
    @Autowired
    ProviderService providerService;
    @Autowired
    ContactService contactService;
    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginForm loginForm) {
        System.out.println("aaaaaaaaaaaaaaa");
        // throws Exception if authentication failed

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generate(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            //User user = userService.findOne(userDetails.getUsername());
            Doctor user = doctorService. findOne(userDetails.getUsername());
            if (user==null){
                Provider use = providerService.findlogin(userDetails.getUsername());
                System.out.println("**"+use.getEmail()+use.getId()+use.getRole()+jwt);
                return ResponseEntity.ok(new JwtResponse(jwt, use.getEmail(), use.getId(), use.getRole()));
            }else {

                System.out.println("**"+user.getEmail()+user.getLastname()+user.getRole()+jwt);
                return ResponseEntity.ok(new JwtResponse(jwt, user.getEmail(), user.getId(), user.getRole()));
            }


        } catch (AuthenticationException e) {
            System.out.println("UNAUTHORIZED"+e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


   /*@PostMapping("/register")
    public ResponseEntity<User> save(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.save(user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }*/
@PostMapping("/registerDoctor")
public Doctor save(@Valid @RequestBody Doctor user) {
  // Doctor user= new Doctor("f@gmail.com", "ffffff", "f", "f", "f", "f");
 //   user= new Doctor("f@gmail.com", "ffffff");
    System.out.println("gg"+user.toString());
        return  userService.save(user);
    }
    @PostMapping("/register-provider")
    public Provider saveprovider(@RequestParam("user") String user,@RequestParam("special") String s) throws JsonProcessingException {
    //public Provider saveprovider(@RequestParam("provider") String user,@RequestParam("newspeciality") String s) throws JsonProcessingException {
        System.out.println("gg"+user);
       return  userService.savprovdd(user,s);
      // return  userService.savprovd(user);
       // return  userService.savep(user);

    }

    // add contact
    @PostMapping("/User/Contact/new")
    public Contact create(@Valid @RequestBody Contact complaint) {

        return contactService.save(complaint);
    }

}
