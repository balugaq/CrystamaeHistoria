package io.github.sefiraat.crystamaehistoria.magic;

import lombok.Getter;

public enum CastResult {
    CAST_SUCCESS("成功"),
    CAST_FAIL_NO_CRYSTA("充能不足"),
    CAST_FAIL_SLOT_EMPTY("该栏位没有法术"),
    ON_COOLDOWN("法术冷却中"),
    SPELL_DISABLED("该法术已被禁用");

    @Getter
    private static final CastResult[] cachedValues = values();
    private final String message;

    CastResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return getMessage(null);
    }

    public String getMessage(String detail) {
        if (detail == null) {
            return message;
        } else {
            return message + ": " + detail;
        }
    }

}
