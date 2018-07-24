package com.fangle.invoiceproject.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.fangle.invoiceproject.domain.AccessToken;
import com.fangle.invoiceproject.repository.AccessTokenRepository;
import com.fangle.invoiceproject.service.dto.AccessTokenDTO;
import com.fangle.invoiceproject.service.mapper.AccessTokenMapper;
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

import static com.fangle.invoiceproject.web.rest.util.ProjectService.doGetTokenFunc;

/**
 * REST controller for managing AccessToken.
 */
@RestController
@RequestMapping("/api")
public class AccessTokenResource {

    private final Logger log = LoggerFactory.getLogger(AccessTokenResource.class);

    private static final String ENTITY_NAME = "accessToken";

    private final AccessTokenRepository accessTokenRepository;
    private final AccessTokenMapper accessTokenMapper;

    public AccessTokenResource(AccessTokenRepository accessTokenRepository, AccessTokenMapper accessTokenMapper) {
        this.accessTokenRepository = accessTokenRepository;
        this.accessTokenMapper = accessTokenMapper;
    }

    /**
     * POST  /access-tokens : Create a new accessToken.
     *
     * @param
     * @return the ResponseEntity with status 201 (Created) and with body the new accessToken, or with status 400 (Bad Request) if the accessToken has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/access-tokens")
    @Timed
    public ResponseEntity<AccessToken> createAccessToken(@Valid @RequestBody AccessTokenDTO accessTokenDTO) throws URISyntaxException {
        log.debug("REST request to save AccessToken : {}", accessTokenDTO);
//        if (accessToken.getId() != null) {
//            throw new BadRequestAlertException("A new accessToken cannot already have an ID", ENTITY_NAME, "idexists");
//        }
        AccessTokenDTO dto = new AccessTokenDTO();
        AccessToken result = null;
        try {
          dto = doGetTokenFunc();
            AccessToken at = accessTokenMapper.toEntity(dto);
            result = accessTokenRepository.save(at);
        }catch (Exception e){
          e.printStackTrace();
        }

        return ResponseEntity.created(new URI("/api/access-tokens/" + result.getPkey()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getPkey().toString()))
            .body(result);
    }

    /**
     * PUT  /access-tokens : Updates an existing accessToken.
     *
     * @param accessTokenDTO the accessTokenDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated accessTokenDTO,
     * or with status 400 (Bad Request) if the accessTokenDTO is not valid,
     * or with status 500 (Internal Server Error) if the accessTokenDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/access-tokens")
    @Timed
    public ResponseEntity<AccessTokenDTO> updateAccessToken(@Valid @RequestBody AccessTokenDTO accessTokenDTO) throws URISyntaxException {
        log.debug("REST request to update AccessToken : {}", accessTokenDTO);
//        if (accessTokenDTO.getPkey() == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
//        }

        AccessToken accessToken = accessTokenMapper.toEntity(accessTokenDTO);
        accessToken = accessTokenRepository.save(accessToken);
        AccessTokenDTO result = accessTokenMapper.toDto(accessToken);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, accessTokenDTO.getPkey().toString()))
            .body(result);
    }

    /**
     * GET  /access-tokens : get all the accessTokens.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of accessTokens in body
     */
    @GetMapping("/access-tokens")
    @Timed
    public List<AccessTokenDTO> getAllAccessTokens() {
        log.debug("REST request to get all AccessTokens");
        List<AccessToken> accessTokens = accessTokenRepository.findAll();
        return accessTokenMapper.toDto(accessTokens);
    }

    /**
     * GET  /access-tokens/:id : get the "id" accessToken.
     *
     * @param id the id of the accessTokenDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the accessTokenDTO, or with status 404 (Not Found)
     */
    @GetMapping("/access-tokens/{id}")
    @Timed
    public ResponseEntity<AccessTokenDTO> getAccessToken(@PathVariable Long id) {
        log.debug("REST request to get AccessToken : {}", id);
        Optional<AccessTokenDTO> accessTokenDTO = accessTokenRepository.findById(id)
            .map(accessTokenMapper::toDto);
        return ResponseUtil.wrapOrNotFound(accessTokenDTO);
    }

    /**
     * DELETE  /access-tokens/:id : delete the "id" accessToken.
     *
     * @param id the id of the accessToken to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/access-tokens/{id}")
    @Timed
    public ResponseEntity<Void> deleteAccessToken(@PathVariable Long id) {
        log.debug("REST request to delete AccessToken : {}", id);

        accessTokenRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
