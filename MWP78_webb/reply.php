<?php
session_start();

if (!isset($_SESSION['username'])) {
    header("Location: index.php");
    exit();
}

include 'koneksi.php';

$username = $_SESSION['username'];
$isi = isset($_POST['isi']) ? trim($_POST['isi']) : '';
$id_postingan = isset($_POST['id']) ? $_POST['id'] : '';

if (!empty($isi) && !empty($id_postingan)) {
    $query = "INSERT INTO reply (id_postingan, username, isi) VALUES ($1, $2, $3)";
    $result = pg_query_params($conn, $query, array($id_postingan, $username, $isi));

    if ($result) {
        echo "sukses";
    } else {
        echo "gagal";
    }
} else {
    echo "kosong";
}
?>