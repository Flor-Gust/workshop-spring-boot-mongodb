package br.com.florluan.workshopmongo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.florluan.workshopmongo.domain.User;
import br.com.florluan.workshopmongo.dto.UserDTO;
import br.com.florluan.workshopmongo.repository.UserRepository;
import br.com.florluan.workshopmongo.service.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id) {
		User user = repo.findById(id)
			.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
		return user;
	}
	
	public User insert(User user) {
		return repo.insert(user);
	}
	
	public void delete(String id) {
		var entity = repo.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Não foi encontrado objeto para esse ID!"));
		repo.delete(entity);
	}
	
	public User fromDTO(UserDTO obj) {
		return new User(
					obj.getId(),
					obj.getName(),
					obj.getEmail()
				);
	}
}
