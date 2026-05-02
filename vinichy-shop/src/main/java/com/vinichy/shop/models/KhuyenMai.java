package com.vinichy.shop.models;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "khuyen_mai")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KhuyenMai {
    @Id
    @Column(name = "ma_giam_gia")
    private String maGiamGia;

    @Column(name = "ten_khuyen_mai", nullable = false)
    private String tenKhuyenMai;

    @Column(name = "gia_tri_giam", nullable = false)
    private BigDecimal giaTriGiam;

    @Column(name = "ngay_bat_dau")
    private LocalDateTime ngayBatDau;

    @Column(name = "ngay_ket_thuc")
    private LocalDateTime ngayKetThuc;

    @Column(name = "trang_thai")
    private Integer trangThai; // 1: Active, 0: Inactive

    @Column(name = "don_toi_thieu")
    private BigDecimal donToiThieu;

    @Column(name = "so_luot_sd")
    private Integer soLuotSD;

    // Các phương thức được liệt kê trong sơ đồ (có thể dùng để xử lý logic nội bộ)
    public void taoKhuyenMai() {
        // Logic khởi tạo nếu cần
    }

    public void xoaKhuyenMai() {
        this.trangThai = 0; // Soft delete
    }

    public void suaKhuyenMai() {
        // Logic cập nhật
    }

    public void capnhatDonMua() {
        // Logic cập nhật liên quan đến đơn mua nếu cần
    }
}
