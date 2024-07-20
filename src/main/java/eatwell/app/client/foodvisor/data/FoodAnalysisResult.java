package eatwell.app.client.foodvisor.data;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodAnalysisResult {
    private String analysis_id;
    private List<String> scopes;
    private List<Item> items;
}
