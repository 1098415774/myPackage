package sh.enhance.http;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class HttpRequestUtils {

    private RestTemplate restTemplate;

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> T postForJSON(String url, String jsonstr, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = MediaType.APPLICATION_JSON_UTF8;
        headers.setContentType(mediaType);
        HttpEntity<String> httpEntity = new HttpEntity<>(jsonstr,headers);
        return restTemplate.postForObject(url,httpEntity,responseType);
    }

    public <T> T get(String url, Class<T> responseType){
        return restTemplate.getForObject(url,responseType);
    }
}
