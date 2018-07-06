package com.raymond.financialdashboard.web.rest;

import com.raymond.financialdashboard.FinancialdashboardApp;

import com.raymond.financialdashboard.domain.TransactionIng;
import com.raymond.financialdashboard.repository.TransactionIngRepository;
import com.raymond.financialdashboard.service.TransactionIngService;
import com.raymond.financialdashboard.service.dto.TransactionIngDTO;
import com.raymond.financialdashboard.service.mapper.TransactionIngMapper;
import com.raymond.financialdashboard.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


import static com.raymond.financialdashboard.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.raymond.financialdashboard.domain.enumeration.Sign;
/**
 * Test class for the TransactionIngResource REST controller.
 *
 * @see TransactionIngResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FinancialdashboardApp.class)
public class TransactionIngResourceIntTest {

    private static final Integer DEFAULT_DATE = 1;
    private static final Integer UPDATED_DATE = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MY_BANK_ACCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_MY_BANK_ACCOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_CONTRA_BANK_ACCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_CONTRA_BANK_ACCOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final Sign DEFAULT_SIGN = Sign.PLUS;
    private static final Sign UPDATED_SIGN = Sign.MINUS;

    private static final Double DEFAULT_AMOUNT = 1D;
    private static final Double UPDATED_AMOUNT = 2D;

    private static final String DEFAULT_MUTATION = "AAAAAAAAAA";
    private static final String UPDATED_MUTATION = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private TransactionIngRepository transactionIngRepository;
    @Mock
    private TransactionIngRepository transactionIngRepositoryMock;

    @Autowired
    private TransactionIngMapper transactionIngMapper;
    
    @Mock
    private TransactionIngService transactionIngServiceMock;

    @Autowired
    private TransactionIngService transactionIngService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTransactionIngMockMvc;

    private TransactionIng transactionIng;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TransactionIngResource transactionIngResource = new TransactionIngResource(transactionIngService);
        this.restTransactionIngMockMvc = MockMvcBuilders.standaloneSetup(transactionIngResource)
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
    public static TransactionIng createEntity(EntityManager em) {
        TransactionIng transactionIng = new TransactionIng()
            .date(DEFAULT_DATE)
            .name(DEFAULT_NAME)
            .myBankAccount(DEFAULT_MY_BANK_ACCOUNT)
            .contraBankAccount(DEFAULT_CONTRA_BANK_ACCOUNT)
            .code(DEFAULT_CODE)
            .sign(DEFAULT_SIGN)
            .amount(DEFAULT_AMOUNT)
            .mutation(DEFAULT_MUTATION)
            .description(DEFAULT_DESCRIPTION);
        return transactionIng;
    }

    @Before
    public void initTest() {
        transactionIng = createEntity(em);
    }

    @Test
    @Transactional
    public void createTransactionIng() throws Exception {
        int databaseSizeBeforeCreate = transactionIngRepository.findAll().size();

        // Create the TransactionIng
        TransactionIngDTO transactionIngDTO = transactionIngMapper.toDto(transactionIng);
        restTransactionIngMockMvc.perform(post("/api/transaction-ings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionIngDTO)))
            .andExpect(status().isCreated());

        // Validate the TransactionIng in the database
        List<TransactionIng> transactionIngList = transactionIngRepository.findAll();
        assertThat(transactionIngList).hasSize(databaseSizeBeforeCreate + 1);
        TransactionIng testTransactionIng = transactionIngList.get(transactionIngList.size() - 1);
        assertThat(testTransactionIng.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testTransactionIng.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTransactionIng.getMyBankAccount()).isEqualTo(DEFAULT_MY_BANK_ACCOUNT);
        assertThat(testTransactionIng.getContraBankAccount()).isEqualTo(DEFAULT_CONTRA_BANK_ACCOUNT);
        assertThat(testTransactionIng.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testTransactionIng.getSign()).isEqualTo(DEFAULT_SIGN);
        assertThat(testTransactionIng.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testTransactionIng.getMutation()).isEqualTo(DEFAULT_MUTATION);
        assertThat(testTransactionIng.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createTransactionIngWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = transactionIngRepository.findAll().size();

        // Create the TransactionIng with an existing ID
        transactionIng.setId(1L);
        TransactionIngDTO transactionIngDTO = transactionIngMapper.toDto(transactionIng);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransactionIngMockMvc.perform(post("/api/transaction-ings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionIngDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TransactionIng in the database
        List<TransactionIng> transactionIngList = transactionIngRepository.findAll();
        assertThat(transactionIngList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionIngRepository.findAll().size();
        // set the field null
        transactionIng.setDate(null);

        // Create the TransactionIng, which fails.
        TransactionIngDTO transactionIngDTO = transactionIngMapper.toDto(transactionIng);

        restTransactionIngMockMvc.perform(post("/api/transaction-ings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionIngDTO)))
            .andExpect(status().isBadRequest());

        List<TransactionIng> transactionIngList = transactionIngRepository.findAll();
        assertThat(transactionIngList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionIngRepository.findAll().size();
        // set the field null
        transactionIng.setName(null);

        // Create the TransactionIng, which fails.
        TransactionIngDTO transactionIngDTO = transactionIngMapper.toDto(transactionIng);

        restTransactionIngMockMvc.perform(post("/api/transaction-ings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionIngDTO)))
            .andExpect(status().isBadRequest());

        List<TransactionIng> transactionIngList = transactionIngRepository.findAll();
        assertThat(transactionIngList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMyBankAccountIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionIngRepository.findAll().size();
        // set the field null
        transactionIng.setMyBankAccount(null);

        // Create the TransactionIng, which fails.
        TransactionIngDTO transactionIngDTO = transactionIngMapper.toDto(transactionIng);

        restTransactionIngMockMvc.perform(post("/api/transaction-ings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionIngDTO)))
            .andExpect(status().isBadRequest());

        List<TransactionIng> transactionIngList = transactionIngRepository.findAll();
        assertThat(transactionIngList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionIngRepository.findAll().size();
        // set the field null
        transactionIng.setCode(null);

        // Create the TransactionIng, which fails.
        TransactionIngDTO transactionIngDTO = transactionIngMapper.toDto(transactionIng);

        restTransactionIngMockMvc.perform(post("/api/transaction-ings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionIngDTO)))
            .andExpect(status().isBadRequest());

        List<TransactionIng> transactionIngList = transactionIngRepository.findAll();
        assertThat(transactionIngList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSignIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionIngRepository.findAll().size();
        // set the field null
        transactionIng.setSign(null);

        // Create the TransactionIng, which fails.
        TransactionIngDTO transactionIngDTO = transactionIngMapper.toDto(transactionIng);

        restTransactionIngMockMvc.perform(post("/api/transaction-ings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionIngDTO)))
            .andExpect(status().isBadRequest());

        List<TransactionIng> transactionIngList = transactionIngRepository.findAll();
        assertThat(transactionIngList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionIngRepository.findAll().size();
        // set the field null
        transactionIng.setAmount(null);

        // Create the TransactionIng, which fails.
        TransactionIngDTO transactionIngDTO = transactionIngMapper.toDto(transactionIng);

        restTransactionIngMockMvc.perform(post("/api/transaction-ings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionIngDTO)))
            .andExpect(status().isBadRequest());

        List<TransactionIng> transactionIngList = transactionIngRepository.findAll();
        assertThat(transactionIngList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMutationIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionIngRepository.findAll().size();
        // set the field null
        transactionIng.setMutation(null);

        // Create the TransactionIng, which fails.
        TransactionIngDTO transactionIngDTO = transactionIngMapper.toDto(transactionIng);

        restTransactionIngMockMvc.perform(post("/api/transaction-ings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionIngDTO)))
            .andExpect(status().isBadRequest());

        List<TransactionIng> transactionIngList = transactionIngRepository.findAll();
        assertThat(transactionIngList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTransactionIngs() throws Exception {
        // Initialize the database
        transactionIngRepository.saveAndFlush(transactionIng);

        // Get all the transactionIngList
        restTransactionIngMockMvc.perform(get("/api/transaction-ings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transactionIng.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].myBankAccount").value(hasItem(DEFAULT_MY_BANK_ACCOUNT.toString())))
            .andExpect(jsonPath("$.[*].contraBankAccount").value(hasItem(DEFAULT_CONTRA_BANK_ACCOUNT.toString())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].sign").value(hasItem(DEFAULT_SIGN.toString())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].mutation").value(hasItem(DEFAULT_MUTATION.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    public void getAllTransactionIngsWithEagerRelationshipsIsEnabled() throws Exception {
        TransactionIngResource transactionIngResource = new TransactionIngResource(transactionIngServiceMock);
        when(transactionIngServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restTransactionIngMockMvc = MockMvcBuilders.standaloneSetup(transactionIngResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restTransactionIngMockMvc.perform(get("/api/transaction-ings?eagerload=true"))
        .andExpect(status().isOk());

        verify(transactionIngServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    public void getAllTransactionIngsWithEagerRelationshipsIsNotEnabled() throws Exception {
        TransactionIngResource transactionIngResource = new TransactionIngResource(transactionIngServiceMock);
            when(transactionIngServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restTransactionIngMockMvc = MockMvcBuilders.standaloneSetup(transactionIngResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restTransactionIngMockMvc.perform(get("/api/transaction-ings?eagerload=true"))
        .andExpect(status().isOk());

            verify(transactionIngServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getTransactionIng() throws Exception {
        // Initialize the database
        transactionIngRepository.saveAndFlush(transactionIng);

        // Get the transactionIng
        restTransactionIngMockMvc.perform(get("/api/transaction-ings/{id}", transactionIng.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(transactionIng.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.myBankAccount").value(DEFAULT_MY_BANK_ACCOUNT.toString()))
            .andExpect(jsonPath("$.contraBankAccount").value(DEFAULT_CONTRA_BANK_ACCOUNT.toString()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.sign").value(DEFAULT_SIGN.toString()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.mutation").value(DEFAULT_MUTATION.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingTransactionIng() throws Exception {
        // Get the transactionIng
        restTransactionIngMockMvc.perform(get("/api/transaction-ings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTransactionIng() throws Exception {
        // Initialize the database
        transactionIngRepository.saveAndFlush(transactionIng);

        int databaseSizeBeforeUpdate = transactionIngRepository.findAll().size();

        // Update the transactionIng
        TransactionIng updatedTransactionIng = transactionIngRepository.findById(transactionIng.getId()).get();
        // Disconnect from session so that the updates on updatedTransactionIng are not directly saved in db
        em.detach(updatedTransactionIng);
        updatedTransactionIng
            .date(UPDATED_DATE)
            .name(UPDATED_NAME)
            .myBankAccount(UPDATED_MY_BANK_ACCOUNT)
            .contraBankAccount(UPDATED_CONTRA_BANK_ACCOUNT)
            .code(UPDATED_CODE)
            .sign(UPDATED_SIGN)
            .amount(UPDATED_AMOUNT)
            .mutation(UPDATED_MUTATION)
            .description(UPDATED_DESCRIPTION);
        TransactionIngDTO transactionIngDTO = transactionIngMapper.toDto(updatedTransactionIng);

        restTransactionIngMockMvc.perform(put("/api/transaction-ings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionIngDTO)))
            .andExpect(status().isOk());

        // Validate the TransactionIng in the database
        List<TransactionIng> transactionIngList = transactionIngRepository.findAll();
        assertThat(transactionIngList).hasSize(databaseSizeBeforeUpdate);
        TransactionIng testTransactionIng = transactionIngList.get(transactionIngList.size() - 1);
        assertThat(testTransactionIng.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testTransactionIng.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTransactionIng.getMyBankAccount()).isEqualTo(UPDATED_MY_BANK_ACCOUNT);
        assertThat(testTransactionIng.getContraBankAccount()).isEqualTo(UPDATED_CONTRA_BANK_ACCOUNT);
        assertThat(testTransactionIng.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testTransactionIng.getSign()).isEqualTo(UPDATED_SIGN);
        assertThat(testTransactionIng.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testTransactionIng.getMutation()).isEqualTo(UPDATED_MUTATION);
        assertThat(testTransactionIng.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingTransactionIng() throws Exception {
        int databaseSizeBeforeUpdate = transactionIngRepository.findAll().size();

        // Create the TransactionIng
        TransactionIngDTO transactionIngDTO = transactionIngMapper.toDto(transactionIng);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTransactionIngMockMvc.perform(put("/api/transaction-ings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionIngDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TransactionIng in the database
        List<TransactionIng> transactionIngList = transactionIngRepository.findAll();
        assertThat(transactionIngList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTransactionIng() throws Exception {
        // Initialize the database
        transactionIngRepository.saveAndFlush(transactionIng);

        int databaseSizeBeforeDelete = transactionIngRepository.findAll().size();

        // Get the transactionIng
        restTransactionIngMockMvc.perform(delete("/api/transaction-ings/{id}", transactionIng.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TransactionIng> transactionIngList = transactionIngRepository.findAll();
        assertThat(transactionIngList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TransactionIng.class);
        TransactionIng transactionIng1 = new TransactionIng();
        transactionIng1.setId(1L);
        TransactionIng transactionIng2 = new TransactionIng();
        transactionIng2.setId(transactionIng1.getId());
        assertThat(transactionIng1).isEqualTo(transactionIng2);
        transactionIng2.setId(2L);
        assertThat(transactionIng1).isNotEqualTo(transactionIng2);
        transactionIng1.setId(null);
        assertThat(transactionIng1).isNotEqualTo(transactionIng2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TransactionIngDTO.class);
        TransactionIngDTO transactionIngDTO1 = new TransactionIngDTO();
        transactionIngDTO1.setId(1L);
        TransactionIngDTO transactionIngDTO2 = new TransactionIngDTO();
        assertThat(transactionIngDTO1).isNotEqualTo(transactionIngDTO2);
        transactionIngDTO2.setId(transactionIngDTO1.getId());
        assertThat(transactionIngDTO1).isEqualTo(transactionIngDTO2);
        transactionIngDTO2.setId(2L);
        assertThat(transactionIngDTO1).isNotEqualTo(transactionIngDTO2);
        transactionIngDTO1.setId(null);
        assertThat(transactionIngDTO1).isNotEqualTo(transactionIngDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(transactionIngMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(transactionIngMapper.fromId(null)).isNull();
    }
}
