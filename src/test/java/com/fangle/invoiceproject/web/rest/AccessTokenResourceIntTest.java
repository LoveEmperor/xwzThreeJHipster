package com.fangle.invoiceproject.web.rest;

import com.fangle.invoiceproject.InvoiceprojectApp;

import com.fangle.invoiceproject.domain.AccessToken;
import com.fangle.invoiceproject.repository.AccessTokenRepository;
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
 * Test class for the AccessTokenResource REST controller.
 *
 * @see AccessTokenResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvoiceprojectApp.class)
public class AccessTokenResourceIntTest {

    private static final String DEFAULT_NSRMC = "AAAAAAAAAA";
    private static final String UPDATED_NSRMC = "BBBBBBBBBB";

    private static final String DEFAULT_NSRSBH = "AAAAAAAAAA";
    private static final String UPDATED_NSRSBH = "BBBBBBBBBB";

    private static final String DEFAULT_EXPIRES = "AAAAAAAA";
    private static final String UPDATED_EXPIRES = "BBBBBBBB";

    private static final String DEFAULT_PKEY = "AAAAAAAAAA";
    private static final String UPDATED_PKEY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private AccessTokenRepository accessTokenRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAccessTokenMockMvc;

    private AccessToken accessToken;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AccessTokenResource accessTokenResource = new AccessTokenResource(accessTokenRepository);
        this.restAccessTokenMockMvc = MockMvcBuilders.standaloneSetup(accessTokenResource)
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
    public static AccessToken createEntity(EntityManager em) {
        AccessToken accessToken = new AccessToken()
            .nsrmc(DEFAULT_NSRMC)
            .nsrsbh(DEFAULT_NSRSBH)
            .expires(DEFAULT_EXPIRES)
            .pkey(DEFAULT_PKEY)
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME);
        return accessToken;
    }

    @Before
    public void initTest() {
        accessToken = createEntity(em);
    }

    @Test
    @Transactional
    public void createAccessToken() throws Exception {
        int databaseSizeBeforeCreate = accessTokenRepository.findAll().size();

        // Create the AccessToken
        restAccessTokenMockMvc.perform(post("/api/access-tokens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accessToken)))
            .andExpect(status().isCreated());

        // Validate the AccessToken in the database
        List<AccessToken> accessTokenList = accessTokenRepository.findAll();
        assertThat(accessTokenList).hasSize(databaseSizeBeforeCreate + 1);
        AccessToken testAccessToken = accessTokenList.get(accessTokenList.size() - 1);
        assertThat(testAccessToken.getNsrmc()).isEqualTo(DEFAULT_NSRMC);
        assertThat(testAccessToken.getNsrsbh()).isEqualTo(DEFAULT_NSRSBH);
        assertThat(testAccessToken.getExpires()).isEqualTo(DEFAULT_EXPIRES);
        assertThat(testAccessToken.getPkey()).isEqualTo(DEFAULT_PKEY);
        assertThat(testAccessToken.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testAccessToken.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void createAccessTokenWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = accessTokenRepository.findAll().size();

        // Create the AccessToken with an existing ID
        accessToken.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAccessTokenMockMvc.perform(post("/api/access-tokens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accessToken)))
            .andExpect(status().isBadRequest());

        // Validate the AccessToken in the database
        List<AccessToken> accessTokenList = accessTokenRepository.findAll();
        assertThat(accessTokenList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNsrsbhIsRequired() throws Exception {
        int databaseSizeBeforeTest = accessTokenRepository.findAll().size();
        // set the field null
        accessToken.setNsrsbh(null);

        // Create the AccessToken, which fails.

        restAccessTokenMockMvc.perform(post("/api/access-tokens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accessToken)))
            .andExpect(status().isBadRequest());

        List<AccessToken> accessTokenList = accessTokenRepository.findAll();
        assertThat(accessTokenList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPkeyIsRequired() throws Exception {
        int databaseSizeBeforeTest = accessTokenRepository.findAll().size();
        // set the field null
        accessToken.setPkey(null);

        // Create the AccessToken, which fails.

        restAccessTokenMockMvc.perform(post("/api/access-tokens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accessToken)))
            .andExpect(status().isBadRequest());

        List<AccessToken> accessTokenList = accessTokenRepository.findAll();
        assertThat(accessTokenList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAccessTokens() throws Exception {
        // Initialize the database
        accessTokenRepository.saveAndFlush(accessToken);

        // Get all the accessTokenList
        restAccessTokenMockMvc.perform(get("/api/access-tokens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(accessToken.getId().intValue())))
            .andExpect(jsonPath("$.[*].nsrmc").value(hasItem(DEFAULT_NSRMC.toString())))
            .andExpect(jsonPath("$.[*].nsrsbh").value(hasItem(DEFAULT_NSRSBH.toString())))
            .andExpect(jsonPath("$.[*].expires").value(hasItem(DEFAULT_EXPIRES.toString())))
            .andExpect(jsonPath("$.[*].pkey").value(hasItem(DEFAULT_PKEY.toString())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())));
    }
    

    @Test
    @Transactional
    public void getAccessToken() throws Exception {
        // Initialize the database
        accessTokenRepository.saveAndFlush(accessToken);

        // Get the accessToken
        restAccessTokenMockMvc.perform(get("/api/access-tokens/{id}", accessToken.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(accessToken.getId().intValue()))
            .andExpect(jsonPath("$.nsrmc").value(DEFAULT_NSRMC.toString()))
            .andExpect(jsonPath("$.nsrsbh").value(DEFAULT_NSRSBH.toString()))
            .andExpect(jsonPath("$.expires").value(DEFAULT_EXPIRES.toString()))
            .andExpect(jsonPath("$.pkey").value(DEFAULT_PKEY.toString()))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingAccessToken() throws Exception {
        // Get the accessToken
        restAccessTokenMockMvc.perform(get("/api/access-tokens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAccessToken() throws Exception {
        // Initialize the database
        accessTokenRepository.saveAndFlush(accessToken);

        int databaseSizeBeforeUpdate = accessTokenRepository.findAll().size();

        // Update the accessToken
        AccessToken updatedAccessToken = accessTokenRepository.findById(accessToken.getId()).get();
        // Disconnect from session so that the updates on updatedAccessToken are not directly saved in db
        em.detach(updatedAccessToken);
        updatedAccessToken
            .nsrmc(UPDATED_NSRMC)
            .nsrsbh(UPDATED_NSRSBH)
            .expires(UPDATED_EXPIRES)
            .pkey(UPDATED_PKEY)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);

        restAccessTokenMockMvc.perform(put("/api/access-tokens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAccessToken)))
            .andExpect(status().isOk());

        // Validate the AccessToken in the database
        List<AccessToken> accessTokenList = accessTokenRepository.findAll();
        assertThat(accessTokenList).hasSize(databaseSizeBeforeUpdate);
        AccessToken testAccessToken = accessTokenList.get(accessTokenList.size() - 1);
        assertThat(testAccessToken.getNsrmc()).isEqualTo(UPDATED_NSRMC);
        assertThat(testAccessToken.getNsrsbh()).isEqualTo(UPDATED_NSRSBH);
        assertThat(testAccessToken.getExpires()).isEqualTo(UPDATED_EXPIRES);
        assertThat(testAccessToken.getPkey()).isEqualTo(UPDATED_PKEY);
        assertThat(testAccessToken.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testAccessToken.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingAccessToken() throws Exception {
        int databaseSizeBeforeUpdate = accessTokenRepository.findAll().size();

        // Create the AccessToken

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAccessTokenMockMvc.perform(put("/api/access-tokens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accessToken)))
            .andExpect(status().isBadRequest());

        // Validate the AccessToken in the database
        List<AccessToken> accessTokenList = accessTokenRepository.findAll();
        assertThat(accessTokenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAccessToken() throws Exception {
        // Initialize the database
        accessTokenRepository.saveAndFlush(accessToken);

        int databaseSizeBeforeDelete = accessTokenRepository.findAll().size();

        // Get the accessToken
        restAccessTokenMockMvc.perform(delete("/api/access-tokens/{id}", accessToken.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AccessToken> accessTokenList = accessTokenRepository.findAll();
        assertThat(accessTokenList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccessToken.class);
        AccessToken accessToken1 = new AccessToken();
        accessToken1.setId(1L);
        AccessToken accessToken2 = new AccessToken();
        accessToken2.setId(accessToken1.getId());
        assertThat(accessToken1).isEqualTo(accessToken2);
        accessToken2.setId(2L);
        assertThat(accessToken1).isNotEqualTo(accessToken2);
        accessToken1.setId(null);
        assertThat(accessToken1).isNotEqualTo(accessToken2);
    }
}
