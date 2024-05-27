<?php
$koneksi = mysqli_connect("localhost","root","","db_kontak");
$sql = mysqli_query($koneksi, "SELECT * FROM kontak");
$data = array();
while($tampil = mysqli_fetch_array($sql)){
    $data[]=array(
        'id_kontak' => $tampil['id_kontak'],
        'namaLengkap' => $tampil['namaLengkap'],
        'namaPanggilan' => $tampil['namaPanggilan'],
        'noHp' => $tampil['noHp'],
        'alamat' => $tampil['alamat'],
    );
}

echo json_encode(array(
    'hasil' =>$data
));