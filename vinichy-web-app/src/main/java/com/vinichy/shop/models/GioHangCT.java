package com.vinichy.shop.models;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "gio_hang_ct")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GioHangCT {

    @EmbeddedId
    private GioHangCTId id;

    @ManyToOne
    @MapsId("gioHangId")
    @JoinColumn(name = "gio_hang_id")
    private GioHang gioHang;

    @ManyToOne
    @MapsId("sanPhamCTId")
    @JoinColumn(name = "sp_ct_id")
    private SanPhamCT sanPhamCT;

    @Column(name = "so_luong_du_dinh")
    private Integer soLuongDuDinh;

    @Column(name = "thanh_tien")
    private BigDecimal thanhTien;
}
