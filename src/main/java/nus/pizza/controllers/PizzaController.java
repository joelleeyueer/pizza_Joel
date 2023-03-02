package nus.pizza.controllers;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import nus.pizza.models.DeliveryDetails;
import nus.pizza.models.Pizza;
import nus.pizza.services.PizzaService;
import nus.pizza.repositories.OrderRepo;


@Controller
@RequestMapping(path={"/","/index.html"})
public class PizzaController {

	@Autowired
	OrderRepo orderRepo;

	@Autowired
	PizzaService pizzaService;


    @GetMapping
	public String getIndexPage(Model model, HttpSession sess) {
		//basically your homepage
		sess.invalidate();
		model.addAttribute("pizza", new Pizza());
		return "index";
	}

    @PostMapping(path="/pizza")
    public String getDeliveryPage(@Valid Pizza pizza, BindingResult result, Model model, HttpSession sess){
		//after selecting the pizza, size and qty
		//to make following HTTP request to the backend
		//POST /pizza
		//Content-type: application/x-www-form-urlencoded

		//validation check for the pizza:
		//1. must have pizza
		//2. should be oen of the 5 pizzas selected
		//3. must have size. sizes incl only sm, md or lg
		//4. min order 1, max order 10
		//if any of the checks fail, return to homepage and throw error message

		if (result.hasErrors()){
			System.out.println("got error");
			return "index";
		}

		sess.setAttribute("pizza", pizza);

		model.addAttribute("delivery", new DeliveryDetails());

		return "deliveryAddress";

	}

	@PostMapping(path="/pizza/ordersuccess")
    public String getOrderSuccessPage(@Valid DeliveryDetails details, BindingResult result, Model model, HttpSession sess){
		//need to build Json using JsonObjectBuilder, then store in redis
		//need method call to get data from redis (to be displayed on screen with RestController)
		System.out.println("in ordersuccess");

		if (result.hasErrors()){
			System.out.println("got error");
			return "deliveryAddress";
		}

		Pizza thisPizza = (Pizza) sess.getAttribute("pizza");
		DeliveryDetails thisDelivery = (DeliveryDetails) sess.getAttribute("delivery");
		Double totalCost=pizzaService.calculateCost(thisPizza, thisDelivery);
		Double oriCost=totalCost-thisDelivery.getTip()-(thisDelivery.getRush()?2:0);
		String uuid=pizzaService.generateUUID();
		orderRepo.addOrderinJson(thisPizza,thisDelivery, uuid, totalCost);


		model.addAttribute("pizza", thisPizza);
		model.addAttribute("delivery", thisDelivery);
		model.addAttribute("Controllertotalcost", totalCost);
		model.addAttribute("Controlleroricost", oriCost);
		model.addAttribute("Controlleruuid", uuid);

		
		//Delivery details
		//order id
		//your order will be delivered to {address}
		//Pizza cost
		//Rush cost --> dont print if no rush selected
		//Tip
		//total cost
		
		return "ordersuccess";
	}
	

    
}
