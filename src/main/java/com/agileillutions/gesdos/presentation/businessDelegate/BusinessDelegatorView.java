package com.agileillutions.gesdos.presentation.businessDelegate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

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
import com.agileillutions.gesdos.modelo.control.DosimetroLogic;
import com.agileillutions.gesdos.modelo.control.ICargoLogic;
import com.agileillutions.gesdos.modelo.control.ICiudaLogic;
import com.agileillutions.gesdos.modelo.control.IContratoDosimetroLogic;
import com.agileillutions.gesdos.modelo.control.IContratosLogic;
import com.agileillutions.gesdos.modelo.control.IDeparLogic;
import com.agileillutions.gesdos.modelo.control.IDetalleContratoLogic;
import com.agileillutions.gesdos.modelo.control.IDosimetroLogic;
import com.agileillutions.gesdos.modelo.control.IEmpresasLogic;
import com.agileillutions.gesdos.modelo.control.IEstudiosLogic;
import com.agileillutions.gesdos.modelo.control.IFacturaLogic;
import com.agileillutions.gesdos.modelo.control.IFacturapagoLogic;
import com.agileillutions.gesdos.modelo.control.IGeominasLogic;
import com.agileillutions.gesdos.modelo.control.IGrupdetLogic;
import com.agileillutions.gesdos.modelo.control.IGruposLogic;
import com.agileillutions.gesdos.modelo.control.IJasperExportService;
import com.agileillutions.gesdos.modelo.control.INumerLogic;
import com.agileillutions.gesdos.modelo.control.IObservaLogic;
import com.agileillutions.gesdos.modelo.control.IPermisosLogic;
import com.agileillutions.gesdos.modelo.control.IPracticaLogic;
import com.agileillutions.gesdos.modelo.control.IPrograma1Logic;
import com.agileillutions.gesdos.modelo.control.IProgramasLogic;
import com.agileillutions.gesdos.modelo.control.IRadiacionLogic;
import com.agileillutions.gesdos.modelo.control.IReveladoLogic;
import com.agileillutions.gesdos.modelo.control.ISociosLogic;
import com.agileillutions.gesdos.modelo.control.ITrabaLogic;
import com.agileillutions.gesdos.modelo.control.IUbicacionLogic;
import com.agileillutions.gesdos.modelo.control.IUsuariosLogic;
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
import com.jamonapi.utils.AppBaseException;

import net.sf.jasperreports.engine.JRException;

/**
 * Use a Business Delegate to reduce coupling between presentation-tier clients
 * and business services. The Business Delegate hides the underlying
 * implementation details of the business service, such as lookup and access
 * details of the EJB architecture.
 *
 * The Business Delegate acts as a client-side business abstraction; it provides
 * an abstraction for, and thus hides, the implementation of the business
 * services. Using a Business Delegate reduces the coupling between
 * presentation-tier clients and the system's business services. Depending on
 * the implementation strategy, the Business Delegate may shield clients from
 * possible volatility in the implementation of the business service API.
 * Potentially, this reduces the number of changes that must be made to the
 * presentation-tier client code when the business service API or its underlying
 * implementation changes.
 *
 * However, interface methods in the Business Delegate may still require
 * modification if the underlying business service API changes. Admittedly,
 * though, it is more likely that changes will be made to the business service
 * rather than to the Business Delegate.
 *
 * Often, developers are skeptical when a design goal such as abstracting the
 * business layer causes additional upfront work in return for future gains.
 * However, using this pattern or its strategies results in only a small amount
 * of additional upfront work and provides considerable benefits. The main
 * benefit is hiding the details of the underlying service. For example, the
 * client can become transparent to naming and lookup services. The Business
 * Delegate also handles the exceptions from the business services, such as
 * java.rmi.Remote exceptions, Java Messages Service (JMS) exceptions and so on.
 * The Business Delegate may intercept such service level exceptions and
 * generate application level exceptions instead. Application level exceptions
 * are easier to handle by the clients, and may be user friendly. The Business
 * Delegate may also transparently perform any retry or recovery operations
 * necessary in the event of a service failure without exposing the client to
 * the problem until it is determined that the problem is not resolvable. These
 * gains present a compelling reason to use the pattern.
 *
 * Another benefit is that the delegate may cache results and references to
 * remote business services. Caching can significantly improve performance,
 * because it limits unnecessary and potentially costly round trips over the
 * network.
 *
 * A Business Delegate uses a component called the Lookup Service. The Lookup
 * Service is responsible for hiding the underlying implementation details of
 * the business service lookup code. The Lookup Service may be written as part
 * of the Delegate, but we recommend that it be implemented as a separate
 * component, as outlined in the Service Locator pattern (See "Service Locator"
 * on page 368.)
 *
 * When the Business Delegate is used with a Session Facade, typically there is
 * a one-to-one relationship between the two. This one-to-one relationship
 * exists because logic that might have been encapsulated in a Business Delegate
 * relating to its interaction with multiple business services (creating a
 * one-to-many relationship) will often be factored back into a Session Facade.
 *
 * Finally, it should be noted that this pattern could be used to reduce
 * coupling between other tiers, not simply the presentation and the business
 * tiers.
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *         www.zathuracode.org
 *
 */
@Scope("singleton")
@Service("BusinessDelegatorView")
public class BusinessDelegatorView implements IBusinessDelegatorView {
	@Autowired
	private ICargoLogic cargoLogic;
	@Autowired
	private ICiudaLogic ciudaLogic;
	@Autowired
	private IContratoDosimetroLogic contratoDosimetroLogic;
	@Autowired
	private IContratosLogic contratosLogic;
	@Autowired
	private IDeparLogic deparLogic;
	@Autowired
	private IDetalleContratoLogic detalleContratoLogic;
	@Autowired
	private IDosimetroLogic dosimetroLogic;
	@Autowired
	private IEmpresasLogic empresasLogic;
	@Autowired
	private IEstudiosLogic estudiosLogic;
	@Autowired
	private IFacturaLogic facturaLogic;
	@Autowired
	private IFacturapagoLogic facturapagoLogic;
	@Autowired
	private IGeominasLogic geominasLogic;
	@Autowired
	private IGrupdetLogic grupdetLogic;
	@Autowired
	private IGruposLogic gruposLogic;
	@Autowired
	private INumerLogic numerLogic;
	@Autowired
	private IObservaLogic observaLogic;
	@Autowired
	private IPermisosLogic permisosLogic;
	@Autowired
	private IPracticaLogic practicaLogic;
	@Autowired
	private IPrograma1Logic programa1Logic;
	@Autowired
	private IProgramasLogic programasLogic;
	@Autowired
	private IRadiacionLogic radiacionLogic;
	@Autowired
	private IReveladoLogic reveladoLogic;
	@Autowired
	private ISociosLogic sociosLogic;
	@Autowired
	private ITrabaLogic trabaLogic;
	@Autowired
	private IUbicacionLogic ubicacionLogic;
	@Autowired
	private IUsuariosLogic usuariosLogic;
	@Autowired
	private IJasperExportService jasperService;

	public List<Cargo> getCargo() throws Exception {
		return cargoLogic.getCargo();
	}

	public void saveCargo(Cargo entity) throws Exception {
		cargoLogic.saveCargo(entity);
	}

	public void deleteCargo(Cargo entity) throws Exception {
		cargoLogic.deleteCargo(entity);
	}

	public void updateCargo(Cargo entity) throws Exception {
		cargoLogic.updateCargo(entity);
	}

	public Cargo getCargo(String carCod) throws Exception {
		Cargo cargo = null;

		try {
			cargo = cargoLogic.getCargo(carCod);
		} catch (Exception e) {
			throw e;
		}

		return cargo;
	}

	public List<Cargo> findByCriteriaInCargo(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		return cargoLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public List<Cargo> findPageCargo(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		return cargoLogic.findPageCargo(sortColumnName, sortAscending,
				startRow, maxResults);
	}

	public Long findTotalNumberCargo() throws Exception {
		return cargoLogic.findTotalNumberCargo();
	}

	public List<CargoDTO> getDataCargo() throws Exception {
		return cargoLogic.getDataCargo();
	}

	public List<Ciuda> getCiuda() throws Exception {
		return ciudaLogic.getCiuda();
	}

	public void saveCiuda(Ciuda entity) throws Exception {
		ciudaLogic.saveCiuda(entity);
	}

	public void deleteCiuda(Ciuda entity) throws Exception {
		ciudaLogic.deleteCiuda(entity);
	}

	public void updateCiuda(Ciuda entity) throws Exception {
		ciudaLogic.updateCiuda(entity);
	}

	public Ciuda getCiuda(CiudaId id) throws Exception {
		Ciuda ciuda = null;

		try {
			ciuda = ciudaLogic.getCiuda(id);
		} catch (Exception e) {
			throw e;
		}

		return ciuda;
	}

	public List<Ciuda> findByCriteriaInCiuda(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		return ciudaLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public List<Ciuda> findPageCiuda(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		return ciudaLogic.findPageCiuda(sortColumnName, sortAscending,
				startRow, maxResults);
	}

	public Long findTotalNumberCiuda() throws Exception {
		return ciudaLogic.findTotalNumberCiuda();
	}

	public List<CiudaDTO> getDataCiuda() throws Exception {
		return ciudaLogic.getDataCiuda();
	}

	public List<ContratoDosimetro> getContratoDosimetro() throws Exception {
		return contratoDosimetroLogic.getContratoDosimetro();
	}

	public void saveContratoDosimetro(ContratoDosimetro entity)
			throws Exception {
		contratoDosimetroLogic.saveContratoDosimetro(entity);
	}

	public void deleteContratoDosimetro(ContratoDosimetro entity)
			throws Exception {
		contratoDosimetroLogic.deleteContratoDosimetro(entity);
	}

	public void updateContratoDosimetro(ContratoDosimetro entity)
			throws Exception {
		contratoDosimetroLogic.updateContratoDosimetro(entity);
	}

	public ContratoDosimetro getContratoDosimetro(Long conDosi)
			throws Exception {
		ContratoDosimetro contratoDosimetro = null;

		try {
			contratoDosimetro = contratoDosimetroLogic
					.getContratoDosimetro(conDosi);
		} catch (Exception e) {
			throw e;
		}

		return contratoDosimetro;
	}

	public List<ContratoDosimetro> findByCriteriaInContratoDosimetro(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		return contratoDosimetroLogic.findByCriteria(variables,
				variablesBetween, variablesBetweenDates);
	}

	public List<ContratoDosimetro> findPageContratoDosimetro(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		return contratoDosimetroLogic.findPageContratoDosimetro(sortColumnName,
				sortAscending, startRow, maxResults);
	}

	public Long findTotalNumberContratoDosimetro() throws Exception {
		return contratoDosimetroLogic.findTotalNumberContratoDosimetro();
	}

	public List<ContratoDosimetroDTO> getDataContratoDosimetro()
			throws Exception {
		return contratoDosimetroLogic.getDataContratoDosimetro();
	}

	public List<Contratos> getContratos() throws Exception {
		return contratosLogic.getContratos();
	}

	public void saveContratos(Contratos entity) throws Exception {
		contratosLogic.saveContratos(entity);
	}

	public void deleteContratos(Contratos entity) throws Exception {
		contratosLogic.deleteContratos(entity);
	}

	public void updateContratos(Contratos entity) throws Exception {
		contratosLogic.updateContratos(entity);
	}

	public Contratos getContratos(ContratosId id) throws Exception {
		Contratos contratos = null;

		try {
			contratos = contratosLogic.getContratos(id);
		} catch (Exception e) {
			throw e;
		}

		return contratos;
	}

	public List<Contratos> findByCriteriaInContratos(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		return contratosLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public List<Contratos> findPageContratos(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		return contratosLogic.findPageContratos(sortColumnName, sortAscending,
				startRow, maxResults);
	}

	public Long findTotalNumberContratos() throws Exception {
		return contratosLogic.findTotalNumberContratos();
	}

	public List<ContratosDTO> getDataContratos() throws Exception {
		return contratosLogic.getDataContratos();
	}

	public List<Depar> getDepar() throws Exception {
		return deparLogic.getDepar();
	}

	public void saveDepar(Depar entity) throws Exception {
		deparLogic.saveDepar(entity);
	}

	public void deleteDepar(Depar entity) throws Exception {
		deparLogic.deleteDepar(entity);
	}

	public void updateDepar(Depar entity) throws Exception {
		deparLogic.updateDepar(entity);
	}

	public Depar getDepar(Long depCod) throws Exception {
		Depar depar = null;

		try {
			depar = deparLogic.getDepar(depCod);
		} catch (Exception e) {
			throw e;
		}

		return depar;
	}

	public List<Depar> findByCriteriaInDepar(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		return deparLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public List<Depar> findPageDepar(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		return deparLogic.findPageDepar(sortColumnName, sortAscending,
				startRow, maxResults);
	}

	public Long findTotalNumberDepar() throws Exception {
		return deparLogic.findTotalNumberDepar();
	}

	public List<DeparDTO> getDataDepar() throws Exception {
		return deparLogic.getDataDepar();
	}

	public List<DetalleContrato> getDetalleContrato() throws Exception {
		return detalleContratoLogic.getDetalleContrato();
	}

	public void saveDetalleContrato(DetalleContrato entity) throws Exception {
		detalleContratoLogic.saveDetalleContrato(entity);
	}

	public void deleteDetalleContrato(DetalleContrato entity) throws Exception {
		detalleContratoLogic.deleteDetalleContrato(entity);
	}

	public void updateDetalleContrato(DetalleContrato entity) throws Exception {
		detalleContratoLogic.updateDetalleContrato(entity);
	}

	public DetalleContrato getDetalleContrato(DetalleContratoId id)
			throws Exception {
		DetalleContrato detalleContrato = null;

		try {
			detalleContrato = detalleContratoLogic.getDetalleContrato(id);
		} catch (Exception e) {
			throw e;
		}

		return detalleContrato;
	}

	public List<DetalleContrato> findByCriteriaInDetalleContrato(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		return detalleContratoLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public List<DetalleContrato> findPageDetalleContrato(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		return detalleContratoLogic.findPageDetalleContrato(sortColumnName,
				sortAscending, startRow, maxResults);
	}

	public Long findTotalNumberDetalleContrato() throws Exception {
		return detalleContratoLogic.findTotalNumberDetalleContrato();
	}

	public List<DetalleContratoDTO> getDataDetalleContrato() throws Exception {
		return detalleContratoLogic.getDataDetalleContrato();
	}

	public List<Dosimetro> getDosimetro() throws Exception {
		return dosimetroLogic.getDosimetro();
	}

	public void saveDosimetro(Dosimetro entity) throws Exception {
		dosimetroLogic.saveDosimetro(entity);
	}

	public void deleteDosimetro(Dosimetro entity) throws Exception {
		dosimetroLogic.deleteDosimetro(entity);
	}

	public void updateDosimetro(Dosimetro entity) throws Exception {
		dosimetroLogic.updateDosimetro(entity);
	}

	public Dosimetro getDosimetro(DosimetroId id) throws Exception {
		Dosimetro dosimetro = null;

		try {
			dosimetro = dosimetroLogic.getDosimetro(id);
		} catch (Exception e) {
			throw e;
		}

		return dosimetro;
	}

	public List<Dosimetro> findByCriteriaInDosimetro(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		return dosimetroLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public List<Dosimetro> findPageDosimetro(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		return dosimetroLogic.findPageDosimetro(sortColumnName, sortAscending,
				startRow, maxResults);
	}

	public Long findTotalNumberDosimetro() throws Exception {
		return dosimetroLogic.findTotalNumberDosimetro();
	}

	public List<DosimetroDTO> getDataDosimetro() throws Exception {
		return dosimetroLogic.getDataDosimetro();
	}

	public List<Empresas> getEmpresas() throws Exception {
		return empresasLogic.getEmpresas();
	}

	public void saveEmpresas(Empresas entity) throws Exception {
		empresasLogic.saveEmpresas(entity);
	}

	public void deleteEmpresas(Empresas entity) throws Exception {
		empresasLogic.deleteEmpresas(entity);
	}

	public void updateEmpresas(Empresas entity) throws Exception {
		empresasLogic.updateEmpresas(entity);
	}

	public Empresas getEmpresas(Long empCod) throws Exception {
		Empresas empresas = null;

		try {
			empresas = empresasLogic.getEmpresas(empCod);
		} catch (Exception e) {
			throw e;
		}

		return empresas;
	}

	public List<Empresas> findByCriteriaInEmpresas(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		return empresasLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public List<Empresas> findPageEmpresas(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		return empresasLogic.findPageEmpresas(sortColumnName, sortAscending,
				startRow, maxResults);
	}

	public Long findTotalNumberEmpresas() throws Exception {
		return empresasLogic.findTotalNumberEmpresas();
	}

	public List<EmpresasDTO> getDataEmpresas() throws Exception {
		return empresasLogic.getDataEmpresas();
	}

	public List<Estudios> getEstudios() throws Exception {
		return estudiosLogic.getEstudios();
	}

	public void saveEstudios(Estudios entity) throws Exception {
		estudiosLogic.saveEstudios(entity);
	}

	public void deleteEstudios(Estudios entity) throws Exception {
		estudiosLogic.deleteEstudios(entity);
	}

	public void updateEstudios(Estudios entity) throws Exception {
		estudiosLogic.updateEstudios(entity);
	}

	public Estudios getEstudios(EstudiosId id) throws Exception {
		Estudios estudios = null;

		try {
			estudios = estudiosLogic.getEstudios(id);
		} catch (Exception e) {
			throw e;
		}

		return estudios;
	}

	public List<Estudios> findByCriteriaInEstudios(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		return estudiosLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public List<Estudios> findPageEstudios(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		return estudiosLogic.findPageEstudios(sortColumnName, sortAscending,
				startRow, maxResults);
	}

	public Long findTotalNumberEstudios() throws Exception {
		return estudiosLogic.findTotalNumberEstudios();
	}

	public List<EstudiosDTO> getDataEstudios() throws Exception {
		return estudiosLogic.getDataEstudios();
	}

	public List<Factura> getFactura() throws Exception {
		return facturaLogic.getFactura();
	}

	public void saveFactura(Factura entity) throws Exception {
		facturaLogic.saveFactura(entity);
	}

	public void deleteFactura(Factura entity) throws Exception {
		facturaLogic.deleteFactura(entity);
	}

	public void updateFactura(Factura entity) throws Exception {
		facturaLogic.updateFactura(entity);
	}

	public Factura getFactura(FacturaId id) throws Exception {
		Factura factura = null;

		try {
			factura = facturaLogic.getFactura(id);
		} catch (Exception e) {
			throw e;
		}

		return factura;
	}

	public List<Factura> findByCriteriaInFactura(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		return facturaLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public List<Factura> findPageFactura(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		return facturaLogic.findPageFactura(sortColumnName, sortAscending,
				startRow, maxResults);
	}

	public Long findTotalNumberFactura() throws Exception {
		return facturaLogic.findTotalNumberFactura();
	}

	public List<FacturaDTO> getDataFactura() throws Exception {
		return facturaLogic.getDataFactura();
	}

	public List<Facturapago> getFacturapago() throws Exception {
		return facturapagoLogic.getFacturapago();
	}

	public void saveFacturapago(Facturapago entity) throws Exception {
		facturapagoLogic.saveFacturapago(entity);
	}

	public void deleteFacturapago(Facturapago entity) throws Exception {
		facturapagoLogic.deleteFacturapago(entity);
	}

	public void updateFacturapago(Facturapago entity) throws Exception {
		facturapagoLogic.updateFacturapago(entity);
	}

	public Facturapago getFacturapago(FacturapagoId id) throws Exception {
		Facturapago facturapago = null;

		try {
			facturapago = facturapagoLogic.getFacturapago(id);
		} catch (Exception e) {
			throw e;
		}

		return facturapago;
	}

	public List<Facturapago> findByCriteriaInFacturapago(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		return facturapagoLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public List<Facturapago> findPageFacturapago(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		return facturapagoLogic.findPageFacturapago(sortColumnName,
				sortAscending, startRow, maxResults);
	}

	public Long findTotalNumberFacturapago() throws Exception {
		return facturapagoLogic.findTotalNumberFacturapago();
	}

	public List<FacturapagoDTO> getDataFacturapago() throws Exception {
		return facturapagoLogic.getDataFacturapago();
	}

	public List<Geominas> getGeominas() throws Exception {
		return geominasLogic.getGeominas();
	}

	public void saveGeominas(Geominas entity) throws Exception {
		geominasLogic.saveGeominas(entity);
	}

	public void deleteGeominas(Geominas entity) throws Exception {
		geominasLogic.deleteGeominas(entity);
	}

	public void updateGeominas(Geominas entity) throws Exception {
		geominasLogic.updateGeominas(entity);
	}

	public Geominas getGeominas(String geoCod) throws Exception {
		Geominas geominas = null;

		try {
			geominas = geominasLogic.getGeominas(geoCod);
		} catch (Exception e) {
			throw e;
		}

		return geominas;
	}

	public List<Geominas> findByCriteriaInGeominas(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		return geominasLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public List<Geominas> findPageGeominas(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		return geominasLogic.findPageGeominas(sortColumnName, sortAscending,
				startRow, maxResults);
	}

	public Long findTotalNumberGeominas() throws Exception {
		return geominasLogic.findTotalNumberGeominas();
	}

	public List<GeominasDTO> getDataGeominas() throws Exception {
		return geominasLogic.getDataGeominas();
	}

	public List<Grupdet> getGrupdet() throws Exception {
		return grupdetLogic.getGrupdet();
	}

	public void saveGrupdet(Grupdet entity) throws Exception {
		grupdetLogic.saveGrupdet(entity);
	}

	public void deleteGrupdet(Grupdet entity) throws Exception {
		grupdetLogic.deleteGrupdet(entity);
	}

	public void updateGrupdet(Grupdet entity) throws Exception {
		grupdetLogic.updateGrupdet(entity);
	}

	public Grupdet getGrupdet(GrupdetId id) throws Exception {
		Grupdet grupdet = null;

		try {
			grupdet = grupdetLogic.getGrupdet(id);
		} catch (Exception e) {
			throw e;
		}

		return grupdet;
	}

	public List<Grupdet> findByCriteriaInGrupdet(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		return grupdetLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public List<Grupdet> findPageGrupdet(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		return grupdetLogic.findPageGrupdet(sortColumnName, sortAscending,
				startRow, maxResults);
	}

	public Long findTotalNumberGrupdet() throws Exception {
		return grupdetLogic.findTotalNumberGrupdet();
	}

	public List<GrupdetDTO> getDataGrupdet() throws Exception {
		return grupdetLogic.getDataGrupdet();
	}

	public List<Grupos> getGrupos() throws Exception {
		return gruposLogic.getGrupos();
	}

	public void saveGrupos(Grupos entity) throws Exception {
		gruposLogic.saveGrupos(entity);
	}

	public void deleteGrupos(Grupos entity) throws Exception {
		gruposLogic.deleteGrupos(entity);
	}

	public void updateGrupos(Grupos entity) throws Exception {
		gruposLogic.updateGrupos(entity);
	}

	public Grupos getGrupos(String grupCodigo) throws Exception {
		Grupos grupos = null;

		try {
			grupos = gruposLogic.getGrupos(grupCodigo);
		} catch (Exception e) {
			throw e;
		}

		return grupos;
	}

	public List<Grupos> findByCriteriaInGrupos(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		return gruposLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public List<Grupos> findPageGrupos(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		return gruposLogic.findPageGrupos(sortColumnName, sortAscending,
				startRow, maxResults);
	}

	public Long findTotalNumberGrupos() throws Exception {
		return gruposLogic.findTotalNumberGrupos();
	}

	public List<GruposDTO> getDataGrupos() throws Exception {
		return gruposLogic.getDataGrupos();
	}

	public List<Numer> getNumer() throws Exception {
		return numerLogic.getNumer();
	}

	public void saveNumer(Numer entity) throws Exception {
		numerLogic.saveNumer(entity);
	}

	public void deleteNumer(Numer entity) throws Exception {
		numerLogic.deleteNumer(entity);
	}

	public void updateNumer(Numer entity) throws Exception {
		numerLogic.updateNumer(entity);
	}

	public Numer getNumer(Long numCod) throws Exception {
		Numer numer = null;

		try {
			numer = numerLogic.getNumer(numCod);
		} catch (Exception e) {
			throw e;
		}

		return numer;
	}

	public List<Numer> findByCriteriaInNumer(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		return numerLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public List<Numer> findPageNumer(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		return numerLogic.findPageNumer(sortColumnName, sortAscending,
				startRow, maxResults);
	}

	public Long findTotalNumberNumer() throws Exception {
		return numerLogic.findTotalNumberNumer();
	}

	public List<NumerDTO> getDataNumer() throws Exception {
		return numerLogic.getDataNumer();
	}

	public List<Observa> getObserva() throws Exception {
		return observaLogic.getObserva();
	}

	public void saveObserva(Observa entity) throws Exception {
		observaLogic.saveObserva(entity);
	}

	public void deleteObserva(Observa entity) throws Exception {
		observaLogic.deleteObserva(entity);
	}

	public void updateObserva(Observa entity) throws Exception {
		observaLogic.updateObserva(entity);
	}

	public Observa getObserva(String obsCod) throws Exception {
		Observa observa = null;

		try {
			observa = observaLogic.getObserva(obsCod);
		} catch (Exception e) {
			throw e;
		}

		return observa;
	}

	public List<Observa> findByCriteriaInObserva(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		return observaLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public List<Observa> findPageObserva(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		return observaLogic.findPageObserva(sortColumnName, sortAscending,
				startRow, maxResults);
	}

	public Long findTotalNumberObserva() throws Exception {
		return observaLogic.findTotalNumberObserva();
	}

	public List<ObservaDTO> getDataObserva() throws Exception {
		return observaLogic.getDataObserva();
	}

	public List<Permisos> getPermisos() throws Exception {
		return permisosLogic.getPermisos();
	}

	public void savePermisos(Permisos entity) throws Exception {
		permisosLogic.savePermisos(entity);
	}

	public void deletePermisos(Permisos entity) throws Exception {
		permisosLogic.deletePermisos(entity);
	}

	public void updatePermisos(Permisos entity) throws Exception {
		permisosLogic.updatePermisos(entity);
	}

	public Permisos getPermisos(PermisosId id) throws Exception {
		Permisos permisos = null;

		try {
			permisos = permisosLogic.getPermisos(id);
		} catch (Exception e) {
			throw e;
		}

		return permisos;
	}

	public List<Permisos> findByCriteriaInPermisos(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		return permisosLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public List<Permisos> findPagePermisos(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		return permisosLogic.findPagePermisos(sortColumnName, sortAscending,
				startRow, maxResults);
	}

	public Long findTotalNumberPermisos() throws Exception {
		return permisosLogic.findTotalNumberPermisos();
	}

	public List<PermisosDTO> getDataPermisos() throws Exception {
		return permisosLogic.getDataPermisos();
	}

	public List<Practica> getPractica() throws Exception {
		return practicaLogic.getPractica();
	}

	public void savePractica(Practica entity) throws Exception {
		practicaLogic.savePractica(entity);
	}

	public void deletePractica(Practica entity) throws Exception {
		practicaLogic.deletePractica(entity);
	}

	public void updatePractica(Practica entity) throws Exception {
		practicaLogic.updatePractica(entity);
	}

	public Practica getPractica(String praCod) throws Exception {
		Practica practica = null;

		try {
			practica = practicaLogic.getPractica(praCod);
		} catch (Exception e) {
			throw e;
		}

		return practica;
	}

	public List<Practica> findByCriteriaInPractica(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		return practicaLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public List<Practica> findPagePractica(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		return practicaLogic.findPagePractica(sortColumnName, sortAscending,
				startRow, maxResults);
	}

	public Long findTotalNumberPractica() throws Exception {
		return practicaLogic.findTotalNumberPractica();
	}

	public List<PracticaDTO> getDataPractica() throws Exception {
		return practicaLogic.getDataPractica();
	}

	public List<Programa1> getPrograma1() throws Exception {
		return programa1Logic.getPrograma1();
	}

	public void savePrograma1(Programa1 entity) throws Exception {
		programa1Logic.savePrograma1(entity);
	}

	public void deletePrograma1(Programa1 entity) throws Exception {
		programa1Logic.deletePrograma1(entity);
	}

	public void updatePrograma1(Programa1 entity) throws Exception {
		programa1Logic.updatePrograma1(entity);
	}

	public Programa1 getPrograma1(Programa1Id id) throws Exception {
		Programa1 programa1 = null;

		try {
			programa1 = programa1Logic.getPrograma1(id);
		} catch (Exception e) {
			throw e;
		}

		return programa1;
	}

	public List<Programa1> findByCriteriaInPrograma1(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		return programa1Logic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public List<Programa1> findPagePrograma1(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		return programa1Logic.findPagePrograma1(sortColumnName, sortAscending,
				startRow, maxResults);
	}

	public Long findTotalNumberPrograma1() throws Exception {
		return programa1Logic.findTotalNumberPrograma1();
	}

	public List<Programa1DTO> getDataPrograma1() throws Exception {
		return programa1Logic.getDataPrograma1();
	}

	public List<Programas> getProgramas() throws Exception {
		return programasLogic.getProgramas();
	}

	public void saveProgramas(Programas entity) throws Exception {
		programasLogic.saveProgramas(entity);
	}

	public void deleteProgramas(Programas entity) throws Exception {
		programasLogic.deleteProgramas(entity);
	}

	public void updateProgramas(Programas entity) throws Exception {
		programasLogic.updateProgramas(entity);
	}

	public Programas getProgramas(String progCodigo) throws Exception {
		Programas programas = null;

		try {
			programas = programasLogic.getProgramas(progCodigo);
		} catch (Exception e) {
			throw e;
		}

		return programas;
	}

	public List<Programas> findByCriteriaInProgramas(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		return programasLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public List<Programas> findPageProgramas(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		return programasLogic.findPageProgramas(sortColumnName, sortAscending,
				startRow, maxResults);
	}

	public Long findTotalNumberProgramas() throws Exception {
		return programasLogic.findTotalNumberProgramas();
	}

	public List<ProgramasDTO> getDataProgramas() throws Exception {
		return programasLogic.getDataProgramas();
	}

	public List<Radiacion> getRadiacion() throws Exception {
		return radiacionLogic.getRadiacion();
	}

	public void saveRadiacion(Radiacion entity) throws Exception {
		radiacionLogic.saveRadiacion(entity);
	}

	public void deleteRadiacion(Radiacion entity) throws Exception {
		radiacionLogic.deleteRadiacion(entity);
	}

	public void updateRadiacion(Radiacion entity) throws Exception {
		radiacionLogic.updateRadiacion(entity);
	}

	public Radiacion getRadiacion(String radCod) throws Exception {
		Radiacion radiacion = null;

		try {
			radiacion = radiacionLogic.getRadiacion(radCod);
		} catch (Exception e) {
			throw e;
		}

		return radiacion;
	}

	public List<Radiacion> findByCriteriaInRadiacion(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		return radiacionLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public List<Radiacion> findPageRadiacion(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		return radiacionLogic.findPageRadiacion(sortColumnName, sortAscending,
				startRow, maxResults);
	}

	public Long findTotalNumberRadiacion() throws Exception {
		return radiacionLogic.findTotalNumberRadiacion();
	}

	public List<RadiacionDTO> getDataRadiacion() throws Exception {
		return radiacionLogic.getDataRadiacion();
	}

	public List<Revelado> getRevelado() throws Exception {
		return reveladoLogic.getRevelado();
	}

	public void saveRevelado(Revelado entity) throws Exception {
		reveladoLogic.saveRevelado(entity);
	}

	public void deleteRevelado(Revelado entity) throws Exception {
		reveladoLogic.deleteRevelado(entity);
	}

	public void updateRevelado(Revelado entity) throws Exception {
		reveladoLogic.updateRevelado(entity);
	}

	public Revelado getRevelado(Long revNro) throws Exception {
		Revelado revelado = null;

		try {
			revelado = reveladoLogic.getRevelado(revNro);
		} catch (Exception e) {
			throw e;
		}

		return revelado;
	}

	public List<Revelado> findByCriteriaInRevelado(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		return reveladoLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public List<Revelado> findPageRevelado(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		return reveladoLogic.findPageRevelado(sortColumnName, sortAscending,
				startRow, maxResults);
	}

	public Long findTotalNumberRevelado() throws Exception {
		return reveladoLogic.findTotalNumberRevelado();
	}

	public List<ReveladoDTO> getDataRevelado() throws Exception {
		return reveladoLogic.getDataRevelado();
	}

	public List<Socios> getSocios() throws Exception {
		return sociosLogic.getSocios();
	}

	public void saveSocios(Socios entity) throws Exception {
		sociosLogic.saveSocios(entity);
	}

	public void deleteSocios(Socios entity) throws Exception {
		sociosLogic.deleteSocios(entity);
	}

	public void updateSocios(Socios entity) throws Exception {
		sociosLogic.updateSocios(entity);
	}

	public Socios getSocios(String socCod) throws Exception {
		Socios socios = null;

		try {
			socios = sociosLogic.getSocios(socCod);
		} catch (Exception e) {
			throw e;
		}

		return socios;
	}

	public List<Socios> findByCriteriaInSocios(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		return sociosLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public List<Socios> findPageSocios(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		return sociosLogic.findPageSocios(sortColumnName, sortAscending,
				startRow, maxResults);
	}

	public Long findTotalNumberSocios() throws Exception {
		return sociosLogic.findTotalNumberSocios();
	}

	public List<SociosDTO> getDataSocios() throws Exception {
		return sociosLogic.getDataSocios();
	}

	public List<Traba> getTraba() throws Exception {
		return trabaLogic.getTraba();
	}

	public void saveTraba(Traba entity) throws Exception {
		trabaLogic.saveTraba(entity);
	}

	public void deleteTraba(Traba entity) throws Exception {
		trabaLogic.deleteTraba(entity);
	}

	public void updateTraba(Traba entity) throws Exception {
		trabaLogic.updateTraba(entity);
	}

	public Traba getTraba(Long traCed) throws Exception {
		Traba traba = null;

		try {
			traba = trabaLogic.getTraba(traCed);
		} catch (Exception e) {
			throw e;
		}

		return traba;
	}

	public List<Traba> findByCriteriaInTraba(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		return trabaLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public List<Traba> findPageTraba(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		return trabaLogic.findPageTraba(sortColumnName, sortAscending,
				startRow, maxResults);
	}

	public Long findTotalNumberTraba() throws Exception {
		return trabaLogic.findTotalNumberTraba();
	}

	public List<TrabaDTO> getDataTraba() throws Exception {
		return trabaLogic.getDataTraba();
	}

	public List<Ubicacion> getUbicacion() throws Exception {
		return ubicacionLogic.getUbicacion();
	}

	public void saveUbicacion(Ubicacion entity) throws Exception {
		ubicacionLogic.saveUbicacion(entity);
	}

	public void deleteUbicacion(Ubicacion entity) throws Exception {
		ubicacionLogic.deleteUbicacion(entity);
	}

	public void updateUbicacion(Ubicacion entity) throws Exception {
		ubicacionLogic.updateUbicacion(entity);
	}

	public Ubicacion getUbicacion(String ubiCod) throws Exception {
		Ubicacion ubicacion = null;

		try {
			ubicacion = ubicacionLogic.getUbicacion(ubiCod);
		} catch (Exception e) {
			throw e;
		}

		return ubicacion;
	}

	public List<Ubicacion> findByCriteriaInUbicacion(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		return ubicacionLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public List<Ubicacion> findPageUbicacion(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		return ubicacionLogic.findPageUbicacion(sortColumnName, sortAscending,
				startRow, maxResults);
	}

	public Long findTotalNumberUbicacion() throws Exception {
		return ubicacionLogic.findTotalNumberUbicacion();
	}

	public List<UbicacionDTO> getDataUbicacion() throws Exception {
		return ubicacionLogic.getDataUbicacion();
	}

	public List<Usuarios> getUsuarios() throws Exception {
		return usuariosLogic.getUsuarios();
	}

	public void saveUsuarios(Usuarios entity) throws Exception {
		usuariosLogic.saveUsuarios(entity);
	}

	public void deleteUsuarios(Usuarios entity) throws Exception {
		usuariosLogic.deleteUsuarios(entity);
	}

	public void updateUsuarios(Usuarios entity) throws Exception {
		usuariosLogic.updateUsuarios(entity);
	}

	public Usuarios getUsuarios(String usuaCodigo) throws Exception {
		Usuarios usuarios = null;

		try {
			usuarios = usuariosLogic.getUsuarios(usuaCodigo);
		} catch (Exception e) {
			throw e;
		}

		return usuarios;
	}

	public List<Usuarios> findByCriteriaInUsuarios(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		return usuariosLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public List<Usuarios> findPageUsuarios(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		return usuariosLogic.findPageUsuarios(sortColumnName, sortAscending,
				startRow, maxResults);
	}

	public Long findTotalNumberUsuarios() throws Exception {
		return usuariosLogic.findTotalNumberUsuarios();
	}

	public List<UsuariosDTO> getDataUsuarios() throws Exception {
		return usuariosLogic.getDataUsuarios();
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
	 * @description
	 * @param codEmpresa
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<TrabaDTO> getDataTrabaEmpresa(Long codEmpresa) throws Exception {
		return trabaLogic.getDataTrabaEmpresa(codEmpresa);
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
	 * @param tipo
	 * @param id
	 * @return
	 * @throws Exception
	 * @see com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView#getDataDosimetroTipo(java.lang.String,
	 *      java.lang.Long)
	 */
	@Override
	public List<DosimetroDTO> getDataDosimetroTipo(String tipo, Long id)
			throws Exception {
		return dosimetroLogic.getDataDosimetroTipo(tipo, id);
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
	 * @param idEmp
	 * @param idTraba
	 * @return
	 * @throws Exception
	 * @see com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView#getDataDosimetroEmpTraba(java.lang.Long,
	 *      java.lang.Long)
	 */
	@Override
	public List<DosimetroDTO> getDataDosimetroEmpTraba(Long idEmp,
			Long idTraba, Long idDosi) throws Exception {
		return dosimetroLogic.getDataDosimetroEmpTraba(idEmp, idTraba, idDosi);
	}

	/**
	 * 
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 27/11/2016
	 * @param idEmpresa
	 * @param idTrabajador
	 * @return
	 * @throws Exception
	 * @see com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView#getDataEstudiosParam(java.lang.Long,
	 *      java.lang.Long)
	 */
	@Override
	public List<EstudiosDTO> getDataEstudiosParam(Long idEmpresa,
			Long idTrabajador) throws Exception {
		return estudiosLogic.getDataEstudiosParam(idEmpresa, idTrabajador);
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
	 * @param idEmpresa
	 * @param idTrabajador
	 * @return
	 * @throws Exception
	 * @see com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView#getDataDosimetroUltimo(java.lang.Long,
	 *      java.lang.Long)
	 */
	@Override
	public DosimetroDTO getDataDosimetroUltimo(Long idEmpresa, Long idTrabajador)
			throws Exception {
		return dosimetroLogic.getDataDosimetroUltimo(idEmpresa, idTrabajador);
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 10/12/2016
	 * @param input
	 * @param arrayList
	 * @return
	 * @see com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView#generarArchivoAspromedica(java.io.InputStream,
	 *      java.util.ArrayList)
	 */
	@Override
	public ByteArrayOutputStream generarArchivoAspromedica(InputStream input,
			List<DosimetroDTO> selectedDosExcel, Long anioDosimetro,
			Long mesDosimetro) throws AppBaseException, IOException,
			JRException, SQLException {
		Map<String, Object> parametros = new HashMap<String, Object>();

		String cedulas = null;
		String dosimetros = null;
		String empresas = null;
		Long codEmpresa = null;
		for (DosimetroDTO dosimetroDTO : selectedDosExcel) {

			if (null == cedulas) {
				cedulas = "" + dosimetroDTO.getTraCed();
			} else {
				cedulas += "," + dosimetroDTO.getTraCed();
			}

			if (null == dosimetros) {
				dosimetros = "" + dosimetroDTO.getDosCod();
			} else {
				dosimetros += "," + dosimetroDTO.getDosCod();
			}
			
			if (null == empresas) {
				empresas = "" + dosimetroDTO.getEmpCod();
			} else {
				empresas += "," + dosimetroDTO.getEmpCod();
			}
			
			codEmpresa = dosimetroDTO.getEmpCod();
		}

		parametros.put("cedulas", "" + cedulas + "");
		parametros.put("dosimetros", "" + dosimetros + "");
		parametros.put("empresas", "" + empresas + "");
		parametros.put("codEmpresa", codEmpresa);
		parametros.put("anioDosimetro", anioDosimetro);
		parametros.put("mesDosimetro", mesDosimetro);
		return jasperService.generarExcel(input, parametros);
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 15/12/2016
	 * @param codEmpresa
	 * @return
	 * @throws Exception
	 * @see com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView#getDataContratosPorEmpresa(java.lang.Long)
	 */
	@Override
	public List<ContratosDTO> getDataContratosPorEmpresa(Long codEmpresa)
			throws Exception {
		return contratosLogic.getDataContratosPorEmpresa(codEmpresa);
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 15/12/2016
	 * @param codEmpresa
	 * @param codContrato
	 * @return
	 * @throws Exception
	 * @see com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView#getDataFacturaEmpresaContrato(java.lang.Long,
	 *      java.lang.Long)
	 */
	@Override
	public List<FacturaDTO> getDataFacturaEmpresaContrato(Long codEmpresa,
			Long codContrato) throws Exception {
		return facturaLogic.getDataFacturaEmpresaContrato(codEmpresa,
				codContrato);
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @param codEmpresa
	 * @param codDosimetro
	 * @return
	 * @throws Exception
	 * @see com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView#getDataDosimetroPrincipal(java.lang.Long,
	 *      java.lang.Long)
	 */
	@Override
	public List<DosimetroDTO> getDataDosimetroPrincipal(Long codEmpresa,
			Long codDosimetro) throws Exception {
		return dosimetroLogic.getDataDosimetroPrincipal(codEmpresa,
				codDosimetro);
	}
}
