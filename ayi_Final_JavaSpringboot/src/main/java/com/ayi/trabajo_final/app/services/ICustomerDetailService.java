package com.ayi.trabajo_final.app.services;

import com.ayi.trabajo_final.app.dto.requests.CustomerDetailDTO;
import com.ayi.trabajo_final.app.dto.responses.CustomerDetailResponseDTO;
import com.ayi.trabajo_final.app.dto.responses.CustomerResponseDTO;
import com.ayi.trabajo_final.app.exceptions.DataBaseException;
import com.ayi.trabajo_final.app.exceptions.ReadAccessException;

public interface ICustomerDetailService {
    CustomerDetailResponseDTO addCustomerDetail(CustomerDetailDTO customerDetailDTO, CustomerResponseDTO customerRDTO) throws ReadAccessException, DataBaseException;

    CustomerDetailResponseDTO findDetailById(Long idDetail) throws ReadAccessException;

    void removeCustomerDetailById(Long id) throws ReadAccessException;

    CustomerDetailResponseDTO updateCustomerDetailById(Long id, CustomerDetailDTO customerDTO) throws ReadAccessException;
}
