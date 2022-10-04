package io.catalyte.training.sportsproducts.domains.purchase;

import static io.catalyte.training.sportsproducts.constants.Paths.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.catalyte.training.sportsproducts.domains.purchase.dto.CreditCardDTO;
import io.catalyte.training.sportsproducts.domains.purchase.dto.PurchaseDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest

public class PurchaseApiTest {
  @Autowired
  private WebApplicationContext wac;

  private MockMvc mockMvc;

  ObjectMapper objectMapper = new ObjectMapper();

  ResultMatcher createdStatus = MockMvcResultMatchers.status().isCreated();

  ResultMatcher unprocessableEntity = MockMvcResultMatchers.status().isUnprocessableEntity();

  ResultMatcher badRequest = MockMvcResultMatchers.status().isBadRequest();

  ResultMatcher expectedType = MockMvcResultMatchers.content()
      .contentType(MediaType.APPLICATION_JSON);


  @Before
  public void setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  @Test
  public void postInactiveProductPurchaseReturns422Test () throws Exception {
    PurchaseDTO mockPurchaseObject = PurchaseTestHelper.generateValidPurchase();
    mockPurchaseObject.setProducts(PurchaseTestHelper.generateInactiveLineItems());
    String mockPurchase = objectMapper.writeValueAsString(mockPurchaseObject);
    mockMvc.perform(post(PURCHASES_PATH)
      .contentType(MediaType.APPLICATION_JSON)
      .content((mockPurchase)))
      .andExpect(unprocessableEntity)
      .andExpect(expectedType);
  }

  @Test
  public void postInvalidCreditCardPurchaseReturns400Test () throws Exception {
    PurchaseDTO mockPurchaseObject = PurchaseTestHelper.generateValidPurchase();
    CreditCardDTO mockCreditCardObject = PurchaseTestHelper.generateValidCreditCard();
    mockCreditCardObject.setCardNumber("1");
    mockPurchaseObject.setCreditCard(mockCreditCardObject);
    String mockPurchase = objectMapper.writeValueAsString(mockPurchaseObject);
    mockMvc.perform(post(PURCHASES_PATH)
      .contentType(MediaType.APPLICATION_JSON)
      .content((mockPurchase)))
      .andExpect(badRequest)
      .andExpect(expectedType);
  }

  @Test
  public void findAllPurchasesByEmailReturns200() throws Exception {
    mockMvc.perform(get(PURCHASES_PATH + "/blah"))
            .andExpect(status().isOk());
  }

  @Test
  public void findAllPurchasesNoEmailReturns404() throws Exception {
    mockMvc.perform(get(PURCHASES_PATH ))
            .andExpect(status().isNotFound());
  }

  @Test
  public void findAllProductsWithReviewsReturns200() throws Exception {
    mockMvc.perform(get(PURCHASES_PATH + "/reviews"))
        .andExpect(status().isOk());
  }
}