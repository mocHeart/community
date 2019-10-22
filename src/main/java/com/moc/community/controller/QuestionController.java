package com.moc.community.controller;

import com.moc.community.dto.CommentCreateDto;
import com.moc.community.dto.CommentDto;
import com.moc.community.dto.QuestionDto;
import com.moc.community.service.CommentService;
import com.moc.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id, Model model) {

        QuestionDto questionDto = questionService.getById(id);
        List<CommentDto> comments = commentService.listByQuestionId(id);

        // 累加阅读数
        questionService.incView(id);
        model.addAttribute("question", questionDto);
        model.addAttribute("comments", comments);
        return "question";
    }


}
