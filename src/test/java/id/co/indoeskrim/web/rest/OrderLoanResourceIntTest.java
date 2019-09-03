package id.co.indoeskrim.web.rest;

import id.co.indoeskrim.InesAPIApp;

import id.co.indoeskrim.domain.OrderLoan;
import id.co.indoeskrim.repository.OrderLoanRepository;
import id.co.indoeskrim.service.OrderLoanService;
import id.co.indoeskrim.service.dto.OrderLoanDTO;
import id.co.indoeskrim.service.mapper.OrderLoanMapper;
import id.co.indoeskrim.web.rest.errors.ExceptionTranslator;

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


import static id.co.indoeskrim.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the OrderLoanResource REST controller.
 *
 * @see OrderLoanResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InesAPIApp.class)
public class OrderLoanResourceIntTest {

    private static final String DEFAULT_PRODUCT_NO = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_ORDER_LOAN_STATUS = "AAA";
    private static final String UPDATED_ORDER_LOAN_STATUS = "BBB";

    private static final Instant DEFAULT_RETURN_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_RETURN_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private OrderLoanRepository orderLoanRepository;

    @Autowired
    private OrderLoanMapper orderLoanMapper;

    @Autowired
    private OrderLoanService orderLoanService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOrderLoanMockMvc;

    private OrderLoan orderLoan;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrderLoanResource orderLoanResource = new OrderLoanResource(orderLoanService);
        this.restOrderLoanMockMvc = MockMvcBuilders.standaloneSetup(orderLoanResource)
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
    public static OrderLoan createEntity(EntityManager em) {
        OrderLoan orderLoan = new OrderLoan()
            .productNo(DEFAULT_PRODUCT_NO)
            .orderLoanStatus(DEFAULT_ORDER_LOAN_STATUS)
            .returnDate(DEFAULT_RETURN_DATE);
        return orderLoan;
    }

    @Before
    public void initTest() {
        orderLoan = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrderLoan() throws Exception {
        int databaseSizeBeforeCreate = orderLoanRepository.findAll().size();

        // Create the OrderLoan
        OrderLoanDTO orderLoanDTO = orderLoanMapper.toDto(orderLoan);
        restOrderLoanMockMvc.perform(post("/api/order-loans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderLoanDTO)))
            .andExpect(status().isCreated());

        // Validate the OrderLoan in the database
        List<OrderLoan> orderLoanList = orderLoanRepository.findAll();
        assertThat(orderLoanList).hasSize(databaseSizeBeforeCreate + 1);
        OrderLoan testOrderLoan = orderLoanList.get(orderLoanList.size() - 1);
        assertThat(testOrderLoan.getProductNo()).isEqualTo(DEFAULT_PRODUCT_NO);
        assertThat(testOrderLoan.getOrderLoanStatus()).isEqualTo(DEFAULT_ORDER_LOAN_STATUS);
        assertThat(testOrderLoan.getReturnDate()).isEqualTo(DEFAULT_RETURN_DATE);
    }

    @Test
    @Transactional
    public void createOrderLoanWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orderLoanRepository.findAll().size();

        // Create the OrderLoan with an existing ID
        orderLoan.setId(1L);
        OrderLoanDTO orderLoanDTO = orderLoanMapper.toDto(orderLoan);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderLoanMockMvc.perform(post("/api/order-loans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderLoanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderLoan in the database
        List<OrderLoan> orderLoanList = orderLoanRepository.findAll();
        assertThat(orderLoanList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOrderLoans() throws Exception {
        // Initialize the database
        orderLoanRepository.saveAndFlush(orderLoan);

        // Get all the orderLoanList
        restOrderLoanMockMvc.perform(get("/api/order-loans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderLoan.getId().intValue())))
            .andExpect(jsonPath("$.[*].productNo").value(hasItem(DEFAULT_PRODUCT_NO.toString())))
            .andExpect(jsonPath("$.[*].orderLoanStatus").value(hasItem(DEFAULT_ORDER_LOAN_STATUS.toString())))
            .andExpect(jsonPath("$.[*].returnDate").value(hasItem(DEFAULT_RETURN_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getOrderLoan() throws Exception {
        // Initialize the database
        orderLoanRepository.saveAndFlush(orderLoan);

        // Get the orderLoan
        restOrderLoanMockMvc.perform(get("/api/order-loans/{id}", orderLoan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(orderLoan.getId().intValue()))
            .andExpect(jsonPath("$.productNo").value(DEFAULT_PRODUCT_NO.toString()))
            .andExpect(jsonPath("$.orderLoanStatus").value(DEFAULT_ORDER_LOAN_STATUS.toString()))
            .andExpect(jsonPath("$.returnDate").value(DEFAULT_RETURN_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOrderLoan() throws Exception {
        // Get the orderLoan
        restOrderLoanMockMvc.perform(get("/api/order-loans/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrderLoan() throws Exception {
        // Initialize the database
        orderLoanRepository.saveAndFlush(orderLoan);

        int databaseSizeBeforeUpdate = orderLoanRepository.findAll().size();

        // Update the orderLoan
        OrderLoan updatedOrderLoan = orderLoanRepository.findById(orderLoan.getId()).get();
        // Disconnect from session so that the updates on updatedOrderLoan are not directly saved in db
        em.detach(updatedOrderLoan);
        updatedOrderLoan
            .productNo(UPDATED_PRODUCT_NO)
            .orderLoanStatus(UPDATED_ORDER_LOAN_STATUS)
            .returnDate(UPDATED_RETURN_DATE);
        OrderLoanDTO orderLoanDTO = orderLoanMapper.toDto(updatedOrderLoan);

        restOrderLoanMockMvc.perform(put("/api/order-loans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderLoanDTO)))
            .andExpect(status().isOk());

        // Validate the OrderLoan in the database
        List<OrderLoan> orderLoanList = orderLoanRepository.findAll();
        assertThat(orderLoanList).hasSize(databaseSizeBeforeUpdate);
        OrderLoan testOrderLoan = orderLoanList.get(orderLoanList.size() - 1);
        assertThat(testOrderLoan.getProductNo()).isEqualTo(UPDATED_PRODUCT_NO);
        assertThat(testOrderLoan.getOrderLoanStatus()).isEqualTo(UPDATED_ORDER_LOAN_STATUS);
        assertThat(testOrderLoan.getReturnDate()).isEqualTo(UPDATED_RETURN_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingOrderLoan() throws Exception {
        int databaseSizeBeforeUpdate = orderLoanRepository.findAll().size();

        // Create the OrderLoan
        OrderLoanDTO orderLoanDTO = orderLoanMapper.toDto(orderLoan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderLoanMockMvc.perform(put("/api/order-loans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderLoanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderLoan in the database
        List<OrderLoan> orderLoanList = orderLoanRepository.findAll();
        assertThat(orderLoanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrderLoan() throws Exception {
        // Initialize the database
        orderLoanRepository.saveAndFlush(orderLoan);

        int databaseSizeBeforeDelete = orderLoanRepository.findAll().size();

        // Get the orderLoan
        restOrderLoanMockMvc.perform(delete("/api/order-loans/{id}", orderLoan.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<OrderLoan> orderLoanList = orderLoanRepository.findAll();
        assertThat(orderLoanList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderLoan.class);
        OrderLoan orderLoan1 = new OrderLoan();
        orderLoan1.setId(1L);
        OrderLoan orderLoan2 = new OrderLoan();
        orderLoan2.setId(orderLoan1.getId());
        assertThat(orderLoan1).isEqualTo(orderLoan2);
        orderLoan2.setId(2L);
        assertThat(orderLoan1).isNotEqualTo(orderLoan2);
        orderLoan1.setId(null);
        assertThat(orderLoan1).isNotEqualTo(orderLoan2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderLoanDTO.class);
        OrderLoanDTO orderLoanDTO1 = new OrderLoanDTO();
        orderLoanDTO1.setId(1L);
        OrderLoanDTO orderLoanDTO2 = new OrderLoanDTO();
        assertThat(orderLoanDTO1).isNotEqualTo(orderLoanDTO2);
        orderLoanDTO2.setId(orderLoanDTO1.getId());
        assertThat(orderLoanDTO1).isEqualTo(orderLoanDTO2);
        orderLoanDTO2.setId(2L);
        assertThat(orderLoanDTO1).isNotEqualTo(orderLoanDTO2);
        orderLoanDTO1.setId(null);
        assertThat(orderLoanDTO1).isNotEqualTo(orderLoanDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(orderLoanMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(orderLoanMapper.fromId(null)).isNull();
    }
}
