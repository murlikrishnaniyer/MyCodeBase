package com.spring.crud.service;

import java.util.List;

import com.spring.crud.entity.UserEntity;

public interface JPAExampleService {
	UserEntity saveUserData(UserEntity userEntity);

	List<UserEntity> getUserData();

	UserEntity updateUserData(UserEntity userEntity);

	void deleteUserData(Long id);
}
