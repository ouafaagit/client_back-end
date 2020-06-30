package client_back1.demo.rest;

import client_back1.demo.entity.Complaint;
import client_back1.demo.entity.Product;
import client_back1.demo.service.ComplaintService;
import client_back1.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ComplaintController {
    @Autowired
    ComplaintService complaintService;
    @Autowired
    ProductService productService;

    @PutMapping("/User/Complaints/getcomplaint")
    public Complaint getcomplaint(@PathVariable("id") long id){
        return complaintService.findOne(id);
    }

    //////Add
    //provider add product
    @PostMapping("/User/product/{id}/Complaint/new")
    public Complaint create(@PathVariable("id")long id, @Valid @RequestBody Complaint complaint) {

        //  p= productService.save(p);
        //  providerService.save(pro);
        //  providerService.save(pro);

        return complaintService.save(id,complaint);
    }
    ////////////////////////////provider//////////////////////////////:

    @GetMapping("/provider/{id}/All-Complaints")
    public List<Complaint> getALLcomplaintpr(@PathVariable("id") long id){
        return complaintService.getALLcomplaintpr(id);
    }

    ////////////////////////////Admin//////////////////////////////:

    @GetMapping("/admin/All-Complaints")
    public List<Complaint> getALLcomplaint(){
        return complaintService.getALLcomplaint();
    }

    //delete from db in all
    @DeleteMapping("/admin/complaint/{id}/delete")
    public ResponseEntity delete(@PathVariable("id") int Id) {
        complaintService.delete(Id);
        return ResponseEntity.ok().build();
    }
}
