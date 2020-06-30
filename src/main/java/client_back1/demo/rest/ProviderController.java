package client_back1.demo.rest;
import client_back1.demo.entity.*;
import client_back1.demo.service.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ProviderController {
    private final ProviderService providerService;
    @Autowired
     AdminService adminService;
    private final ProductService productService;
    private final SpecialityService specialityService;
    private final ImageService imageService;
    private final ImageUploadController imageUploadController;

    public ProviderController(ProductService productService, ProviderService providerService, SpecialityService specialityService, ImageService imageService, ImageUploadController imageUploadController) {
        this.providerService = providerService;
        this.productService = productService;
        this.specialityService = specialityService;

        this.imageService = imageService;
        this.imageUploadController = imageUploadController;
    }
    ///////////////////provider -admin/////////////////
    //profil
    @PutMapping("/profile/update")
    public Provider updateProfil( @Valid @RequestBody Provider provider) {

        return this.providerService.updateProfil(provider);
    }
    @GetMapping("/profile/{id}")
    public Provider getProfile(@Valid @PathVariable("id") long id)  {
        return  this.providerService.getProfil(id);
      /*  Speciality s=new Speciality();
        for (int i=0;i<p.getSpecialities().size();i++)
        {
            s.setId(p.getSpecialities().get(i).getId());
            s.setName(p.getSpecialities().get(i).getName());
            s.setPriceSpeciality(p.getSpecialities().get(i).getPriceSpeciality());
         //   s.setProviders((ArrayList<Provider>) p.getSpecialities().get(i).getProviders());
            l.add(s);
        }*/

      //  ObjectMapper mapper = new ObjectMapper();
      //  final String json = mapper.writeValueAsString(p);
       // return json;

    /*    Provider provider=new Provider();
        provider.setId(p.getId());
        provider.setFirstname(p.getFirstname());
        provider.setLastname(p.getLastname());
        provider.setPhone(p.getPhone());

       // provider.setSpecialities(p.getSpecialities());
        provider.setEmailsociety(p.getEmailsociety());
        provider.setEmail(p.getEmail());
        provider.setRole(p.getRole());
        provider.setActive(p.isActive());
        provider.setPricesubscription(p.getPricesubscription());
        provider.setPassword(p.getPassword());
        provider.setName(p.getName());
        return ResponseEntity.ok(provider);*/


       /* if (principal.getName().equals(id)) {
            return this.providerService.findOne(id);
        } else {
            return null;
        }
*/
        //return this.providerService.getProfil(id);
      //  return new Provider(id);
    }
 /*   @PutMapping("/profile")
    public ResponseEntity<User> update(@RequestBody User user, Principal principal) {

        try {
            if (!principal.getName().equals(user.getEmail())) throw new IllegalArgumentException();
            return ResponseEntity.ok(userService.update(user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/profile/{email}")

    public ResponseEntity<User> getProfile(@PathVariable("email") String email, Principal principal) {
        if (principal.getName().equals(email)) {
            return ResponseEntity.ok(userService.findOne(email));
        } else {
            return ResponseEntity.badRequest().build();
        }

    }*/
////////////////////////////////////////provider///////////////////////////////////
    //provider add product
    @PostMapping("/provider/{id}/product/new")
    public long create(@PathVariable("id")long id,@Valid @RequestBody Product product,
                                 BindingResult bindingResult) {
        Product productIdExists = productService.findOne(product.getId());
        if (productIdExists != null) {
            bindingResult
                    .rejectValue("Id", "error.product",
                            "There is already a product with the code provided");
        }
        if (bindingResult.hasErrors()) {
            System.out.println("erreur"+bindingResult.hasErrors());
            return 0;
        }
        Product p=product;
        Provider pro=this.adminService.getProvider(id);
        p.setProvider(pro);
       // pro.getProduct().add(p);
      //  p= productService.save(p);
      //  providerService.save(pro);
      //  providerService.save(pro);

        return productService.save(p);
    }

///provider edit product -liste image
    @PostMapping("/provider/{id}/product/update")
    public long update(@PathVariable("id") long productId,
                               @Valid @RequestBody Product product) {


        return productService.update(product);
    }
///////////list 1 image
    @GetMapping("/provider/{id}/All-products")
    public  List<Product> allproducts(@PathVariable("id") long id
                                    //  @RequestParam(value = "page", defaultValue = "1") Integer page,
                                   //   @RequestParam(value = "size", defaultValue = "3") Integer size
    ){
        //PageRequest request = PageRequest.of(page - 1, size);
        return  productService.findAllProductprovider(id);
    }
    ////////////////// Quotation -provider/////////////////////////////////////////
   /* @PutMapping("/provider/Quotation/{id}")
    public Quotation getdevis(@Valid @PathVariable("id") long id){
        return this.quotationService.getQuotation(id);
    }
    //all
    @GetMapping("/provider/{id}/All-Quotations")
    public List<Quotation> allQuotation(@PathVariable("id") long id){
        return providerService.allQuotation(id);
    }*/
////////////////// specilality -provider/////////////////////////////////////////
    @GetMapping("/provider/{id}/All-specialities")
    public List<Speciality> allspecialities(@PathVariable("id") long id){
        return providerService.findAllspeprovider(id);
    }

    ///////////////////////////////speciality_providersp///////////////////
    @GetMapping("/provider/{id}/specialities/list-choose")
    public List<Speciality> choosesp(@PathVariable("id") long id){
        return specialityService.shooselist(id);
    }
    @PostMapping("/provider/speciality/add")
    public Provider Addspecialitys(@RequestParam("user")  String provider,
                                  @RequestParam("special") String s) throws JsonProcessingException {
            System.out.println("gg"+provider);
        System.out.println("gg"+s);
        return providerService.updatelistsp(provider,s);
    }
    //get speciality
    @GetMapping("/provider/{provider}/speciality/Edit/{id}")
    public Speciality getspeciality(@PathVariable("provider") long provider,
                              @PathVariable("id") long id) {

       return specialityService.getspecialitypr(provider,id);

    }
    @PostMapping("/provider/{id}/speciality/delete")
    public void deletespeciality(@PathVariable("id") long id,
                                 @Valid @RequestBody long speciality) {

        specialityService.deletespeciality(id,speciality);

    }

    ////////////////////// search ///////////////////////////////////////
    //pas encore
    @PostMapping("/provider/{id}/search")
    public ResponseEntity search(@PathVariable("id") long id, @RequestBody String speciality) {
        Speciality speciality1 = specialityService.findName(speciality);
        Provider p=providerService.getProfil(id);
        p.getSpecialities().remove(speciality1);

        speciality1.getProviders().remove(p);
        return ResponseEntity.ok(specialityService.save(speciality1));
    }

//////////////////////////
/*
    @PostMapping("/addproductt")
    public Product addProduct(@RequestParam("imageFile") MultipartFile image, @RequestParam("File") MultipartFile file) throws IOException {
 Product product=new Product();
//this.imageUploadController.uplaodImage(image);
return product;
    }*/

    /*@GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return this.productService.getProduct(id);
    }

    @PostMapping("/addproduct")
    public Product addProduct(@Valid @RequestBody Product product)
    {
        return this.providerService.addProduct(product);
    }
    @GetMapping("/getproduct/{id}")
    public Product getProduct(@Valid @PathVariable long id) {
        return this.providerService.getProduct(id);
    }*/

/* @PutMapping("/updateProfil")
    public boolean updateProfil(@Valid @PathVariable long id, @Valid @RequestBody Provider provider) {
        return this.providerService.updateProfil(id,provider);
    }
    @GetMapping("/getProfil/{id}")
    public Provider getPRovider(@Valid @PathVariable("id") long id){
        return this.providerService.getProvider(id);
    }
    @PostMapping("/addproduct/{id}")
    public Product addProduct(@PathVariable("id") long id, @RequestParam("product") String productStr,@Valid @RequestParam("images") MultipartFile[] images) {
        Product product = new Gson().fromJson(productStr, Product.class);
       // System.out.println(getPRovider(id).getFirstname());
       // product.setProvider(getPRovider(id));
        Product product1= this.providerService.addProduct(product);
        List<Image> liste=Image.convertToImage(images,product1);
        List<Image> temp=new ArrayList<Image>();
        for(int i=0;i<liste.size();i++)
            temp.add(new Image());
        product1.setImages(temp);
        Product product2= this.providerService.updateProduct(product1.getId(),product1);
        for (int i=0;i<liste.size();i++) {
            liste.get(i).setId(product2.getImages().get(i).getId());
        }
        for (Image image: liste) {
            this.imageService.updateImage(image);
        }
        Product product3= this.providerService.getProduct(product2.getId());
        for ( MultipartFile image: images) {
            this.imageStorageService.storeImage(image,product);
        }
        this.imageController.uploadMultipleFiles(product3,images);
        return product3;
    }
    @PostMapping("/addproduct")
    public Product addProduct(@RequestParam("product") String productStr){
        Product product = new Gson().fromJson(productStr, Product.class);
        System.out.println("ggg"+product);
        return this.providerService.addProduct(product);
    }
    @PutMapping("/updateproduct")

    public Product updateProduct(@Valid @PathVariable long idProduct, @Valid @RequestBody Product newProduct) {
        return this.providerService.updateProduct(idProduct,newProduct);
    }

    @DeleteMapping("/deleteproduct")
    public boolean deleteProduct(@Valid @PathVariable long id) {
        return  this.providerService.deleteProduct(id);
    }

    @GetMapping("/getproduct/{id}")
    public Product getProduct(@Valid @PathVariable long id) {
        return this.providerService.getProduct(id);
    }

    @GetMapping("/specialities")
    public List<Speciality> getAllSpecialitys() {
        return this.providerService.getAllSpecialitys();
    }*/
}
