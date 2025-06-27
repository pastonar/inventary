package com.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warehouse.domain.UserEntity;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	 Optional<UserEntity> findByUsername(String username);
}
