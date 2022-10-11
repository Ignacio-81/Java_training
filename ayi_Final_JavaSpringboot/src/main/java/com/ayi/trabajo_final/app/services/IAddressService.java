package com.ayi.trabajo_final.app.services;

import com.ayi.trabajo_final.app.dto.requests.AddressDTO;
import com.ayi.trabajo_final.app.dto.requests.CustomerDTO;
import com.ayi.trabajo_final.app.dto.responses.AddressResponseDTO;
import com.ayi.trabajo_final.app.dto.responses.CustomerResponseDTO;
import com.ayi.trabajo_final.app.exceptions.DataBaseException;
import com.ayi.trabajo_final.app.exceptions.ReadAccessException;

public interface IAddressService {


    AddressResponseDTO addAddress(AddressDTO addressDTO, CustomerResponseDTO customerRDTO) throws ReadAccessException, DataBaseException;

    AddressResponseDTO findAddressById(Long idAddress) throws ReadAccessException;

    void removeAddressById(Long idAddress) throws ReadAccessException;

    AddressResponseDTO updateAddressById(Long idAddress, AddressDTO addressDTO) throws ReadAccessException;
}
