package com.apisero.service;

import java.util.List;

import com.apisero.model.UserDTO;

public interface UserService {
    public void createUser(UserDTO user);
    public List<UserDTO> getUser();
    public UserDTO findById(long id);
    public UserDTO update(UserDTO user, long l);
    public void deleteUserById(long id);
    public UserDTO updatePartially(UserDTO user, long id);
}