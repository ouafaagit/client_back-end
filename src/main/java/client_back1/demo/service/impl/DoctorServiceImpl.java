package client_back1.demo.service.impl;
import client_back1.demo.entity.*;
import client_back1.demo.repository.DoctorRepository;
import client_back1.demo.service.DoctorService;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@Service
@DependsOn("passwordEncoder")
public class DoctorServiceImpl implements DoctorService {
    private final PasswordEncoder passwordEncoder;
    private final DoctorRepository doctorRepository;


    public DoctorServiceImpl(PasswordEncoder passwordEncoder, DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
        this.passwordEncoder = passwordEncoder;
    }


    //////////////////////////////profile////////////////////////////////////////////////
    @Override
    public Doctor findOne(String email) {
        return doctorRepository.findByEmail(email);
    }
        //edit profile
        public Doctor getProfil(long id){
            System.out.println("************doctor"+id);
            Doctor doctor= this.doctorRepository.findById(id);
                  System.out.println("***********doctor"+doctor.getId());
            return doctor;
        }

        // update Doctor
        @Override
        public Doctor updateProfil(Doctor doctorInfo) {

            doctorInfo.setPassword(passwordEncoder.encode(doctorInfo.getPassword()));

            return doctorRepository.save(doctorInfo);
        }
        //add
        @Override
        public Doctor save(Doctor doctorInfo) {

        return doctorRepository.save(doctorInfo);
        }

    //new Quotation
 /*   @Override
    public void newQuotation(long id,Quotation Quotation){
        Doctor doctor = getProfil(id);
      //  Quotation.setProducts(doctor.getOrderproduct());
        //doctor.setCart(Quotation);
        List<Product> orderProduct=new ArrayList<Product>();
        doctor.setOrderproduct(orderProduct);
        doctorRepository.save(doctor);
        quotationRepository.save(Quotation);
    }*/

    //////////////////////////Doctor////////////////////////////////////
        //speciality
      /*  public List<Speciality> findAllspeDoctor(long id){
            Doctor pro=doctorRepository.findByIdAndStatus(id,1);
            return  pro.getSpecialities();
        }*/

        /*   public doctor adddoctor(doctor doctor) {
       
               return this.doctorRepository.save(doctor);
           }
           public doctor getdoctor(long id) {
               return this.doctorRepository.findById(id).orElseThrow();
           }
           public doctor updatedoctor(long iddoctor, doctor newdoctor) {
               doctor olddoctor = getdoctor(iddoctor);
               if(olddoctor!=null){
                   newdoctor.setId(iddoctor);
       //            System.out.println("success");
                   return this.doctorRepository.save(newdoctor);
               }
       //        System.out.println("failed ");
               return null;
           }*/
///////////////////////////admin////////////////////////////////////////////

        public Doctor getDoctor(String mail){
            return this.doctorRepository.findByEmail(mail);
        }
        public Doctor findByName(String name){
            return this.doctorRepository.findByLastname(name);
        }
        @Override
        public List<Doctor> findAll() {
            List<Doctor>  l= doctorRepository.findAll();
            List<Doctor>  ll=new ArrayList<Doctor>();
            for ( int j=0;j<l.size();j++)
            {   Doctor ss=new Doctor();
                ss.setEmail(l.get(j).getEmail());
                ss.setPhone(l.get(j).getPhone());
              Speciality  s=new Speciality();
              s.setName(l.get(j).getSpeciality().getName());
                ss.setSpeciality(s);
                ss.setFirstname(l.get(j).getFirstname());
                ss.setLastname(l.get(j).getLastname());
                ll.add(ss);
System.out.println("all doc"+ss.getFirstname());
            }
            return ll;
        }

      /*  //delete status -1 in db
        @Override
        public void delete(long doctorId) {
            Doctor doctorInfo = getDoctor(doctorId);
            if (doctorInfo == null) throw new MyException(ResultEnum.Doctor_NOT_EXIST);
            doctorInfo.setStatus(-1);
            doctorRepository.save(doctorInfo);

        }*/




    }
