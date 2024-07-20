package eatwell.app.client.foodvisor.data;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
    private Position position;
    private List<FoodItem> food;
}
