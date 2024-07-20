package eatwell.app.client.foodvisor.data;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodItem {
    private double confidence;
    private double quantity;
    private List<Object> ingredients;
    private FoodInfo food_info;
}
