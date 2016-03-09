package com.restapicep.init;

import com.restapicep.error.RecordNotFoundException;
import com.restapicep.model.Address;
import com.restapicep.service.AddressService;


public class RestApiCepServiceInitialiser {

    public static void init(AddressService AddressService) {
        try {
            AddressService.getAddress("15810075");
        } catch (RecordNotFoundException e) {
            // gera dados iniciais
            Address address = new Address();
            address.setCep("15810070");
            address.setEndereco("Rua Queluz");
            address.setBairro("Vila Sotto");
            address.setCidade("Catanduva");
            address.setEstado("SP");

            AddressService.addAddress(address);

            Address address2 = new Address();
            address2.setCep("15810456");
            address2.setEndereco("Rua Itapaci");
            address2.setBairro("Lot. Cidade Jardim");
            address2.setCidade("Catanduva");
            address2.setEstado("SP");

            AddressService.addAddress(address2);
        }
    }
}
