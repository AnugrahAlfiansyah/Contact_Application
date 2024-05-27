<?php
$namaLengkap = $_POST['namaLengkap'];
$namaPanggilan = $_POST['namaPanggilan'];
$noHp= $_POST['noHp'];
$alamat = $_POST['alamat'];
$sql = "INSERT INTO kontak(id_kontak, namaLengkap, namaPanggilan, noHp, alamat)"
        ."VALUES"
        ."(NULL, '".$namaLengkap."', '".$namaPanggilan."', '".$noHp."', '".$alamat."');";
$koneksi = mysqli_connect("localhost","root","","db_kontak");
if($koneksi):
    $simpan = mysqli_query($koneksi, $sql);
    if($simpan){
        echo json_encode(array(
            'status' => 'oke'
        ));
    }
    else
    {
        echo json_encode(array(
            'status' => 'gagal'
        ));
    }
endif;