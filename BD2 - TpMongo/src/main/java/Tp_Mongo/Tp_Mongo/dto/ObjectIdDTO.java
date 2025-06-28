package Tp_Mongo.Tp_Mongo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ObjectIdDTO {
    @JsonProperty("$oid")
    private String oid;
}
