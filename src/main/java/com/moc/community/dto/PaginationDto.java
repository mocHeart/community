package com.moc.community.dto;

import com.moc.community.dao.Question;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDto {

    private List<QuestionDto> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private Integer totalPage;
    private List<Integer> pages = new ArrayList<>();

    public void setPagination(Integer totalCount, Integer page, Integer size) {

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }


        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        this.page = page;


        pages.add(page);
        for (int i = 1; i < 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }


        // 是否展示上一页
        showPrevious = page != 1;

        // 是否展示下一页
        showNext = !page.equals(totalPage);

        // 是否展示首页
        showFirstPage = !pages.contains(1);

        // 是否展示末页
        showEndPage = !pages.contains(totalPage);



    }
}
