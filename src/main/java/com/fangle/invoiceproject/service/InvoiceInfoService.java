package com.fangle.invoiceproject.service;

import com.fangle.invoiceproject.repository.InvoiceInfoRepository;
import com.fangle.invoiceproject.service.dto.InvoiceInfoDTO;
import com.fangle.invoiceproject.service.mapper.InvoiceInfoMapper;
import com.fangle.invoiceproject.domain.InvoiceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing InvoiceInfo.
 */
@Service
@Transactional
public class InvoiceInfoService {

    private final Logger log = LoggerFactory.getLogger(InvoiceInfoService.class);

    private final InvoiceInfoRepository invoiceInfoRepository;

    private final InvoiceInfoMapper invoiceInfoMapper;

    public InvoiceInfoService(InvoiceInfoRepository invoiceInfoRepository, InvoiceInfoMapper invoiceInfoMapper) {
        this.invoiceInfoRepository = invoiceInfoRepository;
        this.invoiceInfoMapper = invoiceInfoMapper;
    }

    /**
     * Save a invoiceInfo.
     *
     * @param invoiceInfoDTO the entity to save
     * @return the persisted entity
     */
    public InvoiceInfoDTO save(InvoiceInfoDTO invoiceInfoDTO) {
        log.debug("Request to save InvoiceInfo : {}", invoiceInfoDTO);
        InvoiceInfo invoiceInfo = invoiceInfoMapper.toEntity(invoiceInfoDTO);
        invoiceInfo = invoiceInfoRepository.save(invoiceInfo);
        return invoiceInfoMapper.toDto(invoiceInfo);
    }

    /**
     * Get all the invoiceInfos.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<InvoiceInfoDTO> findAll() {
        log.debug("Request to get all InvoiceInfos");
        return invoiceInfoRepository.findAll().stream()
            .map(invoiceInfoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one invoiceInfo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<InvoiceInfoDTO> findOne(Long id) {
        log.debug("Request to get InvoiceInfo : {}", id);
        return invoiceInfoRepository.findById(id)
            .map(invoiceInfoMapper::toDto);
    }

    /**
     * Delete the invoiceInfo by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete InvoiceInfo : {}", id);
        invoiceInfoRepository.deleteById(id);
    }
}
