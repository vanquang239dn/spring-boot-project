package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.RoleService;
import vn.hoidanit.laptopshop.service.UploadService;
import vn.hoidanit.laptopshop.service.UserService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class UserController {

    // DI : dependency injection
    private final UserService userService;
    private final RoleService roleService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;

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
    public String postCreateUser(Model model,
            @ModelAttribute("newUser") User newUser,
            @RequestParam("avatarFile") MultipartFile avatarFile) {

        String uploadImageName = "";
        String hashPassword = "";

        uploadImageName = uploadService.handleSaveUploadFile(avatarFile, "avatar");
        hashPassword = passwordEncoder.encode(newUser.getPassword());

        newUser.setAvatar(uploadImageName);
        newUser.setPassword(hashPassword);
        newUser.setRole(roleService.handleFindRoleByRoleName(newUser.getRole().getName()));
        userService.handleSaveUser(newUser);
        // Redirect need a link not a view. Redirect to link, then get that link by GET
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/{id}")
    public String getUserViewPage(@PathVariable("id") long id, Model model) {
        User user = userService.handleFindUserById(id);
        model.addAttribute("user", user);
        return "admin/user/user-detail";
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
