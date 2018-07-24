package com.fangle.invoiceproject.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.fangle.invoiceproject.domain.InvoiceReport;
import com.fangle.invoiceproject.repository.InvoiceReportRepository;
import com.fangle.invoiceproject.service.dto.InvoiceReportDTO;
import com.fangle.invoiceproject.service.mapper.InvoiceReportMapper;
import com.fangle.invoiceproject.web.rest.errors.BadRequestAlertException;
import com.fangle.invoiceproject.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing InvoiceReport.
 */
@RestController
@RequestMapping("/api")
public class InvoiceReportResource {

    private final Logger log = LoggerFactory.getLogger(InvoiceReportResource.class);

    private static final String ENTITY_NAME = "invoiceReport";

    private final InvoiceReportRepository invoiceReportRepository;

    private final InvoiceReportMapper invoiceReportMapper;

    public InvoiceReportResource(InvoiceReportRepository invoiceReportRepository, InvoiceReportMapper invoiceReportMapper) {
        this.invoiceReportRepository = invoiceReportRepository;
        this.invoiceReportMapper = invoiceReportMapper;
    }

    /**
     * POST  /invoice-reports : Create a new invoiceReport.
     *
     * @param invoiceReportDTO the invoiceReportDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new invoiceReportDTO, or with status 400 (Bad Request) if the invoiceReport has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/invoice-reports")
    @Timed
    public ResponseEntity<InvoiceReportDTO> createInvoiceReport(@Valid @RequestBody InvoiceReportDTO invoiceReportDTO) throws URISyntaxException {
        log.debug("REST request to save InvoiceReport : {}", invoiceReportDTO);
        if (invoiceReportDTO.getId() != null) {
            throw new BadRequestAlertException("A new invoiceReport cannot already have an ID", ENTITY_NAME, "idexists");
        }

        InvoiceReport invoiceReport = invoiceReportMapper.toEntity(invoiceReportDTO);
        invoiceReport = invoiceReportRepository.save(invoiceReport);
        InvoiceReportDTO result = invoiceReportMapper.toDto(invoiceReport);
        return ResponseEntity.created(new URI("/api/invoice-reports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /invoice-reports : Updates an existing invoiceReport.
     *
     * @param invoiceReportDTO the invoiceReportDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated invoiceReportDTO,
     * or with status 400 (Bad Request) if the invoiceReportDTO is not valid,
     * or with status 500 (Internal Server Error) if the invoiceReportDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/invoice-reports")
    @Timed
    public ResponseEntity<InvoiceReportDTO> updateInvoiceReport(@Valid @RequestBody InvoiceReportDTO invoiceReportDTO) throws URISyntaxException {
        log.debug("REST request to update InvoiceReport : {}", invoiceReportDTO);
        if (invoiceReportDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        InvoiceReport invoiceReport = invoiceReportMapper.toEntity(invoiceReportDTO);
        invoiceReport = invoiceReportRepository.save(invoiceReport);
        InvoiceReportDTO result = invoiceReportMapper.toDto(invoiceReport);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, invoiceReportDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /invoice-reports : get all the invoiceReports.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of invoiceReports in body
     */
    @GetMapping("/invoice-reports")
    @Timed
    public List<InvoiceReportDTO> getAllInvoiceReports() {
        log.debug("REST request to get all InvoiceReports");
        List<InvoiceReport> invoiceReports = invoiceReportRepository.findAll();
        return invoiceReportMapper.toDto(invoiceReports);
    }

    /**
     * GET  /invoice-reports/:id : get the "id" invoiceReport.
     *
     * @param id the id of the invoiceReportDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the invoiceReportDTO, or with status 404 (Not Found)
     */
    @GetMapping("/invoice-reports/{id}")
    @Timed
    public ResponseEntity<InvoiceReportDTO> getInvoiceReport(@PathVariable Long id) {
        log.debug("REST request to get InvoiceReport : {}", id);
        Optional<InvoiceReportDTO> invoiceReportDTO = invoiceReportRepository.findById(id)
            .map(invoiceReportMapper::toDto);
        return ResponseUtil.wrapOrNotFound(invoiceReportDTO);
    }

    /**
     * DELETE  /invoice-reports/:id : delete the "id" invoiceReport.
     *
     * @param id the id of the invoiceReportDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/invoice-reports/{id}")
    @Timed
    public ResponseEntity<Void> deleteInvoiceReport(@PathVariable Long id) {
        log.debug("REST request to delete InvoiceReport : {}", id);

        invoiceReportRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
