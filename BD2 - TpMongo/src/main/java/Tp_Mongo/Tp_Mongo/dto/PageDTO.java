package Tp_Mongo.Tp_Mongo.dto;

import Tp_Mongo.Tp_Mongo.model.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO {

    private ObjectIdDTO _id;
    private String title;
    private String text;
    private String author;
    private String date;

    public PageDTO(Page page) {
        this._id = new ObjectIdDTO(page.getId());
        this.title = page.getTitle();
        this.text = page.getText();
        this.author = page.getAuthor();
        this.date = page.getDate().toString();
    }
}
