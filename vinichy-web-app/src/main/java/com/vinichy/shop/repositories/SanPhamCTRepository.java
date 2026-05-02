package com.vinichy.shop.repositories;

import com.vinichy.shop.models.SanPhamCT;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.repository.Lock;
import jakarta.persistence.LockModeType;

public interface SanPhamCTRepository extends JpaRepository<SanPhamCT, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM SanPhamCT s WHERE s.id IN :ids")
    List<SanPhamCT> findByIdsWithLock(@Param("ids") List<Long> ids);

    @Query("SELECT s FROM SanPhamCT s WHERE s.sanPham.id = :sanPhamId")
    List<SanPhamCT> findBySanPhamId(@Param("sanPhamId") Long sanPhamId);

    // Eager load hinhAnhList và mauSac để tránh LazyInitializationException
    @Query("SELECT DISTINCT s FROM SanPhamCT s LEFT JOIN FETCH s.hinhAnhList LEFT JOIN FETCH s.mauSac WHERE s.sanPham.id = :sanPhamId")
    List<SanPhamCT> findBySanPhamIdWithImages(@Param("sanPhamId") Long sanPhamId);
}
