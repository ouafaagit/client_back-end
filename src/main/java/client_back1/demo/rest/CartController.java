package client_back1.demo.rest;


import client_back1.demo.entity.Cart;
import client_back1.demo.entity.Doctor;
import client_back1.demo.entity.OrderMain;
import client_back1.demo.entity.ProductInOrder;
import client_back1.demo.form.ItemForm;
import client_back1.demo.repository.ProductInOrderRepository;
import client_back1.demo.service.CartService;
import client_back1.demo.service.DoctorService;
import client_back1.demo.service.ProductInOrderService;
import client_back1.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;

@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    DoctorService userService;
    @Autowired
    ProductService productService;
    @Autowired
    ProductInOrderService productInOrderService;
    @Autowired
    ProductInOrderRepository productInOrderRepository;

    @PostMapping("")
    public ResponseEntity<Cart> mergeCart(@RequestBody Collection<ProductInOrder> productInOrders, Principal principal) {
        Doctor user = userService.findOne(principal.getName());
        try {
            cartService.mergeLocalCart(productInOrders, user);
        } catch (Exception e) {
            ResponseEntity.badRequest().body("Merge Cart Failed");
        }
        return ResponseEntity.ok(cartService.getCart(user));
    }

    @GetMapping("")
    public Cart getCart(Principal principal) {
        System.out.println("getCart principalmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
        System.out.println("getCart principal"+principal.getName());
        Doctor user = userService.findOne(principal.getName());
        System.out.println("*************************************"+user.getLastname());
        return cartService.getCart(user);
    }


    @PostMapping("/add")
    public boolean addToCart(@RequestBody ItemForm form, Principal principal) {
        var productInfo = productService.findOne(form.getProductId());
        try {
            mergeCart(Collections.singleton(new ProductInOrder(productInfo, form.getQuantity())), principal);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @PutMapping("/{itemId}")
    public ProductInOrder modifyItem(@PathVariable("itemId") String itemId, @RequestBody Integer quantity, Principal principal) {
        Doctor user = userService.findOne(principal.getName());
         productInOrderService.update(itemId, quantity, user);
        return productInOrderService.findOne(itemId, user);
    }

    @DeleteMapping("/{itemId}")
    public void deleteItem(@PathVariable("itemId") String itemId, Principal principal) {
        Doctor user = userService.findOne(principal.getName());
         cartService.delete(itemId, user);
         // flush memory into DB
    }


    @PostMapping("/checkout")
    public ResponseEntity checkout( @RequestBody OrderMain user) {
      //  Doctor user = userService.findOne(principal.getName());// Email as username
        System.out.println("checkout "+user.getUserId());
        cartService.checkout(user);
        return ResponseEntity.ok(null);
    }
   /* public ResponseEntity checkout(Principal principal) {
        Doctor user = userService.findOne(principal.getName());// Email as username
        cartService.checkout(user);
        return ResponseEntity.ok(null);
    }*/


}
