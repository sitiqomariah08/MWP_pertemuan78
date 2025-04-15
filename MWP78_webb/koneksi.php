<?php
$conn = pg_connect("host=172.21.96.1 dbname=MWP user=postgres password=nidzom15");

if (!$conn) {
    die("Koneksi gagal: " . pg_last_error());
}
?>