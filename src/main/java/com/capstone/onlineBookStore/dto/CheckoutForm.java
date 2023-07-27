package com.capstone.onlineBookStore.dto;

import lombok.Data;

@Data
public class CheckoutForm {

    private String address;

    private String zipCode;
    private int s;
}
