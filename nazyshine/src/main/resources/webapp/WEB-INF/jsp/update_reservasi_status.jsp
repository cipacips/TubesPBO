<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Status Reservasi</title>
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
            <h1 class="section-title">Update Status Reservasi</h1>
            <p class="back-link"><a href="/dashboard_admin">Kembali ke Dashboard Admin</a></p>

            <div class="form-card">
                <h3>Cari Reservasi</h3>
                <div class="form-group">
                    <label for="reservasiIdSearch">ID Reservasi:</label>
                    <input type="number" id="reservasiIdSearch" class="full-width-input">
                    <button onclick="searchReservasi()" class="primary-button">Cari</button>
                </div>
            </div>

            <div class="form-card">
                <h3>Form Update Status</h3>
                <form id="updateStatusForm">
                    <div class="form-group">
                        <label for="reservasiId">ID Reservasi:</label>
                        <input type="number" id="reservasiId" name="reservasiId" required readonly class="full-width-input">
                    </div>
                    <div class="form-group">
                        <label for="currentStatus">Status Saat Ini:</label>
                        <input type="text" id="currentStatus" readonly class="full-width-input">
                    </div>
                    <div class="form-group">
                        <label for="newStatus">Status Baru:</label>
                        <select id="newStatus" name="newStatus" required class="full-width-input">
                            <option value="PENDING">PENDING</option>
                            <option value="DIKONFIRMASI">DIKONFIRMASI</option>
                            <option value="SELESAI">SELESAI</option>
                            <option value="CANCELED">CANCELED</option> </select>
                    </div>
                    <button type="submit" class="primary-button">Update Status</button>
                </form>
            </div>
        </div>
    </div>

    <script>
        $(document).ready(function() {
            window.searchReservasi = function() {
                const id = $('#reservasiIdSearch').val();
                if (!id) {
                    alert('Mohon masukkan ID Reservasi.');
                    return;
                }
                $.ajax({
                    url: '/api/reservasi/' + id,
                    type: 'GET',
                    success: function(reservasi) {
                        $('#reservasiId').val(reservasi.id);
                        $('#currentStatus').val(reservasi.status);
                        $('#newStatus').val(reservasi.status);
                    },
                    error: function(xhr, status, error) {
                        alert('Reservasi tidak ditemukan: ' + xhr.responseText);
                        $('#updateStatusForm')[0].reset();
                    }
                });
            };

            $('#updateStatusForm').submit(function(event) {
                event.preventDefault();
                const id = $('#reservasiId').val();
                const newStatus = $('#newStatus').val();

                if (!id) {
                    alert('Mohon cari reservasi terlebih dahulu.');
                    return;
                }

                $.ajax({
                    url: `/api/reservasi/${id}/status?newStatus=${newStatus}`,
                    type: 'PUT',
                    success: function(response) {
                        alert('Status reservasi berhasil diperbarui menjadi: ' + response.status);
                        $('#currentStatus').val(response.status);
                    },
                    error: function(xhr, status, error) {
                        alert('Gagal memperbarui status reservasi: ' + xhr.responseText);
                    }
                });
            });
        });
    </script>
</body>
</html>