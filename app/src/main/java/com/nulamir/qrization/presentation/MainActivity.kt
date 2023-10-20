package com.nulamir.qrization.presentation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.LayoutInflaterCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.nulamir.qrization.R
import com.nulamir.qrization.domain.SwagItem
import com.nulamir.qrization.ui.theme.QrizationTheme
import java.io.File
import java.lang.Exception

class MainActivity : ComponentActivity() {


    private lateinit var viewModel: MainViewModel
    private lateinit var llShopList: LinearLayout
    private lateinit var importExcelBtn: MaterialButton

    private companion object{
        //PERMISSION request constant, assign any value
        private const val STORAGE_PERMISSION_CODE = 100
        private const val TAG = "PERMISSION_TAG"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        llShopList = findViewById(R.id.ll_swag_list)
        importExcelBtn = findViewById(R.id.read_swag_list_from_excel)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.swagList.observe(this){
            showList(it)
        }

        //handle click, create folder
        importExcelBtn.setOnClickListener {
            if (checkPermission()){
                Log.d(TAG, "onCreate: Permission already granted, create folder")
                createFolder()
            }
            else{
                Log.d(TAG, "onCreate: Permission was not granted, request")
                requestPermission()
            }
        }

    }

    private fun createFolder(){

        viewModel.readSwagListFromExcel()

    }

    private fun showList(list: List<SwagItem> ) {
        for(swagItem in list){
            val layoutId = R.layout.item_shop_enabled
            val view = LayoutInflater.from(this).inflate(layoutId,llShopList,false)

            val tvId = view.findViewById<TextView>(R.id.tv_id)
            tvId.text = swagItem.id.toString().padStart(list.size.toString().length, '0')

            val tvName = view.findViewById<TextView>(R.id.tv_name)
            tvName.text = swagItem.name
            val tvAccountNumber = view.findViewById<TextView>(R.id.tv_account_number)
            tvAccountNumber.text = swagItem.accountNumber
            val tvAccountLocation = view.findViewById<TextView>(R.id.tv_account_location)
            tvAccountLocation.text = swagItem.accountLocation
            val tvRealLocation = view.findViewById<TextView>(R.id.tv_real_location)
            tvRealLocation.text = swagItem.realLocation
            val tvComment = view.findViewById<TextView>(R.id.tv_comment)
            tvComment.text = swagItem.comment
            val tvUnit = view.findViewById<TextView>(R.id.tv_unit)
            tvUnit.text = swagItem.unit


            llShopList.addView(view)
        }
    }


    private fun checkPermission(): Boolean{
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            //Android is 11(R) or above
            Environment.isExternalStorageManager()
        }
        else{
            //Android is below 11(R)
            val write = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            val read = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            write == PackageManager.PERMISSION_GRANTED && read == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun requestPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            //Android is 11(R) or above
            try {
                Log.d(TAG, "requestPermission: try")
                val intent = Intent()
                intent.action = Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION
                val uri = Uri.fromParts("package", this.packageName, null)
                intent.data = uri
                storageActivityResultLauncher.launch(intent)
            }
            catch (e: Exception){
                Log.e(TAG, "requestPermission: ", e)
                val intent = Intent()
                intent.action = Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION
                storageActivityResultLauncher.launch(intent)
            }
        }
        else{
            //Android is below 11(R)
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE),
                STORAGE_PERMISSION_CODE
            )
        }
    }

    private val storageActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        Log.d(TAG, "storageActivityResultLauncher: ")
        //here we will handle the result of our intent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            //Android is 11(R) or above
            if (Environment.isExternalStorageManager()){
                //Manage External Storage Permission is granted
                Log.d(TAG, "storageActivityResultLauncher: Manage External Storage Permission is granted")
                createFolder()
            }
            else{
                //Manage External Storage Permission is denied....
                Log.d(TAG, "storageActivityResultLauncher: Manage External Storage Permission is denied....")
                toast("Manage External Storage Permission is denied....")
            }
        }
        else{
            //Android is below 11(R)
        }
    }

    private fun toast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    QrizationTheme {
        Greeting("Android")
    }
}