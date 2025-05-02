package co.com.pragma.mongo.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "customer")
public class CustomerEntity {
    @Id
    @Field("_id")
    private String number;
    private String type;
}
