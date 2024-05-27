<?php
$cari=$_GET['q'];
$konek= mysqli_connect("localhost", "root", "", "db_kontak");
$query= mysqli_query($konek,
        "SELECT * FROM kontak WHERE namaLengkap like '%".$cari."%'"
        );
$data=array();
while ($hasil= mysqli_fetch_array($query)){
    $data[]=array(
        'id_kontak'=>$hasil['id_kontak'],
        'namaLengkap'=>$hasil['namaLengkap'],
        'noHp'=>$hasil['noHp'],
    );
}
echo json_encode(array(
    'hasil'=>$data
));