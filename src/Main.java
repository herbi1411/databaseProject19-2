import java.sql.Connection;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn;
		DBAccess da = new DBAccess();
		while(!da.isExit())System.out.print("");
		conn = da.getConn();
		new SelectView(conn);
	}

}
