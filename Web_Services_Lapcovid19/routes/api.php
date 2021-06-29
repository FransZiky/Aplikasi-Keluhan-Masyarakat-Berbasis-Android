<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Account;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});

Route::get('account/index','AccountController@index');

Route::post('account/login','AccountController@login');

Route::post('account/register','AccountController@register');

Route::post('account/update', 'AccountController@update');

Route::post('cekKesehatan/create','CekKesehatanController@insert');

Route::get('cekKesehatan/index','CekKesehatanController@index');

Route::get('cekKesehatan/search/{username}', 'CekKesehatanController@search');

Route::get('informasi/index','InformasiController@index');

Route::post('informasi/store','InformasiController@store');

Route::get('keluhan/index', 'KeluhanController@index');

Route::post('keluhan/store', 'KeluhanController@store');

Route::get('keluhan/search/{username}', 'KeluhanController@penduduk');

Route::delete('keluhan/delete/{id_keluhan}','KeluhanController@delete');

Route::post('keluhan/update','KeluhanController@update');