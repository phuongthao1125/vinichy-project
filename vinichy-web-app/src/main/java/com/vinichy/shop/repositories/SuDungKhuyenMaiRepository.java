package com.vinichy.shop.repositories;

import com.vinichy.shop.models.SuDungKhuyenMai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SuDungKhuyenMaiRepository extends JpaRepository<SuDungKhuyenMai, Long> {
    java.util.List<SuDungKhuyenMai> findByTaiKhoan_IdAndKhuyenMai_MaGiamGia(Long taiKhoanId, String maGiamGia);
    long countByKhuyenMai_MaGiamGia(String maGiamGia);
}
