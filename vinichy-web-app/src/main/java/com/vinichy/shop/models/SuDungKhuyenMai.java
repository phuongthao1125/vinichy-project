package com.vinichy.shop.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "su_dung_khuyen_mai")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SuDungKhuyenMai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_su_dung")
    private Long maSuDung;

    @ManyToOne
    @JoinColumn(name = "ma_tk")
    private TaiKhoan taiKhoan;

    @ManyToOne
    @JoinColumn(name = "ma_don_mua")
    private DonMua donMua;

    @ManyToOne
    @JoinColumn(name = "ma_giam_gia")
    private KhuyenMai khuyenMai;

    @Column(name = "ngay_su_dung")
    private LocalDateTime ngaySuDung;

    @Column(name = "so_luot_sd")
    private Integer soLuotSD;
}
