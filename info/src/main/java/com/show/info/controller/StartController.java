package com.show.info.controller;

import com.show.info.model.Login;
import com.show.info.service.AnalystService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import java.util.Map;

@Controller
public class StartController {
    @Autowired
    private AnalystService analystService;
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/login")
    public String createSession(@ModelAttribute Login login, HttpSession session, Model model) {
        try {
            ResponseEntity<Map> response = restTemplate.postForEntity("http://authentication:8081/authenticate/login", login, Map.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                session.setAttribute("isLogged", "true");
                return "redirect:/show";
            }

        } catch (HttpClientErrorException httpClientErrorException) {
            if (httpClientErrorException.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                model.addAttribute("error", "Invalid username or password");
                model.addAttribute("login", new Login());
                return "login";
            }
        } catch (Exception exception) {
            model.addAttribute("login", new Login());
            model.addAttribute("error", "An error occurred. Please try again");
            return "login";
        }
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, Model model) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("login", new Login());
        model.addAttribute("error", model.getAttribute("error"));
        return "login";
    }

    @GetMapping("/show")
    public String showAnalysis(HttpSession session, Model model) {
        if (session.getAttribute("isLogged") == null) {
            model.addAttribute("error", "Please login");
            model.addAttribute("login", new Login());
            return "login";
        }
        model.addAttribute("analyst", analystService.findAll());
        return "show";
    }
}
