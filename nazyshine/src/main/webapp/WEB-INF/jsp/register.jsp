<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Daftar Akun Nazyshine Salon</title>
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
        <div class="login-section">
            <h1 class="section-title">Daftar Akun Baru</h1>
            <form id="registerForm" class="form-card">
                <div class="form-group">
                    <label for="nama">Nama Lengkap:</label>
                    <input type="text" id="nama" name="nama" required>
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required>
                </div>
                <div class="form-group">
                    <label for="noHp">Nomor HP:</label>
                    <input type="text" id="noHp" name="noHp" required>
                </div>
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <button type="submit" class="primary-button">Daftar</button>
            </form>
            <p class="form-link">Sudah punya akun? <a href="/login.html">Login di sini</a></p>
        </div>
    </div>

    <script>
        $(document).ready(function() {
            $('#registerForm').submit(function(event) {
                event.preventDefault();
                const userData = {
                    nama: $('#nama').val(),
                    email: $('#email').val(),
                    noHp: $('#noHp').val(),
                    username: $('#username').val(),
                    password: $('#password').val()
                };

                $.ajax({
                    url: '/api/pelanggan/register',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(userData),
                    success: function(response) {
                        alert('Pendaftaran berhasil! Silakan login.');
                        window.location.href = '/login.html';
                    },
                    error: function(xhr, status, error) {
                        alert('Gagal mendaftar: ' + xhr.responseText);
                    }
                });
            });
        });
    </script>
</body>
</html>