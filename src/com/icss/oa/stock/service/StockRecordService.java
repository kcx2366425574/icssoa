package com.icss.oa.stock.service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
import com.icss.oa.stock.dao.StockMapper;
import com.icss.oa.stock.dao.StockRecordMapper;
import com.icss.oa.stock.pojo.Stock;
import com.icss.oa.stock.pojo.StockRecord;

@Service
@Transactional(rollbackFor = Exception.class)
public class StockRecordService {
	
	@Autowired
	private StockRecordMapper mapper;
	
	@Autowired
	private StockMapper stockMapper;
	
	//增加数据
	public void addSrd(StockRecord stockRecord) {
		mapper.insert(stockRecord);
		Integer srNum = stockRecord.getSrNum();
		Integer srStockId = stockRecord.getStock().getStockId();
		Stock stock = stockMapper.queryById(srStockId);
		Integer stockUsedNum = stock.getStockUsedNum();
		Integer count = srNum + stockUsedNum;
		stock.setStockUsedNum(count);
		stockMapper.update(stock);
	}
	
	//删除数据
	public void deleteSrd(Integer srId) {
		mapper.delete(srId);
	}
	
	//修改数据
	public void updateSrd(StockRecord stockRecord) {
		mapper.update(stockRecord);
	}
	
	//根据id查询数据
	@Transactional(readOnly = true)
	public StockRecord querySrdById(Integer srId) {
		return mapper.queryById(srId);
	}
	
	//条件获取记录数
	@Transactional(readOnly = true)
	public Integer getSrdCount(Integer srDeptId, Integer srStockId, String srTime) {
		return mapper.getCount(srDeptId, srStockId, srTime);
	}
	
	//条件查询
	@Transactional(readOnly = true)
	public List<StockRecord> querySrdByCond(Pager pager, Integer srDeptId, Integer srStockId, String srTime) {
		return mapper.queryByCond(pager.getStart(), pager.getPageSize(), srDeptId, srStockId, srTime);
	}
	
	//导出excel报表
	@Transactional(readOnly = true)
	public void exportExcel(OutputStream out, Pager pager, Integer srDeptId, Integer srStockId, String srTime) throws IOException {
		
		// 工作簿对象
		HSSFWorkbook wb = new HSSFWorkbook();

		// 工作表对象
		HSSFSheet sheet1 = wb.createSheet("库存物品申请数据");
		
		// 行对象
		HSSFRow headerRow = sheet1.createRow(0);
	
		headerRow.createCell(0).setCellValue("记录编号");
		headerRow.createCell(1).setCellValue("申请部门名称");
		headerRow.createCell(2).setCellValue("申请物品名称");
		headerRow.createCell(3).setCellValue("申请物品数量");
		headerRow.createCell(4).setCellValue("申请物品时间");
		
		//获得库存记录数据列表
		List<StockRecord> list = mapper.queryByCond(pager.getStart(), pager.getPageSize(), srDeptId, srStockId, srTime);
		
		//遍历数据
		for (int i = 0;i < list.size();i ++) {
			StockRecord srd = list.get(i);
			System.out.println(srd);
			HSSFRow row = sheet1.createRow(i+1);
			
			HSSFCell cell4 = row.createCell(4);
			
			//建立新的cell样式
			HSSFCellStyle cellStyle = wb.createCellStyle(); 
			cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
			//设置值
			cell4.setCellStyle(cellStyle);//应用样式
			
			row.createCell(0).setCellValue(srd.getSrId());
			row.createCell(1).setCellValue(srd.getDept().getDeptName());
			row.createCell(2).setCellValue(srd.getStock().getStockName());
			row.createCell(3).setCellValue(srd.getSrNum());
//			row.createCell(4).setCellValue(srd.getSrTime());
			cell4.setCellValue(srd.getSrTime());
		}
		
		//写入到数据流中
		wb.write(out);
		out.close();
	}

}
