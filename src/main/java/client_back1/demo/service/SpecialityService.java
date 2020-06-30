package client_back1.demo.service;

import client_back1.demo.entity.Speciality;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
public interface SpecialityService {
    //all specialities
    Page<Speciality> findAllnblk(Pageable pageable);

    //////////////////user///////////////////
    Speciality findOne(long Id);
    Speciality findName(String specialityname);
    // All sepecialitys
    List<Speciality> findAllsp();
////////////////////:admin/////////////////

    Speciality getspeciality(long productId);


    /*  //by Name noot blocked
      public Speciality findBySpecialityName(String name) {
          Speciality   res=  specialityRepositor.findByNameAndStatusTrue(name);
          if(res == null) throw new MyException(ResultEnum.CATEGORY_NOT_FOUND);
          return res;
      }*/
  ////////////////////:provider////////////////////:
    Speciality getspecialitypr(long idprov, long id);

    //all specialities
    List<Speciality> findAll();

    Speciality update(Speciality speciality);

    //getspeciality detail
    Speciality getspdetail(long productCategory);

    //getspeciality for update
    Speciality getspdetailUp(long productCategory);

    Speciality save(Speciality speciality);

    //offspeciality
        @Transactional
        Speciality offSpeciality(long productId);

    // onSpeciality  admin  deblock
    @Transactional
    Speciality onSpeciality(long productId);

    void delete(long Id);


    List<Speciality> shooselist(long id);

    //delete -provider
    void deletespeciality(long id, long speciality);

    List<Speciality> findtreesp();
}
