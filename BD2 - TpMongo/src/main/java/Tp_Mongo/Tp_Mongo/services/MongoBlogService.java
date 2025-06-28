package Tp_Mongo.Tp_Mongo.services;

import Tp_Mongo.Tp_Mongo.api.BlogService;
import Tp_Mongo.Tp_Mongo.dto.PageDTO;
import Tp_Mongo.Tp_Mongo.dto.PostDTO;
import Tp_Mongo.Tp_Mongo.model.Page;
import Tp_Mongo.Tp_Mongo.model.Post;
import Tp_Mongo.Tp_Mongo.repository.PageRepository;
import Tp_Mongo.Tp_Mongo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MongoBlogService implements BlogService {

    private final PageRepository pageRepository;
    private final PostRepository postRepository;

    @Autowired
    public MongoBlogService(PageRepository pageRepository, PostRepository postRepository) {
        this.pageRepository = pageRepository;
        this.postRepository = postRepository;
    }

    @Override
    public PageDTO obtenerPaginaPorId(String id) {
        Page page = pageRepository.findById(id).orElseThrow(() -> new RuntimeException("Página no encontrada"));
        return new PageDTO(page);
    }

    @Override
    public List<PostDTO> obtenerUltimosPosts() {
        return postRepository.findTop4ByOrderByDateDesc()
                .stream()
                .map(PostDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO obtenerPostPorId(String id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post no encontrado"));
        return new PostDTO(post);
    }
}
