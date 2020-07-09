package client_back1.demo.rest;

import client_back1.demo.entity.*;
import client_back1.demo.service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public Page<Provider> findAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "size", defaultValue = "10") Integer size) {
        PageRequest request = PageRequest.of(page - 1, size);
        return adminService.findAll(request);
    }
    //all not blocked
    @GetMapping("/admin/Not-blocked-providers")
    public Page<Provider> findAllnotblocked(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        PageRequest request = PageRequest.of(page - 1, size);
        return adminService.findAllnotblocked(request);
    }
    //all  blocked
    @GetMapping("/admin/blocked-providers")
    public Page<Provider> findAllblocked(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        PageRequest request = PageRequest.of(page - 1, size);
        return adminService.findAllblocked(request);
    }
    //all new providers
    @GetMapping("/admin/new-providers")
    public Page<Provider> newproviders(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        PageRequest request = PageRequest.of(page - 1, size);
        return adminService.newproviders(request);
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
    public Page<Product> productsproAd(@PathVariable("id") long id,@RequestParam(value = "page", defaultValue = "1") Integer page,
                                       @RequestParam(value = "size", defaultValue = "10") Integer size){
        PageRequest request = PageRequest.of(page - 1, size);
        return adminService.ProductprovidAd(id,request);

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


}
