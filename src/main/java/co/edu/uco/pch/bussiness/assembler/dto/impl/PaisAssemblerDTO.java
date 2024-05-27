package co.edu.uco.pch.bussiness.assembler.dto.impl;

import co.edu.uco.pch.dto.PaisDTO;

import static co.edu.uco.pch.crosscutting.helpers.ObjetHelper.getObjetHelper;

import co.edu.uco.pch.bussiness.assembler.dto.AssemblerDTO;
import co.edu.uco.pch.bussiness.domain.PaisDomain;

public final class PaisAssemblerDTO implements AssemblerDTO<PaisDomain, PaisDTO>{
	
	private static final AssemblerDTO<PaisDomain, PaisDTO> instance = new PaisAssemblerDTO();
	
	private PaisAssemblerDTO() {
		super();
	}
	
	public static final AssemblerDTO<PaisDomain, PaisDTO> getInstance(){
		return instance;
	}

	@Override
	public final PaisDomain toDomain(final PaisDTO data) {
		var paisDtoTmp = getObjetHelper().getDefaultValue(data, PaisDTO.build());
		return PaisDomain.build(paisDtoTmp.getId(), paisDtoTmp.getNombre());
	}

	@Override
	public final PaisDTO toDTO(final PaisDomain domain) {
		var paisDomainTmp = getObjetHelper().getDefaultValue(domain, PaisDomain.build());
		// TODO Auto-generated method stub
		return PaisDTO.build().setId(paisDomainTmp.getId()).setNombre(paisDomainTmp.getNombre());
	}

}
