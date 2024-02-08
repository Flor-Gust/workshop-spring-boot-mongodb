package br.com.florluan.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.florluan.workshopmongo.domain.Post;
import br.com.florluan.workshopmongo.domain.User;
import br.com.florluan.workshopmongo.dto.AuthorDTO;
import br.com.florluan.workshopmongo.repository.PostRepository;
import br.com.florluan.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		List<User> listUser = new ArrayList<>();
		listUser.addAll(Arrays.asList(maria, alex, bob));
		
		userRepository.saveAll(listUser);
		
		Post post1 = new Post(null
				,sdf.parse("21/03/2018")
				,"Partiu viagem"
				,"Vou viajar para SP. Abraços!"
				,new AuthorDTO(maria));
		Post post2 = new Post(null
				,sdf.parse("23/03/2018")
				,"Bom dia"
				,"Acordei feliz hoje!"
				, new AuthorDTO(maria));
		List<Post> listPost = new ArrayList<>();
		listPost.addAll(Arrays.asList(post1, post2));
		
		postRepository.saveAll(listPost);
	}
}
