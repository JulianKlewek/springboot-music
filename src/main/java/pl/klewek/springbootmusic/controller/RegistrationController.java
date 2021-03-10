package pl.klewek.springbootmusic.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.klewek.springbootmusic.exception.UserAlreadyExistsException;
import pl.klewek.springbootmusic.exception.UserDoesNotExistsException;
import pl.klewek.springbootmusic.model.User;
import pl.klewek.springbootmusic.model.VerificationToken;
import pl.klewek.springbootmusic.model.dto.UserDto;
import pl.klewek.springbootmusic.service.UserService;
import pl.klewek.springbootmusic.service.VerificationTokenService;
import pl.klewek.springbootmusic.util.RegistrationCompleteEvent;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.ZonedDateTime;

@Controller
public class RegistrationController{

    private final static Logger LOGGER = LogManager.getLogger(RegistrationController.class);

    private UserService userService;
    private ApplicationEventPublisher applicationEventPublisher;
    private VerificationTokenService verificationTokenService;

    @Autowired
    public RegistrationController(UserService userService, ApplicationEventPublisher applicationEventPublisher, VerificationTokenService verificationTokenService) {
        this.userService = userService;
        this.applicationEventPublisher = applicationEventPublisher;
        this.verificationTokenService = verificationTokenService;
    }

    @GetMapping("/registration")
    public String getRegistration(Model model){
        model.addAttribute("user", new UserDto());
        return "registrationPage";
    }

    @PostMapping("/add-user")
    public String addNewUser(@ModelAttribute("user") @Valid UserDto userDto, BindingResult bindingResult, HttpServletRequest request) {
        //ZAIMPLEMENTOWAĆ WALIDACJE PO STRONIE FRONTU
        if(bindingResult.hasErrors()){
            return "registrationPage";
        }
        try {
            String appUrl = request.getContextPath();
            userService.registerNewUser(userDto);
            User newUser = userService.getRegisteredUser(userDto);
            applicationEventPublisher.publishEvent(new RegistrationCompleteEvent(newUser, appUrl));
        } catch (UserAlreadyExistsException uaeExc) {
//            uaeExc.printStackTrace();
            return "registrationPage";
        }catch(UserDoesNotExistsException udseExc) {
            udseExc.printStackTrace();
            return "registrationPage";
        }catch (RuntimeException runExc){
            runExc.getStackTrace();
            return "registrationPage";
        }
        return "redirect:/registration";
    }

    @GetMapping("/registrationConfirm")
    public String confirmRegistration(@RequestParam("token") String token) {
        if(verificationTokenService.isTokenExists(token)){
            VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);
            ZonedDateTime actualTime = ZonedDateTime.now();

            if(!verificationToken.isTokenExpired(actualTime)){
                User user = verificationToken.getUser();
                user.setEnabled(true);
                userService.saveRegisteredUser(user);
                LOGGER.info("User {} verified his account.", user.getEmail());
                userService.addBasicRoleToUser(user);
                return "redirect:/registration";
            }
            //STORNA Z NAGŁOWKIEM
            return "tokenProblem";
        }
        //STORNA Z NAGŁOWKIEM
        return "tokenProblem";
    }
}
