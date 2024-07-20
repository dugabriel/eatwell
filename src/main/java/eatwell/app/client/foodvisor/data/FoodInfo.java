package eatwell.app.client.foodvisor.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodInfo {
    private String food_id;
    private String fv_grade;
    private double g_per_serving;
    private String display_name;
    private Nutrition nutrition;
}
