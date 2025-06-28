package Tp_Mongo.Tp_Mongo.web;

import Tp_Mongo.Tp_Mongo.dto.PostDTO;

import Tp_Mongo.Tp_Mongo.model.Post;
import Tp_Mongo.Tp_Mongo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
@CrossOrigin
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/latest")
    public List<PostDTO> getLatestPosts() {
        return postRepository.findTop4ByOrderByDateDesc()
                .stream()
                .map(post -> new PostDTO(post.getId(), post.getTitle(), post.getResume()))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<PostDTO>> getPostById(@PathVariable String id) {
        List<PostDTO> listaPost = new ArrayList<>();
        Optional<Post> post = postRepository.findById(id);
        PostDTO postDTO = new PostDTO(post.get());
        listaPost.add(postDTO);
        return ResponseEntity.ok(listaPost);

    }

    @GetMapping("/author/{author}")
    public List<PostDTO> getPostsByAuthor(@PathVariable String author) {
        return postRepository.findByAuthor(author)
                .stream()
                .map(PostDTO::new)
                .toList();
    }

    @PostMapping("/crear")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post savedPost = postRepository.save(post);
        return ResponseEntity.ok(savedPost);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostDTO> postDTOs = posts.stream()
                .map(PostDTO::new)
                .toList();
        return ResponseEntity.ok(postDTOs);
    }
}

