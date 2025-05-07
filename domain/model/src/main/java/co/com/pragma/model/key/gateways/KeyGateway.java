package co.com.pragma.model.key.gateways;

import co.com.pragma.model.key.KeyInformation;

public interface KeyGateway {
    KeyInformation saveKey(KeyInformation keyInformation);
    KeyInformation keyById(String id);
    String deleteKeyById(String id);
}
