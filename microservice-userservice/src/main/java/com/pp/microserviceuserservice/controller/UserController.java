package com.pp.microserviceuserservice.controller;

import com.pp.microserviceuserservice.mapper.UserMapper;
import com.pp.microserviceuserservice.po.User;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
//import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private UserMapper userMapper;
    @Value("${ORDERSERVICEURL}")
    private String ORDERSERVICEURL;

    @GetMapping(path="/findOrders/{username}")
    public String getOrderByUsername(@PathVariable("username") String username) {
        User user = this.userMapper.selectUser(username);
//        ResponseEntity<List<Order>> rateResponse =
//                restTemplate.exchange(ORDERSERVICEURL
//                                + "/order/findOrders/" + user.getId(),
//                        HttpMethod.GET, null,
//                        new ParameterizedTypeReference<List<Order>>() {});
//        List<Order> orders = rateResponse.getBody();
        String url = ORDERSERVICEURL + "/order/findOrders/" + user.getId();
        return restTemplate.getForObject(url, String.class);
    }

}
