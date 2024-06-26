package co.edu.uco.pch.bussiness.assembler.dto.impl;

import co.edu.uco.pch.dto.DepartamentoDTO;
import co.edu.uco.pch.dto.PaisDTO;

import static co.edu.uco.pch.crosscutting.helpers.ObjetHelper.getObjetHelper;

import java.util.List;

import co.edu.uco.pch.bussiness.assembler.dto.AssemblerDTO;
import co.edu.uco.pch.bussiness.domain.DepartamentoDomain;
import co.edu.uco.pch.bussiness.domain.PaisDomain;

public final class DepartamentoAssemblerDTO implements AssemblerDTO<DepartamentoDomain, DepartamentoDTO>{
	
	private static final AssemblerDTO<PaisDomain, PaisDTO> paisAssembler = PaisAssemblerDTO.getInstance();
	private static final AssemblerDTO<DepartamentoDomain, DepartamentoDTO> instance = new DepartamentoAssemblerDTO();
	
	private DepartamentoAssemblerDTO() {
		super();
	}
	
	public final static AssemblerDTO<DepartamentoDomain, DepartamentoDTO> getInstance(){
		return instance;
	}

	@Override
	public final DepartamentoDomain toDomain(final DepartamentoDTO data) {
		var departamentoDtoTmp = getObjetHelper().getDefaultValue(data, DepartamentoDTO.build());
		var paisDomain = paisAssembler.toDomain(departamentoDtoTmp.getPais());
		return DepartamentoDomain.build(departamentoDtoTmp.getId(), departamentoDtoTmp.getNombre(), paisDomain);
	}

	@Override
	public final DepartamentoDTO toDTO(final DepartamentoDomain domain) {
		var departamentoDomainTmp = getObjetHelper().getDefaultValue(domain, DepartamentoDomain.build());
		var paisDto = paisAssembler.toDTO(departamentoDomainTmp.getPais());
		return DepartamentoDTO.build().setId(departamentoDomainTmp.getId()).setNombre(departamentoDomainTmp.getNombre()).setPais(paisDto);
	}

	@Override
	public List<DepartamentoDomain> toDomainCollection(List<DepartamentoDTO> entityCollection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DepartamentoDTO> toDTOCollection(List<DepartamentoDomain> domainCollection) {
		// TODO Auto-generated method stub
		return null;
	}

}