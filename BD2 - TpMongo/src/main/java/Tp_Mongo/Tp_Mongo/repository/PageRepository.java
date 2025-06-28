package Tp_Mongo.Tp_Mongo.repository;

import Tp_Mongo.Tp_Mongo.model.Page;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PageRepository extends MongoRepository<Page, String> {}
