package com.vinichy.shop.models;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "don_mua_ct")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonMuaCT {
    @EmbeddedId
    private DonMuaCTId id;

    @ManyToOne
    @MapsId("donMuaId")
    @JoinColumn(name = "don_mua_id")
    private DonMua donMua;

    @ManyToOne
    @MapsId("sanPhamCTId")
    @JoinColumn(name = "sp_ct_id")
    private SanPhamCT sanPhamCT;

    @Column(name = "so_luong_mua")
    private Integer soLuongMua;

    @Column(name = "thanh_tien")
    private BigDecimal thanhTien;
}
