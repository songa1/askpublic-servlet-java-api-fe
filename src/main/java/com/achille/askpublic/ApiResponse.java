/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.achille.askpublic;

/**
 *
 * @author achille
 */
public class ApiResponse<T> {
    private int code;
    private String msg;
    private T payload;
    public ApiResponse(int code, String msg, T payload) {
        this.code = code;
        this.msg = msg;
        this.payload = payload;
    }   
}
