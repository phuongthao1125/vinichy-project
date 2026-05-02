package com.vinichy.shop.repositories;

import com.vinichy.shop.models.KhuyenMai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KhuyenMaiRepository extends JpaRepository<KhuyenMai, String> {
    Optional<KhuyenMai> findByMaGiamGiaAndTrangThai(String maGiamGia, Integer trangThai);
}
