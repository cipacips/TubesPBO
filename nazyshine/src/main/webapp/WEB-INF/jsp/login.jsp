<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Nazyshine Salon</title>
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
        <div class="login-section">
            <h1 class="section-title">Login</h1>
            <form action="/login" method="post" class="form-card">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <button type="submit" class="primary-button">Login</button>
            </form>
            <p class="form-link">Belum punya akun? <a href="/register.html">Daftar di sini</a></p>
            <% if (request.getParameter("error") != null) { %>
                <p class="error-message">Username atau password salah!</p>
            <% } %>
            <% if (request.getParameter("logout") != null) { %>
                <p class="success-message">Anda telah berhasil logout.</p>
            <% } %>
        </div>
    </div>
</body>
</html>