package com.example.ecommerce.Service.Implementation;

import com.example.ecommerce.Dto.RequestDto.CheckoutCartRequestDto;
import com.example.ecommerce.Dto.ResponseDto.CardResponseDto;
import com.example.ecommerce.Dto.ResponseDto.CartResponseDto;
import com.example.ecommerce.Dto.ResponseDto.ItemResponseDto;
import com.example.ecommerce.Dto.ResponseDto.OrderResponseDto;
import com.example.ecommerce.Entity.*;
import com.example.ecommerce.Exception.InvalidCardException;
import com.example.ecommerce.Exception.InvalidCustomerException;
import com.example.ecommerce.Repository.CardRepository;
import com.example.ecommerce.Repository.CartRepository;
import com.example.ecommerce.Repository.CustomerRepository;
import com.example.ecommerce.Repository.OrderedRepository;
import com.example.ecommerce.Service.CartService;
import com.example.ecommerce.Service.OrderService;
import com.example.ecommerce.Service.ProductService;
import com.example.ecommerce.Transformer.CartTransformer;
import com.example.ecommerce.Transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CartRepository cartRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderedRepository orderedRepository;
    @Autowired
    ProductService productService;

    public CartResponseDto saveCart(Integer customerId, Item item) {
        //customer already exists because you check in itemsservice

        Customer customer = customerRepository.findById(customerId).get();
        Cart cart = customer.getCart();

        int newTotal = cart.getCartTotal() + item.getRequiredQuantity() * item.getProduct().getPrice();
        cart.setCartTotal(newTotal);
        cart.getItems().add(item);
        cart.setNumberOfItems(cart.getItems().size());
        item.setCart(cart);
        Cart savedCart = cartRepository.save(cart);

        CartResponseDto cartResponseDto = CartResponseDto.builder()
                .cartTotal(savedCart.getCartTotal())
                .customerName(customer.getName())
                .numberOfItems(savedCart.getNumberOfItems())
                .build();

        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for (Item itemEntity : savedCart.getItems()) {
            ItemResponseDto itemResponseDto = ItemTransformer.ItemToItemResponseDto(itemEntity);
            itemResponseDtoList.add(itemResponseDto);
        }

        cartResponseDto.setItems(itemResponseDtoList);
        return cartResponseDto;
    }

    @Override
    public OrderResponseDto checkOutCart(CheckoutCartRequestDto checkoutCartRequestDto) throws Exception {
        Customer customer;
        try{
            customer=customerRepository.findById(checkoutCartRequestDto.getCustomerId()).get();
        }catch (Exception e){
            throw new InvalidCustomerException("customer id is invalid!!!!");
        }
        Card card=cardRepository.findByCardnumber(checkoutCartRequestDto.getCardNo());
        if(card==null || card.getCvv()!=checkoutCartRequestDto.getCvv() || card.getCustomer()!=customer){
            throw new InvalidCardException("Your card is not valid!!");
        }
        Cart cart = customer.getCart();
        if(cart.getNumberOfItems()==0){
            throw new Exception("Cart is empty!!");
        }
        try{
            Ordered order = orderService.placeOrder(customer,card);  // throw exception if product goes out of stock

            resetCart(cart);

            Ordered savedOrder = orderedRepository.save(order);
            customer.getOrderList().add(savedOrder);



            // prepare response dto
            OrderResponseDto orderResponseDto = new OrderResponseDto();
            orderResponseDto.setOrderDate(savedOrder.getOrderDate());
            orderResponseDto.setCardUsed(savedOrder.getCardUse());
            orderResponseDto.setCustomerName(customer.getName());
            orderResponseDto.setOrderNo(savedOrder.getOrderNo());
            orderResponseDto.setTotalValue(savedOrder.getTotalValue());

            List<ItemResponseDto> items = new ArrayList<>();
            for(Item itemEntity: savedOrder.getItems()){
                ItemResponseDto itemResponseDto = ItemTransformer.ItemToItemResponseDto(itemEntity);
                items.add(itemResponseDto);
            }
            orderResponseDto.setItems(items);
            return orderResponseDto;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    public void resetCart(Cart cart){

        cart.setCartTotal(0);
        for(Item item: cart.getItems()){
            item.setCart(null);
        }
        cart.setNumberOfItems(0);
        cart.getItems().clear();

    }
}