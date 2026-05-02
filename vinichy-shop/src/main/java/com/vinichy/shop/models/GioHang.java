package com.vinichy.shop.models;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "gio_hang")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GioHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tong_tien_tam_tinh")
    private BigDecimal tongTienTamTinh;

    @OneToOne
    @JoinColumn(name = "tk_id", unique = true)
    private TaiKhoan taiKhoan;
}
