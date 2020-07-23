package com.stamacake.spring.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
public class MainRESTController {

    @Value("${URL_CONFIRM}")
    private String URL_CONFIRM;

    private static final Logger logger = LoggerFactory.getLogger(MainRESTController.class);

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    HttpEntity<String> entity;

    @RequestMapping("/")
    @ResponseBody
    public String welcome() {
        return "Welcome";
    }

    @RequestMapping("/*")
    public @ResponseBody String editRequest() {

        logger.info("Request: " + ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        logger.info("From/to: " + ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest().getRemoteAddr());

        logger.info("TO:" + URL_CONFIRM);
        HttpHeaders responceHeaders = new HttpHeaders();
        responceHeaders.set("Host","external.com");
        HttpEntity<String> entity2 = new HttpEntity<>(responceHeaders);
        ResponseEntity<String> response = restTemplate.exchange(URL_CONFIRM,
                HttpMethod.GET, entity2, String.class);

        logger.info("BODY " + response.getBody());

        return response.getBody();
    }
}