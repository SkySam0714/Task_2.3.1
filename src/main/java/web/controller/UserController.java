package web.controller;

import models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.UserService;
import service.UserServiceImpl;

@Controller
public class UserController {
    UserService userService = new UserServiceImpl();

    @GetMapping("/users")
    public String printUsers(ModelMap model){
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping(value = "/users", params = "updateUserForm")
    public String updateUserForm(@RequestParam("updateUserForm") Long id, ModelMap model){
        model.addAttribute("user_id",id);
        System.out.println("user update "+id);
        return "update_user_form";
    }

    @GetMapping(value = "/users", params = "addUserForm")
    public String addUserForm(){
        return "add_user_form";
    }


    @PostMapping(value = "/users", params = "changeUser")
    public String changeUser(@RequestParam("changeUser") Long id, @ModelAttribute User user, ModelMap model) {
        user.setId(id);
        userService.updateUser(user);
        return printUsers(model);
    }

    @PostMapping(value = "/users", params = "deleteUser")
    public String deleteUser(@RequestParam("deleteUser") Long id, ModelMap model){
        userService.deleteUserById(id);
        return printUsers(model);
    }

    @PostMapping(value = "/users", params = "addUser")
    public String createUser(@ModelAttribute User user, ModelMap model){
        UserService userService = new UserServiceImpl();
        userService.createUser(user);
        return printUsers(model);
    }
}
