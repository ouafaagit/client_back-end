package client_back1.demo.repository;

import client_back1.demo.entity.Doctor;
import client_back1.demo.entity.Provider;
import client_back1.demo.entity.Speciality;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProviderRepository extends JpaRepository<Provider,Long> {
   // Provider findFirstByAdminEquals(boolean admin) ;
   // Provider findFirstByEmailEquals(String mail) ;
   Provider findByEmail(String mail);
    Provider findByEmailAndStatus(String mail, int i);
/////////////////provider////////////////////
    Provider findByIdAndStatus(long id, int i);

    /////////////////admin////////////////
    int n=-1;
    Provider findByNameAndStatusNot(String name, int n);
    Provider  findByIdAndStatusNot(long providerId, int n);
    Provider findByEmailAndStatusNot(String email,int n);
    //all provider not -1
    List<Provider> findAllByStatusNot(int i);
    List<Provider> findAllByStatus(int i);
    List<Provider> findAllByStatusGreaterThanEqual(int i);
    /////////////////////////////////////////////
//Page<Product> getAllByProduct();


    //all specialities
  //  Page<Speciality> findAllById(long id);

    //find own speciality
 //   Speciality findByidAndSpecialitiesName(long id,String name);

    //find provider by name or socity email
 //   Page<Provider> findAllByNumero_telOrNameOrEmailAndEmailsociety(String r);




    // Page<Product> findByProduct_(long providerId);
    // specialitys for provider
    //Page<Speciality> findAllBy(long id);
    // product for provider
   // Page<Product> findAllByProduct_(long id);
}
