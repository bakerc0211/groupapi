package io.catalyte.training.sportsproducts.domains.product;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;

public interface ProductRepositoryCustom {

  PagedListHolder filterProduct(HashMap<String, List<String>> filter, Pageable pageable, int pageNumber);
}

