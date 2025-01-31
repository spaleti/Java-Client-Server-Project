/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @Korisnik
 */
public abstract class AbstractDomainObject implements Serializable {

    public abstract String tableName(); 
    public abstract String alias(); 
    public abstract String join(); 
    public abstract ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException; 
    public abstract String InsertColumns(); 
    public abstract String condition(); 
    public abstract String inputForInsert(); 
    public abstract String inputForUpdate(); 
    public abstract String selectCondition(); 
    
}
