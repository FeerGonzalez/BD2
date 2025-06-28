package Tp_Mongo.Tp_Mongo.web;

import Tp_Mongo.Tp_Mongo.dto.AutorCantidadDTO;
import Tp_Mongo.Tp_Mongo.dto.PostDTO;
import Tp_Mongo.Tp_Mongo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GeneralController {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    @GetMapping("/byauthor")
    public List<AutorCantidadDTO> getAuthorsWithPostCount() {
        GroupOperation groupOperation = Aggregation.group("author").count().as("count");
        Aggregation aggregation = Aggregation.newAggregation(groupOperation);
        AggregationResults<AutorCantidadDTO> results = mongoTemplate.aggregate(aggregation, "posts", AutorCantidadDTO.class);
        return results.getMappedResults();
        /*
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.group("author").count().as("count"),
                Aggregation.project("count").and("author").previousOperation()
        );

        AggregationResults<AutorCantidadDTO> results = mongoTemplate.aggregate(agg, "post", AutorCantidadDTO.class);
        return results.getMappedResults();

         */
    }

    /*
    @GetMapping("/search/{text}")
    public List<PostDTO> searchByText(@PathVariable String text) {
        return postRepository.findByTextContainingIgnoreCase(text)
                .stream()
                .map(post -> new PostDTO(post.getId(), post.getTitle(), post.getResume(), post.getAuthor(), post.getDate()))
                .toList();
    }
    
     */
    @GetMapping("/search/{text}")
    public List<PostDTO> searchByText(@PathVariable String text) {
        return postRepository.searchInTitleTextOrTags(text)
                .stream()
                .map(post -> new PostDTO(post.getId(), post.getTitle(), post.getResume(), post.getAuthor(), post.getDate()))
                .toList();
    }
}
