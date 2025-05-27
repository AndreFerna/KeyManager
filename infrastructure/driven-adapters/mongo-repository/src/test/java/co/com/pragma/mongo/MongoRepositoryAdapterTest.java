package co.com.pragma.mongo;

import co.com.pragma.model.key.KeyInformation;
import co.com.pragma.mongo.entities.KeyEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.reactivecommons.utils.ObjectMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class MongoRepositoryAdapterTest {

    @Mock
    private MongoDBRepository repository;

    @Mock
    private ObjectMapper mapper;

    @InjectMocks
    private MongoRepositoryAdapter mongoRepositoryAdapter;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        mongoRepositoryAdapter = new MongoRepositoryAdapter(repository, mapper);
    }

    @Test
    void saveKey() {
        KeyInformation keyInformation = new KeyInformation("MSISDN", "3146304163", "ACTIVA", "2025-05-07T19:45:17.788529500", "10234567890", "1193134338"); // puedes setear valores si deseas
        KeyEntity keyEntity = new KeyEntity("3146304163", "MSISDN", "ACTIVA", "2025-05-07T19:45:17.788529500", "1193134338", "10234567890");

        // Mock: convertir dominio a entidad
        when(mapper.map(any(KeyInformation.class), eq(KeyEntity.class))).thenReturn(keyEntity);
        // Mock: guardar y retornar entidad
        when(repository.save(keyEntity)).thenReturn(keyEntity);
        // Mock: convertir entidad a dominio
        when(mapper.map(any(KeyEntity.class), eq(KeyInformation.class))).thenReturn(keyInformation);

        KeyInformation saved = mongoRepositoryAdapter.saveKey(keyInformation);

        assertNotNull(saved);
        verify(repository).save(keyEntity);
    }

    @Test
    void keyById() {
        String id = "3146304163";
        KeyEntity keyEntity = new KeyEntity("3146304163", "MSISDN", "ACTIVA", "2025-05-07T19:45:17.788529500", "1193134338", "10234567890");
        KeyInformation keyInfo = new KeyInformation("MSISDN", "3146304163", "ACTIVA", "2025-05-07T19:45:17.788529500", "10234567890", "1193134338");

        when(repository.findById(id)).thenReturn(java.util.Optional.of(keyEntity));
        when(mapper.map(keyEntity, KeyInformation.class)).thenReturn(keyInfo);

        KeyInformation result = mongoRepositoryAdapter.keyById(id);

        assertNotNull(result);
        verify(repository).findById(id);
    }

    @Test
    void deleteKeyById() {
        String id = "3146304163";

        doNothing().when(repository).deleteById(id);

        String result = mongoRepositoryAdapter.deleteKeyById(id);

        assertEquals("Llave eliminada", result);
        verify(repository).deleteById(id);
    }
}