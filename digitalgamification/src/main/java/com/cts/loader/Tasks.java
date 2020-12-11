package com.cts.loader;

import com.cts.loader.model.DailyData;
import com.cts.loader.model.Participant;
import com.cts.loader.model.Winners;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@SuppressWarnings({"unchecked", "resource"})
public class Tasks {
  @PersistenceContext EntityManager entityManager;

  int party1votes = 0;
  int party2votes = 0;

  @Scheduled(cron = "*/30 * * * * *")
  public void read() throws SQLException {
    entityManager.createNativeQuery("truncate table participant").executeUpdate();
    Date date = Calendar.getInstance().getTime();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String strDate = dateFormat.format(date);
    Participant participant = null;

    try {
      File oldFile =
          new File("C:/Users/Micgogi/Desktop/cognizant_digital_minister/Sample data.xlsx");
      File newFile = new File("E:/Datasheet(CSV)" + strDate + ".xlsx");
      //			File oldFile = new File("/home/s729706/Sample data.xlsx");
      //			File newFile = new File("/home/s729706/backup/Datasheet(CSV)" + strDate + ".xlsx");

      FileInputStream excelFile = new FileInputStream(oldFile);
      Workbook workbook = new XSSFWorkbook(excelFile);
      Sheet datatypeSheet = workbook.getSheetAt(0);
      Iterator<Row> iterator = datatypeSheet.iterator();
      int total = 0;

      while (iterator.hasNext()) {

        Row currentRow = iterator.next();
        Iterator<Cell> cellIterator = currentRow.iterator();
        total = 0;
        if (currentRow.getRowNum() != 0 && !isEmptyRow(currentRow)) {
          participant = new Participant();
          while (cellIterator.hasNext()) {
            Cell currentCell = cellIterator.next();
            if (currentCell.getCellType() == CellType.STRING
                || currentCell.getCellType() == CellType.BLANK) {
              if (currentCell.getColumnIndex() == 1)
                participant.setAssociateName(currentCell.getStringCellValue());
              if (currentCell.getColumnIndex() > 2 && currentCell.getColumnIndex() < 25) {
                if (currentCell.getStringCellValue().equalsIgnoreCase("Completed")) {
                  total++;
                }
              }
            } else if (currentCell.getCellType() == CellType.NUMERIC)
              if (currentCell.getColumnIndex() == 0)
                participant.setPid(String.valueOf((int) currentCell.getNumericCellValue()));

            participant.setCompletion_status(total);
          }
          entityManager.persist(participant);
        }
      }
      this.updateWinnersTable();
      Files.copy(oldFile.toPath(), newFile.toPath());

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {

    }
  }

  public void updateWinnersTable() {

    List<String> constituencies =
        entityManager
            .createNativeQuery("select distinct constituency_name from master_data")
            .getResultList();
    constituencies.stream()
        .forEach(
            constituency -> {
              int total =
                  ((BigInteger)
                          entityManager
                              .createNativeQuery(
                                  "select count(associate_id) from master_data where constituency_name='"
                                      + constituency
                                      + "'")
                              .getSingleResult())
                      .intValue();
              Object party1 =
                  entityManager
                      .createNativeQuery(
                          "select sum(completion_status) as total_votes from participant inner join master_data "
                              + "where participant.associate_id=master_data.associate_id and constituency_name='"
                              + constituency
                              + "' "
                              + "and party_name='Party 1'")
                      .getSingleResult();
              if (party1 != null) party1votes = ((BigDecimal) party1).intValue();
              Object party2 =
                  entityManager
                      .createNativeQuery(
                          "select sum(completion_status) as total_votes from participant inner join master_data "
                              + "where participant.associate_id=master_data.associate_id and constituency_name='"
                              + constituency
                              + "' "
                              + "and party_name='Party 2'")
                      .getSingleResult();
              if (party2 != null) party2votes = ((BigDecimal) party2).intValue();
              int party1percentage = (party1votes * 100 / (total * 20));
              int party2percentage = (party2votes * 100 / (total * 20));
              DailyData daily = new DailyData(constituency, party1percentage, party2percentage);
              Object winner =
                  entityManager
                      .createNativeQuery(
                          "select count(*) from winners where constituency_name='"
                              + constituency
                              + "'")
                      .getSingleResult();
              if (((BigInteger) winner).intValue() == 0) {
                if (party1percentage >= 50 || party2percentage >= 50) {
                  if (party1percentage >= 50 && party2percentage >= 50) {
                    entityManager.persist(new Winners(constituency, "Drawn"));
                  } else if (party1percentage >= 50) {
                    entityManager.persist(new Winners(constituency, "Party 1"));
                  } else if (party2percentage >= 50) {
                    entityManager.persist(new Winners(constituency, "Party 2"));
                  }
                }
              }
              entityManager.persist(daily);
            });
  }

  boolean isEmptyRow(Row row) {
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
