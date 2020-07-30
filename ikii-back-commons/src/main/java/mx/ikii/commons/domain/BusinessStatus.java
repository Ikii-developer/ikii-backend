package mx.ikii.commons.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BusinessStatus {
    ACTIVE("ACTIVE"),
    FOR_APPROVAL("FOR_APPROVAL"),
    REPORTED("REPORTED"),
    CLOSED("CLOSED");

    private final String name;
}
