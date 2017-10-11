package com.example.home.projectalarmrpi;

/**
 * Created by home on 11/10/16.
 */

public class AlarmORM {

    //DAO variables
    private long _id;
    private String _alarm_name;
    private int _hour;
    private int _min;

    public AlarmORM(){

    }


    //setter methods
    public void set_id(long _id){

        this._id = _id;
    }
    public void set_min(int _min){

        this._min = _min;
    }
    public void set_hour(int _hour){
        this._hour = _hour;
    }
    public void set_alarm_name(String _alarm_name){
        this._alarm_name = _alarm_name;
    }


    //getter methods
    public long get_id(){
        return this._id;
    }
    public int get_min(){
        return this._min;
    }
    public int get_hour(){
        return this._hour;
    }
    public String get_alarm_name(){
        return this._alarm_name;
    }


}
