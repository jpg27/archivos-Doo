package co.edu.uco.pch.bussiness.usecase;

public interface UseCaseWithoutReturn<T> {
	
	void execute(T data);
}
