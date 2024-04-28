package co.edu.uco.pch.crosscutting.helpers;

import java.util.UUID;

public final class UUIDHelper {
    
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
    }
}

