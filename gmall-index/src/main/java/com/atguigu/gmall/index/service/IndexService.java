package com.atguigu.gmall.index.service;

import com.atguigu.gmall.pms.entity.CategoryEntity;
import com.atguigu.gmall.pms.vo.CategoryVO;

import java.util.List;

/**
 * @author shkstart
 * @create 2019-11-10 13:53
 */
public interface IndexService {

    List<CategoryEntity> queryLevelCategory();

    List<CategoryVO> queryCategoryWhithSub(Long pid);

    String testLock();

    String testRead();

    String testWrite();

    String latch() throws InterruptedException;

    String out();
}
