package co.com.pragma.model.key.gateways;

import co.com.pragma.model.key.CustomerInformation;

public interface IdentificationGateway {

    CustomerInformation getIdentificationId(String id);

}
