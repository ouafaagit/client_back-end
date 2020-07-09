package client_back1.demo.service.impl;
import client_back1.demo.entity.*;
import client_back1.demo.enums.ResultEnum;
import client_back1.demo.exception.MyException;
import client_back1.demo.repository.ProductRepository;
import client_back1.demo.repository.ProviderRepository;
import client_back1.demo.service.ImageService;
import client_back1.demo.service.ProviderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@Service
@DependsOn("passwordEncoder")
public class ProviderServiceImpl implements ProviderService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final ProviderRepository providerRepository;
    @Autowired
    ImageService imageService;
   // private final SpecialityRepository specialityRepository;
    private final ProductRepository productRepository;

    public ProviderServiceImpl(ProviderRepository providerRepository, ProductRepository productRepository) {
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
    //////////////////////////////profile////////////////////////////////////////////////
    //edit profile
    public Provider getProfil(long id){
        Provider p=this.providerRepository.findByIdAndStatus(id,1);
        if(p==null){
            return null;
        }
        Set<Speciality> l =new HashSet<>();
        List<Product> ll =new ArrayList<>();
        p.setSpecialities(l);
        System.out.println("profile get"+p.getFirstname()+p.getLastname());
        p.setProduct(ll);
        return p;
    }
    // update provider
    @Override
    public Provider updateProfil(Provider productInfo) {
        if(productInfo.isBlocked()) {
            throw new MyException(ResultEnum.PROVIDER_NOT_EXIST);
        }
        System.out.println("**************updateProfil");
        Provider oldUser = providerRepository.findByIdAndStatus(productInfo.getId(),1);
         oldUser.setLastname(productInfo.getLastname());
         oldUser.setFirstname(productInfo.getFirstname());
         oldUser.setName(productInfo.getName());
        oldUser.setPhone(productInfo.getPhone());
        oldUser.setEmailsociety(productInfo.getEmailsociety());
        oldUser.setEmail(productInfo.getEmail());
        System.out.println("hhproductInfo __"+productInfo.getPassword());
        System.out.println("hholdUser__"+oldUser.getPassword());
     /*  String g=oldUser.getPassword();
        System.out.println("hh"+oldUser.getPassword());
        oldUser.setPassword(g);
        System.out.println("jj"+oldUser.getPassword());*/
      if(productInfo.getPassword()==null||productInfo.getPassword().equals(" ")||productInfo.getPassword().isEmpty()) {
          String    g=oldUser.getPassword();
            System.out.println("compar__"+oldUser.getPassword());
            oldUser.setPassword(g);
            System.out.println("jjcompar__"+oldUser.getPassword());
        }else{
            oldUser.setPassword(passwordEncoder.encode(productInfo.getPassword()));}
        providerRepository.save(oldUser);
        return productInfo ;

    }
    //////////////////////////provider////////////////////////////////////
    //speciality
    public List<Speciality> findAllspeprovider(long id){
        Provider pro=providerRepository.findByIdAndStatus(id,1);
        List<Speciality>  ll=new ArrayList<Speciality>();
            Iterator<Speciality> it =pro.getSpecialities().iterator();
            while (it.hasNext())
            {
                Speciality s=it.next();
            Speciality ss=new Speciality();
            ss.setName(s.getName());
            ss.setId(s.getId());
            ss.setPriceSpeciality(s.getPriceSpeciality());
            ll.add(ss);
        }
        return ll;
    }


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
    @Override
    public Provider updatelistsp( String usetr, String userr) throws JsonProcessingException {
        Provider pr = new ObjectMapper().readValue(usetr,Provider.class);
       // double prix = new ObjectMapper().readValue(usetr,double.class);
      //  long id = new ObjectMapper().readValue(idd,long.class);
        Provider provider=this.providerRepository.findByIdAndStatus(pr.getId(),1);
        if(provider==null){
            return null;
        }
        provider.setPricesubscription(pr.getPricesubscription());
        //Provider user  = new Gson().fromJson(userr, Provider.class);
        // List<Speciality> sp=new ObjectMapper().readValue(user.getSpecialities(),Provider.class);
        //  List<Speciality> sp= new ObjectMapper().readValue(userr,Provider.class);
        ObjectMapper mapper = new ObjectMapper();
        List<Speciality> asList=new ArrayList<Speciality>();
        asList = mapper.readValue(
                userr, new TypeReference<List<Speciality>>() { });
        //   System.out.println("****size"+asList.size());
        // assertThat(asList.get(0), instanceOf(MyDto.class));
        //   Provider provider=user;
        //  provider.setSpecialities(new ArrayList<Speciality>());
        // System.out.println("****"+user.getSpecialities().size());
        // for(int i=0;i<user.getSpecialities().size();i++) {
        for(int i=0;i<asList.size();i++) {
            //    System.out.println("****"+user.getSpecialities().get(i).toString());
            // Speciality s=specialityRepository.getOne(asList.get(i).getId());
            Speciality s=asList.get(i);
            provider.getSpecialities().add(s);
            //just added
            s.getProviders().add(provider);
            //    System.out.println("aaaaa"+provider.getSpecialities().get(i).toString());
            //   s.getProviders().add(provider);
        }
        provider=providerRepository.save(provider);
        Provider p=new Provider();
        p.setId(provider.getId());
        p.setFirstname(provider.getFirstname());
        p.setRole(provider.getRole());
        return p;
    }

//Quotation
 /*   @Override
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
    public Provider getProvider(String mail){
        return this.providerRepository.findByEmailAndStatusNot(mail,-1);
    }
    public Provider findByName(String name){
        return this.providerRepository.findByNameAndStatusNot(name,-1);
    }
    /*   public Product addProduct(Product product) {

           return this.productRepository.save(product);
       }
       public Product getProduct(long id) {
           return this.productRepository.findById(id).orElseThrow();
       }
       public Product updateProduct(long idProduct, Product newProduct) {
           Product oldProduct = getProduct(idProduct);
           if(oldProduct!=null){
               newProduct.setId(idProduct);
   //            System.out.println("success");
               return this.productRepository.save(newProduct);
           }
   //        System.out.println("failed ");
           return null;
       }*/







}
