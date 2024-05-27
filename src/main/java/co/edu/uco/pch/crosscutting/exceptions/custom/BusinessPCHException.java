package co.edu.uco.pch.crosscutting.exceptions.custom;

import co.edu.uco.pch.crosscutting.exceptions.PCHException;
import co.edu.uco.pch.crosscutting.exceptions.enums.Lugar;

public class BusinessPCHException extends PCHException {
	
	private static final long serialVersionUID = 1L;
	private static final Lugar lugar = Lugar.BUSINESS;

	public BusinessPCHException(final String mensajeUsuario) {
		super(mensajeUsuario, lugar);
	}
	
	public BusinessPCHException(final String mensajeTecnico, final String mensajeUsuario) {
		super(mensajeTecnico,mensajeUsuario, lugar);
	}
	
	public BusinessPCHException(final String mensajeTecnico,final String mensajeUsuario,final Throwable excepcionRaiz) {
		super(mensajeTecnico, mensajeUsuario, lugar, excepcionRaiz);
	}

}
