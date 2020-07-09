package client_back1.demo.rest;

import client_back1.demo.entity.*;
import client_back1.demo.form.ItemForm;
import client_back1.demo.service.ComplaintService;
import client_back1.demo.service.ProductService;
import client_back1.demo.service.SpecialityService;
import client_back1.demo.vo.request.cardproduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/product")
public class ProductController {
  //  @Autowired
    //SpecialityService specialityService;
    @Autowired
    ProductService productService;
    @Autowired
    ComplaintService complaintService;

//////////////////////////////////all user -for sale///////////////
////for sale
//// for user -provider
    @GetMapping("/show/{productId}")
    public Product showOne(@PathVariable("productId") long productId)
    {
        return productService.getproductU(productId);

    }
    //// for home
    @GetMapping("/related-product/{spcialityid}")
    public List<cardproduct> relatedprod(@PathVariable("spcialityid") long spcialityid )
    { return productService.relatedprod(spcialityid);

    }

    //// for user -provider
    @GetMapping("/quickview/{productId}")
    public Product quickview(@PathVariable("productId") long productId)
    {
        return productService.quickview(productId);

    }
    //// for home
    //no blocked
    @GetMapping("/Allproducts")
    public Page<cardproduct> findAllproducts(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                             @RequestParam(value = "size", defaultValue = "18")Integer size) {
        PageRequest request = PageRequest.of(page - 1, size);

        return productService.findAllproducts(request);
    }
    //// for home
    //no blocked
    @GetMapping("/Allproducts/{id}")
    public Page<cardproduct> findAllbysp(@PathVariable("id") long id,@RequestParam(value = "page", defaultValue = "1") Integer page,
                                         @RequestParam(value = "size", defaultValue = "18") Integer size) {
        PageRequest request = PageRequest.of(page - 1, size);
        //return productService.findAllbysp(id);
        List<cardproduct>cardproducts=productService.findAllbysp(id,request);
System.out.println(" :::findAllbysp"+cardproducts.size());
        return new PageImpl<>(cardproducts, request, cardproducts.size());
    }
    //// for home new products
    //no blocked
    @GetMapping("/Allnewprod")
    public Page<cardproduct> Allnewprod(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                        @RequestParam(value = "size", defaultValue = "18") Integer size) {
        PageRequest request = PageRequest.of(page - 1, size);
        List<cardproduct>cardproducts=productService.Allnewprod(request);

         return new PageImpl<>(cardproducts, request, cardproducts.size());
    }
    @GetMapping("/Diagnostic-new")
    public List<cardproduct> Diagnostinew()
    { return productService.Diagnostinew();

    }
    //// for home
    @GetMapping("/Diagnostic-autre")
    public  List<cardproduct> Diagnostiautre()
    { return productService.Diagnostiautre();
    }
    //// for home
    @GetMapping("/Diagnostic-vendu")
    public  List<cardproduct> Diagnostivendu()
    { return productService.Diagnostivendu();
    }
    //// for home
    @GetMapping("/logiciel-new")
    public List<cardproduct> logicienew()
    { return productService.logicienew();

    }
    //// for home
    @GetMapping("/logiciel-vendu")
    public List<cardproduct> logicievendu()
    { return productService.logicievendu();

    }
    //// for home
    @GetMapping("/logiciel-autre")
    public List<cardproduct> logicieautre()
    { return productService.logicieautre();

    }
    //// for home
    @GetMapping("/tendence-new")
    public List<cardproduct> tendancnew()
    { return productService.tendancnew();

    }
    @GetMapping("/All-products")
    public Page<Product> findUpAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "size", defaultValue = "3") Integer size) {
        PageRequest request = PageRequest.of(page - 1, size);
        return productService.findUpAll(request);
    }
//////////// for provider update////
@GetMapping("/get/{productId}")
public Product getUpProduct(@PathVariable("productId") long productId)
{
    return productService.getproductUp(productId);

}
    //getproduct for sale
/*    @GetMapping("/show/{productId}")
    public Product showOne(@PathVariable("productId") long productId) {
        return productService.getproductU(productId);
    }*/
    //getproducts by speciality name
    @GetMapping("/speciality/{name}/getproducts")
    public Page<Product> getproducts_sp(@PathVariable("name") String name,@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "size", defaultValue = "3") Integer size) {
        PageRequest request = PageRequest.of(page - 1, size);
        return productService.getproducts_sp(name,request);
    }
    //Doctoradd complaint
 /*   @PostMapping("/Complaint/product/{id}")
    public ResponseEntity newcomplaint(@PathVariable("id")long id,
                                       @Valid @RequestBody Complaint cp) {
        Product p = productService.findOne(id);
        p.getComplaint().add(cp);
        cp.setProduct(p);
        //  p= productService.save(p);
        //  providerService.save(pro);
        //  providerService.save(pro);

        return ResponseEntity.ok(complaintService.save(cp));
    }*/

    ////////////////////////admin////////////
    //blocked and no blocked
    @GetMapping("/admin/Allproducts")
    public Page<Product> findAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "size", defaultValue = "18")Integer size) {
        PageRequest request = PageRequest.of(page - 1, size);

       List<Product>  l= productService.findAll(request);
        return  new PageImpl<>(l, request, l.size());
    }
    //getproduct blocked and not
    @GetMapping("/admin/product/{productId}")
    public Product getproduct(@PathVariable("productId") long productId) {

        Product productInfo = productService.getproductAd(productId);

        return productInfo;
    }
    //offSale
    @DeleteMapping("/admin/block-product/{productId}")
    public  void offSale(@PathVariable("productId") long productId) {
        productService.offSale(productId);

    }
    //onSale
    @DeleteMapping("/admin/deblock-product/{productId}")
    public  void onSale(@PathVariable("productId") long productId) {
        productService.onSale(productId);

    }
//delete from db in all
    @DeleteMapping("/delete_product/{id}")
    public long delete(@PathVariable("id") int productId) {
        System.out.println("delete"+productId);
        productService.delete(productId);
        return productId;
    }



}
