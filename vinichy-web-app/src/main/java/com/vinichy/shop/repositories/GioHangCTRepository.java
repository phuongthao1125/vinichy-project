package com.vinichy.shop.repositories;

import com.vinichy.shop.models.GioHangCT;
import com.vinichy.shop.models.GioHangCTId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface GioHangCTRepository extends JpaRepository<GioHangCT, GioHangCTId> {

    // Eager load sanPhamCT → sanPham → hinhAnhList để tránh LazyInitializationException
    @Query("SELECT g FROM GioHangCT g " +
           "JOIN FETCH g.sanPhamCT spct " +
           "JOIN FETCH spct.sanPham sp " +
           "LEFT JOIN FETCH spct.hinhAnhList " +
           "LEFT JOIN FETCH sp.sanPhamCTList allCT " +
           "LEFT JOIN FETCH allCT.mauSac " +
           "WHERE g.id.gioHangId = :gioHangId")
    List<GioHangCT> findByGioHangId(@Param("gioHangId") Long gioHangId);

    @Query("SELECT g FROM GioHangCT g WHERE g.id.gioHangId = :gioHangId AND g.id.sanPhamCTId = :sanPhamCTId")
    Optional<GioHangCT> findByGioHangIdAndSanPhamCTId(@Param("gioHangId") Long gioHangId, @Param("sanPhamCTId") Long sanPhamCTId);
}
