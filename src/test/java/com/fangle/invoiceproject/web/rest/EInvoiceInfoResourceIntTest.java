package com.fangle.invoiceproject.web.rest;

import com.fangle.invoiceproject.InvoiceprojectApp;

import com.fangle.invoiceproject.domain.EInvoiceInfo;
import com.fangle.invoiceproject.repository.EInvoiceInfoRepository;
import com.fangle.invoiceproject.service.EInvoiceInfoService;
import com.fangle.invoiceproject.service.dto.EInvoiceInfoDTO;
import com.fangle.invoiceproject.service.mapper.EInvoiceInfoMapper;
import com.fangle.invoiceproject.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static com.fangle.invoiceproject.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the EInvoiceInfoResource REST controller.
 *
 * @see EInvoiceInfoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvoiceprojectApp.class)
public class EInvoiceInfoResourceIntTest {

    private static final String DEFAULT_FPDM = "AAAAAAAAAA";
    private static final String UPDATED_FPDM = "BBBBBBBBBB";

    private static final String DEFAULT_FPHM = "AAAAAAAA";
    private static final String UPDATED_FPHM = "BBBBBBBB";

    private static final String DEFAULT_KPRQ = "AAAAAAAAAA";
    private static final String UPDATED_KPRQ = "BBBBBBBBBB";

    private static final String DEFAULT_XFSH = "AAAAAAAAAA";
    private static final String UPDATED_XFSH = "BBBBBBBBBB";

    private static final String DEFAULT_XFMC = "AAAAAAAAAA";
    private static final String UPDATED_XFMC = "BBBBBBBBBB";

    private static final String DEFAULT_GFSH = "AAAAAAAAAA";
    private static final String UPDATED_GFSH = "BBBBBBBBBB";

    private static final String DEFAULT_GFMC = "AAAAAAAAAA";
    private static final String UPDATED_GFMC = "BBBBBBBBBB";

    private static final String DEFAULT_JEHJ = "AAAAAAAAAA";
    private static final String UPDATED_JEHJ = "BBBBBBBBBB";

    private static final String DEFAULT_SEHJ = "AAAAAAAAAA";
    private static final String UPDATED_SEHJ = "BBBBBBBBBB";

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private EInvoiceInfoRepository eInvoiceInfoRepository;


    @Autowired
    private EInvoiceInfoMapper eInvoiceInfoMapper;


    @Autowired
    private EInvoiceInfoService eInvoiceInfoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEInvoiceInfoMockMvc;

    private EInvoiceInfo eInvoiceInfo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EInvoiceInfoResource eInvoiceInfoResource = new EInvoiceInfoResource(eInvoiceInfoService);
        this.restEInvoiceInfoMockMvc = MockMvcBuilders.standaloneSetup(eInvoiceInfoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EInvoiceInfo createEntity(EntityManager em) {
        EInvoiceInfo eInvoiceInfo = new EInvoiceInfo()
            .fpdm(DEFAULT_FPDM)
            .fphm(DEFAULT_FPHM)
            .kprq(DEFAULT_KPRQ)
            .xfsh(DEFAULT_XFSH)
            .xfmc(DEFAULT_XFMC)
            .gfsh(DEFAULT_GFSH)
            .gfmc(DEFAULT_GFMC)
            .jehj(DEFAULT_JEHJ)
            .sehj(DEFAULT_SEHJ)
            .url(DEFAULT_URL)
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME);
        return eInvoiceInfo;
    }

    @Before
    public void initTest() {
        eInvoiceInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createEInvoiceInfo() throws Exception {
        int databaseSizeBeforeCreate = eInvoiceInfoRepository.findAll().size();

        // Create the EInvoiceInfo
        EInvoiceInfoDTO eInvoiceInfoDTO = eInvoiceInfoMapper.toDto(eInvoiceInfo);
        restEInvoiceInfoMockMvc.perform(post("/api/e-invoice-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eInvoiceInfoDTO)))
            .andExpect(status().isCreated());

        // Validate the EInvoiceInfo in the database
        List<EInvoiceInfo> eInvoiceInfoList = eInvoiceInfoRepository.findAll();
        assertThat(eInvoiceInfoList).hasSize(databaseSizeBeforeCreate + 1);
        EInvoiceInfo testEInvoiceInfo = eInvoiceInfoList.get(eInvoiceInfoList.size() - 1);
        assertThat(testEInvoiceInfo.getFpdm()).isEqualTo(DEFAULT_FPDM);
        assertThat(testEInvoiceInfo.getFphm()).isEqualTo(DEFAULT_FPHM);
        assertThat(testEInvoiceInfo.getKprq()).isEqualTo(DEFAULT_KPRQ);
        assertThat(testEInvoiceInfo.getXfsh()).isEqualTo(DEFAULT_XFSH);
        assertThat(testEInvoiceInfo.getXfmc()).isEqualTo(DEFAULT_XFMC);
        assertThat(testEInvoiceInfo.getGfsh()).isEqualTo(DEFAULT_GFSH);
        assertThat(testEInvoiceInfo.getGfmc()).isEqualTo(DEFAULT_GFMC);
        assertThat(testEInvoiceInfo.getJehj()).isEqualTo(DEFAULT_JEHJ);
        assertThat(testEInvoiceInfo.getSehj()).isEqualTo(DEFAULT_SEHJ);
        assertThat(testEInvoiceInfo.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testEInvoiceInfo.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testEInvoiceInfo.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void createEInvoiceInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = eInvoiceInfoRepository.findAll().size();

        // Create the EInvoiceInfo with an existing ID
        eInvoiceInfo.setId(1L);
        EInvoiceInfoDTO eInvoiceInfoDTO = eInvoiceInfoMapper.toDto(eInvoiceInfo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEInvoiceInfoMockMvc.perform(post("/api/e-invoice-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eInvoiceInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EInvoiceInfo in the database
        List<EInvoiceInfo> eInvoiceInfoList = eInvoiceInfoRepository.findAll();
        assertThat(eInvoiceInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkFpdmIsRequired() throws Exception {
        int databaseSizeBeforeTest = eInvoiceInfoRepository.findAll().size();
        // set the field null
        eInvoiceInfo.setFpdm(null);

        // Create the EInvoiceInfo, which fails.
        EInvoiceInfoDTO eInvoiceInfoDTO = eInvoiceInfoMapper.toDto(eInvoiceInfo);

        restEInvoiceInfoMockMvc.perform(post("/api/e-invoice-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eInvoiceInfoDTO)))
            .andExpect(status().isBadRequest());

        List<EInvoiceInfo> eInvoiceInfoList = eInvoiceInfoRepository.findAll();
        assertThat(eInvoiceInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFphmIsRequired() throws Exception {
        int databaseSizeBeforeTest = eInvoiceInfoRepository.findAll().size();
        // set the field null
        eInvoiceInfo.setFphm(null);

        // Create the EInvoiceInfo, which fails.
        EInvoiceInfoDTO eInvoiceInfoDTO = eInvoiceInfoMapper.toDto(eInvoiceInfo);

        restEInvoiceInfoMockMvc.perform(post("/api/e-invoice-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eInvoiceInfoDTO)))
            .andExpect(status().isBadRequest());

        List<EInvoiceInfo> eInvoiceInfoList = eInvoiceInfoRepository.findAll();
        assertThat(eInvoiceInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEInvoiceInfos() throws Exception {
        // Initialize the database
        eInvoiceInfoRepository.saveAndFlush(eInvoiceInfo);

        // Get all the eInvoiceInfoList
        restEInvoiceInfoMockMvc.perform(get("/api/e-invoice-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(eInvoiceInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].fpdm").value(hasItem(DEFAULT_FPDM.toString())))
            .andExpect(jsonPath("$.[*].fphm").value(hasItem(DEFAULT_FPHM.toString())))
            .andExpect(jsonPath("$.[*].kprq").value(hasItem(DEFAULT_KPRQ.toString())))
            .andExpect(jsonPath("$.[*].xfsh").value(hasItem(DEFAULT_XFSH.toString())))
            .andExpect(jsonPath("$.[*].xfmc").value(hasItem(DEFAULT_XFMC.toString())))
            .andExpect(jsonPath("$.[*].gfsh").value(hasItem(DEFAULT_GFSH.toString())))
            .andExpect(jsonPath("$.[*].gfmc").value(hasItem(DEFAULT_GFMC.toString())))
            .andExpect(jsonPath("$.[*].jehj").value(hasItem(DEFAULT_JEHJ.toString())))
            .andExpect(jsonPath("$.[*].sehj").value(hasItem(DEFAULT_SEHJ.toString())))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL.toString())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())));
    }


    @Test
    @Transactional
    public void getEInvoiceInfo() throws Exception {
        // Initialize the database
        eInvoiceInfoRepository.saveAndFlush(eInvoiceInfo);

        // Get the eInvoiceInfo
        restEInvoiceInfoMockMvc.perform(get("/api/e-invoice-infos/{id}", eInvoiceInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(eInvoiceInfo.getId().intValue()))
            .andExpect(jsonPath("$.fpdm").value(DEFAULT_FPDM.toString()))
            .andExpect(jsonPath("$.fphm").value(DEFAULT_FPHM.toString()))
            .andExpect(jsonPath("$.kprq").value(DEFAULT_KPRQ.toString()))
            .andExpect(jsonPath("$.xfsh").value(DEFAULT_XFSH.toString()))
            .andExpect(jsonPath("$.xfmc").value(DEFAULT_XFMC.toString()))
            .andExpect(jsonPath("$.gfsh").value(DEFAULT_GFSH.toString()))
            .andExpect(jsonPath("$.gfmc").value(DEFAULT_GFMC.toString()))
            .andExpect(jsonPath("$.jehj").value(DEFAULT_JEHJ.toString()))
            .andExpect(jsonPath("$.sehj").value(DEFAULT_SEHJ.toString()))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL.toString()))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingEInvoiceInfo() throws Exception {
        // Get the eInvoiceInfo
        restEInvoiceInfoMockMvc.perform(get("/api/e-invoice-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEInvoiceInfo() throws Exception {
        // Initialize the database
        eInvoiceInfoRepository.saveAndFlush(eInvoiceInfo);

        int databaseSizeBeforeUpdate = eInvoiceInfoRepository.findAll().size();

        // Update the eInvoiceInfo
        EInvoiceInfo updatedEInvoiceInfo = eInvoiceInfoRepository.findById(eInvoiceInfo.getId()).get();
        // Disconnect from session so that the updates on updatedEInvoiceInfo are not directly saved in db
        em.detach(updatedEInvoiceInfo);
        updatedEInvoiceInfo
            .fpdm(UPDATED_FPDM)
            .fphm(UPDATED_FPHM)
            .kprq(UPDATED_KPRQ)
            .xfsh(UPDATED_XFSH)
            .xfmc(UPDATED_XFMC)
            .gfsh(UPDATED_GFSH)
            .gfmc(UPDATED_GFMC)
            .jehj(UPDATED_JEHJ)
            .sehj(UPDATED_SEHJ)
            .url(UPDATED_URL)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);
        EInvoiceInfoDTO eInvoiceInfoDTO = eInvoiceInfoMapper.toDto(updatedEInvoiceInfo);

        restEInvoiceInfoMockMvc.perform(put("/api/e-invoice-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eInvoiceInfoDTO)))
            .andExpect(status().isOk());

        // Validate the EInvoiceInfo in the database
        List<EInvoiceInfo> eInvoiceInfoList = eInvoiceInfoRepository.findAll();
        assertThat(eInvoiceInfoList).hasSize(databaseSizeBeforeUpdate);
        EInvoiceInfo testEInvoiceInfo = eInvoiceInfoList.get(eInvoiceInfoList.size() - 1);
        assertThat(testEInvoiceInfo.getFpdm()).isEqualTo(UPDATED_FPDM);
        assertThat(testEInvoiceInfo.getFphm()).isEqualTo(UPDATED_FPHM);
        assertThat(testEInvoiceInfo.getKprq()).isEqualTo(UPDATED_KPRQ);
        assertThat(testEInvoiceInfo.getXfsh()).isEqualTo(UPDATED_XFSH);
        assertThat(testEInvoiceInfo.getXfmc()).isEqualTo(UPDATED_XFMC);
        assertThat(testEInvoiceInfo.getGfsh()).isEqualTo(UPDATED_GFSH);
        assertThat(testEInvoiceInfo.getGfmc()).isEqualTo(UPDATED_GFMC);
        assertThat(testEInvoiceInfo.getJehj()).isEqualTo(UPDATED_JEHJ);
        assertThat(testEInvoiceInfo.getSehj()).isEqualTo(UPDATED_SEHJ);
        assertThat(testEInvoiceInfo.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testEInvoiceInfo.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testEInvoiceInfo.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingEInvoiceInfo() throws Exception {
        int databaseSizeBeforeUpdate = eInvoiceInfoRepository.findAll().size();

        // Create the EInvoiceInfo
        EInvoiceInfoDTO eInvoiceInfoDTO = eInvoiceInfoMapper.toDto(eInvoiceInfo);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEInvoiceInfoMockMvc.perform(put("/api/e-invoice-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eInvoiceInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EInvoiceInfo in the database
        List<EInvoiceInfo> eInvoiceInfoList = eInvoiceInfoRepository.findAll();
        assertThat(eInvoiceInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEInvoiceInfo() throws Exception {
        // Initialize the database
        eInvoiceInfoRepository.saveAndFlush(eInvoiceInfo);

        int databaseSizeBeforeDelete = eInvoiceInfoRepository.findAll().size();

        // Get the eInvoiceInfo
        restEInvoiceInfoMockMvc.perform(delete("/api/e-invoice-infos/{id}", eInvoiceInfo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EInvoiceInfo> eInvoiceInfoList = eInvoiceInfoRepository.findAll();
        assertThat(eInvoiceInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EInvoiceInfo.class);
        EInvoiceInfo eInvoiceInfo1 = new EInvoiceInfo();
        eInvoiceInfo1.setId(1L);
        EInvoiceInfo eInvoiceInfo2 = new EInvoiceInfo();
        eInvoiceInfo2.setId(eInvoiceInfo1.getId());
        assertThat(eInvoiceInfo1).isEqualTo(eInvoiceInfo2);
        eInvoiceInfo2.setId(2L);
        assertThat(eInvoiceInfo1).isNotEqualTo(eInvoiceInfo2);
        eInvoiceInfo1.setId(null);
        assertThat(eInvoiceInfo1).isNotEqualTo(eInvoiceInfo2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EInvoiceInfoDTO.class);
        EInvoiceInfoDTO eInvoiceInfoDTO1 = new EInvoiceInfoDTO();
        eInvoiceInfoDTO1.setId(1L);
        EInvoiceInfoDTO eInvoiceInfoDTO2 = new EInvoiceInfoDTO();
        assertThat(eInvoiceInfoDTO1).isNotEqualTo(eInvoiceInfoDTO2);
        eInvoiceInfoDTO2.setId(eInvoiceInfoDTO1.getId());
        assertThat(eInvoiceInfoDTO1).isEqualTo(eInvoiceInfoDTO2);
        eInvoiceInfoDTO2.setId(2L);
        assertThat(eInvoiceInfoDTO1).isNotEqualTo(eInvoiceInfoDTO2);
        eInvoiceInfoDTO1.setId(null);
        assertThat(eInvoiceInfoDTO1).isNotEqualTo(eInvoiceInfoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(eInvoiceInfoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(eInvoiceInfoMapper.fromId(null)).isNull();
    }
}
