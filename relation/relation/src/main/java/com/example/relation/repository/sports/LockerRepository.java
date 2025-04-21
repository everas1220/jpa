package com.example.relation.repository.sports;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.relation.entity.sports.Locker;
import com.example.relation.entity.sports.SportsMember;

 
public interface LockerRepository extends JpaRepository<Locker, Long>{ 

}
