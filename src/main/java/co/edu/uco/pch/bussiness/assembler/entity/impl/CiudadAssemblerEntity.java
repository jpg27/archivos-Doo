package co.edu.uco.pch.bussiness.assembler.entity.impl;

import static co.edu.uco.pch.crosscutting.helpers.ObjetHelper.getObjetHelper;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.pch.bussiness.assembler.entity.AssemblerEntity;
import co.edu.uco.pch.bussiness.domain.CiudadDomain;
import co.edu.uco.pch.bussiness.domain.DepartamentoDomain;
import co.edu.uco.pch.crosscutting.helpers.ObjetHelper;
import co.edu.uco.pch.entity.CiudadEntity;
import co.edu.uco.pch.entity.DepartamentoEntity;

public final class CiudadAssemblerEntity implements AssemblerEntity<CiudadDomain, CiudadEntity>{
	
	private static final AssemblerEntity<CiudadDomain, CiudadEntity> instance = new CiudadAssemblerEntity();
	private static final AssemblerEntity<DepartamentoDomain, DepartamentoEntity> departamentoAssembler = DepartamentoAssemblerEntity.getInstance();
	
	private CiudadAssemblerEntity() {
		super();
	}
	
	public static final AssemblerEntity<CiudadDomain, CiudadEntity> getInstance(){
		return instance;
	}

	@Override
	public final CiudadDomain toDomain(final CiudadEntity data) {
		var ciudadEntityTmp = getObjetHelper().getDefaultValue(data, CiudadEntity.build());
		var departamentoDomain = departamentoAssembler.toDomain(ciudadEntityTmp.getDepartamento());
		return CiudadDomain.build(ciudadEntityTmp.getId(), ciudadEntityTmp.getNombre(), departamentoDomain);
	}

	@Override
	public final CiudadEntity toEntity(final CiudadDomain domain) {
		var ciudadDomainTmp = getObjetHelper().getDefaultValue(domain, CiudadDomain.build());
		var departamentoEntity = departamentoAssembler.toEntity(ciudadDomainTmp.getDepartamento());
		return CiudadEntity.build().setId(ciudadDomainTmp.getId()).setNombre(ciudadDomainTmp.getNombre()).setDepartamento(departamentoEntity);
	}

	@Override
	public final List<CiudadDomain> toDomainCollection(final List<CiudadEntity> entityCollection) {
		var entityCollectionTmp = ObjetHelper.getObjetHelper().getDefaultValue(entityCollection, new ArrayList<CiudadEntity>());
		
		return entityCollectionTmp.stream().map(this::toDomain).toList();
		
	}

}
