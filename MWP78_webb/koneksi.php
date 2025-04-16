<?php
$conn = pg_connect("host= dbname= user=postgres password=123");

if (!$conn) {
    die("Koneksi gagal: " . pg_last_error());
}
?>
