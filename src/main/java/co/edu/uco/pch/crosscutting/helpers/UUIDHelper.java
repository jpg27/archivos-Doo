package co.edu.uco.pch.crosscutting.helpers;

import java.util.UUID;

public final class UUIDHelper {
	
	private static final String DEFAULT_UUID_STRING = "00000000-0000-0000-0000-000000000000";
	
	private UUIDHelper() {
		super();
	}
	
	public static final UUID convertToUUID(final String uuidAsString) {
		return UUID .fromString(uuidAsString);
	}
	
	public static final UUID getDefault(final UUID value, final UUID defaultValue) {
		return ObjetHelper.getObjetHelper().getDefaultValue(value, defaultValue);
	}
	
	public static final UUID getDefault() {
		return convertToUUID(DEFAULT_UUID_STRING);
	}
	
	public static final UUID generate() {
		return UUID.randomUUID();
	}
 /*   
    public static final UUID EMPTY = new UUID(0L, 0L);
    
    private UUIDHelper() {
        super();
    }
    
    public static final boolean isNull(final UUID uuid) {
        return uuid == null || uuid.equals(EMPTY);
    }
    
    public static final boolean isNullOrEmpty(final UUID uuid) {
        return isNull(uuid);
    }
    
    public static final UUID getDefaultValue(final UUID uuid, final UUID defaultValue) {
        return isNullOrEmpty(uuid) ? defaultValue : uuid;
    }
    
    public static final UUID getDefaultValue(final UUID uuid) {
        return getDefaultValue(uuid, EMPTY);
    }*/
}

