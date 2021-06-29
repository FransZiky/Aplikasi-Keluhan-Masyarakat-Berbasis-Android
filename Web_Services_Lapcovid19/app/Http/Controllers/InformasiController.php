<?php

namespace App\Http\Controllers;

use App\informasi;
use Illuminate\Http\Request;

class InformasiController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $informasi = informasi::all();
        return response()->json([
            'informasi'=>$informasi
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
        $informasi = new informasi;

        $informasi->judul = $request->judul;
        $informasi->tanggal = $request->tanggal;
        $informasi->kategori = $request->kategori;
        $informasi->isi = $request->isi;

        $save = $informasi->save();

        if(!$save){
             App::abort(500, 'Error');
        }else{
            $result["success"] = "1";
            $result["message"] = "success";
            echo json_encode($result);
        }
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\informasi  $informasi
     * @return \Illuminate\Http\Response
     */
    public function show(informasi $informasi)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\informasi  $informasi
     * @return \Illuminate\Http\Response
     */
    public function edit(informasi $informasi)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\informasi  $informasi
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, informasi $informasi)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\informasi  $informasi
     * @return \Illuminate\Http\Response
     */
    public function destroy(informasi $informasi)
    {
        //
    }
}
