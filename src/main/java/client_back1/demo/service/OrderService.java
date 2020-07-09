package client_back1.demo.service;

import client_back1.demo.entity.OrderMain;
import client_back1.demo.entity.ProductInOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderService {
    //Page<OrderMain> findAll(Pageable pageable);

    Page<OrderMain> findAll(long id, Pageable pageable);

    Page<OrderMain> findByStatus(Integer status, Pageable pageable);

    Page<OrderMain> findByBuyerEmail(String email, Pageable pageable);

    Page<OrderMain> findByBuyerPhone(String phone, Pageable pageable);

    OrderMain findOne(long orderId);



    @Transactional
    OrderMain finish(long orderId);


    List<ProductInOrder> findByprovider(long orderId, long idprovider);

    @Transactional
    OrderMain cancel(long orderId);
}
