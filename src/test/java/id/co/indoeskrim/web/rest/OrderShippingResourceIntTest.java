package id.co.indoeskrim.web.rest;

import id.co.indoeskrim.InesAPIApp;

import id.co.indoeskrim.domain.OrderShipping;
import id.co.indoeskrim.repository.OrderShippingRepository;
import id.co.indoeskrim.service.OrderShippingService;
import id.co.indoeskrim.service.dto.OrderShippingDTO;
import id.co.indoeskrim.service.mapper.OrderShippingMapper;
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
 * Test class for the OrderShippingResource REST controller.
 *
 * @see OrderShippingResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InesAPIApp.class)
public class OrderShippingResourceIntTest {

    private static final Integer DEFAULT_CUSTOMER_ADDRESS_ID = 1;
    private static final Integer UPDATED_CUSTOMER_ADDRESS_ID = 2;

    private static final String DEFAULT_SHIPPING_METHOD = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_METHOD = "BBBBBBBBBB";

    private static final Long DEFAULT_SHIPPING_PRICE = 1L;
    private static final Long UPDATED_SHIPPING_PRICE = 2L;

    @Autowired
    private OrderShippingRepository orderShippingRepository;

    @Autowired
    private OrderShippingMapper orderShippingMapper;

    @Autowired
    private OrderShippingService orderShippingService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOrderShippingMockMvc;

    private OrderShipping orderShipping;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrderShippingResource orderShippingResource = new OrderShippingResource(orderShippingService);
        this.restOrderShippingMockMvc = MockMvcBuilders.standaloneSetup(orderShippingResource)
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
    public static OrderShipping createEntity(EntityManager em) {
        OrderShipping orderShipping = new OrderShipping()
            .customerAddressId(DEFAULT_CUSTOMER_ADDRESS_ID)
            .shippingMethod(DEFAULT_SHIPPING_METHOD)
            .shippingPrice(DEFAULT_SHIPPING_PRICE);
        return orderShipping;
    }

    @Before
    public void initTest() {
        orderShipping = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrderShipping() throws Exception {
        int databaseSizeBeforeCreate = orderShippingRepository.findAll().size();

        // Create the OrderShipping
        OrderShippingDTO orderShippingDTO = orderShippingMapper.toDto(orderShipping);
        restOrderShippingMockMvc.perform(post("/api/order-shippings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderShippingDTO)))
            .andExpect(status().isCreated());

        // Validate the OrderShipping in the database
        List<OrderShipping> orderShippingList = orderShippingRepository.findAll();
        assertThat(orderShippingList).hasSize(databaseSizeBeforeCreate + 1);
        OrderShipping testOrderShipping = orderShippingList.get(orderShippingList.size() - 1);
        assertThat(testOrderShipping.getCustomerAddressId()).isEqualTo(DEFAULT_CUSTOMER_ADDRESS_ID);
        assertThat(testOrderShipping.getShippingMethod()).isEqualTo(DEFAULT_SHIPPING_METHOD);
        assertThat(testOrderShipping.getShippingPrice()).isEqualTo(DEFAULT_SHIPPING_PRICE);
    }

    @Test
    @Transactional
    public void createOrderShippingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orderShippingRepository.findAll().size();

        // Create the OrderShipping with an existing ID
        orderShipping.setId(1L);
        OrderShippingDTO orderShippingDTO = orderShippingMapper.toDto(orderShipping);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderShippingMockMvc.perform(post("/api/order-shippings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderShippingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderShipping in the database
        List<OrderShipping> orderShippingList = orderShippingRepository.findAll();
        assertThat(orderShippingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOrderShippings() throws Exception {
        // Initialize the database
        orderShippingRepository.saveAndFlush(orderShipping);

        // Get all the orderShippingList
        restOrderShippingMockMvc.perform(get("/api/order-shippings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderShipping.getId().intValue())))
            .andExpect(jsonPath("$.[*].customerAddressId").value(hasItem(DEFAULT_CUSTOMER_ADDRESS_ID)))
            .andExpect(jsonPath("$.[*].shippingMethod").value(hasItem(DEFAULT_SHIPPING_METHOD.toString())))
            .andExpect(jsonPath("$.[*].shippingPrice").value(hasItem(DEFAULT_SHIPPING_PRICE.intValue())));
    }
    
    @Test
    @Transactional
    public void getOrderShipping() throws Exception {
        // Initialize the database
        orderShippingRepository.saveAndFlush(orderShipping);

        // Get the orderShipping
        restOrderShippingMockMvc.perform(get("/api/order-shippings/{id}", orderShipping.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(orderShipping.getId().intValue()))
            .andExpect(jsonPath("$.customerAddressId").value(DEFAULT_CUSTOMER_ADDRESS_ID))
            .andExpect(jsonPath("$.shippingMethod").value(DEFAULT_SHIPPING_METHOD.toString()))
            .andExpect(jsonPath("$.shippingPrice").value(DEFAULT_SHIPPING_PRICE.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingOrderShipping() throws Exception {
        // Get the orderShipping
        restOrderShippingMockMvc.perform(get("/api/order-shippings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrderShipping() throws Exception {
        // Initialize the database
        orderShippingRepository.saveAndFlush(orderShipping);

        int databaseSizeBeforeUpdate = orderShippingRepository.findAll().size();

        // Update the orderShipping
        OrderShipping updatedOrderShipping = orderShippingRepository.findById(orderShipping.getId()).get();
        // Disconnect from session so that the updates on updatedOrderShipping are not directly saved in db
        em.detach(updatedOrderShipping);
        updatedOrderShipping
            .customerAddressId(UPDATED_CUSTOMER_ADDRESS_ID)
            .shippingMethod(UPDATED_SHIPPING_METHOD)
            .shippingPrice(UPDATED_SHIPPING_PRICE);
        OrderShippingDTO orderShippingDTO = orderShippingMapper.toDto(updatedOrderShipping);

        restOrderShippingMockMvc.perform(put("/api/order-shippings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderShippingDTO)))
            .andExpect(status().isOk());

        // Validate the OrderShipping in the database
        List<OrderShipping> orderShippingList = orderShippingRepository.findAll();
        assertThat(orderShippingList).hasSize(databaseSizeBeforeUpdate);
        OrderShipping testOrderShipping = orderShippingList.get(orderShippingList.size() - 1);
        assertThat(testOrderShipping.getCustomerAddressId()).isEqualTo(UPDATED_CUSTOMER_ADDRESS_ID);
        assertThat(testOrderShipping.getShippingMethod()).isEqualTo(UPDATED_SHIPPING_METHOD);
        assertThat(testOrderShipping.getShippingPrice()).isEqualTo(UPDATED_SHIPPING_PRICE);
    }

    @Test
    @Transactional
    public void updateNonExistingOrderShipping() throws Exception {
        int databaseSizeBeforeUpdate = orderShippingRepository.findAll().size();

        // Create the OrderShipping
        OrderShippingDTO orderShippingDTO = orderShippingMapper.toDto(orderShipping);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderShippingMockMvc.perform(put("/api/order-shippings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderShippingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderShipping in the database
        List<OrderShipping> orderShippingList = orderShippingRepository.findAll();
        assertThat(orderShippingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrderShipping() throws Exception {
        // Initialize the database
        orderShippingRepository.saveAndFlush(orderShipping);

        int databaseSizeBeforeDelete = orderShippingRepository.findAll().size();

        // Get the orderShipping
        restOrderShippingMockMvc.perform(delete("/api/order-shippings/{id}", orderShipping.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<OrderShipping> orderShippingList = orderShippingRepository.findAll();
        assertThat(orderShippingList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderShipping.class);
        OrderShipping orderShipping1 = new OrderShipping();
        orderShipping1.setId(1L);
        OrderShipping orderShipping2 = new OrderShipping();
        orderShipping2.setId(orderShipping1.getId());
        assertThat(orderShipping1).isEqualTo(orderShipping2);
        orderShipping2.setId(2L);
        assertThat(orderShipping1).isNotEqualTo(orderShipping2);
        orderShipping1.setId(null);
        assertThat(orderShipping1).isNotEqualTo(orderShipping2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderShippingDTO.class);
        OrderShippingDTO orderShippingDTO1 = new OrderShippingDTO();
        orderShippingDTO1.setId(1L);
        OrderShippingDTO orderShippingDTO2 = new OrderShippingDTO();
        assertThat(orderShippingDTO1).isNotEqualTo(orderShippingDTO2);
        orderShippingDTO2.setId(orderShippingDTO1.getId());
        assertThat(orderShippingDTO1).isEqualTo(orderShippingDTO2);
        orderShippingDTO2.setId(2L);
        assertThat(orderShippingDTO1).isNotEqualTo(orderShippingDTO2);
        orderShippingDTO1.setId(null);
        assertThat(orderShippingDTO1).isNotEqualTo(orderShippingDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(orderShippingMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(orderShippingMapper.fromId(null)).isNull();
    }
}
