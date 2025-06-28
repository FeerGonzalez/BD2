package Tp_Mongo.Tp_Mongo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    private String id;

    private String title;
    private String text;
    private String resume;
    private List<String> tags;
    private List<String> relatedlinks;
    private String author;
    private LocalDate date;

    public Post(String title, String text, String resume, List<String> tags,
                List<String> relatedLinks, String author, LocalDate date) {
        this.title = title;
        this.text = text;
        this.resume = resume;
        this.tags = tags;
        this.relatedlinks = relatedLinks;
        this.author = author;
        this.date = date;
    }
}