package com.mayprayer.common.utils.page;

import com.github.pagehelper.Page;
import com.mayprayer.common.utils.response.R;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PageResult<T> {

    private  Long total ;

    private List<T> data;

    private  Integer currentPage;

    public static PageResult buildPage(Page page){
        return PageResult.builder().total(page.getTotal()).currentPage(page.getPageNum()).data((List) page.getResult()).build();
    }

}
