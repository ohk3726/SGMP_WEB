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
			// HSSFWorkbook�� �������� ��ü ������ ��� �ִ� ��ü
			workbook = new HSSFWorkbook(file.getInputStream());

			// Ž���� ����� Sheet, Row, Cell ��ü
			HSSFSheet curSheet;
			HSSFRow curRow;
			HSSFCell curCell;
			ProductVO vo;

			// Sheet Ž�� for��
			for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
				// ���� sheet ��ȯ
				curSheet = workbook.getSheetAt(sheetIndex);
				// row Ž�� for��
				for (int rowIndex = 0; rowIndex < curSheet.getPhysicalNumberOfRows(); rowIndex++) {
					// row 0�� ��������̱� ������ ����
					if (rowIndex != 0) {
						curRow = curSheet.getRow(rowIndex);
						vo = new ProductVO();
						String value;

						// row�� ù��° cell���� ������� �ʴ� ��츸 cellŽ��
						if (curRow.getCell(0) != null) {
							if (!"".equals(curRow.getCell(0).getStringCellValue())) {
								// cell Ž�� for��
								for (int cellIndex = 0; cellIndex < curRow.getPhysicalNumberOfCells(); cellIndex++) {
									curCell = curRow.getCell(cellIndex);

									if (true) {
										value = "";
										// cell ��Ÿ���� �ٸ����� String���� ��ȯ ����
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

										// ���� colum index�� ���� vo�Է�
										switch (cellIndex) {
										case 0: // ���̵�
											vo.setProd_id(value);
											break;
										case 1: // �̸�
											vo.setProd_name(value);
											break;
										case 2: // ����
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
									// cell Ž�� ���� vo �߰�
								if (0 == ExcelReader_id_same_count(vo)) {
									productMapper.xlsExcelReader(vo);
								} else {
									productMapper.Reader_modify_all(vo);
									System.out.println("��ǰID(" + vo.getProd_id() + ")�� �ߺ��Ǿ� ������ ����˴ϴ�.");
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

		// ��� insert

		return result_;
	}

	@Override
	@Transactional
	public int xlsxExcelReader(MultipartHttpServletRequest req) throws Exception {
		System.out.println("xlsxExcelReader");
		int result_ = 0;
		// ��ȯ�� ��ü�� ����
		List<ProductVO> list = new ArrayList<ProductVO>();

		MultipartFile file = req.getFile("excel");
		XSSFWorkbook workbook = null;
		ProductVO vo;

		try {
			// HSSFWorkbook�� �������� ��ü ������ ��� �ִ� ��ü
			workbook = new XSSFWorkbook(file.getInputStream());

			// Ž���� ����� Sheet, Row, Cell ��ü
			XSSFSheet curSheet;
			XSSFRow curRow;
			XSSFCell curCell;

			// Sheet Ž�� for��
			for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
				// ���� sheet ��ȯ
				curSheet = workbook.getSheetAt(sheetIndex);
				// row Ž�� for��
				for (int rowIndex = 0; rowIndex < curSheet.getPhysicalNumberOfRows(); rowIndex++) {
					// row 0�� ��������̱� ������ ����
					if (rowIndex != 0) {
						curRow = curSheet.getRow(rowIndex);
						vo = new ProductVO();
						String value;

						System.out.println("������");
						// row�� ù��° cell���� ������� �ʴ� ��츸 cellŽ��
						if (curRow.getCell(0) != null) {
							if (!"".equals(curRow.getCell(0).getStringCellValue())) {
								// cell Ž�� for��
								for (int cellIndex = 0; cellIndex < curRow.getPhysicalNumberOfCells(); cellIndex++) {
									curCell = curRow.getCell(cellIndex);
									System.out.println("��Ž��");
									if (true) {
										value = "";
										// cell ��Ÿ���� �ٸ����� String���� ��ȯ ����
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
										// ���� colum index�� ���� vo�Է�
										switch (cellIndex) {
										case 0: // ���̵�
											vo.setProd_id(value);
											break;
										case 1: // �̸�
											vo.setProd_name(value);
											break;
										case 2: // ����
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
											System.out.println("company_id");
											break;

										default:
											System.out.println("��");
											break;
										}
									} // end if

								} // end for
									// cell Ž�� ���� vo �߰�
								System.out.println(vo);
								if (0 == ExcelReader_id_same_count(vo)) {
									productMapper.xlsxExcelReader(vo);
								} else {
									productMapper.Reader_modify_all(vo);
									System.out.println("��ǰID(" + vo.getProd_id() + ")�� �ߺ��Ǿ� ������ ����˴ϴ�.");
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

		// ��� insert

		return result_;
	}

	@Override
	@Transactional
	public String xlsxExcelReader_modify(MultipartHttpServletRequest req) throws Exception {
		System.out.println("xlsxExcelReader_modify");

		// ��ȯ�� ��ü�� ����
		List<ProductVO> list = new ArrayList<ProductVO>();

		MultipartFile file = req.getFile("excel_2");
		XSSFWorkbook workbook = null;
		ProductVO vo;
		String result_ = "";
		try {
			// HSSFWorkbook�� �������� ��ü ������ ��� �ִ� ��ü
			workbook = new XSSFWorkbook(file.getInputStream());

			// Ž���� ����� Sheet, Row, Cell ��ü
			XSSFSheet curSheet;
			XSSFRow curRow;
			XSSFCell curCell;

			// Sheet Ž�� for��
			for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
				// ���� sheet ��ȯ
				curSheet = workbook.getSheetAt(sheetIndex);
				// row Ž�� for��
				for (int rowIndex = 0; rowIndex < curSheet.getPhysicalNumberOfRows(); rowIndex++) {
					// row 0�� ��������̱� ������ ����
					if (rowIndex != 0) {
						curRow = curSheet.getRow(rowIndex);
						vo = new ProductVO();
						String value;

						// row�� ù��° cell���� ������� �ʴ� ��츸 cellŽ��
						if (curRow.getCell(0) != null) {
							if (!"".equals(curRow.getCell(0).getStringCellValue())) {
								// cell Ž�� for��
								for (int cellIndex = 0; cellIndex < curRow.getPhysicalNumberOfCells(); cellIndex++) {
									curCell = curRow.getCell(cellIndex);

									System.out.println("����ΰ�");
									System.out.println(curCell);
									if (true) {
										value = "";
										// cell ��Ÿ���� �ٸ����� String���� ��ȯ ����
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

										// ���� colum index�� ���� vo�Է�
										switch (cellIndex) {
										case 0: // ���̵�
											vo.setProd_id(value);
											break;
										case 1: // �̸�
											vo.setProd_cnt(value);
											break;
										default:
											break;
										}
									} // end if

								} // end for
									// cell Ž�� ���� vo �߰�
								if (0 == ExcelReader_id_same_count(vo)) {
									System.out.println("��ǰ�� ID�� ��ϵ����� �ʽ��ϴ�.");
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

		// ��� insert

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
			// HSSFWorkbook�� �������� ��ü ������ ��� �ִ� ��ü
			workbook = new HSSFWorkbook(file.getInputStream());

			// Ž���� ����� Sheet, Row, Cell ��ü
			HSSFSheet curSheet;
			HSSFRow curRow;
			HSSFCell curCell;
			ProductVO vo;

			// Sheet Ž�� for��
			for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
				// ���� sheet ��ȯ
				curSheet = workbook.getSheetAt(sheetIndex);
				// row Ž�� for��
				for (int rowIndex = 0; rowIndex < curSheet.getPhysicalNumberOfRows(); rowIndex++) {
					// row 0�� ��������̱� ������ ����
					if (rowIndex != 0) {
						curRow = curSheet.getRow(rowIndex);
						vo = new ProductVO();
						String value;

						// row�� ù��° cell���� ������� �ʴ� ��츸 cellŽ��
						if (curRow.getCell(0) != null) {
							if (!"".equals(curRow.getCell(0).getStringCellValue())) {
								// cell Ž�� for��
								for (int cellIndex = 0; cellIndex < curRow.getPhysicalNumberOfCells(); cellIndex++) {
									curCell = curRow.getCell(cellIndex);

									if (true) {
										value = "";
										// cell ��Ÿ���� �ٸ����� String���� ��ȯ ����
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

										// ���� colum index�� ���� vo�Է�
										switch (cellIndex) {
										case 0: // ���̵�
											vo.setProd_id(value);
											break;
										case 1: // ����
											vo.setProd_cnt(value);
											break;
										default:
											break;
										}
									} // end if
								} // end for
									// cell Ž�� ���� vo �߰�
								if (0 == ExcelReader_id_same_count(vo)) {
									System.out.println("��ǰ�� ID�� ��ϵ����� �ʽ��ϴ�.");
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

		// ��� insert

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
