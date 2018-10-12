package com.neo.repository;

import com.neo.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserData, Long> {

    UserData findByStudentId(String studentId);

    void  deleteByStudentId (String studentId);


}
