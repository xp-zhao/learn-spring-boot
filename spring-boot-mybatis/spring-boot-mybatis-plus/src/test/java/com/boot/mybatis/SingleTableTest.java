package com.boot.mybatis;

import cn.hutool.core.util.RandomUtil;
import com.boot.mybatis.entity.SingleTable;
import com.boot.mybatis.service.SingleTableService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class SingleTableTest {
    @Autowired
    private SingleTableService singleTableService;

    @Test
    public void testQuery(){
        System.out.println(singleTableService.count());
    }

    @Test
    public void testInsert(){
        List<SingleTable> list = new ArrayList<> (100);
        for (int i = 0; i < 10000; i++) {
            SingleTable item = new SingleTable();
            item.setKey1(RandomUtil.randomString(5));
            item.setKey2(i + 1);
            item.setKey3(RandomUtil.randomString(5));
            item.setKeyPart1(RandomUtil.randomString(5));
            item.setKeyPart2(RandomUtil.randomString(5));
            item.setKeyPart3(RandomUtil.randomString(5));
            item.setCommonField(RandomUtil.randomString(5));
            list.add(item);
            if(list.size() == 100){
                singleTableService.saveBatch(list);
                list.clear();
            }
        }
    }

}
