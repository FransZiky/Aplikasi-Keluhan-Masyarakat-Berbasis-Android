<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class keluhan extends Model
{
    protected $table = "keluhan";
    public $timestamps = false;

    protected $primaryKey = 'id_keluhan'; 
}
