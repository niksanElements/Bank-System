package database.databaseController;

import dataModel.CurrencyConverter;
import dataModel.models.Currency;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Nikolay on 1/2/2017.
 */
public class DatabaseCurrensyController{
    private Connection connDatabase;

    private PreparedStatement getAll;

    public DatabaseCurrensyController(Connection connDatabase){
        this.connDatabase = connDatabase;
        try{
            this.getAll = this.connDatabase.prepareStatement("SELECT * FROM currencies");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public CurrencyConverter getConverter(){
        ResultSet set = null;
        CurrencyConverter converter = new CurrencyConverter();
        try {
            set = this.getAll.executeQuery();
            while(set.next()){
                String id = set.getString("id");
                BigDecimal value = set.getBigDecimal("value");
                converter.setCurrencyValue(new Currency(id),value);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                if(set != null) {
                    set.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return converter;
    }

    public void close(){
        try {
            this.getAll.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
