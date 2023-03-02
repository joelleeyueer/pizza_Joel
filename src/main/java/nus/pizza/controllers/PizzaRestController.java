package nus.pizza.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;


import jakarta.json.JsonObject;

import nus.pizza.repositories.OrderRepo;


@RestController
@RequestMapping(path="/pizza/ordersuccess/")
public class PizzaRestController {
    
    @Autowired
    OrderRepo OrderRepo;

    @GetMapping(path="{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getDetailsByOrderID(@PathVariable String id){
        
        JsonObject incomingJO = OrderRepo.getOrderinJson(id);

        try{
            return ResponseEntity.status(200).body(incomingJO.toString());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(incomingJO.toString());
        }
    }
}
