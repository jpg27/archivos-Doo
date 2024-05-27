package co.edu.uco.pch.bussiness.assembler.entity;

import co.edu.uco.pch.bussiness.assembler.Assembler;

public interface AssemblerEntity<D, K> extends Assembler<D, K> {
	
	K toEntity(D domain);

}
