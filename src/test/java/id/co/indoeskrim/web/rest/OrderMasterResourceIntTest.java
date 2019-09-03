package id.co.indoeskrim.web.rest;

import id.co.indoeskrim.InesAPIApp;

import id.co.indoeskrim.domain.OrderMaster;
import id.co.indoeskrim.repository.OrderMasterRepository;
import id.co.indoeskrim.service.OrderMasterService;
import id.co.indoeskrim.service.dto.OrderMasterDTO;
import id.co.indoeskrim.service.mapper.OrderMasterMapper;
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
 * Test class for the OrderMasterResource REST controller.
 *
 * @see OrderMasterResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InesAPIApp.class)
public class OrderMasterResourceIntTest {

    private static final String DEFAULT_CUSTOMER_ID = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_TOTAL_PRICE = 1L;
    private static final Long UPDATED_TOTAL_PRICE = 2L;

    private static final String DEFAULT_ORDER_STATUS = "AAA";
    private static final String UPDATED_ORDER_STATUS = "BBB";

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderMasterMapper orderMasterMapper;

    @Autowired
    private OrderMasterService orderMasterService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOrderMasterMockMvc;

    private OrderMaster orderMaster;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrderMasterResource orderMasterResource = new OrderMasterResource(orderMasterService);
        this.restOrderMasterMockMvc = MockMvcBuilders.standaloneSetup(orderMasterResource)
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
    public static OrderMaster createEntity(EntityManager em) {
        OrderMaster orderMaster = new OrderMaster()
            .customerId(DEFAULT_CUSTOMER_ID)
            .totalPrice(DEFAULT_TOTAL_PRICE)
            .orderStatus(DEFAULT_ORDER_STATUS);
        return orderMaster;
    }

    @Before
    public void initTest() {
        orderMaster = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrderMaster() throws Exception {
        int databaseSizeBeforeCreate = orderMasterRepository.findAll().size();

        // Create the OrderMaster
        OrderMasterDTO orderMasterDTO = orderMasterMapper.toDto(orderMaster);
        restOrderMasterMockMvc.perform(post("/api/order-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderMasterDTO)))
            .andExpect(status().isCreated());

        // Validate the OrderMaster in the database
        List<OrderMaster> orderMasterList = orderMasterRepository.findAll();
        assertThat(orderMasterList).hasSize(databaseSizeBeforeCreate + 1);
        OrderMaster testOrderMaster = orderMasterList.get(orderMasterList.size() - 1);
        assertThat(testOrderMaster.getCustomerId()).isEqualTo(DEFAULT_CUSTOMER_ID);
        assertThat(testOrderMaster.getTotalPrice()).isEqualTo(DEFAULT_TOTAL_PRICE);
        assertThat(testOrderMaster.getOrderStatus()).isEqualTo(DEFAULT_ORDER_STATUS);
    }

    @Test
    @Transactional
    public void createOrderMasterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orderMasterRepository.findAll().size();

        // Create the OrderMaster with an existing ID
        orderMaster.setOrderId("1L");
        OrderMasterDTO orderMasterDTO = orderMasterMapper.toDto(orderMaster);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderMasterMockMvc.perform(post("/api/order-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderMasterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderMaster in the database
        List<OrderMaster> orderMasterList = orderMasterRepository.findAll();
        assertThat(orderMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOrderMasters() throws Exception {
        // Initialize the database
        orderMasterRepository.saveAndFlush(orderMaster);

        // Get all the orderMasterList
        restOrderMasterMockMvc.perform(get("/api/order-masters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].orderId").value(hasItem(orderMaster.getOrderId())))
            .andExpect(jsonPath("$.[*].customerId").value(hasItem(DEFAULT_CUSTOMER_ID.toString())))
            .andExpect(jsonPath("$.[*].totalPrice").value(hasItem(DEFAULT_TOTAL_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].orderStatus").value(hasItem(DEFAULT_ORDER_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getOrderMaster() throws Exception {
        // Initialize the database
        orderMasterRepository.saveAndFlush(orderMaster);

        // Get the orderMaster
        restOrderMasterMockMvc.perform(get("/api/order-masters/{orderId}", orderMaster.getOrderId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.orderId").value(orderMaster.getOrderId()))
            .andExpect(jsonPath("$.customerId").value(DEFAULT_CUSTOMER_ID.toString()))
            .andExpect(jsonPath("$.totalPrice").value(DEFAULT_TOTAL_PRICE.intValue()))
            .andExpect(jsonPath("$.orderStatus").value(DEFAULT_ORDER_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOrderMaster() throws Exception {
        // Get the orderMaster
        restOrderMasterMockMvc.perform(get("/api/order-masters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrderMaster() throws Exception {
        // Initialize the database
        orderMasterRepository.saveAndFlush(orderMaster);

        int databaseSizeBeforeUpdate = orderMasterRepository.findAll().size();

        // Update the orderMaster
        OrderMaster updatedOrderMaster = orderMasterRepository.findById(orderMaster.getOrderId()).get();
        // Disconnect from session so that the updates on updatedOrderMaster are not directly saved in db
        em.detach(updatedOrderMaster);
        updatedOrderMaster
            .customerId(UPDATED_CUSTOMER_ID)
            .totalPrice(UPDATED_TOTAL_PRICE)
            .orderStatus(UPDATED_ORDER_STATUS);
        OrderMasterDTO orderMasterDTO = orderMasterMapper.toDto(updatedOrderMaster);

        restOrderMasterMockMvc.perform(put("/api/order-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderMasterDTO)))
            .andExpect(status().isOk());

        // Validate the OrderMaster in the database
        List<OrderMaster> orderMasterList = orderMasterRepository.findAll();
        assertThat(orderMasterList).hasSize(databaseSizeBeforeUpdate);
        OrderMaster testOrderMaster = orderMasterList.get(orderMasterList.size() - 1);
        assertThat(testOrderMaster.getCustomerId()).isEqualTo(UPDATED_CUSTOMER_ID);
        assertThat(testOrderMaster.getTotalPrice()).isEqualTo(UPDATED_TOTAL_PRICE);
        assertThat(testOrderMaster.getOrderStatus()).isEqualTo(UPDATED_ORDER_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingOrderMaster() throws Exception {
        int databaseSizeBeforeUpdate = orderMasterRepository.findAll().size();

        // Create the OrderMaster
        OrderMasterDTO orderMasterDTO = orderMasterMapper.toDto(orderMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderMasterMockMvc.perform(put("/api/order-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderMasterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderMaster in the database
        List<OrderMaster> orderMasterList = orderMasterRepository.findAll();
        assertThat(orderMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrderMaster() throws Exception {
        // Initialize the database
        orderMasterRepository.saveAndFlush(orderMaster);

        int databaseSizeBeforeDelete = orderMasterRepository.findAll().size();

        // Get the orderMaster
        restOrderMasterMockMvc.perform(delete("/api/order-masters/{orderId}", orderMaster.getOrderId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<OrderMaster> orderMasterList = orderMasterRepository.findAll();
        assertThat(orderMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderMaster.class);
        OrderMaster orderMaster1 = new OrderMaster();
        orderMaster1.setOrderId("1L");
        OrderMaster orderMaster2 = new OrderMaster();
        orderMaster2.setOrderId(orderMaster1.getOrderId());
        assertThat(orderMaster1).isEqualTo(orderMaster2);
        orderMaster2.setOrderId("2L");
        assertThat(orderMaster1).isNotEqualTo(orderMaster2);
        orderMaster1.setOrderId(null);
        assertThat(orderMaster1).isNotEqualTo(orderMaster2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderMasterDTO.class);
        OrderMasterDTO orderMasterDTO1 = new OrderMasterDTO();
        orderMasterDTO1.setOrderId("1L");
        OrderMasterDTO orderMasterDTO2 = new OrderMasterDTO();
        assertThat(orderMasterDTO1).isNotEqualTo(orderMasterDTO2);
        orderMasterDTO2.setOrderId(orderMasterDTO1.getOrderId());
        assertThat(orderMasterDTO1).isEqualTo(orderMasterDTO2);
        orderMasterDTO2.setOrderId("2L");
        assertThat(orderMasterDTO1).isNotEqualTo(orderMasterDTO2);
        orderMasterDTO1.setOrderId(null);
        assertThat(orderMasterDTO1).isNotEqualTo(orderMasterDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
    	assertThat(orderMasterMapper.fromId("42L").getOrderId()).isEqualTo(42);
        assertThat(orderMasterMapper.fromId(null)).isNull();
    }
}
