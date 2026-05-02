-- 1. Tài Khoản (Accounts)
INSERT OR IGNORE INTO tai_khoan (ten_dang_nhap, mat_khau) VALUES
('admin@vinichy.com', '123456'),
('phuongthao@gmail.com', '123456'),
('thaophan@gmail.com', '123456'),
('phuongthao1111@gmail.com', '123456'),
('khanhly@gmail.com', '123456'),
('anhphuong@gmail.com', '123456'),
('xuankhanh@gmail.com', '123456'),
('thuha@gmail.com', '123456');

-- 2. Loại Sản Phẩm (Categories)
INSERT OR IGNORE INTO loai_sp (id, ten_loai, slug) VALUES
(1, 'Balo', 'balo'),
(2, 'Túi Tote', 'tui_tote'),
(3, 'Túi Đeo Vai - Đeo Chéo', 'tui_deo_vai_deo_cheo'),
(4, 'Ví', 'vi');

-- DELETE FROM don_mua_ct;
-- DELETE FROM don_mua;

DELETE FROM hinh_anh_sp_ct;
DELETE FROM san_pham_ct;
DELETE FROM san_pham;

-- Mau Sac
INSERT OR IGNORE INTO mau_sac (id, ten_mau) VALUES
('#8B4513', 'Nâu'),
('#000000', 'Đen'),
('#C0C0C0', 'Bạc'),
('#FFFFFF', 'Trắng'),
('#FFC0CB', 'Hồng'),
('#E3BC9A', 'Nude'),
('#4B3621', 'Nâu cafe'),
('#A67B5B', 'Nâu tây'),
('#5E716A', 'Xanh Demin'),
('#5E716A', 'Xanh Denim'),
('#8B0000', 'Đỏ'),
('#FFFDD0', 'Kem'),
('#ADD8E6', 'Xanh Dương'),
('#008000', 'Xanh lá'),
('#FFF9C4', 'Vàng'),
('#B39EB5', 'Tím'),
('#F5F5DC', 'Be'),
('#808080', 'Xám'),
('#ADD8E6', 'Xanh'),
('#4B3621', 'Nâu Coffee'),
('#4B3621', 'Nâu Cafe'),
('#008000', 'Xanh Lá'),
('#A67B5B', 'Nâu Tây'),
('#4B3621', 'Nâu cà phê');

-- Products
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (1, 1, 'Balo Da Thật Premium', 350000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : 30x33x10cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc', 1);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (1, 1, '#8B4513', 0, 'BALO-DA-NAU');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (1, 'https://down-vn.img.susercontent.com/file/sg-11134201-824j5-mef6kht3pzpgd8.webp', 1);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (2, 1, '#000000', 0, 'BALO-DA-DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (2, 'https://down-vn.img.susercontent.com/file/sg-11134201-824gp-mef6kjvrjo5c2a.webp', 2);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (3, 'https://down-vn.img.susercontent.com/file/sg-11134201-824ji-mef6ki5uyubk73.webp', 2);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (4, 'https://down-vn.img.susercontent.com/file/sg-11134201-824ia-mef6kijnedqca0.webp', 2);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (5, 'https://down-vn.img.susercontent.com/file/sg-11134201-824gx-mef6kiy9d91ccf.webp', 2);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (2, 1, 'Balo Da size 20cm', 300000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : 20x19x8cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (3, 2, '#C0C0C0', 1, 'HA385 - BAC');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (6, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rbn0-lld2kekc2hx1a2.webp', 3);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (4, 2, '#8B4513', 100, 'HA385 - NAU');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (7, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rbnl-lld2ke9shxdh8a.webp', 4);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (5, 2, '#000000', 100, 'HA385 - DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (8, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rbnl-lld2kfkelprg88.webp', 5);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (3, 1, 'Balo Da Pu cao cấp', 250000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : 28x36x10cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (6, 3, '#FFFFFF', 100, 'HA730 - TRANG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (9, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-mcilrltcunoc4b.webp', 6);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (7, 3, '#000000', 100, 'HA730 - DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (10, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-mcilrlweeycc8e.webp', 7);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (8, 3, '#FFC0CB', 100, 'HA730 - HONG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (11, 'https://down-vn.img.susercontent.com/file/vn-11134207-820l4-mjbcc744vmkga0.webp', 8);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (4, 1, 'Balo Da size 30cm', 400000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : 30x44x18cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (9, 4, '#8B4513', 100, 'HA725 - NAU');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (12, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-mcbxosy5k4ed1f.webp', 9);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (10, 4, '#000000', 100, 'HA725 - DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (13, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-mcbxoss1t22k97.webp', 10);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (14, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-mcbxoss1rnet36.webp', 10);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (5, 1, 'Balo Hello Kitty', 325000, 'THÔNG TIN SẢN PHẨM :
MÀU SẮC: ĐEN, HỒNG, TRẮNG
CÁCH SỬ DỤNG: ĐEO BALO 2 BÊN
KÍCH THƯỚC: 30x35x13 CM
LOAI DA: DA THỜI TRANG CAO CẤP
QUAI MANG: DÀI 36-50CM, CÓ THỂ ĐIỀU CHỈNH
TRỌNG LƯỢNG: 0.5 KG
DUNG LƯỢNG: ĐỂ VỪA ĐIỆN THOẠI, GIẤY A4, IPAD, MỸ PHẨM', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (11, 5, '#FFC0CB', 100, 'HA727 - HONG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (15, 'https://down-vn.img.susercontent.com/file/vn-11134207-820l4-mez8fta6h7uz86.webp', 11);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (12, 5, '#000000', 100, 'HA727 - DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (16, 'https://down-vn.img.susercontent.com/file/vn-11134207-820l4-mez8ft8kkp3c93.webp', 12);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (13, 5, '#E3BC9A', 100, 'HA727 - NUDE');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (17, 'https://down-vn.img.susercontent.com/file/vn-11134207-820l4-mez8ftf6cpvv86.webp', 13);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (6, 1, 'Balo Da Retro', 450000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : 30x33x12cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc', 1);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (14, 6, '#4B3621', 100, 'HA704 - NAUCAFE');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (18, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rdyc-mbvuhz27i3c579.webp', 14);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (15, 6, '#A67B5B', 100, 'HA704 - NAUTAY');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (19, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rdyr-mbvuhzeyy24ue8.webp', 15);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (16, 6, '#000000', 100, 'HA704 - DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (20, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-mbvuj4jisvbi0f.webp', 16);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (7, 4, 'Ví Nữ Cầm Tay', 200000, 'Thông tin sản phẩm chi tiết
- Kích thước: 12.5 x 2 x 9.5 cm
- Chất liệu: Da PU cao cấp, dẻo, đàn hồi tốt
- Thiết kế: Ngăn tiền, ngăn ảnh, nhiều ngăn thẻ tiện lợi
- Phù hợp sử dụng hàng ngày, đi làm, đi chơi', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (17, 7, '#FFC0CB', 100, 'VI-NHO-HONG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (21, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m7a87c8hko6ubf.webp', 17);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (18, 7, '#ADD8E6', 100, 'VI-NHO-XANH');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (22, 'https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-lx9idzwl88gr2d.webp', 18);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (19, 7, '#000000', 100, 'VI-NHO-DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (23, 'https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-lx9idzwl9n175f.webp', 19);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (8, 4, 'Ví Nữ Da Pu Mini Cute', 120000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : size cm
- Ví Nữ Dễ Thương Đa Năng Đa Năng Hộp Đựng Thẻ Ví Tiền Mặt
- Chất liệu: Da PU
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc
- Đã kèm theo ảnh thật và video thật ở cuối sản phẩm', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (20, 8, '#FFC0CB', 100, 'VI-DA-HONG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (24, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rblt-m5gk8n6v7du25e.webp', 20);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (21, 8, '#000000', 100, 'VI-DA-DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (25, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rbmr-m5gk8q9ar6q273.webp', 21);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (22, 8, '#008000', 100, 'VI-DA-XANH-LA');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (26, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rbk8-m5gk8oqmxqb805.webp', 22);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (9, 4, 'Ví nữ in hình bướm ngọt ngào thời trang', 120000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : size 10.5x2x5cm
- Ví tiền mặt đa chức năng dễ thương
- Chất liệu: Da PU
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc
- Đã kèm theo ảnh thật và video thật ở cuối sản phẩm', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (23, 9, '#FFC0CB', 100, 'VI-IN-HINH-HONG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (27, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rd3y-lvycvr2r38gu53.webp', 23);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (24, 9, '#000000', 100, 'VI-IN-HINH-ĐEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (28, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rd3v-lvycvtdppu8y47.webp', 24);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (25, 9, '#FFF9C4', 100, 'VI-IN-HINH-VANG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (29, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rd3x-lvycvrrg33ib4d.webp', 25);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (10, 4, 'Ví Gấu Dễ Thương Ba Đa Năng', 160000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : size 10x2x8 cm
- Ví Ngắn Gấp Đựng Thẻ Ví Đựng Tiền Xu Nữ Túi Đựng Thẻ Kẹp Túi Hoạt Hình QZZ
- Chất liệu: da 
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc
- Đã kèm theo ảnh thật và video thật ở cuối sản phẩm', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (26, 10, '#B39EB5', 100, 'VI-GAU-TIM');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (30, 'https://down-vn.img.susercontent.com/file/sg-11134201-7reoy-m2xkjl3gqn0m5e.webp', 26);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (27, 10, '#ADD8E6', 100, 'VI-GAU-XANHDUONG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (31, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rent-m2xkjm6l5dpp43.webp', 27);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (28, 10, '#FFF9C4', 100, 'VI-GAU-VANG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (32, 'https://down-vn.img.susercontent.com/file/sg-11134201-7req7-m2xkjmug6gvv76.webp', 28);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (11, 4, 'Ví mùa hè thu mới', 160000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : size 11.5x2x8.cm
- Ví tương phản dành cho nữ nhiều thẻ Zero
- Chất liệu: da cao cấp
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc
- Đã kèm theo ảnh thật và video thật ở cuối sản phẩm', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (29, 11, '#8B0000', 100, 'VI-MUA-DO');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (33, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rd4q-lwidw8smhwb19c.webp', 29);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (30, 11, '#000000', 100, 'VI-MUA-DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (34, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rd6p-lwidw76muqlw5d.webp', 30);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (31, 11, '#FFC0CB', 100, 'VI-MUA-HONG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (35, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rblc-lnl6dafa8qwhaf.webp', 31);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (12, 4, 'Ví Nữ Vuông Dễ Thương', 162000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : size 30x24x11cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc
- Đã kèm theo ảnh thật và video thật ở cuối sản phẩm', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (32, 12, '#8B4513', 100, 'VI-VUONG-NAU');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (36, 'https://down-vn.img.susercontent.com/file/sg-11134201-825zx-mjvbsq6e81l0c1.webp', 32);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (33, 12, '#FFC0CB', 100, 'VI-VUONG-HONG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (37, 'https://down-vn.img.susercontent.com/file/sg-11134201-8261d-mjvbso1ck3r490.webp', 33);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (34, 12, '#000000', 100, 'VI-VUONG-DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (38, 'https://down-vn.img.susercontent.com/file/sg-11134201-8261m-mjvbsoyk31tsda.webp', 34);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (13, 4, 'Ví nữ in hình mèo hoạt hình có thể gập lại đầy màu sắc', 180000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : size 10.5x2x8cm
- Ví đựng nhiều thẻ dễ thương Ví đựng tiền mặt tiện lợi
- Chất liệu: Da PU
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc
- Đã kèm theo ảnh thật và video thật ở cuối sản phẩm', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (35, 13, '#FFFFFF', 100, 'VI-MEO-TRANG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (39, 'https://down-vn.img.susercontent.com/file/sg-11134201-8261d-mjlims1bikn79c.webp', 35);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (36, 13, '#000000', 100, 'VI-MEO-DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (40, 'https://down-vn.img.susercontent.com/file/sg-11134201-825zs-mjlimtaun20w20.webp', 36);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (37, 13, '#FFC0CB', 100, 'VI-MEO-HONG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (41, 'https://down-vn.img.susercontent.com/file/sg-11134201-8260j-mjlimugwa877e2.webp', 37);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (14, 4, 'Ví nữ in hình bánh mì', 200000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : size 10.5x2x8 cm
- Ví Dễ Thương Đa Năng Hộp Đựng Thẻ Ví Tiền Mặt.
- Chất liệu: da cao cấp
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc
- Đã kèm theo ảnh thật và video thật ở cuối sản phẩm', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (38, 14, '#FFC0CB', 100, 'VI-MI-HONG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (42, 'https://down-vn.img.susercontent.com/file/sg-11134201-825zs-mk3mj87ttkw077.webp', 38);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (39, 14, '#FFF9C4', 100, 'VI-MI-VANG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (43, 'https://down-vn.img.susercontent.com/file/sg-11134201-8262b-mk3mjaa8plae04.webp', 39);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (40, 14, '#8B4513', 100, 'VI-MI-NAU');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (44, 'https://down-vn.img.susercontent.com/file/sg-11134201-8260r-mk3mj9aeg5xg4d.webp', 40);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (15, 4, 'Ví Hoạt Hình Dễ Thương', 120000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : size 10.5x1.5x8cm
- Ví Nữ Dễ Thương Trifold Khóa Đa Năng Nhiều Thẻ Ví Đựng Tiền Mặt.
- Chất liệu: da cao cấp
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc
- Đã kèm theo ảnh thật và video thật ở cuối sản phẩm', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (41, 15, '#000000', 100, 'VI-HH-DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (45, 'https://down-vn.img.susercontent.com/file/sg-11134201-822ys-mhym0ipeidqb1c.webp', 41);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (42, 15, '#008000', 100, 'VI-HH-XANHLA');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (46, 'https://down-vn.img.susercontent.com/file/sg-11134201-822xr-mhym0koskqo2ac.webp', 42);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (43, 15, '#FFF9C4', 100, 'VI-HH-VANG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (47, 'https://down-vn.img.susercontent.com/file/sg-11134201-822zh-mhym0jonqozo8b.webp', 43);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (16, 4, 'Ví ngắn hình trái tim', 130000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : size 11x2x8 cm
- Phong cách nhẹ nhàng – Nhiều ngăn thẻ, nhỏ gọn xinh xắn dành cho nữ sinh
- Chất liệu: da cao cấp
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc
- Đã kèm theo ảnh thật và video thật ở cuối sản phẩm', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (44, 16, '#000000', 100, 'VI-TIM-DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (48, 'https://down-vn.img.susercontent.com/file/vn-11134207-820l4-mhsszi7wizgje7.webp', 44);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (45, 16, '#FFC0CB', 100, 'VI-TIM-HONG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (49, 'https://down-vn.img.susercontent.com/file/vn-11134207-820l4-mhsszlced9mr23.webp', 45);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (46, 16, '#ADD8E6', 100, 'VI-TIM-XANH');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (50, 'https://down-vn.img.susercontent.com/file/vn-11134207-820l4-mhsszp16wow398.webp', 46);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (17, 2, 'Túi Tote Da Trơn', 200000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : 30x33x10cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc', 1);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (47, 17, '#8B4513', 100, 'TOTE-DA-TRON-NAU');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (51, 'https://down-vn.img.susercontent.com/file/sg-11134201-8258k-mfwc0g3bxzbdf1.webp', 47);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (48, 17, '#000000', 100, 'TOTE-DA-TRON-DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (52, 'https://down-vn.img.susercontent.com/file/sg-11134201-8259j-mfwc0ghezl6w53.webp', 48);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (53, 'https://down-vn.img.susercontent.com/file/vn-11134207-820l4-mfwc19upmtjj39.webp', 48);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (54, 'https://down-vn.img.susercontent.com/file/vn-11134207-820l4-mfwc19ulukge2c.webp', 48);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (18, 2, 'Túi Tote Dáng Thuyền', 300000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : 30x33x10cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (49, 18, '#8B4513', 100, 'TOTE-DANG-THUYEN-NAU');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (55, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-mdftj24s2rap65.webp', 49);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (50, 18, '#000000', 100, 'TOTE-DANG-THUYEN-DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (56, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-mdftj2fvmi3zd4.webp', 50);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (51, 18, '#FFFFFF', 100, 'TOTE-DANG-THUYEN-TRANG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (57, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-mdftj2d3qka7d5.webp', 51);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (58, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-mdftj1yyba9d74.webp', 51);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (19, 2, 'Túi Tote Hello Kitty', 250000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : 30x33x10cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (52, 19, '#FFC0CB', 100, 'TOTE-HELLO-KITTY-HONG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (59, 'https://down-vn.img.susercontent.com/file/vn-11134207-820l4-me2qqvf6zev7cf.webp', 52);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (53, 19, '#E3BC9A', 100, 'TOTE-HELLO-KITTY-NUDE');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (60, 'https://down-vn.img.susercontent.com/file/sg-11134201-824je-me2qozu3591e08@resize_w900_nl.webp', 53);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (54, 19, '#FFFFFF', 100, 'TOTE-HELLO-KITTY-TRANG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (61, 'https://down-vn.img.susercontent.com/file/sg-11134201-824gq-me2qp06pa4uc3c@resize_w900_nl.webp', 54);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (55, 19, '#000000', 100, 'TOTE-HELLO-KITTY-DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (62, 'https://down-vn.img.susercontent.com/file/sg-11134201-824hj-me2qp0nujvgh70@resize_w900_nl.webp', 55);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (20, 2, 'Túi Tote Thỏ Con', 400000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : 30x33x10cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (56, 20, '#8B4513', 100, 'TOTE-THO-CON-NAU');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (63, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-mdd2sfqber184f@resize_w900_nl.webp', 56);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (57, 20, '#000000', 100, 'TOTE-THO-CON-DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (64, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-mdd2tlttsywdf1.webp', 57);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (65, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-mdd2sfur895od0.webp', 57);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (66, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-mdd2sfnjisodb7.webp', 57);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (21, 2, 'Túi Tote Bán Nguyệt', 300000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : 30x33x10cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc', 1);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (58, 21, '#FFFFFF', 100, 'TOTE-BAN-NGUYET-TRANG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (67, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rdww-mdpx3851z0p654.webp', 58);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (59, 21, '#000000', 100, 'TOTE-BAN-NGUYET-DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (68, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rdwe-mdpx38x2y7kkdc.webp', 59);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (69, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-mdpx4akhqk050b.webp', 59);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (70, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-mdpx4amzobe4a7.webp', 59);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (71, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-mdpx4aa85l7wde.webp', 59);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (22, 2, 'Túi Tote Cổ Điển', 250000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : 30x33x10cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc', 1);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (60, 22, '#000000', 100, 'TOTE-CO-DIEN-DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (72, 'https://down-vn.img.susercontent.com/file/vn-11134207-81ztc-mml6bmyqxclk51.webp', 60);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (61, 22, '#FFFFFF', 100, 'TOTE-CO-DIEN-TRANG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (73, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m9l6dzl3o7ladd.webp', 61);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (62, 22, '#8B4513', 100, 'TOTE-CO-DIEN-NAU');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (74, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m9l6dzr7i32ye9.webp', 62);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (75, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m9l6dzk9pfdmf6.webp', 62);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (76, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m9l6dznvk5cq9a.webp', 62);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (77, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m9l6dzk9pfdmf6.webp', 62);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (23, 2, 'Túi Tote Min Min', 400000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : 30x33x10cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc', 1);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (63, 23, '#FFFDD0', 100, 'TOTE-MIN-MIN-KEM');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (78, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rdx7-mdsad95lw02dac.webp', 63);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (64, 23, '#ADD8E6', 100, 'TOTE-MIN-MIN-XANH');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (79, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rdye-mdsadagrz17oe4.webp', 64);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (65, 23, '#FFC0CB', 100, 'TOTE-MIN-MIN-HONG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (80, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rdx6-mdsad9ixcitmda.webp', 65);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (81, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-mdsaekpuae9x26.webp', 65);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (82, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-mdsaekakvbnpa7.webp', 65);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (83, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-mdsaeknwhgocc6.webp', 65);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (24, 2, 'Túi Tote Tiểu Thư', 450000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : 30x33x10cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (66, 24, '#000000', 100, 'TOTE-TIEU-THU-DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (84, 'https://down-vn.img.susercontent.com/file/sg-11134201-824j3-me19ybr62hoi92.webp', 66);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (67, 24, '#FFC0CB', 100, 'TOTE-TIEU-THU-HONG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (85, 'https://down-vn.img.susercontent.com/file/sg-11134201-824hi-me19yc71x79h7b.webp', 67);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (68, 24, '#FFFFFF', 100, 'TOTE-TIEU-THU-TRANG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (86, 'https://down-vn.img.susercontent.com/file/sg-11134201-824hv-me19ycmn0b9jc2.webp', 68);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (87, 'https://down-vn.img.susercontent.com/file/vn-11134207-820l4-me1a0tvx6fb78f.webp', 68);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (88, 'https://down-vn.img.susercontent.com/file/vn-11134207-820l4-me1a0trrb403dd.webp', 68);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (89, 'https://down-vn.img.susercontent.com/file/vn-11134207-820l4-me1a0u0m1t6t11.webp', 68);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (25, 3, 'Túi Tote Da Mềm Dung Tích Lớn - Size 35cm', 225000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : size 35x20x11 cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc
- Đã kèm theo ảnh thật và video thật ở cuối sản phẩm', 1);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (69, 25, '#8B4513', 100, 'HA838 - NAU');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (90, 'https://down-vn.img.susercontent.com/file/sg-11134201-822zr-mi76k8asg6bm64.webp', 69);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (70, 25, '#000000', 100, 'HA838 - DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (91, 'https://down-vn.img.susercontent.com/file/sg-11134201-822yn-mi76k94qddkxd2.webp', 70);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (71, 25, '#5E716A', 100, 'HA838 - XANHDEMIN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (92, 'https://down-vn.img.susercontent.com/file/sg-11134201-822zm-mi76k8sfpibo2c.webp', 71);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (26, 3, 'Túi Tote Da Mềm Dung Tích Lớn - Size 32cm', 250000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : size 32x20x10 cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc
- Đã kèm theo ảnh thật và video thật ở cuối sản phẩm', 1);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (72, 26, '#000000', 100, 'HA822 - DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (93, 'https://down-vn.img.susercontent.com/file/sg-11134201-82272-mhk95nsobocj95@resize_w900_nl.webp', 72);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (73, 26, '#8B4513', 100, 'HA822 - NAU');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (94, 'https://down-vn.img.susercontent.com/file/vn-11134207-820l4-mhk96y74u2h435.webp', 73);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (27, 3, 'Túi Tote VINICHY Đại Bản - Size 35cm', 300000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : size 35x24x11cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc
- Đã kèm theo ảnh thật và video thật ở cuối sản phẩm', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (74, 27, '#000000', 100, 'HA845 - DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (95, 'https://down-vn.img.susercontent.com/file/sg-11134201-81zu0-minyh7nj7wn85d@resize_w900_nl.webp', 74);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (75, 27, '#8B4513', 100, 'HA845 - NAU');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (96, 'https://down-vn.img.susercontent.com/file/sg-11134201-81zva-minyh6ro1fd0e5@resize_w900_nl.webp', 75);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (28, 3, 'Túi Tote Da Mềm Dung Tích Lớn - Size 28cm', 400000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : size 28x15x9 cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc
- Đã kèm theo ảnh thật và video thật ở cuối sản phẩm', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (76, 28, '#000000', 100, 'HA792 - DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (97, 'https://down-vn.img.susercontent.com/file/sg-11134201-824gh-mfhpr18zqsy77e@resize_w900_nl.webp', 76);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (77, 28, '#8B4513', 100, 'HA792 - NAU');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (98, 'https://down-vn.img.susercontent.com/file/sg-11134201-824g3-mfhpr0cypjbde4@resize_w900_nl.webp', 77);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (78, 28, '#8B0000', 100, 'HA792 - DO');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (99, 'https://down-vn.img.susercontent.com/file/vn-11134207-820l4-mfhps5s3198u79.webp', 78);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (29, 3, 'Túi xách nữ đeo chéo, đeo ngôi sao size 30cm', 300000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : size 30x16x7cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc
- Đã kèm theo ảnh thật và video thật ở cuối sản phẩm', 1);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (79, 29, '#8B4513', 1, 'HA761 - NAU');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (100, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rdvh-mdcovvtneh5g1d.webp', 79);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (80, 29, '#000000', 100, 'HA761 - DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (101, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rdvw-mdcovwnw68e1e4.webp', 80);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (81, 29, '#FFFFFF', 100, 'HA761 - TRANG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (102, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rdvl-mdcovw82rxeu98.webp', 81);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (30, 3, 'Túi xách nữ đeo vai dung tích lớn size 30cm', 250000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : size 30x24x11cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc
- Đã kèm theo ảnh thật và video thật ở cuối sản phẩm', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (82, 30, '#8B4513', 100, 'HA846 - NAU');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (103, 'https://down-vn.img.susercontent.com/file/sg-11134201-81zuz-mipgihdssu81ef.webp', 82);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (83, 30, '#000000', 100, 'HA846 - DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (104, 'https://down-vn.img.susercontent.com/file/sg-11134201-81zwj-mipgiikuowe8b5.webp', 83);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (31, 3, 'Túi xách nữ đeo chéo, đeo vai size 22cM', 300000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : size 22x14x8cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc
- Đã kèm theo ảnh thật và video thật ở cuối sản phẩm', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (84, 31, '#FFFDD0', 100, 'HA765 - KEM');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (105, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rdyk-mdeg6env0mas77.webp', 84);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (85, 31, '#8B4513', 100, 'HA765 - NAU');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (106, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rdwb-mdeg6f4idln822.webp', 85);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (32, 3, 'Túi Đeo Chéo Nữ Đa Năng - Size 21cm', 250000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : size 21x11x6 cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc
- Đã kèm theo ảnh thật và video thật ở cuối sản phẩm', 1);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (86, 32, '#FFC0CB', 100, 'HA623 - HONG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (107, 'https://down-vn.img.susercontent.com/file/sg-11134201-81zvw-mil3agzu8rnn3d.webp', 86);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (87, 32, '#FFFFFF', 100, 'HA623 - TRANG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (108, 'https://down-vn.img.susercontent.com/file/sg-11134201-81zwf-mil3ahmipczs53.webp', 87);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (88, 32, '#000000', 100, 'HA623 - DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (109, 'https://down-vn.img.susercontent.com/file/sg-11134201-81zvi-mil3aidhslc095.webp', 88);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (89, 32, '#8B4513', 100, 'HA623 - NAU');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (110, 'https://down-vn.img.susercontent.com/file/sg-11134201-81zu8-mil3ajnz0sn62c.webp', 89);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (33, 3, 'Túi xách nữ đeo chéo, đeo vai size 24cm', 300000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : size 24x14x5cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc
- Đã kèm theo ảnh thật và video thật ở cuối sản phẩm', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (90, 33, '#000000', 100, 'HA751 - DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (111, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rdye-md5j1ydkm3mud8.webp', 90);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (91, 33, '#FFFFFF', 100, 'HA751 - TRANG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (112, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-md5j3ib4rhwccf.webp', 91);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (34, 3, 'Túi xách nữ đeo chéo, đeo vai size 20cm', 400000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : size 20x15x11 cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc
- Đã kèm theo ảnh thật và video thật ở cuối sản phẩm', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (92, 34, '#000000', 100, 'HA857 - DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (113, 'https://down-vn.img.susercontent.com/file/sg-11134201-82631-mjdozo27tmv6be.webp', 92);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (93, 34, '#8B4513', 100, 'HA857 - NAU');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (114, 'https://down-vn.img.susercontent.com/file/sg-11134201-82625-mjdozmnmd43mba.webp', 93);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (94, 34, '#FFFFFF', 100, 'HA857 - TRANG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (115, 'https://down-vn.img.susercontent.com/file/sg-11134201-8261v-mjdoznd4l0xwa7.webp', 94);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (35, 3, 'Túi Tote Balo Đa Năng VINICHY - Dáng Basic', 250000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : size 30x22x10cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc
- Đã kèm theo ảnh thật và video thật ở cuối sản phẩm', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (95, 35, '#000000', 100, 'HA690-DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (116, 'https://down-vn.img.susercontent.com/file/sg-11134201-7ra1e-mbbxgtmkn7r7c5@resize_w900_nl.webp', 95);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (96, 35, '#8B4513', 100, 'HA690-NAU');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (117, 'https://down-vn.img.susercontent.com/file/sg-11134201-7ra3j-mbbxgu87rjoid6@resize_w900_nl.webp', 96);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (36, 3, 'Túi xách nữ đeo chéo, đeo vai VINICHY, tote,balo dung tích lớn size 30cm', 400000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : size 30x26x10cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc
- Đã kèm theo ảnh thật và video thật ở cuối sản phẩm', 1);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (97, 36, '#000000', 10, 'HA634-DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (118, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m7n8jxbab35dad@resize_w900_nl.webp', 97);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (98, 36, '#A67B5B', 100, 'HA634-NAUTAY');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (119, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m7n8jxd89myrd9.webp', 98);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (99, 36, '#4B3621', 100, 'HA634-NAUCOFFEE');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (120, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m7n8jxi7zj5dd0@resize_w900_nl.webp', 99);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (37, 3, 'Túi xách nữ đeo chéo, đeo vai, đi chơi, đi học VINICHY size 20cm', 250000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : 20x15x10cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc
- Đã kèm theo ảnh thật và video thật ở cuối sản phẩm', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (100, 37, '#000000', 100, 'HA751-DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (121, 'https://down-vn.img.susercontent.com/file/sg-11134201-7qvcz-lgvsx4fztc7g46.webp', 100);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (101, 37, '#8B4513', 100, 'HA751-NAU');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (122, 'https://down-vn.img.susercontent.com/file/sg-11134201-7qve8-lgvsx4923ha75d.webp', 101);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (38, 3, 'Túi cốp cầm tay, đeo chéo, đeo vai VINICHY size 18cm', 250000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : size 18x11x9 cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc
- Đã kèm theo ảnh thật và video thật ở cuối sản phẩm', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (102, 38, '#000000', 100, 'HA832-DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (123, 'https://down-vn.img.susercontent.com/file/sg-11134201-822xz-mhzphbhobci05e@resize_w900_nl.webp', 102);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (103, 38, '#FFC0CB', 100, 'HA832-HONG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (124, 'https://down-vn.img.susercontent.com/file/vn-11134207-820l4-mhzqqlolkk5c8e.webp', 103);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (104, 38, '#FFFFFF', 100, 'HA832-TRANG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (125, 'https://down-vn.img.susercontent.com/file/sg-11134201-822za-mhzphcgflk3l20@resize_w900_nl.webp', 104);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (39, 3, 'Túi xách nữ đeo chéo, đeo vai VINICHY', 400000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : size 20x19x9 cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc
- Đã kèm theo ảnh thật và video thật ở cuối sản phẩm', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (105, 39, '#000000', 100, 'HA866-DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (126, 'https://down-vn.img.susercontent.com/file/sg-11134201-81zvx-mmk6y3o43g1we6.webp', 105);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (106, 39, '#4B3621', 100, 'HA866-NAUCOFFEE');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (127, 'https://down-vn.img.susercontent.com/file/sg-11134201-81zuw-mmk6y45bqvb5f8.webp', 106);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (107, 39, '#FFFFFF', 100, 'HA866-TRANG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (128, 'https://down-vn.img.susercontent.com/file/sg-11134201-81zwa-mmk6y4lz2i9v0b.webp', 107);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (40, 3, 'Túi xách nữ đeo vai, kẹp nách VINICHY size 26cm', 400000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : size 26x15x11cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo vai VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc
- Đã kèm theo ảnh thật và video thật ở cuối sản phẩm', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (108, 40, '#F5F5DC', 100, 'HA647-BE');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (129, 'https://down-vn.img.susercontent.com/file/sg-11134201-7repl-m8iyzp61o74lf5.webp', 108);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (109, 40, '#5E716A', 100, 'HA647-XANHDENIM');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (130, 'https://down-vn.img.susercontent.com/file/sg-11134201-7repp-m8iyzpp6xkn45a.webp', 109);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (110, 40, '#8B4513', 100, 'HA647-NAU');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (131, 'https://down-vn.img.susercontent.com/file/sg-11134201-7reog-m8iyzq6o807pbd.webp', 110);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (111, 40, '#808080', 100, 'HA647-XAM');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (132, 'https://down-vn.img.susercontent.com/file/sg-11134201-7reoi-m8iyzqo5jtfk24.webp', 111);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (41, 3, 'Túi xách nữ đeo vai, kẹp nách VINICHY size 30cm', 400000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : size 30x22x8cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo vai VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc
- Đã kèm theo ảnh thật và video thật ở cuối sản phẩm', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (112, 41, '#000000', 100, 'HA638-DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (133, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m8hi5cvu4m7i31.webp', 112);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (113, 41, '#8B4513', 100, 'HA638-NAU');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (134, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rend-m81k5q3w76w95e.webp', 113);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (42, 3, 'Túi xách nữ đeo chéo, đeo vai VINICHY size 23cm', 400000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : size 30x20x10cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc
- Đã kèm theo ảnh thật và video thật ở cuối sản phẩm', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (114, 42, '#000000', 100, 'HA702-DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (135, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rdyf-mbudx4ag5nw92e@resize_w900_nl.webp', 114);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (115, 42, '#8B4513', 100, 'HA702-NAU');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (136, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rdy1-mbudx3wkq0lp6b@resize_w900_nl.webp', 115);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (43, 3, 'Túi Tote Balo Đa Năng VINICHY - Dáng Retro', 250000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : size 30x22x10cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc
- Đã kèm theo ảnh thật và video thật ở cuối sản phẩm', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (116, 43, '#000000', 100, 'HA684-DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (137, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-maw7y4j661ttf7.webp', 116);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (117, 43, '#8B4513', 100, 'HA684-NAU');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (138, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rat0-maw7vqqzs1s3b3.webp', 117);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (44, 3, 'Túi Đeo Chéo VINICHY Cao Cấp - Size 21cm', 400000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : 21x16x8cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc
- Đã kèm theo ảnh thật và video thật ở cuối sản phẩm', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (118, 44, '#000000', 100, 'HA433-DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (139, 'https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-lp3hsp9an13i4c@resize_w900_nl.webp', 118);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (119, 44, '#FFFFFF', 100, 'HA433-TRANG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (140, 'https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-lp3hsp9apu8ebe@resize_w900_nl.webp', 119);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (45, 3, 'Túi xách nữ đeo vai, đeo chéo Retro Classic', 260000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : 21x13x10 cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (120, 45, '#A67B5B', 100, 'TUI-DEO-VAI-DA-NAU-TAY');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (141, 'https://down-vn.img.susercontent.com/file/sg-11134201-81ztc-mmlmcaf9l7up83@resize_w900_nl.webp', 120);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (121, 45, '#000000', 100, 'TUI-DEO-VAI-DA-DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (142, 'https://down-vn.img.susercontent.com/file/sg-11134201-81zu4-mmlmc9unw076f4@resize_w900_nl.webp', 121);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (122, 45, '#4B3621', 100, 'TUI-DEO-VAI-DA-NAU-CAFE');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (143, 'https://down-vn.img.susercontent.com/file/sg-11134201-81ztt-mmlmc9bc16o602@resize_w900_nl.webp', 122);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (144, 'https://down-vn.img.susercontent.com/file/vn-11134207-81ztc-mmlmd0yday2u0c.webp', 122);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (145, 'https://down-vn.img.susercontent.com/file/vn-11134207-81ztc-mmlmd0z43y8f0a.webp', 122);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (46, 3, 'Túi cốp cầm tay, túi xách nữ đeo chéo', 350000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : 18x14x7cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (123, 46, '#FFFFFF', 100, 'HA567-TRANG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (146, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-m2661jmgogc2d6@resize_w900_nl.webp', 123);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (124, 46, '#000000', 100, 'HA567-DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (147, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-m2661glf40xg38@resize_w900_nl.webp', 124);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (125, 46, '#8B0000', 100, 'HA567-DO');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (148, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-m2661miig6uq27@resize_w900_nl.webp', 125);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (149, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-m265zf6ajx6c93.webp', 125);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (150, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-m265zf4cmrhu9c.webp', 125);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (47, 3, 'Túi xác nữ đeo chéo, đeo vai hình bán nguyệt nơ', 370000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : 22x14x7cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc', 1);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (126, 47, '#FFC0CB', 0, 'HA494-HONG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (151, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rd66-lv4fz1qq0948de@resize_w900_nl.webp', 126);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (127, 47, '#000000', 100, 'HA494-DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (152, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rd5p-lv4fz1eshpnme2@resize_w900_nl.webp', 127);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (128, 47, '#FFFFFF', 100, 'HA494-TRANG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (153, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rd5q-lv4fz241gri608@resize_w900_nl.webp', 128);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (154, 'https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-lv4fzt1iaiuxc1.webp', 128);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (155, 'https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-lv4fzt1ig54p24.webp', 128);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (48, 3, 'Túi xách nữ đeo vai, đeo chéo  Y2K Vibe', 400000, '', 1);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (129, 48, '#FFFFFF', 100, 'HA362-TRANG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (156, 'https://down-vn.img.susercontent.com/file/sg-11134201-7qveo-lipdbkieneuv20@resize_w900_nl.webp', 129);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (130, 48, '#000000', 100, 'HA362-DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (157, 'https://down-vn.img.susercontent.com/file/sg-11134201-7qvdm-lipdbjvnhwox6e@resize_w900_nl.webp', 130);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (131, 48, '#C0C0C0', 100, 'HA362-BAC');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (158, 'https://down-vn.img.susercontent.com/file/sg-11134201-7qvfc-lipdbk7b0uwja2@resize_w900_nl.webp', 131);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (159, 'https://down-vn.img.susercontent.com/file/sg-11134201-7qved-lipdbhcd7hik47.webp', 131);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (160, 'https://down-vn.img.susercontent.com/file/sg-11134201-7qvdr-lipdbix8w8p9d1.webp', 131);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (49, 3, 'Túi xách nữ đeo vai, đeo chéo Lady', 520000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : 24x14x5cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (132, 49, '#FFFFFF', 100, 'HA751- TRANG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (161, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rdyc-md5j1yr628u41d@resize_w900_nl.webp', 132);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (133, 49, '#000000', 100, 'HA751- DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (162, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rdye-md5j1ydkm3mud8@resize_w900_nl.webp', 133);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (163, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-md5j3jdz6my5be.webp', 133);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (164, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-md5j3it6138tb4.webp', 133);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (165, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-md5j3ibyq9ilbc.webp', 133);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (50, 3, 'Túi xách nữ đeo vai,  đeo chéo Vuông Cầu Bạc', 330000, '', 1);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (134, 50, '#FFFFFF', 0, 'HA525-TRANG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (166, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rdvo-lxox4zw039fu4b@resize_w900_nl.webp', 134);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (135, 50, '#000000', 0, 'HA525-DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (167, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rdyk-lxox4zdeugsq46@resize_w900_nl.webp', 135);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (168, 'https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-lxox5qj73lcr86.webp', 135);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (169, 'https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-lxox5qj73ljt22.webp', 135);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (170, 'https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-lxox5qkb1z2x7b.webp', 135);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (51, 3, 'Túi xách nữ đeo vai, đeo chéo Thỏ Bông', 500000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : 20x12x7cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (136, 51, '#4B3621', 100, 'HA848-NAU-CAFE');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (171, 'https://down-vn.img.susercontent.com/file/sg-11134201-81zwc-miwk0cumr08we0@resize_w900_nl.webp', 136);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (137, 51, '#000000', 100, 'HA848-DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (172, 'https://down-vn.img.susercontent.com/file/sg-11134201-81zw6-miwk0dj43k003e@resize_w900_nl.webp', 137);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (138, 51, '#A67B5B', 100, 'HA848-NAU-TAY');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (173, 'https://down-vn.img.susercontent.com/file/sg-11134201-81zuo-miwk0e83igoy81@resize_w900_nl.webp', 138);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (174, 'https://down-vn.img.susercontent.com/file/vn-11134207-820l4-miwk23753taaaa.webp', 138);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (175, 'https://down-vn.img.susercontent.com/file/vn-11134207-820l4-miwk22kwpxxef8.webp', 138);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (52, 3, 'Túi xách nữ đeo vai, kẹp nách Moon', 470000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : 22x10x7cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp với thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (139, 52, '#8B4513', 100, 'HA726-NAU');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (176, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rdyx-mccysb270xkia6@resize_w900_nl.webp', 139);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (140, 52, '#000000', 100, 'HA726-DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (177, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-mccytq3kc66q05.webp', 140);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (141, 52, '#8B0000', 100, 'HA726-DO');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (178, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rdwx-mccys96rvt755f@resize_w900_nl.webp', 141);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (142, 52, '#FFFFFF', 100, 'HA726-TRANG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (179, 'https://down-vn.img.susercontent.com/file/sg-11134201-7rdyl-mccysa2eha7489.webp', 142);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (180, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-mccytq3ufyw3e9.webp', 142);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (53, 3, 'Túi xách nữ đeo vai, đeo chéo  Khăn Lụa', 560000, '', 0);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (143, 53, '#4B3621', 100, 'HA841-NAU-CAFE');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (181, 'https://down-vn.img.susercontent.com/file/sg-11134201-822zn-mib61xkz42dk4d@resize_w900_nl.webp', 143);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (144, 53, '#000000', 100, 'HA841-DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (182, 'https://down-vn.img.susercontent.com/file/sg-11134201-822yv-mib61z8o4qo7fa@resize_w900_nl.webp', 144);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (145, 53, '#8B4513', 100, 'HA841-NAU-TAY');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (183, 'https://down-vn.img.susercontent.com/file/sg-11134201-822wh-mib61y4dxjwha6@resize_w900_nl.webp', 145);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (184, 'https://down-vn.img.susercontent.com/file/vn-11134207-820l4-mib63c2u4r29b0.webp', 145);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (185, 'https://down-vn.img.susercontent.com/file/vn-11134207-820l4-mib63ch5g5c6f5.webp', 145);
INSERT INTO san_pham (id, loai_sp_id, ten_sp, gia_ban, mo_ta, noi_bat) VALUES (54, 3, 'Túi xách nữ đeo vai, đeo chéo Trám Bóng Charm', 370000, 'THÔNG TIN SẢN PHẨM :
- Kích thước : 18x13x8cm
- Phù hợp với mọi hoạt động như đi chơi, đi học, công sở đi làm, đi dự tiệc.
- Chất liệu: da cao cấp
- Túi xách nữ đeo chéo VINICHY có đường may tỉ mỉ tinh tế đảm bảo độ bền đẹp with thời gian
- Màu sắc : Nhiều màu
- Phong cách : Hàn Quốc', 1);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (146, 54, '#FFFFFF', 100, 'HA707-TRANG');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (186, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-mbx90q1sqvl615.webp', 146);
INSERT INTO san_pham_ct (id, sp_id, mau_id, so_luong_ton, sku) VALUES (147, 54, '#000000', 100, 'HA707-DEN');
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (187, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-mbx90pqp73w225.webp', 147);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (188, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-mbx90q5ol6y263.webp', 147);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (189, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-mbx90pqp74a235.webp', 147);
INSERT INTO hinh_anh_sp_ct (id, link_hinh_anh, sp_ct_id) VALUES (190, 'https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-mbx90q2cq1yq11.webp', 147);

-- 7. Giỏ Hàng (Carts) - Sử dụng ID cố định để tránh lặp khi khởi động lại
INSERT OR IGNORE INTO gio_hang (id, tk_id, tong_tien_tam_tinh) VALUES 
(1, 1, 1400000), (2, 2, 500000), (3, 3, 600000), (4, 4, 700000), (5, 5, 800000),
(6, 6, 900000), (7, 7, 1000000), (8, 8, 1100000), (9, 9, 1200000), (10, 10, 1300000),
(11, 11, 0), (12, 12, 0), (13, 13, 0), (14, 14, 0), (15, 15, 0), (16, 16, 0), (17, 17, 0), (18, 18, 0), (19, 19, 0), (20, 20, 0);

-- 8. Thông Tin Giao Hàng (Shipping Info) - Sử dụng ID cố định để tránh lặp
INSERT OR IGNORE INTO thong_tin_giao_hang (id, tk_id, ten_kh, sdt_kh, dia_chi, mac_dinh, trang_thai) VALUES
(1, 1, 'Admin', '0999999999', 'Vinichy Store', 1, 'Đang dùng'),
(2, 2, 'Phương Thảo', '0912345678', 'Hà Nội', 1, 'Đang dùng'),
(3, 3, 'Thanh Thảo', '0912345679', 'TP.HCM', 1, 'Đang dùng'),
(4, 4, 'Khánh Ly', '0912345680', 'Đà Nẵng', 1, 'Ngừng dùng'),
(5, 5, 'Anh Phương', '0912345681', 'Cần Thơ', 1, 'Đang dùng'),
(6, 6, 'Xuân Khánh', '0912345682', 'Hải Phòng', 1, 'Đang dùng'),
(7, 7, 'Gia Hân', '0912345683', 'Nha Trang', 1, 'Đang dùng'),
(8, 8, 'Bảo Ngọc', '0912345684', 'Huế', 1, 'Đang dùng'),
(9, 9, 'Minh Tú', '0912345685', 'Quảng Ninh', 1, 'Đang dùng'),
(10, 10, 'Hồng Hạnh', '0912345686', 'Bắc Ninh', 1, 'Đang dùng'),
(11, 11, 'Thế Hiển', '0912345687', 'Thanh Hóa', 1, 'Đang dùng'),
(12, 12, 'Minh Khoa', '0912345688', 'Nghệ An', 1, 'Đang dùng'),
(13, 13, 'Quốc Việt', '0912345689', 'Lào Cai', 1, 'Đang dùng'),
(14, 14, 'Diệu Nhi', '0912345690', 'Đà Lạt', 1, 'Đang dùng'),
(15, 15, 'Trọng Hiếu', '0912345691', 'Vũng Tàu', 1, 'Đang dùng'),
(16, 16, 'Thanh Hằng', '0912345692', 'Sapa', 1, 'Đang dùng'),
(17, 17, 'Hoàng Long', '0912345693', 'Phú Quốc', 1, 'Đang dùng'),
(18, 18, 'Kim Chi', '0912345694', 'Tây Ninh', 1, 'Đang dùng'),
(19, 19, 'Minh Quân', '0912345695', 'Bình Dương', 1, 'Đang dùng'),
(20, 20, 'Thế Vinh', '0912345696', 'Thái Nguyên', 1, 'Đang dùng');

-- 9. Khuyến Mãi (Promotions)
INSERT OR IGNORE INTO khuyen_mai (ma_giam_gia, ten_khuyen_mai, gia_tri_giam, ngay_bat_dau, ngay_ket_thuc, trang_thai, don_toi_thieu, so_luot_sd) VALUES
('VINICHY10K', 'Khuyến mãi tri ân khách hàng', 10000, '2026-04-24 00:00:00', '2026-05-25 23:59:59', 1, 300000, 50),
('VINICHY5K', 'Khuyến mãi tri ân khách hàng', 10000, '2026-04-24 00:00:00', '2026-05-25 23:59:59', 1, 200000, 50),
('EXPIRED2025', 'Mã khuyến mãi đã hết hạn', 20000, '2025-01-01 00:00:00', '2025-12-31 23:59:59', 1, 100000, 100),
('SOLUOT0', 'Mã khuyến mãi đã hết lượt', 15000, '2026-04-01 00:00:00', '2026-12-31 23:59:59', 1, 50000, 0),
('FUTURE2026', 'Khuyến mãi sắp diễn ra', 30000, '2026-05-15 00:00:00', '2026-06-15 23:59:59', 1, 200000, 100),
('DIEUKIEN500K', 'Khuyến mãi đơn 5 Triệu', 50000, '2025-01-01 00:00:00', '2027-12-31 23:59:59', 1, 5000000, 100);

-- 10. Đơn Mua Test (Order Test)
INSERT OR IGNORE INTO don_mua (id, ngay_tao_don, ma_giam_gia, ty_le_chiet_khau, tong_tien, tong_thanh_toan, trang_thai, ttgh_id) VALUES
(7, '2026-05-02 13:30:00', 'VINICHY10K', 0.0, 300000, 290000, 'Chờ xác nhận', 2);

INSERT OR IGNORE INTO don_mua_ct (don_mua_id, sp_ct_id, so_luong_mua, thanh_tien) VALUES
(7, 4, 1, 300000);

-- 11. Ghi nhận sử dụng khuyến mãi (Promotion Usage)
INSERT OR IGNORE INTO su_dung_khuyen_mai (ngay_su_dung, so_luot_sd, ma_don_mua, ma_giam_gia, ma_tk) VALUES
('2026-05-02 13:30:00', 1, 7, 'VINICHY10K', 2);
