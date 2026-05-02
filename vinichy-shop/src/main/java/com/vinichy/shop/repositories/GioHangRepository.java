package com.vinichy.shop.repositories;

import com.vinichy.shop.models.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GioHangRepository extends JpaRepository<GioHang, Long> {
    @Query("SELECT g FROM GioHang g WHERE g.taiKhoan.id = :taiKhoanId")
    Optional<GioHang> findByTaiKhoanId(@Param("taiKhoanId") Long taiKhoanId);
}
