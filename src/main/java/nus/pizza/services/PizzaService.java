package nus.pizza.services;

import org.springframework.stereotype.Service;

import nus.pizza.models.DeliveryDetails;
import nus.pizza.models.Pizza;
import java.util.UUID;



@Service
public class PizzaService {

    public String generateUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().substring(0, 8);
    }


    public Double calculateCost(Pizza pizza, DeliveryDetails delivery){

        Double totalCost = 0.0;

        String incomingPizza = pizza.getPizza();

        if (incomingPizza.equalsIgnoreCase("bella") || incomingPizza.equalsIgnoreCase("marinara") || incomingPizza.equalsIgnoreCase("spianatacalabrese")) {
            totalCost += 30;
        } else if (incomingPizza.equalsIgnoreCase("margherita")){
            totalCost +=22;
        } else if (incomingPizza.equalsIgnoreCase("trioformaggio")){
            totalCost +=25;
        }

        String incomingSize = pizza.getSize();

        if (incomingSize.equalsIgnoreCase("md")){
            totalCost *=1.2;
        } else if (incomingSize.equalsIgnoreCase("lg")){
            totalCost *= 1.5;
        }

        totalCost *= pizza.getQuantity();

        if (delivery == null){
            System.out.println("delivery is null");
        }

        if (delivery.getRush()){
            totalCost += 2;
        }

        totalCost+=delivery.getTip();
    
        return totalCost;
    }
    

    
    
}
