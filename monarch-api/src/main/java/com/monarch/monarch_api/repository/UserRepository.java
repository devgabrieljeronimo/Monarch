package com.monarch.monarch_api.repository;

import com.monarch.monarch_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
