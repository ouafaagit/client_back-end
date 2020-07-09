package client_back1.demo.rest;

import client_back1.demo.entity.*;
import client_back1.demo.form.ItemForm;
import client_back1.demo.repository.DoctorRepository;
import client_back1.demo.service.*;
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
import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class DoctorController {
    @Autowired
    ImageService imageService;
    DoctorService doctorService;
    ProductService productService;
    //QuotationService quotationService;

    public DoctorController(DoctorService doctorService,ProductService productService) {
        this.doctorService = doctorService;
        this.productService = productService;
    }

    ///////////////////user/////////////////
    //profil
    @PutMapping("/Doctor/Profil/update")
    public Doctor updateProfil(@Valid @RequestBody Doctor provider) {

        return this.doctorService.updateProfil(provider);
    }
    @GetMapping("/Doctor/Profil/{id}")
    public Doctor getProfile(@Valid @PathVariable("id") long id){
        System.out.println("doctor"+id);
        return this.doctorService.getProfil(id);
    }

    ///wishlist add
    @PostMapping("/Doctor/{id}/product/add-to-wishlist")
    public ResponseEntity Addwishlist(@PathVariable("id") long id,
                                        @Valid @RequestBody Product product) {
        Doctor doctor = doctorService.getProfil(id);
        product.nombrewish ++;
     //   doctor.addWishlist(product);
        productService.save(product);
        return ResponseEntity.ok(doctorService.save(doctor));
    }
    //delete
    @PostMapping("/Doctor/{id}/product/remove-to-wishlist")
    public ResponseEntity deletewishlist(@PathVariable("id") long id,
                                        @Valid @RequestBody Product product) {
        Doctor doctor = doctorService.getProfil(id);
        product.nombrewish --;
        productService.save(product);
      //  doctor.deleteWishlist(product);
        return ResponseEntity.ok(doctorService.save(doctor));
    }


    ///////////////////////////wislist////////////
    @GetMapping("/wishlist")
    public  List<ProductInOrder> getwish(Principal principal) {
        System.out.println("getwish principalmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
        System.out.println("getwishlist principal"+principal.getName());
        Doctor user = doctorService.findOne(principal.getName());
        System.out.println("*************************************"+user.getLastname());
        List<ProductInOrder>productInOrders=new ArrayList<>();
        Iterator<Product> it =user.getWishlist().iterator();
        while (it.hasNext())
        {
         ProductInOrder productInOrder=new ProductInOrder(it.next(),1);
            ImageModel  img= new ImageModel();
            img= imageService.findById(productInOrder.getProductIcon().getId());
            productInOrder.setProductIcon(img);
            productInOrders.add(productInOrder);
        }
        return productInOrders;
    }

    @PostMapping("/wishlist/add")
    public boolean addTowishlist(@RequestBody long id, Principal principal) {
        Product productInfo = productService.findOne(id);
        Doctor user = doctorService.findOne(principal.getName());
        productInfo.nombrewish++;
        user.getWishlist().add(productInfo);
        productInfo.getWishdoc().add(user);
        productService.save(productInfo);
        doctorService.save(user);
        return true;
    }
    @DeleteMapping("/wishlist/{itemId}")
    public void deleteItem(@PathVariable("itemId") String itemId, Principal principal) {
        Doctor user = doctorService.findOne(principal.getName());
        doctorService.deleteitem(itemId, user);
    }
    @PostMapping("/wishlist")
    public List<ProductInOrder> mergeCart(@RequestBody Collection<ProductInOrder> productInOrders, Principal principal) {

        return doctorService.mergeLocalwish(productInOrders, principal.getName());
    }

    ///////orderproduct//////
    // add
    @PostMapping("/Doctor/{id}/product/add-to-orderproduct")
    public ResponseEntity Addorderproduct(@PathVariable("id") long id,
                                      @Valid @RequestBody Product product) {
        Doctor doctor = doctorService.getProfil(id);
        doctor.addOrderproduct(product);

        return ResponseEntity.ok(doctorService.save(doctor));
    }
    //delete
    @PostMapping("/Doctor/{id}/product/remove-to-orderproduct")
    public ResponseEntity deleteorderproduct(@PathVariable("id") long id,
                                         @Valid @RequestBody Product product) {
        Doctor doctor = doctorService.getProfil(id);
        doctor.deleteOrderproduct(product);
        return ResponseEntity.ok(doctorService.save(doctor));
    }

    /////// cart//////////
    // /new
   /* @PostMapping("/Doctor/{id}/Product/Devis")
    public void newQuotation(@PathVariable("id") long id,
                                          @Valid @RequestBody Quotation Quotation) {
        doctorService.newQuotation(id,Quotation);
    }*/
    // /getquotation
    @PostMapping("/Doctor/{id}/Product/getDevis")
    public ResponseEntity getQuotation(@PathVariable("id") long id) {
        Doctor doctor = doctorService.getProfil(id);
        return ResponseEntity.ok(doctor.getCart());
    }

    ////////////////////// search ///////////////////////////////////////


   /////////////////////////////// Admin//////////////////////////
    @GetMapping("/admin/AllDoctors")
    public Page<Doctor> findAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                @RequestParam(value = "size", defaultValue = "10") Integer size) {
        PageRequest request = PageRequest.of(page - 1, size);

        return doctorService.findAll(request);
    }


}
