package client_back1.demo.service;



import client_back1.demo.entity.Cart;
import client_back1.demo.entity.Doctor;
import client_back1.demo.entity.OrderMain;
import client_back1.demo.entity.ProductInOrder;

import java.util.Collection;


public interface CartService {
    Cart getCart(Doctor user);

    void mergeLocalCart(Collection<ProductInOrder> productInOrders, Doctor user);

    void delete(String itemId, Doctor user);

    void checkout(OrderMain user);
}
