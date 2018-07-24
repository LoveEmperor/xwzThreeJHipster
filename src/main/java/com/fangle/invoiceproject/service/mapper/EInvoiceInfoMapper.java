package com.fangle.invoiceproject.service.mapper;

import com.fangle.invoiceproject.domain.*;
import com.fangle.invoiceproject.service.dto.EInvoiceInfoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EInvoiceInfo and its DTO EInvoiceInfoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EInvoiceInfoMapper extends EntityMapper<EInvoiceInfoDTO, EInvoiceInfo> {



    default EInvoiceInfo fromId(Long id) {
        if (id == null) {
            return null;
        }
        EInvoiceInfo eInvoiceInfo = new EInvoiceInfo();
        eInvoiceInfo.setId(id);
        return eInvoiceInfo;
    }
}
