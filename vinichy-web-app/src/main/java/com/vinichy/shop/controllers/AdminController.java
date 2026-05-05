package com.vinichy.shop.controllers;

import com.vinichy.shop.models.*;
import com.vinichy.shop.repositories.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired private DonMuaRepository donMuaRepo;
    @Autowired private SanPhamRepository sanPhamRepo;
    @Autowired private LoaiSPRepository loaiSPRepo;
    @Autowired private SanPhamCTRepository sanPhamCTRepo;
    @Autowired private MauSacRepository mauSacRepo;
    @Autowired private KhuyenMaiRepository khuyenMaiRepo;
    @Autowired private HinhAnhSP_CTRepository hinhAnhRepo;

    private boolean isAdmin(HttpSession session) {
        String role = (String) session.getAttribute("vaiTro");
        return "ADMIN".equals(role);
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/";
        
        model.addAttribute("totalOrders", donMuaRepo.count());
        model.addAttribute("totalProducts", sanPhamRepo.count());
        model.addAttribute("recentOrders", donMuaRepo.findAll().stream().sorted((a,b) -> b.getId().compareTo(a.getId())).limit(5).toList());
        
        return "admin/dashboard";
    }

    @GetMapping("/orders")
    public String orderManagement(Model model, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/";
        
        List<DonMua> orders = donMuaRepo.findAll();
        model.addAttribute("orders", orders);
        return "admin/orders";
    }

    @PostMapping("/orders/update-status")
    @ResponseBody
    public ResponseEntity<?> updateOrderStatus(@RequestBody Map<String, Object> body, HttpSession session) {
        if (!isAdmin(session)) return ResponseEntity.status(403).build();

        try {
            Long orderId = Long.parseLong(String.valueOf(body.get("orderId")));
            String status = String.valueOf(body.get("status"));

            Optional<DonMua> orderOpt = donMuaRepo.findById(orderId);
            if (orderOpt.isPresent()) {
                DonMua order = orderOpt.get();
                order.setTrangThai(status);
                donMuaRepo.save(order);
                return ResponseEntity.ok(Map.of("success", true));
            }
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Không tìm thấy đơn hàng"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        }
    }

    @GetMapping("/products")
    public String productManagement(Model model, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/";
        
        model.addAttribute("products", sanPhamRepo.findAll());
        model.addAttribute("categories", loaiSPRepo.findAll());
        model.addAttribute("colors", mauSacRepo.findAll());
        return "admin/products";
    }

    @PostMapping("/products/add")
    @ResponseBody
    public ResponseEntity<?> addProduct(@RequestBody Map<String, Object> body, HttpSession session) {
        if (!isAdmin(session)) return ResponseEntity.status(403).build();

        try {
            String name = String.valueOf(body.get("name"));
            Long categoryId = Long.parseLong(String.valueOf(body.get("categoryId")));
            java.math.BigDecimal price = new java.math.BigDecimal(String.valueOf(body.get("price")));
            String description = body.get("description") != null ? String.valueOf(body.get("description")) : "";
            boolean noiBat = body.get("noiBat") != null && Boolean.parseBoolean(String.valueOf(body.get("noiBat")));

            LoaiSP loai = loaiSPRepo.findById(categoryId).orElseThrow(() -> new RuntimeException("Danh mục không tồn tại"));
            
            SanPham sp = new SanPham();
            sp.setTenSP(name);
            sp.setLoaiSP(loai);
            sp.setGiaBan(price);
            sp.setMoTa(description);
            sp.setNoiBat(noiBat);
            
            sanPhamRepo.save(sp);

            // Tự động tạo biến thể (tồn kho) đầu tiên và hình ảnh
            if (body.get("colorId") != null && body.get("stock") != null) {
                String colorId = String.valueOf(body.get("colorId"));
                Integer stock = Integer.parseInt(String.valueOf(body.get("stock")));
                
                MauSac mau = mauSacRepo.findById(colorId).orElseThrow(() -> new RuntimeException("Màu sắc không tồn tại"));
                SanPhamCT ct = new SanPhamCT();
                ct.setSanPham(sp);
                ct.setMauSac(mau);
                ct.setSoLuongTon(stock);
                ct.setSku(sp.getId() + "-" + mau.getId());
                sanPhamCTRepo.save(ct);

                // Lưu hình ảnh nếu có
                if (body.get("imageUrl") != null && !String.valueOf(body.get("imageUrl")).trim().isEmpty()) {
                    HinhAnhSP_CT hinhAnh = new HinhAnhSP_CT();
                    hinhAnh.setLinkHinhAnh(String.valueOf(body.get("imageUrl")));
                    hinhAnh.setSanPhamCT(ct);
                    hinhAnhRepo.save(hinhAnh);
                }
            }
            
            return ResponseEntity.ok(Map.of("success", true, "message", "Thêm sản phẩm thành công!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Lỗi: " + e.getMessage()));
        }
    }

    @PostMapping("/products/update-stock")
    @ResponseBody
    public ResponseEntity<?> updateStock(@RequestBody Map<String, Object> body, HttpSession session) {
        if (!isAdmin(session)) return ResponseEntity.status(403).build();

        try {
            Long variantId = Long.parseLong(String.valueOf(body.get("variantId")));
            Integer quantity = Integer.parseInt(String.valueOf(body.get("quantity")));

            Optional<SanPhamCT> ctOpt = sanPhamCTRepo.findById(variantId);
            if (ctOpt.isPresent()) {
                SanPhamCT ct = ctOpt.get();
                ct.setSoLuongTon(quantity);
                sanPhamCTRepo.save(ct);
                return ResponseEntity.ok(Map.of("success", true));
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/products/add-variant")
    @ResponseBody
    public ResponseEntity<?> addVariant(@RequestBody Map<String, Object> body, HttpSession session) {
        if (!isAdmin(session)) return ResponseEntity.status(403).build();

        try {
            Long productId = Long.parseLong(String.valueOf(body.get("productId")));
            String colorId = String.valueOf(body.get("colorId"));
            Integer stock = Integer.parseInt(String.valueOf(body.get("stock")));

            SanPham sp = sanPhamRepo.findById(productId).orElseThrow();
            MauSac mau = mauSacRepo.findById(colorId).orElseThrow();

            SanPhamCT ct = new SanPhamCT();
            ct.setSanPham(sp);
            ct.setMauSac(mau);
            ct.setSoLuongTon(stock);
            ct.setSku(sp.getId() + "-" + mau.getId());
            sanPhamCTRepo.save(ct);

            // Lưu hình ảnh nếu có
            if (body.get("imageUrl") != null && !String.valueOf(body.get("imageUrl")).trim().isEmpty()) {
                HinhAnhSP_CT hinhAnh = new HinhAnhSP_CT();
                hinhAnh.setLinkHinhAnh(String.valueOf(body.get("imageUrl")));
                hinhAnh.setSanPhamCT(ct);
                hinhAnhRepo.save(hinhAnh);
            }
            
            return ResponseEntity.ok(Map.of("success", true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        }
    }

    @GetMapping("/promos")
    public String promoManagement(Model model, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/";
        model.addAttribute("promos", khuyenMaiRepo.findAll());
        return "admin/promos";
    }

    @PostMapping("/promos/add")
    @ResponseBody
    public ResponseEntity<?> addPromo(@RequestBody Map<String, Object> body, HttpSession session) {
        if (!isAdmin(session)) return ResponseEntity.status(403).build();

        try {
            KhuyenMai km = new KhuyenMai();
            km.setMaGiamGia(String.valueOf(body.get("code")).toUpperCase());
            km.setTenKhuyenMai(String.valueOf(body.get("name")));
            km.setGiaTriGiam(new java.math.BigDecimal(String.valueOf(body.get("discount"))));
            km.setDonToiThieu(new java.math.BigDecimal(String.valueOf(body.get("minOrder"))));
            km.setNgayBatDau(java.time.LocalDateTime.now());
            km.setNgayKetThuc(java.time.LocalDateTime.parse(String.valueOf(body.get("expiryDate")) + "T23:59:59"));
            km.setTrangThai(1);
            km.setSoLuotSD(0);

            khuyenMaiRepo.save(km);
            return ResponseEntity.ok(Map.of("success", true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Lỗi: " + e.getMessage()));
        }
    }

    @PostMapping("/promos/delete")
    @ResponseBody
    public ResponseEntity<?> deletePromo(@RequestBody Map<String, String> body, HttpSession session) {
        if (!isAdmin(session)) return ResponseEntity.status(403).build();
        khuyenMaiRepo.deleteById(body.get("code"));
        return ResponseEntity.ok(Map.of("success", true));
    }

    @GetMapping("/categories")
    public String categoryManagement(Model model, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/";
        model.addAttribute("categories", loaiSPRepo.findAll());
        return "admin/categories";
    }

    @PostMapping("/categories/add")
    @ResponseBody
    public ResponseEntity<?> addCategory(@RequestBody Map<String, Object> body, HttpSession session) {
        if (!isAdmin(session)) return ResponseEntity.status(403).build();
        try {
            String name = String.valueOf(body.get("name"));
            LoaiSP loai = new LoaiSP();
            loai.setTenLoai(name);
            loai.setSlug(name.toLowerCase().replaceAll(" ", "-")); // Simple slug
            loaiSPRepo.save(loai);
            return ResponseEntity.ok(Map.of("success", true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        }
    }

    @PostMapping("/categories/delete")
    @ResponseBody
    public ResponseEntity<?> deleteCategory(@RequestBody Map<String, Long> body, HttpSession session) {
        if (!isAdmin(session)) return ResponseEntity.status(403).build();
        try {
            loaiSPRepo.deleteById(body.get("id"));
            return ResponseEntity.ok(Map.of("success", true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Không thể xóa danh mục đang có sản phẩm!"));
        }
    }
}
