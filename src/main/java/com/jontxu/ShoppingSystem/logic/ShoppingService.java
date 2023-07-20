package com.jontxu.ShoppingSystem.logic;

import com.jontxu.ShoppingSystem.data.Customer;
import com.jontxu.ShoppingSystem.data.Order;
import com.jontxu.ShoppingSystem.data.Product;

import java.time.YearMonth;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShoppingService {
    public List<Product> getAllProdsUnderPrice(List<Product> products, double price) {
        return products.stream()
                .filter(product -> product.price() < price)
                .collect(Collectors.toList());
    }

    public Customer getCustomerThatSpentMostLastMonth(List<Order> orders) {
        return orders.stream()
                .filter(order -> YearMonth.from(order.getDate()).equals(YearMonth.now().minusMonths(1)))
                .max(Comparator.comparingDouble(Order::getTotalPrice))
                .map(Order::getCustomer)
                .orElse(null);
    }

    public Product getMostBoughtProduct(List<Order> orders) {
        Map<Product, Long> productByAmountSold = orders.stream()
                .flatMap(order -> order.getProducts().stream())
                .collect(Collectors.groupingBy(product -> product, Collectors.counting()));

        return productByAmountSold.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

}
