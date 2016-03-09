package com.restapicep.service;

import com.restapicep.error.RecordNotFoundException;
import com.restapicep.model.Address;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
@Transactional
public class AddressServiceImpl implements AddressService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressServiceImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    public AddressServiceImpl() {
    }


    public void addAddress(Address address) {

        if (findAddress(address.getCep()) != null) {
            throw new RecordNotFoundException("O endereço informado já foi cadastrado.");
        }

        sessionFactory.getCurrentSession().save(address);

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Added new address {}", address);
        }
    }

    @Transactional(readOnly = true)
    public Address getAddress(String addressCep) {

        Address address = findAddress(addressCep);

        if (address == null) {
            throw new RecordNotFoundException("O CEP informado não foi encontrado.");
        }

        return address;
    }

    @Transactional(readOnly = true)
    public Address findAddress(String addressCep) {
        Address address = (Address) sessionFactory.getCurrentSession().get(Address.class, addressCep.replaceAll("\\D", ""));
        return address;
    }

    public void updateAddress(Address address) {

        sessionFactory.getCurrentSession().update(address);

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Updated address {}", address);
        }
    }

    public void deleteAddress(String addresCep) {

        Address address = getAddress(addresCep);
        sessionFactory.getCurrentSession().delete(address);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Deleted address {}", address);
        }
    }


    @Transactional(readOnly = true)
    public List<Address> getAllAddresses() {
        return sessionFactory.getCurrentSession()
                .createCriteria(Address.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }

}
