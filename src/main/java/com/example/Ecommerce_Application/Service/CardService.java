package com.example.Ecommerce_Application.Service;

import com.example.Ecommerce_Application.Exception.CustomerNotFoundException;
import com.example.Ecommerce_Application.Model.Card;
import com.example.Ecommerce_Application.Model.Customer;
import com.example.Ecommerce_Application.Repository.CustomerRepository;
import com.example.Ecommerce_Application.RequestDTO.CardRequestDto;
import com.example.Ecommerce_Application.ResponseDTO.CardDto;
import com.example.Ecommerce_Application.ResponseDTO.CardResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {

    @Autowired
    CustomerRepository customerRepository;

    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotFoundException {

        Customer customer;

        try {
            customer = customerRepository.findById(cardRequestDto.getCustomerId()).get();
        } catch (Exception e) {
            throw new CustomerNotFoundException("Invalid customer id");
        }

        // Make a card object
        Card card = Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cvv(cardRequestDto.getCvv())
                .cardType(cardRequestDto.getCardType())
                .customer(customer)
                .build();

        // add the card to current card list of customer
        customer.getCards().add(card);

        customerRepository.save(customer); // save both customer and card

        // prepare Response Dto
        CardResponseDto cardResponseDto = new CardResponseDto();
        cardResponseDto.setName(customer.getName());

        // convert every card to cardDto
        List<CardDto> cardDtoList = new ArrayList<>();
        for (Card card1 : customer.getCards()) {
            CardDto cardDto = new CardDto();
            cardDto.setCardNo(card1.getCardNo());
            cardDto.setCardType(card1.getCardType());

            cardDtoList.add(cardDto);
        }

        cardResponseDto.setCardDtos(cardDtoList);
        return cardResponseDto;

    }
}