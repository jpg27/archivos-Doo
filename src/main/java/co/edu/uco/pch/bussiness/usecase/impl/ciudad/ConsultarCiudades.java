package co.edu.uco.pch.bussiness.usecase.impl.ciudad;

import java.util.List;

import co.edu.uco.pch.bussiness.assembler.entity.impl.CiudadAssemblerEntity;
import co.edu.uco.pch.bussiness.domain.CiudadDomain;
import co.edu.uco.pch.bussiness.usecase.UseCaseWithReturn;
import co.edu.uco.pch.crosscutting.exceptions.custom.BusinessPCHException;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.data.CodigoMensaje;
import co.edu.uco.pch.crosscutting.helpers.ObjetHelper;
import co.edu.uco.pch.data.dao.factory.DAOFactory;

public class ConsultarCiudades implements UseCaseWithReturn<CiudadDomain, List<CiudadDomain>> {
	
	private DAOFactory factory;
	
	public ConsultarCiudades(final DAOFactory factory) {
		if(ObjetHelper.getObjetHelper().isNull(factory)) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00029);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00030);
			throw new BusinessPCHException(mensajeTecnico, mensajeUsuario);
		}
		
		this.factory = factory;
	}

	@Override
	public final List<CiudadDomain> execute(final CiudadDomain data) {
		var ciudadEntityFilter = CiudadAssemblerEntity.getInstance().toEntity(data);
		var resultadosEntity = factory.getCiudadDAO().consultar(ciudadEntityFilter);
		
		
		return CiudadAssemblerEntity.getInstance().toDomainCollection(resultadosEntity);;
	}




}
