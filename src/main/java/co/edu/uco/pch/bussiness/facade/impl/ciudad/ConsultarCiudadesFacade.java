package co.edu.uco.pch.bussiness.facade.impl.ciudad;

import java.util.List;

import co.edu.uco.pch.bussiness.assembler.dto.impl.CiudadAssemblerDTO;
import co.edu.uco.pch.bussiness.facade.FacadeWithReturn;
import co.edu.uco.pch.bussiness.usecase.impl.ciudad.ConsultarCiudades;
import co.edu.uco.pch.crosscutting.exceptions.PCHException;
import co.edu.uco.pch.crosscutting.exceptions.custom.BusinessPCHException;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.data.CodigoMensaje;
import co.edu.uco.pch.data.dao.factory.DAOFactory;
import co.edu.uco.pch.dto.CiudadDTO;

public final class ConsultarCiudadesFacade implements FacadeWithReturn<CiudadDTO, List<CiudadDTO>> {
	
	private DAOFactory daoFactory;
	
	public ConsultarCiudadesFacade() {
		daoFactory = DAOFactory.getFactory();
	}
	

	@Override
	public final List<CiudadDTO> execute(final CiudadDTO dto) {
		
		try {
			var useCase = new ConsultarCiudades(daoFactory);
			var CiudadDomain = CiudadAssemblerDTO.getInstance().toDomain(dto);
			var resultadosDomain = useCase.execute(CiudadDomain);	
			
			return CiudadAssemblerDTO.getInstance().toDTOCollection(resultadosDomain);
		}catch(final PCHException excepcion) {
			throw excepcion;
		}catch(final Exception excepcion) {
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00032);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00033);
			
			throw new BusinessPCHException(mensajeTecnico, mensajeUsuario, excepcion);
		}finally {
			daoFactory.cerrarConexion();
		}
	}

}
