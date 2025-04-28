package com.example.jpa.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa.entity.Member;
import com.example.jpa.entity.Member.RoleType;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertTest() {
        IntStream.rangeClosed(1, 20).forEach(i -> {
            Member member = Member.builder()
                    .name("홍길동" + i)
                    .roleType(RoleType.USER)
                    .age(i + 5)
                    .description("user " + i)
                    .build();

            memberRepository.save(member);
        });
    }

    @Test
    public void queryDslTest() {

        // where name = '홍길동3'
        System.out.println(memberRepository.findAll(member.name.eq));
        // where age > 15

        // where roleType = USER

        // where name like '%길동%'

        // 전체 조회 후 no의 내림차순으로 정렬하여 출력

    }

}
