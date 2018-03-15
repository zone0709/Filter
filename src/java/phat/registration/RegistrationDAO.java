/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phat.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import phat.utils.DBUtils;

/**
 *
 * @author zone
 */
public class RegistrationDAO implements Serializable{
    public boolean checkLogin(String username , String password)
            throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            if(con != null){
                // Viết câu lệnh truy cập sql
                String sql = "Select * From Registration Where username = ? And password = ?";
                // Tao statement
                stm = con.prepareStatement(sql);
                // truyền tham số vào câu statement
                stm.setString(1,username);
                stm.setString(2,password);
                // thực thi câu lệnh
                rs = stm.executeQuery();
                if(rs.next()){
                    return true;
                }
            } // end of con
        } finally  {
            if(rs != null){
                rs.close();
            }
            if(stm!= null){
                stm.close();
            }
            if(con!= null){
                con.close();
            }
        }
        return false;
    }
    private List<RegistrationDTO> listResult;

    public List<RegistrationDTO> getListResult() {
        return listResult;
    }
    
    public void searchLastname(String searchValue)
          throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            if(con != null){
                // Viết câu lệnh truy cập sql
                String sql = "Select * From Registration Where lastname Like ?";
                // Tao statement
                stm = con.prepareStatement(sql);
                // truyền tham số vào câu statement
                stm.setString(1,"%" + searchValue + "%");
               
                // thực thi câu lệnh
                rs = stm.executeQuery();
                while(rs.next()){
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    RegistrationDTO dto = new RegistrationDTO(username, password, lastname, role);
                    if(this.listResult == null){
                        listResult = new ArrayList<RegistrationDTO>();
                    }
                    listResult.add(dto);
                }
            } // end of con
        } finally  {
            if(rs != null){
                rs.close();
            }
            if(stm!= null){
                stm.close();
            }
            if(con!= null){
                con.close();
            }
        }
    }
    public boolean deletePK(String username)throws SQLException,NamingException{
        Connection con = null;
        PreparedStatement stm = null;
       
        try {
            con = DBUtils.makeConnection();
            if(con != null){
                // Viết câu lệnh truy cập sql
                String sql = "Delete From Registration Where username = ? ";
                // Tao statement
                stm = con.prepareStatement(sql);
                // truyền tham số vào câu statement
                stm.setString(1,username);
                
                // thực thi câu lệnh
                int row = stm.executeUpdate();
                if(row > 0){
                    return true;
                }
            } // end of con
        } finally  {
            
            if(stm!= null){
                stm.close();
            }
            if(con!= null){
                con.close();
            }
        }
        return false;
    }
    public boolean updatePassRole(String username,String password,boolean role)
            throws SQLException,NamingException{
        Connection con = null;
        PreparedStatement stm = null;
       
        try {
            con = DBUtils.makeConnection();
            if(con != null){
                // Viết câu lệnh truy cập sql
                String sql = "Update  Registration Set password=? ,isAdmin=?  Where username = ? ";
                // Tao statement
                stm = con.prepareStatement(sql);
                // truyền tham số vào câu statement
                stm.setString(1,password);
                stm.setBoolean(2,role);
                stm.setString(3,username);
                // thực thi câu lệnh
                int row = stm.executeUpdate();
                if(row > 0){
                    return true;
                }
            } // end of con
        } finally  {
            
            if(stm!= null){
                stm.close();
            }
            if(con!= null){
                con.close();
            }
        }
        return false;
    }
    public boolean createNewAccount(String username,String password , String lastname , boolean role) throws SQLException, NamingException{
          
        Connection con = null;
        PreparedStatement stm = null;
       
        try {
            con = DBUtils.makeConnection();
            if(con != null){
                // Viết câu lệnh truy cập sql
                String sql = "Insert Into Registration(username,password,lastname,isAdmin) "
                        + " Values(?,?,?,?)";
                // Tao statement
                stm = con.prepareStatement(sql);
                // truyền tham số vào câu statement
                stm.setString(1,username);
                stm.setString(2,password);
                stm.setString(3,lastname);
                stm.setBoolean(4,role);
                // thực thi câu lệnh
                int row = stm.executeUpdate();
                if(row > 0){
                    return true;
                }
            } // end of con
        } finally  {
            
            if(stm!= null){
                stm.close();
            }
            if(con!= null){
                con.close();
            }
        }
        return false;
    }
}
