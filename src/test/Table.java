package test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class Table extends JTable{
    
    public Table(){
        super();
        
        DefaultTableModel tableModel = (DefaultTableModel) this.getModel();
            // tableModel.addRow(tableContents);
            String url="jdbc:mysql://dusk.mysql.database.azure.com:3306/try?useSSL=true";
            
            try (Connection con = DriverManager.getConnection(url, "Arceus", "m67Ds#rAm6")) {
                
                // PreparedStatement pst = con.prepareStatement(sql);
                Statement st = con.createStatement();
                String sql = "Select * from test";

                ResultSet rs = st.executeQuery(sql);
                ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();

                //int columnsNumber = rsmd.getColumnCount();
                String[] rowNames = new String[rsmd.getColumnCount()];

                for (int i = 0; i < rowNames.length; i++) {
                    rowNames[i] =  rsmd.getColumnName(i + 1);
                }

                tableModel.setColumnIdentifiers(rowNames);
                while (rs.next()) {
                    String name = rs.getString("name");
                    String age = rs.getString("age");
                    String course = rs.getString("course");
                    tableModel.addRow(new Object[] { name, age, course });
                }
                    
                JOptionPane.showMessageDialog(null, "Success");
                st.close();
                con.close();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage());

            }
    }
    
    public static void main(String[] args) {
        
    }
}
