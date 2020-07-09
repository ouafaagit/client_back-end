package client_back1.demo.repository;


import client_back1.demo.entity.ProductInOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, Long> {
   List<ProductInOrder> findAllByOrderMain_OrderIdAndProviderId(long orderId, long idprovider);
}
