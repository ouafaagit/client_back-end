package client_back1.demo.service;

import client_back1.demo.entity.Product;
import client_back1.demo.entity.Provider;

import client_back1.demo.entity.Speciality;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@Service
public interface AdminService {

    List<Provider> findAllnotblocked();

    List<Provider> findAllblocked();

    Provider getProviderAd(long id);

    ////get specialities providers
    List<Speciality> speprovidAd(long id);

    //for  provider list
    List<Product> ProductprovidAd(long idprovider);

    ////////////////admin/////////////////////
    // All providers for admin
    List<Provider> findAll();
    //Product addProduct(long id, Product product);
    //add
    Provider save(Provider productInfo);
    void delete(long Id);
    List<Provider> newproviders();

    //bolck provider
    void offprovider(long Id);
    //deblock provider
    void onProvider(long Id);
     Provider getProvider(long id);

    Provider findOne(String email);

    Provider findlogin(String username);



}
