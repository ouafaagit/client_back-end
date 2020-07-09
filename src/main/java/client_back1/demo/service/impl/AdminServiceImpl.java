package client_back1.demo.service.impl;
import client_back1.demo.entity.*;
import client_back1.demo.enums.ResultEnum;
import client_back1.demo.exception.MyException;
import client_back1.demo.repository.ProductRepository;
import client_back1.demo.repository.ProviderRepository;
import client_back1.demo.service.AdminService;
import client_back1.demo.service.ImageService;
import client_back1.demo.service.ProviderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@Service
@DependsOn("passwordEncoder")
public class AdminServiceImpl implements AdminService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final ProviderRepository providerRepository;
    @Autowired
    ImageService imageService;
   // private final SpecialityRepository specialityRepository;
    private final ProductRepository productRepository;

    public AdminServiceImpl(ProviderRepository providerRepository, ProductRepository productRepository) {
        this.providerRepository = providerRepository;
       // this.specialityRepository = specialityRepository;
        this.productRepository = productRepository;
      //this.initDB();
//        this.updateDB();
//        System.out.println(this.afficher(this.getAdmin()));
    }

    private void initDB() {
        Provider provider=new Provider();
       provider.setFirstname("amal");
        provider.setLastname("laloo");
        provider.setRole("ROLE_PROVIDER");
        provider.setStatus(1);
        provider.setActive(true);
        provider.setEmail("a@gmail.com");
        provider.setEmailsociety("a@gmail.com");
        provider.setName("a@gmail.com");
        provider.setPricesubscription(144444);

      //  provider.setPassword(this.passwordEncoder.encode("aaaaaaa"));
        Provider p=this.providerRepository.save(provider);
      //  this.addProvider(provider);
       /* for(int i=0;i<10;i++) {
            Product product = new Product();
            product.setName("Product " + (i + 1) + " Name ");
            product.setDescription("Product " + (i + 1) + " Description ");
            product.setCatalogue("Product " + (i + 1) + " Catalogue ");
            product.setMarque("Product " + (i + 1) + " Marque ");
            this.addProduct(product);
        }*/
    }

    @Override
    public Provider findOne(String email) {
        return providerRepository.findByEmail(email);
    }
    @Override
    public Provider findlogin(String email) {
        return providerRepository.findByEmailAndStatus(email,1);
    }

///////////////////////////admin////////////////////////////////////////////
    @Override
    public Provider getProvider(long id){
    return this.providerRepository.findByIdAndStatusNot(id,-1);
}
    @Override
    public Provider getProviderAd(long id){
    Provider providers= this.providerRepository.findByIdAndStatusNot(id,-1);
    Provider provider=new Provider();
    System.out.println("getProviderAd"+id);
        provider.setLastname(providers.getLastname());
        provider.setId(providers.getId());
        provider.setFirstname(providers.getFirstname());
        provider.setName(providers.getName());
        provider.setPhone(providers.getPhone());
        provider.setEmailsociety(providers.getEmailsociety());
        provider.setEmail(providers.getEmail());
        provider.setPricesubscription(providers.getPricesubscription());
        provider.setStatus(providers.getStatus());
return   provider;
}
////get specialities providers
@Override
public List<Speciality> speprovidAd(long id){
    Provider pro=providerRepository.findByIdAndStatusNot(id,-1);
    List<Speciality>  ll=new ArrayList<Speciality>();
 /*   if(pro.getSpecialities()!=null) {
        List<Speciality> l = pro.getSpecialities();
        for (int j = 0; j < l.size(); j++) {
            Speciality ss = new Speciality();
            ss.setName(l.get(j).getName());
            ss.setId(l.get(j).getId());
            ss.setPriceSpeciality(l.get(j).getPriceSpeciality());
            ll.add(ss);
            // System.out.println("$$$$$"+ll.get(j).getName());
        }
    }*/
    Iterator<Speciality> it =pro.getSpecialities().iterator();
    while (it.hasNext())
    {
        Speciality s=it.next();
        Speciality ss=new Speciality();
        ss.setName(s.getName());
        ss.setId(s.getId());
        ss.setPriceSpeciality(s.getPriceSpeciality());
        ll.add(ss);
        // System.out.println("$$$$$"+ll.get(j).getName());

    }
    return ll;
}
    //for  provider list
    @Override
    public Page<Product> ProductprovidAd(long idprovider,Pageable pageable) {
        Page<Product> list= productRepository.findAllByProvider_Id(idprovider,pageable);
        List<Product> l=new ArrayList<>();
        Iterator<Product> it =list.iterator();
        while (it.hasNext())
        {            Product pr=it.next();
            Product product=new Product();
                Speciality s= new Speciality();
                s.setName(pr.getSpeciality().getName());
                s.setId(pr.getSpeciality().getId());
                product.setSpeciality(s);
                product.setId(pr.getId());
                product.setBlocked(pr.isBlocked());
                product.setName(pr.getName());
                //product.setDescription(list.get(i).getDescription());
                product.setReference(pr.getReference());
                // product.setCatalogue(list.get(i).getCatalogue());
                product.setNombreVue(pr.getNombreVue());
                product.setMarque(pr.getMarque());
                ImageModel  img= new ImageModel();
                if(pr.getPr_images().isEmpty()) {}
                else{
                    if (pr.getPr_images().get(0)!=null) {
                        long name = pr.getPr_images().get(0).getId();
                        img = imageService.findById(name);
                        ImageModel imge = new ImageModel();
                        imge.setName(img.getName());
                        imge.setId(img.getId());
                        imge.setPicByte(img.getPicByte());
                        imge.setType(img.getType());
                        System.out.println("allproductprov" + imge.getName());
               /*img.setName(name);
               img.setId(list.get(i).getPr_images().get(1).getId());
               img.setPicByte(list.get(i).getPr_images().get(1).getPicByte());
               img.setType(list.get(i).getPr_images().get(1).getType());
               System.out.println("gg"+img.getName());*/
                        product.setPr_image(imge);
                    }
                }

                l.add(product);
            }

        return new PageImpl<>(l, pageable, l.size()) ;

    }

    public Provider getProvider(String mail){
        return this.providerRepository.findByEmailAndStatusNot(mail,-1);
    }
    public Provider findByName(String name){
        return this.providerRepository.findByNameAndStatusNot(name,-1);
    }
    @Override
    public Page<Provider> findAll(Pageable pageable) {
        Page<Provider>  l=providerRepository.findAllByStatusNot(-1,pageable);
        List<Provider>  ll=new ArrayList<Provider>();
        Iterator<Provider> it =l.iterator();
        while (it.hasNext())
        {
            Provider provider=it.next();
            Provider ss=new Provider();
            ss.setName(provider.getName());
            ss.setStatus(provider.getStatus());
            //System.out.println("provider :"+ss.getStatus()+ " "+l.get(j).getStatus());
            ss.setEmail(provider.getEmail());
            ss.setEmailsociety(provider.getEmailsociety());
            ss.setPhone(provider.getPhone());
            ss.setId(provider.getId());
            ss.setFirstname(provider.getFirstname());
            ss.setLastname(provider.getLastname());

          //  System.out.println("provider :"+ss.getLastname()+ " "+l.get(j).getLastname());
            ss.setPricesubscription(provider.getPricesubscription());
            ll.add(ss);
            // System.out.println("$$$$$"+ll.get(j).getName());

        }
        return  new PageImpl<>(ll, pageable, ll.size());
    }

    @Override
    public  Page<Provider> findAllnotblocked(Pageable pageable) {
        Page<Provider>  l=providerRepository.findAllByStatus(1,pageable);
        List<Provider>  ll=new ArrayList<Provider>();
        Iterator<Provider> it =l.iterator();
        while (it.hasNext())
        {
            Provider provider=it.next();
            Provider ss=new Provider();
            ss.setName(provider.getName());
            ss.setStatus(provider.getStatus());
            //System.out.println("provider :"+ss.getStatus()+ " "+l.get(j).getStatus());
            ss.setEmail(provider.getEmail());
            ss.setEmailsociety(provider.getEmailsociety());
            ss.setPhone(provider.getPhone());
            ss.setId(provider.getId());
            ss.setFirstname(provider.getFirstname());
            ss.setLastname(provider.getLastname());

            //  System.out.println("provider :"+ss.getLastname()+ " "+l.get(j).getLastname());
            ss.setPricesubscription(provider.getPricesubscription());
            ll.add(ss);
            // System.out.println("$$$$$"+ll.get(j).getName());

        }
        return  new PageImpl<>(ll, pageable, ll.size());
    }
    @Override
    public Page<Provider> findAllblocked(Pageable pageable) {
        Page<Provider>  l=providerRepository.findAllByStatus(0,pageable);
        List<Provider>  ll=new ArrayList<Provider>();
        Iterator<Provider> it =l.iterator();
        while (it.hasNext())
        {
            Provider provider=it.next();
            Provider ss=new Provider();
            ss.setName(provider.getName());
            ss.setStatus(provider.getStatus());
            //System.out.println("provider :"+ss.getStatus()+ " "+l.get(j).getStatus());
            ss.setEmail(provider.getEmail());
            ss.setEmailsociety(provider.getEmailsociety());
            ss.setPhone(provider.getPhone());
            ss.setId(provider.getId());
            ss.setFirstname(provider.getFirstname());
            ss.setLastname(provider.getLastname());

            //  System.out.println("provider :"+ss.getLastname()+ " "+l.get(j).getLastname());
            ss.setPricesubscription(provider.getPricesubscription());
            ll.add(ss);
            // System.out.println("$$$$$"+ll.get(j).getName());

        }
        return  new PageImpl<>(ll, pageable, ll.size());
    }
    @Override
    public Page<Provider> newproviders(Pageable pageable) {
        Page<Provider>  l=providerRepository.findAllByStatus(2,pageable);
        List<Provider>  ll=new ArrayList<Provider>();
        Iterator<Provider> it =l.iterator();
        while (it.hasNext())
        {
            Provider provider=it.next();
            Provider ss=new Provider();
            ss.setName(provider.getName());
            ss.setStatus(provider.getStatus());
            //System.out.println("provider :"+ss.getStatus()+ " "+l.get(j).getStatus());
            ss.setEmail(provider.getEmail());
            ss.setEmailsociety(provider.getEmailsociety());
            ss.setPhone(provider.getPhone());
            ss.setId(provider.getId());
            ss.setFirstname(provider.getFirstname());
            ss.setLastname(provider.getLastname());

            //  System.out.println("provider :"+ss.getLastname()+ " "+l.get(j).getLastname());
            ss.setPricesubscription(provider.getPricesubscription());
            ll.add(ss);
            // System.out.println("$$$$$"+ll.get(j).getName());

        }
        return  new PageImpl<>(ll, pageable, ll.size());
    }

    //add
    public Provider addProvider(Provider product) {
        return this.providerRepository.save(product);
    }
    //admin block
    @Override
    public void offprovider(long Id) {
        Provider productInfo = this.getProvider(Id);
        if (productInfo == null ) throw new MyException(ResultEnum.PROVIDER_NOT_EXIST);
//true -1 1
        if (productInfo.isBlocked()) {
            throw new MyException(ResultEnum.PROVIDER_STATUS_ERROR);
        }
//0 for block -1 for drop ,1 good
        System.out.println("offprovider 0"+Id);
        productInfo.setStatus(0);
        providerRepository.save(productInfo);
    }
    //admin  deblock
    @Override

    public void onProvider(long productId) {
        Provider productInfo = getProvider(productId);
        if (productInfo == null) throw new MyException(ResultEnum.PROVIDER_NOT_EXIST);
        if (productInfo.getStatus()==0 ||productInfo.getStatus()==2){
            System.out.println("onProvider 1"+productId +productInfo.getStatus());
        productInfo.setStatus(1);
        }
         providerRepository.save(productInfo);
    }

//delete provider status -1 in db
    @Override
    public void delete(long productId) {
        Provider productInfo = getProvider(productId);
        if (productInfo == null) throw new MyException(ResultEnum.PROVIDER_NOT_EXIST);
        productInfo.setStatus(-1);
        System.out.println("delete set status"+productInfo.getStatus());
        providerRepository.save(productInfo);

    }
    //////////////////////////////:
    //add
    @Override
    public Provider save(Provider productInfo) {
        return providerRepository.save(productInfo);
    }
/////quotation////////////
//Quotation
/*@Override
public List<Quotation> allQuotation(long id) {
    Provider pro=providerRepository.findByIdAndStatus(id,1);
    List<Product> list=new ArrayList<Product>();
    Product l=new Product();
    list.addAll(pro.getProduct());
    Iterator<Product> itr = list.iterator();
    List<Quotation> s=new ArrayList<Quotation>();
    while(itr.hasNext()){
        l=itr.next();
        s.addAll(l.getQuotations());}
    //res.sort(Comparator.comparing(ProductCategory::getCategoryType));
    s.sort(Comparator.comparing(Quotation::getId));
    return s;
}*/

}
