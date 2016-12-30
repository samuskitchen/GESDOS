package com.agileillutions.gesdos.exceptions;

/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 * www.zathuracode.org
 * 
 */
public class ZMessManager extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public final static String ALL = "Todos ";
	public final static String ENTCHILD = "Tablas relacionadas ";
	public final static String FOREIGNDATA = "Datos de clases extranjeras: ";
	public static String ENTITY_SUCCESFULLYSAVED = "Entidad guardada correctamente ";
	public static String ENTITY_SUCCESFULLYDELETED = "Entidad eliminada correctamente ";
	public static String ENTITY_SUCCESFULLYMODIFIED = "Entidad modificada correctamente ";
	public static String ENTITY_WITHSAMEKEY = "Se encontró otra entidad con la misma clave ";
	public static String ENTITY_NOENTITYTOUPDATE = "No se encontró ninguna entidad, con la tecla escrita ";

	public ZMessManager() {
	}

	public ZMessManager(String exception) {
		super(exception);
	}

	public class NotValidFieldException extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public NotValidFieldException(String info) {
			super("El valor para el campo: \"" + info + "\" no es válido");
		}
	}
	
	public class NullEntityExcepcion extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public NullEntityExcepcion(String info) {
			super("El " + info + " Entidad No puede ser nulo o vacío");
		}
	}

	public class EmptyFieldException extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public EmptyFieldException(String info) {
			super("El valor para el campo: \"" + info
					+ "\" No puede ser nulo o vacío");
		}
	}

	public class NotValidFormatException extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public NotValidFormatException(String info) {
			super("Formato o longitud del campo: \"" + info
					+ "\" no es válido");
		}
	}

	public class DeletingException extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public DeletingException(String info) {
			super("La entidad que está intentando eliminar "
					+ "Puede tener información relacionada, "
					+ "Por favor antes de intentar de nuevo, "
					+ "Comprobar los datos de la entidad, \"" + info+"\"");
		}
	}
	
	public class ForeignException extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public ForeignException(String info) {
			super("No hubo datos relacionados con la entrada \"" + info+ "\"");
		}
	}	

	public class GettingException extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public GettingException(String info) {
			super("Hubo una excepción " + info);
		}
	}

	public class FindingException extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public FindingException(String info) {
			super("Hubo una excepción tratando de encontrar " + info);
		}
	}

}
