package user.main.serviceImpl;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import user.main.entity.UserRegister;
import user.main.exception.UserIdNotFoundException;
import user.main.model.UserRegisterDTO;
import user.main.repository.UserRegisterRepo;
import user.main.service.UserRegisterService;

@Service
public class UserRegisterServiceImpl implements UserRegisterService
{
	@Autowired
	private UserRegisterRepo userregisterrepo;

	@Override
	public UserRegister insertUserRegister(UserRegisterDTO userRegisterdto) 
	{
		UserRegister userregister=new UserRegister();
		try
		{
		userregister.setFname(userRegisterdto.getFname());
		userregister.setLname(userRegisterdto.getLname());
		userregister.setEmail(userRegisterdto.getEmail());
		userregister.setPassword(Base64.getEncoder().encodeToString(userRegisterdto.getPassword().getBytes()));
		userregister.setContact(userRegisterdto.getContact());
		
		userregisterrepo.save(userregister);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return userregister;
	}
	
	/*
	 * @Override public String insertUserRegister(UserRegister userregister) {
	 * userregisterrepo.save(userregister); return "User Saved SuccessFully";
	 * 
	 * }
	 */

	@Override
	public UserRegister checkdetails(UserRegisterDTO userRegisterDTO) 
	{
		UserRegister byEmail = userregisterrepo.findByEmail(userRegisterDTO.getEmail());
		
		if(byEmail!=null)
		{
			String decode=new String(Base64.getDecoder().decode(byEmail.getPassword()));
			if(decode.equals(userRegisterDTO.getPassword()))
			{
				return byEmail;
			}
		}
		
		return null;
	}

	@Override
	public UserRegister checkuserbyid(Long id) 
	{
		 UserRegister retriveduser = userregisterrepo.findById(id).orElseThrow(()->new UserIdNotFoundException("User Id Not Exist"));
		 
		 return retriveduser;
		
	}

	@Override
	@Cacheable(value = "getalluser")
	public List<UserRegister> getalluserdetails() 
	{
		List<UserRegister> list = userregisterrepo.findAll();
		System.out.println("Hit the dtatabase");
		return list;
		
	}
		

}
