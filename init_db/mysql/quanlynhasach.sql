

-- --------------------------------------------------------
-- drop database QUANLYNHASACH;
create database QUANLYNHASACH;
use QUANLYNHASACH;


CREATE TABLE TacGia(
	maTG int NOT NULL,
	tenTG Nvarchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



--
-- Cấu trúc bảng cho bảng CTHoaDon
--

CREATE TABLE CTHoaDon (
  maHD int(11) NOT NULL,
  maSP int(11) NOT NULL,
  soLuong int(11) NOT NULL,
  donGia int(11) NOT NULL,
  thanhTien int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;




-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng CTPhieuNhap
--

CREATE TABLE CTPhieuNhap (
  maPN int(11) NOT NULL,
  maSP int(11) NOT NULL,
  soLuong int(11) NOT NULL,
  donGia int(11) NOT NULL,
  thanhTien int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng CTPhieuNhap
--




-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng HoaDon
--

CREATE TABLE HoaDon (
  maHD int(11) NOT NULL,
  maKH int(11) NOT NULL,
  maNV int(11) NOT NULL,
  ngayLap date NOT NULL,
  tongTien int(11) NOT NULL,
  ghiChu varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng HoaDon
--


-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng KhachHang
--

CREATE TABLE KhachHang (
  maKH int(11) NOT NULL,
  Ho varchar(255) NOT NULL,
  Ten varchar(255) NOT NULL,
  gioiTinh text NOT NULL,
  tongChiTieu int(11) NOT NULL,
  tinhTrang int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng KhachHang
--



-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng KhuyenMai
--

CREATE TABLE KhuyenMai (
  maKM int(11) NOT NULL,
  tenKM varchar(255) NOT NULL,
  ngayBD date NOT NULL,
  ngayKT date NOT NULL,
  phanTramGiam int(11) not null,
  dieuKien int(11) not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng KhuyenMai
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng LoaiSanPham
--

CREATE TABLE LoaiSanPham (
  maLoai int(11) NOT NULL,
  tenLoai varchar(120) NOT NULL,
  maNCC int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng LoaiSanPham
--



-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng NhaCungCap
--

CREATE TABLE NhaCungCap (
  maNCC int(11) NOT NULL,
  tenNCC varchar(255) NOT NULL,
  diaChi varchar(255) NOT NULL,
  SDT int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng NhaCungCap
--


-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng NhanVien
--

CREATE TABLE NhanVien (
  maNV int(11) NOT NULL,
  Ho varchar(255) NOT NULL,
  Ten varchar(255) NOT NULL,
  gioiTinh text NOT NULL,
  chucVu varchar(100) NOT NULL,
  tinhTrang int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng NhanVien
--



-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng PhanQuyen
--

CREATE TABLE PhanQuyen (
  quyen varchar(255) NOT NULL,
  nhapHang int(11) NOT NULL,
  qlSanPham int(11) NOT NULL,
  qlNhanVien int(11) NOT NULL,
  qlKhachHang int(11) NOT NULL,
  thongKe int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng PhanQuyen
--

INSERT INTO PhanQuyen (quyen, nhapHang, qlSanPham, qlNhanVien, qlKhachHang, thongKe) VALUES
('Admin', 1, 1, 1, 1, 1),
('Nhân viên', 0, 0, 0, 1, 0),
('Quản lý', 1, 0, 1, 1, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng PhieuNhap
--

CREATE TABLE PhieuNhap (
  maPN int(11) NOT NULL,
  maNCC int(11) NOT NULL,
  maNV int(11) NOT NULL,
  ngayNhap date NOT NULL,
  tongTien int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng PhieuNhap
--




-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng SanPham
--

CREATE TABLE SanPham (
  maSP int(11) NOT NULL,
  tenSP varchar(255) NOT NULL,
  soLuong int(11) NOT NULL,
  donGia int(11),
  maLoai int(11) NOT NULL,
  hinhAnh text NOT NULL,
  maTG int(11) NOT NULL,
  moTa text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng SanPham
--

INSERT INTO SanPham (maSP, tenSP, soLuong,donGia,maLoai, hinhAnh,maTG, moTa) VALUES
(0, 'Bách khoa lịch sử thế giới', 0, 0, 7, 'uihuih', 9, 'Bách khoa Lịch sử thế giới đã vẽ nên bức tranh rộng lớn tái hiện cuộc sống của các dân tộc trước đây, giới thiệu về các nền văn minh nổi tiếng nhân loại, những nhân vật biến đổi thế giới,những sự kiện thay đổi tiến trình lịch sử .....'),
(1, 'Tắt Đèn', 20, 24200, 1, 'tatden.jpg', 1, 'Tác giả Ngô Tất Tố'),
(2, 'Tôi Thấy Hoa Vàng Trên Cỏ Xanh', 0, 0, 1, 'Tôi_thấy_hoa_vàng_trên_cỏ_xanh.jpg', 2, 'Tác giả Nguyễn Nhật Ánh'),
(3, 'Chí Phèo', 20, 24200, 2, 'chipheo.jpg', 3, 'Tác giả Nam Cao'),
(4, 'Lão Hạc', 30, 24200, 2, 'laohac.jpg', 3, 'Tác giả Nam Cao'),
(5, 'Những cuộc phiêu lưu của Sherlock Holmes', 30, 24200, 3, 'SherlockHolmes.jpg', 4, 'Tác giả Arthur Conan Doyle'),
(6, 'Nguồn Gốc Các Loài', 20, 24200, 4, 'nguon_goc_cac_loai.jpg', 5, 'Tác giả Charles Darwin'),
(7, 'Mắt Biếc', 10, 46200, 5, 'nguon_goc_cac_loai.jpg', 2, 'Tác giả Nguyễn Nhật Ánh'),
(8, 'IT', 20, 46200, 6, 'IT.jpg', 7, 'Tác giả Stephen King'),
(9, 'Dế Mèn phiêu lưu ký', 30, 24200, 7, 'de_men_phieu_luu_ky.jpg', 6, 'Tác giả Tô Hoài'),
(10, 'Your Name', 10, 46200, 8, 'your_name.jpg', 8, 'Tác giả Shinkai Makoto'),
(11, 'Thám tử lừng danh Conan-tập1', 30, 24200, 8, 'conantap1.jpg', 12, 'Kudo Shinichi là một cậu thám tử học sinh năng nổ với biệt tài suy luận có thể sánh ngang với Sherlock Holmes! Một ngày nọ, khi mải đuổi theo những kẻ khả nghi, cậu đã bị chúng cho uống một loại thuốc kì tạ khiến cơ thể bị teo nhỏ...'),
(12, 'Tư duy ngược', 20, 46200, 10, 'tuduynguoc.jpg', 13, 'Chúng ta thực sự có hạnh phúc không? Chúng ta có đang sống cuộc đời mình không? Chúng ta có dám dũng cảm chiến thắng mọi khuôn mẫu, định kiến, đi ngược đám đông để khẳng định bản sắc riêng của mình không?....'),
(13, 'Tư duy mở', 20, 46200, 10, 'TDM.jpg', 13, 'Con người đang sống trong thời đại công nghệ, khi mọi thứ thay đổi chóng mặt, điều đó đòi hỏi chúng ta phải linh hoạt trong cách tư duy để bắt kịp xu hướng toàn cầu. hay nói cách khác, chúng ta cần tư duy mở để đón nhận ....'),
(14, 'One Piece- tập 3', 60,26300, 8, 'oncepiecetap3.jpg', 10, 'One Piece kể về một cậu bé tên MonKey D.luffy, hiong buồm ra khơi trên chuyén hành trình tìm kho báu huyền thoại One Piece và trở thành vua hải tặc.....'),
(15, 'One Piece- tập 82', 20, 24200, 8, 'OncePiecetap82.jpg', 10, 'One Piece kể về một cậu bé tên MonKey D.luffy, hiong buồm ra khơi trên chuyén hành trình tìm kho báu huyền thoại One Piece và trở thành vua hải tặc.....'),
(16, 'Bách Khoa Lịch sử thế giới ', 20, 495000, 12, 'BKLSTG.jpg', 16, 'Bách khoa lịch sử thế giới đã vễ nên bức tranh rộng lớn tái hiện cuộc sống của dân tộc trước đây, giới thiệu về các nền văn minh nổi tiếng nhân loại,những nhân vật biến đổi thế giới, những sự kiện thay đổi tiến trình lịch sử'),
(17, 'Đắc nhân tâm', 10, 46200, 10, 'Đắc-nhân-tâm-3.jpg', 14, 'Đắc nhân tâm là cuốn sách của mọi thời đại và 1 hiện tượng đáng kinh ngạc trong ngành xuất bản Hoa Kỳ. Trong suốt nhiều thập kỉ tiếp theo.....'),
(18, 'Doraemon- tập 1', 0, 0, 8, 'doremontap1.jpg', 9, 'Doraemon xoay quanh 5 nhân vật Doraemon,Nobita,shizuka,Suneo và Jaian về những cuộc phiêu lưu đến tời cổ địa khủng long,....'),
(19, 'Doraemon', 30, 24200, 8, 'doremont12.jpg', 9, 'Doraemon xoay quanh 5 nhân vật Doraemon,Nobita,shizuka,Suneo và Jaian về những cuộc phiêu lưu đến tời cổ địa khủng long,....'),
(20, 'Về nhà ăn cơm', 30, 24200, 9, 'venhaancom.jpg', 15, 'Về nhà ăn cơm - tập hợp 45 công thức thuần chay mà tác giả đã dành rất nhiều thời gian chọn lựa kĩ càng');


-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng TaiKhoan
--

CREATE TABLE TaiKhoan (
  maNV int(11) NOT NULL,
  tenDN varchar(255) NOT NULL,
  matKhau varchar(255) NOT NULL,
  quyen varchar(255) NOT NULL,
  trangThai int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng TaiKhoan
--

INSERT INTO NhanVien (`maNV`, `Ho`, `Ten`, `gioiTinh`, `chucVu`, `tinhTrang`) VALUES
(0, 'Admin', 'Admin', 'Nam', 'Admin', 1),
(1, 'Trần Thị', 'Loan', 'Nam', 'Nhân viên', 1),
(2, 'Nguyễn Văn', 'D', 'Nam', 'Nhân viên', 1),
(3, 'Nguyễn Văn', 'B', 'Nam', 'Nhân viên', 1),
(4, 'Nguyễn Văn', 'C', 'Nam', 'Quản lý', 1),
(5, 'Nguyễn Văn', 'A', 'Nam', 'Quản lý', 1),
(6, 'Nguyễn Văn', 'K', 'Nam', 'Quản lý', 1),
(7,'Bùi Thị','H','Nữ','Nhân viên',1);

INSERT INTO TacGia(maTG,tenTG) VALUES
(1, 'Ngô Tất Tố'),
(2, 'Nguyễn Nhật Ánh'),
(3, 'Nam Cao'),
(4, 'Arthur Conan Doyle'),
(5, 'Charles Darwin'),
(6, 'Tô Hoài'),
(7, 'Stephen King'),
(8, 'Shinkai Makoto'),
(9, 'Fujiko F. Fujio'),
(10, 'Oda Eiichirō'),
(11, 'Roise Nguyễn'),
(12, 'Gosho Aoyama'),
(13, 'Nguyễn Anh Dũng'),
(14, 'Dale Carnegie'),
(15, 'Đức Nguyễn'),
(16, 'Jane Bingham, Fiona Chandler và Sam Taplin');

INSERT INTO TaiKhoan (maNV, tenDN, matKhau, quyen, trangThai) VALUES
(0, 'Admin', 'Admin', 'Admin', 1),
(1, 'nv01', 'nv01', 'Nhân viên', 1),
(2, 'nv02', 'nv02', 'Nhân viên', 1),
(3, 'nv03', 'nv03', 'Nhân viên', 1),
(4, 'ql04', 'ql04', 'Nhân viên', 1),
(5, 'ql05', 'ql05', 'Nhân viên', 1),
(6, 'ql06', 'ql06', 'Quản lý', 1),
(7, 'nv07', 'nv07', 'Nhân viên', 1);

INSERT INTO PhieuNhap (`maPN`, `maNCC`, `maNV`, `ngayNhap`, `tongTien`) VALUES
(9, 5, 0, '2024-05-16', 440000),
(10, 1, 0, '2024-05-20', 660000),
(11, 8, 0, '2024-05-24', 1120000),
(12, 3, 0, '2024-05-28', 5340000),
(13, 11, 0, '2024-05-31', 660000),
(14, 4, 0, '2024-06-04', 880000),
(15, 8, 0, '2024-06-07', 440000),
(16, 10, 0, '2024-06-10', 220000),
(17, 6, 0, '2024-06-13', 680000),
(18, 2, 0, '2024-06-20', 5340000),
(19, 8, 0, '2024-07-04', 460000),
(20, 5, 0, '2024-07-09', 880000),
(21, 7, 0, '2024-07-18', 440000),
(22, 9, 0, '2024-07-30', 220000),
(23, 3, 0, '2024-08-05', 840000),
(24, 1, 0, '2024-08-09', 1260000);

--
-- Đang đổ dữ liệu cho bảng CTHoaDon
--
INSERT INTO CTHoaDon (`maHD`, `maSP`, `soLuong`, `donGia`, `thanhTien`) VALUES
(8, 15, 1, 2530, 2),
(9, 16, 1, 495000, 495000),
(10, 14, 1, 25300, 25300),
(11, 16, 1, 495000, 495000),
(12, 3, 1, 24200, 24200),
(13, 12, 1, 46200, 46200),
(13, 15, 1, 25300, 25300),
(14, 8, 1, 46200, 46200),
(15, 3, 1, 24200, 24200),
(16, 1, 1, 24200, 24200),
(16, 14, 1, 25300, 25300),
(16, 15, 1, 25300, 25300),
(17, 14, 2, 25300, 50600),
(18, 4, 1, 24200, 24200),
(19, 16, 1, 495000, 495000),
(20, 15, 1, 25300, 25300),
(21, 16, 1, 495000, 495000),
(22, 5, 1, 24200, 24200),
(23, 10, 1, 46200, 46200),
(23, 15, 1, 25300, 25300),
(24, 17, 1, 46200, 46200),
(25, 6, 1, 24200, 24200),
(26, 11, 1, 24200, 24200),
(26, 14, 2, 2, 50600),
(27, 14, 1, 25300, 25300),
(27, 15, 1, 25300, 25300),
(28, 18, 1, 24200, 24200);

INSERT INTO CTPhieuNhap (`maPN`, `maSP`, `soLuong`, `donGia`, `thanhTien`) VALUES
(9, 3, 10, 22000, 220000),
(9, 4, 10, 22000, 220000),
(10, 1, 10, 22000, 220000),
(10, 9, 10, 22000, 220000),
(10, 14, 10, 22000, 220000),
(11, 14, 20, 23000, 460000),
(11, 19, 20, 22000, 440000),
(11, 20, 10, 22000, 220000),
(12, 12, 10, 42000, 420000),
(12, 13, 10, 42000, 420000),
(12, 16, 10, 450000, 4500000),
(13, 6, 10, 22000, 220000),
(13, 11, 10, 22000, 220000),
(13, 15, 10, 22000, 220000),
(14, 9, 20, 22000, 220000),
(14, 11, 10, 22000, 220000),
(14, 20, 10, 22000, 220000),
(15, 5, 20, 22000, 220000),
(16, 4, 10, 22000, 220000),
(17, 5, 10, 22000, 220000),
(17, 14, 10, 23000, 230000),
(17, 15, 10, 23000, 230000),
(18, 12, 10, 42000, 420000),
(18, 13, 10, 42000, 420000),
(18, 16, 10, 450000, 4500000),
(19, 14, 20, 23000, 460000),
(20, 6, 10, 22000, 220000),
(20, 11, 10, 22000, 220000),
(20, 19, 10, 22000, 220000),
(20, 20, 10, 22000, 220000),
(21, 3, 10, 22000, 220000),
(21, 4, 10, 22000, 220000),
(22, 1, 10, 22000, 220000),
(23, 7, 10, 42000, 420000),
(23, 17, 10, 42000, 420000),
(24, 8, 20, 42000, 420000),
(24, 10, 10, 42000, 420000);

INSERT INTO HoaDon (`maHD`, `maKH`, `maNV`, `ngayLap`, `tongTien`, `ghiChu`) VALUES
(8, 4, 1, '2024-07-10', 25300, 'Đã thanh toán'),
(9, 11, 3, '2024-07-12', 495000, 'Đã thanh toán'),
(10, 9, 3, '2024-07-12', 25300, 'Đã thanh toán'),
(11, 11, 5, '2024-07-15', 495000, 'Đã thanh toán'),
(12, 7, 5, '2024-07-24', 24200, 'Đã thanh toán'),
(13, 9, 4, '2024-09-24', 71500, 'Đã thanh toán'),
(14, 8, 2, '2024-07-26', 46200, 'Đã thanh toán'),
(15, 3, 3, '2024-07-26', 24200, 'Đã thanh toán'),
(16, 6, 7, '2024-07-28', 74800, 'Đã thanh toán'),
(17, 9, 5, '2024-07-31', 50600, 'Đã thanh toán'),
(18, 11, 6, '2024-08-01', 24200, 'Đã thanh toán'),
(19, 11, 3, '2024-07-12', 495000, 'Đã thanh toán'),
(20, 9, 3, '2024-07-12', 25300, 'Đã thanh toán'),
(21, 11, 5, '2024-07-15', 495000, 'Đã thanh toán'),
(22, 7, 5, '2024-07-24', 24200, 'Đã thanh toán'),
(23, 9, 4, '2024-09-24', 71500, 'Đã thanh toán'),
(24, 8, 2, '2024-07-26', 46200, 'Đã thanh toán'),
(25, 3, 3, '2024-07-26', 24200, 'Đã thanh toán'),
(26, 6, 7, '2024-07-28', 74800, 'Đã thanh toán'),
(27, 9, 5, '2024-07-31', 50600, 'Đã thanh toán'),
(28, 11, 6, '2024-08-01', 24200, 'Đã thanh toán');

INSERT INTO KhachHang (`maKH`, `Ho`, `Ten`, `gioiTinh`, `tongChiTieu`, `tinhTrang`) VALUES
(1, 'Nguyễn Văn', 'A', 'Nam', 24200, 1),
(2, 'Nguyễn Văn', 'B', 'Nam', 0, 1),
(3, 'Lê Thị', 'Na', 'Nữ', 48400, 1),
(4, 'Lê Thị', 'B', 'Nữ', 25300, 1),
(5, 'Lê Thị', 'Nở', 'Nữ', 0, 0),
(6,'Võ Thanh', 'Sang','Nam',149600,1),
(7, 'Lê Thị', 'Đào', 'Nữ', 48400, 1),
(8, 'Nguyễn ', 'Hương Giang', 'Nữ', 92400, 1),
(9, 'Trần', 'Thị Kim Ngân', 'Nữ', 294800, 1),
(10, 'Trần', 'Hoài Nhật', 'Nam', 0, 0),
(11, 'Vũ ', 'Tuấn Anh', 'Nam', 2028400, 1);


INSERT INTO LoaiSanPham (`maLoai`, `tenLoai`, `maNCC`) VALUES
(1, 'Truyện Dài', 1),
(2, 'Truyện Ngắn', 1),
(3, 'Trinh Thám', 3),
(4, 'Khoa Học', 2),
(5, 'Lãng Mạn', 3),
(6, 'Kinh Dị', 3),
(7, 'Thiếu Nhi', 4),
(8, 'Anime', 4),
(9, 'Dạy nấu ăn', 7),
(10, 'Hướng nghiệp & phát triển bản thân', 9),
(11, 'Khoa học công nghệ', 10),
(12, 'Lịch sử', 8),
(13, 'Tiểu sử, tự truyện', 5),
(14, 'Giả tưởng & Khoa học viễn tưởng', 11);

INSERT INTO NhaCungCap (`maNCC`, `tenNCC`, `diaChi`, `SDT`) VALUES
(1, 'Công ty cổ phần phát hành sách', '60-62 Lê Lợi, P.Bến Nghé, Q1, TPHCM', 123825798),
(2, 'Công ty cổ phần Sách & Thiết bị Giáo dục Trí Tuệ', '187 Giảng Võ, Q.Đống Đa, Hà Nội', 123515567),
(3, 'Công ty cổ phần TNHH văn hóa Việt Long', '14/35,Đào Duy Anh,P9,Q.Phú Nhuận,TPHCM', 123845708),
(4, 'Công ty TNHH Thương mại dịch vụ Quỳnh Phát', '232 Trần Thủ Độ,P.Phú Thạnh,Q.Tân Phú,TPHCM', 128105513),
(5, 'Nhà Sách Đất Việt', 'Hoàng Hoa Thám,P.13,Q.Tân Bình,TPHCM', 126297356),
(6, 'Nhà Sách An Dương Vương', '87/1 Trần Phú,P.4,Q.5,TPHCM', 123835139),
(7, 'Nhà Sách Vesta', '37,Xuân Thủy, Phường Thảo Điền, Quận 2,TPHCM', 128696594),
(8, 'Công Ty TNHH Minh Khai', '249 Nguyễn Thị Minh Khai,P.Nguyễn Cư Trinh,Q.1,TPHCM', 125783638),
(9, 'Nhà Sách Hoàng Long', '81 Hiệp Bình,P.Hiệp Bình Phước,Q.Thủ Đức,TPHCM', 128935367),
(10, 'Trung tâm Thông tin Sách Việt', 'Lê Đức Thọ,P.17,Q.Gò Vấp,TPHCM', 123842868),
(11, 'Công ty TNHH phát hành Sách Miền Nam', '9 Trần Thủ Độ,P.Phú Thạnh,Q.Tân Phú,TPHCM', 123963193);


--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng CTHoaDon
--
ALTER TABLE CTHoaDon
  ADD PRIMARY KEY (maHD,maSP),
  ADD KEY maSP (maSP);

--
-- Chỉ mục cho bảng CTPhieuNhap
--
ALTER TABLE CTPhieuNhap
  ADD PRIMARY KEY (maPN,maSP);

--
-- Chỉ mục cho bảng HoaDon
--
ALTER TABLE HoaDon
  ADD PRIMARY KEY (maHD),
  ADD KEY maKH (maKH),
  ADD KEY maNV (maNV);

--
-- Chỉ mục cho bảng KhachHang
--
ALTER TABLE KhachHang
  ADD PRIMARY KEY (maKH);


ALTER TABLE TacGia
  ADD PRIMARY KEY (maTG);

--
-- Chỉ mục cho bảng KhuyenMai
--
ALTER TABLE KhuyenMai
  ADD PRIMARY KEY (maKM);

--
-- Chỉ mục cho bảng LoaiSanPham
--
ALTER TABLE LoaiSanPham
  ADD PRIMARY KEY (maLoai),
  ADD KEY maNCC (maNCC);

--
-- Chỉ mục cho bảng NhaCungCap
--
ALTER TABLE NhaCungCap
  ADD PRIMARY KEY (maNCC);

--
-- Chỉ mục cho bảng NhanVien
--
ALTER TABLE NhanVien
  ADD PRIMARY KEY (maNV);
  
--
-- Chỉ mục cho bảng PhanQuyen
--
ALTER TABLE PhanQuyen
  ADD PRIMARY KEY (quyen);

--
-- Chỉ mục cho bảng PhieuNhap
--
ALTER TABLE PhieuNhap
  ADD PRIMARY KEY (maPN),
  ADD KEY maNV (maNV),
  ADD KEY maNCC (maNCC);

--
-- Chỉ mục cho bảng SanPham
--
ALTER TABLE SanPham
  ADD PRIMARY KEY (maSP),
  ADD KEY sanpham_ibfk_1 (maLoai),
  ADD KEY sanpham_ibfk_2 (maTG);
--
-- Chỉ mục cho bảng TaiKhoan
--
ALTER TABLE TaiKhoan
  ADD PRIMARY KEY (maNV),
  ADD KEY quyen (quyen);

--
-- AUTO_INCREMENT cho bảng HoaDon
--
ALTER TABLE HoaDon
  MODIFY maHD int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT cho bảng LoaiSanPham
--
ALTER TABLE LoaiSanPham
  MODIFY maLoai int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT cho bảng NhaCungCap
--
ALTER TABLE NhaCungCap
  MODIFY maNCC int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

ALTER TABLE TacGia
  MODIFY maTG int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

ALTER TABLE NhanVien
  MODIFY maNV int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

ALTER TABLE KhachHang
  MODIFY maKH int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT cho bảng SanPham
--
ALTER TABLE SanPham
  MODIFY maSP int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=145;

ALTER TABLE PhieuNhap
  MODIFY maPN int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

ALTER TABLE KhuyenMai
  MODIFY maKM int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng CTHoaDon
--
ALTER TABLE CTHoaDon
  ADD CONSTRAINT cthoadon_ibfk_1 FOREIGN KEY (maHD) REFERENCES HoaDon (maHD) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT cthoadon_ibfk_2 FOREIGN KEY (maSP) REFERENCES SanPham (maSP) ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng CTPhieuNhap
--
ALTER TABLE CTPhieuNhap
  ADD CONSTRAINT ctphieunhap_ibfk_1 FOREIGN KEY (maPN) REFERENCES PhieuNhap (maPN) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT ctphieunhap_ibfk_2 FOREIGN KEY (maSP) REFERENCES SanPham (maSP) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng HoaDon
--
ALTER TABLE HoaDon
  ADD CONSTRAINT hoadon_ibfk_1 FOREIGN KEY (maKH) REFERENCES KhachHang (maKH) ON UPDATE CASCADE,
  ADD CONSTRAINT hoadon_ibfk_2 FOREIGN KEY (maNV) REFERENCES NhanVien (maNV) ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng LoaiSanPham
--
ALTER TABLE LoaiSanPham
  ADD CONSTRAINT loaisanpham_ibfk_1 FOREIGN KEY (maNCC) REFERENCES NhaCungCap (maNCC);

--
-- Các ràng buộc cho bảng PhieuNhap
--
ALTER TABLE PhieuNhap
  ADD CONSTRAINT phieunhap_ibfk_1 FOREIGN KEY (maNV) REFERENCES NhanVien (maNV),
  ADD CONSTRAINT phieunhap_ibfk_2 FOREIGN KEY (maNCC) REFERENCES NhaCungCap (maNCC) ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng SanPham
--
ALTER TABLE SanPham
  ADD CONSTRAINT sanpham_ibfk_1 FOREIGN KEY (maLoai) REFERENCES LoaiSanPham (maLoai) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT sanpham_ibfk_2 FOREIGN KEY (maTG) REFERENCES TacGia (maTG) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng TaiKhoan
--
ALTER TABLE TaiKhoan
  ADD CONSTRAINT taikhoan_ibfk_1 FOREIGN KEY (maNV) REFERENCES NhanVien (maNV) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT taikhoan_ibfk_2 FOREIGN KEY (quyen) REFERENCES PhanQuyen (quyen) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;
