package com.vinichy.shop.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "san_pham")
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
public class SanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten_sp", nullable = false, length = 255)
    private String tenSP;

    @Column(name = "mo_ta", columnDefinition = "TEXT")
    private String moTa;

    @Column(name = "gia_ban")
    private BigDecimal giaBan;

    @Column(name = "noi_bat")
    private Boolean noiBat = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loai_sp_id")
    private LoaiSP loaiSP;

    @OneToMany(mappedBy = "sanPham", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference // Ngăn lỗi lặp vô hạn khi chuyển sang JSON
    private List<SanPhamCT> sanPhamCTList;

    /**
     * Lấy hình ảnh đại diện từ biến thể đầu tiên.
     * Logic: Sản phẩm -> Biến thể (Detail) đầu tiên -> Hình ảnh đầu tiên.
     */
    @Transient // Không lưu trường này xuống Database
    public String getHinhAnhDaiDien() {
        if (sanPhamCTList != null && !sanPhamCTList.isEmpty()) {
            for (SanPhamCT variant : sanPhamCTList) {
                if (variant.getHinhAnhList() != null && !variant.getHinhAnhList().isEmpty()) {
                    HinhAnhSP_CT firstImage = variant.getHinhAnhList().iterator().next();
                    String link = firstImage.getLinkHinhAnh();
                    if (link != null && !link.trim().isEmpty()) {
                        return link;
                    }
                }
            }
        }
        return "/img/default-product.png";
    }

    /**
     * Kiểm tra xem toàn bộ các phân loại của sản phẩm đã hết hàng chưa.
     */
    @Transient
    public boolean isHetHang() {
        if (sanPhamCTList == null || sanPhamCTList.isEmpty()) {
            return true;
        }
        return sanPhamCTList.stream()
                .mapToInt(ct -> ct.getSoLuongTon() != null ? ct.getSoLuongTon() : 0)
                .sum() <= 0;
    }


}