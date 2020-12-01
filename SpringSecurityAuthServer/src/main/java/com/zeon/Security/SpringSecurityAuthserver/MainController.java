package com.zeon.Security.SpringSecurityAuthserver;

import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


//@RequestMapping("/rest/hello")
@Controller
public class MainController {


    @GetMapping("/principal")
    @ResponseBody
    public Principal getUser(Principal principal,HttpServletRequest request, HttpSession session) {
    	System.out.println("The user name is:" + principal.getName());
    	//session.setAttribute("myuser",principal);
    	//session.setMaxInactiveInterval(60);
        return principal;
    }
    
	////////////////// for login

	@GetMapping("/loginout")
	public String LoginUser(Model model) {
		return "loginUn";
	}
	
	@GetMapping("/home")
	public String gethome(HttpServletRequest request) {
		return "welcomepage";
	}
	
	@GetMapping("/login")
	public String getLogin(Model model) {
		return "login";
	}
	
	 @GetMapping("/oauth_login")
	    public String getLoginPage(Model model) {
		 
//		 Iterable<ClientRegistration> clientRegistrations = null;
//		    ResolvableType type = ResolvableType.forInstance(clientRegistrationRepository)
//		      .as(Iterable.class);
//		    if (type != ResolvableType.NONE && 
//		      ClientRegistration.class.isAssignableFrom(type.resolveGenerics()[0])) {
//		        clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;
//		    }
//		 
//		    clientRegistrations.forEach(registration -> 
//		      oauth2AuthenticationUrls.put(registration.getClientName(), 
//		      authorizationRequestBaseUri + "/" + registration.getRegistrationId()));
//		    model.addAttribute("urls", oauth2AuthenticationUrls);
	        return "loginUn";
	    }
	
    
    @GetMapping("/hello")
    @ResponseBody
    public String helloUser() {
        return "Hello World";
    }

}
