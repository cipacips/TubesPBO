<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Riwayat Reservasi Anda</title>
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
        <div class="reservation-section">
            <h1 class="section-title">Riwayat Reservasi Anda</h1>
            <p class="back-link"><a href="/dashboard_customer">Kembali ke Dashboard Pelanggan</a></p>
            
            <div class="table-card">
                <h3>Daftar Reservasi</h3>
                <div class="table-container">
                    <table id="reservasiTable" class="themed-table">
                        <thead>
                            <tr>
                                <th>ID Reservasi</th>
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
            const pelangganId = $('#pelangganId').val(); // Dari model attribute
            loadReservasiHistory(pelangganId);

            function loadReservasiHistory(pId) {
                $.ajax({
                    url: `/api/reservasi/customer/${pId}`,
                    type: 'GET',
                    success: function(data) {
                        const tableBody = $('#reservasiTable tbody');
                        tableBody.empty();
                        if (data.length === 0) {
                            tableBody.append('<tr><td colspan="4">Tidak ada riwayat reservasi.</td></tr>');
                        } else {
                            data.forEach(reservasi => {
                                // Pastikan objek layanan ada sebelum mengakses propertinya
                                const layananNama = reservasi.layanan ? reservasi.layanan.nama : 'Layanan tidak diketahui';
                                const statusClass = reservasi.status ? `status-${reservasi.status.toLowerCase()}` : '';

                                const row = `
                                    <tr>
                                        <td data-label="ID Reservasi">${reservasi.id}</td>
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
                        alert('Gagal memuat riwayat reservasi: ' + xhr.responseText);
                    }
                });
            }
        });
    </script>
</body>
</html>