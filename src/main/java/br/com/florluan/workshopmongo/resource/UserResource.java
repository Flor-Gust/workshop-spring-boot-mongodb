package br.com.florluan.workshopmongo.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.florluan.workshopmongo.domain.User;
import br.com.florluan.workshopmongo.dto.UserDTO;
import br.com.florluan.workshopmongo.service.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
		List<User> list = service.findAll();
		List<UserDTO> listDto = 
				list.stream()
					.map(x -> new UserDTO(x))
						.collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
}
