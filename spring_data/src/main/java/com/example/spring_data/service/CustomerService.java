package com.example.spring_data.service;

import com.example.spring_data.model.entity.Cart;
import com.example.spring_data.model.entity.Customer;
import com.example.spring_data.model.entity.Product;
import com.example.spring_data.model.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /** Метод, возвращающий все продукты, приобретённые покупателем по его ИД
     *
     * @param id - ИД покупателя, товары которого нужно найти
     * @return - лист со всеми покупками заданного покупателя
     */
    @Transactional
    public List<Product> findAllProductsByCustomerId(Long id) {
        // Получаем Optional-объект заданного покупателя из базы
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        Customer customer;
        // Проверяем покупателя на null и присваиваем ссылку соответствующей
        // переменной. Либо возвращаем пустой результат.
        if (optionalCustomer.isPresent()) customer = optionalCustomer.get();
        else return new ArrayList<>();
        // Получаем все корзины заданного покупателя,
        // а из корзин - все товары, которые он приобрёл
        List<Product> list = new ArrayList<>();
        for (Cart c : customer.getCarts()) {
            list.addAll(c.getProducts());
        }
        return list;
    }
}
