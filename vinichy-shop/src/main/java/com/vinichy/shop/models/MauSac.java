package com.vinichy.shop.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "mau_sac")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MauSac {
    @Id
    @Column(name = "id")
    private String id;  // đổi từ Long thành String, ví dụ "#000000"

    @Column(name = "ten_mau")
    private String tenMau;

    @OneToMany(mappedBy = "mauSac")
    private List<SanPhamCT> sanPhamCTList;
}
