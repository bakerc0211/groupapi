package io.catalyte.training.sportsproducts.domains.purchase;

import static io.catalyte.training.sportsproducts.constants.Paths.PURCHASES_PATH;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

  ResultMatcher createdStatus = MockMvcResultMatchers.status().isCreated();

  ResultMatcher unprocessableEntity = MockMvcResultMatchers.status().isUnprocessableEntity();

  ResultMatcher expectedType = MockMvcResultMatchers.content()
      .contentType(MediaType.APPLICATION_JSON);


  @Before
  public void setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  @Test

  public void postPurchaseReturns200() throws Exception {

    String mockPurchase = "{\r\n\"deliveryAddress\": {\r\n\"deliveryFirstName\": \"Max\",\r\n\"deliveryLastName"
        + "\":\"Space\",\r\n\"deliveryStreet\":\"123 Hickley\",\r\n\"deliveryStreet2\": null,"
        + "\r\n\"deliveryCity\": \"Birmingham\",\r\n\"deliveryState\": \"AL\",\r\n\"deliveryZip\":"
        + " \"43690\"\r\n},\r\n\"billingAddress\": {\r\n\"billingStreet\": \"123 Main\",\r\n\""
        + "billingStreet2\": \"Apt A\",\r\n\"billingCity\": \"Atlanta\",\r\n\"billingState\": \"GA\""
        + ",\r\n\"billingZip\": \"31675\",\r\n\"email\": \"customer@home.com\",\r\n\"phone\": "
        + "\"(714) 345-8765\"\r\n},\r\n\"creditCard\": {\r\n\"cardNumber\": \"1435678998761234\","
        + "\r\n\"cvv\": \"789\",\r\n\"expiration\": \"11/22\",\r\n\"cardHolder\": \"Max Perkins\"\r\n}"
        + ",\r\n\"products\": [\r\n{\r\n\"product\":{\r\n\"id\": 1,\r\n\"name\": \"Wicking Skateboarding Sock\""
        + ",\r\n\"brand\": \"Colombia\",\r\n\"description\": \"Men Wicking Skateboarding\",\r\n\""
        + "demographic\": \"Men\",\r\n\"category\": \"Skateboarding\",\r\n\"type\": \"Sock\",\r\n\""
        + "releaseDate\": \"2021-06-17\",\r\n\"primaryColorCode\": \"#b7c0c7\",\r\n\"secondaryColorCode\":"
        + " \"#f092b0\",\r\n\"styleNumber\": \"sc82216\",\r\n\"globalProductCode\": \"gpc-5531342\","
        + "\r\n\"imageSrc\": \"https://via.placeholder.com/640x360\",\r\n\"material\": \"Synthetic\","
        + "\r\n\"price\": 28.04,\r\n\"quantity\": 21,\r\n\"active\": true\r\n},\r\n\"quantity\": 1\r\n }\r\n]\r\n}";

        mockMvc.perform(post(PURCHASES_PATH)
        .contentType(MediaType.APPLICATION_JSON)
        .content((mockPurchase)))
    .andExpect(createdStatus)
    .andExpect(expectedType);

  }
  @Test
  public void postPurchaseReturns422Test() throws Exception {

    String mockPurchase = "{\r\n\"deliveryAddress\": {\r\n\"deliveryFirstName\": \"Max\",\r\n\"deliveryLastName"
        + "\":\"Space\",\r\n\"deliveryStreet\":\"123 Hickley\",\r\n\"deliveryStreet2\": null,"
        + "\r\n\"deliveryCity\": \"Birmingham\",\r\n\"deliveryState\": \"AL\",\r\n\"deliveryZip\":"
        + " \"43690\"\r\n},\r\n\"billingAddress\": {\r\n\"billingStreet\": \"123 Main\",\r\n\""
        + "billingStreet2\": \"Apt A\",\r\n\"billingCity\": \"Atlanta\",\r\n\"billingState\": \"GA\""
        + ",\r\n\"billingZip\": \"31675\",\r\n\"email\": \"customer@home.com\",\r\n\"phone\": "
        + "\"(714) 345-8765\"\r\n},\r\n\"creditCard\": {\r\n\"cardNumber\": \"1435678998761234\","
        + "\r\n\"cvv\": \"789\",\r\n\"expiration\": \"11/22\",\r\n\"cardHolder\": \"Max Perkins\"\r\n}"
        + ",\r\n\"products\": [\r\n{\r\n\"product\":{\r\n\"id\": 3,\r\n\"name\": \"Wicking Skateboarding Sock\""
        + ",\r\n\"brand\": \"Colombia\",\r\n\"description\": \"Men Wicking Skateboarding\",\r\n\""
        + "demographic\": \"Men\",\r\n\"category\": \"Skateboarding\",\r\n\"type\": \"Sock\",\r\n\""
        + "releaseDate\": \"2021-06-17\",\r\n\"primaryColorCode\": \"#b7c0c7\",\r\n\"secondaryColorCode\":"
        + " \"#f092b0\",\r\n\"styleNumber\": \"sc82216\",\r\n\"globalProductCode\": \"gpc-5531342\","
        + "\r\n\"imageSrc\": \"https://via.placeholder.com/640x360\",\r\n\"material\": \"Synthetic\","
        + "\r\n\"price\": 28.04,\r\n\"quantity\": 21,\r\n\"active\": true\r\n},\r\n\"quantity\": 1\r\n }\r\n]\r\n}";

       mockMvc.perform(post(PURCHASES_PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .content((mockPurchase)))
        .andExpect(unprocessableEntity)
        .andExpect(expectedType);
  }
}
