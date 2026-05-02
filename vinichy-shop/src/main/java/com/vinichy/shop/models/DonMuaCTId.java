package com.vinichy.shop.models;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonMuaCTId implements Serializable {
    private Long donMuaId; // Đã đổi tên thành donMuaId
    private Long sanPhamCTId; // Đã đổi tên thành sanPhamCTId
}
