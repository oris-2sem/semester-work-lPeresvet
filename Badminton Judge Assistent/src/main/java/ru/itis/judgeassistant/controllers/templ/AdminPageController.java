package ru.itis.judgeassistant.controllers.templ;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/control")
@RequiredArgsConstructor
public class AdminPageController {


    @GetMapping("/admin")
    public String controlPanel(Model model) {
        model.addAttribute("isAdminPage", true);
        model.addAttribute("isAdmin", false);
        return "admin/page";
    }

    @GetMapping("/judge")
    public String judgePanel(HttpServletRequest request, Model model) {
        Authentication auth = (Authentication) request.getSession().getAttribute("auth");
        GrantedAuthority authority = auth.getAuthorities().stream().findFirst().get();
        if (authority.getAuthority().equals("ADMIN")) {
            model.addAttribute("isAdmin", true);
        } else {
            model.addAttribute("isAdmin", false);
        }
        model.addAttribute("isAdminPage", false);

        return "judge/page";
    }
}
