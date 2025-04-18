package com.example.jpa.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa.dto.MemoDTO;
import com.example.jpa.service.MemoService;

@SpringBootTest
public class MemoServiceTest {

    @Autowired
    private MemoService memoService;

    @Test
    public void getListTest() {
        List<MemoDTO> list = memoService.getList();
        list.forEach(dto -> System.out.println(dto));
    }

    @Test
    public void getRwoTest() {
        MemoDTO dto = memoService.getRow(2L);
        System.out.println(dto);
    }

    @Test
    public void memoDeleteTest() {
        memoService.memoDelete(5L);
    }
}
