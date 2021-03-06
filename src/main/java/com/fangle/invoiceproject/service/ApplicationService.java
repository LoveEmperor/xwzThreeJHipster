package com.fangle.invoiceproject.service;

import com.fangle.invoiceproject.repository.ApplicationRepository;
import com.fangle.invoiceproject.service.dto.ApplicationDTO;
import com.fangle.invoiceproject.service.mapper.ApplicationMapper;
import com.fangle.invoiceproject.domain.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing Application.
 */
@Service
@Transactional
public class ApplicationService {

    private final Logger log = LoggerFactory.getLogger(ApplicationService.class);

    private final ApplicationRepository applicationRepository;

    private final ApplicationMapper applicationMapper;

    public ApplicationService(ApplicationRepository applicationRepository, ApplicationMapper applicationMapper) {
        this.applicationRepository = applicationRepository;
        this.applicationMapper = applicationMapper;
    }

    /**
     * Save a application.
     *
     * @param applicationDTO the entity to save
     * @return the persisted entity
     */
    public ApplicationDTO save(ApplicationDTO applicationDTO) {
        log.debug("Request to save Application : {}", applicationDTO);
        Application application = applicationMapper.toEntity(applicationDTO);
        application = applicationRepository.save(application);
        return applicationMapper.toDto(application);
    }

    /**
     * Get all the applications.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ApplicationDTO> findAll() {
        log.debug("Request to get all Applications");
        return applicationRepository.findAll().stream()
            .map(applicationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one application by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<ApplicationDTO> findOne(Long id) {
        log.debug("Request to get Application : {}", id);
        return applicationRepository.findById(id)
            .map(applicationMapper::toDto);
    }

    /**
     * Delete the application by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Application : {}", id);
        applicationRepository.deleteById(id);
    }
}
