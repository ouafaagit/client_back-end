package client_back1.demo.service.impl;


import client_back1.demo.entity.Doctor;
import client_back1.demo.entity.ProductInOrder;
import client_back1.demo.repository.ProductInOrderRepository;
import client_back1.demo.service.ProductInOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicReference;

@Service
public class ProductInOrderServiceImpl implements ProductInOrderService {

    @Autowired
    ProductInOrderRepository productInOrderRepository;

    @Override
    @Transactional
    public void update(String itemId, Integer quantity, Doctor user) {
        var op = user.getCart().getProducts().stream().filter(e -> itemId.equals(e.getProductId())).findFirst();
        op.ifPresent(productInOrder -> {
            productInOrder.setCount(quantity);
            System.out.println(" update");
            productInOrderRepository.save(productInOrder);
            System.out.println(" update1"+productInOrder.getCount());
        });

    }

    @Override
    public ProductInOrder findOne(String itemId, Doctor user) {
        var op = user.getCart().getProducts().stream().filter(e -> itemId.equals(e.getProductId())).findFirst();
        AtomicReference<ProductInOrder> res = new AtomicReference<>();
        op.ifPresent(res::set);
        return res.get();
    }
}
