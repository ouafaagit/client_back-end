package client_back1.demo.rest;

import client_back1.demo.entity.*;
import client_back1.demo.entity.Provider;
import client_back1.demo.service.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
//@RequestMapping("/api")
public class SpecialityController {
    @Autowired
    SpecialityService specialityService;

////////////////////user///////////////////////////////
    //getSpeciality
    @GetMapping("/api/specialities/Edit/{Id}")
    public Speciality showOne(@PathVariable("Id") long id) {

        return specialityService.findOne(id);
    }
////all speciality
    @GetMapping("/api/specialities")
    public List<Speciality>  findAllsp() {
        System.out.println("ccccontroller");

        return specialityService.findAllsp();
    }
   /* //// speciality for section home
    @GetMapping("/api/specialities")
    public List<Speciality>  findtreesp() {
        System.out.println("ccccontroller");

        return specialityService.findtreesp();
    }*/
   /* @GetMapping("/specialities")
    public List<String>  findAll() {

        return specialityService.findAll();
    }*/
    @GetMapping("/allspecialities")
    public Page<Speciality> findAllnblk(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                    @RequestParam(value = "size", defaultValue = "3") Integer size) {
        PageRequest request = PageRequest.of(page - 1, size);
        return specialityService.findAllnblk(request);
    }
///////////////////////////////admin///////////////////////////////
    @PostMapping("/api/admin/speciality/new")
    public ResponseEntity create(@Valid @RequestBody Speciality speciality,
                                 BindingResult bindingResult) {
        //findOne active
        Speciality ProviderIdExists = specialityService.findOne(speciality.getId());
        if (ProviderIdExists != null) {
            bindingResult
                    .rejectValue("Id", "error.speciality",
                            "There is already a speciality with the code provided");
        }
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult);
        }
        return ResponseEntity.ok(specialityService.save(speciality));
    }
    //Update speciality
    @PutMapping("/api/admin/speciality/Update")
    public ResponseEntity Update(@Valid @RequestBody Speciality speciality) {
        //findOne active
       // Speciality specialit = specialityService.findOne(speciality.getId());
        return ResponseEntity.ok(specialityService.save(speciality));
    }
    //getspeciality blocked and not
    @GetMapping("/api/admin/speciality/{id}")
    public Speciality getspeciality(@PathVariable("id") long ProviderId) {
        return specialityService.getspdetail(ProviderId);
    }
    //getspeciality blocked and not
    @GetMapping("/api/admin/speciality/get/{id}")
    public Speciality getspecialityUp(@PathVariable("id") long ProviderId) {
        return specialityService.getspdetailUp(ProviderId);
    }

    //blocked and no blocked
    @GetMapping("/api/admin/Allspecilities")
    public List<Speciality> findAll() {
        return specialityService.findAll();
    }
    /*//blocked and no blocked
    @GetMapping("/admin/Allspecilities")
    public Page<Speciality> findAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "size", defaultValue = "3") Integer size) {
        PageRequest request = PageRequest.of(page - 1, size);
        return specialityService.findAll(request);
    }*/
    //offSpeciality
    @GetMapping("/api/admin/Allspecilities/block-specialitises")
    public  List<Speciality> offSpeciality(@PathVariable("ProviderId") long ProviderId) {
        specialityService.offSpeciality(ProviderId);
        return findAll();
    }
    //onSpeciality
    @GetMapping("/api/admin/Allspecilities/deblock-specialitises")
    public List<Speciality> onSpeciality(@PathVariable("ProviderId") long ProviderId) {
        specialityService.onSpeciality(ProviderId);
        return findAll();
    }
    //delete from db in all
    @DeleteMapping("/api/admin/{id}/delete")
    public ResponseEntity delete(@PathVariable("id") int ProviderId) {
        specialityService.delete(ProviderId);
        return ResponseEntity.ok().build();
    }
///////////////// filter search for admin by speciality ////////////////////////////////////
    //Providers by speciality
    @GetMapping("/admin/specialities/{specialityName}/Providers")
    public Page<Provider> findAllProviders(@PathVariable("specialityName") String specialityname, @RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "size", defaultValue = "3") Integer size) {
        PageRequest request = PageRequest.of(page - 1, size);
        Speciality speciality=specialityService.findName(specialityname);

        //return specialityService.findAllInSpeciality(specialityname,request);
        return (Page<Provider>) speciality.getProviders();
    }

    //Doctors by speciality
    @GetMapping("/admin/specialities/{specialityName}/Doctors")
    public Page<Doctor> findAllDoctors(@PathVariable("specialityName") String specialityname, @RequestParam(value = "page", defaultValue = "1") Integer page,
                                       @RequestParam(value = "size", defaultValue = "3") Integer size) {
        PageRequest request = PageRequest.of(page - 1, size);
        Speciality speciality=specialityService.findName(specialityname);

        //return specialityService.findAllInSpeciality(specialityname,request);
        return (Page<Doctor>) speciality.getDoctors();
    }
    //products by speciality
    @GetMapping("/admin/specialities/{specialityName}/products")
    public Page<Product> findAllproviders(@PathVariable("specialityName") String specialityname, @RequestParam(value = "page", defaultValue = "1") Integer page,
                                       @RequestParam(value = "size", defaultValue = "3") Integer size) {
        PageRequest request = PageRequest.of(page - 1, size);
        Speciality speciality=specialityService.findName(specialityname);

        //return specialityService.findAllInSpeciality(specialityname,request);
        return (Page<Product>) speciality.getProducts();
    }


}
