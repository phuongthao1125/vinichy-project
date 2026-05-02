package com.vinichy.shop.repositories;

import com.vinichy.shop.models.LoaiSP;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LoaiSPRepository extends JpaRepository<LoaiSP, Long> {
    Optional<LoaiSP> findBySlug(String slug);
}
