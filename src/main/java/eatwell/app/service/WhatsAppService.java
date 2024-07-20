package eatwell.app.service;

import eatwell.app.controller.model.TwilioModel;
import org.springframework.stereotype.Service;

import eatwell.app.client.foodvisor.FoodVisorClient;
import eatwell.app.client.twilio.TwilioClient;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WhatsAppService {

    private final TwilioClient twilioClient;

    private final FoodVisorClient foodVisorClient;

    public WhatsAppService(TwilioClient twilioClient, FoodVisorClient foodVisorClient) {
        this.twilioClient = twilioClient;
        this.foodVisorClient = foodVisorClient;
    }

    public void process(TwilioModel twilioModel) {
        log.info("Process hook from {} - {}", twilioModel.getFrom(), twilioModel.getProfileName());

        log.info("media url -> {}", twilioModel.getMediaUrl0());

        foodVisorClient.getAnalysisImage(twilioModel.getMediaUrl0());

    }

}
