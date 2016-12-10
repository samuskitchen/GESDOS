package com.agileillutions.gesdos.presentation.businessDelegate;

import java.util.List;

import com.agileillutions.gesdos.modelo.Cargo;
import com.agileillutions.gesdos.modelo.Ciuda;
import com.agileillutions.gesdos.modelo.CiudaId;
import com.agileillutions.gesdos.modelo.ContratoDosimetro;
import com.agileillutions.gesdos.modelo.Contratos;
import com.agileillutions.gesdos.modelo.ContratosId;
import com.agileillutions.gesdos.modelo.Depar;
import com.agileillutions.gesdos.modelo.DetalleContrato;
import com.agileillutions.gesdos.modelo.DetalleContratoId;
import com.agileillutions.gesdos.modelo.Dosimetro;
import com.agileillutions.gesdos.modelo.DosimetroId;
import com.agileillutions.gesdos.modelo.Empresas;
import com.agileillutions.gesdos.modelo.Estudios;
import com.agileillutions.gesdos.modelo.EstudiosId;
import com.agileillutions.gesdos.modelo.Factura;
import com.agileillutions.gesdos.modelo.FacturaId;
import com.agileillutions.gesdos.modelo.Facturapago;
import com.agileillutions.gesdos.modelo.FacturapagoId;
import com.agileillutions.gesdos.modelo.Geominas;
import com.agileillutions.gesdos.modelo.Grupdet;
import com.agileillutions.gesdos.modelo.GrupdetId;
import com.agileillutions.gesdos.modelo.Grupos;
import com.agileillutions.gesdos.modelo.Numer;
import com.agileillutions.gesdos.modelo.Observa;
import com.agileillutions.gesdos.modelo.Permisos;
import com.agileillutions.gesdos.modelo.PermisosId;
import com.agileillutions.gesdos.modelo.Practica;
import com.agileillutions.gesdos.modelo.Programa1;
import com.agileillutions.gesdos.modelo.Programa1Id;
import com.agileillutions.gesdos.modelo.Programas;
import com.agileillutions.gesdos.modelo.Radiacion;
import com.agileillutions.gesdos.modelo.Revelado;
import com.agileillutions.gesdos.modelo.Socios;
import com.agileillutions.gesdos.modelo.Traba;
import com.agileillutions.gesdos.modelo.Ubicacion;
import com.agileillutions.gesdos.modelo.Usuarios;
import com.agileillutions.gesdos.modelo.dto.CargoDTO;
import com.agileillutions.gesdos.modelo.dto.CiudaDTO;
import com.agileillutions.gesdos.modelo.dto.ContratoDosimetroDTO;
import com.agileillutions.gesdos.modelo.dto.ContratosDTO;
import com.agileillutions.gesdos.modelo.dto.DeparDTO;
import com.agileillutions.gesdos.modelo.dto.DetalleContratoDTO;
import com.agileillutions.gesdos.modelo.dto.DosimetroDTO;
import com.agileillutions.gesdos.modelo.dto.EmpresasDTO;
import com.agileillutions.gesdos.modelo.dto.EstudiosDTO;
import com.agileillutions.gesdos.modelo.dto.FacturaDTO;
import com.agileillutions.gesdos.modelo.dto.FacturapagoDTO;
import com.agileillutions.gesdos.modelo.dto.GeominasDTO;
import com.agileillutions.gesdos.modelo.dto.GrupdetDTO;
import com.agileillutions.gesdos.modelo.dto.GruposDTO;
import com.agileillutions.gesdos.modelo.dto.NumerDTO;
import com.agileillutions.gesdos.modelo.dto.ObservaDTO;
import com.agileillutions.gesdos.modelo.dto.PermisosDTO;
import com.agileillutions.gesdos.modelo.dto.PracticaDTO;
import com.agileillutions.gesdos.modelo.dto.Programa1DTO;
import com.agileillutions.gesdos.modelo.dto.ProgramasDTO;
import com.agileillutions.gesdos.modelo.dto.RadiacionDTO;
import com.agileillutions.gesdos.modelo.dto.ReveladoDTO;
import com.agileillutions.gesdos.modelo.dto.SociosDTO;
import com.agileillutions.gesdos.modelo.dto.TrabaDTO;
import com.agileillutions.gesdos.modelo.dto.UbicacionDTO;
import com.agileillutions.gesdos.modelo.dto.UsuariosDTO;

/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *         www.zathuracode.org
 *
 */
public interface IBusinessDelegatorView {
	public List<Cargo> getCargo() throws Exception;

	public void saveCargo(Cargo entity) throws Exception;

	public void deleteCargo(Cargo entity) throws Exception;

	public void updateCargo(Cargo entity) throws Exception;

	public Cargo getCargo(String carCod) throws Exception;

	public List<Cargo> findByCriteriaInCargo(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<Cargo> findPageCargo(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberCargo() throws Exception;

	public List<CargoDTO> getDataCargo() throws Exception;

	public List<Ciuda> getCiuda() throws Exception;

	public void saveCiuda(Ciuda entity) throws Exception;

	public void deleteCiuda(Ciuda entity) throws Exception;

	public void updateCiuda(Ciuda entity) throws Exception;

	public Ciuda getCiuda(CiudaId id) throws Exception;

	public List<Ciuda> findByCriteriaInCiuda(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<Ciuda> findPageCiuda(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberCiuda() throws Exception;

	public List<CiudaDTO> getDataCiuda() throws Exception;

	public List<ContratoDosimetro> getContratoDosimetro() throws Exception;

	public void saveContratoDosimetro(ContratoDosimetro entity)
			throws Exception;

	public void deleteContratoDosimetro(ContratoDosimetro entity)
			throws Exception;

	public void updateContratoDosimetro(ContratoDosimetro entity)
			throws Exception;

	public ContratoDosimetro getContratoDosimetro(Long conDosi)
			throws Exception;

	public List<ContratoDosimetro> findByCriteriaInContratoDosimetro(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<ContratoDosimetro> findPageContratoDosimetro(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberContratoDosimetro() throws Exception;

	public List<ContratoDosimetroDTO> getDataContratoDosimetro()
			throws Exception;

	public List<Contratos> getContratos() throws Exception;

	public void saveContratos(Contratos entity) throws Exception;

	public void deleteContratos(Contratos entity) throws Exception;

	public void updateContratos(Contratos entity) throws Exception;

	public Contratos getContratos(ContratosId id) throws Exception;

	public List<Contratos> findByCriteriaInContratos(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<Contratos> findPageContratos(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberContratos() throws Exception;

	public List<ContratosDTO> getDataContratos() throws Exception;

	public List<Depar> getDepar() throws Exception;

	public void saveDepar(Depar entity) throws Exception;

	public void deleteDepar(Depar entity) throws Exception;

	public void updateDepar(Depar entity) throws Exception;

	public Depar getDepar(Long depCod) throws Exception;

	public List<Depar> findByCriteriaInDepar(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<Depar> findPageDepar(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberDepar() throws Exception;

	public List<DeparDTO> getDataDepar() throws Exception;

	public List<DetalleContrato> getDetalleContrato() throws Exception;

	public void saveDetalleContrato(DetalleContrato entity) throws Exception;

	public void deleteDetalleContrato(DetalleContrato entity) throws Exception;

	public void updateDetalleContrato(DetalleContrato entity) throws Exception;

	public DetalleContrato getDetalleContrato(DetalleContratoId id)
			throws Exception;

	public List<DetalleContrato> findByCriteriaInDetalleContrato(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<DetalleContrato> findPageDetalleContrato(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberDetalleContrato() throws Exception;

	public List<DetalleContratoDTO> getDataDetalleContrato() throws Exception;

	public List<Dosimetro> getDosimetro() throws Exception;

	public void saveDosimetro(Dosimetro entity) throws Exception;

	public void deleteDosimetro(Dosimetro entity) throws Exception;

	public void updateDosimetro(Dosimetro entity) throws Exception;

	public Dosimetro getDosimetro(DosimetroId id) throws Exception;

	public List<Dosimetro> findByCriteriaInDosimetro(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<Dosimetro> findPageDosimetro(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberDosimetro() throws Exception;

	public List<DosimetroDTO> getDataDosimetro() throws Exception;

	public List<Empresas> getEmpresas() throws Exception;

	public void saveEmpresas(Empresas entity) throws Exception;

	public void deleteEmpresas(Empresas entity) throws Exception;

	public void updateEmpresas(Empresas entity) throws Exception;

	public Empresas getEmpresas(Long empCod) throws Exception;

	public List<Empresas> findByCriteriaInEmpresas(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<Empresas> findPageEmpresas(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberEmpresas() throws Exception;

	public List<EmpresasDTO> getDataEmpresas() throws Exception;

	public List<Estudios> getEstudios() throws Exception;

	public void saveEstudios(Estudios entity) throws Exception;

	public void deleteEstudios(Estudios entity) throws Exception;

	public void updateEstudios(Estudios entity) throws Exception;

	public Estudios getEstudios(EstudiosId id) throws Exception;

	public List<Estudios> findByCriteriaInEstudios(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<Estudios> findPageEstudios(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberEstudios() throws Exception;

	public List<EstudiosDTO> getDataEstudios() throws Exception;

	public List<Factura> getFactura() throws Exception;

	public void saveFactura(Factura entity) throws Exception;

	public void deleteFactura(Factura entity) throws Exception;

	public void updateFactura(Factura entity) throws Exception;

	public Factura getFactura(FacturaId id) throws Exception;

	public List<Factura> findByCriteriaInFactura(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<Factura> findPageFactura(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberFactura() throws Exception;

	public List<FacturaDTO> getDataFactura() throws Exception;

	public List<Facturapago> getFacturapago() throws Exception;

	public void saveFacturapago(Facturapago entity) throws Exception;

	public void deleteFacturapago(Facturapago entity) throws Exception;

	public void updateFacturapago(Facturapago entity) throws Exception;

	public Facturapago getFacturapago(FacturapagoId id) throws Exception;

	public List<Facturapago> findByCriteriaInFacturapago(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<Facturapago> findPageFacturapago(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberFacturapago() throws Exception;

	public List<FacturapagoDTO> getDataFacturapago() throws Exception;

	public List<Geominas> getGeominas() throws Exception;

	public void saveGeominas(Geominas entity) throws Exception;

	public void deleteGeominas(Geominas entity) throws Exception;

	public void updateGeominas(Geominas entity) throws Exception;

	public Geominas getGeominas(String geoCod) throws Exception;

	public List<Geominas> findByCriteriaInGeominas(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<Geominas> findPageGeominas(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberGeominas() throws Exception;

	public List<GeominasDTO> getDataGeominas() throws Exception;

	public List<Grupdet> getGrupdet() throws Exception;

	public void saveGrupdet(Grupdet entity) throws Exception;

	public void deleteGrupdet(Grupdet entity) throws Exception;

	public void updateGrupdet(Grupdet entity) throws Exception;

	public Grupdet getGrupdet(GrupdetId id) throws Exception;

	public List<Grupdet> findByCriteriaInGrupdet(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<Grupdet> findPageGrupdet(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberGrupdet() throws Exception;

	public List<GrupdetDTO> getDataGrupdet() throws Exception;

	public List<Grupos> getGrupos() throws Exception;

	public void saveGrupos(Grupos entity) throws Exception;

	public void deleteGrupos(Grupos entity) throws Exception;

	public void updateGrupos(Grupos entity) throws Exception;

	public Grupos getGrupos(String grupCodigo) throws Exception;

	public List<Grupos> findByCriteriaInGrupos(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<Grupos> findPageGrupos(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberGrupos() throws Exception;

	public List<GruposDTO> getDataGrupos() throws Exception;

	public List<Numer> getNumer() throws Exception;

	public void saveNumer(Numer entity) throws Exception;

	public void deleteNumer(Numer entity) throws Exception;

	public void updateNumer(Numer entity) throws Exception;

	public Numer getNumer(Long numCod) throws Exception;

	public List<Numer> findByCriteriaInNumer(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<Numer> findPageNumer(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberNumer() throws Exception;

	public List<NumerDTO> getDataNumer() throws Exception;

	public List<Observa> getObserva() throws Exception;

	public void saveObserva(Observa entity) throws Exception;

	public void deleteObserva(Observa entity) throws Exception;

	public void updateObserva(Observa entity) throws Exception;

	public Observa getObserva(String obsCod) throws Exception;

	public List<Observa> findByCriteriaInObserva(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<Observa> findPageObserva(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberObserva() throws Exception;

	public List<ObservaDTO> getDataObserva() throws Exception;

	public List<Permisos> getPermisos() throws Exception;

	public void savePermisos(Permisos entity) throws Exception;

	public void deletePermisos(Permisos entity) throws Exception;

	public void updatePermisos(Permisos entity) throws Exception;

	public Permisos getPermisos(PermisosId id) throws Exception;

	public List<Permisos> findByCriteriaInPermisos(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<Permisos> findPagePermisos(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberPermisos() throws Exception;

	public List<PermisosDTO> getDataPermisos() throws Exception;

	public List<Practica> getPractica() throws Exception;

	public void savePractica(Practica entity) throws Exception;

	public void deletePractica(Practica entity) throws Exception;

	public void updatePractica(Practica entity) throws Exception;

	public Practica getPractica(String praCod) throws Exception;

	public List<Practica> findByCriteriaInPractica(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<Practica> findPagePractica(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberPractica() throws Exception;

	public List<PracticaDTO> getDataPractica() throws Exception;

	public List<Programa1> getPrograma1() throws Exception;

	public void savePrograma1(Programa1 entity) throws Exception;

	public void deletePrograma1(Programa1 entity) throws Exception;

	public void updatePrograma1(Programa1 entity) throws Exception;

	public Programa1 getPrograma1(Programa1Id id) throws Exception;

	public List<Programa1> findByCriteriaInPrograma1(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<Programa1> findPagePrograma1(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberPrograma1() throws Exception;

	public List<Programa1DTO> getDataPrograma1() throws Exception;

	public List<Programas> getProgramas() throws Exception;

	public void saveProgramas(Programas entity) throws Exception;

	public void deleteProgramas(Programas entity) throws Exception;

	public void updateProgramas(Programas entity) throws Exception;

	public Programas getProgramas(String progCodigo) throws Exception;

	public List<Programas> findByCriteriaInProgramas(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<Programas> findPageProgramas(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberProgramas() throws Exception;

	public List<ProgramasDTO> getDataProgramas() throws Exception;

	public List<Radiacion> getRadiacion() throws Exception;

	public void saveRadiacion(Radiacion entity) throws Exception;

	public void deleteRadiacion(Radiacion entity) throws Exception;

	public void updateRadiacion(Radiacion entity) throws Exception;

	public Radiacion getRadiacion(String radCod) throws Exception;

	public List<Radiacion> findByCriteriaInRadiacion(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<Radiacion> findPageRadiacion(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberRadiacion() throws Exception;

	public List<RadiacionDTO> getDataRadiacion() throws Exception;

	public List<Revelado> getRevelado() throws Exception;

	public void saveRevelado(Revelado entity) throws Exception;

	public void deleteRevelado(Revelado entity) throws Exception;

	public void updateRevelado(Revelado entity) throws Exception;

	public Revelado getRevelado(Long revNro) throws Exception;

	public List<Revelado> findByCriteriaInRevelado(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<Revelado> findPageRevelado(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberRevelado() throws Exception;

	public List<ReveladoDTO> getDataRevelado() throws Exception;

	public List<Socios> getSocios() throws Exception;

	public void saveSocios(Socios entity) throws Exception;

	public void deleteSocios(Socios entity) throws Exception;

	public void updateSocios(Socios entity) throws Exception;

	public Socios getSocios(String socCod) throws Exception;

	public List<Socios> findByCriteriaInSocios(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<Socios> findPageSocios(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberSocios() throws Exception;

	public List<SociosDTO> getDataSocios() throws Exception;

	public List<Traba> getTraba() throws Exception;

	public void saveTraba(Traba entity) throws Exception;

	public void deleteTraba(Traba entity) throws Exception;

	public void updateTraba(Traba entity) throws Exception;

	public Traba getTraba(Long traCed) throws Exception;

	public List<Traba> findByCriteriaInTraba(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<Traba> findPageTraba(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberTraba() throws Exception;

	public List<TrabaDTO> getDataTraba() throws Exception;

	public List<Ubicacion> getUbicacion() throws Exception;

	public void saveUbicacion(Ubicacion entity) throws Exception;

	public void deleteUbicacion(Ubicacion entity) throws Exception;

	public void updateUbicacion(Ubicacion entity) throws Exception;

	public Ubicacion getUbicacion(String ubiCod) throws Exception;

	public List<Ubicacion> findByCriteriaInUbicacion(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<Ubicacion> findPageUbicacion(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberUbicacion() throws Exception;

	public List<UbicacionDTO> getDataUbicacion() throws Exception;

	public List<Usuarios> getUsuarios() throws Exception;

	public void saveUsuarios(Usuarios entity) throws Exception;

	public void deleteUsuarios(Usuarios entity) throws Exception;

	public void updateUsuarios(Usuarios entity) throws Exception;

	public Usuarios getUsuarios(String usuaCodigo) throws Exception;

	public List<Usuarios> findByCriteriaInUsuarios(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<Usuarios> findPageUsuarios(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberUsuarios() throws Exception;

	public List<UsuariosDTO> getDataUsuarios() throws Exception;

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 27/11/2016
	 * @description 
	 * @param codEmpresa
	 * @return
	 * @throws Exception
	 */
	public List<TrabaDTO> getDataTrabaEmpresa(Long codEmpresa) throws Exception;

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 27/11/2016
	 * @description 
	 * @param tipo
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<DosimetroDTO> getDataDosimetroTipo(String tipo, Long id) throws Exception;
	
	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 27/11/2016
	 * @description 
	 * @param idEmp
	 * @param idTraba
	 * @param idDosi
	 * @return
	 * @throws Exception
	 */
	public List<DosimetroDTO> getDataDosimetroEmpTraba(Long idEmp, Long idTraba, Long idDosi) throws Exception;

	/**
	 * 
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a> 
	 * @date 27/11/2016
	 * @description 
	 * @param idEmpresa
	 * @param idTrabajador
	 * @return
	 * @throws Exception
	 */
	public List<EstudiosDTO> getDataEstudiosParam(Long idEmpresa, Long idTrabajador) throws Exception;

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 27/11/2016
	 * @description 
	 * @param idEmpresa
	 * @param idTrabajador
	 * @return
	 * @throws Exception 
	 */
	public DosimetroDTO getDataDosimetroUltimo(Long idEmpresa, Long idTrabajador) throws Exception;

}
