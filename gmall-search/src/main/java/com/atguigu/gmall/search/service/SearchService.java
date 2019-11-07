package com.atguigu.gmall.search.service;

import com.atguigu.gmall.search.vo.SearchParamVO;
import com.atguigu.gmall.search.vo.SearchResponse;

/**
 * @author shkstart
 * @create 2019-11-07 18:55
 */
public interface SearchService {
    public SearchResponse search(SearchParamVO searchParamVO);
}
