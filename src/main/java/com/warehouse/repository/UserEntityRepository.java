package com.warehouse.repository;



import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.warehouse.domain.UserEntity;

@Repository
public interface UserEntityRepository extends CrudRepository<UserEntity, Long> 
{
	Optional<UserEntity> findByUsername(String username);
	

}
