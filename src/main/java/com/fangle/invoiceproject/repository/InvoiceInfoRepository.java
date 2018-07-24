package com.fangle.invoiceproject.repository;

import com.fangle.invoiceproject.domain.InvoiceInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the InvoiceInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvoiceInfoRepository extends JpaRepository<InvoiceInfo, Long> {

}
