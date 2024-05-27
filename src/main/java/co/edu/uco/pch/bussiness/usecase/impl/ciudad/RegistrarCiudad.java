package co.edu.uco.pch.bussiness.usecase.impl.ciudad;

import java.util.UUID;


import co.edu.uco.pch.bussiness.assembler.entity.impl.DepartamentoAssemblerEntity;
import co.edu.uco.pch.bussiness.domain.CiudadDomain;
import co.edu.uco.pch.bussiness.usecase.UseCaseWithoutReturn;
import co.edu.uco.pch.crosscutting.exceptions.custom.BusinessPCHException;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.data.CodigoMensaje;
import co.edu.uco.pch.crosscutting.helpers.ObjetHelper;
import co.edu.uco.pch.crosscutting.helpers.UUIDHelper;
import co.edu.uco.pch.data.dao.factory.DAOFactory;
import co.edu.uco.pch.entity.CiudadEntity;
import co.edu.uco.pch.entity.DepartamentoEntity;

public final class RegistrarCiudad implements UseCaseWithoutReturn<CiudadDomain>{
	
	private DAOFactory factory;
	
	public RegistrarCiudad(final DAOFactory factory) {
		if(ObjetHelper.getObjetHelper().isNull(factory)) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00023);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00025);
			throw new BusinessPCHException(mensajeTecnico, mensajeUsuario);
		}
		
		this.factory = factory;
	}

	@Override
	public void execute(final CiudadDomain data) {
		//lo que el copio de politicas
		validarCiudadMismoNombreMismoDepartamento(data.getNombre(), data.getDepartamento().getId());
		
		var ciudadEntity = CiudadEntity.build().setId(generarIdentificadorCiudad()).setNombre(data.getNombre())
				.setDepartamento(DepartamentoAssemblerEntity.getInstance().toEntity(data.getDepartamento()));
		
		factory.getCiudadDAO().crear(ciudadEntity);
	}
	
	private final UUID generarIdentificadorCiudad() {
		UUID id = UUIDHelper.generate();
		boolean existeId = true;
		
		while(!existeId) {
			id = UUIDHelper.generate();
			var ciudadEntity = CiudadEntity.build().setId(id);
			var resultados = factory.getCiudadDAO().consultar(ciudadEntity);
			existeId = !resultados.isEmpty();
		}
		
		return id;
	}
	
	private final void validarCiudadMismoNombreMismoDepartamento(final String nombreCiudad, final UUID idDepartamento) {
		var ciudadEntity = CiudadEntity.build().setNombre(nombreCiudad).setDepartamento(DepartamentoEntity.build().setId(idDepartamento));
		var resultados = factory.getCiudadDAO().consultar(ciudadEntity);
		
		if(!resultados.isEmpty()) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00026);
			throw new BusinessPCHException(mensajeUsuario);
		}
	}

}
