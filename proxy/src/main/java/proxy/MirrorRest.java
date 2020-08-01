package proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.naming.ServiceUnavailableException;
import java.util.Map;
import java.util.Random;


@RestController
public class MirrorRest {

    @Value("${BASE_URL}")
    private String BASE_URL;
    @Value("${URL_EMPLOYEES}")
    private String URL_EMPLOYEES;

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    HttpEntity<String> entity;

    private static final Logger logger = LoggerFactory.getLogger(MirrorRest.class);;

    @RequestMapping("/employees")
    public @ResponseBody ResponseEntity mirrorRest(@RequestHeader Map<String, String> headers) {
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
        logger.info("REQUEST");
        logger.info("Request: "+ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        logger.info("From: "+((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest().getRemoteAddr());
        headers.forEach((key, value)->
        {
            if(key.equals("x-request-id"))
            logger.info(String.format("Header '%s' = %s", key, value));

        });
        Random rand = new Random();
        if(rand.nextDouble()<0.8){
            logger.info("ServiceUnavailableException");
            return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE, HttpStatus.SERVICE_UNAVAILABLE);
        }
        ResponseEntity<String> response = restTemplate.exchange(URL_EMPLOYEES,
                HttpMethod.GET, entity, String.class);

        logger.info("Response: "+response);
        String result = "v3: "+response.getBody();
        logger.info("result: "+result);

       // return response;
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @RequestMapping("/employee/{empID}")
    public @ResponseBody String mirrorRestEmp(@PathVariable String empID) throws Exception {

        logger.info("Request: "+ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        logger.info("From: "+((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest().getRemoteAddr());

        ResponseEntity<String> response = restTemplate.exchange(BASE_URL+empID,
                HttpMethod.GET, entity, String.class);
        logger.info("Response: "+response);
        String result = "v3: "+response.getBody();
        logger.info("result: "+result);
        return result;
    }
}
