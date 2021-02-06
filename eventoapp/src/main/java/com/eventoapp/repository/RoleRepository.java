package com.eventoapp.repository;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eventoapp.models.Role;

public interface RoleRepository extends CrudRepository<Role, String>{
	
}
