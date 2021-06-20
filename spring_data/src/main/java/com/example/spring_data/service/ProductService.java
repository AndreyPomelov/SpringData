package com.example.spring_data.service;

import com.example.spring_data.model.entity.Customer;
import com.example.spring_data.model.entity.Product;
import com.example.spring_data.model.repository.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /** Метод, возвращающий список покупателей, купивших данных продукт, по ИД продукта
     *
     * @param id - ИД продукта
     * @return - список покупателей
     */
    @Transactional
    public List<Customer> findAllCustomersByProductId(Long id) {
        // Получаем Optional-объект заданного продукта из базы
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product;
        // Проверям продукт на null и присваиваем ссылку соответствующей
        // переменной. Либо возвращаем пустой результат.
        if (optionalProduct.isPresent()) product = optionalProduct.get();
        else return new ArrayList<>();
        List<Customer> list = new ArrayList<>();
        // Получаем все корзины, в которых присутствует товар,
        // и из каждой корзины получаем её владельца-покупателя
        product.getCarts().forEach(x -> list.add(x.getCustomer()));
        return list;
    }
}
