package com.vinichy.shop.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "loai_sp")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoaiSP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // ← đổi từ maLoai thành id

    @Column(name = "ten_loai", nullable = false)
    private String tenLoai;

    @Column(name = "slug", unique = true)
    private String slug;

    @OneToMany(mappedBy = "loaiSP")
    private List<SanPham> danhSachSanPham;
}