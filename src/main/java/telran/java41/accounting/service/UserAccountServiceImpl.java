package telran.java41.accounting.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.java41.accounting.dao.UserAccountRepository;
import telran.java41.accounting.dto.RolesResponseDto;
import telran.java41.accounting.dto.UserAccountResponceDto;
import telran.java41.accounting.dto.UserRegistrDto;
import telran.java41.accounting.dto.UserUpdateDto;
import telran.java41.accounting.dto.exception.UserExistsException;
import telran.java41.accounting.dto.exception.UserNotExistsException;
import telran.java41.accounting.dto.exception.UserNotFoundException;
import telran.java41.accounting.model.UserAccount;

@Service
public class UserAccountServiceImpl implements UserAccountService {

	UserAccountRepository repository;

	ModelMapper modelMapper;

	@Autowired
	public UserAccountServiceImpl(UserAccountRepository repository, ModelMapper modelMapper) {
		this.repository = repository;
		this.modelMapper = modelMapper;
	}

	@Override
	public UserAccountResponceDto addUser(UserRegistrDto userRegistrDto) {
		if (repository.existsById(userRegistrDto.getLogin())) {
			throw new UserExistsException(userRegistrDto.getLogin());
		}
		UserAccount userAccount = modelMapper.map(userRegistrDto, UserAccount.class);
		repository.save(userAccount);
		return modelMapper.map(userAccount, UserAccountResponceDto.class);
	}

	@Override
	public UserAccountResponceDto getUser(String login) {
		UserAccount userAccount = repository.findById(login).orElseThrow(() -> new UserNotFoundException());
		return modelMapper.map(userAccount, UserAccountResponceDto.class);
	}

	@Override
	public UserAccountResponceDto removeUser(String login) {
		if (!repository.existsById(login)) {
			throw new UserNotExistsException(login);
		}
		UserAccount userAccount = repository.findById(login).orElseThrow(() -> new UserNotFoundException());
		repository.delete(userAccount);
		return modelMapper.map(userAccount, UserAccountResponceDto.class);
	}

	@Override
	public UserAccountResponceDto editUser(String login, UserUpdateDto updateDto) {
		if (!repository.existsById(login)) {
			throw new UserNotExistsException(login);
		}
		UserAccount userAccount = repository.findById(login).orElseThrow(() -> new UserNotFoundException());
		if (updateDto.getFirstName() != null) {
			userAccount.setFirstName(updateDto.getFirstName());
		}
		if (updateDto.getLastName() != null) {
			userAccount.setLastName(updateDto.getLastName());
		}
		repository.save(userAccount);
		return modelMapper.map(userAccount, UserAccountResponceDto.class);
	}

	@Override
	public RolesResponseDto changeRolesList(String login, String role, boolean isAddRole) {
		UserAccount userAccount = repository.findById(login).orElseThrow(() -> new UserNotFoundException());
		if (isAddRole) {
			userAccount.addRole(role);
		} else {
			userAccount.removeRole(role);
		}
		return modelMapper.map(userAccount, RolesResponseDto.class);
	}

	@Override
	public void changePassword(String login, String newPassword) {
		UserAccount userAccount = repository.findById(login).orElseThrow(() -> new UserNotFoundException());
		userAccount.setPassword(newPassword);

	}

}
