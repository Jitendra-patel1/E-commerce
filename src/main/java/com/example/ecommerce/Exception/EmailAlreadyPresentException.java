package com.example.ecommerce.Exception;

public class EmailAlreadyPresentException extends  Exception{
    public EmailAlreadyPresentException(String message){
        super(message);
    }
}
