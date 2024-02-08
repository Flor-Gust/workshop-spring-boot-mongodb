package br.com.florluan.workshopmongo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.florluan.workshopmongo.domain.Post;
import br.com.florluan.workshopmongo.repository.PostRepository;
import br.com.florluan.workshopmongo.service.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;
	
	public Post findById(String id) {
		Post post = repo.findById(id)
			.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
		return post;
	}
	
	public List<Post> findByTitle(String text){
		return repo.searchTitle(text);
	}
	
}
