package mmt.main.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MakeMytripController 
{
	@GetMapping("/makemytrip")
	public String displayname()
	{
		return "Welcome To Make My Trip ......... ";
	}

}
