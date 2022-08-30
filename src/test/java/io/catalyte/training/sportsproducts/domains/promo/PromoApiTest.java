package io.catalyte.training.sportsproducts.domains.promo;

import static io.catalyte.training.sportsproducts.constants.Paths.PROMOS_PATH;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PromoApiTest {

  @Autowired
  private WebApplicationContext wac;
  private MockMvc mockMvc;

  private Promo testPromo;
  private ObjectMapper objectMapper;
  private PromoTestHelper promoTestHelper;

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    objectMapper = new ObjectMapper();
    promoTestHelper = new PromoTestHelper();
    testPromo = promoTestHelper.generateValidPromoCode();
  }

  @Test
  public void postValidPromoReturns201() throws Exception {
    mockMvc.perform(post(PROMOS_PATH).contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(testPromo))).andExpect(status().isCreated());
  }

  @Test
  public void postInvalidPromoReturns400() throws Exception {
    testPromo.setType("notAFlat");
    mockMvc.perform(post(PROMOS_PATH).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(testPromo))).
        andExpect(status().isBadRequest());
  }

}
