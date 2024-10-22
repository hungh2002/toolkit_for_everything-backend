package com.ryu.toolkit_for_everything.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ryu.toolkit_for_everything.entity.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
