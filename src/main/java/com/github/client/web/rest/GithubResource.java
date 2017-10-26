package com.github.client.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.github.client.config.DefaultProfileUtil;
import com.github.client.domain.GithubUser;
import com.github.client.domain.Token;
import com.github.client.service.GithubService;
import io.github.jhipster.config.JHipsterProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.view.RedirectView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Resource to return information about the currently running Spring profiles.
 */
@RestController
@RequestMapping("/api/github")
public class GithubResource {
    private final Logger log = LoggerFactory.getLogger(GithubResource.class);

    @Inject
    private GithubService service;

    public GithubResource() {

    }

    @GetMapping("/success")
    @Timed
    public RedirectView success(HttpServletRequest request, @RequestParam(value = "code") String code) {
        try {
            Token t = service.getToken(code);
            GithubUser u = service.getUser(t);
            request.getSession(true).setAttribute("token", t);
            Authentication auth =
                new UsernamePasswordAuthenticationToken(u, null, u.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(auth);
            return new RedirectView("/#/");
        } catch (Exception e) {
            log.error("Exception creating social user: ", e);
            return new RedirectView("/#/");
        }
    }


    @GetMapping("/get-token")
    @Timed
    public Token token(HttpServletRequest request) {
        return (Token) request.getSession().getAttribute("token");
    }

    @GetMapping("/current")
    @Timed
    public GithubUser currentUser(HttpServletRequest request) {
        try {
            return service.getUser((Token) request.getSession().getAttribute("token"));
        } catch (Exception ex) {
            return null;
        }
    }

    @RequestMapping(value={"/call"}, method= RequestMethod.GET)
    @Timed
    public Map callApi(HttpServletRequest request, @RequestParam(value = "path") String path) {
        Token t = (Token) request.getSession().getAttribute("token");
        path = path + "?access_token=" + t.getValue();
        Map r = (Map) service.callApi(path, HttpMethod.GET, Map.class, null, null);
        return  r;
    }


}
