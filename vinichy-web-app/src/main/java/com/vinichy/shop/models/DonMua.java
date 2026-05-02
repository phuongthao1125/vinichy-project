package com.vinichy.shop.models;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "don_mua")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonMua {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Đã sửa lại thành id

    @Column(name = "ngay_tao_don")
    private LocalDateTime ngayTaoDon;

    @ManyToOne
    @JoinColumn(name = "ma_giam_gia")
    private KhuyenMai khuyenMai;

    @Column(name = "ty_le_chiet_khau")
    private Double tyLeChietKhau;

    @Column(name = "tong_tien")
    private BigDecimal tongTien;

    @Column(name = "tong_thanh_toan")
    private BigDecimal tongThanhToan;

    @Column(name = "trang_thai")
    private String trangThai;

    @Column(name = "ly_do_huy")
    private String lyDoHuy;

    @Column(name = "ngay_huy")
    private LocalDateTime ngayHuy;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @ManyToOne
    @JoinColumn(name = "ttgh_id")
    private ThongTinGiaoHang thongTinGiaoHang;
}
