package com.fangle.invoiceproject.repository;

import com.fangle.invoiceproject.domain.EInvoiceInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EInvoiceInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EInvoiceInfoRepository extends JpaRepository<EInvoiceInfo, Long> {

}
