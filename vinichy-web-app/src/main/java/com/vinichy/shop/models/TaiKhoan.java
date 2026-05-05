package com.vinichy.shop.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "tai_khoan")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaiKhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten_dang_nhap", nullable = false, unique = true)
    private String tenDangNhap;

    @Column(name = "mat_khau", nullable = false)
    private String matKhau;

    @Column(name = "vai_tro")
    private String vaiTro; // "ADMIN" hoặc "USER"

    @OneToMany(mappedBy = "taiKhoan")
    private List<ThongTinGiaoHang> danhSachThongTinGiaoHang;
}
