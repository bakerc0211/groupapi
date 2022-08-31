package io.catalyte.training.sportsproducts.domains.purchase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import io.catalyte.training.sportsproducts.domains.product.Product;
import io.catalyte.training.sportsproducts.domains.product.ProductService;
import io.catalyte.training.sportsproducts.exceptions.UnprocessableEntity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(PurchaseServiceImpl.class)

public class PurchaseServiceImplTest {

  @InjectMocks
  private PurchaseServiceImpl purchaseServiceImpl;

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Mock
  private PurchaseRepository purchaseRepository;
  @Mock
  private ProductService productService;

  @Mock  private LineItemRepository lineItemRepository;
  Purchase testPurchase;
  List<Purchase> testPurchaseList = new ArrayList<>();

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    activeProduct.setId(1L);
    activeProduct.setActive(true);
    activeProduct.setQuantity(1);
    activeProduct.setActive(true);
    activeProduct.setPrice(1.00F);
    inactiveProduct.setPrice(1.00F);
    inactiveProduct.setId(2L);
    inactiveProduct.setActive(false);
    inactiveProduct.setQuantity(1);
    activeLineItem.setProduct(activeProduct);
    activeLineItem.setId(1L);
    activeLineItem.setQuantity(1);
    inactiveLineItem.setProduct(inactiveProduct);
    inactiveLineItem.setId(2L);
    inactiveLineItem.setQuantity(1);
    inactiveLineItem.setPurchase(inactivePurchase);
    activeL.add(activeLineItem);
    inactiveL.add(inactiveLineItem);
    activePurchase.setProducts(activeL);
    inactivePurchase.setId(2L);
    inactivePurchase.setProducts(inactiveL);
  }
  Purchase activePurchase = new Purchase();
  Purchase inactivePurchase = new Purchase();
  Product activeProduct = new Product();
  Product inactiveProduct = new Product();
  LineItem activeLineItem = new LineItem();
  LineItem inactiveLineItem = new LineItem();
  Set<LineItem> activeL = new HashSet<>();
  Set<LineItem> inactiveL = new HashSet<>();

  @Test
  public void saveActivePurchaseToDataBase() {
    when(productService.getProductById(anyLong())).thenReturn(activeProduct);
    when(lineItemRepository.save(any(LineItem.class))).thenReturn(activeLineItem);
    when(purchaseRepository.save(any(Purchase.class))).thenReturn(activePurchase);
    Purchase testPurchase = activePurchase;
    Purchase results = purchaseServiceImpl.savePurchase(testPurchase);
    assertEquals(activePurchase, results);

  }
  @Test
  public void inactivePurchaseThrowsUnprocessableEntity() {
    when(productService.getProductById(anyLong())).thenReturn(inactiveProduct);
    assertThrows(UnprocessableEntity.class,
        () -> purchaseServiceImpl.savePurchase(inactivePurchase));
  }
  @Test
  public void getAllPurchasesFromDataBase() {
    when(purchaseRepository.findAll()).thenReturn(testPurchaseList);
    List<Purchase> actual = purchaseServiceImpl.findAllPurchases();
    assertEquals(testPurchaseList, actual);
  }
}

