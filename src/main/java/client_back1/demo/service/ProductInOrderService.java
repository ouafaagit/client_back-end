package client_back1.demo.service;

import client_back1.demo.entity.Doctor;
import client_back1.demo.entity.ProductInOrder;

public interface ProductInOrderService {
    void update(String itemId, Integer quantity, Doctor user);
    ProductInOrder findOne(String itemId, Doctor user);
}
