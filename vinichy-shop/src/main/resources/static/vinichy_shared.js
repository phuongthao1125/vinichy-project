/* ===================================================
   VINICHY - Shared Header / Footer / Navigation
   =================================================== */

/* ---------- CSS CHUNG ---------- */
const VINICHY_CSS = `
*{ box-sizing:border-box; }
body{ margin:0; font-family:Arial,sans-serif; background:#fff; }

/* TOP BAR */
.topbar{ display:flex; justify-content:center; background:#0F1112;
  padding:18px; color:white; font-size:13px; }

/* HEADER */
.header{ display:flex; align-items:center; padding:24px 64px; background:white; }
.logo{ width:32px; height:32px; margin-right:8px; }
.brand{ font-size:23px; font-weight:bold; cursor:pointer; }
.spacer{ flex:1; }
.menu{ display:flex; align-items:center; gap:50px; font-size:20px;
  padding-left:10px; padding-right:10px; }
.menu span{ cursor:pointer; transition:color .2s; }
.menu span:hover{ color:#A09A78; }
.menu span.active{ color:#A09A78; font-weight:bold; }
.search{ border-radius:15px; border:1px solid #A09A78; padding:7px 20px; margin-right:17px; }
.icon{ width:28px; height:28px; cursor:pointer; }
.icon2{ margin-right:28px; }
.icon1{ margin-right:24px; }

/* USER DROPDOWN */
.user-menu{ position:relative; display:flex; }
.auth-popup{ display:none; position:absolute; top:45px; right:0; z-index:1000; }
.auth-popup.active{ display:block; }

/* PRODUCT POPUP */
.product-popup{ display:none; position:fixed; top:90px; left:0; width:100%;
  background:white; z-index:999; box-shadow:0 5px 20px rgba(0,0,0,.2); }
.product-popup-content{ width:1440px; height:467px; margin:auto; position:relative; overflow:hidden; }
.product-popup-content img{ position:absolute; }
.popup-title{ position:absolute; left:129px; top:45px; font-size:25px; font-weight:700; }
.popup-item{ position:absolute; display:flex; align-items:center; font-size:25px;
  color:rgba(0,0,0,.85); cursor:pointer; transition:color .2s; }
.popup-item:hover{ color:#A09A78; }
.popup-arrow{ font-size:33px; margin-right:10px; }

/* FOOTER */
.footer{ background:#0F1112; padding:30px 40px 20px 40px; color:white; }
.footer-container{ max-width:1200px; margin:auto; display:flex; justify-content:space-between; }
.footer-col{ width:33%; }
.footer-col2{ width:20%; }
.footer-logo{ display:flex; align-items:center; gap:10px; margin-bottom:10px; }
.footer-desc{ color:#cfcfcf; max-width:280px; line-height:1.7; }
.contact{ display:flex; align-items:center; gap:10px; margin-bottom:12px; }
.contact img{ width:18px; }
.copyright{ border-top:1px solid #222; margin-top:20px; padding-top:20px;
  font-size:13px; color:#888; }

/* RESPONSIVE */
@media(max-width:768px){
  .header{ flex-wrap:wrap; padding:15px 20px; }
  .menu{ width:100%; justify-content:space-around; margin:10px 0; gap:20px; font-size:16px; }
  .search{ width:100%; margin:10px 0; }
  .user-menu{ width:100%; justify-content:space-around; }
  .footer-container{ flex-direction:column; gap:40px; }
  .footer-col,.footer-col2{ width:100%; }
}
@media(max-width:480px){
  .brand{ font-size:18px; }
  .menu{ font-size:14px; gap:15px; }
  .icon{ width:24px; height:24px; }
  .search{ font-size:14px; }
}
@media(max-width:1024px){
  .product-popup-content{ width:100%; height:auto; padding:40px 20px; }
  .product-popup-content img{ display:none; }
  .popup-title{ position:static; margin-bottom:30px; }
  .popup-item{ position:static; font-size:20px; margin:15px 0; }
  .popup-arrow{ font-size:24px; }
}
`;

/* ---------- HTML HEADER ---------- */
function buildHeader(activePage) {
  const menuHtml = [
    `<span class="${activePage === 'trang_chu' ? 'active' : ''}" onclick="navigate('trang_chu')">Trang chủ</span>`,
    `<span class="${activePage === 'san_pham' ? 'active' : ''}" onclick="toggleProductPopup()">Sản phẩm</span>`,
    `<span onclick="scrollToContact()">Liên hệ</span>`,
  ].join('');

  return `
<!-- TOP BAR -->
<div class="topbar">VINICHY - Khám phá các dòng túi xách đa dạng phù hợp với mọi phong cách</div>

<!-- HEADER -->
<div class="header">
  <img class="logo" src="https://storage.googleapis.com/tagjs-prod.appspot.com/v1/poABPRZHAM/0i53wqtq_expires_30_days.png">
  <span class="brand" onclick="navigate('trang_chu')">VINICHY</span>
  <div class="spacer"></div>
  <div class="menu">${menuHtml}</div>
  <div class="spacer"></div>
  <input class="search" type="text" placeholder="Tìm kiếm sản phẩm" onkeydown="handleSearch(event,this)">

  <!-- USER MENU -->
  <div class="user-menu">
    <img class="icon icon1" src="https://storage.googleapis.com/tagjs-prod.appspot.com/v1/poABPRZHAM/co4khhnq_expires_30_days.png" onclick="navigate('gio_hang')" title="Giỏ hàng">
    <div>
      <img class="icon icon2" src="https://storage.googleapis.com/tagjs-prod.appspot.com/v1/poABPRZHAM/5fitz6ap_expires_30_days.png" onclick="toggleLogin()">

      <!-- POPUP ĐĂNG NHẬP -->
      <div id="loginPopup" class="auth-popup">
        <div style="width:427px;position:relative;background:white;box-shadow:0 8px 24px rgba(0,0,0,.3);border-radius:5px;padding:30px;">
          <div style="text-align:center;margin-bottom:15px;">
            <div style="font-size:24px;font-weight:500">ĐĂNG NHẬP</div>
            <div style="color:rgba(15,17,18,.7);font-size:16px">Nhập email và mật khẩu của bạn</div>
          </div>
          <hr style="margin-bottom:20px;">
          <label>Tên đăng nhập</label>
          <input id="loginEmail" style="width:100%;height:36px;padding-left:16px;margin:5px 0 12px;border-radius:10px;border:1px solid #ccc" placeholder="Nhập email">
          <label>Mật khẩu</label>
          <input id="loginPassword" type="password" style="width:100%;height:36px;padding-left:16px;margin:5px 0 12px;border-radius:10px;border:1px solid #ccc" placeholder="Nhập mật khẩu">
          <div style="text-align:right;font-size:12px;margin-bottom:15px;">
            <span onclick="showPopup('forgotPopup')" style="cursor:pointer;color:#A09A78">Quên mật khẩu?</span>
          </div>
          <button onclick="doLogin()" style="width:100%;padding:10px;background:black;color:white;border:none;border-radius:8px;cursor:pointer;">Đăng nhập</button>
          <div style="text-align:center;margin-top:10px;font-size:13px;">
            <span style="color:#8a7f5c">Chưa có tài khoản? </span>
            <span onclick="showPopup('registerPopup')" style="cursor:pointer">Đăng ký</span>
          </div>
        </div>
      </div>

      <!-- POPUP ĐĂNG KÝ -->
      <div id="registerPopup" class="auth-popup">
        <div style="width:427px;position:relative;background:white;box-shadow:0 8px 24px rgba(0,0,0,.3);border-radius:5px;padding:30px;">
          <div style="text-align:center;margin-bottom:15px;">
            <div style="font-size:24px;font-weight:500">ĐĂNG KÝ</div>
            <div style="color:rgba(15,17,18,.7);font-size:16px">Tạo tài khoản mới</div>
          </div>
          <hr style="margin-bottom:20px;">
          <label>Họ và tên</label>
          <input style="width:100%;height:36px;padding-left:16px;margin:5px 0 12px;border-radius:10px;border:1px solid #ccc" placeholder="Nhập họ tên">
          <label>Email</label>
          <input style="width:100%;height:36px;padding-left:16px;margin:5px 0 12px;border-radius:10px;border:1px solid #ccc" placeholder="Nhập email">
          <label>Mật khẩu</label>
          <input type="password" style="width:100%;height:36px;padding-left:16px;margin:5px 0 8px;border-radius:10px;border:1px solid #ccc" placeholder="Nhập mật khẩu">
          <div style="font-size:11px;color:#888;margin-bottom:15px;">Mật khẩu phải có ít nhất 8 ký tự, bao gồm ký tự và số</div>
          <button style="width:70%;height:38px;display:block;margin:0 auto;background:black;color:white;border:none;border-radius:10px;cursor:pointer;">Tạo tài khoản</button>
          <div style="text-align:center;margin-top:15px;font-size:12px">
            <div onclick="showPopup('forgotPopup')" style="cursor:pointer;color:#888;margin-bottom:8px">Đổi email</div>
            <span style="color:#A09A78">Bạn đã có tài khoản? </span>
            <span onclick="showPopup('loginPopup')" style="cursor:pointer">Trở về đăng nhập</span>
          </div>
        </div>
      </div>

      <!-- POPUP QUÊN MẬT KHẨU -->
      <div id="forgotPopup" class="auth-popup">
        <div style="width:427px;position:relative;background:white;box-shadow:0 8px 24px rgba(0,0,0,.3);border-radius:5px;padding:30px;">
          <div style="text-align:center;margin-bottom:15px;">
            <div style="font-size:25px;font-weight:500">KHÔI PHỤC MẬT KHẨU</div>
            <div style="font-size:18px;color:rgba(15,17,18,.7)">Nhập email của bạn</div>
          </div>
          <hr style="margin-bottom:20px;">
          <label>Email</label>
          <input style="padding-left:16px;margin:5px 0 20px;width:100%;height:36px;border-radius:10px;border:1px solid rgba(0,0,0,.28)" placeholder="Nhập email">
          <button onclick="showPopup('otpPopup')" style="width:70%;height:38px;display:block;margin:0 auto;background:black;color:white;border:none;border-radius:20px;cursor:pointer;">Gửi OTP</button>
          <div style="text-align:center;margin-top:15px;font-size:12px">
            <span style="color:#A09A78">Bạn đã nhớ mật khẩu? </span>
            <span onclick="showPopup('loginPopup')" style="cursor:pointer">Trở về đăng nhập</span>
          </div>
        </div>
      </div>

      <!-- POPUP OTP -->
      <div id="otpPopup" class="auth-popup">
        <div style="width:427px;position:relative;background:white;box-shadow:0 8px 24px rgba(0,0,0,.3);border-radius:5px;padding:30px;">
          <div style="text-align:center;margin-bottom:15px;">
            <div style="font-size:25px;font-weight:500">KHÔI PHỤC MẬT KHẨU</div>
            <div style="font-size:18px;color:rgba(15,17,18,.7)">Nhập mã OTP của bạn:</div>
          </div>
          <hr style="margin-bottom:20px;">
          <label>Mã OTP</label>
          <input type="text" placeholder="Nhập OTP" style="width:100%;height:36px;border-radius:10px;border:1px solid rgba(0,0,0,.28);padding-left:16px;margin:5px 0 5px;">
          <div style="font-size:13px;opacity:.5;margin-bottom:20px;">01:00</div>
          <button onclick="showPopup('resetPopup')" style="width:70%;height:38px;display:block;margin:0 auto;background:black;color:white;border:none;border-radius:20px;cursor:pointer;">Xác minh</button>
          <div style="text-align:center;margin-top:15px;font-size:12px">
            <span style="color:#A09A78">Bạn đã nhớ mật khẩu? </span>
            <span onclick="showPopup('loginPopup')" style="cursor:pointer">Trở về đăng nhập</span>
          </div>
        </div>
      </div>

      <!-- POPUP ĐẶT MẬT KHẨU MỚI -->
      <div id="resetPopup" class="auth-popup">
        <div style="width:427px;position:relative;background:white;box-shadow:0 8px 24px rgba(0,0,0,.3);border-radius:5px;padding:30px;">
          <div style="text-align:center;margin-bottom:15px;">
            <div style="font-size:25px;font-weight:500">QUÊN MẬT KHẨU</div>
            <div style="font-size:18px;color:rgba(15,17,18,.7)">Đổi mật khẩu mới</div>
          </div>
          <hr style="margin-bottom:20px;">
          <label>Mật khẩu mới</label>
          <input type="password" style="padding-left:16px;margin:5px 0 12px;width:100%;height:36px;border-radius:10px;border:1px solid rgba(0,0,0,.28)" placeholder="Nhập mật khẩu mới">
          <label>Xác nhận mật khẩu mới</label>
          <input type="password" style="padding-left:16px;margin:5px 0 20px;width:100%;height:36px;border-radius:10px;border:1px solid rgba(0,0,0,.28)" placeholder="Nhập xác nhận mật khẩu">
          <button onclick="showPopup('loginPopup')" style="width:70%;height:38px;display:block;margin:0 auto;background:black;color:white;border:none;border-radius:20px;cursor:pointer;">Cập nhật</button>
          <div style="text-align:center;margin-top:15px;font-size:12px">
            <div onclick="showPopup('forgotPopup')" style="cursor:pointer;color:#888;margin-bottom:8px">Đổi email</div>
            <span style="color:#A09A78">Bạn đã nhớ mật khẩu? </span>
            <span onclick="showPopup('loginPopup')" style="cursor:pointer">Trở về đăng nhập</span>
          </div>
        </div>
      </div>

      <!-- POPUP TÀI KHOẢN -->
      <div id="accountPopup" class="auth-popup">
        <div style="width:255px;background:white;box-shadow:0 8px 24px rgba(0,0,0,.5);border-radius:5px;padding-bottom:15px;">
          <h3 style="text-align:center;padding-top:15px;margin:0 0 10px;">TÀI KHOẢN CỦA TÔI</h3>
          <hr>
          <p style="padding:10px 20px;cursor:pointer;margin:0" onclick="navigate('lich_su_giao_hang')">Lịch sử đơn hàng</p>
          <p style="padding:10px 20px;cursor:pointer;margin:0" onclick="doLogout()">Đăng xuất</p>
        </div>
      </div>
    </div>

    <img class="icon" src="https://storage.googleapis.com/tagjs-prod.appspot.com/v1/poABPRZHAM/w2j7j34y_expires_30_days.png" onclick="navigate('gio_hang')" title="Giỏ hàng">
  </div>
</div>

<!-- DANH MỤC SẢN PHẨM POPUP -->
<div id="productPopup" class="product-popup">
  <div class="product-popup-content">
    <img style="width:280px;height:377px;left:653px;top:45px;" src="https://placehold.co/280x377">
    <img style="width:281px;height:375px;left:1026px;top:47px;" src="https://placehold.co/281x375">
    <div class="popup-title">DANH MỤC SẢN PHẨM</div>
    <div class="popup-item" style="left:129px;top:94px;" onclick="navigate('ds_sp_loai','balo')"><span class="popup-arrow">▸</span> Balo</div>
    <div class="popup-item" style="left:129px;top:164px;" onclick="navigate('ds_sp_loai','tui_tote')"><span class="popup-arrow">▸</span> Túi Tote</div>
    <div class="popup-item" style="left:129px;top:234px;" onclick="navigate('ds_sp_loai','tui_deo_vai')"><span class="popup-arrow">▸</span> Túi Đeo Vai - Đeo Chéo</div>
    <div class="popup-item" style="left:129px;top:303px;" onclick="navigate('ds_sp_loai','vi')"><span class="popup-arrow">▸</span> Ví</div>
    <div class="popup-item" style="left:129px;top:373px;" onclick="navigate('ds_sp_noi_bat')"><span class="popup-arrow">▸</span> Sản phẩm bán chạy</div>
  </div>
</div>
`;
}

/* ---------- HTML FOOTER ---------- */
function buildFooter() {
  return `
<div class="footer">
  <div class="footer-container">
    <div class="footer-col">
      <div class="footer-logo">
        <img style="width:32px;height:32px;" src="https://storage.googleapis.com/tagjs-prod.appspot.com/v1/poABPRZHAM/r1tnys9b_expires_30_days.png">
        <span style="font-size:23px;font-weight:bold;">VINICHY</span>
      </div>
      <p class="footer-desc">Cửa hàng túi xách cao cấp hàng đầu Việt Nam. Chất lượng và phong cách là ưu tiên của chúng tôi.</p>
    </div>
    <div class="footer-col2">
      <h3>Liên kết nhanh</h3>
      
      <p style="color:#cfcfcf;cursor:pointer;" onclick="openProductFromFooter()">Sản phẩm</p>
      
    </div>
    <div class="footer-col">
      <h3>Liên hệ</h3>
      <div class="contact">
        <img src="https://storage.googleapis.com/tagjs-prod.appspot.com/v1/poABPRZHAM/9dxfxb2z_expires_30_days.png">
        <span>31 Lê Duy Lương, Cẩm Lệ, Đà Nẵng</span>
      </div>
      <div class="contact">
        <img src="https://storage.googleapis.com/tagjs-prod.appspot.com/v1/poABPRZHAM/2hoj7yve_expires_30_days.png">
        <span>+84 123 456 789</span>
      </div>
      <div class="contact">
        <img src="https://storage.googleapis.com/tagjs-prod.appspot.com/v1/poABPRZHAM/3am6xyah_expires_30_days.png">
        <span>vinichy@email.com</span>
      </div>
      <p style="color:#aaaaaa;">Giờ làm việc:</p>
      <p>Thứ 2 - Chủ nhật: 9:00 - 21:00</p>
    </div>
  </div>
  <div class="copyright">© 2026 VINICHY. Tất cả quyền được bảo lưu.</div>
</div>
`;
}

/* ---------- NAVIGATION ---------- */
const PAGE_MAP = {
  trang_chu:         'trang_chu.html',
  ds_sp_loai:        'xem_DS_SP_theo_loai.html',
  ds_sp_noi_bat:     'xem_DS_SP_theo_SP_noi_bat.html',
  chi_tiet_sp:       'xem_chi_tiet_SP.html',
  gio_hang:          'gio_hang.html',
  dat_hang:          'dat_hang.html',
  dat_hang_thanh_cong: 'dat_hang_thanh_cong.html',
  lich_su_giao_hang: 'lich_su_giao_hang.html',
  chi_tiet_don_hang: 'xem_chi_tiet_lich_su_DH.html',
};

function navigate(page, param) {
  const file = PAGE_MAP[page];
  if (!file) return;
  let url = file;
  if (param) url += '?cat=' + encodeURIComponent(param);
  window.location.href = url;
}

function handleSearch(e, input) {
  if (e.key === 'Enter' && input.value.trim()) {
    navigate('ds_sp_noi_bat');
  }
}

/* ---------- AUTH HELPERS ---------- */
function toggleLogin() {
  const isLoggedIn = localStorage.getItem('vinichy_logged_in');
  if (isLoggedIn) {
    const popup = document.getElementById('accountPopup');
    if (popup.classList.contains('active')) { closeAllPopups(); }
    else { closeAllPopups(); popup.classList.add('active'); }
  } else {
    const popup = document.getElementById('loginPopup');
    if (popup.classList.contains('active')) { closeAllPopups(); }
    else { closeAllPopups(); popup.classList.add('active'); }
  }
}

function showPopup(id) {
  closeAllPopups();
  document.getElementById(id).classList.add('active');
}

function closeAllPopups() {
  document.querySelectorAll('.auth-popup').forEach(p => p.classList.remove('active'));
}

function doLogin() {
  const email = document.getElementById('loginEmail').value.trim();
  const matKhau = document.getElementById('loginPassword').value;

  if (!email || !matKhau) {
    alert("Vui lòng nhập đầy đủ thông tin");
    return;
  }

  fetch('/api/auth/login', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ email, matKhau })
  })
  .then(res => res.json())
  .then(data => {
    if (data.success) {
      localStorage.setItem('vinichy_logged_in', '1');
      closeAllPopups();
      location.reload();
    } else {
      alert(data.message || "Đăng nhập thất bại");
    }
  })
  .catch(err => {
    console.error("Login error:", err);
    alert("Lỗi kết nối máy chủ");
  });
}

function doLogout() {
  localStorage.removeItem('vinichy_logged_in');
  closeAllPopups();
  navigate('trang_chu');
}

/* ---------- PRODUCT POPUP ---------- */
function toggleProductPopup() {
  const popup = document.getElementById('productPopup');
  popup.style.display = popup.style.display === 'block' ? 'none' : 'block';
}

function openProductFromFooter() {
  window.scrollTo({ top: 0, behavior: 'smooth' });
  setTimeout(() => {
    document.getElementById('productPopup').style.display = 'block';
  }, 400);
}

/* ---------- CLOSE POPUPS ON OUTSIDE CLICK ---------- */
window.addEventListener('click', function(e) {
  if (!e.target.closest('.user-menu')) closeAllPopups();
  if (!e.target.closest('#productPopup') && !e.target.closest('.menu')) {
    const pp = document.getElementById('productPopup');
    if (pp) pp.style.display = 'none';
  }
});

/* ---------- INIT ---------- */
function vinichyInit(activePage) {
  // Inject CSS
  const style = document.createElement('style');
  style.textContent = VINICHY_CSS;
  document.head.appendChild(style);

  // Inject header before #main-content
  const mainContent = document.getElementById('main-content');
  if (mainContent) {
    mainContent.insertAdjacentHTML('beforebegin', buildHeader(activePage));
  }

  // Inject footer replacing #footer-placeholder
  const footer = document.getElementById('footer-placeholder');
  if (footer) {
    footer.outerHTML = buildFooter();
  }
}

/* ---------- SCROLL TO CONTACT (Liên hệ) ---------- */
function scrollToContact() {
  // Nếu đang ở trang chủ hoặc trang có footer, cuộn đến phần liên hệ trong footer
  const footer = document.querySelector('.footer');
  if (footer) {
    footer.scrollIntoView({ behavior: 'smooth', block: 'start' });
  } else {
    // Nếu chưa render footer thì cuộn xuống cuối trang
    window.scrollTo({ top: document.body.scrollHeight, behavior: 'smooth' });
  }
}