package io.catalyte.training.sportsproducts.domains.product;

import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import io.catalyte.training.sportsproducts.data.ProductFactory;
import io.catalyte.training.sportsproducts.exceptions.ResourceNotFound;
import java.util.Optional;
import io.catalyte.training.sportsproducts.exceptions.ServerError;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.dao.DataAccessException;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(ProductServiceImpl.class)

public class ProductServiceImplTest {

  @InjectMocks
  private ProductServiceImpl productServiceImpl;

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Mock
  private ProductRepository productRepository;

  Product testProduct;

  ProductFactory productFactory;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    productFactory = new ProductFactory();
    testProduct = productFactory.createRandomProduct();
    when(productRepository.findById(anyLong())).thenReturn(Optional.of(testProduct));
  }

  @Test
  public void getProductByIdReturnsProduct() {
    Product actual = productServiceImpl.getProductById(123L);
    assertEquals(testProduct, actual);
  }

  @Test
  public void getProductByIdThrowsErrorWhenNotFound() {
    when(productRepository.findById(anyLong())).thenReturn(empty());
    assertThrows(ResourceNotFound.class, () -> productServiceImpl.getProductById(123L));
  }

  @Test
  public void getProductByIdThrowsErrorWhenServerError() {
    when(productRepository.findById(anyLong())).thenThrow(new DataAccessException("...") {
    });
    assertThrows(ServerError.class, () -> productServiceImpl.getProductById(123L));
  }

  @Test
  public void getDistinctTypesReturnsTypes() {
    List<String> uniqueTypes = new ArrayList<>();
    uniqueTypes.add("Short");
    when(productRepository.findDistinctTypes()).thenReturn(uniqueTypes);
    List<String> actual = productServiceImpl.getDistinctTypes();
    assertEquals(uniqueTypes, actual);
  }

  @Test
  public void getDistinctTypesThrowsErrorWhenServerError() {
    when(productRepository.findDistinctTypes()).thenThrow(new DataAccessException("...") {
    });
    assertThrows(ServerError.class, () -> productServiceImpl.getDistinctTypes());
  }

  @Test
  public void getDistinctCategoriesReturnsCategories() {
    List<String> uniqueCategories = new ArrayList<>();
    uniqueCategories.add("Running");
    when(productRepository.findDistinctTypes()).thenReturn(uniqueCategories);
    List<String> actual = productServiceImpl.getDistinctTypes();
    assertEquals(uniqueCategories, actual);
  }

  @Test
  public void getDistinctCategoriesThrowsErrorWhenServerError() {
    when(productRepository.findDistinctCategories()).thenThrow(new DataAccessException("...") {
    });
    assertThrows(ServerError.class, () -> productServiceImpl.getDistinctCategories());
  }
}
