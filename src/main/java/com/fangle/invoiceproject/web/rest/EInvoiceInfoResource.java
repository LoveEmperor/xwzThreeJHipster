package com.fangle.invoiceproject.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.fangle.invoiceproject.service.EInvoiceInfoService;
import com.fangle.invoiceproject.service.dto.EInvoiceInfoDTO;
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
 * REST controller for managing EInvoiceInfo.
 */
@RestController
@RequestMapping("/api")
public class EInvoiceInfoResource {

    private final Logger log = LoggerFactory.getLogger(EInvoiceInfoResource.class);

    private static final String ENTITY_NAME = "eInvoiceInfo";

    private final EInvoiceInfoService eInvoiceInfoService;

    public EInvoiceInfoResource(EInvoiceInfoService eInvoiceInfoService) {
        this.eInvoiceInfoService = eInvoiceInfoService;
    }

    /**
     * POST  /e-invoice-infos : Create a new eInvoiceInfo.
     *
     * @param eInvoiceInfoDTO the eInvoiceInfoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new eInvoiceInfoDTO, or with status 400 (Bad Request) if the eInvoiceInfo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/e-invoice-infos")
    @Timed
    public ResponseEntity<EInvoiceInfoDTO> createEInvoiceInfo(@Valid @RequestBody EInvoiceInfoDTO eInvoiceInfoDTO) throws URISyntaxException {
        log.debug("REST request to save EInvoiceInfo : {}", eInvoiceInfoDTO);
        if (eInvoiceInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new eInvoiceInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EInvoiceInfoDTO result = eInvoiceInfoService.save(eInvoiceInfoDTO);
        return ResponseEntity.created(new URI("/api/e-invoice-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /e-invoice-infos : Updates an existing eInvoiceInfo.
     *
     * @param eInvoiceInfoDTO the eInvoiceInfoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated eInvoiceInfoDTO,
     * or with status 400 (Bad Request) if the eInvoiceInfoDTO is not valid,
     * or with status 500 (Internal Server Error) if the eInvoiceInfoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/e-invoice-infos")
    @Timed
    public ResponseEntity<EInvoiceInfoDTO> updateEInvoiceInfo(@Valid @RequestBody EInvoiceInfoDTO eInvoiceInfoDTO) throws URISyntaxException {
        log.debug("REST request to update EInvoiceInfo : {}", eInvoiceInfoDTO);
        if (eInvoiceInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EInvoiceInfoDTO result = eInvoiceInfoService.save(eInvoiceInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, eInvoiceInfoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /e-invoice-infos : get all the eInvoiceInfos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of eInvoiceInfos in body
     */
    @GetMapping("/e-invoice-infos")
    @Timed
    public List<EInvoiceInfoDTO> getAllEInvoiceInfos() {
        log.debug("REST request to get all EInvoiceInfos");
        return eInvoiceInfoService.findAll();
    }

    /**
     * GET  /e-invoice-infos/:id : get the "id" eInvoiceInfo.
     *
     * @param id the id of the eInvoiceInfoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the eInvoiceInfoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/e-invoice-infos/{id}")
    @Timed
    public ResponseEntity<EInvoiceInfoDTO> getEInvoiceInfo(@PathVariable Long id) {
        log.debug("REST request to get EInvoiceInfo : {}", id);
        Optional<EInvoiceInfoDTO> eInvoiceInfoDTO = eInvoiceInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(eInvoiceInfoDTO);
    }

    /**
     * DELETE  /e-invoice-infos/:id : delete the "id" eInvoiceInfo.
     *
     * @param id the id of the eInvoiceInfoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/e-invoice-infos/{id}")
    @Timed
    public ResponseEntity<Void> deleteEInvoiceInfo(@PathVariable Long id) {
        log.debug("REST request to delete EInvoiceInfo : {}", id);
        eInvoiceInfoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
