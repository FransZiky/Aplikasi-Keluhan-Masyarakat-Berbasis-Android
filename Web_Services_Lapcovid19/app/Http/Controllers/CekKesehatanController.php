<?php

namespace App\Http\Controllers;

use App\CekKesehatan;
use Illuminate\Http\Request;
Use DB;

class CekKesehatanController extends Controller
{
    //

    public function insert(Request $request){
        $cek = new CekKesehatan;
        
        $cek->daftarpertanyaan_gejala = $request->gejala;
        $cek->daftarpertanyaan_aktivitas = $request->aktivitasi;
        $cek->username = $request->username;
        $cek->hasil = $request->hasil;
        $save = $cek->save();
        

        if(!$save){
            App::abort(500, 'Error');
        }else{
            $result["success"] = "1";
            $result["message"] = "success";
            echo json_encode($result);
        }

    }

    public function index(){
        $cekKesehatan = CekKesehatan::all();
        return response()->json([
            'cekKesehatan'=>$cekKesehatan
        ]);
    }

    public function search($username){
            $cekKesehatan = CekKesehatan::query()
            ->where('username', $username)
            ->get();
            return response()->json([
                'cekKesehatan'=>$cekKesehatan
            ]);

    }


}
