package com.example.spring_data.service;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Test {

    private final CartService cartService;
    private final CustomerService customerService;
    private final ProductService productService;

    public Test(CartService cartService, CustomerService customerService, ProductService productService) {
        this.cartService = cartService;
        this.customerService = customerService;
        this.productService = productService;
    }

    @PostConstruct
    public void test() {
        System.out.println("\nИщем всех покупателей по ИД продукта\n");
        productService.findAllCustomersByProductId(1L).forEach(x -> System.out.println(x.getName()));
        System.out.println("\nПо ИД покупателя ищем все продукты, приобретённые им\n");
        customerService.findAllProductsByCustomerId(2L).forEach(x -> System.out.println(x.getTitle()));
    }
}
