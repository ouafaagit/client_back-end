package client_back1.demo.service.impl;

import client_back1.demo.entity.OrderMain;
import client_back1.demo.entity.Product;
import client_back1.demo.entity.ProductInOrder;
import client_back1.demo.enums.OrderStatusEnum;
import client_back1.demo.enums.ResultEnum;
import client_back1.demo.exception.MyException;
import client_back1.demo.repository.DoctorRepository;
import client_back1.demo.repository.OrderRepository;
import client_back1.demo.repository.ProductInOrderRepository;
import client_back1.demo.repository.ProductRepository;
import client_back1.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    DoctorRepository userRepository;
    @Autowired
    ProductRepository productInfoRepository;
    @Autowired
    ProductInOrderRepository productInOrderRepository;

    @Override
    public Page<OrderMain> findAll( long id,Pageable pageable) {
        return orderRepository.findDistinctByProductsProviderId(id,pageable);
    }

    @Override
    public Page<OrderMain> findByStatus(Integer status, Pageable pageable) {
        return orderRepository.findAllByOrderStatusOrderByCreateTimeDesc(status, pageable);
    }

    @Override
    public Page<OrderMain> findByBuyerEmail(String email, Pageable pageable) {
        return orderRepository.findAllByBuyerEmailOrderByOrderStatusAscCreateTimeDesc(email, pageable);
    }

    @Override
    public Page<OrderMain> findByBuyerPhone(String phone, Pageable pageable) {
        return orderRepository.findAllByBuyerPhoneOrderByOrderStatusAscCreateTimeDesc(phone, pageable);
    }

    @Override
    public OrderMain findOne(long orderId) {
        OrderMain orderMain = orderRepository.findByOrderId(orderId);
        if(orderMain == null) {
            throw new MyException(ResultEnum.ORDER_NOT_FOUND);
        }
        return orderMain;
    }
    @Override
    public List<ProductInOrder> findByprovider(long orderId,long idprovider) {
        List<ProductInOrder> orderMain = productInOrderRepository.findAllByOrderMain_OrderIdAndProviderId(orderId,idprovider);
        if(orderMain == null) {
            //findAllByProductIdEqualsAndAndProductId
            throw new MyException(ResultEnum.ORDER_NOT_FOUND);
        }
        return orderMain;
    }

    @Override
    @Transactional
    public OrderMain finish(long orderId) {
        OrderMain orderMain = findOne(orderId);
        if(!orderMain.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new MyException(ResultEnum.ORDER_STATUS_ERROR);
        }

        orderMain.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderRepository.save(orderMain);
        return orderRepository.findByOrderId(orderId);
    }

    @Override
    @Transactional
    public OrderMain cancel(long orderId) {
        OrderMain orderMain = findOne(orderId);
        if(!orderMain.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new MyException(ResultEnum.ORDER_STATUS_ERROR);
        }

        orderMain.setOrderStatus(OrderStatusEnum.CANCELED.getCode());
        orderRepository.save(orderMain);


        return orderRepository.findByOrderId(orderId);

    }
}
