package com.spring_boot.app.services;


import com.spring_boot.app.dto.OrderDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class OrderServices {

    public static void main(String[] args) {
        List<OrderDTO> list = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            list.add(new OrderDTO("1", 2, 100.0, "1234567890", sdf.parse("15/11/2021"), "John Doe"));
            list.add(new OrderDTO("2", 1, 50.0, "0987654321", sdf.parse("18/05/2015"), "Jane Smith"));
            list.add(new OrderDTO("3", 5, 200.0, "1122334455", sdf.parse("16/02/2001"), "Alice Johnson"));
            list.add(new OrderDTO("4", 1, 50.0, "0987654321", sdf.parse("01/05/2017"), "Jane Smith"));
            list.add(new OrderDTO("5", 5, 200.0, "1122334455", sdf.parse("12/02/2018"), "Alice Johnson"));
        } catch (ParseException e) {
            System.err.println("Failed to parse date: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        Map<String, List<OrderDTO>> result = findPrimeOrders(list);

        System.out.println("Prime Orders:");
        result.getOrDefault("primeOrders", Collections.emptyList()).forEach(System.out::println);

        System.out.println("Normal Orders:");
        result.getOrDefault("normalOrders", Collections.emptyList()).forEach(System.out::println);
    }

    public static Map<String, List<OrderDTO>> findPrimeOrders(List<OrderDTO> orders) {

        List<OrderDTO> primeList = new ArrayList<>();
        List<OrderDTO> normalList = new ArrayList<>();

        for (OrderDTO order : orders) {
            if (order.price() * order.quantity() > 200) {
                primeList.add(order);
            } else {
                normalList.add(order);
            }
        }

        Map<String, List<OrderDTO>> result = new HashMap<>();
        result.put("primeOrders", primeList);
        result.put("normalOrders", normalList);

        return result;
    }

}
