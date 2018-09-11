package com.gubin.cdek.controller;

import com.gubin.cdek.form.TermForm;
import com.gubin.cdek.form.UserForm;
import com.gubin.cdek.model.User;
import com.gubin.cdek.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RestController {

    private final UserRepository userRepository;

    public RestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = { "/userList" }, method = RequestMethod.GET)
    public String userList(Model model) {
        TermForm termForm = new TermForm();
        model.addAttribute("termForm", termForm);
        model.addAttribute("users", userRepository.findAll());
        return "userList";
    }


    @RequestMapping(value = { "/filterUsers" }, method = RequestMethod.GET)
    public String showFilteredUserList(Model model, @ModelAttribute("termForm") TermForm termForm) {
        List<User> userList = userRepository.findAll();
        model.addAttribute("filteredUsers",
                userList.stream().filter(user -> user.getName().contains(termForm.getTerm())).collect(Collectors.toList()));
        return "filterUsers";
    }

    @RequestMapping(value = { "/addUser" }, method = RequestMethod.GET)
    public String showAddUserPage(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "addUser";
    }

    @RequestMapping(value = { "/addUser" }, method = RequestMethod.POST)
    public String saveUser(Model model, //
                             @ModelAttribute("userForm") UserForm userForm) {
        String name = userForm.getName();

        if (name != null && name.length() > 0) {
            User newUser = new User(name);
            userRepository.save(newUser);

            return "redirect:/userList";
        }

        return "addUser";
    }


    @RequestMapping(value = { "/userList" }, method = RequestMethod.POST)
    public String filterUsers(Model model, //
                              @ModelAttribute("termForm") TermForm termForm) {

        if (termForm != null &&
                termForm.getTerm() != null &&
                    termForm.getTerm().length() > 0) {
            return "redirect:/filterUsers";
        }
        return "userList";
    }

}


