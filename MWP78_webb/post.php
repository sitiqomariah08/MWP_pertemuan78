<?php
session_start();

if (!isset($_SESSION['username'])) {
    header("Location: index.php");
    exit();
}

include 'koneksi.php';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $username = $_SESSION['username'];
    $isi = trim($_POST['isi']);

    if (!empty($isi)) {
        $stmtName = "insert_postingan";
        $sql = "INSERT INTO postingan (username, isi) VALUES ($1, $2)";

        pg_prepare($conn, $stmtName, $sql);
        $result = pg_execute($conn, $stmtName, array($username, $isi));

        if ($result) {
            echo 'sukses';
        } else {
            echo 'gagal';
        }
    } else {
        echo 'kosong';
    }
}
?>