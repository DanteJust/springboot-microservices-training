package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

}