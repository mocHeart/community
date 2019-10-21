package com.moc.community.mapper;

import com.moc.community.dao.Question;
import com.moc.community.dao.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionExtMapper {
    int incView(Question question);
    int incCommentCount(Question record);
}