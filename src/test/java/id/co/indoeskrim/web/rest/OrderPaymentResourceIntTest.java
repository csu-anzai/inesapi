package id.co.indoeskrim.web.rest;

import id.co.indoeskrim.InesAPIApp;

import id.co.indoeskrim.domain.OrderPayment;
import id.co.indoeskrim.repository.OrderPaymentRepository;
import id.co.indoeskrim.service.OrderPaymentService;
import id.co.indoeskrim.service.dto.OrderPaymentDTO;
import id.co.indoeskrim.service.mapper.OrderPaymentMapper;
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
import java.util.List;


import static id.co.indoeskrim.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the OrderPaymentResource REST controller.
 *
 * @see OrderPaymentResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InesAPIApp.class)
public class OrderPaymentResourceIntTest {

    private static final String DEFAULT_PAYMENT_METHOD = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_METHOD = "BBBBBBBBBB";

    private static final Long DEFAULT_AMOUNT = 1L;
    private static final Long UPDATED_AMOUNT = 2L;

    @Autowired
    private OrderPaymentRepository orderPaymentRepository;

    @Autowired
    private OrderPaymentMapper orderPaymentMapper;

    @Autowired
    private OrderPaymentService orderPaymentService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOrderPaymentMockMvc;

    private OrderPayment orderPayment;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrderPaymentResource orderPaymentResource = new OrderPaymentResource(orderPaymentService);
        this.restOrderPaymentMockMvc = MockMvcBuilders.standaloneSetup(orderPaymentResource)
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
    public static OrderPayment createEntity(EntityManager em) {
        OrderPayment orderPayment = new OrderPayment()
            .paymentMethod(DEFAULT_PAYMENT_METHOD)
            .amount(DEFAULT_AMOUNT);
        return orderPayment;
    }

    @Before
    public void initTest() {
        orderPayment = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrderPayment() throws Exception {
        int databaseSizeBeforeCreate = orderPaymentRepository.findAll().size();

        // Create the OrderPayment
        OrderPaymentDTO orderPaymentDTO = orderPaymentMapper.toDto(orderPayment);
        restOrderPaymentMockMvc.perform(post("/api/order-payments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderPaymentDTO)))
            .andExpect(status().isCreated());

        // Validate the OrderPayment in the database
        List<OrderPayment> orderPaymentList = orderPaymentRepository.findAll();
        assertThat(orderPaymentList).hasSize(databaseSizeBeforeCreate + 1);
        OrderPayment testOrderPayment = orderPaymentList.get(orderPaymentList.size() - 1);
        assertThat(testOrderPayment.getPaymentMethod()).isEqualTo(DEFAULT_PAYMENT_METHOD);
        assertThat(testOrderPayment.getAmount()).isEqualTo(DEFAULT_AMOUNT);
    }

    @Test
    @Transactional
    public void createOrderPaymentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orderPaymentRepository.findAll().size();

        // Create the OrderPayment with an existing ID
        orderPayment.setId(1L);
        OrderPaymentDTO orderPaymentDTO = orderPaymentMapper.toDto(orderPayment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderPaymentMockMvc.perform(post("/api/order-payments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderPaymentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderPayment in the database
        List<OrderPayment> orderPaymentList = orderPaymentRepository.findAll();
        assertThat(orderPaymentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOrderPayments() throws Exception {
        // Initialize the database
        orderPaymentRepository.saveAndFlush(orderPayment);

        // Get all the orderPaymentList
        restOrderPaymentMockMvc.perform(get("/api/order-payments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderPayment.getId().intValue())))
            .andExpect(jsonPath("$.[*].paymentMethod").value(hasItem(DEFAULT_PAYMENT_METHOD.toString())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())));
    }
    
    @Test
    @Transactional
    public void getOrderPayment() throws Exception {
        // Initialize the database
        orderPaymentRepository.saveAndFlush(orderPayment);

        // Get the orderPayment
        restOrderPaymentMockMvc.perform(get("/api/order-payments/{id}", orderPayment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(orderPayment.getId().intValue()))
            .andExpect(jsonPath("$.paymentMethod").value(DEFAULT_PAYMENT_METHOD.toString()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingOrderPayment() throws Exception {
        // Get the orderPayment
        restOrderPaymentMockMvc.perform(get("/api/order-payments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrderPayment() throws Exception {
        // Initialize the database
        orderPaymentRepository.saveAndFlush(orderPayment);

        int databaseSizeBeforeUpdate = orderPaymentRepository.findAll().size();

        // Update the orderPayment
        OrderPayment updatedOrderPayment = orderPaymentRepository.findById(orderPayment.getId()).get();
        // Disconnect from session so that the updates on updatedOrderPayment are not directly saved in db
        em.detach(updatedOrderPayment);
        updatedOrderPayment
            .paymentMethod(UPDATED_PAYMENT_METHOD)
            .amount(UPDATED_AMOUNT);
        OrderPaymentDTO orderPaymentDTO = orderPaymentMapper.toDto(updatedOrderPayment);

        restOrderPaymentMockMvc.perform(put("/api/order-payments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderPaymentDTO)))
            .andExpect(status().isOk());

        // Validate the OrderPayment in the database
        List<OrderPayment> orderPaymentList = orderPaymentRepository.findAll();
        assertThat(orderPaymentList).hasSize(databaseSizeBeforeUpdate);
        OrderPayment testOrderPayment = orderPaymentList.get(orderPaymentList.size() - 1);
        assertThat(testOrderPayment.getPaymentMethod()).isEqualTo(UPDATED_PAYMENT_METHOD);
        assertThat(testOrderPayment.getAmount()).isEqualTo(UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingOrderPayment() throws Exception {
        int databaseSizeBeforeUpdate = orderPaymentRepository.findAll().size();

        // Create the OrderPayment
        OrderPaymentDTO orderPaymentDTO = orderPaymentMapper.toDto(orderPayment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderPaymentMockMvc.perform(put("/api/order-payments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderPaymentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderPayment in the database
        List<OrderPayment> orderPaymentList = orderPaymentRepository.findAll();
        assertThat(orderPaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrderPayment() throws Exception {
        // Initialize the database
        orderPaymentRepository.saveAndFlush(orderPayment);

        int databaseSizeBeforeDelete = orderPaymentRepository.findAll().size();

        // Get the orderPayment
        restOrderPaymentMockMvc.perform(delete("/api/order-payments/{id}", orderPayment.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<OrderPayment> orderPaymentList = orderPaymentRepository.findAll();
        assertThat(orderPaymentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderPayment.class);
        OrderPayment orderPayment1 = new OrderPayment();
        orderPayment1.setId(1L);
        OrderPayment orderPayment2 = new OrderPayment();
        orderPayment2.setId(orderPayment1.getId());
        assertThat(orderPayment1).isEqualTo(orderPayment2);
        orderPayment2.setId(2L);
        assertThat(orderPayment1).isNotEqualTo(orderPayment2);
        orderPayment1.setId(null);
        assertThat(orderPayment1).isNotEqualTo(orderPayment2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderPaymentDTO.class);
        OrderPaymentDTO orderPaymentDTO1 = new OrderPaymentDTO();
        orderPaymentDTO1.setId(1L);
        OrderPaymentDTO orderPaymentDTO2 = new OrderPaymentDTO();
        assertThat(orderPaymentDTO1).isNotEqualTo(orderPaymentDTO2);
        orderPaymentDTO2.setId(orderPaymentDTO1.getId());
        assertThat(orderPaymentDTO1).isEqualTo(orderPaymentDTO2);
        orderPaymentDTO2.setId(2L);
        assertThat(orderPaymentDTO1).isNotEqualTo(orderPaymentDTO2);
        orderPaymentDTO1.setId(null);
        assertThat(orderPaymentDTO1).isNotEqualTo(orderPaymentDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(orderPaymentMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(orderPaymentMapper.fromId(null)).isNull();
    }
}
