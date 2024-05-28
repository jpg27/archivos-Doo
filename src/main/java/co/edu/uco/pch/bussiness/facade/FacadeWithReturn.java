package co.edu.uco.pch.bussiness.facade;

public interface FacadeWithReturn<T, K> {
	
	K execute(T dto);
	
	

}
