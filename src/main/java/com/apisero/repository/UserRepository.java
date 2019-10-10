package com.apisero.repository;

import org.springframework.data.repository.CrudRepository;

import com.apisero.model.UserDTO;

public interface UserRepository extends CrudRepository<UserDTO, Long>{

}
