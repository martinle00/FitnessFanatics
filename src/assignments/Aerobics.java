package assignments;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Martin
 */
public class Aerobics {
    private StringProperty type;
    private DoubleProperty distance;
    private DoubleProperty time;
    private DoubleProperty timegoal;
    private DoubleProperty distancegoal;
    
    public Aerobics(String type, double distance, double time, double timegoal, double distancegoal){
        this.type = new SimpleStringProperty(type);
        this.distance = new SimpleDoubleProperty(distance);
        this.time = new SimpleDoubleProperty(time);
        this.timegoal = new SimpleDoubleProperty(timegoal);
        this.distancegoal = new SimpleDoubleProperty(distancegoal);
    }
    
    public String getType(){
        return type.get();
    }
    
    public StringProperty getTypeProperty(){
        return type;
    }
    
    public void setType(String type){
        this.type.set(type);
    }
    public double getDistance(){
        return distance.get();
    }
    public DoubleProperty DistanceProperty(){
        if(distance == null){
            distance = new SimpleDoubleProperty(0);
        }
        return distance;
    }
    public void setDistance(double distance){
        this.distance.set(distance);
    }
    public double getTime(){
        return time.get();
    }
    public DoubleProperty TimeProperty(){
        if(time == null){
            time = new SimpleDoubleProperty(0);
        }
        return time;
    }
    public void setTime(double time){
        this.time.set(time);
    }
    public double getTimeGoal(){
        return timegoal.get();
    }
    public DoubleProperty TimeGoalProperty(){
        if(timegoal == null){
            timegoal = new SimpleDoubleProperty(0);
        }
        return time;
    }
    public void setTimeGoal(double timegoal){
        this.timegoal.set(timegoal);
    }
    public double getDistanceGoal(){
        return distancegoal.get();
    }
    public DoubleProperty DistanceGoalProperty(){
        if(distancegoal == null){
            distancegoal = new SimpleDoubleProperty(0);
        }
        return time;
    }
    public void setDistaneGoal(double distancegoal){
        this.distancegoal.set(distancegoal);
    }
}
