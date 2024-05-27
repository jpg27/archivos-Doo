package co.edu.uco.pch.bussiness.assembler;

public interface Assembler<D, K> {
	
	D toDomain(K data);

}
