<?php
$conn_string = "host=172.28.48.1 dbname=web_78 user=postgres password=123";
$conn = pg_connect($conn_string);

if (!$conn) {
    error_log("Koneksi gagal: " . pg_last_error());
    echo "Terjadi kesalahan saat menghubungkan ke database.";
    exit;
}
?>
