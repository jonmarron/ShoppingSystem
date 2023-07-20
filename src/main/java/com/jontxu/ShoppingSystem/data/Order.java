package com.jontxu.ShoppingSystem.data;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public final class Order {
    private final int id;
    private final LocalDate data;
    private final Customer customer;
    private final List<Product> products;

    public Order(int id, LocalDate data, Customer customer, List<Product> products) {
        this.id = id;
        this.data = data;
        this.customer = customer;
        this.products = products;
    }

    public double getTotalPrice(){
        return products.stream()
                .mapToDouble(Product::price)
                .sum();
    }
    public void addProduct(Product product){
        products.add(product);
    }

    public void removeProduct(Product product){
        products.remove(product);
    }
    public int getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Product> getProducts() {
        return products;
    }
}
