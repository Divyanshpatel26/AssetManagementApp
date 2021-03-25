package com.cg.ama.service;

import java.util.List;

import com.cg.ama.exception.DuplicateEntryException;
import com.cg.ama.exception.UserNotFoundException;
import com.cg.ama.model.UserModel;

public interface IAdminUserService {
	UserModel getUserById(Long userId) throws UserNotFoundException;
	UserModel addUser(UserModel userModel) throws DuplicateEntryException;
	List<UserModel> getUsers() throws UserNotFoundException;
	UserModel modifyUser(Long userId, UserModel userModel) throws UserNotFoundException;
	String deleteUserById(Long userId) throws UserNotFoundException;
}
