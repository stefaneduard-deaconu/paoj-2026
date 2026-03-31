package com.pao.laboratory06.exercise3;

public interface PlataOnlineSMS extends PlataOnline {
    @Override
    boolean trimiteSMS(String mesaj);
}