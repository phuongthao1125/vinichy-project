package com.vinichy.shop.models;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GioHangCTId implements Serializable {
    private Long gioHangId; // Đã đổi tên thành gioHangId
    private Long sanPhamCTId; // Đã đổi tên thành sanPhamCTId
}
