package com.nulamir.qrization.data

import android.os.Environment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nulamir.qrization.domain.SwagItem
import com.nulamir.qrization.domain.SwagListRepository
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.DateUtil
import org.apache.poi.ss.usermodel.FormulaEvaluator
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.FileInputStream


object SwagListRepositoryImpl : SwagListRepository {

    private val swagListLD = MutableLiveData<List<SwagItem>>()
    private val swagList = mutableListOf<SwagItem>()


    private var autoIncrementId = 2

//    init {
//        for (i in 0 until 10) {
//            val item = SwagItem("Монитор HP TFT Z23i (23\") LED AH-IPS Monitor (250 cd/m2,1000:1,8 ms,178/178,VGA,DVI-D,DisplayPort,US", "МЦ0018680", "202", "201" , "IT"  )
//            addSwagItem(item)
//        }
//      //  readSwagListFromExcel()
//    }

    override fun addSwagItem(swagItem: SwagItem) {
        if (swagItem.id == SwagItem.UDEFINED_ID) {
            swagItem.id = autoIncrementId++
        }
        swagList.add(swagItem)
        //updateSwagList()

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

        fun getVAlueFromFormula(cell: Cell): String {

           when (cell.getCellType()) {
                CellType.BOOLEAN -> return cell.booleanCellValue.toString()
                CellType.STRING -> return cell.stringCellValue.toString()
                CellType.NUMERIC -> if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.dateCellValue.toString()
                } else {
                    if (cell.numericCellValue is Double) {
                        return String.format("%.0f", cell.numericCellValue)
                    } else {
                        return "type error"
                    }
                }
                CellType.FORMULA -> {
                    //evaluator.evaluateFormulaCell(cell)
                    return cell.stringCellValue
                }
                else -> return ""
            }
        }


//        val folderName = "11156"
//        //create folder using name we just input
//        val file = File("${Environment.getExternalStorageDirectory()}/$folderName")
//        //create folder
//        val folderCreated = file.mkdir()


        val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        val filepath = "/storage/emulated/0/Download/list.xls"

        try{
            val inputStream = FileInputStream(filepath)

            var xlWb = WorkbookFactory.create(inputStream)

            swagList.clear()

            //Row index specifies the row in the worksheet (starting at 0):
            val rowNumber = 0
            //Cell index specifies the column within the chosen row (starting at 0):
            val columnNumber = 0
            //Get reference to first sheet:
            val xlWs = xlWb.getSheetAt(0)
            val xlRows = xlWs.lastRowNum
           // val evalutor  = xlWb.creationHelper.createFormulaEvaluator()
            var i=1;
            while (i<= xlRows){
                //println(xlWs.getRow(i).getCell(4))
                //Log.d("myTag", xlWs.getRow(i).getCell(4).toString());
                val item = SwagItem(getVAlueFromFormula(xlWs.getRow(i).getCell(3)), getVAlueFromFormula(xlWs.getRow(i).getCell(1)) , getVAlueFromFormula( xlWs.getRow(i).getCell(5)), "" , "IT"  )
                addSwagItem(item)
                i++
            }

//            for (i in 0 until 5) {
//                val item = SwagItem("Монитор HP TFT Z23i (23\") LED AH", "МЦ00186", "202", "201" , "IT"  )
//                addSwagItem(item)
//            }
            updateSwagList()

        } catch (exception: Exception){
            println(exception.message)
        }


        //Instantiate Excel workbook using existing file:



    }

}