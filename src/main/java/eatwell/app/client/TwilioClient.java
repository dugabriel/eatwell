package eatwell.app.client;

import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class TwilioClient {

    private static final String ACCOUNT_SID = "ACdc12f25ba4102ad9ff276d209915bcd8";
    private static final String AUTH_TOKEN = "b80df41e307eebd02b260290d66b44c2";

    private final WebClient webClient;

    public TwilioClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.twilio.com/")
                .defaultHeaders(httpHeaders -> httpHeaders.setBasicAuth(ACCOUNT_SID, AUTH_TOKEN)).build();
    }

    @Retryable
    public Mono<byte[]> getImage(String imageUrl) {
        return this.webClient.get()
                .uri(imageUrl)
                .retrieve()
                .bodyToMono(byte[].class);
    }

}
