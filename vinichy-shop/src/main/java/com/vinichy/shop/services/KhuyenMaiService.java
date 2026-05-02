package com.vinichy.shop.services;

import com.vinichy.shop.models.KhuyenMai;
import com.vinichy.shop.repositories.DonMuaRepository;
import com.vinichy.shop.repositories.KhuyenMaiRepository;
import com.vinichy.shop.repositories.SuDungKhuyenMaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class KhuyenMaiService {

    @Autowired
    private KhuyenMaiRepository khuyenMaiRepository;

    @Autowired
    private SuDungKhuyenMaiRepository suDungKhuyenMaiRepository;

    /**
     * Kiểm tra mã khuyến mãi có hợp lệ cho tài khoản và đơn hàng này không.
     */
    public String checkValidity(String code, Long taiKhoanId, BigDecimal tongTien) {
        Optional<KhuyenMai> kmOpt = khuyenMaiRepository.findById(code);

        if (kmOpt.isEmpty()) {
            return "Mã khuyến mãi không tồn tại.";
        }

        KhuyenMai km = kmOpt.get();

        // Kiểm tra trạng thái
        if (km.getTrangThai() == null || km.getTrangThai() == 0) {
            return "Mã khuyến mãi đã bị vô hiệu hóa.";
        }

        // Kiểm tra thời gian
        LocalDateTime now = LocalDateTime.now();
        if (km.getNgayBatDau() != null && now.isBefore(km.getNgayBatDau())) {
            return "Chương trình khuyến mãi chưa bắt đầu.";
        }
        if (km.getNgayKetThuc() != null && now.isAfter(km.getNgayKetThuc())) {
            return "Mã khuyến mãi đã hết hạn.";
        }

        // Kiểm tra đơn tối thiểu
        if (km.getDonToiThieu() != null && tongTien.compareTo(km.getDonToiThieu()) < 0) {
            return "Đơn hàng chưa đạt giá trị tối thiểu " + km.getDonToiThieu() + "đ để áp dụng mã.";
        }

        // Kiểm tra giới hạn: 1 tài khoản dùng 1 lần (sử dụng bảng mới)
        if (suDungKhuyenMaiRepository.findByTaiKhoan_IdAndKhuyenMai_MaGiamGia(taiKhoanId, code).isPresent()) {
            return "Bạn đã sử dụng mã khuyến mãi này cho một đơn hàng trước đó.";
        }

        // Kiểm tra giới hạn số lượt sử dụng tổng thể (sử dụng bảng mới)
        if (km.getSoLuotSD() != null) {
            long currentUsage = suDungKhuyenMaiRepository.countByKhuyenMai_MaGiamGia(code);
            if (currentUsage >= km.getSoLuotSD()) {
                return "Mã khuyến mãi đã hết lượt sử dụng.";
            }
        }

        return "VALID";
    }
}
