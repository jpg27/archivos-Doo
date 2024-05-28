package co.edu.uco.pch.bussiness.assembler;

import java.util.List;

public interface Assembler<D, K> {
	
	D toDomain(K data);
	
	List<D> toDomainCollection(List<K> entityCollection);

}
