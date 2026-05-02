package com.vinichy.shop.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "san_pham_ct", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"sp_id", "mau_id"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SanPhamCT {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Đã sửa lại thành id

    @ManyToOne
    @JoinColumn(name = "sp_id")
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "mau_id")
    private MauSac mauSac;

    @Column(name = "so_luong_ton")
    private Integer soLuongTon;

    @Column(name = "sku")
    private String sku;

    @OneToMany(mappedBy = "sanPhamCT", fetch = FetchType.LAZY)
    @OrderBy("id ASC")
    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<HinhAnhSP_CT> hinhAnhList = new LinkedHashSet<>();
}
