package co.edu.uco.pch.bussiness.assembler.dto;

import java.util.List;

import co.edu.uco.pch.bussiness.assembler.Assembler;

public interface AssemblerDTO<D, K> extends Assembler<D, K>{
	
	K toDTO(D domain);
	
	List<K> toDTOCollection(List<D> domainCollection);

}
