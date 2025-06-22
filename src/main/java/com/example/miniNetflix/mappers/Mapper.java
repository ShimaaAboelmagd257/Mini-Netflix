package com.example.miniNetflix.mappers;

public interface Mapper <A,B> {
    B toDto(A a);
    A fromDto(B b);
}
