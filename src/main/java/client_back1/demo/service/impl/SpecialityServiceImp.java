package client_back1.demo.service.impl;
import client_back1.demo.entity.Doctor;
import client_back1.demo.entity.Product;
import client_back1.demo.entity.Provider;
import client_back1.demo.entity.Speciality;
import client_back1.demo.enums.ResultEnum;
import client_back1.demo.exception.MyException;
import client_back1.demo.repository.ProductRepository;
import client_back1.demo.repository.ProviderRepository;
import client_back1.demo.repository.SpecialityRepository;
import client_back1.demo.service.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@Service
public class SpecialityServiceImp  implements SpecialityService {
   @Autowired
    private final SpecialityRepository specialityRepositor;
    @Autowired
    private final ProviderRepository providerRepository;
    @Autowired
   ProductRepository productRepository;

    public SpecialityServiceImp(SpecialityRepository specialityRepositor, ProviderRepository providerRepository) {
        this.specialityRepositor = specialityRepositor;
        this.providerRepository = providerRepository;
       //this.initDB();
    }

    private void initDB() {
        Speciality speciality1= new Speciality();

        Speciality speciality= new Speciality();
        Speciality speciality2= new Speciality();
        speciality1.setName("gériatrie");
      //  Provider p=providerRepository.getOne((long) 1);
        speciality1.setProvider(providerRepository.getOne((long) 1));
        speciality1.setPriceSpeciality(54656);
        speciality1=this.specialityRepositor.save(speciality1);
       // p.setSpecialitie(speciality1);
  //   this.providerRepository.save(p);
      speciality2.setName("chirurgie ");
        speciality2.setPriceSpeciality(400);
     speciality2.setProvider(providerRepository.getOne((long) 1));
        this.specialityRepositor.save(speciality2);
        speciality.setName("La pédiatrie ");
       speciality.setPriceSpeciality(500);
       speciality.setProvider(providerRepository.getOne((long) 1));
      this.specialityRepositor.save(speciality);

    }
//////provider_shoose list /////
//for home
public List<Speciality> shooselist(long id) {
        Provider p=new Provider(id);
    List<Speciality>  l=specialityRepositor.findAllByStatusTrueAndProvidersIdNot(id);
    List<Speciality>  ll=new ArrayList<Speciality>();
    for ( int j=0;j<l.size();j++)
    {   Speciality ss=new Speciality();
        ss.setName(l.get(j).getName());
        ss.setId(l.get(j).getId());
        ss.setPriceSpeciality(l.get(j).getPriceSpeciality());
        ll.add(ss);
        // System.out.println("$$$$$"+ll.get(j).getName());

    }
    return ll;
     /*    Iterator<Speciality> itr = l.iterator();
        //Speciality sl= new Speciality();
       Speciality sl;
       int i=0;
      while(itr.hasNext()){
          Speciality ss=new Speciality();
           sl=itr.next();
           ss.setName(sl.getName());
           ss.setId(sl.getId());
           // ll.add(i,ss);
            ll.add(ss);
            System.out.println(">>>>"+ll.get(i).getName());
            i++;
      }*/

}
    ////////////////////////////user-provider/////////////////
    //for home
    public List<Speciality> findAllsp() {
       // List<Speciality> l=new ArrayList<Speciality>( specialityRepositor.findAllByStatusTrueOrderByIdAsc());
       /* List<String> ll=new ArrayList<String>();
        Iterator<Speciality> itr = l.iterator();
        Speciality s=new Speciality();
        while(itr.hasNext()){
            s=itr.next();
            ll.add(s.getName());}
        return ll;*/
      // System.out.println("serviceggg");
/*       List<Speciality> l=new ArrayList<>();
       l.add(new Speciality("medicale",400)) ;
       l.add(new Speciality("chirergerie",200)) ;
       l.add(new Speciality("med",500)) ;
         return  l;
       */
        List<Speciality>  l=specialityRepositor.findAllByStatusTrue();
        List<Speciality>  ll=new ArrayList<Speciality>();
        for ( int j=0;j<l.size();j++)
        {   Speciality ss=new Speciality();
            ss.setName(l.get(j).getName());
            ss.setId(l.get(j).getId());
            ss.setPriceSpeciality(l.get(j).getPriceSpeciality());
            ll.add(ss);
           // System.out.println("Speciality "+ll.get(j).getName());

        }
        return ll;
     /*    Iterator<Speciality> itr = l.iterator();
        //Speciality sl= new Speciality();
       Speciality sl;
       int i=0;
      while(itr.hasNext()){
          Speciality ss=new Speciality();
           sl=itr.next();
           ss.setName(sl.getName());
           ss.setId(sl.getId());
           // ll.add(i,ss);
            ll.add(ss);
            System.out.println(">>>>"+ll.get(i).getName());
            i++;
      }*/

    }

    //for home
    public List<Speciality> findtreesp() {

        List<Speciality>  l=specialityRepositor.findAllByStatusTrue();
        List<Speciality>  ll=new ArrayList<Speciality>();
        for ( int j=0;j<l.size();j++)
        {   Speciality ss=new Speciality();
            ss.setName(l.get(j).getName());
            ss.setId(l.get(j).getId());
            ss.setPriceSpeciality(l.get(j).getPriceSpeciality());
            ll.add(ss);
            System.out.println("Speciality "+ll.get(j).getName());

        }
        return ll;


    }
    //all specialities
    @Override
    public Page<Speciality> findAllnblk(Pageable pageable) {
        return specialityRepositor.findAllByStatusTrueOrderByIdAsc(pageable);
    }
    @Override
    public Speciality findOne(long Id) {
        Speciality   res=  specialityRepositor.findByIdAndStatusTrue(Id);
       // if(res == null) throw new MyException(ResultEnum.CATEGORY_NOT_FOUND);
        return res;
    }

    public Speciality findName(String Id) {
        Speciality   res=  specialityRepositor.findByNameAndStatusTrue(Id);
        if(res == null) throw new MyException(ResultEnum.CATEGORY_NOT_FOUND);
        return res;
    }
  /*  //by Name noot blocked
    public Speciality findBySpecialityName(String name) {
        Speciality   res=  specialityRepositor.findByNameAndStatusTrue(name);
        if(res == null) throw new MyException(ResultEnum.CATEGORY_NOT_FOUND);
        return res;
    }*/
////////////////////:provider////////////////////:
  @Override
    public Speciality getspecialitypr(long idprov, long id) {

        Speciality res=specialityRepositor.findByIdAndStatusTrue(id);
        if(res == null) throw new MyException(ResultEnum.CATEGORY_NOT_FOUND);
        List<Product> products=productRepository.findAllByBlockedIsFalseAndProvider_IdAndSpecialityId(idprov,id);
        System.out.println("speciality"+res.getName());
        Speciality speciality=new Speciality();
        speciality.setId(res.getId());
        speciality.setPriceSpeciality(res.getPriceSpeciality());
        speciality.setName(res.getName());
        for(int i=0;i<products.size();i++){
            Product product=new Product();
            product.setName(products.get(i).getName());
            product.setDescription(products.get(i).getDescription());
            product.setReference(products.get(i).getReference());
            product.setMarque(products.get(i).getMarque());
            product.setNombreVue(products.get(i).getNombreVue());
            product.setNombrewish(products.get(i).getNombrewish());
            speciality.getProducts().add(product);
        }
        return speciality;
    }
    ////////////////////////////admin//////////

//all specialities
    @Override
    public List<Speciality> findAll() {
        List<Speciality>  l=specialityRepositor.findAll();
        List<Speciality>  ll=new ArrayList<Speciality>();
        for ( int j=0;j<l.size();j++)
        {   Speciality ss=new Speciality();
            ss.setName(l.get(j).getName());
            ss.setStatus(l.get(j).isStatus());
            ss.setId(l.get(j).getId());
            ss.setPriceSpeciality(l.get(j).getPriceSpeciality());
            ll.add(ss);
            // System.out.println("$$$$$"+ll.get(j).getName());

        }
        return ll;
    }
    //update
    @Override
    public Speciality update(Speciality speciality)
    {
        return specialityRepositor.save(speciality);
    }

    //getspeciality detail
    @Override
    public Speciality getspdetail(long productCategory) {

        Speciality res= specialityRepositor.getOne(productCategory);
        Speciality speciality=new Speciality();

        speciality.setId(res.getId());
        speciality.setPriceSpeciality(res.getPriceSpeciality());
        speciality.setName(res.getName());
        speciality.setStatus(res.isStatus());
        System.out.println("getspdetail"+productCategory);
        for(int i=0;i<res.getProducts().size();i++){
            Product product=new Product();
            product.setName(res.getProducts().get(i).getName());
            product.setDescription(res.getProducts().get(i).getDescription());
            product.setReference(res.getProducts().get(i).getReference());
            product.setMarque(res.getProducts().get(i).getMarque());
            product.setNombreVue(res.getProducts().get(i).getNombreVue());
            product.setNombrewish(res.getProducts().get(i).getNombrewish());
            speciality.setProduct(product);
        }
        for(int i=0;i<res.getProviders().size();i++){
            Provider provider=new Provider();
            provider.setFirstname(res.getProviders().get(i).getFirstname());
            provider.setLastname(res.getProviders().get(i).getLastname());
            provider.setPricesubscription(res.getProviders().get(i).getPricesubscription());
            provider.setEmail(res.getProviders().get(i).getEmail());
            provider.setPhone(res.getProviders().get(i).getPhone());
            provider.setStatus(res.getProviders().get(i).getStatus());
            speciality.setProvider(provider);
        }
        for(int i=0;i<res.getDoctors().size();i++){
            Doctor provider=new Doctor();
            provider.setFirstname(res.getDoctors().get(i).getFirstname());
            provider.setLastname(res.getDoctors().get(i).getLastname());
            provider.setEmail(res.getDoctors().get(i).getEmail());
            provider.setPhone(res.getDoctors().get(i).getPhone());
            speciality.setDoctor(provider);
        }

        System.out.println("getspdetail"+speciality.getPriceSpeciality());
        return speciality;
    }

    //getspeciality for update
    @Override
    public Speciality getspdetailUp(long productCategory) {

        Speciality res= specialityRepositor.getOne(productCategory);
        Speciality speciality=new Speciality();

        speciality.setId(res.getId());
        speciality.setPriceSpeciality(res.getPriceSpeciality());
        speciality.setName(res.getName());
        speciality.setStatus(res.isStatus());
        System.out.println("getspdetail"+productCategory);
        System.out.println("getspdetail"+speciality.getPriceSpeciality());
        return speciality;
    }
//save
    @Override
    public Speciality save(Speciality productCategory)
    { //productCategory.setStatus(true);
        return specialityRepositor.save(productCategory);
    }
    //getspeciality
    public Speciality getspeciality(long productCategory) {
        return specialityRepositor.getOne(productCategory);
    }
//offspeciality
    @Override
    @Transactional
    public Speciality offSpeciality(long productId) {
        //blocked and not ----findOne ->status true
        Speciality productInfo = findOne(productId);
        productInfo.setStatus(false);
        return specialityRepositor.save(productInfo);
    }
    // onSpeciality  admin  deblock
    @Override
    @Transactional
    public Speciality onSpeciality(long productId) {
        Speciality productInfo = getspeciality(productId);

        productInfo.setStatus(true);
        return specialityRepositor.save(productInfo);
    }
    ///delete on db
    @Override
    public void delete(long productId) {
        Speciality productInfo = getspeciality(productId);
        if (productInfo == null) throw new MyException(ResultEnum.CATEGORY_NOT_FOUND);
        specialityRepositor.delete(productInfo);

    }
    //delete for -provider
    @Override
    public void deletespeciality(long id, long speciality)
    {
        Speciality speciality1 = specialityRepositor.findByIdAndStatusTrue(speciality);
        Provider p=providerRepository.getOne(id);
        System.out.println("prix avant delete :"+p.getPricesubscription());
        p.setPricesubscription(p.getPricesubscription()-speciality1.getPriceSpeciality());
        System.out.println("prix apres delete :"+p.getPricesubscription());
        p.getSpecialities().remove(speciality1);
        providerRepository.save(p);
        speciality1.getProviders().remove(p);
        System.out.println("speciality"+speciality1.getName());
         specialityRepositor.save(speciality1);
    }

}
