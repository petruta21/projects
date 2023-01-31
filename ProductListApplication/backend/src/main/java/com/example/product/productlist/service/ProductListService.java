package com.example.product.productlist.service;

import com.example.product.productlist.persistence.ProductList;
import com.example.product.productlist.persistence.ProductListPagingRepository;
import com.example.product.productlist.web.ProductListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductListService {

    private final ProductListPagingRepository productListRepository;

    public ProductListService(ProductListPagingRepository productListRepository) {
        this.productListRepository = productListRepository;
    }

    public List<ProductListDTO> list() {
        return StreamSupport.stream(productListRepository.findAll().spliterator(), false).map(ProductListService::convertToProductListDTO).collect(Collectors.toList());
    }

    public ProductListDTO save(ProductListDTO productListDto) {
        return convertToProductListDTO(productListRepository.save(convertToProductList(productListDto)));
    }

    public void deleteById(Long id) {
        productListRepository.deleteById(id);
    }

    public ProductListDTO saveOrUpdate(ProductListDTO productListDto) {
        return convertToProductListDTO(productListRepository.save(convertToProductList(productListDto)));
    }


    public ProductListDTO getUserById(Long id) {
        return productListRepository.findById(id).map(ProductListService::convertToProductListDTO).orElse(null);
    }

    public ProductListDTO update(ProductListDTO productListDto, Long id) {
        ProductList productList = new ProductList();
        productList.setProductListId(id);
        productList.setProductName(productListDto.getName());
        productList.setProductAmount(productListDto.getAmount());
        productList.setProductBought(productListDto.getBought());
        return convertToProductListDTO(productListRepository.save(productList));
    }

    private ProductList convertToProductList(ProductListDTO productListDto) {
        ProductList productList = new ProductList();
        productList.setProductListId(productListDto.getId());
        productList.setProductName(productListDto.getName());
        productList.setProductAmount(productListDto.getAmount());
        productList.setProductBought(productListDto.getBought());
        return productList;
    }

    private static ProductListDTO convertToProductListDTO(ProductList c) {
        ProductListDTO result = new ProductListDTO();
        result.setId(c.getId());
        result.setName(c.getProductName());
        result.setAmount(c.getProductAmount());
        result.setBought(c.getProductBought());
        return result;
    }

    public List<ProductListDTO> listPaginated(Pageable pageable) {
        Page<ProductList> pagedResult = productListRepository.findAll(pageable);
        return pagedResult.toList().stream().map(ProductListService::convertToProductListDTO).collect(Collectors.toList());
    }
}
