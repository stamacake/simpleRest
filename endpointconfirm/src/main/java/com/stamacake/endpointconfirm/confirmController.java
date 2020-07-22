package com.stamacake.endpointconfirm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class confirmController {

    private static final Logger logger = LoggerFactory.getLogger(confirmController.class);;

    @RequestMapping("/*")
    public @ResponseBody String confirm(@RequestBody String httpEntity){
        logger.info("HttpEntity: "+httpEntity);
        return "confirmed: "+httpEntity;
    }
}
