package com.shop.sales.service.impl;

import com.shop.core.mapper.EntityFactory;
import com.shop.core.model.dto.ProductDto;
import com.shop.core.model.entity.Product;
import com.shop.sales.repository.ProductRepository;
import com.shop.sales.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository prodRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository prodRepository) {
        this.prodRepository = prodRepository;
    }


    @Override
    @Transactional
    public Product stockInProducts(ProductDto productDto) {
        return prodRepository.save(EntityFactory.convertToProductFactory(productDto));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> findAllProductsList(Pageable pageable) {
        return prodRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Optional<Product> findProductById(Long id) {
        return prodRepository.findById(id);
    }

    @Override
    @Transactional
    public List<Product> findNonZeroQuantsProducts(Set<Long> productsId) {
        return deductQuantsByCount(prodRepository.findAllByProdIdInAndQuantsIsNot(productsId, 0), 1);
    }


    private List<Product> deductQuantsByCount(List<Product> productList, int count) {// TODO: 10/25/2022 will refactor
        List<Product> deductedQuantsProductList = new ArrayList<>();
        for (Product product : productList) {
            product.setQuants(product.getQuants() - count);
            deductedQuantsProductList.add(product);
        }
        return deductedQuantsProductList;
    }

}
