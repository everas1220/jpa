package com.example.relation.repository.sports;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.relation.entity.sports.SportsMember;

public interface SportsMemberReopsitory extends JpaRepository<SportsMember, Long> {

}
