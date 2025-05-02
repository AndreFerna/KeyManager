package co.com.pragma.mongo;

import co.com.pragma.model.key.KeyInformation;
import co.com.pragma.model.key.gateways.KeyGateway;
import co.com.pragma.mongo.entities.KeyEntity;
import co.com.pragma.mongo.helper.AdapterOperations;
import co.com.pragma.mongo.mapper.KeyMapper;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MongoRepositoryAdapter extends AdapterOperations<KeyInformation, KeyEntity, String, MongoDBRepository> implements KeyGateway
// implements ModelRepository from domain
{

    public MongoRepositoryAdapter(MongoDBRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, KeyInformation.class));
    }

    @Override
    public KeyInformation saveKey(KeyInformation keyInformation) {
        KeyEntity keyEntity = saveData(KeyMapper.toEntity(keyInformation));
        return KeyMapper.toDomain(keyEntity);
    }
}
