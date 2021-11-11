package com.example.tradestore.exception;

public class InvalidTradeException extends Exception{

    private String message;

    public InvalidTradeException(final String id){
        super();
        this.message = String.format("Invalid trade received for Id %s as the version is old" , id);

    }

    @Override
    public String getMessage() {
        return message;
    }
}
 
