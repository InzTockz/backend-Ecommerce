package com.proyecto.ecommerce.backend.infrastructure.adapter;

import com.proyecto.ecommerce.backend.domain.model.Order;
import com.proyecto.ecommerce.backend.domain.model.OrderState;
import com.proyecto.ecommerce.backend.domain.model.User;
import com.proyecto.ecommerce.backend.domain.port.IOrderRepository;
import com.proyecto.ecommerce.backend.infrastructure.entity.OrderEntity;
import com.proyecto.ecommerce.backend.infrastructure.entity.UserEntity;
import com.proyecto.ecommerce.backend.infrastructure.mapper.OrderMapper;
import org.springframework.stereotype.Repository;

@Repository
public class OrderCrudRepositoryImpl implements IOrderRepository {

    private final IOrderCrudRepository iOrderCrudRepository;
    private final OrderMapper orderMapper;

    public OrderCrudRepositoryImpl(IOrderCrudRepository iOrderCrudRepository, OrderMapper orderMapper) {
        this.iOrderCrudRepository = iOrderCrudRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = this.orderMapper.toOrderEntity(order);
        orderEntity.getOrderProducts().forEach(
                orderProductEntity -> orderProductEntity.setOrderEntity(orderEntity)
        );
        return this.orderMapper.toOrder(this.iOrderCrudRepository.save(orderEntity));
    }

    @Override
    public Order findById(Integer id) {
        return this.orderMapper.toOrder(this.iOrderCrudRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Orden con id: " + id + " no encontrado.")
        ));
    }

    @Override
    public Iterable<Order> findAll() {
        return this.orderMapper.toOrderList(this.iOrderCrudRepository.findAll());
    }

    @Override
    public Iterable<Order> findByUserId(Integer userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        return this.orderMapper.toOrderList(this.iOrderCrudRepository.findByUserEntity(userEntity));
    }

    @Override
    public void updateStateById(Integer id, String state) {
        if(state.equals(OrderState.CANCELLED)){
            this.iOrderCrudRepository.updateStateById(id, OrderState.CANCELLED);
        } else {
            this.iOrderCrudRepository.updateStateById(id, OrderState.CONFIRMED);
        }
    }
}
