# ỨNG DỤNG BÁN TRÀ SỮA
## Mục tiêu dự án:
Ứng dụng bán trà sữa giúp người dùng dễ dàng đặt hàng, tìm kiếm sản phẩm, theo dõi đơn hàng, và quản lý thông tin cá nhân. Đây là đồ án cuối kỳ của môn học **Lập trình di động**.

## Chức năng chính
1. Đăng nhập, đăng ký, đăng xuất, Quên mật khẩu
2. Quản lý giỏ hàng
3. Xem chi tiết sản phẩm
4. Xem đánh giá sản phẩm
5. Tìm kiếm sản phẩm
6. Xem sản phẩm bán chạy
7. Xem sản phẩm mới
8. Xem danh sách và chi tiết khuyến mãi
9. Lọc sản phẩm theo danh mục
10. Quản lý đơn hàng
11. Đặt hàng và thanh toán
12. Quản lý đánh giá
13. Quản lý thông tin cá nhân

## Công nghệ sử dụng
Ngôn ngữ lập trình: Java, XML

IDE: Android Studio, Spring Tool Suite (STS)

Cơ sở dữ liệu: SQL Server

Thư viện và công cụ:
- Retrofit: Gọi API
- Glide: Hiển thị hình ảnh
- Figma: Thiết kế giao diện
  
Môi trường thử nghiệm:
- Máy ảo Android Emulator (Android 11, x86)
- Thiết bị thật

## Hướng dẫn cài đặt
**Bước 1: Cài đặt môi trường**

Cài đặt Android Studio.

Cài đặt Spring Tool Suite (STS).

Cài đặt SQL Server.

**Bước 2: Clone mã nguồn**

Mở Git Bash hoặc Command Prompt.

Chạy lệnh: **git clone https://github.com/KieuLinh0701/LTDD_Project_TheLa.git**

**Bước 3: Cấu hình Android Studio**

Mở Android Studio và chọn Open Project.

Tải xuống và cấu hình các SDK cần thiết (Android 11, x86).

Đồng bộ hóa Gradle.

**Bước 4: Cấu hình backend với Spring Boot**

Mở Spring Tool Suite (STS) và import dự án backend.

Cập nhật tệp application.properties với thông tin kết nối cơ sở dữ liệu:

spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=LTDD_TheLa

spring.datasource.username=<your_username>

spring.datasource.password=<your_password>

Chạy ứng dụng Spring Boot với lệnh: **mvn spring-boot:run**

**Bước 5: Cấu hình kết nối API trong ứng dụng Android**

Cập nhật URL của API trong file APIClient.java theo địa chỉ server backend

**Bước 6: Chạy ứng dụng**

Chọn thiết bị thử nghiệm:

Máy ảo Android Emulator hoặc thiết bị thật (kích hoạt chế độ gỡ lỗi USB).

Nhấn Run trong Android Studio.

## Sinh viên thực hiện
- Họ tên: Uý Nữ Kiều Linh
- Mã số sinh viên: 21110896
- Trường: Đại học Sư phạm Kỹ thuật Thành phố Hồ Chí Minh
