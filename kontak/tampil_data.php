<?php
$id_kontak = $_GET['id_kontak'];
$koneksi = mysqli_connect("localhost","root","","db_kontak");
$query = mysqli_query($koneksi, 
        "SELECT * FROM kontak WHERE id_kontak = '".$id_kontak."'");

$hasil = mysqli_fetch_array($query);
echo json_encode(array(
    'namaLengkap' => $hasil['namaLengkap'],
    'namaPanggilan' => $hasil['namaPanggilan'],
    'noHp' => $hasil['noHp'],
    'alamat' => $hasil['alamat'],
));