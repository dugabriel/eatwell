package eatwell.app.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eatwell.app.controller.model.TwillioModel;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/hook")
@Slf4j
public class HookController {

    @PostMapping(produces = "application/x-www-form-urlencoded")
    public void handleWhatsapp(@ModelAttribute TwillioModel twillioModel) {
        log.info(twillioModel.toString());
    }

}
