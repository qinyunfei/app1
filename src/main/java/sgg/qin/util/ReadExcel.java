package sgg.qin.util;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;


/** 
 * @说明 Excel导入工具类 
 * @author xiaoqin
 */  

public class ReadExcel {
    //总行数
    private int totalRows = 0;  
    //总条数
    private int totalCells = 0; 
    
    private Sheet sheet;
    //错误信息接收器
    private String errorMsg;
    //文件
    private MultipartFile file;
    
    public ReadExcel(MultipartFile file) {
		this.file = file;
		try {
			//this.sheet = new HSSFWorkbook((FileInputStream) file.getInputStream()).getSheetAt(0);
			this.sheet = WorkbookFactory.create(file.getInputStream()).getSheetAt(0);
			this.totalRows=this.sheet.getPhysicalNumberOfRows();
	        this.totalCells=this.sheet.getRow(0).getPhysicalNumberOfCells();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    //获取总行数
    public int getTotalRows()  { return totalRows;} 
    //获取总列数
    public int getTotalCells() {  return totalCells;} 
    //获取错误信息-暂时未用到暂时留着
    public String getErrorInfo() { return errorMsg; }

/**
   * 读EXCEL文件，获取信息集合
   * @param fielName
   * @return
 * @throws IOException 
   */
  public List<Map<String, Object>> getExcelInfo(int i){

       //初始化客户信息的集合    
       List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
       list =readExcelValue(i);
      return list;
  }
 
  /**
   * 读取Excel里面客户的信息
   * @param wb
   * @return
   */
  private List<Map<String, Object>> readExcelValue(int i){ 

       List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();//声明一个对象集合  
      
      //循环Excel行数,从第二行开始。标题不入库
       for(int r=i;r<totalRows;r++){
           Row row = sheet.getRow(r);
           if (row == null) continue;
           Map<String, Object> map =new HashMap<String, Object>();
           List<String> lists=new ArrayList<String>();//声明一个对象集合     
           //循环Excel的列
           for(int c = 0; c <this.totalCells; c++){ 
               Cell cell = row.getCell(c);
               if (null != cell){
            	   map.put("s"+c, getValue(cell));
            	   lists.add(getValue(cell));
               }
           }
           map.put("lists", lists);
           //添加对象到集合中
           list.add(map);
       }
       return list;
  }
  
  /** 
   * 根据Cell类型设置数据 (以后要慢慢调试的)
   * @param cell 
   * @return 
   */  
  @SuppressWarnings({ "static-access", "unused" })
  private String getValue(Cell cell) {
      String strCell = "";
      switch (cell.getCellType()) {
      case Cell.CELL_TYPE_STRING:
          strCell = cell.getStringCellValue();  
          break;  
      case Cell.CELL_TYPE_NUMERIC:  
          strCell = String.valueOf(cell.getNumericCellValue());  
          break;  
      case Cell.CELL_TYPE_BOOLEAN:  
          strCell = String.valueOf(cell.getBooleanCellValue());  
          break;  
      default:  
    	  strCell = cell.getStringCellValue();  
          break;  
      }  

      return strCell;
  }
  
  
  



}