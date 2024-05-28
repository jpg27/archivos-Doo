package co.edu.uco.pch.bussiness.facade.impl.ciudad;

import co.edu.uco.pch.bussiness.assembler.dto.impl.CiudadAssemblerDTO;
import co.edu.uco.pch.bussiness.facade.FacadeWithoutReturn;
import co.edu.uco.pch.bussiness.usecase.impl.ciudad.RegistrarCiudad;
import co.edu.uco.pch.crosscutting.exceptions.PCHException;
import co.edu.uco.pch.crosscutting.exceptions.custom.BusinessPCHException;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.data.CodigoMensaje;
import co.edu.uco.pch.data.dao.factory.DAOFactory;
import co.edu.uco.pch.dto.CiudadDTO;

public final class RegistrarCiudadFacade implements FacadeWithoutReturn<CiudadDTO> {
	
	private DAOFactory daoFactory;
	
	public RegistrarCiudadFacade() {
		daoFactory = DAOFactory.getFactory();
	}

	@Override
	public void execute(final CiudadDTO dto) {
		
		daoFactory.iniciarTransaccion();
		
		try {
			var useCase = new RegistrarCiudad(daoFactory);
			var CiudadDomain = CiudadAssemblerDTO.getInstance().toDomain(dto);
			useCase.execute(CiudadDomain);
			
			daoFactory.confirmarTransaccion();
		}catch(final PCHException excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		}catch(final Exception excepcion) {
			daoFactory.cancelarTransaccion();
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00023);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00031);
			
			throw new BusinessPCHException(mensajeTecnico, mensajeUsuario, excepcion);
		}finally {
			daoFactory.cerrarConexion();
		}
	}

}
