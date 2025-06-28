package Tp_Mongo.Tp_Mongo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "pages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page {

    @Id
    private String id;

    private String title;
    private String text;
    private String author;
    private LocalDate date;

    public Page(String title, String text, String author, LocalDate date) {
        this.title = title;
        this.text = text;
        this.author = author;
        this.date = date;
    }
}