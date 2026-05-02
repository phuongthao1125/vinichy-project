// main.js - VINICHY Frontend Logic

/* ===== PRODUCT POPUP ===== */
function toggleProductPopup() {
  const popup = document.getElementById('productPopup');
  if (popup) popup.style.display = popup.style.display === 'block' ? 'none' : 'block';
}

function openProductFromFooter() {
  window.scrollTo({ top: 0, behavior: 'smooth' });
  setTimeout(() => {
    const pp = document.getElementById('productPopup');
    if (pp) pp.style.display = 'block';
  }, 400);
}

/* ===== AUTH POPUP ===== */
function showPopup(id) {
  closeAllAuthPopups();
  const el = document.getElementById(id);
  if (el) {
    el.classList.add('active');
  } else {
    console.error("Popup not found:", id);
  }
}

function closeAllAuthPopups() {
  document.querySelectorAll('.auth-popup').forEach(p => p.classList.remove('active'));
}

function toggleLogin() {
  const loggedIn = (typeof window.isLoggedIn !== 'undefined') ? window.isLoggedIn : (document.body.getAttribute('data-logged-in') === 'true');
  console.log("toggleLogin called, loggedIn state:", loggedIn);
  
  if (loggedIn) {
    showPopup('accountPopup');
  } else {
    showPopup('loginPopup');
  }
}

function togglePopup(id) {
  const popup = document.getElementById(id);
  if (!popup) return;
  if (popup.classList.contains('active')) {
    closeAllAuthPopups();
  } else {
    closeAllAuthPopups();
    popup.classList.add('active');
  }
}



/* ===== AUTH API ===== */
// Chuyển sang dùng các hàm trong header.html để đồng bộ logic

/* ===== CART ===== */

/* ===== CART ===== */
// Global popup helpers
function siOpen(id) {
  const el = document.getElementById(id);
  if (el) el.classList.add('active');
}
function siClose(id) {
  const el = document.getElementById(id);
  if (el) el.classList.remove('active');
}

async function addToCart(sanPhamCTId, soLuong, productInfo) {
  console.log("Adding to cart:", { sanPhamCTId, soLuong });
  if (!sanPhamCTId) {
    showToast('Có lỗi xảy ra: Không tìm thấy biến thể sản phẩm');
    return;
  }
  soLuong = soLuong || getCurrentQty();
  try {
    const res = await fetch('/api/cart/add', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ sanPhamCTId, soLuong })
    });
    
    if (res.status === 401) {
      showPopup('loginPopup');
      return;
    }

    const data = await res.json();
    if (data.success) {
      updateCartBadge(data.cartCount);
      if (productInfo) {
        showCartSuccessPopup(productInfo);
      } else {
        showToast('✓ Đã thêm vào giỏ hàng!');
      }
    } else {
      showToast(data.message || 'Có lỗi xảy ra');
    }
  } catch(e) {
    console.error("Cart error:", e);
    // Nếu là lỗi parse JSON từ response không phải JSON (VD: trang lỗi 500 HTML)
    showToast('Lỗi hệ thống. Vui lòng thử lại sau.');
  }
}

function showCartSuccessPopup(info) {
  const imgEl = document.getElementById('successProductImg');
  const nameEl = document.getElementById('successProductName');
  const colorEl = document.getElementById('successProductColor');
  const priceEl = document.getElementById('successProductPrice');
  
  if (imgEl && info.img) imgEl.src = info.img;
  if (nameEl) nameEl.textContent = info.name || 'Sản phẩm';
  if (colorEl) colorEl.textContent = info.color || '';
  if (priceEl) priceEl.textContent = info.price || '';
  
  window.siOpen('cartSuccessPopup');
}

function getCurrentQty() {
  const qtyEl = document.getElementById('qtyInput');
  return qtyEl ? parseInt(qtyEl.value) || 1 : 1;
}

function updateCartBadge(count) {
  const badge = document.getElementById('cartBadge');
  if (badge && count > 0) {
    badge.textContent = count;
    badge.style.display = 'inline-block';
  }
}

/* ===== QUANTITY CONTROL ===== */
function changeQty(delta) {
  const qtyEl = document.getElementById('qtyInput');
  if (!qtyEl) return;
  let val = parseInt(qtyEl.value) + delta;
  
  if (delta > 0 && typeof currentStock !== 'undefined' && val > currentStock) {
    showToast('Số lượng vượt ngưỡng tồn kho');
    val = currentStock;
  }

  if (val < 1) val = 1;
  qtyEl.value = val;
}

function validateQtyInput(input, stock) {
  let val = parseInt(input.value);
  if (isNaN(val) || val < 1) {
    input.value = 1;
    return;
  }
  if (typeof stock !== 'undefined' && val > stock) {
    showToast('Số lượng vượt ngưỡng tồn kho');
    input.value = stock;
    return;
  }
}

/* ===== PRODUCT DETAIL - COLOR SELECT ===== */
let selectedSanPhamCTId = null;
let currentStock = 999;

function selectColor(btn, sanPhamCTId) {
  document.querySelectorAll('.color-btn').forEach(b => b.classList.remove('active'));
  btn.classList.add('active');
  selectedSanPhamCTId = sanPhamCTId;
  currentStock = parseInt(btn.getAttribute('data-stock')) || 0;

  // Update SKU
  const sku = btn.getAttribute('data-sku');
  const skuEl = document.querySelector('.sku span');
  if (skuEl && sku) skuEl.textContent = sku;

  // Update Main Image
  const imgUrl = btn.getAttribute('data-img');
  const mainImg = document.getElementById('mainProductImg');
  if (mainImg && imgUrl) mainImg.src = imgUrl;

  // Update Cart Button UI based on stock
  const addCartBtn = document.querySelector('.btn-add-cart');
  const qtyInput = document.getElementById('qtyInput');
  const qtyBtns = document.querySelectorAll('.qty-control .qty-btn');

  if (currentStock <= 0) {
      if (addCartBtn) {
          addCartBtn.textContent = 'ĐÃ HẾT HÀNG';
          addCartBtn.classList.add('disabled');
          addCartBtn.disabled = true;
      }
      if (qtyInput) qtyInput.disabled = true;
      qtyBtns.forEach(b => b.disabled = true);
  } else {
      if (addCartBtn) {
          addCartBtn.textContent = 'THÊM VÀO GIỎ HÀNG';
          addCartBtn.classList.remove('disabled');
          addCartBtn.disabled = false;
      }
      if (qtyInput) qtyInput.disabled = false;
      qtyBtns.forEach(b => b.disabled = false);
  }
}

function handleAddToCart() {
  if (!selectedSanPhamCTId) {
    showToast('Vui lòng chọn màu sắc');
    return;
  }
  
  const qty = getCurrentQty();
  if (qty > currentStock) {
    showToast('Số lượng vượt ngưỡng tồn kho');
    return;
  }

  const activeBtn = document.querySelector('.color-btn.active');
  const productInfo = {
    name: document.querySelector('.detail-info h1')?.textContent || '',
    img: activeBtn?.getAttribute('data-img') || document.getElementById('mainProductImg')?.src,
    color: activeBtn?.getAttribute('title') || '',
    price: document.querySelector('.detail-price')?.textContent || ''
  };

  addToCart(selectedSanPhamCTId, qty, productInfo);
}

/* ===== TOAST ===== */
let toastTimer;
function showToast(msg) {
  const toast = document.getElementById('toastNotification');
  if (!toast) {
    alert(msg);
    return;
  }
  toast.textContent = msg;
  toast.classList.add('show');
  clearTimeout(toastTimer);
  toastTimer = setTimeout(() => toast.classList.remove('show'), 3000);
}

/* ===== SEARCH ===== */
function handleSearch(event, input) {
  if (event.key === 'Enter' && input.value.trim()) {
    location.href = '/san-pham?q=' + encodeURIComponent(input.value.trim());
  }
}

/* ===== DETAIL - IMAGE GALLERY ===== */
function switchImage(src, thumbEl) {
  const mainImg = document.getElementById('mainProductImg');
  if (mainImg) mainImg.src = src;
  document.querySelectorAll('.detail-thumb').forEach(t => t.classList.remove('active'));
  if (thumbEl) thumbEl.classList.add('active');
}

/* ===== CLOSE POPUPS ON OUTSIDE CLICK ===== */
window.addEventListener('click', function(e) {
  // Chỉ đóng popup nếu click vào khoảng trống (không phải là nút, link, hoặc select)
//   if (e.target.tagName !== 'BUTTON' && e.target.tagName !== 'A' && e.target.tagName !== 'SELECT' && e.target.tagName !== 'INPUT') {
//       if (!e.target.closest('.user-menu')) closeAllAuthPopups();
      
//       const pp = document.getElementById('productPopup');
//       if (pp && !e.target.closest('#productPopup') && !e.target.closest('.menu')) {
//           pp.style.display = 'none';
//       }
//   }
// });
  if (!e.target.closest('button') && 
      !e.target.closest('a') && 
      !e.target.closest('select') && 
      !e.target.closest('input')) {

      if (!e.target.closest('.user-menu')) closeAllAuthPopups();
      
      const pp = document.getElementById('productPopup');
      if (pp && !e.target.closest('#productPopup') && !e.target.closest('.menu')) {
          pp.style.display = 'none';
      }
  }
});

/* ===== SCROLL TO CONTACT ===== */
function scrollToContact() {
  const footer = document.querySelector('.footer');
  if (footer) footer.scrollIntoView({ behavior: 'smooth', block: 'start' });
  else window.scrollTo({ top: document.body.scrollHeight, behavior: 'smooth' });
}

/* ===== LOAD CART COUNT ON INIT ===== */
async function loadCartCount() {
  try {
    const res = await fetch('/api/cart');
    if (res.ok) {
      const data = await res.json();
      updateCartBadge(data.count || 0);
    }
  } catch(e) {}
}

// Kiểm tra đồng bộ trạng thái đăng nhập để tránh cache trình duyệt khi nhấn Back
async function checkLoginSync() {
    const bodyLoggedIn = document.body.dataset.loggedIn === 'true';
    
    try {
        const res = await fetch('/api/auth/me');
        
        // Nếu server báo 401 (chưa login)
        if (res.status === 401) {
            if (bodyLoggedIn) {
                console.warn("Session expired or logged out, redirecting to login...");
                // Chuyển hướng về trang chủ và yêu cầu hiển thị popup đăng nhập
                location.replace('/?showLogin=true');
            }
            return;
        }

        const data = await res.json();
        // Nếu server báo đã login nhưng UI vẫn hiển thị là chưa login (do cache trang công khai)
        if (data.loggedIn && !bodyLoggedIn) {
            location.reload();
        }
    } catch (e) {}
}

// Xử lý bfcache (Back-Forward Cache) của trình duyệt
window.addEventListener('pageshow', function(event) {
    // event.persisted = true nếu trang được lấy từ bộ nhớ đệm (khi nhấn Back/Forward)
    if (event.persisted) {
        checkLoginSync();
    }
});

document.addEventListener('DOMContentLoaded', () => {
  // Kiểm tra tham số URL để hiển thị popup đăng nhập nếu cần
  const urlParams = new URLSearchParams(window.location.search);
  if (urlParams.get('showLogin') === 'true') {
      // Xóa tham số khỏi URL mà không load lại trang
      const newUrl = window.location.pathname;
      window.history.replaceState({}, document.title, newUrl);
      showPopup('loginPopup');
  }

  checkLoginSync();
  loadCartCount();
  
  const loginEmail = document.getElementById('loginEmail');
  const loginPass = document.getElementById('loginPassword');
  if (loginEmail) loginEmail.addEventListener('input', () => loginEmail.style.borderColor = '#ccc');
  if (loginPass) loginPass.addEventListener('input', () => loginPass.style.borderColor = '#ccc');
});

// ==========================================
// XỬ LÝ TRANG GIỎ HÀNG
// ==========================================

// 1. Hàm tăng/giảm số lượng và xóa sản phẩm
// Thay thế toàn bộ hàm updateCartQuantity cũ bằng hàm này
async function updateCartQuantity(buttonElement, changeValue) {
    
    const cartItem = buttonElement.closest('.cart-item');
    const sanPhamCTId = cartItem.getAttribute('data-id'); // Lấy maSP_CT từ data-id
    const inputField = cartItem.querySelector('.qty-input');
    
    let currentQty = parseInt(inputField.value);
    let newQty = currentQty + changeValue;

    if (newQty < 1) {
        newQty = 0;
    }

    try {        
        const response = await fetch('/api/cart/update-quantity', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ sanPhamCTId: parseInt(sanPhamCTId), soLuong: newQty })
        });
                
        const data = await response.json();
        
        if (data.success) {
            window.location.reload();
        } else {
            showToast(data.message || "Không thể cập nhật số lượng!");
            setTimeout(() => window.location.reload(), 2000);
        }
    } catch (error) {
        showToast("Lỗi kết nối. Vui lòng thử lại!");
    }
}

async function validateCartQtyInput(input, stock) {
    let val = parseInt(input.value);
    const cartItem = input.closest('.cart-item');
    const sanPhamCTId = cartItem.getAttribute('data-id');
    let showedToast = false;
    
    if (isNaN(val) || val < 1) {
        window.location.reload(); // Revert to old value by reloading
        return;
    }
    
    if (val > stock) {
        showToast('Số lượng vượt ngưỡng tồn kho');
        val = stock;
        input.value = stock;
        showedToast = true;
    }

    try {
        const response = await fetch('/api/cart/update-quantity', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ sanPhamCTId: parseInt(sanPhamCTId), soLuong: val })
        });
        const data = await response.json();
        if (data.success) {
            if (showedToast) {
                setTimeout(() => window.location.reload(), 2000);
            } else {
                window.location.reload();
            }
        } else {
            showToast(data.message || "Không thể cập nhật số lượng!");
            setTimeout(() => window.location.reload(), 2000);
        }
    } catch (error) {
        showToast("Lỗi kết nối. Vui lòng thử lại!");
    }
}

// 2. Hàm thay đổi màu sắc (biến thể)
async function updateCartVariant(selectElement) {
    const cartItem = selectElement.closest('.cart-item');
    const sanPhamCTId = cartItem.getAttribute('data-id'); // Lấy maSP_CT từ data-id
    const newSanPhamCTId = selectElement.value; // Lấy ID của màu sắc mới được chọn

    try {
        const response = await fetch('/api/cart/update-variant', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ sanPhamCTId: parseInt(sanPhamCTId), newSanPhamCTId: parseInt(newSanPhamCTId) })
        });
        
        const data = await response.json();
        if (data.success) {
            window.location.reload();
        } else {
            showToast(data.message || "Không thể đổi màu sắc!");
        }
    } catch (error) {
        showToast("Lỗi kết nối khi đổi màu sắc!");
    }
}

function handleCartClick(event) {
  event.preventDefault();
  const loggedIn = (typeof window.isLoggedIn !== 'undefined') ? window.isLoggedIn : (document.body.getAttribute('data-logged-in') === 'true');
  if (loggedIn) {
    window.location.href = '/gio-hang';
  } else {
    showPopup('loginPopup');
  }
}

async function huyDonHang(id) {
    if (!confirm("Bạn có chắc chắn muốn hủy đơn hàng này?")) return;
    try {
        const res = await fetch('/api/don-hang/huy/' + id, { method: 'POST' });
        const data = await res.json();
        if (data.success) {
            showToast("✓ Hủy đơn hàng thành công!");
            setTimeout(() => location.reload(), 1000);
        } else {
            showToast(data.message || "Lỗi khi hủy đơn hàng");
        }
    } catch (e) {
        showToast("Lỗi kết nối. Vui lòng thử lại!");
    }
}
