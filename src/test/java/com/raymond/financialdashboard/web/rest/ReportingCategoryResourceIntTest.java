package com.raymond.financialdashboard.web.rest;

import com.raymond.financialdashboard.FinancialdashboardApp;

import com.raymond.financialdashboard.domain.ReportingCategory;
import com.raymond.financialdashboard.repository.ReportingCategoryRepository;
import com.raymond.financialdashboard.service.ReportingCategoryService;
import com.raymond.financialdashboard.service.dto.ReportingCategoryDTO;
import com.raymond.financialdashboard.service.mapper.ReportingCategoryMapper;
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

/**
 * Test class for the ReportingCategoryResource REST controller.
 *
 * @see ReportingCategoryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FinancialdashboardApp.class)
public class ReportingCategoryResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private ReportingCategoryRepository reportingCategoryRepository;


    @Autowired
    private ReportingCategoryMapper reportingCategoryMapper;
    

    @Autowired
    private ReportingCategoryService reportingCategoryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restReportingCategoryMockMvc;

    private ReportingCategory reportingCategory;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ReportingCategoryResource reportingCategoryResource = new ReportingCategoryResource(reportingCategoryService);
        this.restReportingCategoryMockMvc = MockMvcBuilders.standaloneSetup(reportingCategoryResource)
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
    public static ReportingCategory createEntity(EntityManager em) {
        ReportingCategory reportingCategory = new ReportingCategory()
            .name(DEFAULT_NAME);
        return reportingCategory;
    }

    @Before
    public void initTest() {
        reportingCategory = createEntity(em);
    }

    @Test
    @Transactional
    public void createReportingCategory() throws Exception {
        int databaseSizeBeforeCreate = reportingCategoryRepository.findAll().size();

        // Create the ReportingCategory
        ReportingCategoryDTO reportingCategoryDTO = reportingCategoryMapper.toDto(reportingCategory);
        restReportingCategoryMockMvc.perform(post("/api/reporting-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reportingCategoryDTO)))
            .andExpect(status().isCreated());

        // Validate the ReportingCategory in the database
        List<ReportingCategory> reportingCategoryList = reportingCategoryRepository.findAll();
        assertThat(reportingCategoryList).hasSize(databaseSizeBeforeCreate + 1);
        ReportingCategory testReportingCategory = reportingCategoryList.get(reportingCategoryList.size() - 1);
        assertThat(testReportingCategory.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createReportingCategoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = reportingCategoryRepository.findAll().size();

        // Create the ReportingCategory with an existing ID
        reportingCategory.setId(1L);
        ReportingCategoryDTO reportingCategoryDTO = reportingCategoryMapper.toDto(reportingCategory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReportingCategoryMockMvc.perform(post("/api/reporting-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reportingCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ReportingCategory in the database
        List<ReportingCategory> reportingCategoryList = reportingCategoryRepository.findAll();
        assertThat(reportingCategoryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllReportingCategories() throws Exception {
        // Initialize the database
        reportingCategoryRepository.saveAndFlush(reportingCategory);

        // Get all the reportingCategoryList
        restReportingCategoryMockMvc.perform(get("/api/reporting-categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reportingCategory.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    

    @Test
    @Transactional
    public void getReportingCategory() throws Exception {
        // Initialize the database
        reportingCategoryRepository.saveAndFlush(reportingCategory);

        // Get the reportingCategory
        restReportingCategoryMockMvc.perform(get("/api/reporting-categories/{id}", reportingCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(reportingCategory.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingReportingCategory() throws Exception {
        // Get the reportingCategory
        restReportingCategoryMockMvc.perform(get("/api/reporting-categories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReportingCategory() throws Exception {
        // Initialize the database
        reportingCategoryRepository.saveAndFlush(reportingCategory);

        int databaseSizeBeforeUpdate = reportingCategoryRepository.findAll().size();

        // Update the reportingCategory
        ReportingCategory updatedReportingCategory = reportingCategoryRepository.findById(reportingCategory.getId()).get();
        // Disconnect from session so that the updates on updatedReportingCategory are not directly saved in db
        em.detach(updatedReportingCategory);
        updatedReportingCategory
            .name(UPDATED_NAME);
        ReportingCategoryDTO reportingCategoryDTO = reportingCategoryMapper.toDto(updatedReportingCategory);

        restReportingCategoryMockMvc.perform(put("/api/reporting-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reportingCategoryDTO)))
            .andExpect(status().isOk());

        // Validate the ReportingCategory in the database
        List<ReportingCategory> reportingCategoryList = reportingCategoryRepository.findAll();
        assertThat(reportingCategoryList).hasSize(databaseSizeBeforeUpdate);
        ReportingCategory testReportingCategory = reportingCategoryList.get(reportingCategoryList.size() - 1);
        assertThat(testReportingCategory.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingReportingCategory() throws Exception {
        int databaseSizeBeforeUpdate = reportingCategoryRepository.findAll().size();

        // Create the ReportingCategory
        ReportingCategoryDTO reportingCategoryDTO = reportingCategoryMapper.toDto(reportingCategory);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restReportingCategoryMockMvc.perform(put("/api/reporting-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reportingCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ReportingCategory in the database
        List<ReportingCategory> reportingCategoryList = reportingCategoryRepository.findAll();
        assertThat(reportingCategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteReportingCategory() throws Exception {
        // Initialize the database
        reportingCategoryRepository.saveAndFlush(reportingCategory);

        int databaseSizeBeforeDelete = reportingCategoryRepository.findAll().size();

        // Get the reportingCategory
        restReportingCategoryMockMvc.perform(delete("/api/reporting-categories/{id}", reportingCategory.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ReportingCategory> reportingCategoryList = reportingCategoryRepository.findAll();
        assertThat(reportingCategoryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReportingCategory.class);
        ReportingCategory reportingCategory1 = new ReportingCategory();
        reportingCategory1.setId(1L);
        ReportingCategory reportingCategory2 = new ReportingCategory();
        reportingCategory2.setId(reportingCategory1.getId());
        assertThat(reportingCategory1).isEqualTo(reportingCategory2);
        reportingCategory2.setId(2L);
        assertThat(reportingCategory1).isNotEqualTo(reportingCategory2);
        reportingCategory1.setId(null);
        assertThat(reportingCategory1).isNotEqualTo(reportingCategory2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReportingCategoryDTO.class);
        ReportingCategoryDTO reportingCategoryDTO1 = new ReportingCategoryDTO();
        reportingCategoryDTO1.setId(1L);
        ReportingCategoryDTO reportingCategoryDTO2 = new ReportingCategoryDTO();
        assertThat(reportingCategoryDTO1).isNotEqualTo(reportingCategoryDTO2);
        reportingCategoryDTO2.setId(reportingCategoryDTO1.getId());
        assertThat(reportingCategoryDTO1).isEqualTo(reportingCategoryDTO2);
        reportingCategoryDTO2.setId(2L);
        assertThat(reportingCategoryDTO1).isNotEqualTo(reportingCategoryDTO2);
        reportingCategoryDTO1.setId(null);
        assertThat(reportingCategoryDTO1).isNotEqualTo(reportingCategoryDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(reportingCategoryMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(reportingCategoryMapper.fromId(null)).isNull();
    }
}
