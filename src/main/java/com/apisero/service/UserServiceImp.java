package com.apisero.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apisero.model.UserDTO;
import com.apisero.repository.UserRepository;

@Service
@Transactional
public class UserServiceImp implements UserService {
	@Autowired
	UserRepository userRepository;

	public void createUser(UserDTO user) {
		userRepository.save(user);
	}

	public List<UserDTO> getUser() {
		return (List<UserDTO>) userRepository.findAll();
	}

	public UserDTO findById(long id) {
		return userRepository.findById(id).get();
	}

	public UserDTO update(UserDTO user, long l) {
		return userRepository.save(user);
	}

	public void deleteUserById(long id) {
		UserDTO user = userRepository.findById(id).get();
		userRepository.delete(user);
	}

	public UserDTO updatePartially(UserDTO user, long id) {
		UserDTO usr = findById(id);
		usr.setCountry(user.getCountry());
		return userRepository.save(usr);
	}
}