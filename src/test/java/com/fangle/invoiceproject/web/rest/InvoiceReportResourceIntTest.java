package com.fangle.invoiceproject.web.rest;

import com.fangle.invoiceproject.InvoiceprojectApp;

import com.fangle.invoiceproject.domain.InvoiceReport;
import com.fangle.invoiceproject.repository.InvoiceReportRepository;
import com.fangle.invoiceproject.service.dto.InvoiceReportDTO;
import com.fangle.invoiceproject.service.mapper.InvoiceReportMapper;
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
 * Test class for the InvoiceReportResource REST controller.
 *
 * @see InvoiceReportResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvoiceprojectApp.class)
public class InvoiceReportResourceIntTest {

    private static final String DEFAULT_FS = "AAAAAAAAAA";
    private static final String UPDATED_FS = "BBBBBBBBBB";

    private static final String DEFAULT_JEHJ = "AAAAAAAAAA";
    private static final String UPDATED_JEHJ = "BBBBBBBBBB";

    private static final String DEFAULT_SEHJ = "AAAAAAAA";
    private static final String UPDATED_SEHJ = "BBBBBBBB";

    private static final String DEFAULT_JSHJ = "AAAAAAAAAA";
    private static final String UPDATED_JSHJ = "BBBBBBBBBB";

    private static final String DEFAULT_NSRSUM = "AAAAAAAAAA";
    private static final String UPDATED_NSRSUM = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private InvoiceReportRepository invoiceReportRepository;


    @Autowired
    private InvoiceReportMapper invoiceReportMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restInvoiceReportMockMvc;

    private InvoiceReport invoiceReport;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final InvoiceReportResource invoiceReportResource = new InvoiceReportResource(invoiceReportRepository, invoiceReportMapper);
        this.restInvoiceReportMockMvc = MockMvcBuilders.standaloneSetup(invoiceReportResource)
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
    public static InvoiceReport createEntity(EntityManager em) {
        InvoiceReport invoiceReport = new InvoiceReport()
            .fs(DEFAULT_FS)
            .jehj(DEFAULT_JEHJ)
            .sehj(DEFAULT_SEHJ)
            .jshj(DEFAULT_JSHJ)
            .nsrsum(DEFAULT_NSRSUM)
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME);
        return invoiceReport;
    }

    @Before
    public void initTest() {
        invoiceReport = createEntity(em);
    }

    @Test
    @Transactional
    public void createInvoiceReport() throws Exception {
        int databaseSizeBeforeCreate = invoiceReportRepository.findAll().size();

        // Create the InvoiceReport
        InvoiceReportDTO invoiceReportDTO = invoiceReportMapper.toDto(invoiceReport);
        restInvoiceReportMockMvc.perform(post("/api/invoice-reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(invoiceReportDTO)))
            .andExpect(status().isCreated());

        // Validate the InvoiceReport in the database
        List<InvoiceReport> invoiceReportList = invoiceReportRepository.findAll();
        assertThat(invoiceReportList).hasSize(databaseSizeBeforeCreate + 1);
        InvoiceReport testInvoiceReport = invoiceReportList.get(invoiceReportList.size() - 1);
        assertThat(testInvoiceReport.getFs()).isEqualTo(DEFAULT_FS);
        assertThat(testInvoiceReport.getJehj()).isEqualTo(DEFAULT_JEHJ);
        assertThat(testInvoiceReport.getSehj()).isEqualTo(DEFAULT_SEHJ);
        assertThat(testInvoiceReport.getJshj()).isEqualTo(DEFAULT_JSHJ);
        assertThat(testInvoiceReport.getNsrsum()).isEqualTo(DEFAULT_NSRSUM);
        assertThat(testInvoiceReport.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testInvoiceReport.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void createInvoiceReportWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = invoiceReportRepository.findAll().size();

        // Create the InvoiceReport with an existing ID
        invoiceReport.setId(1L);
        InvoiceReportDTO invoiceReportDTO = invoiceReportMapper.toDto(invoiceReport);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInvoiceReportMockMvc.perform(post("/api/invoice-reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(invoiceReportDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InvoiceReport in the database
        List<InvoiceReport> invoiceReportList = invoiceReportRepository.findAll();
        assertThat(invoiceReportList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkFsIsRequired() throws Exception {
        int databaseSizeBeforeTest = invoiceReportRepository.findAll().size();
        // set the field null
        invoiceReport.setFs(null);

        // Create the InvoiceReport, which fails.
        InvoiceReportDTO invoiceReportDTO = invoiceReportMapper.toDto(invoiceReport);

        restInvoiceReportMockMvc.perform(post("/api/invoice-reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(invoiceReportDTO)))
            .andExpect(status().isBadRequest());

        List<InvoiceReport> invoiceReportList = invoiceReportRepository.findAll();
        assertThat(invoiceReportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkJehjIsRequired() throws Exception {
        int databaseSizeBeforeTest = invoiceReportRepository.findAll().size();
        // set the field null
        invoiceReport.setJehj(null);

        // Create the InvoiceReport, which fails.
        InvoiceReportDTO invoiceReportDTO = invoiceReportMapper.toDto(invoiceReport);

        restInvoiceReportMockMvc.perform(post("/api/invoice-reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(invoiceReportDTO)))
            .andExpect(status().isBadRequest());

        List<InvoiceReport> invoiceReportList = invoiceReportRepository.findAll();
        assertThat(invoiceReportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInvoiceReports() throws Exception {
        // Initialize the database
        invoiceReportRepository.saveAndFlush(invoiceReport);

        // Get all the invoiceReportList
        restInvoiceReportMockMvc.perform(get("/api/invoice-reports?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(invoiceReport.getId().intValue())))
            .andExpect(jsonPath("$.[*].fs").value(hasItem(DEFAULT_FS.toString())))
            .andExpect(jsonPath("$.[*].jehj").value(hasItem(DEFAULT_JEHJ.toString())))
            .andExpect(jsonPath("$.[*].sehj").value(hasItem(DEFAULT_SEHJ.toString())))
            .andExpect(jsonPath("$.[*].jshj").value(hasItem(DEFAULT_JSHJ.toString())))
            .andExpect(jsonPath("$.[*].nsrsum").value(hasItem(DEFAULT_NSRSUM.toString())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())));
    }


    @Test
    @Transactional
    public void getInvoiceReport() throws Exception {
        // Initialize the database
        invoiceReportRepository.saveAndFlush(invoiceReport);

        // Get the invoiceReport
        restInvoiceReportMockMvc.perform(get("/api/invoice-reports/{id}", invoiceReport.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(invoiceReport.getId().intValue()))
            .andExpect(jsonPath("$.fs").value(DEFAULT_FS.toString()))
            .andExpect(jsonPath("$.jehj").value(DEFAULT_JEHJ.toString()))
            .andExpect(jsonPath("$.sehj").value(DEFAULT_SEHJ.toString()))
            .andExpect(jsonPath("$.jshj").value(DEFAULT_JSHJ.toString()))
            .andExpect(jsonPath("$.nsrsum").value(DEFAULT_NSRSUM.toString()))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingInvoiceReport() throws Exception {
        // Get the invoiceReport
        restInvoiceReportMockMvc.perform(get("/api/invoice-reports/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInvoiceReport() throws Exception {
        // Initialize the database
        invoiceReportRepository.saveAndFlush(invoiceReport);

        int databaseSizeBeforeUpdate = invoiceReportRepository.findAll().size();

        // Update the invoiceReport
        InvoiceReport updatedInvoiceReport = invoiceReportRepository.findById(invoiceReport.getId()).get();
        // Disconnect from session so that the updates on updatedInvoiceReport are not directly saved in db
        em.detach(updatedInvoiceReport);
        updatedInvoiceReport
            .fs(UPDATED_FS)
            .jehj(UPDATED_JEHJ)
            .sehj(UPDATED_SEHJ)
            .jshj(UPDATED_JSHJ)
            .nsrsum(UPDATED_NSRSUM)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);
        InvoiceReportDTO invoiceReportDTO = invoiceReportMapper.toDto(updatedInvoiceReport);

        restInvoiceReportMockMvc.perform(put("/api/invoice-reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(invoiceReportDTO)))
            .andExpect(status().isOk());

        // Validate the InvoiceReport in the database
        List<InvoiceReport> invoiceReportList = invoiceReportRepository.findAll();
        assertThat(invoiceReportList).hasSize(databaseSizeBeforeUpdate);
        InvoiceReport testInvoiceReport = invoiceReportList.get(invoiceReportList.size() - 1);
        assertThat(testInvoiceReport.getFs()).isEqualTo(UPDATED_FS);
        assertThat(testInvoiceReport.getJehj()).isEqualTo(UPDATED_JEHJ);
        assertThat(testInvoiceReport.getSehj()).isEqualTo(UPDATED_SEHJ);
        assertThat(testInvoiceReport.getJshj()).isEqualTo(UPDATED_JSHJ);
        assertThat(testInvoiceReport.getNsrsum()).isEqualTo(UPDATED_NSRSUM);
        assertThat(testInvoiceReport.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testInvoiceReport.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingInvoiceReport() throws Exception {
        int databaseSizeBeforeUpdate = invoiceReportRepository.findAll().size();

        // Create the InvoiceReport
        InvoiceReportDTO invoiceReportDTO = invoiceReportMapper.toDto(invoiceReport);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restInvoiceReportMockMvc.perform(put("/api/invoice-reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(invoiceReportDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InvoiceReport in the database
        List<InvoiceReport> invoiceReportList = invoiceReportRepository.findAll();
        assertThat(invoiceReportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInvoiceReport() throws Exception {
        // Initialize the database
        invoiceReportRepository.saveAndFlush(invoiceReport);

        int databaseSizeBeforeDelete = invoiceReportRepository.findAll().size();

        // Get the invoiceReport
        restInvoiceReportMockMvc.perform(delete("/api/invoice-reports/{id}", invoiceReport.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<InvoiceReport> invoiceReportList = invoiceReportRepository.findAll();
        assertThat(invoiceReportList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvoiceReport.class);
        InvoiceReport invoiceReport1 = new InvoiceReport();
        invoiceReport1.setId(1L);
        InvoiceReport invoiceReport2 = new InvoiceReport();
        invoiceReport2.setId(invoiceReport1.getId());
        assertThat(invoiceReport1).isEqualTo(invoiceReport2);
        invoiceReport2.setId(2L);
        assertThat(invoiceReport1).isNotEqualTo(invoiceReport2);
        invoiceReport1.setId(null);
        assertThat(invoiceReport1).isNotEqualTo(invoiceReport2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvoiceReportDTO.class);
        InvoiceReportDTO invoiceReportDTO1 = new InvoiceReportDTO();
        invoiceReportDTO1.setId(1L);
        InvoiceReportDTO invoiceReportDTO2 = new InvoiceReportDTO();
        assertThat(invoiceReportDTO1).isNotEqualTo(invoiceReportDTO2);
        invoiceReportDTO2.setId(invoiceReportDTO1.getId());
        assertThat(invoiceReportDTO1).isEqualTo(invoiceReportDTO2);
        invoiceReportDTO2.setId(2L);
        assertThat(invoiceReportDTO1).isNotEqualTo(invoiceReportDTO2);
        invoiceReportDTO1.setId(null);
        assertThat(invoiceReportDTO1).isNotEqualTo(invoiceReportDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(invoiceReportMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(invoiceReportMapper.fromId(null)).isNull();
    }
}
