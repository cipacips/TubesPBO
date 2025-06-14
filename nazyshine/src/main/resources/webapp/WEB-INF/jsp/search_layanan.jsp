<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cari Reservasi berdasarkan Layanan</title>
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
            <h1 class="section-title">Cari Reservasi berdasarkan Layanan</h1>
            <p class="back-link"><a href="/dashboard_admin">Kembali ke Dashboard Admin</a></p>

            <div class="form-card">
                <div class="form-group">
                    <label for="layananName">Nama Layanan:</label>
                    <input type="text" id="layananName" class="full-width-input" required>
                    <button onclick="searchReservasiByLayanan()" class="primary-button">Cari</button>
                </div>
            </div>

            <div class="table-card">
                <h3>Hasil Pencarian</h3>
                <div class="table-container">
                    <table id="reservasiTable" class="themed-table">
                        <thead>
                            <tr>
                                <th>ID Reservasi</th>
                                <th>Nama Pelanggan</th>
                                <th>Layanan</th>
                                <th>Tanggal</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <script>
        $(document).ready(function() {
            window.searchReservasiByLayanan = function() {
                const layananName = $('#layananName').val();
                if (!layananName) {
                    alert('Mohon masukkan nama layanan.');
                    return;
                }

                // NOTE: Anda perlu endpoint API di backend untuk mencari reservasi berdasarkan nama layanan.
                // Contoh di bawah mengasumsikan Anda memiliki endpoint seperti `/api/reservasi/layanan/search?name=...`
                // Atau, Anda bisa mendapatkan semua reservasi lalu filter di frontend (kurang efisien untuk data besar).
                // Atau, yang lebih baik, buat endpoint di LayananService untuk mencari layanan berdasarkan nama, lalu gunakan ID-nya untuk mencari reservasi.

                alert('Fungsi pencarian reservasi berdasarkan nama layanan perlu implementasi API backend.');
                $('#reservasiTable tbody').empty().append('<tr><td colspan="5">Implementasi API pencarian berdasarkan layanan diperlukan.</td></tr>');
            };
        });
    </script>
</body>
</html>