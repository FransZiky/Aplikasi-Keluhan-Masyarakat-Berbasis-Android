<?php

namespace App\Http\Controllers;

use App\keluhan;
use Illuminate\Http\Request;

class KeluhanController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $keluhan = keluhan::all();
        return response()->json([
            'keluhan'=>$keluhan
        ]);
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $keluhan = new Keluhan;

        $keluhan->pengirim = $request->pengirim;
        $keluhan->kategori = $request->kategori;
        $keluhan->tanggal = $request->tanggal;
        $keluhan->isi = $request->isi;
        $keluhan->status  = $request->status;

        $save = $keluhan->save();

        if(!$save){
            App::abort(500, 'Error');
       }else{
           $result["success"] = "1";
           $result["message"] = "success";
           echo json_encode($result);
       }
    }

    
    public function penduduk($username){
        $keluhan = keluhan::query()
        ->where('pengirim',$username)
        ->get();

        return response()->json([
            'keluhan'=>$keluhan
        ]); 
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\keluhan  $keluhan
     * @return \Illuminate\Http\Response
     */
    public function show(keluhan $keluhan)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\keluhan  $keluhan
     * @return \Illuminate\Http\Response
     */
    public function edit(keluhan $keluhan)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\keluhan  $keluhan
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request)
    {

        $id_keluhan = $request->id_keluhan;
        $keluhan = keluhan::find($id_keluhan);

        
        $keluhan->pengirim = $request->pengirim;
        $keluhan->kategori = $request->kategori;
        $keluhan->tanggal = $request->tanggal;
        $keluhan->isi = $request->isi;
        $keluhan->status  = $request->status;

        $update = $keluhan->update();

        if(!$update){
            App::abort(500, 'Error');
        }else{
            $result["success"] = "1";
            $result["message"] = "success";
            echo json_encode($result);
        }
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\keluhan  $keluhan
     * @return \Illuminate\Http\Response
     */
    public function delete($id_keluhan)
    {
        $keluhan = keluhan::find($id_keluhan);
        $delete = $keluhan->delete();

        if(!$delete){
            App::abort(500, 'Error');
        }else{
            $result["success"] = "1";
            $result["message"] = "success";
            echo json_encode($result);
        }
    }
}
