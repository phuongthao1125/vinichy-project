package com.vinichy.shop.controllers;

import com.vinichy.shop.models.*;
import com.vinichy.shop.repositories.*;
import com.vinichy.shop.services.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class CartController {

    @Autowired private GioHangRepository gioHangRepo;
    @Autowired private GioHangCTRepository gioHangCTRepo;
    @Autowired private SanPhamCTRepository sanPhamCTRepo;
    @Autowired private TaiKhoanRepository taiKhoanRepo;
    @Autowired private DonMuaRepository donMuaRepo;
    @Autowired private DonMuaCTRepository donMuaCTRepo;
    @Autowired private SanPhamRepository sanPhamRepo;
    @Autowired private ThongTinGiaoHangRepository thongTinGiaoHangRepo;
    @Autowired private KhuyenMaiRepository khuyenMaiRepo;
    @Autowired private KhuyenMaiService khuyenMaiService;
    @Autowired private SuDungKhuyenMaiRepository suDungKhuyenMaiRepo;

    private void updateCartTotal(GioHang gh) {
        List<GioHangCT> items = gioHangCTRepo.findByGioHangId(gh.getId());
        java.math.BigDecimal tongTien = java.math.BigDecimal.ZERO;
        for (GioHangCT item : items) {
            java.math.BigDecimal thanhTien = item.getSanPhamCT().getSanPham().getGiaBan()
                .multiply(java.math.BigDecimal.valueOf(item.getSoLuongDuDinh()));
            item.setThanhTien(thanhTien);
            gioHangCTRepo.save(item);
            tongTien = tongTien.add(thanhTien);
        }
        gh.setTongTienTamTinh(tongTien);
        gioHangRepo.save(gh);
    }

    // ===== TRANG GIỎ HÀNG =====
    @GetMapping("/gio-hang")
    public String gioHangPage(Model model, HttpSession session) {
        Long taiKhoanId = (Long) session.getAttribute("taiKhoanId");
        if (taiKhoanId == null) {
            return "redirect:/";
        }
        Optional<GioHang> ghOpt = gioHangRepo.findByTaiKhoanId(taiKhoanId);
        List<GioHangCT> items = ghOpt.isPresent() 
            ? gioHangCTRepo.findByGioHangId(ghOpt.get().getId()) 
            : List.of();
            
        long tongTien = items.stream()
            .mapToLong(i -> i.getSanPhamCT().getSanPham().getGiaBan().multiply(java.math.BigDecimal.valueOf(i.getSoLuongDuDinh())).longValueExact())
            .sum();
            
        boolean allOutOfStock = !items.isEmpty() && items.stream()
            .allMatch(i -> i.getSoLuongDuDinh() > i.getSanPhamCT().getSoLuongTon());
            
        model.addAttribute("items", items);
        model.addAttribute("tongTien", tongTien);
        model.addAttribute("allOutOfStock", allOutOfStock);

        // Sắp xếp: Cùng danh mục trước, Nổi bật trước, Mới nhất sau
        List<SanPham> allProducts = sanPhamRepo.findAllWithImages();
        final Long currentLoaiId = (!items.isEmpty()) 
            ? items.get(0).getSanPhamCT().getSanPham().getLoaiSP().getId() 
            : -1L;

        List<SanPham> danhSachLienQuan = allProducts.stream()
            .sorted((a, b) -> {
                // Ưu tiên cùng danh mục
                boolean aSameCat = a.getLoaiSP() != null && a.getLoaiSP().getId().equals(currentLoaiId);
                boolean bSameCat = b.getLoaiSP() != null && b.getLoaiSP().getId().equals(currentLoaiId);
                if (aSameCat != bSameCat) return aSameCat ? -1 : 1;
                
                // Ưu tiên nổi bật
                if (a.getNoiBat() != b.getNoiBat()) return a.getNoiBat() ? -1 : 1;
                
                // Mới nhất
                return b.getId().compareTo(a.getId());
            })
            .limit(15)
            .collect(Collectors.toList());
        
        // Tính threshold sản phẩm mới
        long maxId = sanPhamRepo.findAll().stream().mapToLong(SanPham::getId).max().orElse(0L);
        model.addAttribute("moiThreshold", maxId - 4);
        
        model.addAttribute("sanPhamLienQuan", danhSachLienQuan);


        model.addAttribute("activePage", "gio_hang");
        model.addAttribute("loggedIn", true);
        model.addAttribute("hoTen", session.getAttribute("hoTen"));
        return "cart";
    }

    // ===== API THÊM VÀO GIỎ HÀNG =====
    @PostMapping("/api/cart/add")
    @ResponseBody
    public ResponseEntity<?> addToCart(@RequestBody Map<String, Object> body, HttpSession session) {
        Long taiKhoanId = (Long) session.getAttribute("taiKhoanId");
        if (taiKhoanId == null) {
            return ResponseEntity.status(401).body(Map.of("success", false, "message", "Vui lòng đăng nhập"));
        }

        Long sanPhamCTId = Long.valueOf(body.get("sanPhamCTId").toString());
        Integer soLuong = Integer.valueOf(body.getOrDefault("soLuong", 1).toString());
        if (soLuong < 1) soLuong = 1;

        Optional<SanPhamCT> spctOpt = sanPhamCTRepo.findById(sanPhamCTId);
        if (spctOpt.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Sản phẩm không tồn tại"));
        }

        SanPhamCT spct = spctOpt.get();
        Optional<TaiKhoan> tkOpt = taiKhoanRepo.findById(taiKhoanId);
        if (tkOpt.isEmpty()) {
            session.invalidate(); 
            return ResponseEntity.status(401).body(Map.of("success", false, "message", "Tài khoản không tồn tại. Vui lòng đăng nhập lại."));
        }
        TaiKhoan tk = tkOpt.get();

        GioHang gioHang = gioHangRepo.findByTaiKhoanId(taiKhoanId).orElseGet(() -> {
            GioHang gh = new GioHang();
            gh.setTaiKhoan(tk);
            return gioHangRepo.save(gh);
        });

        Optional<GioHangCT> existing = gioHangCTRepo.findByGioHangIdAndSanPhamCTId(gioHang.getId(), sanPhamCTId);
        if (existing.isPresent()) {
            GioHangCT ct = existing.get();
            if (ct.getSoLuongDuDinh() + soLuong > spct.getSoLuongTon()) {
                if (ct.getSoLuongDuDinh() >= spct.getSoLuongTon()) {
                    return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Số lượng sản phẩm này trong giỏ hàng đã đạt giới hạn tồn kho hiện có!"));
                }
                return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Tổng số lượng cộng dồn vượt quá tồn kho hiện tại (" + spct.getSoLuongTon() + " sản phẩm)!"));
            }
            ct.setSoLuongDuDinh(ct.getSoLuongDuDinh() + soLuong);
            gioHangCTRepo.save(ct);
        } else {
            if (soLuong > spct.getSoLuongTon()) {
                return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Số lượng vượt ngưỡng tồn kho"));
            }
            GioHangCT ct = new GioHangCT();
            ct.setId(new GioHangCTId(gioHang.getId(), spct.getId()));
            ct.setGioHang(gioHang);
            ct.setSanPhamCT(spct);
            ct.setSoLuongDuDinh(soLuong);
            gioHangCTRepo.save(ct);
        }
        
        updateCartTotal(gioHang);

        long count = gioHangCTRepo.findByGioHangId(gioHang.getId()).size();
        return ResponseEntity.ok(Map.of("success", true, "cartCount", count));
    }

    // ===== API XEM GIỎ HÀNG =====
    @GetMapping("/api/cart")
    @ResponseBody
    public ResponseEntity<?> getCart(HttpSession session) {
        Long taiKhoanId = (Long) session.getAttribute("taiKhoanId");
        if (taiKhoanId == null) {
            return ResponseEntity.status(401).body(Map.of("success", false));
        }
        Optional<GioHang> ghOpt = gioHangRepo.findByTaiKhoanId(taiKhoanId);
        if (ghOpt.isEmpty()) {
            return ResponseEntity.ok(Map.of("items", List.of(), "count", 0));
        }
        List<GioHangCT> items = gioHangCTRepo.findByGioHangId(ghOpt.get().getId());
        return ResponseEntity.ok(Map.of("count", items.size()));
    }

    // ===== TRANG ĐẶT HÀNG =====
    @GetMapping("/dat-hang")
    public String datHangPage(Model model, HttpSession session) {
        Long taiKhoanId = (Long) session.getAttribute("taiKhoanId");
        if (taiKhoanId == null) return "redirect:/?showLogin=true";

        Optional<GioHang> ghOpt = gioHangRepo.findByTaiKhoanId(taiKhoanId);
        if (ghOpt.isEmpty()) return "redirect:/gio-hang";

        List<GioHangCT> items = gioHangCTRepo.findByGioHangId(ghOpt.get().getId());
        if (items.isEmpty()) return "redirect:/gio-hang";

        long tongTien = items.stream()
            .filter(i -> i.getSoLuongDuDinh() <= i.getSanPhamCT().getSoLuongTon())
            .mapToLong(i -> i.getSanPhamCT().getSanPham().getGiaBan().multiply(java.math.BigDecimal.valueOf(i.getSoLuongDuDinh())).longValueExact())
            .sum();

        boolean hasOutOfStock = items.stream()
            .anyMatch(i -> i.getSoLuongDuDinh() > i.getSanPhamCT().getSoLuongTon());

        model.addAttribute("items", items);
        model.addAttribute("tongTien", tongTien);
        model.addAttribute("hasOutOfStock", hasOutOfStock);
        model.addAttribute("loggedIn", true);
        model.addAttribute("hoTen", session.getAttribute("hoTen"));
        return "order";
    }

    // ===== API CẬP NHẬT SỐ LƯỢNG =====
    @PostMapping("/api/cart/update-quantity")
    @ResponseBody
    public ResponseEntity<?> updateQuantity(@RequestBody Map<String, Object> body, HttpSession session) {
        Long taiKhoanId = (Long) session.getAttribute("taiKhoanId");
        if (taiKhoanId == null) return ResponseEntity.status(401).body(Map.of("success", false));

        Long sanPhamCTId = Long.valueOf(body.get("sanPhamCTId").toString());
        Integer soLuong = Integer.valueOf(body.get("soLuong").toString());

        Optional<GioHang> ghOpt = gioHangRepo.findByTaiKhoanId(taiKhoanId);
        if (ghOpt.isEmpty()) return ResponseEntity.badRequest().body(Map.of("success", false));

        GioHangCTId id = new GioHangCTId(ghOpt.get().getId(), sanPhamCTId);
        Optional<GioHangCT> ctOpt = gioHangCTRepo.findById(id);
        if (ctOpt.isPresent()) {
            GioHangCT ct = ctOpt.get();
            if (soLuong > ct.getSanPhamCT().getSoLuongTon()) {
                return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Số lượng vượt ngưỡng tồn kho"));
            }
            if (soLuong > 0) {
                ct.setSoLuongDuDinh(soLuong);
                gioHangCTRepo.save(ct);
            } else {
                gioHangCTRepo.delete(ct);
            }
            gioHangCTRepo.flush();
            updateCartTotal(ghOpt.get());
            return ResponseEntity.ok(Map.of("success", true));
        }
        return ResponseEntity.badRequest().body(Map.of("success", false));
    }

    // ===== API ĐỔI MÀU SẮC (BIẾN THỂ) =====
    @PostMapping("/api/cart/update-variant")
    @ResponseBody
    public ResponseEntity<?> updateVariant(@RequestBody Map<String, Object> body, HttpSession session) {
        Long taiKhoanId = (Long) session.getAttribute("taiKhoanId");
        if (taiKhoanId == null) return ResponseEntity.status(401).body(Map.of("success", false));

        Long sanPhamCTId = Long.valueOf(body.get("sanPhamCTId").toString());
        Long newSanPhamCTId = Long.valueOf(body.get("newSanPhamCTId").toString());

        Optional<GioHang> ghOpt = gioHangRepo.findByTaiKhoanId(taiKhoanId);
        if (ghOpt.isEmpty()) return ResponseEntity.badRequest().body(Map.of("success", false));

        GioHangCTId id = new GioHangCTId(ghOpt.get().getId(), sanPhamCTId);
        Optional<GioHangCT> ctOpt = gioHangCTRepo.findById(id);
        Optional<SanPhamCT> newSpctOpt = sanPhamCTRepo.findById(newSanPhamCTId);

        if (ctOpt.isPresent() && newSpctOpt.isPresent()) {
            GioHangCT ct = ctOpt.get();
            Optional<GioHangCT> existing = gioHangCTRepo.findByGioHangIdAndSanPhamCTId(ct.getGioHang().getId(), newSanPhamCTId);
            
            if (existing.isPresent() && !existing.get().getId().equals(ct.getId())) {
                GioHangCT existCt = existing.get();
                existCt.setSoLuongDuDinh(existCt.getSoLuongDuDinh() + ct.getSoLuongDuDinh());
                gioHangCTRepo.save(existCt);
                gioHangCTRepo.delete(ct);
            } else {
                GioHangCTId newId = new GioHangCTId(ct.getGioHang().getId(), newSanPhamCTId);
                GioHangCT newCt = new GioHangCT();
                newCt.setId(newId);
                newCt.setGioHang(ct.getGioHang());
                newCt.setSanPhamCT(newSpctOpt.get());
                newCt.setSoLuongDuDinh(ct.getSoLuongDuDinh());
                gioHangCTRepo.save(newCt);
                gioHangCTRepo.delete(ct);
            }
            gioHangCTRepo.flush();
            updateCartTotal(ghOpt.get());
            return ResponseEntity.ok(Map.of("success", true));
        }
        return ResponseEntity.badRequest().body(Map.of("success", false));
    }

    // ===== API KIỂM TRA MÃ KHUYẾN MÃI =====
    @PostMapping("/api/khuyen-mai/validate")
    @ResponseBody
    public ResponseEntity<?> validatePromotion(@RequestBody Map<String, Object> body, HttpSession session) {
        Long taiKhoanId = (Long) session.getAttribute("taiKhoanId");
        if (taiKhoanId == null) return ResponseEntity.status(401).body(Map.of("success", false, "message", "Vui lòng đăng nhập"));

        String code = body.getOrDefault("code", "").toString().trim();
        java.math.BigDecimal tongTien = new java.math.BigDecimal(body.getOrDefault("tongTien", 0).toString());

        String result = khuyenMaiService.checkValidity(code, taiKhoanId, tongTien);
        if ("VALID".equals(result)) {
            KhuyenMai km = khuyenMaiRepo.findById(code).get();
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Áp dụng mã khuyến mãi thành công!",
                "giaTriGiam", km.getGiaTriGiam(),
                "maGiamGia", km.getMaGiamGia()
            ));
        } else {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", result));
        }
    }

    // ===== API LẤY DANH SÁCH MÃ KHUYẾN MÃI KHẢ DỤNG =====
    @GetMapping("/api/khuyen-mai/available")
    @ResponseBody
    public ResponseEntity<?> getAvailablePromotions(HttpSession session) {
        Long taiKhoanId = (Long) session.getAttribute("taiKhoanId");
        if (taiKhoanId == null) return ResponseEntity.status(401).body(Map.of("success", false));

        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        List<KhuyenMai> allActive = khuyenMaiRepo.findAll().stream()
            .filter(km -> km.getTrangThai() != null && km.getTrangThai() == 1)
            .filter(km -> km.getNgayBatDau() == null || now.isAfter(km.getNgayBatDau()))
            .filter(km -> km.getNgayKetThuc() == null || now.isBefore(km.getNgayKetThuc()))
            .collect(Collectors.toList());

        List<Map<String, Object>> available = allActive.stream()
            .filter(km -> suDungKhuyenMaiRepo.findByTaiKhoan_IdAndKhuyenMai_MaGiamGia(taiKhoanId, km.getMaGiamGia()).isEmpty())
            .map(km -> {
                Map<String, Object> m = new HashMap<>();
                m.put("code", km.getMaGiamGia());
                m.put("name", km.getTenKhuyenMai());
                m.put("value", km.getGiaTriGiam());
                m.put("minOrder", km.getDonToiThieu());
                m.put("endDate", km.getNgayKetThuc());
                m.put("totalLimit", km.getSoLuotSD());
                return m;
            })
            .collect(Collectors.toList());

        return ResponseEntity.ok(Map.of("success", true, "data", available));
    }

    // ===== API ĐẶT HÀNG =====
    @PostMapping("/api/dat-hang")
    @ResponseBody
    @Transactional
    public ResponseEntity<?> datHang(@RequestBody Map<String, String> body, HttpSession session) {
        Long taiKhoanId = (Long) session.getAttribute("taiKhoanId");
        if (taiKhoanId == null) {
            return ResponseEntity.status(401).body(Map.of("success", false, "message", "Vui lòng đăng nhập"));
        }

        Optional<GioHang> ghOpt = gioHangRepo.findByTaiKhoanId(taiKhoanId);
        if (ghOpt.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Giỏ hàng trống"));
        }

        List<GioHangCT> items = gioHangCTRepo.findByGioHangId(ghOpt.get().getId());
        if (items.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Giỏ hàng trống"));
        }
        
        List<Long> spctIds = items.stream().map(i -> i.getSanPhamCT().getId()).collect(Collectors.toList());
        List<SanPhamCT> lockedSpcts = sanPhamCTRepo.findByIdsWithLock(spctIds);
        Map<Long, SanPhamCT> spctMap = lockedSpcts.stream().collect(Collectors.toMap(SanPhamCT::getId, sp -> sp));

        for (GioHangCT item : items) {
            SanPhamCT spct = spctMap.get(item.getSanPhamCT().getId());
            if (spct == null || item.getSoLuongDuDinh() > spct.getSoLuongTon()) {
                String tenSP = spct != null ? spct.getSanPham().getTenSP() : "Sản phẩm";
                return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Sản phẩm [" + tenSP + "] không đủ số lượng tồn kho hoặc đã hết hàng. Vui lòng cập nhật lại giỏ hàng!"));
            }
        }

        long tongTien = items.stream()
            .mapToLong(i -> i.getSanPhamCT().getSanPham().getGiaBan().multiply(java.math.BigDecimal.valueOf(i.getSoLuongDuDinh())).longValueExact())
            .sum();

        TaiKhoan tk = taiKhoanRepo.findById(taiKhoanId).orElseThrow();

        String hoTenInput = body.getOrDefault("tenKH", "").trim();
        if (hoTenInput.isEmpty()) hoTenInput = session.getAttribute("hoTen") != null ? session.getAttribute("hoTen").toString() : tk.getTenDangNhap();
        
        final String hoTenFinal = hoTenInput;
        final String sdtXacNhan = body.getOrDefault("soDienThoai", "").trim();
        final String diaChiXacNhan = body.getOrDefault("diaChi", "").trim();

        // Kiểm tra xem địa chỉ này đã có trong danh sách của user chưa
        Optional<ThongTinGiaoHang> existingTtgh = thongTinGiaoHangRepo.findByTaiKhoan_Id(taiKhoanId).stream()
            .filter(a -> a.getTenKH().equalsIgnoreCase(hoTenFinal) 
                      && a.getSdtKH().equals(sdtXacNhan) 
                      && a.getDiaChi().equalsIgnoreCase(diaChiXacNhan)
                      && ("Đang dùng".equals(a.getTrangThai()) || "Dang dung".equals(a.getTrangThai())))
            .findFirst();

        ThongTinGiaoHang ttgh;
        if (existingTtgh.isPresent()) {
            ttgh = existingTtgh.get();
        } else {
            ttgh = new ThongTinGiaoHang();
            ttgh.setTaiKhoan(tk);
            ttgh.setTenKH(hoTenFinal);
            ttgh.setSdtKH(sdtXacNhan);
            ttgh.setDiaChi(diaChiXacNhan);
            boolean hasActiveAddress = thongTinGiaoHangRepo.findByTaiKhoan_Id(taiKhoanId).stream()
                .anyMatch(a -> "Đang dùng".equals(a.getTrangThai()) || "Dang dung".equals(a.getTrangThai()));
            ttgh.setMacDinh(!hasActiveAddress); 
            ttgh.setTrangThai("Đang dùng");
            ttgh = thongTinGiaoHangRepo.save(ttgh);
        }

        String maGiamGia = body.get("maGiamGia");
        KhuyenMai km = null;
        java.math.BigDecimal giamGia = java.math.BigDecimal.ZERO;

        if (maGiamGia != null && !maGiamGia.trim().isEmpty()) {
            String check = khuyenMaiService.checkValidity(maGiamGia, taiKhoanId, java.math.BigDecimal.valueOf(tongTien));
            if ("VALID".equals(check)) {
                km = khuyenMaiRepo.findById(maGiamGia).orElse(null);
                if (km != null) {
                    giamGia = km.getGiaTriGiam();
                }
            } else {
                return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Mã khuyến mãi không hợp lệ hoặc đã được sử dụng: " + check));
            }
        }

        DonMua don = new DonMua();
        don.setThongTinGiaoHang(ttgh);
        don.setKhuyenMai(km);
        don.setTongTien(java.math.BigDecimal.valueOf(tongTien));
        don.setTongThanhToan(java.math.BigDecimal.valueOf(tongTien).subtract(giamGia));
        don.setTrangThai("Chờ xác nhận");
        don.setNgayTaoDon(java.time.LocalDateTime.now());
        don.setGhiChu(body.getOrDefault("ghiChu", ""));
        don = donMuaRepo.save(don);

        for (GioHangCT item : items) {
            SanPhamCT spct = spctMap.get(item.getSanPhamCT().getId());
            DonMuaCT ct = new DonMuaCT();
            ct.setId(new DonMuaCTId(don.getId(), spct.getId()));
            ct.setDonMua(don);
            ct.setSanPhamCT(spct);
            ct.setSoLuongMua(item.getSoLuongDuDinh());
            ct.setThanhTien(spct.getSanPham().getGiaBan().multiply(java.math.BigDecimal.valueOf(item.getSoLuongDuDinh())));
            donMuaCTRepo.save(ct);

            // Trừ tồn kho thực tế
            spct.setSoLuongTon(spct.getSoLuongTon() - item.getSoLuongDuDinh());
            sanPhamCTRepo.save(spct);
        }

        // Ghi nhận sử dụng mã khuyến mãi vào bảng mới
        if (km != null) {
            SuDungKhuyenMai sdkm = SuDungKhuyenMai.builder()
                .taiKhoan(tk)
                .donMua(don)
                .khuyenMai(km)
                .ngaySuDung(java.time.LocalDateTime.now())
                .soLuotSD(1)
                .build();
            suDungKhuyenMaiRepo.save(sdkm);
        }

        gioHangCTRepo.deleteAll(items);
        gioHangCTRepo.flush();
        updateCartTotal(ghOpt.get());

        return ResponseEntity.ok(Map.of("success", true, "donMuaId", don.getId()));
    }

    // ===== TRANG ĐẶT HÀNG THÀNH CÔNG =====
    @GetMapping("/dat-hang-thanh-cong")
    public String datHangThanhCong(Model model, HttpSession session) {
        model.addAttribute("loggedIn", session.getAttribute("taiKhoanId") != null);
        
        // Màn hình thành công: Hiển thị sản phẩm nổi bật
        List<SanPham> danhSachNoiBat = sanPhamRepo.findByNoiBatTrue();
        if (danhSachNoiBat.isEmpty()) {
            danhSachNoiBat = sanPhamRepo.findAllWithImages();
        }
        model.addAttribute("sanPhamLienQuan", danhSachNoiBat);

        // Tính threshold sản phẩm mới (Top 5)
        long maxId = sanPhamRepo.findAll().stream().mapToLong(SanPham::getId).max().orElse(0L);
        model.addAttribute("moiThreshold", maxId - 4);

        return "order-success";
    }


    // ===== TRANG LỊCH SỬ ĐƠN HÀNG =====
    @GetMapping("/lich-su-don-hang")
    @Transactional
    public String lichSuDonHang(Model model, HttpSession session) {
        Long taiKhoanId = (Long) session.getAttribute("taiKhoanId");
        if (taiKhoanId == null) return "redirect:/?showLogin=true";

        List<DonMua> donMuas = donMuaRepo.findByTaiKhoanIdOrderByNgayTaoDonDesc(taiKhoanId);

        Map<Long, List<DonMuaCT>> mapChiTiet = new HashMap<>();
        Map<Long, Integer> mapSoLuong = new HashMap<>();
        
        for (DonMua don : donMuas) {
            List<DonMuaCT> chiTiets = donMuaCTRepo.findByDonMuaId(don.getId());
            for(DonMuaCT ct : chiTiets) {
                if(ct.getSanPhamCT() != null && ct.getSanPhamCT().getSanPham() != null) {
                    ct.getSanPhamCT().getSanPham().getTenSP();
                }
            }
            mapChiTiet.put(don.getId(), chiTiets);
            mapSoLuong.put(don.getId(), chiTiets.stream().mapToInt(DonMuaCT::getSoLuongMua).sum());
        }

        model.addAttribute("donMuas", donMuas);
        model.addAttribute("mapChiTiet", mapChiTiet);
        model.addAttribute("mapSoLuong", mapSoLuong);
        model.addAttribute("loggedIn", true);
        model.addAttribute("hoTen", session.getAttribute("hoTen"));
        return "order-history";
    }

    // ===== CHI TIẾT ĐƠN HÀNG =====
    @GetMapping("/lich-su-don-hang/{id}")
    @Transactional
    public String chiTietDonHang(@PathVariable Long id, Model model, HttpSession session) {
        Long taiKhoanId = (Long) session.getAttribute("taiKhoanId");
        if (taiKhoanId == null) return "redirect:/?showLogin=true";

        Optional<DonMua> donOpt = donMuaRepo.findById(id);
        if (donOpt.isEmpty() || !donOpt.get().getThongTinGiaoHang().getTaiKhoan().getId().equals(taiKhoanId)) {
            return "redirect:/lich-su-don-hang";
        }

        List<DonMuaCT> chiTiets = donMuaCTRepo.findByDonMuaId(id);
        model.addAttribute("donMua", donOpt.get());
        model.addAttribute("chiTiets", chiTiets);
        model.addAttribute("loggedIn", true);
        model.addAttribute("hoTen", session.getAttribute("hoTen"));
        return "order-detail";
    }

    // ===== ĐỔI ĐỊA CHỈ MẶC ĐỊNH =====
    @GetMapping("/dia-chi/{id}/mac-dinh")
    @Transactional
    public String doiDiaChiMacDinh(@PathVariable Long id, HttpSession session) {
        Long taiKhoanId = (Long) session.getAttribute("taiKhoanId");
        if (taiKhoanId == null) return "redirect:/?showLogin=true";

        Optional<ThongTinGiaoHang> addrOpt = thongTinGiaoHangRepo.findById(id);
        if (addrOpt.isPresent() && addrOpt.get().getTaiKhoan().getId().equals(taiKhoanId)) {
            thongTinGiaoHangRepo.resetMacDinhByTaiKhoanId(taiKhoanId);
            ThongTinGiaoHang address = addrOpt.get();
            address.setMacDinh(true);
            thongTinGiaoHangRepo.save(address);
        }
        return "redirect:/dat-hang";
    }

    // ===== API LAY DANH SACH DIA CHI GIAO HANG =====
    @GetMapping("/api/dia-chi")
    @ResponseBody
    public ResponseEntity<?> getDiaChiList(HttpSession session) {
        Long taiKhoanId = (Long) session.getAttribute("taiKhoanId");
        if (taiKhoanId == null)
            return ResponseEntity.status(401).body(Map.of("success", false, "message", "Chua dang nhap"));
        List<ThongTinGiaoHang> list = thongTinGiaoHangRepo.findByTaiKhoan_Id(taiKhoanId);
        List<Map<String, Object>> result = list.stream()
            .filter(a -> "Đang dùng".equals(a.getTrangThai()) || "Dang dung".equals(a.getTrangThai()))
            .map(a -> {
                Map<String, Object> m = new java.util.LinkedHashMap<>();
                m.put("id", a.getId()); m.put("name", a.getTenKH());
                m.put("phone", a.getSdtKH()); m.put("street", a.getDiaChi());
                m.put("macDinh", Boolean.TRUE.equals(a.getMacDinh()));
                return m;
            }).toList();
        return ResponseEntity.ok(Map.of("success", true, "data", result));
    }

    @PostMapping("/api/dia-chi")
    @ResponseBody
    @Transactional
    public ResponseEntity<?> themDiaChi(@RequestBody Map<String, Object> body, HttpSession session) {
        Long taiKhoanId = (Long) session.getAttribute("taiKhoanId");
        if (taiKhoanId == null) return ResponseEntity.status(401).body(Map.of("success", false, "message", "Vui lòng đăng nhập"));

        String tenKH = body.getOrDefault("name", "").toString().trim();
        String sdt = body.getOrDefault("phone", "").toString().trim();
        String diaChi = body.getOrDefault("street", "").toString().trim();
        boolean macDinh = Boolean.TRUE.equals(body.get("macDinh"));

        // Exception 4a: Thông tin nhập bị trống
        if (tenKH.isEmpty() || sdt.isEmpty() || diaChi.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Vui lòng không để trống thông tin"));
        }

        // Business Rule: SĐT 10 số, bắt đầu bằng số 0
        if (!sdt.matches("^0[0-9]{9}$")) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Số điện thoại không hợp lệ!"));
        }

        // Exception 6a: Thông tin trùng lặp
        if (thongTinGiaoHangRepo.existsActiveDuplicate(taiKhoanId, tenKH, sdt, diaChi)) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Thông tin giao hàng này đã tồn tại"));
        }

        TaiKhoan tk = taiKhoanRepo.findById(taiKhoanId).orElseThrow();
        boolean hasAddress = thongTinGiaoHangRepo.findByTaiKhoan_Id(taiKhoanId).stream().anyMatch(a -> "Đang dùng".equals(a.getTrangThai()));
        
        if (macDinh || !hasAddress) {
            thongTinGiaoHangRepo.resetMacDinhByTaiKhoanId(taiKhoanId);
        }

        try {
            ThongTinGiaoHang ttgh = ThongTinGiaoHang.builder()
                .taiKhoan(tk).tenKH(tenKH).sdtKH(sdt).diaChi(diaChi)
                .macDinh(macDinh || !hasAddress)
                .trangThai("Đang dùng").build();
            ttgh = thongTinGiaoHangRepo.save(ttgh);
            return ResponseEntity.ok(Map.of("success", true, "id", ttgh.getId(), "message", "Thêm mới thông tin giao hàng thành công!"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("success", false, "message", "Thêm mới thất bại, vui lòng thử lại sau!"));
        }
    }

    // ===== API SUA DIA CHI GIAO HANG =====
    @PutMapping("/api/dia-chi/{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity<?> suaDiaChi(@PathVariable Long id, @RequestBody Map<String, Object> body, HttpSession session) {
        Long taiKhoanId = (Long) session.getAttribute("taiKhoanId");
        if (taiKhoanId == null) return ResponseEntity.status(401).body(Map.of("success", false, "message", "Vui lòng đăng nhập"));

        Optional<ThongTinGiaoHang> opt = thongTinGiaoHangRepo.findById(id);
        if (opt.isEmpty() || !opt.get().getTaiKhoan().getId().equals(taiKhoanId)) {
            return ResponseEntity.status(403).body(Map.of("success", false, "message", "Không có quyền"));
        }

        String tenKH = body.getOrDefault("name", "").toString().trim();
        String sdt = body.getOrDefault("phone", "").toString().trim();
        String diaChi = body.getOrDefault("street", "").toString().trim();
        boolean macDinh = Boolean.TRUE.equals(body.get("macDinh"));

        if (tenKH.isEmpty() || sdt.isEmpty() || diaChi.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Vui lòng không để trống thông tin"));
        }

        if (!sdt.matches("^0[0-9]{9}$")) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Số điện thoại không hợp lệ!"));
        }

        // Exception 6a: Trùng lặp (trừ chính nó)
        ThongTinGiaoHang ttgh = opt.get();
        if (!ttgh.getTenKH().equals(tenKH) || !ttgh.getSdtKH().equals(sdt) || !ttgh.getDiaChi().equals(diaChi)) {
            if (thongTinGiaoHangRepo.existsActiveDuplicate(taiKhoanId, tenKH, sdt, diaChi)) {
                return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Thông tin giao hàng này đã tồn tại"));
            }
        }

        if (macDinh) thongTinGiaoHangRepo.resetMacDinhByTaiKhoanId(taiKhoanId);
        
        try {
            ttgh.setTenKH(tenKH);
            ttgh.setSdtKH(sdt);
            ttgh.setDiaChi(diaChi);
            if (macDinh) ttgh.setMacDinh(true);
            thongTinGiaoHangRepo.save(ttgh);
            return ResponseEntity.ok(Map.of("success", true, "message", "Cập nhật thông tin giao hàng thành công!"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("success", false, "message", "Cập nhật thất bại, vui lòng thử lại sau!"));
        }
    }

    // ===== API XOA DIA CHI GIAO HANG (soft delete) =====
    @DeleteMapping("/api/dia-chi/{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity<?> xoaDiaChi(@PathVariable Long id, HttpSession session) {
        Long taiKhoanId = (Long) session.getAttribute("taiKhoanId");
        if (taiKhoanId == null) return ResponseEntity.status(401).body(Map.of("success", false, "message", "Vui lòng đăng nhập"));

        Optional<ThongTinGiaoHang> opt = thongTinGiaoHangRepo.findById(id);
        if (opt.isEmpty() || !opt.get().getTaiKhoan().getId().equals(taiKhoanId)) {
            return ResponseEntity.status(403).body(Map.of("success", false, "message", "Không có quyền"));
        }

        ThongTinGiaoHang ttgh = opt.get();
        boolean wasMacDinh = Boolean.TRUE.equals(ttgh.getMacDinh());

        try {
            // Theo Use Case 5.3: Xóa khỏi cơ sở dữ liệu
            // Nhưng thực tế nếu có đơn hàng tham chiếu, ta nên dùng soft delete để bảo toàn dữ liệu
            // SQLite by default does not enforce foreign keys, which causes orphaned records instead of Exception.
            // Để đảm bảo data integrity cho DonMua, luôn luôn dùng soft-delete
            ttgh.setTrangThai("Ngừng dùng");
            ttgh.setMacDinh(false);
            thongTinGiaoHangRepo.save(ttgh);
        } catch (Exception e) {
            // Log error if needed
        }

        if (wasMacDinh) {
            List<ThongTinGiaoHang> remaining = thongTinGiaoHangRepo.findByTaiKhoan_Id(taiKhoanId)
                .stream().filter(a -> "Đang dùng".equals(a.getTrangThai())).toList();
            if (!remaining.isEmpty()) {
                ThongTinGiaoHang nextDefault = remaining.get(0);
                nextDefault.setMacDinh(true);
                thongTinGiaoHangRepo.save(nextDefault);
            }
        }
        return ResponseEntity.ok(Map.of("success", true, "message", "Xóa địa chỉ thành công!"));
    }

    // ===== API HUY DON HANG =====
    @PostMapping("/api/don-hang/huy/{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity<?> huyDonHang(@PathVariable Long id, HttpSession session) {
        Long taiKhoanId = (Long) session.getAttribute("taiKhoanId");
        if (taiKhoanId == null) return ResponseEntity.status(401).body(Map.of("success", false, "message", "Vui lòng đăng nhập"));

        Optional<DonMua> opt = donMuaRepo.findById(id);
        if (opt.isEmpty() || opt.get().getThongTinGiaoHang() == null || !opt.get().getThongTinGiaoHang().getTaiKhoan().getId().equals(taiKhoanId)) {
            return ResponseEntity.status(403).body(Map.of("success", false, "message", "Không có quyền"));
        }

        DonMua don = opt.get();
        if (!"Chờ xác nhận".equals(don.getTrangThai())) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Chỉ có thể hủy đơn hàng ở trạng thái Chờ xác nhận"));
        }

        don.setTrangThai("Đã hủy");
        donMuaRepo.save(don);
        return ResponseEntity.ok(Map.of("success", true, "message", "Hủy đơn hàng thành công!"));
    }
}