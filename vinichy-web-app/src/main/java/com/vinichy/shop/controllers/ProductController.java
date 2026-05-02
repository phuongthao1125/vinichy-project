package com.vinichy.shop.controllers;

import com.vinichy.shop.models.SanPham;
import com.vinichy.shop.models.SanPhamCT;
import com.vinichy.shop.repositories.SanPhamCTRepository;
import com.vinichy.shop.repositories.SanPhamRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    @Autowired
    private SanPhamRepository sanPhamRepo;

    @Autowired
    private SanPhamCTRepository sanPhamCTRepo;

    @GetMapping("/san-pham/{id}")
    @Transactional
    public String chiTietSP(@PathVariable("id") Long id, Model model, HttpSession session) {
        // 1. Lấy thông tin sản phẩm đang xem
        Optional<SanPham> spOpt = sanPhamRepo.findById(id);
        if (spOpt.isEmpty()) {
            return "redirect:/"; // Nếu không tìm thấy SP thì về trang chủ
        }
        SanPham sp = spOpt.get();

        // 2. Lấy chi tiết các biến thể (Màu sắc, số lượng, hình ảnh)
        List<SanPhamCT> chiTiets = sanPhamCTRepo.findBySanPhamIdWithImages(id);

        // 3. Lấy sản phẩm liên quan & Gợi ý (Sắp xếp: Cùng danh mục trước, Nổi bật trước)
        List<SanPham> allProducts = sanPhamRepo.findAllWithImages();
        Long currentCatId = (sp.getLoaiSP() != null) ? sp.getLoaiSP().getId() : -1L;
        
        List<SanPham> lienQuan = allProducts.stream()
                .filter(p -> !p.getId().equals(id))
                .sorted((a, b) -> {
                    // 0. Hết hàng thì xuống cuối
                    if (a.isHetHang() != b.isHetHang()) return a.isHetHang() ? 1 : -1;

                    // 1. Ưu tiên cùng danh mục
                    boolean aSameCat = a.getLoaiSP() != null && a.getLoaiSP().getId().equals(currentCatId);
                    boolean bSameCat = b.getLoaiSP() != null && b.getLoaiSP().getId().equals(currentCatId);
                    if (aSameCat != bSameCat) return aSameCat ? -1 : 1;
                    
                    // 2. Ưu tiên nổi bật
                    if (a.getNoiBat() != b.getNoiBat()) return a.getNoiBat() ? -1 : 1;
                    
                    // 3. Mới nhất
                    return b.getId().compareTo(a.getId());
                })
                .limit(15)
                .collect(Collectors.toList());
        model.addAttribute("sanPhamLienQuan", lienQuan);

        // Tính threshold sản phẩm mới (Top 5)
        long maxId = sanPhamRepo.findAll().stream().mapToLong(SanPham::getId).max().orElse(0L);
        model.addAttribute("moiThreshold", maxId - 4);

        // 4. Đẩy các dữ liệu lên giao diện
        model.addAttribute("sanPham", sp);
        model.addAttribute("chiTiets", chiTiets);
        model.addAttribute("activePage", "san_pham");

        // 5. Xử lý trạng thái đăng nhập
        Object taiKhoanId = session.getAttribute("taiKhoanId");
        model.addAttribute("loggedIn", taiKhoanId != null);
        model.addAttribute("hoTen", session.getAttribute("hoTen"));

        return "detail";
    }
}