package example.controller;

import example.service.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controller")
@Slf4j
public class Controller {

    @Autowired private Service service;

    @GetMapping("/quotes")
    public String getQuotes() {
        return service.getInfo();
    }

}
