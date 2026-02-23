package com.spring_boot.app.dto;

import java.util.Date;


public record OrderDTO(

    String orderId,
     int quantity,
     double price,
     String accountNumber,
     Date orderCreationDate,
    String customerName

){}



