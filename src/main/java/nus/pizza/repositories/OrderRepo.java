package nus.pizza.repositories;

import java.io.Reader;
import java.io.StringReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import nus.pizza.models.DeliveryDetails;
import nus.pizza.models.Pizza;

@Repository
public class OrderRepo {

    @Autowired
    RedisTemplate<String, String> template;

    public void addOrderinJson(Pizza pizza, DeliveryDetails details, String uuid, Double totalCost){

        JsonObjectBuilder orderBuilder = Json.createObjectBuilder();
        orderBuilder
        .add("orderId", uuid)
        .add("name",details.getName())
        .add("address",details.getAddress())
        .add("phone",details.getPhone())
        .add("rush",details.getRush())
        .add("tip",details.getTip())
        .add("comments",details.getComments())
        .add("pizza",pizza.getPizza())
        .add("size",pizza.getSize())
        .add("quantity",pizza.getQuantity())
        .add("total",totalCost);

        JsonObject jo = orderBuilder.build();
        template.opsForValue().set(uuid, jo.toString());
    }

    public JsonObject getOrderinJson(String uuid){
        String jsonPayload = template.opsForValue().get(uuid);
        JsonReader reader = Json.createReader(new StringReader(jsonPayload));
		JsonObject obj = reader.readObject();
        
        return obj;
    }
    
}
