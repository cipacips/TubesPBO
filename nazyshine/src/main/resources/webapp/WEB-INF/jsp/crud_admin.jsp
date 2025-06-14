<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Kelola Admin</title>
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
            <h1 class="section-title">Manajemen Admin</h1>
            <p class="back-link"><a href="/dashboard_admin">Kembali ke Dashboard Admin</a></p>

            <div class="table-card">
                <h3>Daftar Admin</h3>
                <div class="table-container">
                    <table id="adminTable" class="themed-table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nama</th>
                                <th>Email</th>
                                <th>Username</th>
                                <th>Shift</th>
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
            loadAdmin();

            function loadAdmin() {
                $.ajax({
                    url: '/api/admin',
                    type: 'GET',
                    success: function(data) {
                        const tableBody = $('#adminTable tbody');
                        tableBody.empty();
                        if (data.length === 0) {
                            tableBody.append('<tr><td colspan="6">Tidak ada admin terdaftar.</td></tr>');
                        } else {
                            data.forEach(admin => {
                                const row = `
                                    <tr>
                                        <td data-label="ID">${admin.id}</td>
                                        <td data-label="Nama">${admin.nama}</td>
                                        <td data-label="Email">${admin.email}</td>
                                        <td data-label="Username">${admin.username}</td>
                                        <td data-label="Shift">${admin.shift}</td>
                                        <td data-label="Aksi">
                                            <button onclick="editAdmin(${admin.id})" class="action-button edit-button">Edit</button>
                                            <button onclick="deleteAdmin(${admin.id})" class="action-button delete-button">Hapus</button>
                                        </td>
                                    </tr>
                                `;
                                tableBody.append(row);
                            });
                        }
                    },
                    error: function(xhr, status, error) {
                        alert('Gagal memuat admin: ' + xhr.responseText);
                    }
                });
            }

            window.editAdmin = function(id) {
                alert('Fungsi Edit Admin dengan ID: ' + id + ' (implementasikan modal atau form edit)');
                // Implementasi edit serupa dengan Layanan
            };

            window.deleteAdmin = function(id) {
                if (confirm('Anda yakin ingin menghapus admin ini?')) {
                    $.ajax({
                        url: '/api/admin/' + id,
                        type: 'DELETE',
                        success: function(response) {
                            alert('Admin berhasil dihapus!');
                            loadAdmin();
                        },
                        error: function(xhr, status, error) {
                            alert('Gagal menghapus admin: ' + xhr.responseText);
                        }
                    });
                }
            };
        });
    </script>
</body>
</html>