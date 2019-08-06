package com.pp.microserviceorderservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.pp.microserviceorderservice.mapper.OrderMapper;
import com.pp.microserviceorderservice.po.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderMapper orderMapper;

    @GetMapping(path="/findOrders/{userid}")
    @HystrixCommand(fallbackMethod = "findOrderFallback")
    public List<Order> findOrder(@PathVariable("userid") Integer userid) {
        List<Order> orders = this.orderMapper.selectOrder(userid);
        return orders;
    }
    public List<Order> findOrderFallback(Integer userid) {
        List<Order> orders = new ArrayList<>();
        return orders;
    }

}

