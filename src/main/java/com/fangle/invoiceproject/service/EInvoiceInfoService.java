package com.fangle.invoiceproject.service;

import com.fangle.invoiceproject.repository.EInvoiceInfoRepository;
import com.fangle.invoiceproject.service.dto.EInvoiceInfoDTO;
import com.fangle.invoiceproject.service.mapper.EInvoiceInfoMapper;
import com.fangle.invoiceproject.domain.EInvoiceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing EInvoiceInfo.
 */
@Service
@Transactional
public class EInvoiceInfoService {

    private final Logger log = LoggerFactory.getLogger(EInvoiceInfoService.class);

    private final EInvoiceInfoRepository eInvoiceInfoRepository;

    private final EInvoiceInfoMapper eInvoiceInfoMapper;

    public EInvoiceInfoService(EInvoiceInfoRepository eInvoiceInfoRepository, EInvoiceInfoMapper eInvoiceInfoMapper) {
        this.eInvoiceInfoRepository = eInvoiceInfoRepository;
        this.eInvoiceInfoMapper = eInvoiceInfoMapper;
    }

    /**
     * Save a eInvoiceInfo.
     *
     * @param eInvoiceInfoDTO the entity to save
     * @return the persisted entity
     */
    public EInvoiceInfoDTO save(EInvoiceInfoDTO eInvoiceInfoDTO) {
        log.debug("Request to save EInvoiceInfo : {}", eInvoiceInfoDTO);
        EInvoiceInfo eInvoiceInfo = eInvoiceInfoMapper.toEntity(eInvoiceInfoDTO);
        eInvoiceInfo = eInvoiceInfoRepository.save(eInvoiceInfo);
        return eInvoiceInfoMapper.toDto(eInvoiceInfo);
    }

    /**
     * Get all the eInvoiceInfos.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<EInvoiceInfoDTO> findAll() {
        log.debug("Request to get all EInvoiceInfos");
        return eInvoiceInfoRepository.findAll().stream()
            .map(eInvoiceInfoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one eInvoiceInfo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<EInvoiceInfoDTO> findOne(Long id) {
        log.debug("Request to get EInvoiceInfo : {}", id);
        return eInvoiceInfoRepository.findById(id)
            .map(eInvoiceInfoMapper::toDto);
    }

    /**
     * Delete the eInvoiceInfo by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete EInvoiceInfo : {}", id);
        eInvoiceInfoRepository.deleteById(id);
    }
}
