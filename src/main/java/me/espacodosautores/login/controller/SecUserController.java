package me.espacodosautores.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import me.espacodosautores.login.model.SecUser;
import me.espacodosautores.login.service.SecUserService;

@Controller
public class SecUserController {
	
	@Autowired
    SecUserService secUserService;
	
	@RequestMapping("/")
    public String root(Model model) {
        model.addAttribute("formUser", new SecUser());
        return "login";
    }
	

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("formUser", new SecUser());
        return "login";
    }
    
    @RequestMapping("/home")
    public String home(Model model) {
        return "home";
    }

	@RequestMapping(value = {"/register"}, method = RequestMethod.GET)
	public String getRegistrationPage(Model model) {
		model.addAttribute("formUser", new SecUser());
		
		
		
		return "register";
	}
	
	@RequestMapping(value = {"/register"}, method = RequestMethod.POST)
	public String register(@ModelAttribute("formUser") SecUser formUser) {
		SecUser secUser = secUserService.findByUsername(formUser.getUsername());
		if (secUser != null) {
			System.out.println("User already exists!!!");
            return "redirect:/register";
        }
		formUser.setPassword(new BCryptPasswordEncoder().encode(formUser.getPassword()));
		if (secUserService.save(formUser) != null) {
			System.out.println("Mais Uma Conta Criada");
			
			
			
			return "sucesso";
			
        } else {
        	System.out.println("Cadastro Falho.");
        }

        return "redirect:/register";
	}
}
