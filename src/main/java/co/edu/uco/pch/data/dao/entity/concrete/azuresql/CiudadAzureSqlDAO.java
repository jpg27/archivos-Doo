package co.edu.uco.pch.data.dao.entity.concrete.azuresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.pch.crosscutting.exceptions.custom.DataPCHException;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.data.CodigoMensaje;
import co.edu.uco.pch.crosscutting.helpers.UUIDHelper;
import co.edu.uco.pch.data.dao.entity.CiudadDAO;
import co.edu.uco.pch.data.dao.entity.concrete.SqlConnection;
import co.edu.uco.pch.entity.CiudadEntity;
import co.edu.uco.pch.entity.DepartamentoEntity;
import co.edu.uco.pch.entity.PaisEntity;


public final class CiudadAzureSqlDAO extends SqlConnection implements CiudadDAO{

	public CiudadAzureSqlDAO(final Connection conexion) {
		super(conexion);
	}

	@Override
	public final void crear(final CiudadEntity data) {
		final StringBuilder sentenciaSql = new StringBuilder();
		
		sentenciaSql.append("INSERT INTO Ciudad (id, nombre, departamento) ");
		sentenciaSql.append("SELECT ?,?,?");
		
		try(final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
			
			sentenciaSqlPreparada.setObject(1, data.getId());
			sentenciaSqlPreparada.setString(2, data.getNombre());
			sentenciaSqlPreparada.setObject(3, data.getDepartamento().getId());
			
			sentenciaSqlPreparada.executeUpdate();
			
		}catch(final SQLException excepcion) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00023);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00024);
			
			throw new DataPCHException(mensajeTecnico, mensajeUsuario, excepcion);
		}catch(final Exception excepcion) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00023);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00022);
			
			throw new DataPCHException(mensajeTecnico, mensajeUsuario, excepcion);
		}
		
	}

	@Override
	public List<CiudadEntity> consultar(CiudadEntity data) {
		
	    final List<CiudadEntity> ciudades = new ArrayList<>();
	    final StringBuilder sentenciaSql = new StringBuilder();
	    
	    sentenciaSql.append("SELECT c.id, c.nombre, d.id AS departamento_id, d.nombre AS departamento_nombre, ");
	    sentenciaSql.append("p.id AS pais_id, p.nombre AS pais_nombre ");
	    sentenciaSql.append("FROM Ciudad c ");
	    sentenciaSql.append("JOIN Departamento d ON c.departamento = d.id ");
	    sentenciaSql.append("JOIN Pais p ON d.pais = p.id ");
	    sentenciaSql.append("WHERE 1=1 ");
	    
	    if (data != null) {
	        if (data.getId() != null) {
	            sentenciaSql.append("AND c.id = ? ");
	        }
	        if (data.getNombre() != null && !data.getNombre().isEmpty()) {
	            sentenciaSql.append("AND c.nombre LIKE ? ");
	        }
	        if (data.getDepartamento() != null && data.getDepartamento().getId() != null) {
	            sentenciaSql.append("AND d.id = ? ");
	        }
	        if (data.getDepartamento() != null && data.getDepartamento().getNombre() != null && !data.getDepartamento().getNombre().isEmpty()) {
	            sentenciaSql.append("AND d.nombre LIKE ? ");
	        }
	        if (data.getDepartamento() != null && data.getDepartamento().getPais() != null && data.getDepartamento().getPais().getId() != null) {
	            sentenciaSql.append("AND p.id = ? ");
	        }
	        if (data.getDepartamento() != null && data.getDepartamento().getPais() != null && data.getDepartamento().getPais().getNombre() != null && !data.getDepartamento().getPais().getNombre().isEmpty()) {
	            sentenciaSql.append("AND p.nombre LIKE ? ");
	        }
	    }

	    try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
	        int parameterIndex = 1;

	        if (data != null) {
	            if (data.getId() != null) {
	                sentenciaSqlPreparada.setObject(parameterIndex++, data.getId());
	            }
	            if (data.getNombre() != null && !data.getNombre().isEmpty()) {
	                sentenciaSqlPreparada.setString(parameterIndex++, "%" + data.getNombre() + "%");
	            }
	            if (data.getDepartamento() != null && data.getDepartamento().getId() != null) {
	                sentenciaSqlPreparada.setObject(parameterIndex++, data.getDepartamento().getId());
	            }
	            if (data.getDepartamento() != null && data.getDepartamento().getNombre() != null && !data.getDepartamento().getNombre().isEmpty()) {
	                sentenciaSqlPreparada.setString(parameterIndex++, "%" + data.getDepartamento().getNombre() + "%");
	            }
	            if (data.getDepartamento() != null && data.getDepartamento().getPais() != null && data.getDepartamento().getPais().getId() != null) {
	                sentenciaSqlPreparada.setObject(parameterIndex++, data.getDepartamento().getPais().getId());
	            }
	            if (data.getDepartamento() != null && data.getDepartamento().getPais() != null && data.getDepartamento().getPais().getNombre() != null && !data.getDepartamento().getPais().getNombre().isEmpty()) {
	                sentenciaSqlPreparada.setString(parameterIndex++, "%" + data.getDepartamento().getPais().getNombre() + "%");
	            }
	        }

	        try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {
	            while (resultado.next()) {
	                final UUID idCiudad = UUIDHelper.convertToUUID(resultado.getString("id"));
	                final String nombreCiudad = resultado.getString("nombre");
	                
	                final UUID idDepartamento = UUIDHelper.convertToUUID(resultado.getString("departamento_id"));
	                final String nombreDepartamento = resultado.getString("departamento_nombre");
	                
	                final UUID idPais = UUIDHelper.convertToUUID(resultado.getString("pais_id"));
	                final String nombrePais = resultado.getString("pais_nombre");

	                final PaisEntity pais = PaisEntity.build()
	                        .setId(idPais)
	                        .setNombre(nombrePais);

	                final DepartamentoEntity departamento = DepartamentoEntity.build()
	                        .setId(idDepartamento)
	                        .setNombre(nombreDepartamento)
	                        .setPais(pais);

	                final CiudadEntity ciudad = CiudadEntity.build()
	                        .setId(idCiudad)
	                        .setNombre(nombreCiudad)
	                        .setDepartamento(departamento);

	                ciudades.add(ciudad);
	            }
	        }
	    } catch (final SQLException excepcion) {
	        var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00023);
	        var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00024);
	        
	        throw new DataPCHException(mensajeTecnico, mensajeUsuario, excepcion);
	    } catch (final Exception excepcion) {
	        var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00023);
	        var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00022);
	        
	        throw new DataPCHException(mensajeTecnico, mensajeUsuario, excepcion);
	    }

	    return ciudades;

	}

	@Override
	public void modificar(CiudadEntity data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(UUID id) {
		// TODO Auto-generated method stub
		
	}

}
