package id.co.indoeskrim.web.rest;

import id.co.indoeskrim.InesAPIApp;

import id.co.indoeskrim.domain.CustomerAddress;
import id.co.indoeskrim.repository.CustomerAddressRepository;
import id.co.indoeskrim.service.CustomerAddressService;
import id.co.indoeskrim.service.dto.CustomerAddressDTO;
import id.co.indoeskrim.service.mapper.CustomerAddressMapper;
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
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static id.co.indoeskrim.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CustomerAddressResource REST controller.
 *
 * @see CustomerAddressResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InesAPIApp.class)
public class CustomerAddressResourceIntTest {

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_RECEIVER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_RECEIVER_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVER_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_POSTAL_CODE = "AAAAAAAAA";
    private static final String UPDATED_POSTAL_CODE = "BBBBBBBBB";

    private static final String DEFAULT_LAT = "AAAAAAAAAA";
    private static final String UPDATED_LAT = "BBBBBBBBBB";

    private static final String DEFAULT_LNG = "AAAAAAAAAA";
    private static final String UPDATED_LNG = "BBBBBBBBBB";

    @Autowired
    private CustomerAddressRepository customerAddressRepository;

    @Autowired
    private CustomerAddressMapper customerAddressMapper;

    @Autowired
    private CustomerAddressService customerAddressService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restCustomerAddressMockMvc;

    private CustomerAddress customerAddress;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CustomerAddressResource customerAddressResource = new CustomerAddressResource(customerAddressService);
        this.restCustomerAddressMockMvc = MockMvcBuilders.standaloneSetup(customerAddressResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustomerAddress createEntity(EntityManager em) {
        CustomerAddress customerAddress = new CustomerAddress()
            .address(DEFAULT_ADDRESS)
            .receiverName(DEFAULT_RECEIVER_NAME)
            .receiverPhone(DEFAULT_RECEIVER_PHONE)
            .postalCode(DEFAULT_POSTAL_CODE)
            .lat(DEFAULT_LAT)
            .lng(DEFAULT_LNG);
        return customerAddress;
    }

    @Before
    public void initTest() {
        customerAddress = createEntity(em);
    }

    @Test
    @Transactional
    public void createCustomerAddress() throws Exception {
        int databaseSizeBeforeCreate = customerAddressRepository.findAll().size();

        // Create the CustomerAddress
        CustomerAddressDTO customerAddressDTO = customerAddressMapper.toDto(customerAddress);
        restCustomerAddressMockMvc.perform(post("/api/customer-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerAddressDTO)))
            .andExpect(status().isCreated());

        // Validate the CustomerAddress in the database
        List<CustomerAddress> customerAddressList = customerAddressRepository.findAll();
        assertThat(customerAddressList).hasSize(databaseSizeBeforeCreate + 1);
        CustomerAddress testCustomerAddress = customerAddressList.get(customerAddressList.size() - 1);
        assertThat(testCustomerAddress.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testCustomerAddress.getReceiverName()).isEqualTo(DEFAULT_RECEIVER_NAME);
        assertThat(testCustomerAddress.getReceiverPhone()).isEqualTo(DEFAULT_RECEIVER_PHONE);
        assertThat(testCustomerAddress.getPostalCode()).isEqualTo(DEFAULT_POSTAL_CODE);
        assertThat(testCustomerAddress.getLat()).isEqualTo(DEFAULT_LAT);
        assertThat(testCustomerAddress.getLng()).isEqualTo(DEFAULT_LNG);
    }

    @Test
    @Transactional
    public void createCustomerAddressWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = customerAddressRepository.findAll().size();

        // Create the CustomerAddress with an existing ID
        customerAddress.setId(1L);
        CustomerAddressDTO customerAddressDTO = customerAddressMapper.toDto(customerAddress);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomerAddressMockMvc.perform(post("/api/customer-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerAddressDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CustomerAddress in the database
        List<CustomerAddress> customerAddressList = customerAddressRepository.findAll();
        assertThat(customerAddressList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCustomerAddresses() throws Exception {
        // Initialize the database
        customerAddressRepository.saveAndFlush(customerAddress);

        // Get all the customerAddressList
        restCustomerAddressMockMvc.perform(get("/api/customer-addresses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customerAddress.getId().intValue())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].receiverName").value(hasItem(DEFAULT_RECEIVER_NAME.toString())))
            .andExpect(jsonPath("$.[*].receiverPhone").value(hasItem(DEFAULT_RECEIVER_PHONE.toString())))
            .andExpect(jsonPath("$.[*].postalCode").value(hasItem(DEFAULT_POSTAL_CODE.toString())))
            .andExpect(jsonPath("$.[*].lat").value(hasItem(DEFAULT_LAT.toString())))
            .andExpect(jsonPath("$.[*].lng").value(hasItem(DEFAULT_LNG.toString())));
    }
    
    @Test
    @Transactional
    public void getCustomerAddress() throws Exception {
        // Initialize the database
        customerAddressRepository.saveAndFlush(customerAddress);

        // Get the customerAddress
        restCustomerAddressMockMvc.perform(get("/api/customer-addresses/{id}", customerAddress.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(customerAddress.getId().intValue()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.receiverName").value(DEFAULT_RECEIVER_NAME.toString()))
            .andExpect(jsonPath("$.receiverPhone").value(DEFAULT_RECEIVER_PHONE.toString()))
            .andExpect(jsonPath("$.postalCode").value(DEFAULT_POSTAL_CODE.toString()))
            .andExpect(jsonPath("$.lat").value(DEFAULT_LAT.toString()))
            .andExpect(jsonPath("$.lng").value(DEFAULT_LNG.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCustomerAddress() throws Exception {
        // Get the customerAddress
        restCustomerAddressMockMvc.perform(get("/api/customer-addresses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCustomerAddress() throws Exception {
        // Initialize the database
        customerAddressRepository.saveAndFlush(customerAddress);

        int databaseSizeBeforeUpdate = customerAddressRepository.findAll().size();

        // Update the customerAddress
        CustomerAddress updatedCustomerAddress = customerAddressRepository.findById(customerAddress.getId()).get();
        // Disconnect from session so that the updates on updatedCustomerAddress are not directly saved in db
        em.detach(updatedCustomerAddress);
        updatedCustomerAddress
            .address(UPDATED_ADDRESS)
            .receiverName(UPDATED_RECEIVER_NAME)
            .receiverPhone(UPDATED_RECEIVER_PHONE)
            .postalCode(UPDATED_POSTAL_CODE)
            .lat(UPDATED_LAT)
            .lng(UPDATED_LNG);
        CustomerAddressDTO customerAddressDTO = customerAddressMapper.toDto(updatedCustomerAddress);

        restCustomerAddressMockMvc.perform(put("/api/customer-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerAddressDTO)))
            .andExpect(status().isOk());

        // Validate the CustomerAddress in the database
        List<CustomerAddress> customerAddressList = customerAddressRepository.findAll();
        assertThat(customerAddressList).hasSize(databaseSizeBeforeUpdate);
        CustomerAddress testCustomerAddress = customerAddressList.get(customerAddressList.size() - 1);
        assertThat(testCustomerAddress.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testCustomerAddress.getReceiverName()).isEqualTo(UPDATED_RECEIVER_NAME);
        assertThat(testCustomerAddress.getReceiverPhone()).isEqualTo(UPDATED_RECEIVER_PHONE);
        assertThat(testCustomerAddress.getPostalCode()).isEqualTo(UPDATED_POSTAL_CODE);
        assertThat(testCustomerAddress.getLat()).isEqualTo(UPDATED_LAT);
        assertThat(testCustomerAddress.getLng()).isEqualTo(UPDATED_LNG);
    }

    @Test
    @Transactional
    public void updateNonExistingCustomerAddress() throws Exception {
        int databaseSizeBeforeUpdate = customerAddressRepository.findAll().size();

        // Create the CustomerAddress
        CustomerAddressDTO customerAddressDTO = customerAddressMapper.toDto(customerAddress);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomerAddressMockMvc.perform(put("/api/customer-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerAddressDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CustomerAddress in the database
        List<CustomerAddress> customerAddressList = customerAddressRepository.findAll();
        assertThat(customerAddressList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCustomerAddress() throws Exception {
        // Initialize the database
        customerAddressRepository.saveAndFlush(customerAddress);

        int databaseSizeBeforeDelete = customerAddressRepository.findAll().size();

        // Delete the customerAddress
        restCustomerAddressMockMvc.perform(delete("/api/customer-addresses/{id}", customerAddress.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CustomerAddress> customerAddressList = customerAddressRepository.findAll();
        assertThat(customerAddressList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomerAddress.class);
        CustomerAddress customerAddress1 = new CustomerAddress();
        customerAddress1.setId(1L);
        CustomerAddress customerAddress2 = new CustomerAddress();
        customerAddress2.setId(customerAddress1.getId());
        assertThat(customerAddress1).isEqualTo(customerAddress2);
        customerAddress2.setId(2L);
        assertThat(customerAddress1).isNotEqualTo(customerAddress2);
        customerAddress1.setId(null);
        assertThat(customerAddress1).isNotEqualTo(customerAddress2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomerAddressDTO.class);
        CustomerAddressDTO customerAddressDTO1 = new CustomerAddressDTO();
        customerAddressDTO1.setId(1L);
        CustomerAddressDTO customerAddressDTO2 = new CustomerAddressDTO();
        assertThat(customerAddressDTO1).isNotEqualTo(customerAddressDTO2);
        customerAddressDTO2.setId(customerAddressDTO1.getId());
        assertThat(customerAddressDTO1).isEqualTo(customerAddressDTO2);
        customerAddressDTO2.setId(2L);
        assertThat(customerAddressDTO1).isNotEqualTo(customerAddressDTO2);
        customerAddressDTO1.setId(null);
        assertThat(customerAddressDTO1).isNotEqualTo(customerAddressDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(customerAddressMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(customerAddressMapper.fromId(null)).isNull();
    }
}
