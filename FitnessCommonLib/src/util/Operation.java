/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.Serializable;

/**
 *
 * @author Marija
 */
public class Operation implements Serializable{
    private static final long serialVersionUID=3;
    
    public static final int OPERATION_LOGIN=1;
    public static final int OPERATION_SAVE_KORISNIK=2;
    public static final int OPERATION_DELETE_KORISNIK=3;
    public static final int OPERATION_SEARCH_KORISNIK=4;
    public static final int OPERATION_UPDATE_KORISNIK=5;
    public static final int OPERATION_GET_KORISNIK=6;
    public static final int OPERATION_SEARCH_VEZBA=7;
    public static final int OPERATION_SAVE_VEZBA=8;
    public static final int OPERATION_GET_VEZBA=9;
    public static final int OPERATION_UPDATE_VEZBA=10;
    public static final int OPERATION_DELETE_VEZBA=11;
    public static final int OPERATION_SEARCH_PLAN_VEZBI=12;
    public static final int OPERATION_GET_PLAN_VEZBI=13;
    public static final int OPERATION_DELETE_PLAN_VEZBI=14;
    public static final int OPERATION_UPDATE_PLAN_VEZBI=15;
    public static final int OPERATION_SAVE_PLAN_VEZBI=16;
}
