package hello;

import javax.inject.Inject;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.TwitterAccount;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

    private LinkedIn linkedin;
    @Inject
    private Facebook facebook;
    @Inject
    private TwitterAccount twitter;
    @Inject
	private ConnectionRepository connectionRepository;

    @Inject
    public HelloController(LinkedIn linkedin, ConnectionRepository connectionRepository) {
        this.linkedin = linkedin;
		this.connectionRepository = connectionRepository;
    }

    @RequestMapping(value="/",method=RequestMethod.GET)
    public String helloLinkedin(Model model) {
        if (connectionRepository.findPrimaryConnection(LinkedIn.class) == null) {
            return "redirect:/connect/linkedin";
        }else if(connectionRepository.findPrimaryConnection(Facebook.class) == null){
        	return "redirect:/connect/facebook";
        }else if(connectionRepository.findPrimaryConnection(TwitterAccount.class) == null){
        	return "redirect:/connect/twitter";
        }
        model.addAttribute("linkedinProfile", linkedin.profileOperations().getUserProfile());
        model.addAttribute("facebookProfile", facebook.userOperations().getUserProfile());
        model.addAttribute("twitterProfile",twitter.getProviderAccountName());
        return "hello";
        
        
    }

}