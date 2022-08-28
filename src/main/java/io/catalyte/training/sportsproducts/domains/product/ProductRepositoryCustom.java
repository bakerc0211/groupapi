package io.catalyte.training.sportsproducts.domains.product;

import java.util.HashMap;
import java.util.List;

interface ProductRepositoryCustom {
 List<Product> filterProduct(HashMap<String, List<String>> filter);
}

