package Tp_Mongo.Tp_Mongo.api;



import Tp_Mongo.Tp_Mongo.dto.PageDTO;
import Tp_Mongo.Tp_Mongo.dto.PostDTO;

import java.util.List;

public interface BlogService {

    PageDTO obtenerPaginaPorId(String id);

    List<PostDTO> obtenerUltimosPosts();

    PostDTO obtenerPostPorId(String id);
}