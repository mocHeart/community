package com.moc.community.controller;

import com.moc.community.dao.Comment;
import com.moc.community.dto.ResultDto;
import com.moc.community.dao.User;
import com.moc.community.dto.CommentCreateDto;
import com.moc.community.exception.CustomizeErrorCodeEnum;
import com.moc.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {


    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    @ResponseBody
    public Object post(@RequestBody CommentCreateDto commentCreateDto,
                       HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDto.errorOf(CustomizeErrorCodeEnum.NO_LOGIN);
        }

        if (commentCreateDto == null || StringUtils.isBlank(commentCreateDto.getContent())) {
            return ResultDto.errorOf(CustomizeErrorCodeEnum.COMMENT_IS_EMPTY);
        }


        Comment comment = new Comment();
        comment.setParentId(commentCreateDto.getParentId());
        comment.setContent(commentCreateDto.getContent());
        comment.setType(commentCreateDto.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(1L);
        comment.setLikeCount(0L);
        commentService.insert(comment);
        return ResultDto.okOf();
    }

}
