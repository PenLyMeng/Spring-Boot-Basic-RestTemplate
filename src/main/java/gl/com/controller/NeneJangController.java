package gl.com.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gl.com.entity.Todo;
import gl.com.entity.Token;
import gl.com.entity.User;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by penlymeng on 5/3/17.
 */


@RestController
public class NeneJangController {

    String neneJangUrl = "https://nene-jang-todo-api.herokuapp.com/";


    @RequestMapping(value = "todos", method = RequestMethod.GET)
    public Map<String, Object> findAllTodos(@RequestHeader("Auth") String token) {
        Map<String, Object> map = new HashMap();

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Auth",token);

        HttpEntity<String> entity = new HttpEntity<>("parameters",httpHeaders);

        ResponseEntity<String> responseEntity = restTemplate.exchange(neneJangUrl+"todos",HttpMethod.GET,entity,String.class);


        ObjectMapper objectMapper = new ObjectMapper();


        try {
            map.put("crush", objectMapper.readValue(responseEntity.getBody(), Todo[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(responseEntity.getBody());

        return map;
    }


    @RequestMapping(value = "user/login", method = RequestMethod.POST)
    public Map<String, Object> login(@RequestBody User user) {

        Map<String, Object> map = new HashMap();

        ClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();


        RestTemplate restTemplate = new RestTemplate(requestFactory);

        HttpEntity<User> request = new HttpEntity<>(user);

        map.put("crush", restTemplate.exchange(neneJangUrl+"users/login",HttpMethod.POST,request,User.class).getHeaders());

        return map;
    }




}
