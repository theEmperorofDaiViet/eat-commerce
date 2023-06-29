package com.educative.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educative.ecommerce.dto.product.ProductDTO;
import com.educative.ecommerce.model.User;
import com.educative.ecommerce.model.WishList;
import com.educative.ecommerce.repository.WishListRepository;

@Service
public class WishListService {

	@Autowired
	private WishListRepository wishListRepository;
	
	public void addWishList(WishList wishList) {
		wishListRepository.save(wishList);
	}
	
	public List<ProductDTO> getWishList(User user) {
		List<WishList> wishLists = wishListRepository.findAllByUserOrderByCreatedDateDesc(user);
		List<ProductDTO> productDTOs = new ArrayList<>();
		for (WishList wishList: wishLists) {
			productDTOs.add(new ProductDTO(wishList.getProduct()));
		}
		return productDTOs;
	}
}
