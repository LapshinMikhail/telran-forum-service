package telran.java41.accounting.service;

import telran.java41.accounting.dto.RolesResponseDto;
import telran.java41.accounting.dto.UserAccountResponceDto;
import telran.java41.accounting.dto.UserRegistrDto;
import telran.java41.accounting.dto.UserUpdateDto;

public interface UserAccountService {
	
	UserAccountResponceDto addUser(UserRegistrDto userRegistrDto);

	UserAccountResponceDto getUser(String login);

	UserAccountResponceDto removeUser(String login);

	UserAccountResponceDto editUser(String login, UserUpdateDto updateDto);

	RolesResponseDto changeRolesList(String login, String role, boolean isAddRole);
	
	void changePassword(String login, String newPassword);
}
