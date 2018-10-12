package com.neo.repository;

import com.neo.entity.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TableRepository extends JpaRepository<TimeTable, Long> {

    List<TimeTable> findByStudentId(String studentId);

    void  deleteByStudentId (String studentId);


}
