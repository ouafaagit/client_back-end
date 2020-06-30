package client_back1.demo.service.impl;
//import com.google.gson.Gson;
import client_back1.demo.entity.*;
import client_back1.demo.enums.ResultEnum;
import client_back1.demo.exception.MyException;
import client_back1.demo.repository.*;
import client_back1.demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

@CrossOrigin(origins = "http://localhost:4200")
@Service
@DependsOn("passwordEncoder")
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    SpecialityRepository specialityRepository;
    @Autowired
    ProviderRepository providerRepository;
    List<Speciality> speciall=new ArrayList<>();
    // @Autowired
   // CartRepository cartRepository;

    @Override
    public User findOne(String email) {
        return userRepository.findByEmail(email);
    }
    @Override
    public User get(long id) {
        return userRepository.getOne(id);
    }

    @Override
    public Collection<User> findByRole(String role) {
        return userRepository.findAllByRole(role);
    }



/*    @Override
    @Transactional
    public User save(User user) {
        //register
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            User savedUser = userRepository.save(user);

            // initial Cart
           // Cart savedCart = cartRepository.save(new Cart(savedUser));
          //  savedUser.setCart(savedCart);
            return userRepository.save(savedUser);

        } catch (Exception e) {
            throw new MyException(ResultEnum.VALID_ERROR);
        }

    }*/

    @Override
    @Transactional
    public Doctor save(Doctor user) {
        //register
       user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
          //  Doctor savedUser =doctorRepository.save( new Doctor("f@gmail.com", "ffffff", "f", "f", "f", "f"));
            //Speciality sp =specialityRepository.save(user.getSpeciality());
          //  Speciality sp =specialityRepository.save(user.getSpeciality());
          //  Doctor savedUser =userRepository.save( user);

            ///user.setSpeciality(sp);
           // savedUser.setSpeciality(sp);
            // initial Cart
            // Cart savedCart = cartRepository.save(new Cart(savedUser));
            //  savedUser.setCart(savedCart);
          //  List<Product> wishlist=new ArrayList<>();
         //   List<Product> order=new ArrayList<>();
         //   user.setOrderproduct(order);
         //   user.setWishlist(wishlist);

           Doctor d=doctorRepository.save(user);
            // initial Cart
            Cart savedCart = cartRepository.save(new Cart(d));
            d.setCart(savedCart);
            d= doctorRepository.save(d);
            Doctor doctor=new Doctor();
           doctor.setId(d.getId());
           doctor.setFirstname(d.getFirstname());
           doctor.setLastname(d.getLastname());
           doctor.setEmail(d.getEmail());
           doctor.setSpeciality(d.getSpeciality());
           //doctor.setCart(new Quotation());
            return  doctor;

        } catch (Exception e) {
            System.out.println(e);
          //  throw new MyException(ResultEnum.VALID_ERROR);
            return null;
        }

    }

    @Override
    public Provider savprovdd(String usetr, String userr) throws JsonProcessingException {

        Provider provider = new ObjectMapper().readValue(usetr,Provider.class);
        //Provider user  = new Gson().fromJson(userr, Provider.class);
        // List<Speciality> sp=new ObjectMapper().readValue(user.getSpecialities(),Provider.class);
        //  List<Speciality> sp= new ObjectMapper().readValue(userr,Provider.class);

        ObjectMapper mapper = new ObjectMapper();
        List<Speciality> asList=new ArrayList<Speciality>();
        asList = mapper.readValue(
                userr, new TypeReference<List<Speciality>>() { });
     //   System.out.println("****size"+asList.size());
       // assertThat(asList.get(0), instanceOf(MyDto.class));
     //   Provider provider=user;
        provider.setPassword(passwordEncoder.encode(provider.getPassword()));
      //  provider.setSpecialities(new ArrayList<Speciality>());
       // System.out.println("****"+user.getSpecialities().size());
       // for(int i=0;i<user.getSpecialities().size();i++) {
        for(int i=0;i<asList.size();i++) {
        //    System.out.println("****"+user.getSpecialities().get(i).toString());
           // Speciality s=specialityRepository.getOne(asList.get(i).getId());
            Speciality s=asList.get(i);
            provider.getSpecialities().add(s);
        //    System.out.println("aaaaa"+provider.getSpecialities().get(i).toString());
         //   s.getProviders().add(provider);
        }
                provider=providerRepository.save(provider);
        Provider p=new Provider();
        p.setId(provider.getId());
        p.setFirstname(provider.getFirstname());
        p.setRole(provider.getRole());
        return p;
    }
    @Override
    public Provider savep(String userr) throws JsonProcessingException {

        Provider user = new ObjectMapper().readValue(userr,Provider.class);
        //Provider user  = new Gson().fromJson(userr, Provider.class);
       // List<Speciality> sp=new ObjectMapper().readValue(user.getSpecialities(),Provider.class);
      //  List<Speciality> sp= new ObjectMapper().readValue(userr,Provider.class);
        Provider provider=user;
        provider.setPassword(passwordEncoder.encode(user.getPassword()));
        provider.setSpecialities(new ArrayList<Speciality>());
        System.out.println("****"+user.getSpecialities().size());
        for(int i=0;i<user.getSpecialities().size();i++) {
            System.out.println("****"+user.getSpecialities().get(i).toString());
            Speciality s=specialityRepository.getOne(user.getSpecialities().get(i).getId());
            provider.getSpecialities().add(s);
            System.out.println("aaaaa"+provider.getSpecialities().get(i).toString());

            s.getProviders().add(provider);
        }
        return providerRepository.save(provider);
    }

    @Override
    @Transactional
    public Provider savprovd(Provider user)  {
        //register
        //speciall = new ObjectMapper().readValue(special,List<Speciality.class>);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

            Provider provider=user;
        provider.setSpecialities(new ArrayList<Speciality>());
            //Provider provider=new Provider();
           // provider.setId(user.getId());
       /*     provider.setFirstname(user.getFirstname());
            provider.setLastname(user.getLastname());
            provider.setEmail(user.getEmail());
            provider.setPhone(user.getPhone());
            provider.setName(user.getName());
            provider.setEmailsociety(user.getEmailsociety());
            provider.setPricesubscription(user.getPricesubscription());*/
        /*    provider.getSpecialities().addAll(user.getSpecialities().stream()
            .map(v->{ Speciality s=specialityRepository.getOne(v.getId());
            s.getProviders().add(provider);
            return s;
            }).collect(Collectors.toList()));*/
            //Provider savedUser =providerRepository.save(provider);
            System.out.println("****"+user.getSpecialities().size());
            for(int i=0;i<user.getSpecialities().size();i++) {
                System.out.println("****"+user.getSpecialities().get(i).toString());
                Speciality s=specialityRepository.getOne(user.getSpecialities().get(i).getId());
                provider.getSpecialities().add(s);
                System.out.println("aaaaa"+provider.getSpecialities().get(i).toString());

                s.getProviders().add(provider);
            }
           // Provider savedUser =providerRepository.save(provider);

            // initial Cart
            // Cart savedCart = cartRepository.save(new Cart(savedUser));
            //  savedUser.setCart(savedCart);
      /*
            provider.setId(savedUser.getId());
            provider.setFirstname(savedUser.getFirstname());
            provider.setLastname(savedUser.getLastname());
            provider.setEmail(savedUser.getEmail());*/
          //  provider.setSpeciality(d.getSpeciality());
            //provider.setCart(new Quotation());
            return providerRepository.save(provider);



    }


 /*   @Override
    @Transactional
    public User update(User user) {
        User oldUser = userRepository.findByEmail(user.getEmail());
        oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
        oldUser.setName(user.getName());
        oldUser.setPhone(user.getPhone());
        oldUser.setAddress(user.getAddress());
        return userRepository.save(oldUser);
    }*/

}
