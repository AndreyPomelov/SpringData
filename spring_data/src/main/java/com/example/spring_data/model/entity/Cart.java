package com.example.spring_data.model.entity;

import javax.persistence.*;
import java.util.List;

/** Класс, описывающий корзину, в которой лежат продукты конкретного покупателя
 *
 * @author Andrey Pomelov
 * @version 1.0-SNAPSHOT
 */
@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "customer_id", insertable = false, updatable = false)
    private Long customer_id;

    /** Связываем корзину с покупателем */
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /** Связываем корзину с продуктами */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "products_carts",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    /** Далее - геттеры и сеттеры */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }
}