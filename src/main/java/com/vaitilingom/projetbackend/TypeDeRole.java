package com.vaitilingom.projetbackend;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TypeDeRole {
    USER,
    ADMINISTRATEUR;

    @JsonCreator
    public static TypeDeRole fromString(String value) {
        return TypeDeRole.valueOf(value.toUpperCase());
    }

    @JsonValue
    public String toString() {
        return this.name();
    }
}
