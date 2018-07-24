package com.fangle.invoiceproject.service.mapper;

import com.fangle.invoiceproject.domain.*;
import com.fangle.invoiceproject.service.dto.InvoiceInfoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity InvoiceInfo and its DTO InvoiceInfoDTO.
 */
@Mapper(componentModel = "spring", uses = {EInvoiceInfoMapper.class})
public interface InvoiceInfoMapper extends EntityMapper<InvoiceInfoDTO, InvoiceInfo> {

    @Mapping(source = "extract.id", target = "extractId")
    @Mapping(source = "extract.fphm", target = "extractFphm")
    InvoiceInfoDTO toDto(InvoiceInfo invoiceInfo);

    @Mapping(source = "extractId", target = "extract")
    InvoiceInfo toEntity(InvoiceInfoDTO invoiceInfoDTO);

    default InvoiceInfo fromId(Long id) {
        if (id == null) {
            return null;
        }
        InvoiceInfo invoiceInfo = new InvoiceInfo();
        invoiceInfo.setId(id);
        return invoiceInfo;
    }
}
