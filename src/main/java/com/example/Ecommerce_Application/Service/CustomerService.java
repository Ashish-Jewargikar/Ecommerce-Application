package com.example.Ecommerce_Application.Service;

import com.example.Ecommerce_Application.Convertor.CustomerConvertor;
import com.example.Ecommerce_Application.Model.Cart;
import com.example.Ecommerce_Application.Model.Customer;
import com.example.Ecommerce_Application.Repository.CustomerRepository;
import com.example.Ecommerce_Application.RequestDTO.CustomerRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public String addCustomer(CustomerRequestDto customerRequestDto){

        Customer customer = CustomerConvertor.CustomerRequestDtoToCustomer(customerRequestDto);

        // allocate a cart to customer
        Cart cart = new Cart();
        cart.setCartTotal(0);
        cart.setCustomer(customer);

        // set cart in customer
        customer.setCart(cart);

        customerRepository.save(customer);
        return "Welcome !!..have a great Shopping";
    }
}