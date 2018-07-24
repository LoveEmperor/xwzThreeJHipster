package com.fangle.invoiceproject.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.fangle.invoiceproject.service.InvoiceInfoService;
import com.fangle.invoiceproject.service.dto.InvoiceInfoDTO;
import com.fangle.invoiceproject.service.mapper.InvoiceInfoMapper;
import com.fangle.invoiceproject.service.vo.OpenInvoiceInfoVo;
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

import static com.fangle.invoiceproject.web.rest.util.ProjectService.doSubmitInvoiceInfoFunc;

/**
 * REST controller for managing InvoiceInfo.
 */
@RestController
@RequestMapping("/api")
public class InvoiceInfoResource {
    private final Logger log = LoggerFactory.getLogger(InvoiceInfoResource.class);

    private static final String ENTITY_NAME = "invoiceInfo";

    private final InvoiceInfoService invoiceInfoService;
    private final InvoiceInfoMapper invoiceInfoMapper;

    public InvoiceInfoResource(InvoiceInfoService invoiceInfoService,InvoiceInfoMapper invoiceInfoMapper) {
        this.invoiceInfoService = invoiceInfoService;
        this.invoiceInfoMapper = invoiceInfoMapper;
    }

    /**
     * POST  /invoice-infos : Create a new invoiceInfo.
     *
     * @param
     * @return the ResponseEntity with status 201 (Created) and with body the new invoiceInfoDTO, or with status 400 (Bad Request) if the invoiceInfo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/invoice-infos")
    @Timed
    public ResponseEntity<InvoiceInfoDTO> createInvoiceInfo(@Valid @RequestBody OpenInvoiceInfoVo infoVo) throws URISyntaxException {
        log.debug("REST request to save InvoiceInfo : {}", infoVo);
//        if (invoiceInfoDTO.getId() != null) {
//            throw new BadRequestAlertException("A new invoiceInfo cannot already have an ID", ENTITY_NAME, "idexists");
//        }

        InvoiceInfoDTO dto = new InvoiceInfoDTO();
        String url = "/advSer/st/openfp";
        InvoiceInfoDTO result = null;
        try {
            dto = doSubmitInvoiceInfoFunc(infoVo);
            result  = invoiceInfoService.save(dto);
        }catch (Exception e){
           e.printStackTrace();
        }

        return ResponseEntity.created(new URI("/api/invoice-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /invoice-infos : Updates an existing invoiceInfo.
     *
     * @param invoiceInfoDTO the invoiceInfoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated invoiceInfoDTO,
     * or with status 400 (Bad Request) if the invoiceInfoDTO is not valid,
     * or with status 500 (Internal Server Error) if the invoiceInfoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/invoice-infos")
    @Timed
    public ResponseEntity<InvoiceInfoDTO> updateInvoiceInfo(@Valid @RequestBody InvoiceInfoDTO invoiceInfoDTO) throws URISyntaxException {
        log.debug("REST request to update InvoiceInfo : {}", invoiceInfoDTO);
        if (invoiceInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InvoiceInfoDTO result = invoiceInfoService.save(invoiceInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, invoiceInfoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /invoice-infos : get all the invoiceInfos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of invoiceInfos in body
     */
    @GetMapping("/invoice-infos")
    @Timed
    public List<InvoiceInfoDTO> getAllInvoiceInfos() {
        log.debug("REST request to get all InvoiceInfos");
        return invoiceInfoService.findAll();
    }

    /**
     * GET  /invoice-infos/:id : get the "id" invoiceInfo.
     *
     * @param id the id of the invoiceInfoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the invoiceInfoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/invoice-infos/{id}")
    @Timed
    public ResponseEntity<InvoiceInfoDTO> getInvoiceInfo(@PathVariable Long id) {
        log.debug("REST request to get InvoiceInfo : {}", id);
        Optional<InvoiceInfoDTO> invoiceInfoDTO = invoiceInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(invoiceInfoDTO);
    }

    /**
     * DELETE  /invoice-infos/:id : delete the "id" invoiceInfo.
     *
     * @param id the id of the invoiceInfoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/invoice-infos/{id}")
    @Timed
    public ResponseEntity<Void> deleteInvoiceInfo(@PathVariable Long id) {
        log.debug("REST request to delete InvoiceInfo : {}", id);
        invoiceInfoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
