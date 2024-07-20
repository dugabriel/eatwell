package eatwell.app.client.foodvisor;

import com.fasterxml.jackson.databind.ObjectMapper;
import eatwell.app.client.foodvisor.data.FoodAnalysisResult;
import eatwell.app.client.foodvisor.data.FoodItem;
import eatwell.app.client.foodvisor.data.Item;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Service
@Slf4j
public class FoodVisorClient {

    private static final String TOKEN_FOOD_VISOR = "Api-Key Sp89l5rL.0qI3nWZu1YYJgByl8kH9fW6zgKnSj8u2";

    private final OkHttpClient okHttpClient;

    private final ObjectMapper objectMapper;

    public FoodVisorClient(OkHttpClient okHttpClient, ObjectMapper objectMapper) {
        this.okHttpClient = okHttpClient;
        this.objectMapper = objectMapper;
    }

    @Retryable(retryFor = IOException.class, maxAttempts = 4, backoff = @Backoff(delay = 1000))
    public FoodAnalysisResult getAnalysisImage(String imageUrl) {
        log.info("getAnalysisImage: {}", imageUrl);
        MultipartBody multipartBody = this.createMultipartBodyFromImageUrl(imageUrl);

        if (multipartBody != null) {
            Request request = new Request.Builder()
                    .url("https://vision.foodvisor.io/api/1.0/en/analysis/")
                    .method("POST", multipartBody)
                    .addHeader("Authorization", TOKEN_FOOD_VISOR)
                    .build();

            try (Response response = okHttpClient.newCall(request).execute()) {
                if (response.isSuccessful() && response.body() != null) {
                    FoodAnalysisResult result = objectMapper.readValue(response.body().string(), FoodAnalysisResult.class);
                    this.logResult(result);
                    return result;
                }
            } catch (IOException ex) {
                log.error("error to vision api {}", ex.getMessage());
                return null;
            }
        }
        return null;
    }

    private void logResult(FoodAnalysisResult result) {
        log.info("Analysis ID: {}", result.getAnalysis_id());
        log.info("Scopes: {}", result.getScopes());

        for (Item item : result.getItems()) {
            log.info("Position: x={} , y={}", item.getPosition().getX(), item.getPosition().getY());

            for (FoodItem foodItem : item.getFood()) {
                log.info("Food: {}", foodItem.getFood_info().getDisplay_name());
                log.info("Confidence: {}", foodItem.getConfidence());
                log.info("Quantity: {}", foodItem.getQuantity());
                log.info("Nutrition: {}", foodItem.getFood_info().getNutrition());
                log.info("-----------");
            }
        }
    }

    private MultipartBody createMultipartBodyFromImageUrl(String imageUrl) {
        try {
            RequestBody requestBody = RequestBody.create(this.getImageBytes(imageUrl), MediaType.parse("image/jpeg"));
            return new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("image", "image.jpg", requestBody)
                    .build();
        } catch (IOException | URISyntaxException exception) {
            log.info("Failed to download image {}. Cause: {}", imageUrl, exception.getMessage());
            return null;
        }
    }

    private byte[] getImageBytes(String imageUrl) throws IOException, URISyntaxException {
        var url = new URI(imageUrl).toURL();
        byte[] imageBytes;
        try (InputStream inputStream = url.openStream()) {
            imageBytes = inputStream.readAllBytes();
        }
        return imageBytes;
    }

}
