package client_back1.demo.service;

import client_back1.demo.entity.Product;
import client_back1.demo.entity.Provider;

import client_back1.demo.entity.Speciality;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@Service
public interface AdminService {

    Page<Provider> findAllnotblocked(Pageable pageable);

    Page<Provider> findAllblocked(Pageable pageable);

    Provider getProviderAd(long id);

    ////get specialities providers
    List<Speciality> speprovidAd(long id);

    //for  provider list
    Page<Product> ProductprovidAd(long idprovider,Pageable pageable);

    ////////////////admin/////////////////////
    // All providers for admin
    Page<Provider> findAll(Pageable pageable);
    //Product addProduct(long id, Product product);
    //add
    Provider save(Provider productInfo);
    void delete(long Id);
    Page<Provider> newproviders(Pageable pageable);

    //bolck provider
    void offprovider(long Id);
    //deblock provider
    void onProvider(long Id);
     Provider getProvider(long id);

    Provider findOne(String email);

    Provider findlogin(String username);



}
