package co.edu.uco.pch.bussiness.assembler.dto.impl;

import static co.edu.uco.pch.crosscutting.helpers.ObjetHelper.getObjetHelper;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.pch.bussiness.assembler.dto.AssemblerDTO;
import co.edu.uco.pch.bussiness.domain.CiudadDomain;
import co.edu.uco.pch.bussiness.domain.DepartamentoDomain;
import co.edu.uco.pch.crosscutting.helpers.ObjetHelper;
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

	@Override
	public final List<CiudadDomain> toDomainCollection(final List<CiudadDTO> dtoCollection) {
		var dtoCollectionTmp = ObjetHelper.getObjetHelper().getDefaultValue(dtoCollection, new ArrayList<CiudadDTO>());
		
		var resultadosDomain = new ArrayList<CiudadDomain>();
		
		for (CiudadDTO ciudadDto : dtoCollectionTmp) {
			var ciudadDomainTmp = toDomain(ciudadDto);
			resultadosDomain.add(ciudadDomainTmp);
		}
		return resultadosDomain;
	}

	@Override
	public final List<CiudadDTO> toDTOCollection(final List<CiudadDomain> domainCollection) {
		var domainCollectionTmp = ObjetHelper.getObjetHelper().getDefaultValue(domainCollection, new ArrayList<CiudadDomain>());

		return domainCollectionTmp.stream().map(this::toDTO).toList();
	}

}
