package br.com.florluan.workshopmongo.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.florluan.workshopmongo.domain.User;
import br.com.florluan.workshopmongo.dto.UserDTO;
import br.com.florluan.workshopmongo.repository.UserRepository;
import br.com.florluan.workshopmongo.resource.UserResource;
import br.com.florluan.workshopmongo.service.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<UserDTO> findAll(){
		List<User> list = repo.findAll();
		List<UserDTO> listDto = 
				list.stream()
					.map(x -> new UserDTO(x))
						.collect(Collectors.toList());
		listDto
		.stream()
		.forEach(p -> p.add(linkTo(methodOn(UserResource.class).findById(p.getId())).withSelfRel()));
		return listDto;
	}
	
	public UserDTO findById(String id) {
		User user = repo.findById(id)
			.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
		UserDTO obj = new UserDTO(user);
		obj.add(linkTo(methodOn(UserResource.class).findById(id)).withSelfRel());
		return obj;
	}
	
	public User findByIdPosts(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public UserDTO create(UserDTO user) {
		if (user == null) throw new ObjectNotFoundException("Objeto não encontrado!");
		
		User obj = fromDTO(user);
		UserDTO newObj = new UserDTO(repo.save(obj));
		newObj.add(linkTo(methodOn(UserResource.class).findById(newObj.getId())).withSelfRel());
		return newObj;
	}
	
	public void delete(String id) {
		var entity = repo.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Não foi encontrado objeto para esse ID!"));
		repo.delete(entity);
	}
	
	public UserDTO update(UserDTO objDto) {
		if (objDto == null) throw new ObjectNotFoundException("Não foi encontrado objeto para esse ID!");
		User user = fromDTO(objDto);
		UpdateData(user, objDto);
		
		UserDTO newDto = new UserDTO(repo.save(user));
		newDto.add(linkTo(methodOn(UserResource.class).findById(newDto.getId())).withSelfRel());
		return newDto;
	}
	
	private void UpdateData(User user, UserDTO obj) {
		user.setName(obj.getName());
		user.setEmail(obj.getEmail());
	}

	public User fromDTO(UserDTO obj) {
		return new User(
					obj.getId(),
					obj.getName(),
					obj.getEmail()
				);
	}
}
