package com.ll.exam.app_2022_09_22.app.cart.repository;

import com.ll.exam.app_2022_09_22.app.cart.entity.CartItem;
import com.ll.exam.app_2022_09_22.app.member.entity.Member;
import com.ll.exam.app_2022_09_22.app.product.entity.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByMemberIdAndProductOptionId(Long memberId, Long productId);

    List<CartItem> findAllByMemberId(Long memberId);
}