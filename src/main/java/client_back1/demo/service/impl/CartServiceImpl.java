package client_back1.demo.service.impl;


import client_back1.demo.entity.*;
import client_back1.demo.repository.CartRepository;
import client_back1.demo.repository.DoctorRepository;
import client_back1.demo.repository.OrderRepository;
import client_back1.demo.repository.ProductInOrderRepository;
import client_back1.demo.service.CartService;
import client_back1.demo.service.DoctorService;
import client_back1.demo.service.ImageService;
import client_back1.demo.service.ProductService;
import com.sun.xml.bind.v2.runtime.reflect.Lister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static client_back1.demo.service.ImageService.compressBytes;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    ProductService productService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    DoctorRepository userRepository;

    @Autowired
    ProductInOrderRepository productInOrderRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    DoctorService userService;
    @Autowired
    ImageService imageService;
    @Override
    public Cart getCart(Doctor user) {
        Cart cart=user.getCart();
        System.out.println("getCart"+cart.toString());
Iterator<ProductInOrder> it =cart.getProducts().iterator();
        Cart cart1=new Cart(user);
        cart1.setCartId(cart.getCartId());
while (it.hasNext())
{
    ProductInOrder productInOrder=it.next();
    ImageModel  img= new ImageModel();
    img= imageService.findById(productInOrder.getProductIcon().getId());

    productInOrder.setProductIcon(img);
    cart1.getProducts().add(productInOrder);
}
        System.out.println("getCart1 :"+cart1.toString());
   //     Cart cart1=new Cart(user);
      //  cart1.setCartId(cart.getCartId());
      //  cart1.setProducts(cart.getCartId());
        return  cart1;
    }

    @Override
    @Transactional
    public void mergeLocalCart(Collection<ProductInOrder> productInOrders, Doctor user) {
        Cart finalCart = user.getCart();

        System.out.println(" ************user"+ user.getEmail());
        //System.out.println(" ************user"+ user.getEmail()+" carte :"+user.getCart().getCartId()+" carte user:"+user.getCart().getUser().getId());
        System.out.println(" ************finalCart"+ finalCart.getCartId()+" user"+finalCart.getUser().getId());
        productInOrders.forEach(productInOrder -> {
            System.out.println("productInOrder"+ productInOrder.getProductName());
            Set<Product> wishlist=user.getWishlist();
            Optional<Product> pro = wishlist.stream().filter(e -> productInOrder.getProductId().equals(" "+e.getId())).findFirst();
            if (pro.isPresent())
         { System.out.println(" ***wishlist.contains(product) :"+pro.get());
             user.getWishlist().remove(pro.get());
             userRepository.save(user);
             pro.get().getWishdoc().remove(user);
             productService.save(pro.get());
         }
                Set<ProductInOrder> set = finalCart.getProducts();
                Optional<ProductInOrder> old = set.stream().filter(e -> productInOrder.getProductId().equals(e.getProductId())).findFirst();
                ProductInOrder prod;
                if (old.isPresent()) {
                    prod = old.get();
                    prod.setCount(productInOrder.getCount() + prod.getCount());
                    System.out.println(" prod.setCount" + prod.getCount());
                } else {
                    System.out.println("mergeLocalCart");
                    prod = productInOrder;
                //    prod.setProductIcon(compressBytes(productInOrder.getProductIcon()));
                    System.out.println("prod test" + prod.toString());
                  //  prod.setProductIcon(productInOrder.getProductIcon());
                      prod.setCart(finalCart);
                  //  ProductInOrder p= productInOrderRepository.save(prod);
                   // productInOrderRepository.save(new ProductInOrder() );
                  //  System.out.println("prod testtttttttttt" + p.toString());

                     finalCart.getProducts().add(prod);
                //    cartRepository.save(finalCart);
                  // System.out.println("finalCart" + finalCart.toString());
                }
                System.out.println("mergeLocalCart2");
                System.out.println("prod"+prod.toString());
                ///////////
                ProductInOrder productInOrder1=productInOrderRepository.save(prod);
                System.out.println(" productInOrder1"+ productInOrder1.toString());



        });
        System.out.println(" affiche "+ finalCart.toString());

        Cart cart= cartRepository.save(finalCart);
        System.out.println(" affiche cart"+ cart.toString());

    }

    @Override
    //@Transactional
    public void delete(String itemId, Doctor user) {
        var op = user.getCart().getProducts().stream().filter(e -> itemId.equals(e.getProductId())).findFirst();
        op.ifPresent(productInOrder -> {
           productInOrder.setCart(null);
         //   productInOrderRepository.deleteById(productInOrder.getId());
            productInOrderRepository.delete(productInOrder);
        });
    }



    @Override
    //@Transactional
    public void checkout(OrderMain order) {
        // Creat an order
       OrderMain order1 = new OrderMain(order);
        orderRepository.save(order1);
Doctor user=userService.getProfil(order.getUserId());
System.out.println(" Doctor checkout :"+user.getEmail());
        // clear cart's foreign key & set order's foreign key& decrease stock
        user.getCart().getProducts().forEach(productInOrder -> {
            productInOrder.setCart(null);
            productInOrder.setOrderMain(order1);
            productInOrderRepository.save(productInOrder);
        });

    }
}
