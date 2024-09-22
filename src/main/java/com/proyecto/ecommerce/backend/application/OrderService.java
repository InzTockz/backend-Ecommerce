package com.proyecto.ecommerce.backend.application;

import com.proyecto.ecommerce.backend.domain.model.Order;
import com.proyecto.ecommerce.backend.domain.port.IOrderRepository;
import org.aspectj.weaver.ast.Or;

public class OrderService {

    private final IOrderRepository iOrderRepository;

    public OrderService(IOrderRepository iOrderRepository) {
        this.iOrderRepository = iOrderRepository;
    }

    public Order save(Order order){
        return this.iOrderRepository.save(order);
    }

    public Iterable<Order> findall(){
        return this.iOrderRepository.findAll();
    }

    public Iterable<Order> findByUserId(Integer userId){
        return this.iOrderRepository.findByUserId(userId);
    }

    public void updateStateById(Integer id, String state){
        this.iOrderRepository.updateStateById(id, state);
    }

    public Order findById(Integer id){
        return this.iOrderRepository.findById(id);
    }
}
