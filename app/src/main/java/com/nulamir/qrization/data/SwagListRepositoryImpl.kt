package com.nulamir.qrization.data

import android.os.Build
import android.os.Environment
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nulamir.qrization.Manifest
import com.nulamir.qrization.domain.SwagItem
import com.nulamir.qrization.domain.SwagListRepository
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.File
import java.io.FileInputStream


object SwagListRepositoryImpl : SwagListRepository {

    private val swagListLD = MutableLiveData<List<SwagItem>>()
    private val swagList = mutableListOf<SwagItem>()


    private var autoIncrementId = 0

    init {
        for (i in 0 until 10) {
            val item = SwagItem("Монитор HP TFT Z23i (23\") LED AH-IPS Monitor (250 cd/m2,1000:1,8 ms,178/178,VGA,DVI-D,DisplayPort,US", "МЦ0018680", "202", "201" , "IT"  )
            addSwagItem(item)
        }
        readSwagListFromExcel()
    }

    override fun addSwagItem(swagItem: SwagItem) {
        if (swagItem.id == SwagItem.UDEFINED_ID) {
            swagItem.id = autoIncrementId++
        }
        swagList.add(swagItem)
        updateSwagList()

    }

    override fun deleteSwagItem(swagItem: SwagItem) {
        swagList.remove(swagItem)
        updateSwagList()
    }

    override fun editSwagItem(swagItem: SwagItem) {

        val oldItem = getSwagItem(swagItem.id)
        deleteSwagItem(oldItem)
        addSwagItem(swagItem)

    }

    override fun getSwagList(): LiveData<List<SwagItem>> {
        return swagListLD
    }

    override fun getSwagItem(swagItemId: Int): SwagItem {
        return swagList.find {
            it.id == swagItemId
        } ?: throw RuntimeException("Element $swagItemId not found")
    }

    fun updateSwagList(){
        swagListLD.value = swagList.toList()
    }

    override fun readSwagListFromExcel() {

        val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        val filepath = "/storage/emulated/0/Download/list.xls"


        try{
            val inputStream = FileInputStream(filepath)

            var xlWb = WorkbookFactory.create(inputStream)
            //Row index specifies the row in the worksheet (starting at 0):
            val rowNumber = 0
            //Cell index specifies the column within the chosen row (starting at 0):
            val columnNumber = 0
            //Get reference to first sheet:
            val xlWs = xlWb.getSheetAt(0)
            val xlRows = xlWs.lastRowNum
            var i=0;
            while (i<= xlRows){
                println(xlWs.getRow(i).getCell(4))
                i++
            }

        } catch (exception: Exception){
            println(exception.message)
        }


        //Instantiate Excel workbook using existing file:



    }

}