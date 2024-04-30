package co.edu.uco.pch.crosscutting.helpers;

public final class ObjetHelper {
	
	private static final ObjetHelper INSTANCE = new ObjetHelper();
	
	private ObjetHelper() {
		super();
	}
	
	public static final ObjetHelper getObjetHelper() {
		return INSTANCE;
	}
	
	public <O> boolean isNull(O objeto) {
		return objeto == null;
	}
	
	public <O> O getDefaultValue(O objeto, O valorDefecto) {
		return isNull(objeto)? valorDefecto: objeto;
	}


}
