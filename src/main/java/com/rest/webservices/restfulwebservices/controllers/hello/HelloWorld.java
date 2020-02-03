package com.rest.webservices.restfulwebservices.controllers.hello;

import com.rest.webservices.restfulwebservices.models.helloworld.HelloWorldBeen;
import org.springframework.web.bind.annotation.*;

// Controller
@RestController
public class HelloWorld {
    // GET
    // URI - /hello-world
    // Method - "Hello World!"
    // - Long Method:
//    @RequestMapping(method =  RequestMethod.GET,
//                    path = "/hello-world")
//    public String helloWorld(){
//        return "Hello World!";
//    }

    // - Short Method:
    @GetMapping( "/hello-word" )
    public String helloWorld() {
        return "Hello Word!";
    }

    //
    @GetMapping( "/hello-word-been" )
    public HelloWorldBeen helloWorldBeen() {
        return new HelloWorldBeen("Hello Word!");
    }

    @GetMapping( "/hello-word-been/path-variable/{name}" )
    public HelloWorldBeen helloWorldBeenPathVariable(@PathVariable String name) {
        return new HelloWorldBeen(String.format("Hello %s!", name));
    }
}
