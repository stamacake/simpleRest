package proxy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;


@RestController
public class mirrorRest {

    @Value("${BASE_URL}")
    private String BASE_URL;
    @Value("${URL_EMPLOYEES}")
    private String URL_EMPLOYEES;

    HttpHeaders headers = new HttpHeaders();
    RestTemplate restTemplate = new RestTemplate();
    // HttpEntity<String>: To get result as String.
    HttpEntity<String> entity = new HttpEntity<String>(headers);
    @RequestMapping("/employees")
    public @ResponseBody String mirrorRest() throws URISyntaxException
    {
        // Send request with GET method, and Headers.
        ResponseEntity<String> response = restTemplate.exchange(URL_EMPLOYEES,
                HttpMethod.GET, entity, String.class);

        String result = response.getBody();
        return result;
    }

    @RequestMapping("/employee/{empID}")
    public @ResponseBody String mirrorRestEmp(@PathVariable String empID) throws URISyntaxException
    {

        // Send request with GET method, and Headers.
        ResponseEntity<String> response = restTemplate.exchange(BASE_URL+empID,
                HttpMethod.GET, entity, String.class);

        String result = response.getBody();
        return result;
    }
}
