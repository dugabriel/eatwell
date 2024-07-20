package eatwell.app.client.twilio;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TwilioClient {

    private static final String ACCOUNT_SID = "ACdc12f25ba4102ad9ff276d209915bcd8";
    private static final String AUTH_TOKEN = "b80df41e307eebd02b260290d66b44c2";
    private static final String FROM = "whatsapp:+14155238886";

    private final OkHttpClient okHttpClient;

    public TwilioClient(OkHttpClient okHttpClient) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        this.okHttpClient = okHttpClient;
    }

    @Retryable(retryFor = Exception.class, maxAttempts = 4, backoff = @Backoff(delay = 1000))
    public void sendWhatsAppMessage(String text, String to) {
        log.info("Send whatsapp message to {} with text: {}", to, text);
        Message message = Message.creator(new PhoneNumber(to), new PhoneNumber(FROM), text).create();
        log.info("message twilio sid {} with status {}", message.getSid(), message.getStatus());
    }

}
