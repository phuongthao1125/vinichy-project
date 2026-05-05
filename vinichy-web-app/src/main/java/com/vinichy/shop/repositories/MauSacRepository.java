package com.vinichy.shop.repositories;

import com.vinichy.shop.models.MauSac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MauSacRepository extends JpaRepository<MauSac, String> {
}
