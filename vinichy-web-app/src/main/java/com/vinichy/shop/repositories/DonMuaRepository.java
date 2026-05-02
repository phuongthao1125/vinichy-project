package com.vinichy.shop.repositories;

import com.vinichy.shop.models.DonMua;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DonMuaRepository extends JpaRepository<DonMua, Long> {

    // Eager load thongTinGiaoHang và taiKhoan để tránh LazyInitializationException
    @Query("SELECT d FROM DonMua d " +
           "JOIN FETCH d.thongTinGiaoHang ttgh " +
           "JOIN FETCH ttgh.taiKhoan tk " +
           "WHERE tk.id = :taiKhoanId " +
           "ORDER BY d.ngayTaoDon DESC")
    List<DonMua> findByTaiKhoanIdOrderByNgayTaoDonDesc(@Param("taiKhoanId") Long taiKhoanId);

    boolean existsByKhuyenMai_MaGiamGiaAndThongTinGiaoHang_TaiKhoan_Id(String maGiamGia, Long taiKhoanId);

    long countByKhuyenMai_MaGiamGia(String maGiamGia);
}
