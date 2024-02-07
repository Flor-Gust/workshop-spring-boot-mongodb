package br.com.florluan.workshopmongo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.florluan.workshopmongo.domain.User;
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
}
