<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cari Reservasi berdasarkan Username Pelanggan</title>
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
            <h1 class="section-title">Cari Reservasi berdasarkan Username Pelanggan</h1>
            <p class="back-link"><a href="/dashboard_admin">Kembali ke Dashboard Admin</a></p>

            <div class="form-card">
                <div class="form-group">
                    <label for="username">Username Pelanggan:</label>
                    <input type="text" id="username" class="full-width-input" required>
                    <button onclick="searchReservasiByUsername()" class="primary-button">Cari</button>
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
            window.searchReservasiByUsername = function() {
                const username = $('#username').val();
                if (!username) {
                    alert('Mohon masukkan username pelanggan.');
                    return;
                }

                $.ajax({
                    url: `/api/pelanggan/username/${username}`, // Endpoint untuk mendapatkan pelanggan berdasarkan username
                    type: 'GET',
                    success: function(pelanggan) {
                        $.ajax({
                            url: `/api/reservasi/customer/${pelanggan.id}`, // Endpoint untuk mendapatkan reservasi berdasarkan customer ID
                            type: 'GET',
                            success: function(data) {
                                const tableBody = $('#reservasiTable tbody');
                                tableBody.empty();
                                if (data.length === 0) {
                                    tableBody.append('<tr><td colspan="5">Tidak ada reservasi ditemukan untuk username ini.</td></tr>');
                                } else {
                                    data.forEach(reservasi => {
                                        const layananNama = reservasi.layanan ? reservasi.layanan.nama : 'Layanan tidak diketahui';
                                        const statusClass = reservasi.status ? `status-${reservasi.status.toLowerCase()}` : '';
                                        const row = `
                                            <tr>
                                                <td data-label="ID Reservasi">${reservasi.id}</td>
                                                <td data-label="Nama Pelanggan">${reservasi.customer ? reservasi.customer.nama : 'N/A'}</td>
                                                <td data-label="Layanan">${layananNama}</td>
                                                <td data-label="Tanggal">${new Date(reservasi.tanggal).toLocaleDateString('id-ID')}</td>
                                                <td data-label="Status"><span class="status-badge ${statusClass}">${reservasi.status}</span></td>
                                            </tr>
                                        `;
                                        tableBody.append(row);
                                    });
                                }
                            },
                            error: function(xhr, status, error) {
                                alert('Gagal memuat reservasi: ' + xhr.responseText);
                            }
                        });
                    },
                    error: function(xhr, status, error) {
                        alert('Pelanggan dengan username tersebut tidak ditemukan: ' + xhr.responseText);
                        $('#reservasiTable tbody').empty().append('<tr><td colspan="5">Pelanggan tidak ditemukan.</td></tr>');
                    }
                });
            };
        });
    </script>
</body>
</html>