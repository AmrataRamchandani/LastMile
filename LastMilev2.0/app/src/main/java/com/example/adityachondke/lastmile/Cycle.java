package com.example.adityachondke.lastmile;

public class Cycle  {

    double cycleid;
    double latitude;
    double longitude;
    int cstatus;


    public Cycle(){

    }

    public Cycle(double cycleid,double latitude,double longitude)
     {
        this.cycleid = cycleid;
        this.latitude = latitude;
        this.longitude = longitude;
        this.cstatus=cstatus;
    }

    public double getCycleid() {
       return cycleid;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
       return longitude;
    }

    public int getCstatus(){ return  cstatus;}

    public void setCycleid(double cycleid){this.cycleid=cycleid;}

    public void setLatitude(double latitude){this.latitude=latitude;}
   public void setLongitude(double longitude){this.longitude=longitude;}
   public void setCstatus(int cstatus){this.cstatus=cstatus;}

}
