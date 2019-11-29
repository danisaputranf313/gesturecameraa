package com.example.gesturecameraa

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //button clik
        btn_galery.setOnClickListener {
            var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            startActivityForResult(i,123)
        }


        btn_capture.setOnClickListener {
            //mengecek permision
            if
                    (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if
                        (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    //permission akses ditolak
                    val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);

                    requestPermissions(permission, PERMISSION_CODE);
                } else {
                    //permisiion akses dsetujui
                    pickImageFromGalerry()
                }
            } else {
                pickImageFromGalerry();
            }
        }
        }



    //method untuk mengambil image dr galeri
    private fun pickImageFromGalerry(){
    val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent,IMAGE_PICK_CODE)
    }
    companion object {
        private val IMAGE_PICK_CODE = 1000;
        private val PERMISSION_CODE = 1001;
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size >0 && grantResults[0]==
                        PackageManager.PERMISSION_GRANTED){
                    pickImageFromGalerry()
                }
                else{
                    Toast.makeText(this, "Permission denied",Toast.LENGTH_SHORT).show()

                }
            }
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            imageView.setImageURI(data?.data)
        }

        if (requestCode == 123)
        {
            var bmp= data?.extras?.get("data") as? Bitmap
            imageView.setImageBitmap(bmp)
        }
    }
}