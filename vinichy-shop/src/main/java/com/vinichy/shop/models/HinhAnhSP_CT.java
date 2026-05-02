package com.vinichy.shop.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hinh_anh_sp_ct", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"link_hinh_anh", "sp_ct_id"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HinhAnhSP_CT {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Đã sửa maHinhAnh thành id

    @Column(name = "link_hinh_anh")
    private String linkHinhAnh;

    @ManyToOne
    @JoinColumn(name = "sp_ct_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private SanPhamCT sanPhamCT;
}
