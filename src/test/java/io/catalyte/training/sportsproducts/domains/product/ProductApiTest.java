package io.catalyte.training.sportsproducts.domains.product;

import static io.catalyte.training.sportsproducts.constants.Paths.CATEGORIES_PATH;
import static io.catalyte.training.sportsproducts.constants.Paths.PRODUCTS_PATH;
import static io.catalyte.training.sportsproducts.constants.Paths.TYPES_PATH;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductApiTest {

  @Autowired
  private WebApplicationContext wac;

  private MockMvc mockMvc;

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  @Test
  public void getProductsReturns200() throws Exception {
    mockMvc.perform(get(PRODUCTS_PATH))
        .andExpect(status().isOk());
  }

  @Test
  public void getPaginatedProductsReturns200() throws Exception {
    mockMvc.perform(get(PRODUCTS_PATH + "/page"))
            .andExpect(status().isOk());
  }

  @Test
  public void getProductByIdReturnsProductWith200() throws Exception {
    mockMvc.perform(get(PRODUCTS_PATH + "/1"))
        .andExpect(status().isOk());
  }

  @Test
  public void deleteProductByIdReturns200() throws Exception {
    mockMvc.perform(delete(PRODUCTS_PATH + "/12"))
        .andExpect(status().isOk());
  }

  @Test
  public void changeProductActiveStatusByIdReturns200() throws Exception {
    mockMvc.perform(put(PRODUCTS_PATH + "/1"))
        .andExpect(status().isOk());
  }

  @Test
  public void getDistinctCategoriesReturns200() throws Exception {
    mockMvc.perform(get(PRODUCTS_PATH + CATEGORIES_PATH))
        .andExpect(status().isOk());
  }

  @Test
  public void getDistinctTypesReturns200() throws Exception {
    mockMvc.perform(get(PRODUCTS_PATH + TYPES_PATH))
        .andExpect(status().isOk());
  }

  @Test
  public void filterProductReturns200() throws Exception {
    mockMvc.perform(get(PRODUCTS_PATH + "/filter?page=0"))
        .andExpect(status().isOk());
  }

  @Test
  public void getProductsByFilterBrand200() throws Exception {
    mockMvc.perform(get(PRODUCTS_PATH + "/filter?page=0&brand=Adidas"))
        .andExpect(status().isOk());
  }

  @Test
  public void getProductsByFilterCategory200() throws Exception {
    mockMvc.perform(get(PRODUCTS_PATH + "/filter?page=0&category=Weightlifting"))
        .andExpect(status().isOk());
  }

  @Test
  public void getProductsByFilterDemographic200() throws Exception {
    mockMvc.perform(get(PRODUCTS_PATH + "/filter?page=0&demographic=Non-Binary"))
        .andExpect(status().isOk());
  }

  @Test
  public void getProductsByFilterPrice200() throws Exception {
    mockMvc.perform(get(PRODUCTS_PATH + "/filter?page=0&minPrice=100&maxPrice=300"))
        .andExpect(status().isOk());
  }

  @Test
  public void getProductsByFilterColor200() throws Exception {
    mockMvc.perform(get(PRODUCTS_PATH + "/filter?page=0&primaryColorCode=%23f092b0"))
        .andExpect(status().isOk());
  }

  @Test
  public void getProductsByFilterMaterial200() throws Exception {
    mockMvc.perform(get(PRODUCTS_PATH + "/filter?page=0&material=Calico"))
        .andExpect(status().isOk());
  }
  @Test
  public void getProductsByFilterType200() throws Exception {
    mockMvc.perform(get(PRODUCTS_PATH + "/filter?page=0&type=Hat"))
            .andExpect(status().isOk());
  }
  @Test
  public void getProductsByFilterActive200() throws Exception {
    mockMvc.perform(get(PRODUCTS_PATH + "/filter?page=0&active"))
            .andExpect(status().isOk());
  }
  @Test
    public void getProductsByFilterColorCode200() throws Exception {
      mockMvc.perform(get(PRODUCTS_PATH + "/filter?page=0&colorCode=%23ffffff"))
              .andExpect(status().isOk());
    }

}
