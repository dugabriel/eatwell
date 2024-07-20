package eatwell.app.client.twilio;

import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import okhttp3.OkHttpClient;

@Service
public class TwilioClient {

    private static final String ACCOUNT_SID = "ACdc12f25ba4102ad9ff276d209915bcd8";
    private static final String AUTH_TOKEN = "b80df41e307eebd02b260290d66b44c2";

    private final OkHttpClient okHttpClient;

    public TwilioClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    @Retryable
    public void getImage(String imageUrl) {

    }

}
