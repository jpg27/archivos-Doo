package co.edu.uco.pch.data.dao.factory.concrete;

import java.sql.DriverManager;
import java.sql.SQLException;

import co.edu.uco.pch.crosscutting.exceptions.custom.DataPCHException;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.data.CodigoMensaje;
import co.edu.uco.pch.crosscutting.helpers.SQLHelper;
import co.edu.uco.pch.data.dao.entity.CiudadDAO;
import co.edu.uco.pch.data.dao.entity.DepartamentoDAO;
import co.edu.uco.pch.data.dao.entity.PaisDAO;
import co.edu.uco.pch.data.dao.entity.concrete.SqlConnection;
import co.edu.uco.pch.data.dao.entity.concrete.azuresql.CiudadAzureSqlDAO;
import co.edu.uco.pch.data.dao.entity.concrete.azuresql.DepartamentoAzureSqlDAO;
import co.edu.uco.pch.data.dao.entity.concrete.azuresql.PaisAzureSqlDAO;
import co.edu.uco.pch.data.dao.factory.DAOFactory;


public final class AzureSQLDAOFactory extends SqlConnection implements DAOFactory {

	public AzureSQLDAOFactory() {
		super();
		abrirConexion();
	}

	private void abrirConexion() {
		final String connectionUrl = "jdbc:sqlserver://wednesday.database.windows.net:1433;databaseName=friday;user=fridayDmlUser;password=fr1d4yus3r!";
		try {
			setConexion(DriverManager.getConnection(connectionUrl));
		} catch (final SQLException excepcion) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00027);

			throw new DataPCHException(mensajeTecnico, mensajeUsuario, excepcion);
		} catch (final Exception excepcion) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00028);

			throw new DataPCHException(mensajeTecnico, mensajeUsuario, excepcion);
		}
	}

	@Override
	public void cerrarConexion() {
		SQLHelper.close(getConexion());
	}

	@Override
	public void iniciarTransaccion() {
		SQLHelper.initTransaction(getConexion());
	}

	@Override
	public void confirmarTransaccion() {
		SQLHelper.commit(getConexion());
	}

	@Override
	public void cancelarTransaccion() {
		SQLHelper.rollback(getConexion());
	}

	@Override
	public PaisDAO getPaisDAO() {
		return new PaisAzureSqlDAO(getConexion());
	}

	@Override
	public DepartamentoDAO getDepartamentoDAO() {
		return new DepartamentoAzureSqlDAO(getConexion());
	}

	@Override
	public CiudadDAO getCiudadDAO() {
		return new CiudadAzureSqlDAO(getConexion());
	}
	
}
