package dao;

import context.DBUtils;
import entity.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KhoaHD7621
 */
public class AccountDAO {

    private static final String GET_AN_ACCOUNT = "SELECT UserID, username, Password, name, Phone, email, Role,address FROM Accounts WHERE Email = ? AND Password = ?;";
    private static final String GET_AN_ACCOUNT1 = "SELECT UserID, username, Password, name, Phone, email,photo,status, Role,address FROM Accounts WHERE username = ? AND Password = ?;";
    private static final String GET_USER_ID = "SELECT UserID FROM Accounts WHERE username = ?;";
    private static final String INSERT_ACCOUNT = "INSERT INTO Accounts (email, password, username, phone, status, role,photo) VALUES (?, ?, ?, ?, ?, ?,?)";
    private static final String GET_ACCOUNT_INFO_BY_EMAIL = "SELECT UserID, Email,photo,name, Password, username, Phone, Status, Role,address FROM Accounts WHERE Email = ?";

    private static final String GET_AN_ACCOUNT_BY_TOKEN = "SELECT UserID, Email, Password, name, Phone, Status, Role,address FROM Accounts WHERE token = ?";
    private static final String GET_ACC = "SELECT UserID,username ,Email, Password, name, Phone, photo,Status, Role,address FROM Accounts WHERE username = ?";
    private static final String UPDATE_TOKEN = "UPDATE Accounts Set token = ? WHERE email = ?";
    private static final String VALID_TOKEN = "SELECT UserID, Email, Password, name, Phone, Status, Role FROM Accounts WHERE token = ?";
    private static final String VALID_ACCOUNT_USERNAME = "select * from Accounts where username = ?  ";
    private static final String GET_ROLE_ACCOUNT_BY_TOKEN = "SELECT role FROM Accounts WHERE token = ?";

    private static final String UPDATE_PASSWORD = "UPDATE Accounts Set password = ? WHERE UserID = ?";
    private static final String UPDATE_ACC = "UPDATE Accounts " +
                  "SET " +
                  "[password] = ?, " +
                  "[name] = ?, " +
                  "[email] = ?, " +
                
                  "[phone] = ?, " +
                  "[address] = ? " +
                  "WHERE USERID = ?;";

    public boolean updateACCOUNT(int accId, String newPassword, String phone, String Address, String Fullname, String email) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement psm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                psm = conn.prepareStatement(UPDATE_ACC);
                psm.setString(1, newPassword);
                psm.setString(2, Fullname);
                psm.setString(3, email);
      
                psm.setString(4, phone);
                psm.setString(5, Address);
                psm.setInt(6, accId);
                check = psm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (psm != null) {
                try {
                    psm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return check;
    }
    public boolean updateAccountPassword(int accId, String newPassword) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement psm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                psm = conn.prepareStatement(UPDATE_PASSWORD);
                psm.setString(1, newPassword);
                psm.setInt(2, accId);
                check = psm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (psm != null) {
                try {
                    psm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return check;
    }

    public int getRoleAccountByToken(String token) throws SQLException {
        int role = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_ROLE_ACCOUNT_BY_TOKEN);
                stm.setString(1, token);
                rs = stm.executeQuery();
                if (rs.next()) {
                    role = rs.getInt("role");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return role;
    }

    public boolean validToken(String token) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                psm = conn.prepareStatement(VALID_TOKEN);
                psm.setString(1, token);
                rs = psm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (psm != null) {
                psm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean updateToken(String token, String email) throws SQLException {
        boolean check = true;
        Connection conn = null;
        PreparedStatement psm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                psm = conn.prepareStatement(UPDATE_TOKEN);
                psm.setString(1, token);
                psm.setString(2, email);
                check = psm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (psm != null) {
                psm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean getAccountByEmail(String email) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_ACCOUNT_INFO_BY_EMAIL);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public Account getAccount1(String user, String password) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Account acc = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_AN_ACCOUNT1);
                stm.setString(1, user);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int AccId = rs.getInt("UserID");
                    String Email = rs.getString("Email");
                    String Password = rs.getString("Password");
                    String photo = rs.getString("photo");
                    String username = rs.getString("username");
                    String FullName = rs.getString("name");
                    String Phone = rs.getString("Phone");
                    int Status = rs.getInt("Status");
                    int Role = rs.getInt("Role");
                      String add = rs.getString("address");
                    acc = new Account(AccId, Email, photo, username, Password, FullName, Status, Phone, Role,add);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return acc;
    }

    public Account getAccount(String email, String password) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Account acc = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_AN_ACCOUNT);
                stm.setString(1, email);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int AccId = rs.getInt("UserID");
                    String Email = rs.getString("Email");
                    String Password = rs.getString("Password");
                    String photo = rs.getString("photo");
                    String username = rs.getString("username");
                    String FullName = rs.getString("name");
                    String Phone = rs.getString("Phone");
                    int Status = rs.getInt("Status");
                    int Role = rs.getInt("Role");
                      String add = rs.getString("address");
                    acc = new Account(AccId, Email, photo, username, Password, FullName, Status, Phone, Role,add);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return acc;
    }

    public Account getAccount(String token) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Account acc = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_AN_ACCOUNT_BY_TOKEN);
                stm.setString(1, token);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int AccId = rs.getInt("UserID");
                    String Email = rs.getString("Email");
                    String Password = rs.getString("Password");
                    String photo = rs.getString("photo");
                    String username = rs.getString("username");
                    String FullName = rs.getString("name");
                    String Phone = rs.getString("Phone");
                    int Status = rs.getInt("Status");
                    int Role = rs.getInt("Role");
                      String add = rs.getString("address");
                    acc = new Account(AccId, Email, photo, username, Password, FullName, Status, Phone, Role,add);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return acc;
    }

    public Account getAccount_BYUSER(String name) {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Account acc = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_ACC);
                stm.setString(1, name);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int AccId = rs.getInt("UserID");
                    String Email = rs.getString("Email");
                    String Password = rs.getString("Password");
                    String photo = rs.getString("photo");
                    String username = rs.getString("username");
                    String FullName = rs.getString("name");
                    String Phone = rs.getString("Phone");
                    int Status = rs.getInt("Status");
                    int Role = rs.getInt("Role");
                    String add = rs.getString("address");
                   
                    acc = new Account(AccId, Email, photo, username, Password, FullName, Status, Phone, Role,add);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return acc;
    }

    public Account getAccountInfoByEmail(String email) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Account acc = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_ACCOUNT_INFO_BY_EMAIL);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int AccId = rs.getInt("UserID");
                    String Email = rs.getString("Email");
                    String Password = rs.getString("Password");
                    String photo = rs.getString("photo");
                    String username = rs.getString("username");
                    String FullName = rs.getString("name");
                    String Phone = rs.getString("Phone");
                    int Status = rs.getInt("Status");
                    int Role = rs.getInt("Role");
                      String add = rs.getString("address");
                    acc = new Account(AccId, Email, photo, username, Password, FullName, Status, Phone, Role,add);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return acc;
    }

    public boolean insertAccount(String newEmail,String newPassword, String username, String newPhone, int newStatus, int newRole,String photo) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(INSERT_ACCOUNT);
                stm.setString(1, newEmail);
                stm.setString(2, newPassword);
                stm.setString(3, username);
                stm.setString(4, newPhone);
                stm.setInt(5, newStatus);
                stm.setInt(6, newRole);
                stm.setString(7, photo);
                check = stm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return check;
    }

    public boolean checkEmail(String email) {

        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String query = "select * from Accounts \n"
                    + "where Email = ?";
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
        }

        return false;
    }

    public boolean checkAccountExits(String user) {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String query = VALID_ACCOUNT_USERNAME;
            conn = new DBUtils().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public int GetUSERID(String username) {
        int id = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_USER_ID);
                stm.setString(1, username);
                rs = stm.executeQuery();
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return id;
    }

    public static void main(String[] args) throws SQLException {
        AccountDAO dao = new AccountDAO();

        System.out.println("" + dao.getAccount_BYUSER("PASS"));

       

    }

}
