package com.example.spring_data.model.entity;

import javax.persistence.*;
import java.util.List;

/** Класс, описывающий продукт, сохраняемый в БД
 *
 * @author Andrey Pomelov
 * @version 1.0-SNAPSHOT
 */
@Entity
@Table(name = "products")
public class Product {

    /** ID продукта. Primary key в БД */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** Наименование продукта */
    @Column(name = "title")
    private String title;

    /**  Стоимость продукта */
    @Column(name = "price")
    private int price;

    /** Связываем продукт с корзиной */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "products_carts",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "cart_id")
    )
    private List<Cart> carts;

    /** Далее - стандартные геттеры и сеттеры */
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }
}