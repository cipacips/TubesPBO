<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Kelola Layanan</title>
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
            <h1 class="section-title">Manajemen Layanan</h1>
            <p class="back-link"><a href="/dashboard_admin">Kembali ke Dashboard Admin</a></p>

            <div class="form-card">
                <h3>Tambah Layanan Baru</h3>
                <form id="createLayananForm">
                    <div class="form-group">
                        <label for="nama">Nama Layanan:</label>
                        <input type="text" id="nama" name="nama" required>
                    </div>
                    <div class="form-group">
                        <label for="harga">Harga:</label>
                        <input type="number" id="harga" name="harga" step="0.01" required>
                    </div>
                    <div class="form-group">
                        <label for="durasi">Durasi (menit):</label>
                        <input type="number" id="durasi" name="durasi" required>
                    </div>
                    <button type="submit" class="primary-button">Tambah Layanan</button>
                </form>
            </div>

            <div class="table-card">
                <h3>Daftar Layanan</h3>
                <div class="table-container">
                    <table id="layananTable" class="themed-table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nama Layanan</th>
                                <th>Harga</th>
                                <th>Durasi</th>
                                <th>Aksi</th>
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
            loadLayanan();

            $('#createLayananForm').submit(function(event) {
                event.preventDefault();
                const layananData = {
                    nama: $('#nama').val(),
                    harga: parseFloat($('#harga').val()),
                    durasi: parseInt($('#durasi').val())
                };

                $.ajax({
                    url: '/api/layanan',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(layananData),
                    success: function(response) {
                        alert('Layanan berhasil ditambahkan!');
                        $('#createLayananForm')[0].reset();
                        loadLayanan();
                    },
                    error: function(xhr, status, error) {
                        alert('Gagal menambahkan layanan: ' + xhr.responseText);
                    }
                });
            });

            function loadLayanan() {
                $.ajax({
                    url: '/api/layanan',
                    type: 'GET',
                    success: function(data) {
                        const tableBody = $('#layananTable tbody');
                        tableBody.empty();
                        if (data.length === 0) {
                            tableBody.append('<tr><td colspan="5">Tidak ada layanan tersedia.</td></tr>');
                        } else {
                            data.forEach(layanan => {
                                const row = `
                                    <tr>
                                        <td data-label="ID">${layanan.id}</td>
                                        <td data-label="Nama Layanan">${layanan.nama}</td>
                                        <td data-label="Harga">Rp${layanan.harga}</td>
                                        <td data-label="Durasi">${layanan.durasi} menit</td>
                                        <td data-label="Aksi">
                                            <button onclick="editLayanan(${layanan.id})" class="action-button edit-button">Edit</button>
                                            <button onclick="deleteLayanan(${layanan.id})" class="action-button delete-button">Hapus</button>
                                        </td>
                                    </tr>
                                `;
                                tableBody.append(row);
                            });
                        }
                    },
                    error: function(xhr, status, error) {
                        alert('Gagal memuat layanan: ' + xhr.responseText);
                    }
                });
            }

            window.editLayanan = function(id) {
                alert('Fungsi Edit Layanan dengan ID: ' + id + ' (implementasikan modal atau form edit)');
                // Implementasi untuk mengambil data layanan dan mengisi form edit
            };

            window.deleteLayanan = function(id) {
                if (confirm('Anda yakin ingin menghapus layanan ini?')) {
                    $.ajax({
                        url: '/api/layanan/' + id,
                        type: 'DELETE',
                        success: function(response) {
                            alert('Layanan berhasil dihapus!');
                            loadLayanan();
                        },
                        error: function(xhr, status, error) {
                            alert('Gagal menghapus layanan: ' + xhr.responseText);
                        }
                    });
                }
            };
        });
    </script>
</body>
</html>