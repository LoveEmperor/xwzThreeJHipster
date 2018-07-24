package com.fangle.invoiceproject.web.rest;

import com.fangle.invoiceproject.InvoiceprojectApp;

import com.fangle.invoiceproject.domain.InvoiceInfo;
import com.fangle.invoiceproject.repository.InvoiceInfoRepository;
import com.fangle.invoiceproject.service.InvoiceInfoService;
import com.fangle.invoiceproject.service.dto.InvoiceInfoDTO;
import com.fangle.invoiceproject.service.mapper.InvoiceInfoMapper;
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
 * Test class for the InvoiceInfoResource REST controller.
 *
 * @see InvoiceInfoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvoiceprojectApp.class)
public class InvoiceInfoResourceIntTest {

    private static final String DEFAULT_KP_TIME = "AAAAAAAAAA";
    private static final String UPDATED_KP_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_KP_STATUS = "AAAA";
    private static final String UPDATED_KP_STATUS = "BBBB";

    private static final Integer DEFAULT_KP_MONEY = 1;
    private static final Integer UPDATED_KP_MONEY = 2;

    private static final String DEFAULT_TITLE_TYPE = "AAAA";
    private static final String UPDATED_TITLE_TYPE = "BBBB";

    private static final String DEFAULT_INVOICE_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_INVOICE_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_QYDUTY_NUM = "AAAAAAAAAA";
    private static final String UPDATED_QYDUTY_NUM = "BBBBBBBBBB";

    private static final String DEFAULT_FP_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_FP_CONTENT = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_PAY_RECORDID = "AAAAAAAAAA";
    private static final String UPDATED_PAY_RECORDID = "BBBBBBBBBB";

    private static final String DEFAULT_INVGU_ID = "AAAAAAAAAA";
    private static final String UPDATED_INVGU_ID = "BBBBBBBBBB";

    private static final String DEFAULT_FPDM = "AAAAAAAAAA";
    private static final String UPDATED_FPDM = "BBBBBBBBBB";

    private static final String DEFAULT_FPHM = "AAAAAAAAAA";
    private static final String UPDATED_FPHM = "BBBBBBBBBB";

    private static final String DEFAULT_KPR = "AAAAAAAAAA";
    private static final String UPDATED_KPR = "BBBBBBBBBB";

    private static final String DEFAULT_KPR_ID = "AAAAAAAAAA";
    private static final String UPDATED_KPR_ID = "BBBBBBBBBB";

    private static final String DEFAULT_RESON = "AAAAAAAAAA";
    private static final String UPDATED_RESON = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private InvoiceInfoRepository invoiceInfoRepository;


    @Autowired
    private InvoiceInfoMapper invoiceInfoMapper;


    @Autowired
    private InvoiceInfoService invoiceInfoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restInvoiceInfoMockMvc;

    private InvoiceInfo invoiceInfo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final InvoiceInfoResource invoiceInfoResource = new InvoiceInfoResource(invoiceInfoService);
        this.restInvoiceInfoMockMvc = MockMvcBuilders.standaloneSetup(invoiceInfoResource)
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
    public static InvoiceInfo createEntity(EntityManager em) {
        InvoiceInfo invoiceInfo = new InvoiceInfo()
            .kpTime(DEFAULT_KP_TIME)
            .kpStatus(DEFAULT_KP_STATUS)
            .kpMoney(DEFAULT_KP_MONEY)
            .titleType(DEFAULT_TITLE_TYPE)
            .invoiceTitle(DEFAULT_INVOICE_TITLE)
            .qydutyNum(DEFAULT_QYDUTY_NUM)
            .fpContent(DEFAULT_FP_CONTENT)
            .email(DEFAULT_EMAIL)
            .phone(DEFAULT_PHONE)
            .payRecordid(DEFAULT_PAY_RECORDID)
            .invguId(DEFAULT_INVGU_ID)
            .fpdm(DEFAULT_FPDM)
            .fphm(DEFAULT_FPHM)
            .kpr(DEFAULT_KPR)
            .kprId(DEFAULT_KPR_ID)
            .reson(DEFAULT_RESON)
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME);
        return invoiceInfo;
    }

    @Before
    public void initTest() {
        invoiceInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createInvoiceInfo() throws Exception {
        int databaseSizeBeforeCreate = invoiceInfoRepository.findAll().size();

        // Create the InvoiceInfo
        InvoiceInfoDTO invoiceInfoDTO = invoiceInfoMapper.toDto(invoiceInfo);
        restInvoiceInfoMockMvc.perform(post("/api/invoice-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(invoiceInfoDTO)))
            .andExpect(status().isCreated());

        // Validate the InvoiceInfo in the database
        List<InvoiceInfo> invoiceInfoList = invoiceInfoRepository.findAll();
        assertThat(invoiceInfoList).hasSize(databaseSizeBeforeCreate + 1);
        InvoiceInfo testInvoiceInfo = invoiceInfoList.get(invoiceInfoList.size() - 1);
        assertThat(testInvoiceInfo.getKpTime()).isEqualTo(DEFAULT_KP_TIME);
        assertThat(testInvoiceInfo.getKpStatus()).isEqualTo(DEFAULT_KP_STATUS);
        assertThat(testInvoiceInfo.getKpMoney()).isEqualTo(DEFAULT_KP_MONEY);
        assertThat(testInvoiceInfo.getTitleType()).isEqualTo(DEFAULT_TITLE_TYPE);
        assertThat(testInvoiceInfo.getInvoiceTitle()).isEqualTo(DEFAULT_INVOICE_TITLE);
        assertThat(testInvoiceInfo.getQydutyNum()).isEqualTo(DEFAULT_QYDUTY_NUM);
        assertThat(testInvoiceInfo.getFpContent()).isEqualTo(DEFAULT_FP_CONTENT);
        assertThat(testInvoiceInfo.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testInvoiceInfo.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testInvoiceInfo.getPayRecordid()).isEqualTo(DEFAULT_PAY_RECORDID);
        assertThat(testInvoiceInfo.getInvguId()).isEqualTo(DEFAULT_INVGU_ID);
        assertThat(testInvoiceInfo.getFpdm()).isEqualTo(DEFAULT_FPDM);
        assertThat(testInvoiceInfo.getFphm()).isEqualTo(DEFAULT_FPHM);
        assertThat(testInvoiceInfo.getKpr()).isEqualTo(DEFAULT_KPR);
        assertThat(testInvoiceInfo.getKprId()).isEqualTo(DEFAULT_KPR_ID);
        assertThat(testInvoiceInfo.getReson()).isEqualTo(DEFAULT_RESON);
        assertThat(testInvoiceInfo.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testInvoiceInfo.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void createInvoiceInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = invoiceInfoRepository.findAll().size();

        // Create the InvoiceInfo with an existing ID
        invoiceInfo.setId(1L);
        InvoiceInfoDTO invoiceInfoDTO = invoiceInfoMapper.toDto(invoiceInfo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInvoiceInfoMockMvc.perform(post("/api/invoice-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(invoiceInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InvoiceInfo in the database
        List<InvoiceInfo> invoiceInfoList = invoiceInfoRepository.findAll();
        assertThat(invoiceInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkKpTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = invoiceInfoRepository.findAll().size();
        // set the field null
        invoiceInfo.setKpTime(null);

        // Create the InvoiceInfo, which fails.
        InvoiceInfoDTO invoiceInfoDTO = invoiceInfoMapper.toDto(invoiceInfo);

        restInvoiceInfoMockMvc.perform(post("/api/invoice-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(invoiceInfoDTO)))
            .andExpect(status().isBadRequest());

        List<InvoiceInfo> invoiceInfoList = invoiceInfoRepository.findAll();
        assertThat(invoiceInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKpStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = invoiceInfoRepository.findAll().size();
        // set the field null
        invoiceInfo.setKpStatus(null);

        // Create the InvoiceInfo, which fails.
        InvoiceInfoDTO invoiceInfoDTO = invoiceInfoMapper.toDto(invoiceInfo);

        restInvoiceInfoMockMvc.perform(post("/api/invoice-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(invoiceInfoDTO)))
            .andExpect(status().isBadRequest());

        List<InvoiceInfo> invoiceInfoList = invoiceInfoRepository.findAll();
        assertThat(invoiceInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKpMoneyIsRequired() throws Exception {
        int databaseSizeBeforeTest = invoiceInfoRepository.findAll().size();
        // set the field null
        invoiceInfo.setKpMoney(null);

        // Create the InvoiceInfo, which fails.
        InvoiceInfoDTO invoiceInfoDTO = invoiceInfoMapper.toDto(invoiceInfo);

        restInvoiceInfoMockMvc.perform(post("/api/invoice-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(invoiceInfoDTO)))
            .andExpect(status().isBadRequest());

        List<InvoiceInfo> invoiceInfoList = invoiceInfoRepository.findAll();
        assertThat(invoiceInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTitleTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = invoiceInfoRepository.findAll().size();
        // set the field null
        invoiceInfo.setTitleType(null);

        // Create the InvoiceInfo, which fails.
        InvoiceInfoDTO invoiceInfoDTO = invoiceInfoMapper.toDto(invoiceInfo);

        restInvoiceInfoMockMvc.perform(post("/api/invoice-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(invoiceInfoDTO)))
            .andExpect(status().isBadRequest());

        List<InvoiceInfo> invoiceInfoList = invoiceInfoRepository.findAll();
        assertThat(invoiceInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkInvoiceTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = invoiceInfoRepository.findAll().size();
        // set the field null
        invoiceInfo.setInvoiceTitle(null);

        // Create the InvoiceInfo, which fails.
        InvoiceInfoDTO invoiceInfoDTO = invoiceInfoMapper.toDto(invoiceInfo);

        restInvoiceInfoMockMvc.perform(post("/api/invoice-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(invoiceInfoDTO)))
            .andExpect(status().isBadRequest());

        List<InvoiceInfo> invoiceInfoList = invoiceInfoRepository.findAll();
        assertThat(invoiceInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPayRecordidIsRequired() throws Exception {
        int databaseSizeBeforeTest = invoiceInfoRepository.findAll().size();
        // set the field null
        invoiceInfo.setPayRecordid(null);

        // Create the InvoiceInfo, which fails.
        InvoiceInfoDTO invoiceInfoDTO = invoiceInfoMapper.toDto(invoiceInfo);

        restInvoiceInfoMockMvc.perform(post("/api/invoice-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(invoiceInfoDTO)))
            .andExpect(status().isBadRequest());

        List<InvoiceInfo> invoiceInfoList = invoiceInfoRepository.findAll();
        assertThat(invoiceInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInvoiceInfos() throws Exception {
        // Initialize the database
        invoiceInfoRepository.saveAndFlush(invoiceInfo);

        // Get all the invoiceInfoList
        restInvoiceInfoMockMvc.perform(get("/api/invoice-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(invoiceInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].kpTime").value(hasItem(DEFAULT_KP_TIME.toString())))
            .andExpect(jsonPath("$.[*].kpStatus").value(hasItem(DEFAULT_KP_STATUS.toString())))
            .andExpect(jsonPath("$.[*].kpMoney").value(hasItem(DEFAULT_KP_MONEY)))
            .andExpect(jsonPath("$.[*].titleType").value(hasItem(DEFAULT_TITLE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].invoiceTitle").value(hasItem(DEFAULT_INVOICE_TITLE.toString())))
            .andExpect(jsonPath("$.[*].qydutyNum").value(hasItem(DEFAULT_QYDUTY_NUM.toString())))
            .andExpect(jsonPath("$.[*].fpContent").value(hasItem(DEFAULT_FP_CONTENT.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].payRecordid").value(hasItem(DEFAULT_PAY_RECORDID.toString())))
            .andExpect(jsonPath("$.[*].invguId").value(hasItem(DEFAULT_INVGU_ID.toString())))
            .andExpect(jsonPath("$.[*].fpdm").value(hasItem(DEFAULT_FPDM.toString())))
            .andExpect(jsonPath("$.[*].fphm").value(hasItem(DEFAULT_FPHM.toString())))
            .andExpect(jsonPath("$.[*].kpr").value(hasItem(DEFAULT_KPR.toString())))
            .andExpect(jsonPath("$.[*].kprId").value(hasItem(DEFAULT_KPR_ID.toString())))
            .andExpect(jsonPath("$.[*].reson").value(hasItem(DEFAULT_RESON.toString())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())));
    }


    @Test
    @Transactional
    public void getInvoiceInfo() throws Exception {
        // Initialize the database
        invoiceInfoRepository.saveAndFlush(invoiceInfo);

        // Get the invoiceInfo
        restInvoiceInfoMockMvc.perform(get("/api/invoice-infos/{id}", invoiceInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(invoiceInfo.getId().intValue()))
            .andExpect(jsonPath("$.kpTime").value(DEFAULT_KP_TIME.toString()))
            .andExpect(jsonPath("$.kpStatus").value(DEFAULT_KP_STATUS.toString()))
            .andExpect(jsonPath("$.kpMoney").value(DEFAULT_KP_MONEY))
            .andExpect(jsonPath("$.titleType").value(DEFAULT_TITLE_TYPE.toString()))
            .andExpect(jsonPath("$.invoiceTitle").value(DEFAULT_INVOICE_TITLE.toString()))
            .andExpect(jsonPath("$.qydutyNum").value(DEFAULT_QYDUTY_NUM.toString()))
            .andExpect(jsonPath("$.fpContent").value(DEFAULT_FP_CONTENT.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.payRecordid").value(DEFAULT_PAY_RECORDID.toString()))
            .andExpect(jsonPath("$.invguId").value(DEFAULT_INVGU_ID.toString()))
            .andExpect(jsonPath("$.fpdm").value(DEFAULT_FPDM.toString()))
            .andExpect(jsonPath("$.fphm").value(DEFAULT_FPHM.toString()))
            .andExpect(jsonPath("$.kpr").value(DEFAULT_KPR.toString()))
            .andExpect(jsonPath("$.kprId").value(DEFAULT_KPR_ID.toString()))
            .andExpect(jsonPath("$.reson").value(DEFAULT_RESON.toString()))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingInvoiceInfo() throws Exception {
        // Get the invoiceInfo
        restInvoiceInfoMockMvc.perform(get("/api/invoice-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInvoiceInfo() throws Exception {
        // Initialize the database
        invoiceInfoRepository.saveAndFlush(invoiceInfo);

        int databaseSizeBeforeUpdate = invoiceInfoRepository.findAll().size();

        // Update the invoiceInfo
        InvoiceInfo updatedInvoiceInfo = invoiceInfoRepository.findById(invoiceInfo.getId()).get();
        // Disconnect from session so that the updates on updatedInvoiceInfo are not directly saved in db
        em.detach(updatedInvoiceInfo);
        updatedInvoiceInfo
            .kpTime(UPDATED_KP_TIME)
            .kpStatus(UPDATED_KP_STATUS)
            .kpMoney(UPDATED_KP_MONEY)
            .titleType(UPDATED_TITLE_TYPE)
            .invoiceTitle(UPDATED_INVOICE_TITLE)
            .qydutyNum(UPDATED_QYDUTY_NUM)
            .fpContent(UPDATED_FP_CONTENT)
            .email(UPDATED_EMAIL)
            .phone(UPDATED_PHONE)
            .payRecordid(UPDATED_PAY_RECORDID)
            .invguId(UPDATED_INVGU_ID)
            .fpdm(UPDATED_FPDM)
            .fphm(UPDATED_FPHM)
            .kpr(UPDATED_KPR)
            .kprId(UPDATED_KPR_ID)
            .reson(UPDATED_RESON)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);
        InvoiceInfoDTO invoiceInfoDTO = invoiceInfoMapper.toDto(updatedInvoiceInfo);

        restInvoiceInfoMockMvc.perform(put("/api/invoice-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(invoiceInfoDTO)))
            .andExpect(status().isOk());

        // Validate the InvoiceInfo in the database
        List<InvoiceInfo> invoiceInfoList = invoiceInfoRepository.findAll();
        assertThat(invoiceInfoList).hasSize(databaseSizeBeforeUpdate);
        InvoiceInfo testInvoiceInfo = invoiceInfoList.get(invoiceInfoList.size() - 1);
        assertThat(testInvoiceInfo.getKpTime()).isEqualTo(UPDATED_KP_TIME);
        assertThat(testInvoiceInfo.getKpStatus()).isEqualTo(UPDATED_KP_STATUS);
        assertThat(testInvoiceInfo.getKpMoney()).isEqualTo(UPDATED_KP_MONEY);
        assertThat(testInvoiceInfo.getTitleType()).isEqualTo(UPDATED_TITLE_TYPE);
        assertThat(testInvoiceInfo.getInvoiceTitle()).isEqualTo(UPDATED_INVOICE_TITLE);
        assertThat(testInvoiceInfo.getQydutyNum()).isEqualTo(UPDATED_QYDUTY_NUM);
        assertThat(testInvoiceInfo.getFpContent()).isEqualTo(UPDATED_FP_CONTENT);
        assertThat(testInvoiceInfo.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testInvoiceInfo.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testInvoiceInfo.getPayRecordid()).isEqualTo(UPDATED_PAY_RECORDID);
        assertThat(testInvoiceInfo.getInvguId()).isEqualTo(UPDATED_INVGU_ID);
        assertThat(testInvoiceInfo.getFpdm()).isEqualTo(UPDATED_FPDM);
        assertThat(testInvoiceInfo.getFphm()).isEqualTo(UPDATED_FPHM);
        assertThat(testInvoiceInfo.getKpr()).isEqualTo(UPDATED_KPR);
        assertThat(testInvoiceInfo.getKprId()).isEqualTo(UPDATED_KPR_ID);
        assertThat(testInvoiceInfo.getReson()).isEqualTo(UPDATED_RESON);
        assertThat(testInvoiceInfo.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testInvoiceInfo.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingInvoiceInfo() throws Exception {
        int databaseSizeBeforeUpdate = invoiceInfoRepository.findAll().size();

        // Create the InvoiceInfo
        InvoiceInfoDTO invoiceInfoDTO = invoiceInfoMapper.toDto(invoiceInfo);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restInvoiceInfoMockMvc.perform(put("/api/invoice-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(invoiceInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InvoiceInfo in the database
        List<InvoiceInfo> invoiceInfoList = invoiceInfoRepository.findAll();
        assertThat(invoiceInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInvoiceInfo() throws Exception {
        // Initialize the database
        invoiceInfoRepository.saveAndFlush(invoiceInfo);

        int databaseSizeBeforeDelete = invoiceInfoRepository.findAll().size();

        // Get the invoiceInfo
        restInvoiceInfoMockMvc.perform(delete("/api/invoice-infos/{id}", invoiceInfo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<InvoiceInfo> invoiceInfoList = invoiceInfoRepository.findAll();
        assertThat(invoiceInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvoiceInfo.class);
        InvoiceInfo invoiceInfo1 = new InvoiceInfo();
        invoiceInfo1.setId(1L);
        InvoiceInfo invoiceInfo2 = new InvoiceInfo();
        invoiceInfo2.setId(invoiceInfo1.getId());
        assertThat(invoiceInfo1).isEqualTo(invoiceInfo2);
        invoiceInfo2.setId(2L);
        assertThat(invoiceInfo1).isNotEqualTo(invoiceInfo2);
        invoiceInfo1.setId(null);
        assertThat(invoiceInfo1).isNotEqualTo(invoiceInfo2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvoiceInfoDTO.class);
        InvoiceInfoDTO invoiceInfoDTO1 = new InvoiceInfoDTO();
        invoiceInfoDTO1.setId(1L);
        InvoiceInfoDTO invoiceInfoDTO2 = new InvoiceInfoDTO();
        assertThat(invoiceInfoDTO1).isNotEqualTo(invoiceInfoDTO2);
        invoiceInfoDTO2.setId(invoiceInfoDTO1.getId());
        assertThat(invoiceInfoDTO1).isEqualTo(invoiceInfoDTO2);
        invoiceInfoDTO2.setId(2L);
        assertThat(invoiceInfoDTO1).isNotEqualTo(invoiceInfoDTO2);
        invoiceInfoDTO1.setId(null);
        assertThat(invoiceInfoDTO1).isNotEqualTo(invoiceInfoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(invoiceInfoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(invoiceInfoMapper.fromId(null)).isNull();
    }
}
