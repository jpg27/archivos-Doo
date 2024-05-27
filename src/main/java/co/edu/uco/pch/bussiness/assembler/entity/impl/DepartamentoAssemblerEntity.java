package co.edu.uco.pch.bussiness.assembler.entity.impl;

import static co.edu.uco.pch.crosscutting.helpers.ObjetHelper.getObjetHelper;

import co.edu.uco.pch.bussiness.assembler.entity.AssemblerEntity;
import co.edu.uco.pch.bussiness.domain.DepartamentoDomain;
import co.edu.uco.pch.bussiness.domain.PaisDomain;
import co.edu.uco.pch.entity.DepartamentoEntity;
import co.edu.uco.pch.entity.PaisEntity;

public final class DepartamentoAssemblerEntity implements AssemblerEntity<DepartamentoDomain, DepartamentoEntity> {
	
	private static final AssemblerEntity<DepartamentoDomain, DepartamentoEntity> instance = new DepartamentoAssemblerEntity();
	private static final AssemblerEntity<PaisDomain, PaisEntity> paisAssembler = PaisAssemblerEntity.getInstance();
	
	private DepartamentoAssemblerEntity() {
		super();
	}
	
	public static final AssemblerEntity<DepartamentoDomain, DepartamentoEntity> getInstance(){
		return instance;
	}

	@Override
	public final DepartamentoDomain toDomain(final DepartamentoEntity data) {
		var departamentoEntityTmp = getObjetHelper().getDefaultValue(data, DepartamentoEntity.build());
		var paisDomain = paisAssembler.toDomain(departamentoEntityTmp.getPais());
		return DepartamentoDomain.build(departamentoEntityTmp.getId(), departamentoEntityTmp.getNombre(), paisDomain);
	}

	@Override
	public final DepartamentoEntity toEntity(final DepartamentoDomain domain) {
		var departamentoDomainTmp = getObjetHelper().getDefaultValue(domain, DepartamentoDomain.build());
		var paisEntity = paisAssembler.toEntity(departamentoDomainTmp.getPais());
		return DepartamentoEntity.build().setId(departamentoDomainTmp.getId()).setNombre(departamentoDomainTmp.getNombre()).setPais(paisEntity);
	}

}
