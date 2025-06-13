<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard Admin</title>
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
            <h1 class="section-title">Dashboard Admin</h1>
            <h2 class="welcome-text">Selamat datang, ${username}!</h2>
            <nav class="dashboard-nav">
                <h3>Manajemen Data Master:</h3>
                <ul>
                    <li><a href="/dashboard_admin/layanan" class="nav-button">Kelola Layanan (CRUD)</a></li>
                    <li><a href="/dashboard_admin/pelanggan" class="nav-button">Kelola Pelanggan (CRUD)</a></li>
                    <li><a href="/dashboard_admin/admin" class="nav-button">Kelola Admin (CRUD)</a></li>
                </ul>
                <h3>Manajemen Reservasi:</h3>
                <ul>
                    <li><a href="/dashboard_admin/update_reservasi_status" class="nav-button">Update Status Reservasi</a></li>
                    <li><a href="/dashboard_admin/search_layanan" class="nav-button">Cari Reservasi Berdasarkan Layanan</a></li>
                    <li><a href="/dashboard_admin/search_id" class="nav-button">Cari Reservasi Berdasarkan ID</a></li>
                    <li><a href="/dashboard_admin/search_username" class="nav-button">Cari Reservasi Berdasarkan Username Pelanggan</a></li>
                </ul>
                <ul>
                    <li><a href="/logout" class="nav-button logout-button">Logout</a></li>
                </ul>
            </nav>
        </div>
    </div>
</body>
</html>