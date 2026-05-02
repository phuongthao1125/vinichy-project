package com.vinichy.shop.controllers;

import com.vinichy.shop.models.TaiKhoan;
import com.vinichy.shop.models.ThongTinGiaoHang;
import com.vinichy.shop.repositories.ThongTinGiaoHangRepository;
import com.vinichy.shop.repositories.TaiKhoanRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private TaiKhoanRepository taiKhoanRepo;
    @Autowired private ThongTinGiaoHangRepository thongTinGiaoHangRepo;
    @Autowired private JavaMailSender mailSender;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body, HttpSession session) {
        String email = body.get("email");
        String matKhau = body.get("matKhau");

        if (email == null || matKhau == null) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Vui lòng nhập đầy đủ thông tin"));
        }

        Optional<TaiKhoan> tkOpt = taiKhoanRepo.findByTenDangNhap(email.trim());
        if (tkOpt.isEmpty()) {
            return ResponseEntity.status(401).body(Map.of("success", false, "message", "Tên đăng nhập hoặc mật khẩu không hợp lệ. Vui lòng nhập lại"));
        }

        TaiKhoan tk = tkOpt.get();
        if (!tk.getMatKhau().equals(matKhau)) {
            return ResponseEntity.status(401).body(Map.of("success", false, "message", "Tên đăng nhập hoặc mật khẩu không hợp lệ. Vui lòng nhập lại"));
        }

        email = tk.getTenDangNhap();
        String hoTen = email.contains("@") ? email.split("@")[0] : email;

        session.setAttribute("taiKhoanId", tk.getId());
        session.setAttribute("email", email);
        session.setAttribute("hoTen", hoTen);

        return ResponseEntity.ok(Map.of(
            "success", true,
            "hoTen", hoTen,
            "email", tk.getTenDangNhap()
        ));
    }

    @PostMapping("/register/send-otp")
    public ResponseEntity<?> sendRegisterOtp(@RequestBody Map<String, String> body, HttpSession session) {
        String email = body.get("email");
        if (email == null || email.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Vui lòng nhập email"));
        }
        if (!email.trim().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Email không đúng định dạng!"));
        }

        if (taiKhoanRepo.existsByTenDangNhap(email.trim())) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Email này đã có tài khoản rồi!"));
        }

        // Tạo mã OTP ngẫu nhiên 6 số
        String otp = String.format("%06d", new java.util.Random().nextInt(999999));
        
        // Lưu OTP vào session để xác minh khi bấm Đăng ký
        session.setAttribute("REG_OTP_" + email.trim(), otp);
        // Thiết lập hết hạn sau 60 giây
        session.setAttribute("REG_OTP_EXP_" + email.trim(), System.currentTimeMillis() + 60000);

        try {
            org.springframework.mail.SimpleMailMessage message = new org.springframework.mail.SimpleMailMessage();
            message.setTo(email.trim());
            message.setSubject("Mã OTP Xác Thực Đăng Ký - VINICHY");
            message.setText("Xin chào,\n\n" +
                            "Bạn đang thực hiện đăng ký tài khoản tại VINICHY.\n" +
                            "Mã OTP xác thực của bạn là: " + otp + "\n\n" +
                            "Vui lòng nhập mã này để hoàn tất quá trình đăng ký. Mã này sẽ hết hạn khi bạn tắt trình duyệt.\n\n" +
                            "Trân trọng,\nĐội ngũ VINICHY.");
            
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("success", false, "message", "Không thể gửi email lúc này. Vui lòng thử lại sau!"));
        }

        return ResponseEntity.ok(Map.of("success", true, "message", "Mã OTP đã được gửi"));
    }

    @PostMapping("/register/verify-otp")
    public ResponseEntity<?> verifyRegisterOtp(@RequestBody Map<String, String> body, HttpSession session) {
        String email = body.get("email");
        String otp = body.get("otp");

        if (email == null || otp == null || email.trim().isEmpty() || otp.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Vui lòng nhập đầy đủ email và mã OTP"));
        }

        String savedOtp = (String) session.getAttribute("REG_OTP_" + email.trim());
        Long expiry = (Long) session.getAttribute("REG_OTP_EXP_" + email.trim());

        if (savedOtp != null && savedOtp.equals(otp.trim())) {
            if (expiry != null && System.currentTimeMillis() <= expiry) {
                return ResponseEntity.ok(Map.of("success", true));
            } else {
                return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Mã OTP đã hết hạn. Vui lòng gửi lại mã!"));
            }
        }
        
        return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Mã OTP không chính xác"));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> body, HttpSession session) {
        String email = body.get("email");
        String matKhau = body.get("matKhau");
        String otp = body.get("otp");
        String hoTen = body.get("hoTen");

        if (email == null || matKhau == null || otp == null || email.trim().isEmpty() || matKhau.trim().isEmpty() || otp.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Vui lòng nhập đầy đủ thông tin và mã OTP"));
        }

        // Xác minh OTP
        String savedOtp = (String) session.getAttribute("REG_OTP_" + email.trim());
        Long expiry = (Long) session.getAttribute("REG_OTP_EXP_" + email.trim());

        if (savedOtp == null || !savedOtp.equals(otp.trim())) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Mã OTP không chính xác"));
        }
        if (expiry == null || System.currentTimeMillis() > expiry) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Mã OTP đã hết hạn"));
        }

        if (taiKhoanRepo.existsByTenDangNhap(email.trim())) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Email đã được sử dụng"));
        }

        if (matKhau.length() < 6) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Mật khẩu phải có ít nhất 6 ký tự"));
        }

        TaiKhoan tk = new TaiKhoan();
        tk.setTenDangNhap(email.trim());
        tk.setMatKhau(matKhau);
        taiKhoanRepo.save(tk);

        // Xóa OTP khỏi session
        session.removeAttribute("REG_OTP_" + email.trim());

        String userEmail = tk.getTenDangNhap();
        String userHoTen = userEmail.contains("@") ? userEmail.split("@")[0] : userEmail;

        session.setAttribute("taiKhoanId", tk.getId());
        session.setAttribute("email", userEmail);
        session.setAttribute("hoTen", userHoTen);

        return ResponseEntity.ok(Map.of(
            "success", true,
            "hoTen", (hoTen != null && !hoTen.trim().isEmpty()) ? hoTen.trim() : userHoTen,
            "email", tk.getTenDangNhap()
        ));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(Map.of("success", true));
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(HttpSession session) {
        Object taiKhoanId = session.getAttribute("taiKhoanId");
        if (taiKhoanId == null) {
            return ResponseEntity.status(401).body(Map.of("loggedIn", false));
        }
        return ResponseEntity.ok(Map.of(
            "loggedIn", true,
            "hoTen", session.getAttribute("hoTen"),
            "email", session.getAttribute("email")
        ));
    }

// ==========================================
    // CÁC API KHÔI PHỤC MẬT KHẨU
    // ==========================================

    @PostMapping("/forgot-password/send-otp")
    public ResponseEntity<?> sendOtp(@RequestBody Map<String, String> body, HttpSession session) {
        String email = body.get("email");
        if (email == null || email.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Vui lòng nhập email"));
        }
        if (!email.trim().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Email không đúng định dạng!"));
        }

        Optional<TaiKhoan> tkOpt = taiKhoanRepo.findByTenDangNhap(email.trim());
        if (tkOpt.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("success", false, "message", "Email không tồn tại trong hệ thống"));
        }

        // Tạo mã OTP ngẫu nhiên 6 số
        String otp = String.format("%06d", new java.util.Random().nextInt(999999));
        
        // Lưu OTP vào session để lát xác minh (thời gian sống phụ thuộc vào session)
        session.setAttribute("RESET_OTP_" + email.trim(), otp);
        // Thiết lập hết hạn sau 60 giây
        session.setAttribute("RESET_OTP_EXP_" + email.trim(), System.currentTimeMillis() + 60000);

        // ==========================================
        // THỰC HIỆN GỬI EMAIL TỰ ĐỘNG QUA GMAIL
        // ==========================================
        try {
            org.springframework.mail.SimpleMailMessage message = new org.springframework.mail.SimpleMailMessage();
            message.setTo(email.trim()); // Gửi tới email khách hàng
            message.setSubject("Mã OTP Khôi Phục Mật Khẩu - VINICHY"); // Tiêu đề
            
            // Nội dung email
            message.setText("Xin chào,\n\n" +
                            "Bạn vừa yêu cầu khôi phục mật khẩu cho tài khoản VINICHY.\n" +
                            "Mã OTP của bạn là: " + otp + "\n\n" +
                            "Vui lòng không chia sẻ mã này cho bất kỳ ai. Mã này sẽ hết hạn khi bạn tắt trình duyệt.\n\n" +
                            "Trân trọng,\nĐội ngũ VINICHY.");
            
            mailSender.send(message); // Ra lệnh gửi email
            
        } catch (Exception e) {
            // Nếu gửi lỗi (sai mật khẩu ứng dụng, rớt mạng...), in ra lỗi và báo cho web biết
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("success", false, "message", "Hệ thống đang bận, không thể gửi email lúc này. Vui lòng kiểm tra lại cấu hình Gmail!"));
        }

        return ResponseEntity.ok(Map.of("success", true, "message", "Mã OTP đã được gửi đến email của bạn"));
    }

    @PostMapping("/forgot-password/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody Map<String, String> body, HttpSession session) {
        String email = body.get("email");
        String otp = body.get("otp");

        String savedOtp = (String) session.getAttribute("RESET_OTP_" + email.trim());
        Long expiry = (Long) session.getAttribute("RESET_OTP_EXP_" + email.trim());

        if (savedOtp != null && savedOtp.equals(otp)) {
            if (expiry != null && System.currentTimeMillis() <= expiry) {
                return ResponseEntity.ok(Map.of("success", true));
            } else {
                return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Mã OTP đã hết hạn. Vui lòng gửi lại!"));
            }
        }
        
        return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Mã OTP không chính xác"));
    }

    @PostMapping("/forgot-password/reset")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> body, HttpSession session) {
        String email = body.get("email");
        String newPassword = body.get("newPassword");

        if (newPassword == null || newPassword.length() < 6) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Mật khẩu mới phải có ít nhất 6 ký tự"));
        }

        Optional<TaiKhoan> tkOpt = taiKhoanRepo.findByTenDangNhap(email.trim());
        if (tkOpt.isPresent()) {
            TaiKhoan tk = tkOpt.get();
            tk.setMatKhau(newPassword); // Đặt lại mật khẩu mới
            taiKhoanRepo.save(tk);
            
            // Xóa OTP khỏi session sau khi đổi thành công
            session.removeAttribute("RESET_OTP_" + email.trim());

            return ResponseEntity.ok(Map.of("success", true, "message", "Cập nhật mật khẩu thành công"));
        }

        return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Đã xảy ra lỗi, vui lòng thử lại"));
    }
}
