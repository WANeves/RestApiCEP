package com.restapicep.controller;

import com.restapicep.error.RecordNotFoundException;
import com.restapicep.model.Address;
import com.restapicep.model.AddressDTO;
import com.restapicep.service.AddressService;
import com.restapicep.util.ApiResponse;
import com.restapicep.util.ApiResponse.ApiError;
import com.restapicep.util.ApiResponse.Status;
import com.restapicep.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest-api")
public class AddressesController {

    static final String MSG_CEP_INVALID = "O CEP informado é inválido.";
    static final String MSG_CEP_NOT_FOUND = "O CEP informado não foi encontrado.";
    static final String MSG_CEP_SUCESS = "O endereço foi cadastrado com sucesso.";
    static final String MSG_CEP_ERROR_ADD = "O endereço informado já foi cadastrado.";

    @Autowired
    private AddressService addressService;

    public AddressesController() {
    }

    public AddressesController(AddressService service) {
        this.addressService = service;
    }

    @RequestMapping(value = "/addresses", method = RequestMethod.GET)
    public String addressesList(Model model) {
        model.addAttribute("addresses", addressService.getAllAddresses());
        return "addresses";
    }

    @RequestMapping(value = "/busca/all", method = RequestMethod.GET, produces = {"application/json"})
    public
    @ResponseBody
    List<Address> getAllAddresses() throws Exception {
        return addressService.getAllAddresses();
    }

    @RequestMapping(value = "/busca/{cepEscolhido}", method = RequestMethod.GET)
    public ApiResponse getAddress(@PathVariable("cepEscolhido") String cep) {

        try {

            if (!Util.isValidCep(cep)) {
                return new ApiResponse(Status.ERRO, null, new ApiError(MSG_CEP_INVALID));
            }

            Address address = addressService.getAddress(cep);
            return new ApiResponse(Status.SUCESSO, new AddressDTO(address));
        } catch (RecordNotFoundException e) {
            return new ApiResponse(Status.ERRO, null, new ApiError(MSG_CEP_NOT_FOUND));
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ApiResponse addAddress(@RequestBody AddressDTO address) {
        try {
            createAddress(address);
            return new ApiResponse(Status.SUCESSO, new ApiError(MSG_CEP_SUCESS));
        } catch (RecordNotFoundException e) {
            return new ApiResponse(Status.ERRO, null, new ApiError(MSG_CEP_ERROR_ADD));
        }
    }

    private Address createAddress(AddressDTO address) {
        return createAddress(address.getCep(),
                address.getEndereco(),
                address.getBairro(),
                address.getCidade(),
                address.getEstado());
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ApiResponse addAddress(String cep, String endereco, String bairro, String cidade, String estado) {
        createAddress(cep, endereco, bairro, cidade, estado);
        return new ApiResponse(Status.SUCESSO, new ApiError(MSG_CEP_SUCESS));
    }

    private Address createAddress(String cep, String endereco, String bairro, String cidade, String estado) {
        Address address = new Address();
        address.setCep(cep);
        address.setEndereco(endereco);
        address.setBairro(bairro);
        address.setCidade(cidade);
        address.setEstado(estado);

        addressService.addAddress(address);
        return address;
    }

    @RequestMapping(value = "/update/{cepEscolhido}", method = RequestMethod.PUT)
    public ApiResponse updateAddress(@PathVariable("cepEscolhido") String cep, @RequestBody AddressDTO updatedAddress) {
        try {

            if (!Util.isValidCep(cep)) {
                return new ApiResponse(Status.ERRO, null, new ApiError(MSG_CEP_INVALID));
            }

            Address address = addressService.getAddress(cep);
            updatedAddress(updatedAddress, address);
            return new ApiResponse(Status.SUCESSO, new AddressDTO(address));
        } catch (RecordNotFoundException e) {
            return new ApiResponse(Status.ERRO, null, new ApiError("O endereço não existe."));
        }
    }

//    @RequestMapping(value = "/update/{cepEscolhido}", method = RequestMethod.POST, headers = {"X-HTTP-Method-Override=PUT"})
//    public ApiResponse updateAddressAsPost(@PathVariable("cepEscolhido") String cep, @RequestBody AddressDTO updatedAddress) {
//        return updateAddress(cep, updatedAddress);
//    }

    private void updatedAddress(AddressDTO updatedAddress, Address address) {

        address.setEndereco(updatedAddress.getEndereco());
        address.setBairro(updatedAddress.getBairro());
        address.setCidade(updatedAddress.getCidade());
        address.setEstado(updatedAddress.getEstado());

        addressService.updateAddress(address);
    }

    @RequestMapping(value = "/{cepEscolhido}", method = RequestMethod.DELETE)
    public ApiResponse deleteAddress(@PathVariable("cepEscolhido") String cep) {
        try {

            if (!Util.isValidCep(cep)) {
                return new ApiResponse(Status.ERRO, null, new ApiError(MSG_CEP_INVALID));
            }

            Address address = addressService.getAddress(cep);
            addressService.deleteAddress(address.getCep());
            return new ApiResponse(Status.SUCESSO, new ApiError("Endereço removido com sucesso."));
        } catch (RecordNotFoundException e) {
            return new ApiResponse(Status.ERRO, null, new ApiError(MSG_CEP_NOT_FOUND));
        }
    }


}
