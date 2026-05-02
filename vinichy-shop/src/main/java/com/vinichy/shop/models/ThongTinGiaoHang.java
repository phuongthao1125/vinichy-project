package com.vinichy.shop.models;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "thong_tin_giao_hang")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ThongTinGiaoHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten_kh")
    private String tenKH;

    @Column(name = "sdt_kh")
    private String sdtKH;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "mac_dinh")
    private Boolean macDinh;

    @Column(name = "trang_thai")
    @Builder.Default
    private String trangThai = "Đang dùng";

    @ManyToOne(fetch = FetchType.LAZY) // fetch = FetchType.LAZY là tốt cho hiệu năng
    @JoinColumn(name = "tk_id", nullable = false) // ma_tk là khóa ngoại, không được null
    @JsonIgnore // Tránh lỗi đệ quy khi serialize JSON
    @ToString.Exclude // Tránh lỗi đệ quy khi gọi toString()
    private TaiKhoan taiKhoan; // Quan hệ ManyToOne với TaiKhoan
}
