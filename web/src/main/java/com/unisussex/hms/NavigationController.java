package com.unisussex.hms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class NavigationController {
    private static final Logger logger = LoggerFactory.getLogger(NavigationController.class);

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userInSession");
        session.invalidate();
        return "login";
    }

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        if(isSessionInactive(session)){
            return "login";
        }
        logger.info("In base controller...");
        logger.info("redirecting to home...");
        return "index";
    }

    @GetMapping("/users")
    public String users(HttpSession session, Model model) {
        logger.info("In users controller...");
        if(isSessionInactive(session)){
            return "login";
        }
//        initialiseModelFromSessionAttributes(model, session);
//        if(!userHasAccess(session, FeaturesEnum.USERS.getName())){
//            model.addAttribute("REQUESTING_SECURE_VIEW","Resource is not available to your role");
//            return "welcome";
//        }
        logger.info("redirecting to users...");
        return "user";
    }

    @GetMapping("/roles")
    public String roles(HttpSession session, Model model) {
        if(isSessionInactive(session)){
            return "login";
        }
        logger.info("In roles controller...");
        logger.info("redirecting to roles...");
        return "role";
    }

    @GetMapping("/patients")
    public String patients(HttpSession session, Model model) {
        if(isSessionInactive(session)){
            return "login";
        }
        logger.info("In patients controller...");

        logger.info("redirecting to patients...");
        return "patient";
    }

    @GetMapping("/appointments")
    public String appointments(HttpSession session, Model model) {
        if(isSessionInactive(session)){
            return "login";
        }
        logger.info("In appointments controller...");
        logger.info("redirecting to appointments...");
        return "appointment";
    }
    @GetMapping("/labresults")
    public String Results(HttpSession session, Model model) {
        if(isSessionInactive(session)){
            return "login";
        }
        logger.info("In results controller...");
        logger.info("redirecting to results...");
        return "labresult";
    }

    private boolean isSessionInactive(HttpSession session){
        if(session==null || session.getAttribute("userInSession") == null){
            return true;
        }
        return false;
    }

    private void initialiseModelFromSessionAttributes(Model model, HttpSession session){
        model.addAttribute("name", session.getAttribute("userInSession"));
        model.addAttribute("userInSessionRole", session.getAttribute("userInSessionRole"));
        model.addAttribute("userFeaturesInRole", session.getAttribute("userFeaturesInRole"));
    }

    private boolean userHasAccess(HttpSession session, String resource){
        List<String> fList = (List<String>) session.getAttribute("userFeaturesInRole");
        if(fList.contains(resource))
            return true;
        return false;
    }
}
