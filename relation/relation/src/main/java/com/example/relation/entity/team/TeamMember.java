package com.example.relation.entity.team;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// 회원은 단 하나의 팀에 소속된다.

@ToString(exclude = "team")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class TeamMember {
    // id, name(회원명)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String userName;

    // 외래키의 필드명을 지정
    // @JoinColumn 을 미사용시 default 값은 table명_pk명 으로 지정됨
    @JoinColumn(name = "team_id")
    @ManyToOne
    private Team team;
}
