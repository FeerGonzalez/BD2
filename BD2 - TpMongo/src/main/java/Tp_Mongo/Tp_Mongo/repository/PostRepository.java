package Tp_Mongo.Tp_Mongo.repository;

import Tp_Mongo.Tp_Mongo.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findTop4ByOrderByDateDesc();
    List<Post> findByAuthor(String author);

    @Query("{ '$or': [ " +
            "{ 'title': { $regex: ?0, $options: 'i' } }, " +
            "{ 'text': { $regex: ?0, $options: 'i' } }, " +
            "{ 'tags': { $regex: ?0, $options: 'i' } } " +
            "] }")
    List<Post> searchInTitleTextOrTags(String text);
}
