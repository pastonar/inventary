package com.warehouse.repository;



import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.warehouse.domain.RolEntity;

@Repository
public interface RolRepository extends CrudRepository<RolEntity, Long> 
{
//	Optional<RolEntity> findByUsername(String username);
	

}
