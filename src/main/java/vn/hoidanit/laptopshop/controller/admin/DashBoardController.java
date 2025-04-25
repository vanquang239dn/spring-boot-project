package vn.hoidanit.laptopshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashBoardController {

    @GetMapping("/admin/dashboard")
    public String getDashBoard() {
        return "admin/dashboard/dashboard";
    }

}
