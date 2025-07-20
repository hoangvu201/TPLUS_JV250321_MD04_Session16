package business;

import entity.TransferFunds;
import util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;

public class TransferFundsBussiness {
    public boolean  transferFunds(int senderId, int receiverId, int amount) {
        Connection conn = null;
        CallableStatement pstmt = null;
        try {
            conn = ConnectionDB.openConnection();
            pstmt = conn.prepareCall("{call transfer_funds(?,?,?)}");
            pstmt.setInt(1, senderId);
            pstmt.setInt(2, receiverId);
            pstmt.setInt(3, amount);
            pstmt.executeUpdate();
            System.out.println("Chuyển tiền thành công");
            return true;
        }catch (Exception e){
            System.err.println("Lỗi khi chuyển tiền: " + e.getMessage());
            return false;
        }
        finally {
            ConnectionDB.closeConnection(conn,pstmt);
        }
    }
}
