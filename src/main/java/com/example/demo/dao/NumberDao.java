package com.example.demo.dao;

import com.example.demo.demo.number;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NumberDao extends JpaRepository<number,Long> {

}
