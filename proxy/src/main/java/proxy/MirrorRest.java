package proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


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
    public @ResponseBody String mirrorRest()
    {
        logger.info("Request: "+ ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        logger.info("From: "+((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest().getRemoteAddr());

        ResponseEntity<String> response = restTemplate.exchange(URL_EMPLOYEES,
                HttpMethod.GET, entity, String.class);
        logger.info("Response: "+response);
        String result = "v1: "+response.getBody();
        logger.info("result: "+result);
        return result;
    }

    @RequestMapping("/employee/{empID}")
    public @ResponseBody String mirrorRestEmp(@PathVariable String empID)
    {
        logger.info("Request: "+ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        logger.info("From: "+((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest().getRemoteAddr());

        ResponseEntity<String> response = restTemplate.exchange(BASE_URL+empID,
                HttpMethod.GET, entity, String.class);
        logger.info("Response: "+response);
        String result = "v1: "+response.getBody();
        logger.info("result: "+result);
        return result;
    }
}
