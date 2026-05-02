package com.vinichy.shop.services;

import com.vinichy.shop.models.TaiKhoan;
import com.vinichy.shop.models.ThongTinGiaoHang;
import com.vinichy.shop.repositories.ThongTinGiaoHangRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ThongTinGiaoHangService {

    private final ThongTinGiaoHangRepository thongTinGiaoHangRepository;

    @Transactional
    public Long saveDeliveryInfo(ThongTinGiaoHang newDeliveryInfo, Long maTK) {

        // 1. Lấy danh sách địa chỉ giao hàng hiện có của user
        List<ThongTinGiaoHang> existingInfos = thongTinGiaoHangRepository.findByTaiKhoan_Id(maTK);

        // Đảm bảo thông tin giao hàng mới được gán với tài khoản người dùng
        if (newDeliveryInfo.getTaiKhoan() == null) {
            TaiKhoan taiKhoan = new TaiKhoan(); // Tạo đối tượng TaiKhoan tạm thời
            taiKhoan.setId(maTK);
            newDeliveryInfo.setTaiKhoan(taiKhoan);
        }

        // 2. Xử lý logic thiết lập mặc định (macDinh)
        if (existingInfos.isEmpty()) {
            // Kiểm tra lần đầu: Nếu chưa có bất kỳ địa chỉ nào, bắt buộc set macDinh = true
            newDeliveryInfo.setMacDinh(true); // setMacDinh() là đúng
        } else {
            // Lần mua thứ 2 trở đi
            if (Boolean.TRUE.equals(newDeliveryInfo.getMacDinh())) {
                // Người dùng chủ động chọn 'Đặt làm mặc định' -> Tìm địa chỉ cũ đang mặc định và chuyển thành false
                Optional<ThongTinGiaoHang> oldDefault = thongTinGiaoHangRepository.findByTaiKhoan_IdAndMacDinhTrue(maTK);
                if (oldDefault.isPresent()) {
                    ThongTinGiaoHang oldAddress = oldDefault.get();
                    oldAddress.setMacDinh(false); // setMacDinh() là đúng
                    thongTinGiaoHangRepository.save(oldAddress); // Cập nhật lại địa chỉ cũ thành false
                }
                // Địa chỉ mới này sẽ giữ nguyên giá trị macDinh = true như người dùng truyền vào
            } else {
                // Mặc định hệ thống set macDinh = false nếu người dùng nhập mới và không tick chọn
                newDeliveryInfo.setMacDinh(false); // setMacDinh() là đúng
            }
        }

        // Lưu thông tin giao hàng mới
        ThongTinGiaoHang savedInfo = thongTinGiaoHangRepository.save(newDeliveryInfo);

        // Cập nhật đơn hàng: Sau khi lưu địa chỉ, trả về maTTGH để controller/service khác gọi và thêm vào bảng DonMua
        return savedInfo.getId();
    }
}
