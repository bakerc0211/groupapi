package io.catalyte.training.sportsproducts.domains.purchase;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import io.catalyte.training.sportsproducts.data.ProductFactory;
import io.catalyte.training.sportsproducts.domains.product.Product;

import io.catalyte.training.sportsproducts.domains.product.ProductRepository;
import io.catalyte.training.sportsproducts.domains.product.ProductService;
import io.catalyte.training.sportsproducts.exceptions.UnprocessableEntity;
import java.util.HashSet;
import java.util.Optional;
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
  private  PurchaseRepository purchaseRepository;
 @Mock
  private ProductRepository productRepository;

 @Mock
   private ProductService productService;

 @Mock
   private  LineItemRepository lineItemRepository;

  ProductFactory productFactory;


  Purchase activePurchase = new Purchase();

  Purchase inactivePurchase = new Purchase();

  Product activeProduct = new Product();

  Product inactiveProduct = new Product();


 LineItem activeLineItem = new LineItem();

 LineItem inactiveLineItem = new LineItem();

 Set<LineItem> activeL = new HashSet<>();

 Set<LineItem> inactiveL = new HashSet<>();








  @Before
  public void setup(){
    MockitoAnnotations.initMocks(this);
    productFactory = new ProductFactory();
    inactiveProduct.setId(2L);
    inactiveProduct.setActive(false);
    activeProduct.setId(1L);
    activeProduct.setActive(true);
    activeLineItem.setProduct(activeProduct);
    activeLineItem.setId(1L);
    activeLineItem.setQuantity(1);
    activeLineItem.setPurchase(activePurchase);
    inactiveLineItem.setProduct(inactiveProduct);
    inactiveLineItem.setId(2L);
    inactiveLineItem.setQuantity(1);
    inactiveLineItem.setPurchase(inactivePurchase);
    activeL.add(activeLineItem);
    inactiveL.add(inactiveLineItem);
    activePurchase.setProducts(activeL);
    inactivePurchase.setId(2L);
    inactivePurchase.setProducts(inactiveL);
    when(productService.getProductById(anyLong())).thenReturn(activeProduct);
    when(lineItemRepository.save(any(LineItem.class))).thenReturn(activeLineItem);
    when(productRepository.findById(anyLong())).thenReturn(Optional.of(activeProduct));
    when(purchaseRepository.save(any(Purchase.class))).thenReturn(activePurchase);

  }



  @Test
  public void saveActivePurchase() {
    Product testProduct = new Product();
    Purchase testPurchase = activePurchase;
    testProduct.setId(1L);
    testProduct.setActive(true);
    Set<LineItem> testLine = new HashSet<>();
    LineItem lineTest = new LineItem(1L, testPurchase, testProduct, 1);
    testLine.add(lineTest);
    Purchase results = purchaseServiceImpl.savePurchase(testPurchase);

    assertEquals(activePurchase, results);





  }

  @Test
  public void inactivePurchaseThrowsUnprocessableEntity() {
    when(productService.getProductById(anyLong())).thenReturn(inactiveProduct);
    when(lineItemRepository.save(any(LineItem.class))).thenReturn(inactiveLineItem);
    when(purchaseRepository.save(any(Purchase.class))).thenReturn(null);
    assertThrows(UnprocessableEntity.class, ()-> purchaseServiceImpl.savePurchase(inactivePurchase));
  }
}