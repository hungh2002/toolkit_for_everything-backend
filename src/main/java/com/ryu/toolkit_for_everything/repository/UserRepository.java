package com.ryu.toolkit_for_everything.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ryu.toolkit_for_everything.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
