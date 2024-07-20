package eatwell.app.service;

import eatwell.app.client.foodvisor.data.FoodAnalysisResult;
import eatwell.app.client.foodvisor.data.FoodItem;
import eatwell.app.client.foodvisor.data.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@Slf4j
public class TemplateService {


    public String foodTemplateAnalysis(FoodAnalysisResult foodAnalysisResult) {
        StringBuilder builder = new StringBuilder();
        builder.append(MessageFormat.format("Analysis id: {0}", foodAnalysisResult.getAnalysis_id())).append(System.lineSeparator());
        builder.append(MessageFormat.format("Scopes: {0}", foodAnalysisResult.getScopes())).append(System.lineSeparator());
        builder.append(System.lineSeparator());

        for (Item item : foodAnalysisResult.getItems()) {
            //log.info("Position: x={} , y={}", item.getPosition().getX(), item.getPosition().getY());

            for (FoodItem foodItem : item.getFood()) {
                builder.append(MessageFormat.format("Food: {0}", foodItem.getFood_info().getDisplay_name())).append(System.lineSeparator());
                builder.append(MessageFormat.format("Confidence: {0}", foodItem.getConfidence())).append(System.lineSeparator());
                builder.append(MessageFormat.format("Quantity: {0}", foodItem.getQuantity())).append(System.lineSeparator());
                builder.append(System.lineSeparator());
                //log.info("Nutrition: {}", foodItem.getFood_info().getNutrition());
            }
        }

        return builder.toString();
    }
}
