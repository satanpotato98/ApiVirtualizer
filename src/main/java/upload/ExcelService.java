package upload;

import java.io.InputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExcelService {
	

	    public String processExcelFile(InputStream inputStream) {
	        try (Workbook workbook = WorkbookFactory.create(inputStream)) {
	            // Example: Read the first sheet and the first cell
	            String cellValue = workbook.getSheetAt(0).getRow(0).getCell(0).getStringCellValue();
	            return "Received Excel file. First cell value: " + cellValue;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "Error processing file: " + e.getMessage();
	        }
	    }
	}

