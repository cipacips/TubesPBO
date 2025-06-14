<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cari Reservasi berdasarkan ID</title>
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
            <h1 class="section-title">Cari Reservasi berdasarkan ID</h1>
            <p class="back-link"><a href="/dashboard_admin">Kembali ke Dashboard Admin</a></p>

            <div class="form-card">
                <div class="form-group">
                    <label for="reservasiId">ID Reservasi:</label>
                    <input type="number" id="reservasiId" class="full-width-input" required>
                    <button onclick="searchReservasiById()" class="primary-button">Cari</button>
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
            window.searchReservasiById = function() {
                const id = $('#reservasiId').val();
                if (!id) {
                    alert('Mohon masukkan ID Reservasi.');
                    return;
                }

                $.ajax({
                    url: `/api/reservasi/${id}`,
                    type: 'GET',
                    success: function(reservasi) {
                        const tableBody = $('#reservasiTable tbody');
                        tableBody.empty();
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
                    },
                    error: function(xhr, status, error) {
                        alert('Reservasi tidak ditemukan: ' + xhr.responseText);
                        $('#reservasiTable tbody').empty().append('<tr><td colspan="5">Reservasi tidak ditemukan.</td></tr>');
                    }
                });
            };
        });
    </script>
</body>
</html>