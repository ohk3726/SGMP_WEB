package com.sgmp.web.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sgmp.web.dao.ProductDAO;
import com.sgmp.web.vo.ProductVO;

@Service("ProductService")
@Repository
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDAO productMapper;

	@Override
	@Transactional
	public List selectProductList() throws Exception {
		return productMapper.selectProductList();
	}

	@Override
	@Transactional
	public List selectedCompanyList_A(ProductVO vo) throws Exception {
		return productMapper.selectedCompanyList_A(vo);
	}

	@Override
	@Transactional
	public List prodList() throws Exception {
		return productMapper.prodList();
	}

	@Override
	@Transactional
	public List productInfo(ProductVO vo_2) throws Exception {
		return productMapper.productInfo(vo_2);
	}

	@Override
	@Transactional
	public int product_modify(ProductVO vo) throws Exception {
		return productMapper.product_modify(vo);
	}

	@Override
	@Transactional
	public int product_insert(ProductVO vo) throws Exception {
		return productMapper.product_insert(vo);
	}

	@Override
	@Transactional
	public List prod_search(ProductVO vo) throws Exception {
		return productMapper.prod_search(vo);
	}


	@Override
	@Transactional
	public int xlsExcelReader(MultipartHttpServletRequest req) throws Exception {
		List<ProductVO> list = new ArrayList<ProductVO>();
		int result_ = 0;
		MultipartFile file = req.getFile("excel");
		HSSFWorkbook workbook = null;

		try {
			// HSSFWorkbook은 엑셀파일 전체 내용을 담고 있는 객체
			workbook = new HSSFWorkbook(file.getInputStream());

			// 탐색에 사용할 Sheet, Row, Cell 객체
			HSSFSheet curSheet;
			HSSFRow curRow;
			HSSFCell curCell;
			ProductVO vo;

			// Sheet 탐색 for문
			for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
				// 현재 sheet 반환
				curSheet = workbook.getSheetAt(sheetIndex);
				// row 탐색 for문
				for (int rowIndex = 0; rowIndex < curSheet.getPhysicalNumberOfRows(); rowIndex++) {
					// row 0은 헤더정보이기 때문에 무시
					if (rowIndex != 0) {
						curRow = curSheet.getRow(rowIndex);
						vo = new ProductVO();
						String value;

						// row의 첫번째 cell값이 비어있지 않는 경우만 cell탐색
						if (curRow.getCell(0) != null) {
							if (!"".equals(curRow.getCell(0).getStringCellValue())) {
								// cell 탐색 for문
								for (int cellIndex = 0; cellIndex < curRow.getPhysicalNumberOfCells(); cellIndex++) {
									curCell = curRow.getCell(cellIndex);

									if (true) {
										value = "";
										// cell 스타일이 다르더라도 String으로 반환 받음
										switch (curCell.getCellType()) {
										case HSSFCell.CELL_TYPE_FORMULA:
											value = curCell.getCellFormula();
											break;
										case HSSFCell.CELL_TYPE_NUMERIC:
											value = curCell.getNumericCellValue() + "";
											break;
										case HSSFCell.CELL_TYPE_STRING:
											value = curCell.getStringCellValue() + "";
											break;
										case HSSFCell.CELL_TYPE_BLANK:
											value = curCell.getBooleanCellValue() + "";
											break;
										case HSSFCell.CELL_TYPE_ERROR:
											value = curCell.getErrorCellValue() + "";
											break;
										default:
											value = new String();
											break;
										} // end switch

										// 현재 colum index에 따라서 vo입력
										switch (cellIndex) {
										case 0: // 아이디
											vo.setProd_id(value);
											break;
										case 1: // 이름
											vo.setProd_name(value);
											break;
										case 2: // 가격
											vo.setProd_price(value);
											break;
										case 3: // prod_cnt
											vo.setProd_cnt(value);
											break;
										case 4: // prod_main_category
											vo.setProd_main_category(value);
											break;
										case 5: // prod_sub_category
											vo.setProd_sub_category(value);
											break;
										case 6: // prod_ssub_category
											vo.setProd_ssub_category(value);
											break;
										case 7: // prod_date
											vo.setProd_date(value);
											break;
										case 8: // prod_wearing_price
											vo.setProd_wearing_price(value);
											break;
										case 9: // prod_cnt_min
											vo.setProd_cnt_min(value);
											break;
										case 10: // company_id
											vo.setCompany_id(value);
											break;

										default:
											break;
										}
									} // end if
								} // end for
									// cell 탐색 이후 vo 추가
								if (0 == ExcelReader_id_same_count(vo)) {
									productMapper.xlsExcelReader(vo);
								} else {
									productMapper.Reader_modify_all(vo);
									System.out.println("제품ID(" + vo.getProd_id() + ")가 중복되어 수량만 변경됩니다.");
									result_ ++;
								}

								list.add(vo);
							} // end
						} // end if
					}

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 디비에 insert

		return result_;
	}

	@Override
	@Transactional
	public int xlsxExcelReader(MultipartHttpServletRequest req) throws Exception {
		System.out.println("xlsxExcelReader");
		int result_ = 0;
		// 반환할 객체를 생성
		List<ProductVO> list = new ArrayList<ProductVO>();

		MultipartFile file = req.getFile("excel");
		XSSFWorkbook workbook = null;
		ProductVO vo;

		try {
			// HSSFWorkbook은 엑셀파일 전체 내용을 담고 있는 객체
			workbook = new XSSFWorkbook(file.getInputStream());

			// 탐색에 사용할 Sheet, Row, Cell 객체
			XSSFSheet curSheet;
			XSSFRow curRow;
			XSSFCell curCell;

			// Sheet 탐색 for문
			for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
				// 현재 sheet 반환
				curSheet = workbook.getSheetAt(sheetIndex);
				// row 탐색 for문
				for (int rowIndex = 0; rowIndex < curSheet.getPhysicalNumberOfRows(); rowIndex++) {
					// row 0은 헤더정보이기 때문에 무시
					if (rowIndex != 0) {
						curRow = curSheet.getRow(rowIndex);
						vo = new ProductVO();
						String value;

						System.out.println("실행중");
						// row의 첫번째 cell값이 비어있지 않는 경우만 cell탐색
						if (curRow.getCell(0) != null) {
							if (!"".equals(curRow.getCell(0).getStringCellValue())) {
								// cell 탐색 for문
								for (int cellIndex = 0; cellIndex < curRow.getPhysicalNumberOfCells(); cellIndex++) {
									curCell = curRow.getCell(cellIndex);
									System.out.println("셀탐색");
									if (true) {
										value = "";
										// cell 스타일이 다르더라도 String으로 반환 받음
										switch (curCell.getCellType()) {
										case HSSFCell.CELL_TYPE_FORMULA:
											value = curCell.getCellFormula();
											break;
										case HSSFCell.CELL_TYPE_NUMERIC:
											value = curCell.getNumericCellValue() + "";
											break;
										case HSSFCell.CELL_TYPE_STRING:
											value = curCell.getStringCellValue() + "";
											break;
										case HSSFCell.CELL_TYPE_BLANK:
											value = curCell.getBooleanCellValue() + "";
											break;
										case HSSFCell.CELL_TYPE_ERROR:
											value = curCell.getErrorCellValue() + "";
											break;
										default:
											value = new String();
											break;
										} // end switch
										System.out.println("break");
										// 현재 colum index에 따라서 vo입력
										switch (cellIndex) {
										case 0: // 아이디
											vo.setProd_id(value);
											break;
										case 1: // 이름
											vo.setProd_name(value);
											break;
										case 2: // 가격
											vo.setProd_price(value);
											break;
										case 3: // prod_cnt
											vo.setProd_cnt(value);
											break;
										case 4: // prod_main_category
											vo.setProd_main_category(value);
											break;
										case 5: // prod_sub_category
											vo.setProd_sub_category(value);
											break;
										case 6: // prod_ssub_category
											vo.setProd_ssub_category(value);
											break;
										case 7: // prod_date
											vo.setProd_date(value);
											break;
										case 8: // prod_wearing_price
											vo.setProd_wearing_price(value);
											break;
										case 9: // prod_cnt_min
											vo.setProd_cnt_min(value);
											break;
										case 10: // company_id
											vo.setCompany_id(value);
											System.out.println(value);
											break;

										default:
											System.out.println("끝");
											break;
										}
									} // end if

								} // end for
									// cell 탐색 이후 vo 추가
								System.out.println(vo);
								if (0 == ExcelReader_id_same_count(vo)) {
									productMapper.xlsxExcelReader(vo);
								} else {
									productMapper.Reader_modify_all(vo);
									System.out.println("제품ID(" + vo.getProd_id() + ")가 중복되어 수량만 변경됩니다.");
									result_+=1;
								}

								list.add(vo);

							} // end
						} // end if
					}

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 디비에 insert

		return result_;
	}

	@Override
	@Transactional
	public String xlsxExcelReader_modify(MultipartHttpServletRequest req) throws Exception {
		System.out.println("xlsxExcelReader_modify");

		// 반환할 객체를 생성
		List<ProductVO> list = new ArrayList<ProductVO>();

		MultipartFile file = req.getFile("excel_2");
		XSSFWorkbook workbook = null;
		ProductVO vo;
		String result_ = "";
		try {
			// HSSFWorkbook은 엑셀파일 전체 내용을 담고 있는 객체
			workbook = new XSSFWorkbook(file.getInputStream());

			// 탐색에 사용할 Sheet, Row, Cell 객체
			XSSFSheet curSheet;
			XSSFRow curRow;
			XSSFCell curCell;

			// Sheet 탐색 for문
			for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
				// 현재 sheet 반환
				curSheet = workbook.getSheetAt(sheetIndex);
				// row 탐색 for문
				for (int rowIndex = 0; rowIndex < curSheet.getPhysicalNumberOfRows(); rowIndex++) {
					// row 0은 헤더정보이기 때문에 무시
					if (rowIndex != 0) {
						curRow = curSheet.getRow(rowIndex);
						vo = new ProductVO();
						String value;

						// row의 첫번째 cell값이 비어있지 않는 경우만 cell탐색
						if (curRow.getCell(0) != null) {
							if (!"".equals(curRow.getCell(0).getStringCellValue())) {
								// cell 탐색 for문
								for (int cellIndex = 0; cellIndex < curRow.getPhysicalNumberOfCells(); cellIndex++) {
									curCell = curRow.getCell(cellIndex);

									System.out.println("어디인가");
									System.out.println(curCell);
									if (true) {
										value = "";
										// cell 스타일이 다르더라도 String으로 반환 받음
										switch (curCell.getCellType()) {
										case HSSFCell.CELL_TYPE_FORMULA:
											value = curCell.getCellFormula();
											break;
										case HSSFCell.CELL_TYPE_NUMERIC:
											value = curCell.getNumericCellValue() + "";
											break;
										case HSSFCell.CELL_TYPE_STRING:
											value = curCell.getStringCellValue() + "";
											break;
										case HSSFCell.CELL_TYPE_BLANK:
											value = curCell.getBooleanCellValue() + "";
											break;
										case HSSFCell.CELL_TYPE_ERROR:
											value = curCell.getErrorCellValue() + "";
											break;
										default:
											value = new String();
											break;
										} // end switch

										// 현재 colum index에 따라서 vo입력
										switch (cellIndex) {
										case 0: // 아이디
											vo.setProd_id(value);
											break;
										case 1: // 이름
											vo.setProd_cnt(value);
											break;
										default:
											break;
										}
									} // end if

								} // end for
									// cell 탐색 이후 vo 추가
								if (0 == ExcelReader_id_same_count(vo)) {
									System.out.println("제품의 ID가 등록되있지 않습니다.");
									return vo.getProd_id();
								} else {										
									productMapper.xlsxExcelReader_modify(vo);
								}

								list.add(vo);

							} // end
						} // end if
					}

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 디비에 insert

		return result_;
	}

	@Override
	@Transactional
	public String xlsExcelReader_modify(MultipartHttpServletRequest req) throws Exception {
		List<ProductVO> list = new ArrayList<ProductVO>();
		String result_ = "";
		MultipartFile file = req.getFile("excel_2");
		HSSFWorkbook workbook = null;

		try {
			// HSSFWorkbook은 엑셀파일 전체 내용을 담고 있는 객체
			workbook = new HSSFWorkbook(file.getInputStream());

			// 탐색에 사용할 Sheet, Row, Cell 객체
			HSSFSheet curSheet;
			HSSFRow curRow;
			HSSFCell curCell;
			ProductVO vo;

			// Sheet 탐색 for문
			for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
				// 현재 sheet 반환
				curSheet = workbook.getSheetAt(sheetIndex);
				// row 탐색 for문
				for (int rowIndex = 0; rowIndex < curSheet.getPhysicalNumberOfRows(); rowIndex++) {
					// row 0은 헤더정보이기 때문에 무시
					if (rowIndex != 0) {
						curRow = curSheet.getRow(rowIndex);
						vo = new ProductVO();
						String value;

						// row의 첫번째 cell값이 비어있지 않는 경우만 cell탐색
						if (curRow.getCell(0) != null) {
							if (!"".equals(curRow.getCell(0).getStringCellValue())) {
								// cell 탐색 for문
								for (int cellIndex = 0; cellIndex < curRow.getPhysicalNumberOfCells(); cellIndex++) {
									curCell = curRow.getCell(cellIndex);

									if (true) {
										value = "";
										// cell 스타일이 다르더라도 String으로 반환 받음
										switch (curCell.getCellType()) {
										case HSSFCell.CELL_TYPE_FORMULA:
											value = curCell.getCellFormula();
											break;
										case HSSFCell.CELL_TYPE_NUMERIC:
											value = curCell.getNumericCellValue() + "";
											break;
										case HSSFCell.CELL_TYPE_STRING:
											value = curCell.getStringCellValue() + "";
											break;
										case HSSFCell.CELL_TYPE_BLANK:
											value = curCell.getBooleanCellValue() + "";
											break;
										case HSSFCell.CELL_TYPE_ERROR:
											value = curCell.getErrorCellValue() + "";
											break;
										default:
											value = new String();
											break;
										} // end switch

										// 현재 colum index에 따라서 vo입력
										switch (cellIndex) {
										case 0: // 아이디
											vo.setProd_id(value);
											break;
										case 1: // 개수
											vo.setProd_cnt(value);
											break;
										default:
											break;
										}
									} // end if
								} // end for
									// cell 탐색 이후 vo 추가
								if (0 == ExcelReader_id_same_count(vo)) {
									System.out.println("제품의 ID가 등록되있지 않습니다.");
									return vo.getProd_id();

								} else {
									productMapper.xlsExcelReader_modify(vo);
								}

								list.add(vo);
							} // end
						} // end if
					}

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 디비에 insert

		return result_;
	}

	@Override
	@Transactional
	public int ExcelReader_id_same_count(ProductVO vo) throws Exception {
		return productMapper.ExcelReader_id_same_count(vo);
	}

	public void Reader_modify_all(ProductVO vo) throws Exception {

	}

}
