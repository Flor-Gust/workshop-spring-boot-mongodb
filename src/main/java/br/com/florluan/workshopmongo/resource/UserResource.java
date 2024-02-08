package br.com.florluan.workshopmongo.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	public ResponseEntity<List<UserDTO>> findAll(){
		List<User> list = service.findAll();
		List<UserDTO> listDto = 
				list.stream()
					.map(x -> new UserDTO(x))
						.collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<UserDTO> findById(@PathVariable(value = "id") String id){
		User user = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(user));
	}
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto){
		User obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable(value = "id") String id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value="/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable(value = "id") String id){
		User obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	//posts de um usuario
	
	@GetMapping(value = "/{id}/posts", produces = "application/json")
	public ResponseEntity<List<Post>> findPosts(@PathVariable(value = "id") String id){
		User user = service.findById(id);
		return ResponseEntity.ok().body(user.getPosts());
	}
}
