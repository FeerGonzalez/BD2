package Tp_Mongo.Tp_Mongo.dto;

import Tp_Mongo.Tp_Mongo.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private ObjectIdDTO _id;
    private String title;
    private String resume;
    private String text;
    private List<String> tags;
    private List<String> relatedlinks;
    private String author;
    private String date;

    public PostDTO(Post post) {
        this._id = new ObjectIdDTO(post.getId());
        this.title = post.getTitle();
        this.resume = post.getResume();
        this.text = post.getText();
        this.tags = post.getTags();
        this.relatedlinks = post.getRelatedlinks();
        this.author = post.getAuthor();
        this.date = post.getDate().toString();
    }

    public PostDTO(String id, String title, String resume) {
        this._id = new ObjectIdDTO(id);
        this.title = title;
        this.resume = resume;
    }

    public PostDTO(String id, String title, String resume, String author, LocalDate date) {
        this._id = new ObjectIdDTO(id);
        this.title = title;
        this.resume = resume;
        this.author = author;
        this.date = date.toString();
    }
}