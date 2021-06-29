<?php

namespace App\Http\Controllers;

use App\Account;
use Illuminate\Http\Request;
use DB;

class AccountController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        // return Account::all();
        $account = Account::all();
        return response()->json([
            'account'=>$account
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
        $account = new Account;
        // $account->
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Account  $account
     * @return \Illuminate\Http\Response
     */
    public function show(Account $account)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Account  $account
     * @return \Illuminate\Http\Response
     */
    public function edit(Account $account)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Account  $account
     * @return \Illuminate\Http\Response
     */
    public function update(request $request)
    {

    
        if($request->hasFile('foto')){
           $file= $request->file('foto');
           $file_size = $file->getSize();
           $file_extension = $file->getClientOriginalExtension();
           $file_name2 = $file->getClientOriginalName();
           $file_name = pathinfo($file_name2,PATHINFO_FILENAME);
           $tst_id =$file_name.str_pad( 1, 8, '0', STR_PAD_LEFT);
           $transaction_id = $tst_id;
           $new_file_name = $transaction_id.".".$file_extension;
        //    dd($new_file_name);
           $destination_path = public_path('/FotoProfil');
           $file->move($destination_path,$new_file_name);
           $username = $request->username;

           $account = Account::find($username);
           $image_path = public_path()."/FotoProfil/".$account->foto;
           if(file_exists($image_path)){
                unlink($image_path);
           }
   
           $account->foto = $new_file_name;
           $account->nama = $request->nama;
           $account->jk = $request->jk;
           $account->tanggallahir = $request->tanggallahir;
           $account->alamat  = $request->alamat;
           $account->nik = $request->nik;
           $account->password = $request->password;
           $account->pekerjaan = $request->pekerjaan;
           $account->update();
           $result["foto"] = $new_file_name;
           $result["success"] = "1";
           $result["message"] = "success";    
           echo json_encode($result);
        }else{
            $username = $request->username;

            $account = Account::find($username);
    
            $account->nama = $request->nama;
            $account->jk = $request->jk;
            $account->tanggallahir = $request->tanggallahir;
            $account->alamat  = $request->alamat;
            $account->nik = $request->nik;
            $account->password = $request->password;
            $account->pekerjaan = $request->pekerjaan;
            $account->update();
            $result["foto"] = $account->foto;
            $result["success"] = "1";
            $result["message"] = "success";    
            echo json_encode($result);
        }
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Account  $account
     * @return \Illuminate\Http\Response
     */
    public function destroy(Account $account)
    {
        //
    }

    public function login(Request $request){  

       
        $account = DB::table('account')
        ->where('username' , $request->username)
        ->where('password', $request->password)
        ->get();
         // return response()->json([
            //     'account' =>$account 
            // ]);
           
        if (count($account) > 0) {
            foreach ($account as $logg) {
             $result["success"] = "1";
             $result["message"] = "success";
             //untuk memanggil data sesi Login
             $result["nama"] = $logg->nama;
             $result["username"] = $logg->username;
             $result["password"] = $logg->password;
             $result["jk"] = $logg->jk;
             $result["tanggallahir"] = $logg->tanggallahir;
             $result["perkerjaan"] = $logg->pekerjaan;
             $result["foto"] = $logg->foto;
             $result["nik"] = $logg->nik;
             $result["alamat"] = $logg->alamat;
             $result["role"] = $logg->role;
            }
            echo json_encode($result);  
           } else {
            $result["success"] = "0";
            $result["message"] = "error";
            echo json_encode($result);
           }

           
    }

    public function register(Request $request){
        $account = new Account;
        if(count(DB::table('account')->where('username', $request->username)->get())>0){
            $result["success"] = "0";
            $result["message"] = "error";
            echo json_encode($result);
        }else{
        $account->nama = $request->nama;
        $account->jk = $request->jk;
        $account->tanggallahir = $request->tanggallahir;
        $account->alamat  = $request->alamat;
        $account->nik = $request->nik;
        $account->username = $request->username;
        $account->password = $request->password;
        $account->pekerjaan = $request->pekerjaan;
        $account->foto = $request->foto;
        $account->role = $request->role;
        $account->save();
        $result["success"] = "1";
        $result["message"] = "success";
        echo json_encode($result);
        }

    }

}
