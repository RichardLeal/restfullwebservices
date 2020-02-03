package com.rest.webservices.restfulwebservices.models.helloworld;

public class HelloWorldBeen {

    private String message;

    public HelloWorldBeen(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HelloWorldBeen{" +
                "message='" + message + '\'' +
                '}';
    }
}
