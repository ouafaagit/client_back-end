package client_back1.demo.rest;

import client_back1.demo.entity.*;
import client_back1.demo.repository.DoctorRepository;
import client_back1.demo.service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class DoctorController {

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
        doctor.addWishlist(product);
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
        doctor.deleteWishlist(product);
        return ResponseEntity.ok(doctorService.save(doctor));
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
    public List<Doctor> findAll() {
        return doctorService.findAll();
    }


}
