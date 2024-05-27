<?php
$koneksi = mysqli_connect("localhost","root","","db_kontak");
$id_kontak = $_GET['id_kontak'];
$namaLengkap = $_POST['namaLengkap'];
$namaPanggilan = $_POST['namaPanggilan'];
$noHp= $_POST['noHp'];
$alamat = $_POST['alamat'];
$sql = "UPDATE kontak SET
        namaLengkap='".$namaLengkap."',
        namaPanggilan='".$namaPanggilan."',
        noHp='".$noHp."',
        alamat='".$alamat."'
        WHERE id_kontak='".$id_kontak."'";

$query = mysqli_query($koneksi, $sql);
if($query){
    echo json_encode(array(
        'status' => 'data_berhasil_diubah'
    ));
}        
