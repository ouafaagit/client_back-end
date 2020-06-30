package client_back1.demo.repository;

import client_back1.demo.entity.Product;
import client_back1.demo.entity.Provider;
import client_back1.demo.entity.Speciality;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpecialityRepository extends JpaRepository<Speciality,Long> {
   // Page<Speciality> findAllBy(long id);
    Speciality findByNameAndStatusTrue(String name);
    Speciality findByIdAndStatusTrue(long Id);

    ////////user/////////////////
    List<Speciality> findAllByStatusTrue();
   // List<Speciality> findAllByStatusTrueAndProvidersIdNot(long Id);
    List<Speciality> findAllByStatusTrueAndProvidersIdNot(long Id);
    List<Speciality> findAllByProvidersIsNotAndStatusTrue(Provider p);
    List<Speciality> findAll();
   // List<Speciality> findAllByStatusAndOrderByIdAsc(Boolean t);
///////provider///////
   Speciality findByIdAndStatusTrueAndProvidersId(long Id,long provider);
    Page<Speciality> findAllByStatusTrueOrderByIdAsc(Pageable pageable);
//price speciality
 //   Page<Speciality> findAllByPriceSpeciality();
  //
    //Speciality findByDoctorsName(String name);
}
