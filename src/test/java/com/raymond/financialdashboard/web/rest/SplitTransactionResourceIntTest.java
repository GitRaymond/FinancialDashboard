package com.raymond.financialdashboard.web.rest;

import com.raymond.financialdashboard.FinancialdashboardApp;

import com.raymond.financialdashboard.domain.SplitTransaction;
import com.raymond.financialdashboard.repository.SplitTransactionRepository;
import com.raymond.financialdashboard.service.SplitTransactionService;
import com.raymond.financialdashboard.service.dto.SplitTransactionDTO;
import com.raymond.financialdashboard.service.mapper.SplitTransactionMapper;
import com.raymond.financialdashboard.web.rest.errors.ExceptionTranslator;

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
import java.util.List;


import static com.raymond.financialdashboard.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.raymond.financialdashboard.domain.enumeration.Sign;
/**
 * Test class for the SplitTransactionResource REST controller.
 *
 * @see SplitTransactionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FinancialdashboardApp.class)
public class SplitTransactionResourceIntTest {

    private static final String DEFAULT_SPLIT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SPLIT_NAME = "BBBBBBBBBB";

    private static final Double DEFAULT_AMOUNT = 1D;
    private static final Double UPDATED_AMOUNT = 2D;

    private static final Sign DEFAULT_SIGN = Sign.PLUS;
    private static final Sign UPDATED_SIGN = Sign.MINUS;

    @Autowired
    private SplitTransactionRepository splitTransactionRepository;


    @Autowired
    private SplitTransactionMapper splitTransactionMapper;
    

    @Autowired
    private SplitTransactionService splitTransactionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSplitTransactionMockMvc;

    private SplitTransaction splitTransaction;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SplitTransactionResource splitTransactionResource = new SplitTransactionResource(splitTransactionService);
        this.restSplitTransactionMockMvc = MockMvcBuilders.standaloneSetup(splitTransactionResource)
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
    public static SplitTransaction createEntity(EntityManager em) {
        SplitTransaction splitTransaction = new SplitTransaction()
            .splitName(DEFAULT_SPLIT_NAME)
            .amount(DEFAULT_AMOUNT)
            .sign(DEFAULT_SIGN);
        return splitTransaction;
    }

    @Before
    public void initTest() {
        splitTransaction = createEntity(em);
    }

    @Test
    @Transactional
    public void createSplitTransaction() throws Exception {
        int databaseSizeBeforeCreate = splitTransactionRepository.findAll().size();

        // Create the SplitTransaction
        SplitTransactionDTO splitTransactionDTO = splitTransactionMapper.toDto(splitTransaction);
        restSplitTransactionMockMvc.perform(post("/api/split-transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(splitTransactionDTO)))
            .andExpect(status().isCreated());

        // Validate the SplitTransaction in the database
        List<SplitTransaction> splitTransactionList = splitTransactionRepository.findAll();
        assertThat(splitTransactionList).hasSize(databaseSizeBeforeCreate + 1);
        SplitTransaction testSplitTransaction = splitTransactionList.get(splitTransactionList.size() - 1);
        assertThat(testSplitTransaction.getSplitName()).isEqualTo(DEFAULT_SPLIT_NAME);
        assertThat(testSplitTransaction.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testSplitTransaction.getSign()).isEqualTo(DEFAULT_SIGN);
    }

    @Test
    @Transactional
    public void createSplitTransactionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = splitTransactionRepository.findAll().size();

        // Create the SplitTransaction with an existing ID
        splitTransaction.setId(1L);
        SplitTransactionDTO splitTransactionDTO = splitTransactionMapper.toDto(splitTransaction);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSplitTransactionMockMvc.perform(post("/api/split-transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(splitTransactionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SplitTransaction in the database
        List<SplitTransaction> splitTransactionList = splitTransactionRepository.findAll();
        assertThat(splitTransactionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSplitTransactions() throws Exception {
        // Initialize the database
        splitTransactionRepository.saveAndFlush(splitTransaction);

        // Get all the splitTransactionList
        restSplitTransactionMockMvc.perform(get("/api/split-transactions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(splitTransaction.getId().intValue())))
            .andExpect(jsonPath("$.[*].splitName").value(hasItem(DEFAULT_SPLIT_NAME.toString())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].sign").value(hasItem(DEFAULT_SIGN.toString())));
    }
    

    @Test
    @Transactional
    public void getSplitTransaction() throws Exception {
        // Initialize the database
        splitTransactionRepository.saveAndFlush(splitTransaction);

        // Get the splitTransaction
        restSplitTransactionMockMvc.perform(get("/api/split-transactions/{id}", splitTransaction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(splitTransaction.getId().intValue()))
            .andExpect(jsonPath("$.splitName").value(DEFAULT_SPLIT_NAME.toString()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.sign").value(DEFAULT_SIGN.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingSplitTransaction() throws Exception {
        // Get the splitTransaction
        restSplitTransactionMockMvc.perform(get("/api/split-transactions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSplitTransaction() throws Exception {
        // Initialize the database
        splitTransactionRepository.saveAndFlush(splitTransaction);

        int databaseSizeBeforeUpdate = splitTransactionRepository.findAll().size();

        // Update the splitTransaction
        SplitTransaction updatedSplitTransaction = splitTransactionRepository.findById(splitTransaction.getId()).get();
        // Disconnect from session so that the updates on updatedSplitTransaction are not directly saved in db
        em.detach(updatedSplitTransaction);
        updatedSplitTransaction
            .splitName(UPDATED_SPLIT_NAME)
            .amount(UPDATED_AMOUNT)
            .sign(UPDATED_SIGN);
        SplitTransactionDTO splitTransactionDTO = splitTransactionMapper.toDto(updatedSplitTransaction);

        restSplitTransactionMockMvc.perform(put("/api/split-transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(splitTransactionDTO)))
            .andExpect(status().isOk());

        // Validate the SplitTransaction in the database
        List<SplitTransaction> splitTransactionList = splitTransactionRepository.findAll();
        assertThat(splitTransactionList).hasSize(databaseSizeBeforeUpdate);
        SplitTransaction testSplitTransaction = splitTransactionList.get(splitTransactionList.size() - 1);
        assertThat(testSplitTransaction.getSplitName()).isEqualTo(UPDATED_SPLIT_NAME);
        assertThat(testSplitTransaction.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testSplitTransaction.getSign()).isEqualTo(UPDATED_SIGN);
    }

    @Test
    @Transactional
    public void updateNonExistingSplitTransaction() throws Exception {
        int databaseSizeBeforeUpdate = splitTransactionRepository.findAll().size();

        // Create the SplitTransaction
        SplitTransactionDTO splitTransactionDTO = splitTransactionMapper.toDto(splitTransaction);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSplitTransactionMockMvc.perform(put("/api/split-transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(splitTransactionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SplitTransaction in the database
        List<SplitTransaction> splitTransactionList = splitTransactionRepository.findAll();
        assertThat(splitTransactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSplitTransaction() throws Exception {
        // Initialize the database
        splitTransactionRepository.saveAndFlush(splitTransaction);

        int databaseSizeBeforeDelete = splitTransactionRepository.findAll().size();

        // Get the splitTransaction
        restSplitTransactionMockMvc.perform(delete("/api/split-transactions/{id}", splitTransaction.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SplitTransaction> splitTransactionList = splitTransactionRepository.findAll();
        assertThat(splitTransactionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SplitTransaction.class);
        SplitTransaction splitTransaction1 = new SplitTransaction();
        splitTransaction1.setId(1L);
        SplitTransaction splitTransaction2 = new SplitTransaction();
        splitTransaction2.setId(splitTransaction1.getId());
        assertThat(splitTransaction1).isEqualTo(splitTransaction2);
        splitTransaction2.setId(2L);
        assertThat(splitTransaction1).isNotEqualTo(splitTransaction2);
        splitTransaction1.setId(null);
        assertThat(splitTransaction1).isNotEqualTo(splitTransaction2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SplitTransactionDTO.class);
        SplitTransactionDTO splitTransactionDTO1 = new SplitTransactionDTO();
        splitTransactionDTO1.setId(1L);
        SplitTransactionDTO splitTransactionDTO2 = new SplitTransactionDTO();
        assertThat(splitTransactionDTO1).isNotEqualTo(splitTransactionDTO2);
        splitTransactionDTO2.setId(splitTransactionDTO1.getId());
        assertThat(splitTransactionDTO1).isEqualTo(splitTransactionDTO2);
        splitTransactionDTO2.setId(2L);
        assertThat(splitTransactionDTO1).isNotEqualTo(splitTransactionDTO2);
        splitTransactionDTO1.setId(null);
        assertThat(splitTransactionDTO1).isNotEqualTo(splitTransactionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(splitTransactionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(splitTransactionMapper.fromId(null)).isNull();
    }
}
