package com.example.spring_data.model.entity;

import javax.persistence.*;
import java.util.List;

/** Класс, описывающий покупателя
 *
 * @author Andrey Pomelov
 * @version 1.0-SNAPSHOT
 */
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    /** Связываем покупателя с корзиной */
    @OneToMany(mappedBy = "customer")
    private List<Cart> carts;

    /** Геттеры и сеттеры */
    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}