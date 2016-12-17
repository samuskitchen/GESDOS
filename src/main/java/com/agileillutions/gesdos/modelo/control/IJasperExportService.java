package com.agileillutions.gesdos.modelo.control;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.jamonapi.utils.AppBaseException;

import net.sf.jasperreports.engine.JRException;

/**
 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a>
 * @project mso-service
 * @class JasperExportService
 * @description
 * @date 10/12/2016
 */
public interface IJasperExportService {

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">JDaniel De La Pava Suarez</a>
	 * @date 10/12/2016
	 * @description Genera el PDF usando las consultas(Query SQL) internas del
	 *              reporte
	 * @param reportLocation
	 * @param parametros
	 * @return {@link ByteArrayOutputStream}
	 * @throws SQLException
	 * @throws JRException
	 * @throws AppBaseException
	 */
	public ByteArrayOutputStream generarPdf(InputStream reportLocation,
			Map<String, Object> parametros) throws AppBaseException,
			JRException, SQLException;

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">JDaniel De La Pava Suarez</a>
	 * @date 10/12/2016
	 * @description Genera el PDF usando JavaBean DataSource (beanCollection)
	 * @param reportLocation
	 * @param beanCollection
	 * @param parametros
	 * @return {@link ByteArrayOutputStream}
	 * @throws JRException
	 * @throws AppBaseException
	 */
	ByteArrayOutputStream generarPdf(InputStream reportLocation,
			List<?> beanCollection, Map<String, Object> parametros)
			throws JRException, AppBaseException;

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a>
	 * @date 10/12/2016
	 * @param reportLocation
	 * @param beanCollection
	 * @param parametros
	 * @return
	 * @throws JRException
	 * @throws AppBaseException
	 */
	ByteArrayOutputStream generarExcel(InputStream reportLocation,
			List<?> beanCollection, Map<String, Object> parametros)
			throws JRException, AppBaseException;

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a>
	 * @date 10/12/2016
	 * @param reportLocation
	 * @param beanCollection
	 * @param parametros
	 * @return
	 * @throws JRException
	 * @throws AppBaseException
	 */
	ByteArrayOutputStream generarExcel(InputStream reportLocation,
			Map<String, Object> parametros) throws JRException,
			AppBaseException, SQLException;

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a>
	 * @date 10/12/2016
	 * @param reportLocation
	 * @param parametros
	 * @param destfile
	 * @return
	 * @throws AppBaseException
	 * @throws JRException
	 * @throws SQLException
	 */
	File generarPdf(InputStream reportLocation, Map<String, Object> parametros,
			String destfile) throws AppBaseException, JRException, SQLException;

}
