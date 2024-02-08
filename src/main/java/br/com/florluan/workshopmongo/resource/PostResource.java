package br.com.florluan.workshopmongo.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.florluan.workshopmongo.domain.Post;
import br.com.florluan.workshopmongo.service.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostResource {

	@Autowired
	private PostService service;
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Post> findById(@PathVariable(value = "id") String id){
		Post post = service.findById(id);
		return ResponseEntity.ok().body(post);
	}

}
