package client_back1.demo.rest;

import client_back1.demo.entity.*;
import client_back1.demo.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AdminController {
    private final ProviderService providerService;
    private final AdminService adminService;
    private final ContactService contactService;
    private final ProductService productService;
    private final SpecialityService specialityService;
    private final ImageService imageService;
    private final ImageUploadController imageUploadController;

    public AdminController(ProductService productService, ProviderService providerService, AdminService adminService, ContactService contactService, SpecialityService specialityService, ImageService imageService, ImageUploadController imageUploadController) {
        this.providerService = providerService;
        this.productService = productService;
        this.adminService = adminService;
        this.contactService = contactService;
        this.specialityService = specialityService;

        this.imageService = imageService;
        this.imageUploadController = imageUploadController;
    }

   /////////////////////////////// Admin///////////////////////////////////////

    //blocked and no blocked
    @GetMapping("/admin/Allproviders")
    public List<Provider> findAll() {

        return adminService.findAll();
    }
    //all not blocked
    @GetMapping("/admin/Not-blocked-providers")
    public List<Provider> findAllnotblocked() {

        return adminService.findAllnotblocked();
    }
    //all  blocked
    @GetMapping("/admin/blocked-providers")
    public List<Provider> findAllblocked() {

        return adminService.findAllblocked();
    }
    //all new providers
    @GetMapping("/admin/new-providers")
    public List<Provider> newproviders() {

        return adminService.newproviders();
    }
    //getprovider blocked and not
    @GetMapping("/admin/provider/{productId}")
    public Provider getProvider(@PathVariable("productId") long productId) {

        Provider productInfo = adminService.getProviderAd(productId);

        return productInfo;
    }
    //offprovider
    @DeleteMapping("/admin/block-provider/{productId}")
    public void offprovider(@PathVariable("productId") long productId) {
        System.out.println("block-providers"+productId);
        adminService.offprovider(productId);
      //  return productId;
    }
    //onProvider
    @DeleteMapping("/admin/deblock-provider/{productId}")
    public  void onProvider(@PathVariable("productId") long productId) {
        System.out.println("deblock-providers"+productId);
        adminService.onProvider(productId);
    //return productId;
    }
    /////// all speciality provider(blocked..)
    @GetMapping("/admin/All-specialities/{id}")
    public List<Speciality> specialitiesproAd(@PathVariable("id") long id){
        return adminService.speprovidAd(id);
    }
    /////// all products provider(blocked..)
    @GetMapping("/admin/All-products/{id}")
    public List<Product> productsproAd(@PathVariable("id") long id){
        return adminService.ProductprovidAd(id);
    }
    //delete provider -1 not in db
    @DeleteMapping("/admin/delete_provider/{id}")
    public long delete(@PathVariable("id") long productId) {
        adminService.delete(productId);
        System.out.println("delete");
        return productId;
    }
    /////////////////admin-contact////////
    @GetMapping("/admin/All-Contact")
    public List<Contact> getALLcontact(){
        return contactService.getALLcontact();
    }

    //delete from db in all
    @DeleteMapping("/admin/contact/{id}/delete")
    public ResponseEntity delete(@PathVariable("id") int Id) {
        contactService.delete(Id);
        return ResponseEntity.ok().build();
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
