package co.edu.uco.pch.bussiness.assembler.dto.impl;

import static co.edu.uco.pch.crosscutting.helpers.ObjetHelper.getObjetHelper;

import co.edu.uco.pch.bussiness.assembler.dto.AssemblerDTO;
import co.edu.uco.pch.bussiness.domain.CiudadDomain;
import co.edu.uco.pch.bussiness.domain.DepartamentoDomain;
import co.edu.uco.pch.dto.CiudadDTO;
import co.edu.uco.pch.dto.DepartamentoDTO;

public final class CiudadAssemblerDTO implements AssemblerDTO<CiudadDomain, CiudadDTO> {
	
	private static final AssemblerDTO<DepartamentoDomain, DepartamentoDTO> departamentoAssembler = DepartamentoAssemblerDTO.getInstance();
	private static final AssemblerDTO<CiudadDomain, CiudadDTO> instance = new CiudadAssemblerDTO();
	
	private CiudadAssemblerDTO() {
		super();
	}
	
	public static final AssemblerDTO<CiudadDomain, CiudadDTO> getInstance(){
		return instance;
	}

	@Override
	public final CiudadDomain toDomain(final CiudadDTO data) {
		var ciudadDtoTmp = getObjetHelper().getDefaultValue(data, CiudadDTO.build());
		var departamentoDomain = departamentoAssembler.toDomain(ciudadDtoTmp.getDepartamento());
		return CiudadDomain.build(ciudadDtoTmp.getId(), ciudadDtoTmp.getNombre(), departamentoDomain);
	}

	@Override
	public final CiudadDTO toDTO(CiudadDomain domain) {
		var ciudadDomainTmp = getObjetHelper().getDefaultValue(domain, CiudadDomain.build());
		var departamentoDto = departamentoAssembler.toDTO(ciudadDomainTmp.getDepartamento());
		return CiudadDTO.build().setId(ciudadDomainTmp.getId()).setNombre(ciudadDomainTmp.getNombre()).setDepartamento(departamentoDto);
	}

}
