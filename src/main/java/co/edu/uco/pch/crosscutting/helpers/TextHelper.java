package co.edu.uco.pch.crosscutting.helpers;


public final class TextHelper {
	
	public static final String EMPTY= "";
	public static final String UNDERLINE= "_";
	
	private TextHelper() {
		super();
	}
	
	public static final boolean isNull(final String string) {
		return ObjetHelper.getObjetHelper().isNull(string);
	}
	
	public static final boolean isNullOrEmpty(final String string) {
		return isNull(string) || EMPTY.equals(applyTrim(string));
	}
	
	public static final String getDefaultValue(final String string, final String defaultValue) {
		return ObjetHelper.getObjetHelper().getDefaultValue(string, defaultValue);
	}
	
	public static final String getDefaultValue(final String string) {
		return getDefaultValue(string, EMPTY);
	}
	
	public static final String applyTrim(final String string) {
		return getDefaultValue(string).trim();
				
	}
	
	public static final String concatenate(final String... strings) {
		final var sb = new StringBuilder(EMPTY);
		
		if(!ObjetHelper.getObjetHelper().isNull(strings)) {
			for (final var string : strings) {
				sb.append(applyTrim(string));
			}
		
		}
		
		return sb.toString();
	}


}
