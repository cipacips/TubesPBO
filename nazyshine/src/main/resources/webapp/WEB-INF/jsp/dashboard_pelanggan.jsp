<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard Pelanggan</title>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600;700&family=Playfair+Display:wght@700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="/resources/css/style.css">
</head>
<body>
    <div class="header">
        <span>Nazyshine Salon</span>
        <div class="menu-icon"><i class="fas fa-bars"></i></div>
    </div>

    <div class="main-container">
        <div class="dashboard-section">
            <h1 class="section-title">Dashboard Pelanggan</h1>
            <h2 class="welcome-text">Selamat datang, ${username}!</h2>
            <nav class="dashboard-nav">
                <ul>
                    <li><a href="/dashboard_customer/registrasi_layanan" class="nav-button">Reservasi Layanan Baru</a></li>
                    <li><a href="/dashboard_customer/riwayat_reservasi" class="nav-button">Lihat Riwayat Reservasi</a></li>
                    <li><a href="/logout" class="nav-button logout-button">Logout</a></li>
                </ul>
            </nav>
        </div>
    </div>
</body>
</html>