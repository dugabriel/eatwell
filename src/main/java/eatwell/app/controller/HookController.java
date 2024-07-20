package eatwell.app.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eatwell.app.controller.model.TwilioModel;
import eatwell.app.service.WhatsAppService;

@RestController
@RequestMapping("/api/hook")
public class HookController {

    private final WhatsAppService whatsAppService;

    public HookController(WhatsAppService whatsAppService) {
        this.whatsAppService = whatsAppService;
    }

    @PostMapping(produces = "application/x-www-form-urlencoded")
    public void handleWhatsapp(@ModelAttribute TwilioModel twillioModel) {
        whatsAppService.process(twillioModel);
    }

}
