<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reservasi Layanan</title>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600;700&family=Playfair+Display:wght@700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="/resources/css/style.css">
    <script src="/resources/js/jquery-3.6.0.min.js"></script>
</head>
<body>
    <div class="header">
        <span>Nazyshine Salon</span>
        <div class="menu-icon"><i class="fas fa-bars"></i></div>
    </div>

    <div class="main-container">
        <div class="content-section">
            <h1 class="section-title">Reservasi Layanan Salon</h1>
            <p class="back-link"><a href="/dashboard_customer">Kembali ke Dashboard Pelanggan</a></p>

            <div class="form-card">
                <h3>Form Reservasi</h3>
                <form id="reservasiForm">
                    <div class="form-group">
                        <label for="pelangganId">ID Pelanggan:</label>
                        <input type="text" id="pelangganId" name="pelangganId" value="${pelangganId}" readonly>
                    </div>
                    <div class="form-group">
                        <label for="layananId">Pilih Layanan:</label>
                        <select id="layananId" name="layananId" required>
                            </select>
                    </div>
                    <div class="form-group">
                        <label for="tanggal">Tanggal Reservasi:</label>
                        <input type="date" id="tanggal" name="tanggal" required>
                    </div>
                    <button type="submit" class="primary-button">Buat Reservasi</button>
                </form>
            </div>
        </div>
    </div>

    <script>
        $(document).ready(function() {
            loadLayananOptions();

            function loadLayananOptions() {
                $.ajax({
                    url: '/api/layanan', // Public endpoint
                    type: 'GET',
                    success: function(data) {
                        const selectElement = $('#layananId');
                        selectElement.empty();
                        if (data.length === 0) {
                            selectElement.append('<option value="">Tidak ada layanan tersedia</option>');
                        } else {
                            selectElement.append('<option value="">-- Pilih Layanan --</option>');
                            data.forEach(layanan => {
                                selectElement.append(`<option value="${layanan.id}">${layanan.nama} (Rp${layanan.harga}, ${layanan.durasi} menit)</option>`);
                            });
                        }
                    },
                    error: function(xhr, status, error) {
                        alert('Gagal memuat daftar layanan: ' + xhr.responseText);
                    }
                });
            }

            $('#reservasiForm').submit(function(event) {
                event.preventDefault();
                const pelangganId = $('#pelangganId').val();
                const layananId = $('#layananId').val();
                const tanggal = $('#tanggal').val(); // Tanggal akan diatur di backend

                if (!layananId) {
                    alert("Mohon pilih layanan.");
                    return;
                }

                $.ajax({
                    url: `/api/reservasi/create?customerId=${pelangganId}&layananId=${layananId}`,
                    type: 'POST',
                    success: function(response) {
                        alert('Reservasi berhasil dibuat! Status: ' + response.status);
                        window.location.href = '/dashboard_customer/riwayat_reservasi';
                    },
                    error: function(xhr, status, error) {
                        alert('Gagal membuat reservasi: ' + xhr.responseText);
                    }
                });
            });
        });
    </script>
</body>
</html>