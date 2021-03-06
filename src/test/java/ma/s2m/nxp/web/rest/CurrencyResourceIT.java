package ma.s2m.nxp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import ma.s2m.nxp.IntegrationTest;
import ma.s2m.nxp.domain.Currency;
import ma.s2m.nxp.repository.CurrencyRepository;
import ma.s2m.nxp.service.dto.CurrencyDTO;
import ma.s2m.nxp.service.mapper.CurrencyMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CurrencyResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CurrencyResourceIT {

    private static final String DEFAULT_CUR_ALPH_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CUR_ALPH_CODE = "BBBBBBBBBB";

    private static final Long DEFAULT_CUR_DEFA_NUMB_DECI = 1L;
    private static final Long UPDATED_CUR_DEFA_NUMB_DECI = 2L;

    private static final String DEFAULT_CUR_LABE = "AAAAAAAAAA";
    private static final String UPDATED_CUR_LABE = "BBBBBBBBBB";

    private static final String DEFAULT_CUR_SYMB = "AAAAAAAAAA";
    private static final String UPDATED_CUR_SYMB = "BBBBBBBBBB";

    private static final String DEFAULT_CUR_IDEN = "AAAAAAAAAA";
    private static final String UPDATED_CUR_IDEN = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/currencies";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{curCode}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private CurrencyMapper currencyMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCurrencyMockMvc;

    private Currency currency;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Currency createEntity(EntityManager em) {
        Currency currency = new Currency()
            .curAlphCode(DEFAULT_CUR_ALPH_CODE)
            .curDefaNumbDeci(DEFAULT_CUR_DEFA_NUMB_DECI)
            .curLabe(DEFAULT_CUR_LABE)
            .curSymb(DEFAULT_CUR_SYMB)
            .curIden(DEFAULT_CUR_IDEN);
        return currency;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Currency createUpdatedEntity(EntityManager em) {
        Currency currency = new Currency()
            .curAlphCode(UPDATED_CUR_ALPH_CODE)
            .curDefaNumbDeci(UPDATED_CUR_DEFA_NUMB_DECI)
            .curLabe(UPDATED_CUR_LABE)
            .curSymb(UPDATED_CUR_SYMB)
            .curIden(UPDATED_CUR_IDEN);
        return currency;
    }

    @BeforeEach
    public void initTest() {
        currency = createEntity(em);
    }

    @Test
    @Transactional
    void createCurrency() throws Exception {
        int databaseSizeBeforeCreate = currencyRepository.findAll().size();
        // Create the Currency
        CurrencyDTO currencyDTO = currencyMapper.toDto(currency);
        restCurrencyMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(currencyDTO)))
            .andExpect(status().isCreated());

        // Validate the Currency in the database
        List<Currency> currencyList = currencyRepository.findAll();
        assertThat(currencyList).hasSize(databaseSizeBeforeCreate + 1);
        Currency testCurrency = currencyList.get(currencyList.size() - 1);
        assertThat(testCurrency.getCurAlphCode()).isEqualTo(DEFAULT_CUR_ALPH_CODE);
        assertThat(testCurrency.getCurDefaNumbDeci()).isEqualTo(DEFAULT_CUR_DEFA_NUMB_DECI);
        assertThat(testCurrency.getCurLabe()).isEqualTo(DEFAULT_CUR_LABE);
        assertThat(testCurrency.getCurSymb()).isEqualTo(DEFAULT_CUR_SYMB);
        assertThat(testCurrency.getCurIden()).isEqualTo(DEFAULT_CUR_IDEN);
    }

    @Test
    @Transactional
    void createCurrencyWithExistingId() throws Exception {
        // Create the Currency with an existing ID
        currency.setCurCode(1L);
        CurrencyDTO currencyDTO = currencyMapper.toDto(currency);

        int databaseSizeBeforeCreate = currencyRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCurrencyMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(currencyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Currency in the database
        List<Currency> currencyList = currencyRepository.findAll();
        assertThat(currencyList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCurrencies() throws Exception {
        // Initialize the database
        currencyRepository.saveAndFlush(currency);

        // Get all the currencyList
        restCurrencyMockMvc
            .perform(get(ENTITY_API_URL + "?sort=curCode,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].curCode").value(hasItem(currency.getCurCode().intValue())))
            .andExpect(jsonPath("$.[*].curAlphCode").value(hasItem(DEFAULT_CUR_ALPH_CODE)))
            .andExpect(jsonPath("$.[*].curDefaNumbDeci").value(hasItem(DEFAULT_CUR_DEFA_NUMB_DECI.intValue())))
            .andExpect(jsonPath("$.[*].curLabe").value(hasItem(DEFAULT_CUR_LABE)))
            .andExpect(jsonPath("$.[*].curSymb").value(hasItem(DEFAULT_CUR_SYMB)))
            .andExpect(jsonPath("$.[*].curIden").value(hasItem(DEFAULT_CUR_IDEN)));
    }

    @Test
    @Transactional
    void getCurrency() throws Exception {
        // Initialize the database
        currencyRepository.saveAndFlush(currency);

        // Get the currency
        restCurrencyMockMvc
            .perform(get(ENTITY_API_URL_ID, currency.getCurCode()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.curCode").value(currency.getCurCode().intValue()))
            .andExpect(jsonPath("$.curAlphCode").value(DEFAULT_CUR_ALPH_CODE))
            .andExpect(jsonPath("$.curDefaNumbDeci").value(DEFAULT_CUR_DEFA_NUMB_DECI.intValue()))
            .andExpect(jsonPath("$.curLabe").value(DEFAULT_CUR_LABE))
            .andExpect(jsonPath("$.curSymb").value(DEFAULT_CUR_SYMB))
            .andExpect(jsonPath("$.curIden").value(DEFAULT_CUR_IDEN));
    }

    @Test
    @Transactional
    void getNonExistingCurrency() throws Exception {
        // Get the currency
        restCurrencyMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewCurrency() throws Exception {
        // Initialize the database
        currencyRepository.saveAndFlush(currency);

        int databaseSizeBeforeUpdate = currencyRepository.findAll().size();

        // Update the currency
        Currency updatedCurrency = currencyRepository.findById(currency.getCurCode()).get();
        // Disconnect from session so that the updates on updatedCurrency are not directly saved in db
        em.detach(updatedCurrency);
        updatedCurrency
            .curAlphCode(UPDATED_CUR_ALPH_CODE)
            .curDefaNumbDeci(UPDATED_CUR_DEFA_NUMB_DECI)
            .curLabe(UPDATED_CUR_LABE)
            .curSymb(UPDATED_CUR_SYMB)
            .curIden(UPDATED_CUR_IDEN);
        CurrencyDTO currencyDTO = currencyMapper.toDto(updatedCurrency);

        restCurrencyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, currencyDTO.getCurCode())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(currencyDTO))
            )
            .andExpect(status().isOk());

        // Validate the Currency in the database
        List<Currency> currencyList = currencyRepository.findAll();
        assertThat(currencyList).hasSize(databaseSizeBeforeUpdate);
        Currency testCurrency = currencyList.get(currencyList.size() - 1);
        assertThat(testCurrency.getCurAlphCode()).isEqualTo(UPDATED_CUR_ALPH_CODE);
        assertThat(testCurrency.getCurDefaNumbDeci()).isEqualTo(UPDATED_CUR_DEFA_NUMB_DECI);
        assertThat(testCurrency.getCurLabe()).isEqualTo(UPDATED_CUR_LABE);
        assertThat(testCurrency.getCurSymb()).isEqualTo(UPDATED_CUR_SYMB);
        assertThat(testCurrency.getCurIden()).isEqualTo(UPDATED_CUR_IDEN);
    }

    @Test
    @Transactional
    void putNonExistingCurrency() throws Exception {
        int databaseSizeBeforeUpdate = currencyRepository.findAll().size();
        currency.setCurCode(count.incrementAndGet());

        // Create the Currency
        CurrencyDTO currencyDTO = currencyMapper.toDto(currency);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCurrencyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, currencyDTO.getCurCode())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(currencyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Currency in the database
        List<Currency> currencyList = currencyRepository.findAll();
        assertThat(currencyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCurrency() throws Exception {
        int databaseSizeBeforeUpdate = currencyRepository.findAll().size();
        currency.setCurCode(count.incrementAndGet());

        // Create the Currency
        CurrencyDTO currencyDTO = currencyMapper.toDto(currency);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCurrencyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(currencyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Currency in the database
        List<Currency> currencyList = currencyRepository.findAll();
        assertThat(currencyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCurrency() throws Exception {
        int databaseSizeBeforeUpdate = currencyRepository.findAll().size();
        currency.setCurCode(count.incrementAndGet());

        // Create the Currency
        CurrencyDTO currencyDTO = currencyMapper.toDto(currency);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCurrencyMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(currencyDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Currency in the database
        List<Currency> currencyList = currencyRepository.findAll();
        assertThat(currencyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCurrencyWithPatch() throws Exception {
        // Initialize the database
        currencyRepository.saveAndFlush(currency);

        int databaseSizeBeforeUpdate = currencyRepository.findAll().size();

        // Update the currency using partial update
        Currency partialUpdatedCurrency = new Currency();
        partialUpdatedCurrency.setCurCode(currency.getCurCode());

        partialUpdatedCurrency.curAlphCode(UPDATED_CUR_ALPH_CODE).curLabe(UPDATED_CUR_LABE).curIden(UPDATED_CUR_IDEN);

        restCurrencyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCurrency.getCurCode())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCurrency))
            )
            .andExpect(status().isOk());

        // Validate the Currency in the database
        List<Currency> currencyList = currencyRepository.findAll();
        assertThat(currencyList).hasSize(databaseSizeBeforeUpdate);
        Currency testCurrency = currencyList.get(currencyList.size() - 1);
        assertThat(testCurrency.getCurAlphCode()).isEqualTo(UPDATED_CUR_ALPH_CODE);
        assertThat(testCurrency.getCurDefaNumbDeci()).isEqualTo(DEFAULT_CUR_DEFA_NUMB_DECI);
        assertThat(testCurrency.getCurLabe()).isEqualTo(UPDATED_CUR_LABE);
        assertThat(testCurrency.getCurSymb()).isEqualTo(DEFAULT_CUR_SYMB);
        assertThat(testCurrency.getCurIden()).isEqualTo(UPDATED_CUR_IDEN);
    }

    @Test
    @Transactional
    void fullUpdateCurrencyWithPatch() throws Exception {
        // Initialize the database
        currencyRepository.saveAndFlush(currency);

        int databaseSizeBeforeUpdate = currencyRepository.findAll().size();

        // Update the currency using partial update
        Currency partialUpdatedCurrency = new Currency();
        partialUpdatedCurrency.setCurCode(currency.getCurCode());

        partialUpdatedCurrency
            .curAlphCode(UPDATED_CUR_ALPH_CODE)
            .curDefaNumbDeci(UPDATED_CUR_DEFA_NUMB_DECI)
            .curLabe(UPDATED_CUR_LABE)
            .curSymb(UPDATED_CUR_SYMB)
            .curIden(UPDATED_CUR_IDEN);

        restCurrencyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCurrency.getCurCode())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCurrency))
            )
            .andExpect(status().isOk());

        // Validate the Currency in the database
        List<Currency> currencyList = currencyRepository.findAll();
        assertThat(currencyList).hasSize(databaseSizeBeforeUpdate);
        Currency testCurrency = currencyList.get(currencyList.size() - 1);
        assertThat(testCurrency.getCurAlphCode()).isEqualTo(UPDATED_CUR_ALPH_CODE);
        assertThat(testCurrency.getCurDefaNumbDeci()).isEqualTo(UPDATED_CUR_DEFA_NUMB_DECI);
        assertThat(testCurrency.getCurLabe()).isEqualTo(UPDATED_CUR_LABE);
        assertThat(testCurrency.getCurSymb()).isEqualTo(UPDATED_CUR_SYMB);
        assertThat(testCurrency.getCurIden()).isEqualTo(UPDATED_CUR_IDEN);
    }

    @Test
    @Transactional
    void patchNonExistingCurrency() throws Exception {
        int databaseSizeBeforeUpdate = currencyRepository.findAll().size();
        currency.setCurCode(count.incrementAndGet());

        // Create the Currency
        CurrencyDTO currencyDTO = currencyMapper.toDto(currency);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCurrencyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, currencyDTO.getCurCode())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(currencyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Currency in the database
        List<Currency> currencyList = currencyRepository.findAll();
        assertThat(currencyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCurrency() throws Exception {
        int databaseSizeBeforeUpdate = currencyRepository.findAll().size();
        currency.setCurCode(count.incrementAndGet());

        // Create the Currency
        CurrencyDTO currencyDTO = currencyMapper.toDto(currency);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCurrencyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(currencyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Currency in the database
        List<Currency> currencyList = currencyRepository.findAll();
        assertThat(currencyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCurrency() throws Exception {
        int databaseSizeBeforeUpdate = currencyRepository.findAll().size();
        currency.setCurCode(count.incrementAndGet());

        // Create the Currency
        CurrencyDTO currencyDTO = currencyMapper.toDto(currency);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCurrencyMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(currencyDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Currency in the database
        List<Currency> currencyList = currencyRepository.findAll();
        assertThat(currencyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCurrency() throws Exception {
        // Initialize the database
        currencyRepository.saveAndFlush(currency);

        int databaseSizeBeforeDelete = currencyRepository.findAll().size();

        // Delete the currency
        restCurrencyMockMvc
            .perform(delete(ENTITY_API_URL_ID, currency.getCurCode()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Currency> currencyList = currencyRepository.findAll();
        assertThat(currencyList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
