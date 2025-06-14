<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Kelola Pelanggan</title>
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
            <h1 class="section-title">Manajemen Pelanggan</h1>
            <p class="back-link"><a href="/dashboard_admin">Kembali ke Dashboard Admin</a></p>

            <div class="table-card">
                <h3>Daftar Pelanggan</h3>
                <div class="table-container">
                    <table id="pelangganTable" class="themed-table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nama</th>
                                <th>Email</th>
                                <th>Username</th>
                                <th>No. HP</th>
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
            loadPelanggan();

            function loadPelanggan() {
                $.ajax({
                    url: '/api/pelanggan',
                    type: 'GET',
                    success: function(data) {
                        const tableBody = $('#pelangganTable tbody');
                        tableBody.empty();
                        if (data.length === 0) {
                            tableBody.append('<tr><td colspan="6">Tidak ada pelanggan terdaftar.</td></tr>');
                        } else {
                            data.forEach(pelanggan => {
                                const row = `
                                    <tr>
                                        <td data-label="ID">${pelanggan.id}</td>
                                        <td data-label="Nama">${pelanggan.nama}</td>
                                        <td data-label="Email">${pelanggan.email}</td>
                                        <td data-label="Username">${pelanggan.username}</td>
                                        <td data-label="No. HP">${pelanggan.noHp}</td>
                                        <td data-label="Aksi">
                                            <button onclick="editPelanggan(${pelanggan.id})" class="action-button edit-button">Edit</button>
                                            <button onclick="deletePelanggan(${pelanggan.id})" class="action-button delete-button">Hapus</button>
                                        </td>
                                    </tr>
                                `;
                                tableBody.append(row);
                            });
                        }
                    },
                    error: function(xhr, status, error) {
                        alert('Gagal memuat pelanggan: ' + xhr.responseText);
                    }
                });
            }

            window.editPelanggan = function(id) {
                alert('Fungsi Edit Pelanggan dengan ID: ' + id + ' (implementasikan modal atau form edit)');
                // Implementasi edit serupa dengan Layanan
            };

            window.deletePelanggan = function(id) {
                if (confirm('Anda yakin ingin menghapus pelanggan ini?')) {
                    $.ajax({
                        url: '/api/pelanggan/' + id,
                        type: 'DELETE',
                        success: function(response) {
                            alert('Pelanggan berhasil dihapus!');
                            loadPelanggan();
                        },
                        error: function(xhr, status, error) {
                            alert('Gagal menghapus pelanggan: ' + xhr.responseText);
                        }
                    });
                }
            };
        });
    </script>
</body>
</html>