package com.vinichy.shop.repositories;

import com.vinichy.shop.models.ThongTinGiaoHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ThongTinGiaoHangRepository extends JpaRepository<ThongTinGiaoHang, Long> {
    List<ThongTinGiaoHang> findByTaiKhoan_Id(Long taiKhoanId);
    Optional<ThongTinGiaoHang> findByTaiKhoan_IdAndMacDinhTrue(Long taiKhoanId);

    @Modifying
    @Query("UPDATE ThongTinGiaoHang t SET t.macDinh = false WHERE t.taiKhoan.id = :taiKhoanId")
    void resetMacDinhByTaiKhoanId(@Param("taiKhoanId") Long taiKhoanId);

    boolean existsByTaiKhoan_Id(Long taiKhoanId);

    @Query("SELECT COUNT(t) > 0 FROM ThongTinGiaoHang t WHERE t.taiKhoan.id = :tkId AND t.tenKH = :tenKH AND t.sdtKH = :sdtKH AND t.diaChi = :diaChi AND t.trangThai = 'Đang dùng'")
    boolean existsActiveDuplicate(@Param("tkId") Long tkId, @Param("tenKH") String tenKH, @Param("sdtKH") String sdtKH, @Param("diaChi") String diaChi);
}
