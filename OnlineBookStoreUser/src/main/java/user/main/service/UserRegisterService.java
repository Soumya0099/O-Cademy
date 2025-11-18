package user.main.service;

import java.util.List;

import user.main.entity.UserRegister;
import user.main.model.UserRegisterDTO;

public interface UserRegisterService 
{
	public UserRegister insertUserRegister(UserRegisterDTO userRegisterdto);

	public UserRegister checkdetails(UserRegisterDTO userRegisterDTO);

	public UserRegister checkuserbyid(Long id);

	public List<UserRegister> getalluserdetails();

}
