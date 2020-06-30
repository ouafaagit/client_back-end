package client_back1.demo.service.impl;

import client_back1.demo.entity.*;
import client_back1.demo.enums.ResultEnum;
import client_back1.demo.exception.MyException;
import client_back1.demo.repository.ImageRepository;
import client_back1.demo.repository.ProductRepository;
import client_back1.demo.repository.SpecialityRepository;
import client_back1.demo.service.ImageService;
import client_back1.demo.service.ProductService;
import client_back1.demo.vo.request.cardproduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    ImageService imageService;
    @Autowired
     SpecialityRepository specialityRepository;
////////////////////////////////for sale///////////////////////////////
    //all product active
    @Override
    public Page<Product> findUpAll(Pageable pageable) {
        return productRepository.findAllByBlockedFalse(pageable);
    }
    //all product card
    @Override
    public List<cardproduct> findAllproducts() {
        List<Product> products=productRepository.findAllByBlockedFalse();
       ImageModel  img= new ImageModel();
        List<cardproduct> cardproducts= new ArrayList<>();
        for(int i=0;i<products.size();i++)
        {       cardproduct  cardproduct=new cardproduct(products.get(i));
            if (products.get(i).getPr_images().get(0)!=null) {
                img = imageService.findById(products.get(i).getPr_images().get(0).getId());

               // products.get(i).setPr_images(new ArrayList<ImageModel>() );
              //  products.get(i).getPr_images().add(img);
                cardproduct.setPicByte(img.getPicByte());
            }

            cardproducts.add(cardproduct);
            }
        return cardproducts;
    }

    //all product card
    @Override
    public List<cardproduct> findAllbysp(long id) {
        List<Product> products=productRepository.findAllByBlockedIsFalseAndSpeciality_Id(id);
        ImageModel  img= new ImageModel();
        List<cardproduct> cardproducts= new ArrayList<>();
        for(int i=0;i<products.size();i++)
        {       cardproduct  cardproduct=new cardproduct(products.get(i));
            if (products.get(i).getPr_images().get(0)!=null) {
                img = imageService.findById(products.get(i).getPr_images().get(0).getId());

                // products.get(i).setPr_images(new ArrayList<ImageModel>() );
                //  products.get(i).getPr_images().add(img);
                cardproduct.setPicByte(img.getPicByte());
            }

            cardproducts.add(cardproduct);
        }
        return cardproducts;
    }

    //all new product card
    @Override
    public List<cardproduct> Allnewprod() {
        List<Product> products=productRepository.findAllByBlockedFalseAndAndNombreVueLessThanEqual(200);
        System.out.println(" Allnewprod"+products.toString());
        ImageModel  img= new ImageModel();
        List<cardproduct> cardproducts= new ArrayList<>();
        for(int i=0;i<products.size();i++)
        {       cardproduct  cardproduct=new cardproduct(products.get(i));
            if (products.get(i).getPr_images().get(0)!=null) {
                img = imageService.findById(products.get(i).getPr_images().get(0).getId());

                // products.get(i).setPr_images(new ArrayList<ImageModel>() );
                //  products.get(i).getPr_images().add(img);
                cardproduct.setPicByte(img.getPicByte());
            }

            cardproducts.add(cardproduct);
        }
        return cardproducts;
    }
    /// One product for user and provider
    @Override
    public Product getproductU(long productId) {
         Product p=productRepository.findByIdAndBlockedIsFalse(productId);
       double n= p.getNombreVue()+1;
        p.setNombreVue(n);
        p= productRepository.save(p);
        Product product=new Product();
        Speciality s= new Speciality();
        s.setName(p.getSpeciality().getName());
        s.setId(p.getSpeciality().getId());
        product.setSpeciality(s);
        System.out.println("speciality :"+product.getSpeciality());
        product.setId(p.getId());
        product.setName(p.getName());
        product.setDescription(p.getDescription());
        product.setReference(p.getReference());
        product.setCatalogue(p.getCatalogue());
        product.setMarque(p.getMarque());
        product.setNombreVue(n);
        System.out.println("nbr vue"+product.getNombreVue());
        product.setNombrewish(p.getNombrewish());
            for(int i=0;i<p.getPr_images().size();i++) {
                ImageModel  img= new ImageModel();
                long name = p.getPr_images().get(i).getId();
                img= imageService.findById(name);
                ImageModel  imge= new ImageModel();
               imge.setName(img.getName());
                imge.setId(img.getId());
               imge.setPicByte(img.getPicByte());
               imge.setType(img.getType());
            System.out.println("p.getPr_images().get(i).getId() :"+p.getPr_images().get(i).getId());
            System.out.println("image name:"+name);
            System.out.println("image img.getId():"+img.getId());
            System.out.println("image :"+imge.getName());
            product.setPr_image(imge);
        }
        for(int i=0;i<p.getComplaint().size();i++) {
            Complaint  complaint= new Complaint();
            String name = p.getComplaint().get(i).getName();
            System.out.println("getComplaint :"+name);
            complaint.setEmail(p.getComplaint().get(i).getEmail());
            complaint.setId(p.getComplaint().get(i).getId());
            complaint.setMessage(p.getComplaint().get(i).getMessage());
            complaint.setName(p.getComplaint().get(i).getName());
            complaint.setObjet(p.getComplaint().get(i).getObjet());
            product.getComplaint().add(complaint);
        }

        return product;
    }

    /// One product for user and provider
    @Override
    public Product quickview(long productId) {
        Product p=productRepository.findByIdAndBlockedIsFalse(productId);
        double n= p.getNombreVue()+1;
        p.setNombreVue(n);
        p= productRepository.save(p);
        Product product=new Product();
        Speciality s= new Speciality();
        s.setName(p.getSpeciality().getName());
        s.setId(p.getSpeciality().getId());
        product.setSpeciality(s);
        System.out.println("speciality :"+product.getSpeciality());
        product.setId(p.getId());
        product.setName(p.getName());
        product.setDescription(p.getDescription());
        product.setReference(p.getReference());
        product.setMarque(p.getMarque());
        System.out.println("nbr vue"+product.getNombreVue());
        for(int i=0;i<p.getPr_images().size();i++) {
            ImageModel  img= new ImageModel();
            long name = p.getPr_images().get(i).getId();
            img= imageService.findById(name);
            ImageModel  imge= new ImageModel();
            imge.setName(img.getName());
            imge.setId(img.getId());
            imge.setPicByte(img.getPicByte());
            System.out.println("p.getPr_images().get(i).getId() :"+p.getPr_images().get(i).getId());
            System.out.println("image name:"+name);
            System.out.println("image img.getId():"+img.getId());
            System.out.println("image :"+imge.getName());
            product.setPr_image(imge);
        }

        return product;
    }

    ////
    @Override
    public List<cardproduct> relatedprod(long spcialityid) {
        List<Product>  p= productRepository.findAllByBlockedIsFalseAndSpeciality_Id(spcialityid);
        List<cardproduct> products= new ArrayList<>();        //   System.out.println("tendancnew");
        if(p.size()<4){
            for(int i=0;i<p.size();i++){
                System.out.println(" tendancnew produit"+p.get(i).getName());
                cardproduct  cardproduct=new cardproduct(p.get(i));
                ImageModel  img= new ImageModel();
                if(p.get(i).getPr_images().isEmpty()) {}
                else{
                    if(p.get(i).getPr_images().get(0)!=null){
                        long name = p.get(i).getPr_images().get(0).getId();
                        img= imageService.findById(name);
                        cardproduct.setPicByte(img.getPicByte());
                    }}
                products.add(cardproduct);
                System.out.println(" tendancnew 1"+p.get(i).getName());
            }
        }else{
            for(int i=0;i<4;i++){
                ImageModel  img= new ImageModel();
                cardproduct  cardproduct=new cardproduct(p.get(p.size()-i));
                if(p.get(p.size()-i).getPr_images().isEmpty()) {}
                else{
                    if(p.get(p.size()-i).getPr_images().get(0)!=null){
                        long name = p.get(i).getPr_images().get(0).getId();
                        img= imageService.findById(name);
                        cardproduct.setPicByte(img.getPicByte());
                    }}
                products.add(cardproduct);
                //     System.out.println(" tendancnew produit"+p.get(p.size()-i).getName());

            }}

        System.out.println(" ***************relatedprod produit"+products.toString());

        return products;

    }
    /////////provider get update ////////////

    @Override
    public Product getproductUp(long productId) {
        Product p=productRepository.findByIdAndBlockedIsFalse(productId);
        Product product=new Product();
        Speciality s= new Speciality();
        s.setName(p.getSpeciality().getName());
        s.setId(p.getSpeciality().getId());
        product.setSpeciality(s);
        System.out.println("speciality :"+product.getSpeciality());
        product.setId(p.getId());
        product.setName(p.getName());
        product.setDescription(p.getDescription());
        product.setReference(p.getReference());
        product.setCatalogue(p.getCatalogue());
        product.setMarque(p.getMarque());
        for(int i=0;i<p.getPr_images().size();i++) {
            ImageModel  img= new ImageModel();
            long name = p.getPr_images().get(i).getId();
            img= imageService.findById(name);
            ImageModel  imge= new ImageModel();
            imge.setName(img.getName());
            imge.setId(img.getId());
            imge.setPicByte(img.getPicByte());
            imge.setType(img.getType());
            System.out.println("image img.getId():"+imge.getId());
            System.out.println("image :"+imge.getName());
            product.setPr_image(imge);
        }

        return product;
    }

    //products by speciality name
    @Override
    public Page<Product> getproducts_sp(String name, Pageable pageable) {
        return productRepository.findAllByBlockedIsFalseAndSpecialityName(name,pageable);
    }

/////////////////////////for admin//////////////////////////////
//blocked and not
@Override
public Product findOne(long productId) {
    Product product = productRepository.findById(productId);
    return product;
}

///Get product detail
@Override
public Product getproductAd(long productId) {
    Product p=productRepository.getOne(productId);
    double n= p.getNombreVue()+1;
    p.setNombreVue(n);
    p= productRepository.save(p);
    Product product=new Product();
    Speciality s= new Speciality();
    s.setName(p.getSpeciality().getName());
    s.setId(p.getSpeciality().getId());
    product.setSpeciality(s);
    System.out.println("speciality :"+product.getSpeciality());
    product.setId(p.getId());
    product.setName(p.getName());
    product.setDescription(p.getDescription());
    product.setReference(p.getReference());
    product.setCatalogue(p.getCatalogue());
    product.setMarque(p.getMarque());
    product.setNombreVue(n);
    product.setBlocked(p.isBlocked());
     Provider provider= new Provider();
    provider.setLastname(p.getProvider().getLastname());
    provider.setFirstname(p.getProvider().getFirstname());
    provider.setEmail(p.getProvider().getEmail());
    product.setProvider(provider);
    System.out.println("nbr vue"+product.getNombreVue());
    System.out.println("nbr vue"+product.getProvider().getLastname());
    product.setNombrewish(p.getNombrewish());
    for(int i=0;i<p.getPr_images().size();i++) {
        ImageModel  img= new ImageModel();
        long name = p.getPr_images().get(i).getId();
        img= imageService.findById(name);
        ImageModel  imge= new ImageModel();
        imge.setName(img.getName());
        imge.setId(img.getId());
        imge.setPicByte(img.getPicByte());
        imge.setType(img.getType());
        System.out.println("image :"+imge.getName());
        product.setPr_image(imge);
    }
    for(int i=0;i<p.getComplaint().size();i++) {
        Complaint  complaint= new Complaint();
        String name = p.getComplaint().get(i).getName();
        System.out.println("getComplaint :"+name);
        complaint.setEmail(p.getComplaint().get(i).getEmail());
        complaint.setId(p.getComplaint().get(i).getId());
        complaint.setMessage(p.getComplaint().get(i).getMessage());
        complaint.setName(p.getComplaint().get(i).getName());
        complaint.setObjet(p.getComplaint().get(i).getObjet());
        product.getComplaint().add(complaint);
    }

    return product;
}
    @Override
    public List<Product> findAll() {
        List<Product> list=productRepository.findAll();
        List<Product> l=new ArrayList<>();
        if(list!=null){
            for(int i=0;i<list.size();i++) {
                //  System.out.println("****"+user.getSpecialities().get(i).toString());
                // Speciality s=specialityRepository.getOne(user.getSpecialities().get(i).getId());
                Product product=new Product();
                Speciality s= new Speciality();
                s.setName(list.get(i).getSpeciality().getName());
                s.setId(list.get(i).getSpeciality().getId());
                product.setSpeciality(s);
                product.setId(list.get(i).getId());
                product.setBlocked(list.get(i).isBlocked());
                product.setName(list.get(i).getName());
                //product.setDescription(list.get(i).getDescription());
                product.setReference(list.get(i).getReference());
                // product.setCatalogue(list.get(i).getCatalogue());
                product.setMarque(list.get(i).getMarque());
                ImageModel  img= new ImageModel();
                if(list.get(i).getPr_images().isEmpty()) {}
                else{
                if(list.get(i).getPr_images().get(0)!=null){
                    long name = list.get(i).getPr_images().get(0).getId();
                    img= imageService.findById(name);
                    ImageModel  imge= new ImageModel();
                    imge.setName(img.getName());
                    imge.setId(img.getId());
                    imge.setPicByte(img.getPicByte());
                    imge.setType(img.getType());
                    System.out.println("allproductprov"+imge.getName());
                    product.setPr_image(imge);
                }
                }

                l.add(product);
            }
        }
        return l ;

    }
    //admin block
    @Override
    public void offSale(long productId) {
        //blocked and not
        Product productInfo = findOne(productId);
        if (productInfo == null) throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);

        if (productInfo.isBlocked()) {
            throw new MyException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        System.out.println("offSale true "+productId);
        productInfo.setBlocked(true);
         productRepository.save(productInfo);
    }
    //admin  deblock
    @Override

    public void onSale(long productId) {
        Product productInfo = findOne(productId);
        if (productInfo == null) throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);
        productInfo.setBlocked(false);
        System.out.println("onSale false "+productId);
         productRepository.save(productInfo);
    }
    ///delete on db
    @Override
    public void delete(long productId) {
        Product productInfo = findOne(productId);
        System.out.println("Supprimer  prod"+productId);
        if (productInfo == null) throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);
        for(int i=0;i<productInfo.getPr_images().size();i++)
        {  System.out.println("Supprimer image prod"+productId);
            imageRepository.delete(productInfo.getPr_images().get(i));
        }
        productRepository.delete(productInfo);

    }
//////////////////////////////provider////////////////////
    //for  provider list
    @Override
    public List<Product> findAllProductprovider(long idprovider) {
        List<Product> list= productRepository.findAllByBlockedIsFalseAndProvider_Id(idprovider);
        List<Product> l=new ArrayList<>();
        if(list!=null){
        for(int i=0;i<list.size();i++) {
          //  System.out.println("****"+user.getSpecialities().get(i).toString());
           // Speciality s=specialityRepository.getOne(user.getSpecialities().get(i).getId());
          Product product=new Product();
            Speciality s= new Speciality();
                   s.setName(list.get(i).getSpeciality().getName());
                   s.setId(list.get(i).getSpeciality().getId());
            product.setSpeciality(s);
            product.setId(list.get(i).getId());
            product.setName(list.get(i).getName());
            //product.setDescription(list.get(i).getDescription());
            product.setReference(list.get(i).getReference());
           // product.setCatalogue(list.get(i).getCatalogue());
            product.setMarque(list.get(i).getMarque());
         ImageModel  img= new ImageModel();
           if(list.get(i).getPr_images().isEmpty()) {}
           else{
               if (list.get(i).getPr_images().get(0)!=null) {
                   long name = list.get(i).getPr_images().get(0).getId();
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
        }
        return l ;

    }

    //edit update product


    //edit update product
    @Override
    public long update(Product productInfo) {

        // if null throw exception
        // categoryService.findByCategoryType(productInfo.getCategoryType());
        if(productInfo.isBlocked()) {
            System.out.println("blocked");
            throw new MyException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        if(productInfo.getId()!=0){
            Product product=productRepository.getOne(productInfo.getId());
            product.setDescription(productInfo.getDescription());
            product.setMarque(productInfo.getMarque());
            product.setReference(productInfo.getReference());
            product.setName(productInfo.getName());
            if(productInfo.getSpeciality().getId()>product.getSpeciality().getId()||
                    productInfo.getSpeciality().getId()<product.getSpeciality().getId()){
                System.out.println("--"+productInfo.getName());
                Speciality sp=specialityRepository.findByIdAndStatusTrue(productInfo.getSpeciality().getId());
                System.out.println("speciality"+sp.getName());
                product.setSpeciality(sp);

            }
            Product p=productRepository.save(product);
            return p.getId();
        }else{
        System.out.println("--"+productInfo.getName());
        Speciality sp=specialityRepository.findByIdAndStatusTrue(productInfo.getSpeciality().getId());
        System.out.println("speciality"+sp.getName());
        productInfo.setSpeciality(sp);
        Product p=productRepository.save(productInfo);
        return p.getId();}
    }
    //add
    @Override
    public long save(Product productInfo) {
        return update(productInfo);
    }

    @Override
    public List<cardproduct> Diagnostinew() {
        List<Product>  p= productRepository.findAllByBlockedIsFalseAndSpeciality_NameAndNombreVueLessThan("Diagnostic ",20);
        List<cardproduct> products=new ArrayList<>();
        //   System.out.println("tendancnew");
        if(p.size()<6){
            for(int i=0;i<p.size();i++){
                System.out.println(" tendancnew produit"+p.get(i).getName());
                cardproduct  cardproduct=new cardproduct(p.get(i));
                ImageModel  img= new ImageModel();
                if(p.get(i).getPr_images().isEmpty()) {}
                else{
                    if(p.get(i).getPr_images().get(0)!=null){
                        long name = p.get(i).getPr_images().get(0).getId();
                        img= imageService.findById(name);
                        cardproduct.setPicByte(img.getPicByte());
                    }}
                products.add(cardproduct);
                System.out.println(" Diagnostinew 1"+p.get(i).getName());
            }
        }else{
            for(int i=0;i<6;i++){
                ImageModel  img= new ImageModel();
                cardproduct  cardproduct=new cardproduct(p.get(p.size()-i));
                if(p.get(p.size()-i).getPr_images().isEmpty()) {}
                else{
                    if(p.get(p.size()-i).getPr_images().get(0)!=null){
                        long name = p.get(i).getPr_images().get(0).getId();
                        img= imageService.findById(name);
                        cardproduct.setPicByte(img.getPicByte());
                    }}
                products.add(cardproduct);
                //     System.out.println(" tendancnew produit"+p.get(p.size()-i).getName());

            }}
        return products;
    }

    @Override
    public List<cardproduct> Diagnostiautre() {
        List<Product>  p= productRepository.findAllByBlockedIsFalseAndSpeciality_Name("Diagnostic");
        List<cardproduct> products=new ArrayList<>();
        //   System.out.println("tendancnew");
        if(p.size()<6){
            for(int i=0;i<p.size();i++){
                System.out.println(" tendancnew produit"+p.get(i).getName());
                cardproduct  cardproduct=new cardproduct(p.get(i));
                ImageModel  img= new ImageModel();
                if(p.get(i).getPr_images().isEmpty()) {}
                else{
                    if(p.get(i).getPr_images().get(0)!=null){
                        long name = p.get(i).getPr_images().get(0).getId();
                        img= imageService.findById(name);
                        cardproduct.setPicByte(img.getPicByte());
                    }}
                products.add(cardproduct);
                System.out.println(" tendancnew 1"+p.get(i).getName());
            }
        }else{
            for(int i=0;i<6;i++){
                ImageModel  img= new ImageModel();
                cardproduct  cardproduct=new cardproduct(p.get(p.size()-i));
                if(p.get(p.size()-i).getPr_images().isEmpty()) {}
                else{
                    if(p.get(p.size()-i).getPr_images().get(0)!=null){
                        long name = p.get(i).getPr_images().get(0).getId();
                        img= imageService.findById(name);
                        cardproduct.setPicByte(img.getPicByte());
                    }}
                products.add(cardproduct);
                //     System.out.println(" tendancnew produit"+p.get(p.size()-i).getName());

            }}
        return products;
    }

    @Override
    public List<cardproduct> Diagnostivendu() {
        List<Product>  p=productRepository.findAllByBlockedIsFalseAndSpeciality_NameAndNombreVueGreaterThan("Diagnostic",5);
        List<cardproduct> products=new ArrayList<>();
        //   System.out.println("tendancnew");
        if(p.size()<6){
            for(int i=0;i<p.size();i++){
                System.out.println(" tendancnew produit"+p.get(i).getName());
                cardproduct  cardproduct=new cardproduct(p.get(i));
                ImageModel  img= new ImageModel();
                if(p.get(i).getPr_images().isEmpty()) {}
                else{
                    if(p.get(i).getPr_images().get(0)!=null){
                        long name = p.get(i).getPr_images().get(0).getId();
                        img= imageService.findById(name);
                        cardproduct.setPicByte(img.getPicByte());
                    }}
                products.add(cardproduct);
                System.out.println(" tendancnew 1"+p.get(i).getName());
            }
        }else{
            for(int i=0;i<6;i++){
                ImageModel  img= new ImageModel();
                cardproduct  cardproduct=new cardproduct(p.get(p.size()-i));
                if(p.get(p.size()-i).getPr_images().isEmpty()) {}
                else{
                    if(p.get(p.size()-i).getPr_images().get(0)!=null){
                        long name = p.get(i).getPr_images().get(0).getId();
                        img= imageService.findById(name);
                        cardproduct.setPicByte(img.getPicByte());
                    }}
                products.add(cardproduct);
                //     System.out.println(" tendancnew produit"+p.get(p.size()-i).getName());

            }}
        return products;
    }

    @Override
    public List<cardproduct> logicienew() {
        List<Product>  p= productRepository.findAllByBlockedIsFalseAndSpeciality_NameNotAndNombreVueLessThan("Diagnostic",10);
        List<cardproduct> products=new ArrayList<>();
        //   System.out.println("tendancnew");
        if(p.size()<6){
            for(int i=0;i<p.size();i++){
                System.out.println(" logicienew"+p.get(i).getName());
                cardproduct  cardproduct=new cardproduct(p.get(i));
                ImageModel  img= new ImageModel();
                if(p.get(i).getPr_images().isEmpty()) {}
                else{
                    if(p.get(i).getPr_images().get(0)!=null){
                        long name = p.get(i).getPr_images().get(0).getId();
                        img= imageService.findById(name);
                        cardproduct.setPicByte(img.getPicByte());
                    }}
                products.add(cardproduct);
                System.out.println("logicienew"+p.get(i).getName());
            }
        }else{
            for(int i=0;i<6;i++){
                ImageModel  img= new ImageModel();
                cardproduct  cardproduct=new cardproduct(p.get(p.size()-i));
                if(p.get(p.size()-i).getPr_images().isEmpty()) {}
                else{
                    if(p.get(p.size()-i).getPr_images().get(0)!=null){
                        long name = p.get(i).getPr_images().get(0).getId();
                        img= imageService.findById(name);
                        cardproduct.setPicByte(img.getPicByte());
                    }}
                products.add(cardproduct);
                //     System.out.println(" tendancnew produit"+p.get(p.size()-i).getName());

            }}
        return products;    }

    @Override
    public List<cardproduct> logicievendu() {
        List<Product>  p= productRepository.findAllByBlockedIsFalseAndNombreVueNotNullAndSpeciality_NameNot("Diagnostic");
        List<cardproduct> products=new ArrayList<>();
        //   System.out.println("tendancnew");
        if(p.size()<6){
            for(int i=0;i<p.size();i++){
                System.out.println(" logicievendu"+p.get(i).getName());
                cardproduct  cardproduct=new cardproduct(p.get(i));
                ImageModel  img= new ImageModel();
                if(p.get(i).getPr_images().isEmpty()) {}
                else{
                    if(p.get(i).getPr_images().get(0)!=null){
                        long name = p.get(i).getPr_images().get(0).getId();
                        img= imageService.findById(name);
                        cardproduct.setPicByte(img.getPicByte());
                    }}
                products.add(cardproduct);
                System.out.println(" logicievendu 1"+p.get(i).getName());
            }
        }else{
            for(int i=0;i<6;i++){
                ImageModel  img= new ImageModel();
                cardproduct  cardproduct=new cardproduct(p.get(p.size()-i));
                if(p.get(p.size()-i).getPr_images().isEmpty()) {}
                else{
                    if(p.get(p.size()-i).getPr_images().get(0)!=null){
                        long name = p.get(i).getPr_images().get(0).getId();
                        img= imageService.findById(name);
                        cardproduct.setPicByte(img.getPicByte());
                    }}
                products.add(cardproduct);
                //     System.out.println(" tendancnew produit"+p.get(p.size()-i).getName());

            }}
        return products;    }

    @Override
    public List<cardproduct> logicieautre() {
        List<Product>  p= productRepository.findAllByBlockedIsFalseAndSpeciality_NameNot("Diagnostic");
        List<cardproduct> products=new ArrayList<>();
        //   System.out.println("tendancnew");
        if(p.size()<6){
            for(int i=0;i<p.size();i++){
                System.out.println(" logicieautre produit"+p.get(i).getName());
                cardproduct  cardproduct=new cardproduct(p.get(i));
                ImageModel  img= new ImageModel();
                if(p.get(i).getPr_images().isEmpty()) {}
                else{
                    if(p.get(i).getPr_images().get(0)!=null){
                        long name = p.get(i).getPr_images().get(0).getId();
                        img= imageService.findById(name);
                        cardproduct.setPicByte(img.getPicByte());
                    }}
                products.add(cardproduct);
                System.out.println(" logicieautre 1"+p.get(i).getName());
            }
        }else{
            for(int i=0;i<6;i++){
                ImageModel  img= new ImageModel();
                cardproduct  cardproduct=new cardproduct(p.get(p.size()-i));
                if(p.get(p.size()-i).getPr_images().isEmpty()) {}
                else{
                    if(p.get(p.size()-i).getPr_images().get(0)!=null){
                        long name = p.get(i).getPr_images().get(0).getId();
                        img= imageService.findById(name);
                        cardproduct.setPicByte(img.getPicByte());
                    }}
                products.add(cardproduct);
                //     System.out.println(" tendancnew produit"+p.get(p.size()-i).getName());

            }}
        return products;    }

    @Override
    public List<cardproduct> tendancnew() {
        List<Product>  p= productRepository.findAllByBlockedFalseAndAndNombreVueGreaterThanEqual(1);
        List<cardproduct> products=new ArrayList<>();
     //   System.out.println("tendancnew");
        if(p.size()<6){
            for(int i=0;i<p.size();i++){
                System.out.println(" tendancnew produit"+p.get(i).getName());
                cardproduct  cardproduct=new cardproduct(p.get(i));
                ImageModel  img= new ImageModel();
                if(p.get(i).getPr_images().isEmpty()) {}
                else{
                    if(p.get(i).getPr_images().get(0)!=null){
                        long name = p.get(i).getPr_images().get(0).getId();
                        img= imageService.findById(name);
                        cardproduct.setPicByte(img.getPicByte());
                    }}
                products.add(cardproduct);
                System.out.println(" tendancnew 1"+p.get(i).getName());
            }
        }else{
            for(int i=0;i<6;i++){
                ImageModel  img= new ImageModel();
                cardproduct  cardproduct=new cardproduct(p.get(p.size()-i));
                if(p.get(p.size()-i).getPr_images().isEmpty()) {}
                else{
                    if(p.get(p.size()-i).getPr_images().get(0)!=null){
                        long name = p.get(i).getPr_images().get(0).getId();
                        img= imageService.findById(name);
                        cardproduct.setPicByte(img.getPicByte());
                    }}
                products.add(cardproduct);
           //     System.out.println(" tendancnew produit"+p.get(p.size()-i).getName());

            }}
      //  System.out.println(" ***************tendancnew produit"+products.toString());

        return products;

    }


}
