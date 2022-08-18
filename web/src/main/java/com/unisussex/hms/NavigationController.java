package com.unisussex.hms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class NavigationController {
    private static final Logger logger = LoggerFactory.getLogger(NavigationController.class);

    @GetMapping("/users")
    public String users(HttpSession session, Model model) {
        logger.info("In users controller...");
//        if(isSessionInactive(session)){
//            return "login";
//        }
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
        logger.info("In roles controller...");
        logger.info("redirecting to roles...");
        return "role";
    }

    @GetMapping("/patients")
    public String patients(HttpSession session, Model model) {
        logger.info("In patients controller...");

        logger.info("redirecting to patients...");
        return "patient";
    }

    @GetMapping("/appointments")
    public String appointments(HttpSession session, Model model) {
        logger.info("In appointments controller...");
        logger.info("redirecting to appointments...");
        return "appointment";
    }
}
