package com.github.client.service;

import io.github.jhipster.config.JHipsterProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.github.client.config.Constants;
import com.github.client.domain.GithubUser;
import com.github.client.domain.Token;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GithubService {



    private static final String clientId = Constants.GITHUB_CLIENT_ID;
    private static final String clientSecret = Constants.GITHUB_CLIENT_SECRET;
    private static final String gitHubApiBaseUrl=Constants.GITHUB_API_URL;
    private static final String gitHubBaseUrl=Constants.GITHUB_BASE_URL;


    /**
     * Make a call to github
     * @param url
     * @param method
     * @param responseType
     * @param urlVars
     * @param body
     * @return
     */
    private Object call(String url, HttpMethod method, Class<?> responseType, Map<String, String> urlVars, Map<String, Object> body, boolean includeConstants) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            Object resp = null;
            switch (method) {
                case GET:
                    if(urlVars==null){
                        urlVars=new ConcurrentHashMap<String, String>();
                    }
                    if(includeConstants) {
                        urlVars.put("client_id", clientId);
                        urlVars.put("client_secret", clientSecret);
                    }
                    resp = restTemplate.getForObject(url, responseType, urlVars);
                    break;
                case POST:
                    if(body==null){
                        body=new ConcurrentHashMap<String, Object>();
                    }
                    if(includeConstants) {
                        body.put("client_secret", clientSecret);
                        body.put("client_id", clientId);
                    }

                    HttpEntity request = new HttpEntity<>(body);
                    resp = restTemplate.postForObject( url, request, responseType);
                    break;
                default:
                    throw new Exception("Method not supported");
            }

            return resp;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Make a call to the api service
     * @param url
     * @param method
     * @param responseType
     * @param urlVars
     * @param body
     * @return
     */
    public Object callApi(String url, HttpMethod method, Class<?> responseType, Map<String, String> urlVars, Map<String, Object> body) {
        return call(gitHubApiBaseUrl + url, method, responseType, urlVars, body, false);
    }

    /**
     * Make call to the public github site
     * @param url
     * @param method
     * @param responseType
     * @param urlVars
     * @param body
     * @return
     */
    public Object callSite(String url, HttpMethod method, Class<?> responseType, Map<String, String> urlVars, Map<String, Object> body) {
        return call(gitHubBaseUrl + url, method, responseType, urlVars, body, true);
    }

    public Token getToken(String code){
        RestTemplate restTemplate = new RestTemplate();
        Map body=new ConcurrentHashMap<String, String>();
        body.put("code", code);
        body.put("client_id", Constants.GITHUB_CLIENT_ID);
        body.put("client_secret", Constants.GITHUB_CLIENT_SECRET);

        HttpEntity request = new HttpEntity<>(body);
        Token t= (Token) callSite("login/oauth/access_token", HttpMethod.POST, Token.class, null, body);

        return t;
    }

    public GithubUser getUser(Token token){
        Map vars=new ConcurrentHashMap<String, String>();
        //vars.put("access_token", token.getValue());
        GithubUser m = (GithubUser) callApi("/user?access_token=" + token.getValue(), HttpMethod.GET, GithubUser.class, null, null);
        return m;
    }
}
