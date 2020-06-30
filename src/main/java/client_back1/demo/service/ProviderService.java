package client_back1.demo.service;

import client_back1.demo.entity.Provider;
import client_back1.demo.entity.Speciality;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@Service
public interface ProviderService {


    List<Speciality> findAllspeprovider(long id);

//////////////////////provider///////






    //add specialitys
  /*  @Override
    public Provider updatelistsp(long id, Speciality sp)  {

        Provider provider=this.providerRepository.findByIdAndStatus(id,1);
        if(provider==null){
            return null;
        }
        provider.setPricesubscription(provider.getPricesubscription()+sp.getPriceSpeciality());
            provider.getSpecialities().add(sp);
        provider=providerRepository.save(provider);
        Provider p=new Provider();
        p.setId(provider.getId());
        p.setFirstname(provider.getFirstname());
        p.setRole(provider.getRole());
        return p;
    }*/
   //add specialitys
    Provider updatelistsp(String usetr, String userr) throws JsonProcessingException;


    Provider updateProfil(Provider provider);
    //Product addProduct(long id, Product product);

    Provider findOne(String email);

    Provider getProfil(long id);

    Provider findlogin(String username);


    //  Provider getProviderbyEmail(String mail);
 //  Provider addProvider(Provider provider);
  //  Product updateProduct(long idProduct, Product newProduct);
    // All providers not blocked
    // Page<Provider> findUpAll(Pageable pageable);

}
