package com.vinichy.shop.repositories;

import com.vinichy.shop.models.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SanPhamRepository extends JpaRepository<SanPham, Long> {

    @Query("SELECT DISTINCT s FROM SanPham s LEFT JOIN FETCH s.sanPhamCTList ct LEFT JOIN FETCH ct.hinhAnhList WHERE s.noiBat = true")
    List<SanPham> findByNoiBatTrue();

    @Query("SELECT DISTINCT s FROM SanPham s LEFT JOIN FETCH s.sanPhamCTList ct LEFT JOIN FETCH ct.hinhAnhList WHERE s.loaiSP.id = :loaiId")
    List<SanPham> findByLoaiSPId(@Param("loaiId") Long loaiId);

    @Query("SELECT DISTINCT s FROM SanPham s LEFT JOIN FETCH s.sanPhamCTList ct LEFT JOIN FETCH ct.hinhAnhList WHERE s.loaiSP.slug = :slug")
    List<SanPham> findByLoaiSPSlug(@Param("slug") String slug);

    @Query("SELECT DISTINCT s FROM SanPham s LEFT JOIN FETCH s.sanPhamCTList ct LEFT JOIN FETCH ct.hinhAnhList WHERE LOWER(s.tenSP) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<SanPham> findByTenSPContainingIgnoreCase(@Param("keyword") String keyword);

    @Query("SELECT DISTINCT s FROM SanPham s LEFT JOIN FETCH s.sanPhamCTList ct LEFT JOIN FETCH ct.hinhAnhList")
    List<SanPham> findAllWithImages();
}
