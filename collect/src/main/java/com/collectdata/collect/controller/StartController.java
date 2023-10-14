package com.collectdata.collect.controller;

import com.collectdata.collect.model.Data;
import com.collectdata.collect.model.Login;
import com.collectdata.collect.service.IDataService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Controller
public class StartController {
    private final IDataService dataService;
    private final RestTemplate restTemplate;

    @Autowired
    public StartController(IDataService dataService, RestTemplate restTemplate) {
        this.dataService = dataService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/input")
    public String showInputPage(HttpSession session, Model model) {
        if (session.getAttribute("isLogged") == null) {
            model.addAttribute("error", "Please login");
            model.addAttribute("login", new Login());
            return "login";
        }
        model.addAttribute("allData", dataService.findAll());
        model.addAttribute("response", "response");
        return "input";
    }


    @PostMapping("/login")
    public String createSession(@ModelAttribute Login login, HttpSession session, Model model) {
        try {
            ResponseEntity<Map> response = restTemplate.postForEntity("http://authentication:8081/authenticate/login", login, Map.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                session.setAttribute("isLogged", "true");
                return "redirect:/input";
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

    @PostMapping("/new")
    public String add(@RequestParam("val") double value, Model model) {
        Data data = new Data();
        data.setVal(value);
        dataService.saveData(data);
        model.addAttribute("allData", dataService.findAll());
        String response = restTemplate.getForObject("http://analysis:8083/analysis/analyze", String.class);
        model.addAttribute("response", response);
        return "input";
    }
}
