package com.gubin.cdek.repository;

import com.gubin.cdek.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
