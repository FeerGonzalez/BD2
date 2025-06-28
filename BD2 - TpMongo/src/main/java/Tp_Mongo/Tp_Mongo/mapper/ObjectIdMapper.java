package Tp_Mongo.Tp_Mongo.mapper;

import Tp_Mongo.Tp_Mongo.dto.ObjectIdDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public class ObjectIdMapper {

    @Named("mapObjectId")
    public ObjectIdDTO map(String id) {
        if (id == null) {
            return null;
        }
        return new ObjectIdDTO(id);
    }

    @Named("mapStringId")
    public String map(ObjectIdDTO objectIdDto) {
        if (objectIdDto == null) {
            return null;
        }
        return objectIdDto.getOid();
    }
}
