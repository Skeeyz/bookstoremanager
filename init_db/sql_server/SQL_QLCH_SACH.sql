create database QUANLYNHASACH
use QUANLYNHASACH
use master
--drop database QUANLYNHASACH
/*Tao table*/


CREATE TABLE khachhang (
  maKH int NOT NULL,
  Ho Nvarchar(255) NOT NULL,
  Ten Nvarchar(255) NOT NULL,
  gioiTinh Ntext NOT NULL,
  tongChiTieu int NOT NULL,
  tinhTrang int NOT NULL
)

CREATE TABLE nhanvien (
  maNV int NOT NULL,
  Ho Nvarchar(255) NOT NULL,
  Ten Nvarchar(255) NOT NULL,
  gioiTinh Ntext NOT NULL,
  chucVu Nvarchar(100) NOT NULL,
  tinhTrang int NOT NULL
)

CREATE TABLE tacgia(
	maTG int NOT NULL,
	tenTG Nvarchar(255)
)

CREATE TABLE sanpham (
  maSP int NOT NULL,
  tenSP Nvarchar(255) NOT NULL,
  soLuong int NOT NULL,
  donGia int NOT NULL,
  maLoai int NOT NULL,
  hinhAnh text NOT NULL,
  maTG int NOT NULL,
  moTa Nvarchar(255) NOT NULL
)

CREATE TABLE nhacungcap (
  maNCC int NOT NULL,
  tenNCC Nvarchar(255) NOT NULL,
  diachi Nvarchar(255) NOT NULL,
  SDT int NOT NULL
)

CREATE TABLE loaisanpham (
  maLoai int NOT NULL,
  tenLoai Nvarchar(120) NOT NULL,
  maNCC int NOT NULL,
--  foreign key (maNCC) references nhacungcap(maNCC)
)

CREATE TABLE phanquyen (
  quyen Nvarchar(255) NOT NULL,
  NHAPHANG int NOT NULL,
  QLSANPHAM int NOT NULL,
  QLNHANVIEN int NOT NULL,
  QLKHACHHANG int NOT NULL,
  THONGKE int NOT NULL
)

CREATE TABLE taikhoan (
  maNV int NOT NULL,
  tenDN varchar(255) NOT NULL,
  matKhau varchar(255) NOT NULL,
  quyen Nvarchar(255) NOT NULL,
  trangThai int NOT NULL
--  foreign key (quyen) references phanquyen(quyen)
)

CREATE TABLE khuyenmai (
  maKM int NOT NULL,
  tenKM Nvarchar(255) NOT NULL,
  ngayBD date NOT NULL,
  ngayKT date NOT NULL
)

CREATE TABLE phieunhap (
  maPN int NOT NULL,
  maNCC int NOT NULL,
  maNV int NOT NULL,
  ngayNhap date NOT NULL,
  tongTien int NOT NULL,
--  foreign key (maNCC) references nhacungcap(maNCC),
--  foreign key (maNV) references nhanvien(maNV)
)

CREATE TABLE hoadon (
  maHD int NOT NULL,
  maKH int NOT NULL,
  maNV int NOT NULL,
  ngayLap date NOT NULL,
  tongTien int NOT NULL,
  ghiChu Nvarchar(255) NOT NULL
--  foreign key (maKH) references khachhang(maKH),
--  foreign key (maNV) references nhanvien(maNV)
)


CREATE TABLE cthoadon (
  maHD int NOT NULL,
  MaSP int NOT NULL,
  soLuong int  NOT NULL,
  donGia int  NOT NULL,
  thanhTien int  NOT NULL,
--  primary key(maHD,MaSP),
--  foreign key (MaSP) references sanpham(maSP)
)

CREATE TABLE ctphieunhap (
  maPN int NOT NULL,
  maSP int NOT NULL,
  soLuong int NOT NULL,
  donGia int NOT NULL,
  thanhTien int NOT NULL,
--  primary key (maPN,maSP),
--  foreign key (maSP) references sanpham(maSP)
)

CREATE TABLE ctkhuyenmai (
  maKM int NOT NULL,
  maSP int NOT NULL,
  phanTramGiam int NOT NULL,
  dieuKien int NOT NULL,
--  primary key (maKM,maSP),
--  foreign key (maSP) references sanpham(maSP)
)


-- Chỉ mục cho bảng `cthoadon`
--
ALTER TABLE cthoadon
  ADD PRIMARY KEY (maHD,MaSP)
CREATE INDEX MaSP on cthoadon(MaSP)

--
-- Chỉ mục cho bảng `ctkhuyenmai`
--
ALTER TABLE ctkhuyenmai
  ADD PRIMARY KEY (maKM,maSP)
CREATE INDEX maSP ON ctkhuyenmai(maSP)

--
-- Chỉ mục cho bảng `ctphieunhap`
--
ALTER TABLE ctphieunhap
  ADD PRIMARY KEY (maPN,maSP);

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE hoadon
  ADD PRIMARY KEY (maHD)
CREATE INDEX maKH on hoadon(maKH)
CREATE INDEX maNV on hoadon(maNV)

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE khachhang
  ADD PRIMARY KEY (maKH);

--
-- Chỉ mục cho bảng `khuyenmai`
--
ALTER TABLE khuyenmai
  ADD PRIMARY KEY (maKM);

--
-- Chỉ mục cho bảng `loaisanpham`
--
ALTER TABLE loaisanpham
  ADD PRIMARY KEY (maLoai)
CREATE INDEX maNCC on loaisanpham(maNCC)

--
-- Chỉ mục cho bảng `nhacungcap`
--
ALTER TABLE nhacungcap
  ADD PRIMARY KEY (maNCC);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE nhanvien
  ADD PRIMARY KEY (maNV);

--
-- Chỉ mục cho bảng `phanquyen`
--
ALTER TABLE phanquyen
  ADD PRIMARY KEY (quyen);

--
-- Chỉ mục cho bảng `phieunhap`
--
ALTER TABLE phieunhap
  ADD PRIMARY KEY (maPN)
CREATE INDEX maNV on phieunhap(maNV)
CREATE INDEX maNCC on phieunhap(maNCC)

--
-- Chỉ mục cho bảng `tacgia`
--
ALTER TABLE tacgia
	ADD PRIMARY KEY(maTG)
--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE sanpham
  ADD PRIMARY KEY (MaSP)
CREATE INDEX sanpham_ibfk_1 on sanpham(maLoai)
CREATE INDEX sanpham_ibfk_2 on sanpham(maTG)

--
-- Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE taikhoan
  ADD PRIMARY KEY (maNV)
CREATE INDEX quyen on taikhoan(quyen);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `hoadon`
--
CREATE SEQUENCE hoadon_sequence
START WITH 5
INCREMENT BY 1;

--
-- AUTO_INCREMENT cho bảng `loaisanpham`
--
CREATE SEQUENCE loaisanpham_sequence
    START WITH 9
    INCREMENT BY 1;

--
-- AUTO_INCREMENT cho bảng `loaisanpham`
--
CREATE SEQUENCE tacgia_sequence
    START WITH 5
    INCREMENT BY 1;

--
-- AUTO_INCREMENT cho bảng `nhacungcap`
--
CREATE SEQUENCE nhacungcap_sequence
START WITH 5
INCREMENT BY 1;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
CREATE SEQUENCE sanpham_sequence
START WITH 145
INCREMENT BY 1;

--
-- AUTO_INCREMENT cho bảng `phieunhap`
--
CREATE SEQUENCE phieunhap_sequence
START WITH 3
INCREMENT BY 1;
--
--
--Cac rang buoc khoa ngoai

ALTER TABLE cthoadon
  ADD CONSTRAINT cthoadon_ibfk_1 FOREIGN KEY (maHD) REFERENCES hoadon (maHD) ON DELETE CASCADE ON UPDATE CASCADE
ALTER TABLE cthoadon
  ADD CONSTRAINT cthoadon_ibfk_2 FOREIGN KEY (MaSP) REFERENCES sanpham (MaSP) ON UPDATE CASCADE

--
-- Các ràng buộc cho bảng `ctkhuyenmai`
--
ALTER TABLE ctkhuyenmai
  ADD CONSTRAINT ctkhuyenmai_ibfk_1 FOREIGN KEY (maKM) REFERENCES khuyenmai (maKM) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `ctphieunhap`
--
ALTER TABLE ctphieunhap
  ADD CONSTRAINT ctphieunhap_ibfk_1 FOREIGN KEY (maPN) REFERENCES phieunhap (maPN) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `hoadon`
--
ALTER TABLE hoadon
  ADD CONSTRAINT hoadon_ibfk_1 FOREIGN KEY (maKH) REFERENCES khachhang (maKH)
ALTER TABLE hoadon
  ADD CONSTRAINT hoadon_ibfk_2 FOREIGN KEY (maNV) REFERENCES nhanvien (maNV) ON UPDATE CASCADE

--
-- Các ràng buộc cho bảng `khuyenmai`
--
ALTER TABLE ctkhuyenmai
  ADD CONSTRAINT khuyenmai_ibfk_1 FOREIGN KEY (maKM) REFERENCES khuyenmai (maKM);

--
-- Các ràng buộc cho bảng `loaisanpham`
--
ALTER TABLE loaisanpham
  ADD CONSTRAINT loaisanpham_ibfk_1 FOREIGN KEY (maNCC) REFERENCES nhacungcap (maNCC);

--
-- Các ràng buộc cho bảng `phieunhap`
--
ALTER TABLE phieunhap
  ADD CONSTRAINT phieunhap_ibfk_1 FOREIGN KEY (maNV) REFERENCES nhanvien (maNV)
ALTER TABLE phieunhap
  ADD CONSTRAINT phieunhap_ibfk_2 FOREIGN KEY (maNCC) REFERENCES nhacungcap (maNCC) ON UPDATE CASCADE

--
-- Các ràng buộc cho bảng `sanpham`
--
ALTER TABLE sanpham
  ADD CONSTRAINT sanpham_ibfk_1 FOREIGN KEY (maLoai) REFERENCES loaisanpham (maLoai) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE sanpham
  ADD CONSTRAINT sanpham_ibfk_2 FOREIGN KEY (maTG) REFERENCES tacgia (maTG) ON DELETE CASCADE ON UPDATE CASCADE;
--
-- Các ràng buộc cho bảng `taikhoan`
--
ALTER TABLE taikhoan
  ADD CONSTRAINT taikhoan_ibfk_1 FOREIGN KEY (maNV) REFERENCES nhanvien (maNV) ON DELETE CASCADE ON UPDATE CASCADE
ALTER TABLE taikhoan
  ADD CONSTRAINT taikhoan_ibfk_2 FOREIGN KEY (quyen) REFERENCES phanquyen (quyen) ON DELETE CASCADE ON UPDATE CASCADE

--them du lieu cho cac bang:

INSERT INTO nhacungcap VALUES
(1, 'NCC1', 'TP.HCM', 1281015513),
(2, 'NCC2', 'Hà Nội', 1281015513),
(3, 'NCC3', '', 1281015513),
(4, 'NCC4', 'CCC', 1281015513);

INSERT INTO loaisanpham VALUES
(1, N'Truyện Dài', 1),
(2, N'Truyện Ngắn', 1),
(3, N'Trinh Thám', 3),
(4, N'Khoa Học', 2),
(5, N'Lãng Mạn', 3),
(6, N'Kinh Dị', 3),
(7, N'Thiếu Nhi', 4),
(8, N'Anime', 4);

INSERT INTO tacgia VALUES
(1, N'Ngô Tất Tố'),
(2, N'Nguyễn Nhật Ánh'),
(3, N'Nam Cao'),
(4, N'Arthur Conan Doyle'),
(5, N'Charles Darwin'),
(6, N'Tô Hoài'),
(7, N'Stephen King'),
(8, N'Shinkai Makoto');

INSERT INTO sanpham VALUES
(1, N'Tắt Đèn', 100, 25000, 1, N'tatden.jpg',1, N'Tác giả Ngô Tất Tố'),
(2, N'Tôi Thấy Hoa Vàng Trên Cỏ Xanh', 100, 25000, 1, N'Tôi_thấy_hoa_vàng_trên_cỏ_xanh.jpg',2, N'Tác giả Nguyễn Nhật Ánh'),
(3, N'Chí Phèo', 100, 20000, 2, N'chipheo.jpg',3, N'Tác giả Nam Cao'),
(4, N'Lão Hạc', 100, 20000, 2, N'laohac.jpg',3, N'Tác giả Nam Cao'),
(5, N'Những cuộc phiêu lưu của Sherlock Holmes', 100, 20000, 3, N'SherlockHolmes.jpg',4, N'Tác giả Arthur Conan Doyle'),
(6, N'Nguồn Gốc Các Loài', 100, 20000, 4, N'nguon_goc_cac_loai.jpg',5, N'Tác giả Charles Darwin'),
(7, N'Mắt Biếc', 100, 20000, 5, N'nguon_goc_cac_loai.jpg',2, N'Tác giả Nguyễn Nhật Ánh'),
(8, N'IT', 100, 20000, 6, N'IT.jpg',7, N'Tác giả Stephen King'),
(9, N'Dế Mèn phiêu lưu ký', 100, 20000, 7, N'de_men_phieu_luu_ky.jpg',6, N'Tác giả Tô Hoài'),
(10, N'Your Name', 100, 20000, 8, N'your_name.jpg',8, N'Tác giả Shinkai Makoto');

INSERT INTO nhanvien VALUES
(0, N'Admin', 'Duy', 'Nam', N'Admin', 1),
(1, N'Trần Thị', 'Loan', 'Nam', N'Nhân viên', 1),
(2, N'Nguyễn Văn', 'D', 'Nam', N'Nhân viên', 1),
(3, N'Nguyễn Văn', 'K', 'Nam', N'Nhân viên', 1),
(4, N'Nguyễn Văn', 'K', 'Nam', N'Quản lý', 1),
(5, N'Nguyễn Văn', 'K', 'Nam', N'Quản lý', 1),
(6, N'Nguyễn Văn', 'K', 'Nam', N'Quản lý', 1);

INSERT INTO khachhang VALUES
(1, N'Nguyễn Văn', 'A', 'Nam', 500000, 1),
(2, N'Nguyễn Văn', 'B', 'Nam', 200000, 1),
(3, N'Lê Thị', 'Na',N'Nữ', 60000, 1),
(4, N'Lê Thị', 'B', N'Nữ', 100000, 0),
(5, N'Lê Thị', N'Nở', N'Nữ', 100000, 0),
(7, N'Lê Thị', N'Đào', N'Nữ', 0, 1);

INSERT INTO phanquyen VALUES
(N'Admin', 1, 1, 1, 1, 1),
(N'Nhân viên', 0, 0, 0, 1, 0),
(N'Quản lý', 1, 0, 1, 1, 1);

INSERT INTO taikhoan VALUES
(0, 'Admin', 'Admin', 'Admin', 1),
(1, 'nv01', 'nv01', N'Nhân viên', 1),
(6, 'ql06', 'ql06', N'Quản lý', 1);

INSERT INTO khuyenmai VALUES
(0, N'Không khuyến mãi', '2021-05-01', '2034-05-04'),
(1, N'Khuyến mãi Back to School', '2022-05-01', '2022-10-04'),
(2, N'Tết Nguyên Đán', '2022-01-01', '2022-02-01'),
(3, N'Khuyến mãi Black  Friday', '2022-11-25', '2022-11-30');

INSERT INTO hoadon VALUES
(1, 1, 1, '2022-04-01', 200000, N'Đã thanh toán'),
(2, 2, 2, '2022-04-01', 100000, N'Đã thanh toán'),
(3, 1, 2, '2022-05-01', 300000, N'Đã thanh toán'),
(4, 2, 1, '2022-04-13', 100000, N'Đã thanh toán');


INSERT INTO phieunhap VALUES
(1, 1, 1, '2022-01-02', 1250000),
(2, 2, 2, '2022-05-01', 750000);

INSERT INTO cthoadon VALUES
(1, 1, 8, 25000, 200000),
(1, 2, 4, 25000, 100000),
(2, 2, 12, 25000, 300000),
(2, 3, 4, 20000, 100000);

INSERT INTO ctkhuyenmai VALUES
(0, 0, 0, 0),
(1, 1, 15, 100000),
(1, 2, 10, 100000),
(1, 3, 5, 100000),
(2, 0, 5, 0),
(2, 1, 15, 9000000),
(2, 4, 10, 0),
(3, 1, 5, 0),
(3, 2, 5, 100000),
(3, 3, 5, 0),
(3, 4, 5, 0),
(3, 8, 5, 0);

INSERT INTO ctphieunhap VALUES
(1, 1, 50, 25000, 1250000),
(2, 2, 30, 25000, 750000);
/*
INSERT INTO hoadon VALUES
(7, 1, 1, '2023-01-01', 200000, N'Đã thanh toán'),
(8, 2, 2, '2023-02-01', 100000, N'Đã thanh toán'),
(9, 1, 2, '2023-05-01', 300000, N'Đã thanh toán'),
(10, 2, 1, '2023-04-13', 80000, N'Đã thanh toán');


INSERT INTO cthoadon VALUES
(7, 1, 8, 25000, 200000),
(8, 2, 4, 25000, 100000),
(9, 2, 12, 25000, 300000),
(10, 3, 4, 20000, 80000);
*/