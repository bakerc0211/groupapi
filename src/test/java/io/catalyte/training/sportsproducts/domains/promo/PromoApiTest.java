package io.catalyte.training.sportsproducts.domains.promo;

import static io.catalyte.training.sportsproducts.constants.Paths.PROMOS_PATH;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }


  @Test
  public void postValidPromoReturns201() throws Exception {
    String mockPromo = "{\n"
        + "    \"title\":\"A\",\n"
        + "    \"description\":\"something\",\n"
        + "    \"type\":\"Flat\",\n"
        + "    \"rate\":\"$2222222222.20\",\n"
        + "    \"expirationDate\":\"05/10/69\"\n"
        + "\n"
        + "}";
    mockMvc.perform(post(PROMOS_PATH).contentType(MediaType.APPLICATION_JSON).content((mockPromo))).
        andExpect(status().isCreated());
  }

  @Test
  public void postInvalidPromoReturns400() throws Exception {
    String mockPromo = "{\n"
        + "    \"title\":\"aaaaa\",\n"
        + "    \"description\":\"something\",\n"
        + "    \"type\":\"notAflat\",\n"
        + "    \"rate\":\"0.20\",\n"
        + "    \"expirationDate\":\"05/10/1969\"\n"
        + "\n"
        + "}";
    mockMvc.perform(post(PROMOS_PATH).contentType(MediaType.APPLICATION_JSON).content((mockPromo))).
        andExpect(status().isBadRequest());
  }

}
