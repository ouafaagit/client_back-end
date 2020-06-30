package client_back1.demo.repository;
import client_back1.demo.entity.Complaint;
import client_back1.demo.entity.Product;
import client_back1.demo.entity.Provider;
import client_back1.demo.entity.Speciality;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    ////////////////for admin ////////////////////
    //Optional<Product> findByName(String name);
    Product findById(long productId);
    // product all for admin
    List<Product> findAll();

    //for provider pas utilise
  //  Product findByIdAndBlockedIsFalseAndProvider_IdAndProvider_Status(long productId,long ids, int n);

    //////////////////////user/////////////////////////////
    //getproduct id
    Product findByIdAndBlockedIsFalse(long productId);
    //List<Product> findAllByBlockedIsFalseAndSpeciality_NameAndOrderByOrderDesc(String s);
    List<Product> findAllByBlockedIsFalseAndSpeciality_NameAndNombreVueLessThan(String sn ,double i);
    List<Product> findAllByBlockedIsFalseAndSpeciality_Name(String sn);
    List<Product> findAllByBlockedIsFalseAndSpeciality_Id(long sn);
    List<Product> findAllByBlockedIsFalseAndSpeciality_NameAndNombreVueGreaterThan(String s,double i);

    List<Product> findAllByBlockedIsFalseAndSpeciality_NameNotAndNombreVueLessThan(String s,double i);
    List<Product> findAllByBlockedIsFalseAndNombreVueNotNullAndSpeciality_NameNot(String s);
    List<Product> findAllByBlockedIsFalseAndSpeciality_NameNot(String s);
   // List<Product> findAllByBlockedIsFalseAndOrderByOrderNombreVueDesc();
   List<Product> findAllByBlockedFalseAndAndNombreVueGreaterThanEqual(double i);
    // All products on sale
   // Page<Product> findAllByBlockedIsFalsedOrderByIdIdAsc( Pageable pageable);
    Page<Product> findAllByBlockedFalse( Pageable pageable);
    List<Product> findAllByBlockedFalse();
    List<Product> findAllByBlockedFalseAndAndNombreVueLessThanEqual(double i);
    // products by speciality name
    Page<Product> findAllByBlockedIsFalseAndSpecialityName( String name,Pageable pageable);

    ///////////////  products of provider//////////////
    List<Product> findAllByBlockedIsFalseAndProvider_Id(long provider);
    List<Product> findAllByProvider_Id(long provider);
    List<Product> findAllByBlockedIsFalseAndProvider_IdAndSpecialityId(long provider,long id);
    //product by speciality user or provider
  //  Page<Product>  findAllBySpeciality_NameAndBlockedIsFalse(String speciality, Pageable pageable);
    //product by speciality for provider
  //  Page<Product>  findAllBySpeciality_NameAndBlockedIsFalseAndProvider_Id(String speciality,long id, Pageable pageable);



// for searching
}
