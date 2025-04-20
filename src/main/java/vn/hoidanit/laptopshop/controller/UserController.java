package vn.hoidanit.laptopshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UserService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class UserController {

    // DI : dependency injection
    private final UserService userService;

    @GetMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> userList = userService.handleShowAllUser();
        model.addAttribute("userList", userList);
        return "admin/user/user-list";
    }

    @GetMapping("/admin/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/user-create";
    }

    @PostMapping("/admin/user/create")
    public String postCreateUser(@ModelAttribute("newUser") User newUser, Model model) {
        userService.handleSaveUser(newUser);
        // Redirect need a link not a view. Redirect to link, then get that link by GET
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/{id}")
    public String getUserViewPage(@PathVariable("id") long id, Model model) {
        User user = userService.handleFindUserById(id);
        model.addAttribute("user", user);
        return "admin/user/user-view";
    }

    @GetMapping("/admin/user/update/{id}")
    public String getUpdateUserPage(@PathVariable("id") long id, Model model) {
        User user = userService.handleFindUserById(id);
        model.addAttribute("updateUser", user);
        return "admin/user/user-update";
    }

    @PostMapping("/admin/user/update")
    public String postUpdateUser(@ModelAttribute("updateUser") User updateUser, Model model) {
        userService.handleUpdateUser(updateUser);
        // Redirect need a link not a view. Redirect to link, then get that link by GET
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String getDeleteUserPage(@PathVariable("id") long id, Model model) {
        User user = userService.handleFindUserById(id);
        model.addAttribute("deleteUser", user);
        return "admin/user/user-delete";
    }

    @PostMapping("/admin/user/delete")
    public String postDeleteUser(@ModelAttribute("deleteUser") User deleteUser, Model model) {
        userService.handleDeleteUserById(deleteUser.getId());
        // Redirect need a link not a view. Redirect to link, then get that link by GET
        return "redirect:/admin/user";
    }

}
