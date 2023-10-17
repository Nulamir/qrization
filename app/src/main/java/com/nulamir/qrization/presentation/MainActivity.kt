package com.nulamir.qrization.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.LayoutInflaterCompat
import androidx.lifecycle.ViewModelProvider
import com.nulamir.qrization.R
import com.nulamir.qrization.domain.SwagItem
import com.nulamir.qrization.ui.theme.QrizationTheme

class MainActivity : ComponentActivity() {


    private lateinit var viewModel: MainViewModel
    private lateinit var llShopList: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        llShopList = findViewById(R.id.ll_swag_list)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.swagList.observe(this){
            showList(it)
        }
    }
    private fun showList(list: List<SwagItem> ) {
        for(swagItem in list){
            val layoutId = R.layout.item_shop_enabled
            val view = LayoutInflater.from(this).inflate(layoutId,llShopList,false)
            val tvName = view.findViewById<TextView>(R.id.tv_name)
            tvName.text = swagItem.name
            val tvAccountNumber = view.findViewById<TextView>(R.id.tv_account_number)
            tvAccountNumber.text = swagItem.accountNumber
            val tvAccountLocation = view.findViewById<TextView>(R.id.tv_account_location)
            tvAccountLocation.text = swagItem.accountLocation
            val tvRealLocation = view.findViewById<TextView>(R.id.tv_real_location)
            tvRealLocation.text = swagItem.realLocation
            val tvUnit = view.findViewById<TextView>(R.id.tv_unit)
            tvUnit.text = swagItem.unit


            llShopList.addView(view)
        }
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