package nus.pizza.models;

import jakarta.validation.constraints.*;

public class Pizza {

    @NotNull(message="You did not select a pizza")
    @Pattern(regexp="^(bella|margherita|marinara|spianatacalabrese|trioformaggio)$", message="We do not have that pizza, please choose again.")
    private String pizza;

    @NotNull(message="You did not select a pizza size")
    @Pattern(regexp="^(sm|md|lg)$", message="Please select only small, medium or large pizza")
    private String size;

    @NotNull(message="You did not input a quantity.")
    @Min(value=1, message="Min 1 pizza")
    @Max(value=10, message="Max 10 pizza")
    private Integer quantity;


    public Pizza() {
    }

    public String getPizza() {
        return pizza;
    }
    public void setPizza(String pizza) {
        this.pizza = pizza;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    

    

}
