<?php
$id_kontak = $_GET['id_kontak'];
$koneksi = mysqli_connect("localhost","root","","db_kontak");
$query = mysqli_query($koneksi, "DELETE FROM kontak WHERE id_kontak = '".$id_kontak."'");
if($query){
    echo json_encode(array(
        'status' => 'data_terhapus'
    ));
}else{
    echo json_encode(array(
        'status' => 'data_gagal_dihapus'
    ));
}
