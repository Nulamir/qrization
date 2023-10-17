package com.nulamir.qrization.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nulamir.qrization.domain.SwagItem
import com.nulamir.qrization.domain.SwagListRepository
import java.lang.RuntimeException

object SwagListRepositoryImpl : SwagListRepository {

    private val swagListLD = MutableLiveData<List<SwagItem>>()
    private val swagList = mutableListOf<SwagItem>()


    private var autoIncrementId = 0

    init {
        for (i in 0 until 10) {
            val item = SwagItem("Монитор HP TFT Z23i (23\") LED AH-IPS Monitor (250 cd/m2,1000:1,8 ms,178/178,VGA,DVI-D,DisplayPort,US", "МЦ0018680", "202", "201" , "IT"  )
            addSwagItem(item)
        }
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
//        fun `when file is created then content is correct`() {
//            val workbook = XSSFWorkbook()
//            val workSheet = workbook.createSheet()
//            val cellStyle = workbook.createCellStyle()
//            cellStyle.fillForegroundColor = IndexedColors.RED.getIndex()
//            cellStyle.fillPattern = FillPatternType.SOLID_FOREGROUND
//            val firstCell = workSheet
//                .createRow(0)
//                .createCell(0)
//            firstCell.setCellValue("SAVED VALUE")
//            firstCell.cellStyle = cellStyle
//
//            val tempFile = createTempFile("test_output_", ".xlsx")
//            workbook.write(tempFile.outputStream())
//            workbook.close()
//
//            val inputWorkbook = WorkbookFactory.create(tempFile.toFile().inputStream())
//            val firstSheet = inputWorkbook.getSheetAt(0)
//            assertThat(firstSheet.getRow(0).getCell(0).stringCellValue).isEqualTo("SAVED VALUE")
//        }
    }

}