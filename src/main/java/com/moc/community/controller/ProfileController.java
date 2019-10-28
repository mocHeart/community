package com.moc.community.controller;

import com.moc.community.dao.User;
import com.moc.community.dto.PaginationDto;
import com.moc.community.service.NotificationService;
import com.moc.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "action") String action,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size,
                          Model model) {
        // session中获取到用户信息
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
            PaginationDto paginationDto = questionService.list(user.getId(), page, size);
            model.addAttribute("pagination", paginationDto);
        } else if ("replies".equals(action)) {
            PaginationDto paginationDto = notificationService.list(user.getId(), page, size);
            model.addAttribute("pagination", paginationDto);
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }


        return "profile";
    }

}
