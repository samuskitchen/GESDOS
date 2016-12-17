package com.agileillutions.gesdos.modelo.control;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamonapi.utils.AppBaseException;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * @author <a href="mailto:jhona.filigrana@premize.com">JDaniel De La Pava Suarez</a>
 * @project mso-service
 * @class JasperExportServiceImpl
 * @description
 * @date 10/12/2016
 */
@Service
public class JasperExportServiceImpl implements IJasperExportService {

	public static final String PDF_EXTENSIOIN = ".pdf";
	private static final Logger LOG = LoggerFactory
			.getLogger(JasperExportServiceImpl.class);

	@Autowired
	private DataSource dataSource;

	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">JDaniel De La Pava Suarez</a>
	 * @date 10/12/2016
	 * @param reportLocation
	 * @param parametros
	 * @return {@link ByteArrayOutputStream}
	 * @throws SQLException
	 * @throws JRException
	 * @throws AppBaseException
	 * @see com.agileillutions.gesdos.modelo.control.IJasperExportServic#generarPdf(java.io.InputStream,
	 *      java.util.Map)
	 */
	/**
	 */
	@Override
	public ByteArrayOutputStream generarPdf(InputStream reportLocation,
			Map<String, Object> parametros) throws AppBaseException,
			JRException, SQLException {
		basicValidation(reportLocation, parametros);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		Connection connection = dataSource.getConnection();
		JasperPrint jasperPrint = generarJasperPrint(connection,
				reportLocation, parametros);
		JasperExportManager.exportReportToPdfStream(jasperPrint,
				byteArrayOutputStream);
		connection.close();
		LOG.info("dataSource connection closed");
		return byteArrayOutputStream;
	}

	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Daniel De La Pava Suarez</a>
	 * @date 10/12/2016
	 * @param reportLocation
	 * @param beanCollection
	 * @param parametros
	 * @return {@link ByteArrayOutputStream}
	 * @throws JRException
	 * @throws AppBaseException
	 * @see com.agileillutions.gesdos.modelo.control.IJasperExportServic#generarPdf(java.io.InputStream,
	 *      java.util.List, java.util.Map)
	 */
	@Override
	public ByteArrayOutputStream generarPdf(InputStream reportLocation,
			List<?> beanCollection, Map<String, Object> parametros)
			throws JRException, AppBaseException {
		basicValidation(reportLocation, parametros);

		if (null == beanCollection) {
			throw new IllegalArgumentException(
					"Error al generar el reporte, bean collection es nulo");
		}

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		JRDataSource dataSourceTemp = new JRBeanCollectionDataSource(
				beanCollection);
		JasperPrint jasperPrint = generarJasperPrint(dataSourceTemp,
				reportLocation, parametros);
		JasperExportManager.exportReportToPdfStream(jasperPrint,
				byteArrayOutputStream);
		return byteArrayOutputStream;
	}

	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Daniel De La Pava Suarez</a>
	 * @date 10/12/2016
	 * @description
	 * @param connection
	 * @param reportLocation
	 * @param reportName
	 * @param parameters
	 * @return
	 * @throws JRException
	 */
	private JasperPrint generarJasperPrint(Connection connection,
			InputStream reportLocation, Map<String, Object> parameters)
			throws JRException {
		JasperReport jasperReport = (JasperReport) JRLoader
				.loadObject(reportLocation);
		JasperPrint jasperPrint;
		jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
				connection);
		return jasperPrint;
	}

	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Daniel De La Pava Suarez</a>
	 * @date 10/12/2016
	 * @description
	 * @param dataSource
	 * @param reportLocation
	 * @param reportName
	 * @param parameters
	 * @return
	 * @throws JRException
	 */
	private JasperPrint generarJasperPrint(JRDataSource dataSource,
			InputStream reportLocation, Map<String, Object> parameters)
			throws JRException {
		JasperReport jasperReport = (JasperReport) JRLoader
				.loadObject(reportLocation);
		return JasperFillManager.fillReport(jasperReport, parameters,
				dataSource);
	}

	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Daniel De La Pava Suarez</a>
	 * @date 10/12/2016
	 * @description
	 * @param reportLocation
	 * @param parametros
	 * @throws AppBaseException
	 */
	private void basicValidation(InputStream reportLocation,
			Map<String, Object> parametros) throws AppBaseException {
		if (null == reportLocation) {
			throw new IllegalArgumentException(
					"Error al generar el reporte, reportLocation es nulo");
		}

		if (null == parametros) {
			throw new IllegalArgumentException(
					"Erro al generar el reporte, El mapa de parametros es nulo");
		}
	}

	/**
	 * @author <a href="mailto:humbertoa.guerrero@premize.com">Daniel De La Pava Suarez</a>
	 * @date 10/12/2016
	 * @see com.agileillutions.gesdos.modelo.control.IJasperExportServic#generarExcel(java.io.InputStream,
	 *      java.util.List, java.util.Map)
	 */
	@Override
	public ByteArrayOutputStream generarExcel(InputStream reportLocation,
			List<?> beanCollection, Map<String, Object> parametros)
			throws JRException, AppBaseException {

		basicValidation(reportLocation, parametros);

		if (null == beanCollection) {
			throw new IllegalArgumentException(
					"Error al generar el reporte, bean collection es nulo");
		}

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		JRXlsExporter exporterXLS = new JRXlsExporter();
		JRDataSource dataSourceJR = new JRBeanCollectionDataSource(
				beanCollection);
		JasperPrint jasperPrint = generarJasperPrint(dataSourceJR,
				reportLocation, parametros);
		exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT,
				jasperPrint);
		exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,
				byteArrayOutputStream);
		exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
				Boolean.FALSE);
		exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,
				Boolean.TRUE);
		exporterXLS.setParameter(
				JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
		exporterXLS.setParameter(
				JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
				Boolean.TRUE);
		exporterXLS.exportReport();
		return byteArrayOutputStream;
	}

	/**
	 * @author <a href="mailto:humbertoa.guerrero@premize.com">Daniel De La Pava Suarez</a>
	 * @date 10/12/2016
	 * @see com.agileillutions.gesdos.modelo.control.IJasperExportServic#generarExcel(java.io.InputStream,
	 *      java.util.List, java.util.Map)
	 */
	@Override
	public ByteArrayOutputStream generarExcel(InputStream reportLocation,
			Map<String, Object> parametros) throws JRException,
			AppBaseException, SQLException {

		basicValidation(reportLocation, parametros);

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		JRXlsExporter exporterXLS = new JRXlsExporter();
		Connection connection = dataSource.getConnection();
		JasperPrint jasperPrint = generarJasperPrint(connection,
				reportLocation, parametros);
		exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT,
				jasperPrint);
		exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,
				byteArrayOutputStream);
		exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
				Boolean.FALSE);
		exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,
				Boolean.TRUE);
		exporterXLS.setParameter(
				JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
		exporterXLS.setParameter(
				JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
				Boolean.TRUE);
		exporterXLS.exportReport();
		return byteArrayOutputStream;
	}

	/**
	 * Metodo que compila el reporte PDF y lo retorna como un File
	 * 
	 * @author <a href="mailto:humbertoa.guerrero@premize.com">Daniel De La Pava Suarez</a>
	 * @date 10/12/2016
	 * @see com.agileillutions.gesdos.modelo.control.IJasperExportServic#generarPdf(java.io.InputStream,
	 *      java.util.Map, boolean)
	 */
	@Override
	public File generarPdf(InputStream reportLocation,
			Map<String, Object> parametros, String destfile)
			throws AppBaseException, JRException, SQLException {
		basicValidation(reportLocation, parametros);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		Connection connection = dataSource.getConnection();
		JasperPrint jasperPrint = generarJasperPrint(connection,
				reportLocation, parametros);
		JasperExportManager.exportReportToPdfStream(jasperPrint,
				byteArrayOutputStream);
		JasperExportManager.exportReportToPdfFile(jasperPrint, destfile);
		connection.close();
		return new File(destfile);
	}
}
