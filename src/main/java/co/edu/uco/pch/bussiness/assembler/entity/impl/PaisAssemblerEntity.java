package co.edu.uco.pch.bussiness.assembler.entity.impl;

import static co.edu.uco.pch.crosscutting.helpers.ObjetHelper.getObjetHelper;

import co.edu.uco.pch.bussiness.assembler.entity.AssemblerEntity;
import co.edu.uco.pch.bussiness.domain.PaisDomain;
import co.edu.uco.pch.entity.PaisEntity;

public class PaisAssemblerEntity implements AssemblerEntity<PaisDomain, PaisEntity> {
	
	private static final AssemblerEntity<PaisDomain, PaisEntity> instance = new PaisAssemblerEntity();
	private PaisAssemblerEntity() {
		super();
	}
	
	public static final AssemblerEntity<PaisDomain, PaisEntity> getInstance(){
		return instance;
	}

	@Override
	public final PaisDomain toDomain(final PaisEntity data) {
		var paisEntityTmp = getObjetHelper().getDefaultValue(data, PaisEntity.build());
		return PaisDomain.build(paisEntityTmp.getId(), paisEntityTmp.getNombre());
	}

	@Override
	public final  PaisEntity toEntity(final PaisDomain domain) {
		var paisDomainTmp = getObjetHelper().getDefaultValue(domain, PaisDomain.build());
		return PaisEntity.build().setId(paisDomainTmp.getId()).setNombre(paisDomainTmp.getNombre());
	}

}
