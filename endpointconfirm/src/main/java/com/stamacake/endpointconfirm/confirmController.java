package com.stamacake.endpointconfirm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;



@RestController
public class confirmController {



    @RequestMapping("/confirm")
    public @ResponseBody String confirm(@RequestBody String httpEntity){
        return "confirmed: "+httpEntity;
    }
}
