package com.vinichy.shop.controllers;

import com.vinichy.shop.models.SanPham;
import com.vinichy.shop.repositories.LoaiSPRepository;
import com.vinichy.shop.repositories.SanPhamRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;
import com.vinichy.shop.utils.StringUtil;

@Controller
public class HomeController {

    @Autowired
    private SanPhamRepository sanPhamRepo;

    @Autowired
    private LoaiSPRepository loaiSPRepo;

    @GetMapping("/")
    @Transactional
    public String home(Model model, HttpSession session) {
        List<SanPham> sanPhams = sanPhamRepo.findAllWithImages();
        
        // Xác định threshold cho sản phẩm mới (5 sản phẩm có ID cao nhất)
        long maxId = sanPhams.stream().mapToLong(SanPham::getId).max().orElse(0L);
        model.addAttribute("moiThreshold", maxId - 4);

        sanPhams.sort((a, b) -> {
            // 1. Hết hàng thì xuống cuối
            if (a.isHetHang() != b.isHetHang()) return a.isHetHang() ? 1 : -1;
            // 2. Nổi bật lên trên
            if (a.getNoiBat() != b.getNoiBat()) return b.getNoiBat() ? 1 : -1;
            // 3. Mới nhất lên trên
            return b.getId().compareTo(a.getId());
        });

        if (sanPhams.size() > 12) {
            sanPhams = sanPhams.subList(0, 12);
        }

        model.addAttribute("sanPhams", sanPhams);
        model.addAttribute("loaiSPs", loaiSPRepo.findAll());
        model.addAttribute("activePage", "trang_chu");
        addSessionInfo(model, session);
        return "index";
    }

    @GetMapping("/san-pham")
    @Transactional
    public String danhSachSP(@RequestParam(required = false) String loai,
                             @RequestParam(required = false) String q,
                             Model model, HttpSession session) {

        List<SanPham> sanPhams;
        String tieuDe;

        if (q != null && !q.trim().isEmpty()) {
            String query = q.trim();
            sanPhams = sanPhamRepo.findAllWithImages().stream()
                    .filter(sp -> StringUtil.fuzzyMatch(sp.getTenSP(), query))
                    .collect(Collectors.toList());
            tieuDe = "Kết quả tìm kiếm: \"" + q + "\"";
        } else if ("noi_bat".equals(loai)) {
            sanPhams = sanPhamRepo.findByNoiBatTrue();
            tieuDe = "Sản phẩm nổi bật";
        } else if (loai != null && !loai.equals("tat_ca")) {
            sanPhams = sanPhamRepo.findByLoaiSPSlug(loai);
            tieuDe = loaiSPRepo.findBySlug(loai)
                    .map(l -> l.getTenLoai())
                    .orElse("Danh mục sản phẩm");
        } else {
            sanPhams = sanPhamRepo.findAllWithImages();
            tieuDe = "Tất cả sản phẩm";
        }

        // Luôn tính threshold dựa trên toàn bộ SP để đồng bộ nhãn
        List<SanPham> allSP = sanPhamRepo.findAll();
        long maxId = allSP.stream().mapToLong(SanPham::getId).max().orElse(0L);
        model.addAttribute("moiThreshold", maxId - 4);

        sanPhams.sort((a, b) -> {
            // 1. Hết hàng thì xuống cuối
            if (a.isHetHang() != b.isHetHang()) return a.isHetHang() ? 1 : -1;
            // 2. Nổi bật lên trên
            if (a.getNoiBat() != b.getNoiBat()) return b.getNoiBat() ? 1 : -1;
            // 3. Mới nhất lên trên
            return b.getId().compareTo(a.getId());
        });

        model.addAttribute("sanPhams", sanPhams);
        model.addAttribute("loaiSPs", loaiSPRepo.findAll());
        model.addAttribute("tieuDe", tieuDe);
        model.addAttribute("currentLoai", loai);
        model.addAttribute("q", q);
        model.addAttribute("activePage", "san_pham");
        addSessionInfo(model, session);
        return "product-list";
    }

    private void addSessionInfo(Model model, HttpSession session) {
        Object taiKhoanId = session.getAttribute("taiKhoanId");
        Object hoTen = session.getAttribute("hoTen");
        model.addAttribute("loggedIn", taiKhoanId != null);
        model.addAttribute("hoTen", hoTen);
    }
}