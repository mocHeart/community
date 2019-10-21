package com.moc.community.controller;

import com.moc.community.dto.QuestionDto;
import com.moc.community.mapper.QuestionMapper;
import com.moc.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           Model model) {

        QuestionDto questionDto = questionService.getById(id);
        // 累加阅读数
        questionService.incView(id);
        model.addAttribute("question", questionDto);
        return "question";
    }


}
