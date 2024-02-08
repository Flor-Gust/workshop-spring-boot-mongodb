package br.com.florluan.workshopmongo.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.florluan.workshopmongo.domain.Post;
import br.com.florluan.workshopmongo.domain.User;
import br.com.florluan.workshopmongo.dto.UserDTO;
import br.com.florluan.workshopmongo.service.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	//crud de usuarios
	
	@GetMapping(produces = "application/json")
	public List<UserDTO> findAll(){
		return service.findAll();
	}
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public UserDTO findById(@PathVariable(value = "id") String id){
		return service.findById(id);
	}
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public UserDTO create(@RequestBody UserDTO objDto){
		return service.create(objDto);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable(value = "id") String id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(consumes = "application/json", produces = "application/json")
	public UserDTO update(@RequestBody UserDTO objDto){
		return service.update(objDto);
	}
	
	//posts de um usuario
	
	@GetMapping(value = "/{id}/posts", produces = "application/json")
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
		User obj = service.findByIdPosts(id);
		return ResponseEntity.ok().body(obj.getPosts());
	}
	
	
}
