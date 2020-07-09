package client_back1.demo.rest;

import client_back1.demo.entity.ImageModel;
import client_back1.demo.entity.OrderMain;
import client_back1.demo.entity.Product;
import client_back1.demo.entity.ProductInOrder;
import client_back1.demo.service.DoctorService;
import client_back1.demo.service.ImageService;
import client_back1.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


@RestController
@CrossOrigin
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    DoctorService userService;
    @Autowired
    ImageService imageService;
    @GetMapping("/order/{id}")
    public Page<OrderMain> orderList(@PathVariable("id") long orderId,@RequestParam(value = "page", defaultValue = "1") Integer page,
                                     @RequestParam(value = "size", defaultValue = "10") Integer size,
                                     Authentication authentication) {
        PageRequest request = PageRequest.of(page - 1, size);
        Page<OrderMain> orderPage;
      /*  if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CUSTOMER"))) {
            orderPage = orderService.findByBuyerEmail(authentication.getName(), request);
        } else {*/
            orderPage = orderService.findAll(orderId,request);

        return orderPage;
    }

    @PatchMapping("/order/cancel/{id}")
    public ResponseEntity<OrderMain> cancel(@PathVariable("id") long orderId) {
        OrderMain orderMain = orderService.findOne(orderId);

        return ResponseEntity.ok(orderService.cancel(orderId));
    }

    @PatchMapping("/order/finish/{id}")
    public ResponseEntity<OrderMain> finish(@PathVariable("id") long orderId) {

        return ResponseEntity.ok(orderService.finish(orderId));
    }

    @GetMapping("/order/{id}/provider/{idprovider}")
    public List<ProductInOrder> show(@PathVariable("id") long orderId, @PathVariable("idprovider") long idprovider) {
System.out.println("show order"+orderId+idprovider);
        List<ProductInOrder> orderMain = orderService.findByprovider(orderId,idprovider);
        Iterator<ProductInOrder> it =orderMain.iterator();
        List<ProductInOrder>productInOrders=new ArrayList<>();
        while (it.hasNext())
        {
            ProductInOrder productInOrder=it.next();
            ImageModel img= new ImageModel();
            img= imageService.findById(productInOrder.getProductIcon().getId());
            productInOrder.setProductIcon(img);
            productInOrders.add(productInOrder);
        }
        return productInOrders;
    }

   /* @GetMapping("/order/{id}")
    public ResponseEntity show(@PathVariable("id") Long orderId, Authentication authentication) {
        boolean isCustomer = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
        OrderMain orderMain = orderService.findOne(orderId);
        if (isCustomer && !authentication.getName().equals(orderMain.getBuyerEmail())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Collection<ProductInOrder> items = orderMain.getProducts();
        return ResponseEntity.ok(orderMain);
    }*/
}
