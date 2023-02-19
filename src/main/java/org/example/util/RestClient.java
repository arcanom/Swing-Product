package org.example.util;

import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestClient<T, V> {
    private String server = "http://localhost:8082";
    private RestTemplate template;
    private HttpHeaders headers;
    private HttpStatusCode status;

    public RestClient() {
        template = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        headers = new HttpHeaders();
        headers.add("Accept", "*/*");
        headers.add("content-type", "application/json");
    }

    public T get(String uri, Class<T> type) {
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        ResponseEntity<T> responseEntity = template.exchange(server + uri, HttpMethod.GET, requestEntity, type);
        status = responseEntity.getStatusCode();
        return responseEntity.getBody();
    }

    public T patch(String uri, Class<T> type) {
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        ResponseEntity<T> responseEntity = template.exchange(server + uri, HttpMethod.PATCH, requestEntity, type);
        status = responseEntity.getStatusCode();
        return responseEntity.getBody();
    }

    public T post(String uri, V data, Class<T> type) {
        HttpEntity<V> requestEntity = new HttpEntity<>(data, headers);
        ResponseEntity<T> responseEntity = template.exchange(server + uri, HttpMethod.POST, requestEntity, type);
        status = responseEntity.getStatusCode();
        return responseEntity.getBody();
    }
}
