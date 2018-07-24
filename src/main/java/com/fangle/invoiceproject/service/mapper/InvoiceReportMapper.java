package com.fangle.invoiceproject.service.mapper;

import com.fangle.invoiceproject.domain.*;
import com.fangle.invoiceproject.service.dto.InvoiceReportDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity InvoiceReport and its DTO InvoiceReportDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InvoiceReportMapper extends EntityMapper<InvoiceReportDTO, InvoiceReport> {



    default InvoiceReport fromId(Long id) {
        if (id == null) {
            return null;
        }
        InvoiceReport invoiceReport = new InvoiceReport();
        invoiceReport.setId(id);
        return invoiceReport;
    }
}
