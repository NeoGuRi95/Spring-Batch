package com.ll.exam.app_2022_09_22.app.cart.service;

import com.ll.exam.app_2022_09_22.app.cart.entity.CartItem;
import com.ll.exam.app_2022_09_22.app.cart.repository.CartItemRepository;
import com.ll.exam.app_2022_09_22.app.member.entity.Member;
import com.ll.exam.app_2022_09_22.app.product.entity.ProductOption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartItemRepository cartItemRepository;

    public CartItem addItem(Member member, ProductOption productOption, int quantity) {
        Optional<CartItem> optOldCartItem = cartItemRepository.findByMemberIdAndProductOptionId(member.getId(), productOption.getId());

        if (optOldCartItem.isPresent()) {
            CartItem cartItem = optOldCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItemRepository.save(cartItem);

            return cartItem;
        } else {
            CartItem cartItem = CartItem.builder()
                    .member(member)
                    .productOption(productOption)
                    .quantity(quantity)
                    .build();
            cartItemRepository.save(cartItem);

            return cartItem;
        }
    }
}