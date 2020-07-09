package client_back1.demo.service.impl;
import client_back1.demo.entity.*;
import client_back1.demo.repository.DoctorRepository;
import client_back1.demo.repository.ProductRepository;
import client_back1.demo.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@Service
@DependsOn("passwordEncoder")
public class DoctorServiceImpl implements DoctorService {
    private final PasswordEncoder passwordEncoder;
    private final DoctorRepository doctorRepository;
    @Autowired
    ProductRepository productRepository;


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

        ////merge wislist////
        @Override
        @Transactional
        public List<ProductInOrder> mergeLocalwish(Collection<ProductInOrder> productInOrders, String userr) {
            Doctor user = doctorRepository.findByEmail(userr);
            System.out.println(" ************user"+ user.getEmail());
            Iterator<ProductInOrder> it =productInOrders.iterator();
            while (it.hasNext())
            {
                Product product=productRepository.findByIdAndBlockedIsFalse(it.next().getId());
                user.getWishlist().add(product);
              //  user.getWishlist().
            }
          user=  doctorRepository.save(user);
            Iterator<Product> itt =user.getWishlist().iterator();
            List<ProductInOrder>productInOrd=new ArrayList<>();
            while (itt.hasNext())
            {
                ProductInOrder productInOrder=new ProductInOrder(itt.next(),1);
                productInOrd.add(productInOrder);
                //  user.getWishlist().
            }
return productInOrd;
        }

    @Override
    //@Transactional
    public void deleteitem(String itemId, Doctor user) {
        var op = user.getWishlist().stream().filter(e -> itemId.equals(" "+e.getId())).findFirst();
        op.ifPresent(product -> {
            System.out.println("&&&&&&&delete item");
            //product.getWishdoc().remove(user);
            System.out.println("uuuOP"+op.get().getName());
            user.getWishlist().remove(op.get());
            doctorRepository.save(user);
            op.get().getWishdoc().remove(user);
            //   productInOrderRepository.deleteById(productInOrder.getId());

            productRepository.save(op.get());
        });
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
        public Page<Doctor> findAll(Pageable pageable) {
            Page<Doctor>  l= doctorRepository.findAll(pageable);
            List<Doctor>  ll=new ArrayList<Doctor>();
            Iterator<Doctor> it =l.iterator();
            while (it.hasNext())
            {
                Doctor doctor=it.next();
                                Doctor ss=new Doctor();
                ss.setEmail(doctor.getEmail());
                ss.setPhone(doctor.getPhone());
              Speciality  s=new Speciality();
              s.setName(doctor.getSpeciality().getName());
                ss.setSpeciality(s);
                ss.setFirstname(doctor.getFirstname());
                ss.setLastname(doctor.getLastname());
                ll.add(ss);
System.out.println("all doc"+ss.getFirstname());
            }
            return new PageImpl<>(ll, pageable, ll.size());
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
