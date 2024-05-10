package co.edu.uco.pch.crosscutting.exceptions.messageCatalog;

import co.edu.uco.pch.crosscutting.exceptions.custom.CrosscuttingPCHException;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.data.CodigoMensaje;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.data.Mensaje;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.impl.MessageCatalogBase;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.impl.MessageCatalogExternalService;
import co.edu.uco.pch.crosscutting.helpers.ObjetHelper;

public final class MessageCatalogStrategy {
	
	private static final MessageCatalog base = new MessageCatalogBase();
	private static final MessageCatalog externalService = new MessageCatalogExternalService();
	
	private MessageCatalogStrategy() {
		super();
	}
	
	public static void inicializar() {
		base.inicializar();
		externalService.inicializar();
	}
	
	private static final MessageCatalog getStrategy(final boolean esBase) {
		return esBase? base: externalService;
	}
	
	public static final Mensaje getMensaje(final CodigoMensaje codigo, final String...parametros) {
		
		if(ObjetHelper.getObjetHelper().isNull(codigo)) {
			throw new CrosscuttingPCHException(null, null);
		}
		
		return getStrategy(codigo.esBase()).obtenerMensaje(codigo, parametros);
	}

}
