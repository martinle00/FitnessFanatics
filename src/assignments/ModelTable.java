/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignments;

/**
 *
 * @author galstern
 */
public class ModelTable {
    String date, type;
    
    public ModelTable(String date, String type) {
        this.date = date;
        this.type = type;
    }

    ModelTable(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String getFutureDate() {
        return date;
    }
    
    public void setFutureDate(String date) {
        this.date = date;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
}
