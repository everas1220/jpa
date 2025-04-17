package com.example.jpa.repository;

import java.util.stream.LongStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.jpa.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

@SpringBootTest
public interface MemoRepositoryTest {

    @Autowired
    private MemoRepository memoRepository;

    // test 메소드 작성
    @Test
    public void insertTest() {
        LongStream.range(1, 10).forEach(i -> {
            Memo memo = Memo.builder().memoText("memoText"+i).build();
            memoRepository.save(memo);
        });
    }

    @Test
    public void updateTest() {
        Memo memo = Memo.builder().mno(1L).memoText("memoText update").build();
        memoRepository.save(memo);
        
    }

    @Test
    public void readTest(){
        
    }

    @Test
    public void listTest(){
        
    }

    @Test
    public void deleteTest(){
        
    }
}
