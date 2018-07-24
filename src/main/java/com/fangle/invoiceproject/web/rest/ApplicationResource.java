package com.fangle.invoiceproject.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.fangle.invoiceproject.domain.AccessToken;
import com.fangle.invoiceproject.service.ApplicationService;
import com.fangle.invoiceproject.service.dto.*;
import com.fangle.invoiceproject.service.vo.ApplicationVo;
import com.fangle.invoiceproject.web.rest.errors.BadRequestAlertException;
import com.fangle.invoiceproject.web.rest.util.HeaderUtil;
import com.fangle.invoiceproject.web.rest.util.ProjectService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.fangle.invoiceproject.web.rest.util.ProjectService.doGetTokenFunc;

/**
 * REST controller for managing Application.
 */
@RestController
@RequestMapping("/api")
public class ApplicationResource {

    private final Logger log = LoggerFactory.getLogger(ApplicationResource.class);

    private static final String ENTITY_NAME = "application";

    private final ApplicationService applicationService;

    public ApplicationResource(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    /**
     * POST  /applications : Create a new application.
     *
     * @param
     * @return the ResponseEntity with status 201 (Created) and with body the new applicationDTO, or with status 400 (Bad Request) if the application has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/applications")
    @Timed
    public AckDto<AccessTokenDTO> createApplication(@Valid @RequestBody ApplicationVo vo) throws IOException {
        String url = "/advSer/getToken?appId=0d0c67b8295a11e7b7434cd30abca22q&appSecret=57111-78743-49030";
        AckDto<AccessTokenDTO> result =  null;
//        AckDto<AccessTokenDTO> result =  ProjectService.doHttpFun(url,vo , AccessToken.class);

//        ApplicationDTO result = applicationService.save(applicationDTO.getDataObj());
//        return ResponseEntity.created(new URI("/api/applications/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
//            .body(result);

        return result;
    }

    /**
     * PUT  /applications : Updates an existing application.
     *
     * @param applicationDTO the applicationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated applicationDTO,
     * or with status 400 (Bad Request) if the applicationDTO is not valid,
     * or with status 500 (Internal Server Error) if the applicationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/applications")
    @Timed
    public ResponseEntity<ApplicationDTO> updateApplication(@Valid @RequestBody ApplicationDTO applicationDTO) throws URISyntaxException {
        log.debug("REST request to update Application : {}", applicationDTO);
        if (applicationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ApplicationDTO result = applicationService.save(applicationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, applicationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /applications : get all the applications.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of applications in body
     */
    @GetMapping("/applications")
    @Timed
    public List<ApplicationDTO> getAllApplications() {
        log.debug("REST request to get all Applications");
        return applicationService.findAll();
    }

    /**
     * GET  /applications/:id : get the "id" application.
     *
     * @param id the id of the applicationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the applicationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/applications/{id}")
    @Timed
    public ResponseEntity<ApplicationDTO> getApplication(@PathVariable Long id) {
        log.debug("REST request to get Application : {}", id);
        Optional<ApplicationDTO> applicationDTO = applicationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(applicationDTO);
    }

    /**
     * DELETE  /applications/:id : delete the "id" application.
     *
     * @param id the id of the applicationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/applications/{id}")
    @Timed
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        log.debug("REST request to delete Application : {}", id);
        applicationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
