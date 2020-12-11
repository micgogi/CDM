package com.cts.election;

import com.cts.election.entity.MasterData;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@SuppressWarnings({"resource"})
public class Reader {
  @PersistenceContext EntityManager entityManager;

  public void read() {

    MasterData masterData;
    entityManager.createNativeQuery("truncate table master_data").executeUpdate();
    try {
      // File oldFile = new
      // File("C:/Users/Micgogi/Desktop/cognizant_digital_minister/ConstituencyData_V1.0.xlsx");
      //			File oldFile = new File("/home/s729706/ConstituencyData_V1.0.xlsx");
      File oldFile = new File("/home/ubuntu/ConstituencyData_V1.0.xlsx");
      FileInputStream excelFile = new FileInputStream(oldFile);
      Workbook workbook = new XSSFWorkbook(excelFile);
      Sheet datatypeSheet = workbook.getSheetAt(0);
      Iterator<Row> iterator = datatypeSheet.iterator();

      while (iterator.hasNext()) {

        Row currentRow = iterator.next();
        Iterator<Cell> cellIterator = currentRow.iterator();

        if (currentRow.getRowNum() != 0 && !isEmptyRow(currentRow)) {
          masterData = new MasterData();
          while (cellIterator.hasNext()) {
            Cell currentCell = cellIterator.next();
            if (currentCell.getCellType() == CellType.STRING) {
              if (currentCell.getColumnIndex() == 1)
                masterData.setAssociateName(currentCell.getStringCellValue());
              if (currentCell.getColumnIndex() == 2)
                masterData.setConstituencyName(currentCell.getStringCellValue());
              if (currentCell.getColumnIndex() == 3)
                masterData.setPartyName(currentCell.getStringCellValue());

              if (currentCell.getColumnIndex() == 4)
                masterData.setGender(currentCell.getStringCellValue());
            } else if (currentCell.getCellType() == CellType.NUMERIC)
              if (currentCell.getColumnIndex() == 0)
                masterData.setAssociateId(String.valueOf((int) currentCell.getNumericCellValue()));
          }

          entityManager.persist(masterData);
        }
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {

    }
  }

  static boolean isEmptyRow(Row row) {
    boolean isEmptyRow = true;
    int cellNum = row.getFirstCellNum();
    Cell cell = row.getCell(cellNum);
    if (cell != null
        && cell.getCellType() != CellType.BLANK
        && StringUtils.isNotBlank(cell.toString())) {
      isEmptyRow = false;
    }
    return isEmptyRow;
  }
}
