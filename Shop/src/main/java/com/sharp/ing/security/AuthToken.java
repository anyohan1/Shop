package com.sharp.ing.security;

public interface AuthToken<T> {
    boolean validate();
    T getData();
}
