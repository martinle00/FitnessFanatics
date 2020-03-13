/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignments;

/**
 *
 * @author Martin
 */
public class Medical {
    private String Date;
    private String Type;
    
    public Medical(String Date, String Type){
        this.Date = Date;
        this.Type = Type;
    }
    public String getDate(){
        return Date;
    }
    public void setDate(String Date){
        this.Date = Date;
    }
    public String getType(){
        return Type;
    }
    public void setType(String Type){
        this.Type = Type;
    }
}
