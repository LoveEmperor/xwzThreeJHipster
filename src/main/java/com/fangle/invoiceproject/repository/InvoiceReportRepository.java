package com.fangle.invoiceproject.repository;

import com.fangle.invoiceproject.domain.InvoiceReport;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the InvoiceReport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvoiceReportRepository extends JpaRepository<InvoiceReport, Long> {

}
