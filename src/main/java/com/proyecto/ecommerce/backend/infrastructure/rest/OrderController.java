package com.proyecto.ecommerce.backend.infrastructure.rest;

import com.proyecto.ecommerce.backend.application.OrderService;
import com.proyecto.ecommerce.backend.domain.model.Order;
import com.proyecto.ecommerce.backend.domain.model.OrderState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/orders")
@Slf4j
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> save(@RequestBody Order order){
        if(order.getOrderState().toString().equals(OrderState.CANCELLED.toString())){
            order.setOrderState(OrderState.CANCELLED);
        } else {
            order.setOrderState(OrderState.CONFIRMED);
        }
        return new ResponseEntity<>(orderService.save(order), HttpStatus.CREATED);
    }

    @PostMapping("/update/state/order")
    public ResponseEntity updateStateById(@RequestParam Integer id, @RequestParam String state){
        orderService.updateStateById(id, state);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Iterable<Order>> findAll(){
        return ResponseEntity.ok().body(orderService.findall());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(this.orderService.findById(id));
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<Iterable<Order>> findByUserId(@PathVariable Integer userId){
        return ResponseEntity.ok().body(this.orderService.findByUserId(userId));
    }
}
