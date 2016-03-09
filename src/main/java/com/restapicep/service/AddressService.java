package com.restapicep.service;

import com.restapicep.model.Address;

import java.util.List;

public interface AddressService {

    public void addAddress(Address address);

    public Address getAddress(String addressCep);

    public void updateAddress(Address address);

    public void deleteAddress(String addressCep);

    public Address findAddress(String addressCep);

    public List<Address> getAllAddresses();
}
