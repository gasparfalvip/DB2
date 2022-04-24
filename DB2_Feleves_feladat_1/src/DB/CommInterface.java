package DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import GUI.FilmTableModel;
import GUI.StoreTableModel;

public class CommInterface {
	
	private static Statement s = null;
	private static ResultSet rs = null;
	
	public static int Auth(String username, String password) {
		int pc = -1;
		String sqlp = "select count(*) as pc from Users where Username='"+username+"' and Password='"+password+"';";
		try {
			s = ConnectionManager.conn.createStatement();
			rs = s.executeQuery(sqlp);
			while(rs.next()) {
				pc = rs.getInt("pc");
			}
			rs.close();
		} catch (SQLException e) {
			Util.Alert(e.getMessage());
		}
		return pc;
	}
	
	public static StoreTableModel getAllStoreData() {
		Object fieldNames[]= {"ID", "Nev", "Nyitas", "Alkalmazottak"};
		StoreTableModel storemodel = new StoreTableModel(fieldNames, 0);
		
		String nev="", nyitas="";
		int id = 0, alkalmazottak=0; 
		
		String sqlp = "select ID ,Nev ,Nyitas ,Alkalmazottak from Kolcsonzok";
		
		try {
			s = ConnectionManager.conn.createStatement();
			rs = s.executeQuery(sqlp);
			while(rs.next()) {
				id=rs.getInt("ID");
				nev=rs.getString("Nev");
				nyitas=rs.getString("Nyitas");
				alkalmazottak=rs.getInt("Alkalmazottak");
				storemodel.addRow(new Object[] {id,nev,nyitas,alkalmazottak,});
			}
			rs.close();
		} catch (SQLException e) {
			Util.Alert(e.getMessage());
		}
		
		return storemodel;
	}
	
	public static FilmTableModel getAllFilmData() {
		Object fieldNames[]= {"ID", "Filmnev", "Kiadasdatuma", "Ar", "TK_ID"};
		FilmTableModel filmmodel = new FilmTableModel(fieldNames, 0);
		
		String filmnev="", kiadasdatuma="";
		int id = 0, ar=0, tk_id=0; 
		
		String sqlp = "select ID ,Filmnev ,Kiadasdatuma ,Ar , TK_ID from Filmek";
		
		try {
			s = ConnectionManager.conn.createStatement();
			rs = s.executeQuery(sqlp);
			while(rs.next()) {
				id=rs.getInt("ID");
				filmnev=rs.getString("Filmnev");
				kiadasdatuma=rs.getString("Kiadasdatuma");
				ar=rs.getInt("Ar");
				tk_id=rs.getInt("TK_ID");
				filmmodel.addRow(new Object[] {id,filmnev,kiadasdatuma,ar,tk_id});
			}
			rs.close();
		} catch (SQLException e) {
			Util.Alert(e.getMessage());
		}
		
		return filmmodel;
	}

	public static String[] getStoreNames() {
		List<String> stores = new ArrayList<String>();
		String sqlp = "select Nev from Kolcsonzok";
		try {
			s = ConnectionManager.conn.createStatement();
			rs = s.executeQuery(sqlp);
			while(rs.next()) {
				stores.add(rs.getString("Nev"));
			}
			rs.close();
		} catch (SQLException e) {
			Util.Alert(e.getMessage());
		}
		return stores.toArray(new String[0]);
	}
	
	public static String[] getFilmNames() {
		List<String> films = new ArrayList<String>();
		String sqlp = "select Filmnev from Filmek";
		try {
			s = ConnectionManager.conn.createStatement();
			rs = s.executeQuery(sqlp);
			while(rs.next()) {
				films.add(rs.getString("Filmnev"));
			}
			rs.close();
		} catch (SQLException e) {
			Util.Alert(e.getMessage());
		}
		return films.toArray(new String[0]);
	}
	
	public static int getIDFromFilmName(String name) {
		int key = -1;
		String sqlp = "select ID from Filmek where Filmnev='"+name+"'";
		try {
			s = ConnectionManager.conn.createStatement();
			rs = s.executeQuery(sqlp);
			while(rs.next()) {
				key = rs.getInt("ID");
			}
			rs.close();
		} catch (SQLException e) {
			Util.Alert(e.getMessage());
		}
		return key;
	}
	
	public static int getIDFromStoreName(String name) {
		int key = -1;
		String sqlp = "select ID from Kolcsonzok where Nev='"+name+"'";
		try {
			s = ConnectionManager.conn.createStatement();
			rs = s.executeQuery(sqlp);
			while(rs.next()) {
				key = rs.getInt("ID");
			}
			rs.close();
		} catch (SQLException e) {
			Util.Alert(e.getMessage());
		}
		return key;
	}

	public static void InsertFilm(String moviename, String releaseDate, String price, Object selectedItem) {
		System.out.println(selectedItem.toString());
		int fkey = getIDFromStoreName(selectedItem.toString());
		String sqlp="insert into Filmek (Filmnev,Kiadasdatuma,Ar,TK_ID) values"
				+ "('"+moviename+"', '"+releaseDate+"', '"+price+"', '"+fkey+"')";
		try {
			s= ConnectionManager.conn.createStatement();
			s.execute(sqlp);
		}catch(SQLException e) {
			Util.Alert(e.getMessage());
		}
		
	}
	
	public static void InsertStore(String name, String openingdate, String workers) {
		String sqlp="insert into Kolcsonzok (Nev,Nyitas,Alkalmazottak)"+ " values(?,?,?);";
		PreparedStatement ps;
		try {
			ps = ConnectionManager.conn.prepareStatement(sqlp);
			ps.setString(1, name);
			ps.setString(2, openingdate);
			ps.setString(3, workers);
			ps.execute();
		}catch(SQLException e) {
			Util.Alert(e.getMessage());
		}
		
	}
	
	public static void DeleteFilm(int kod) { 
        String sqlp = "delete from Filmek where ID like '" + kod + "'";
       
            try { 
            	s = ConnectionManager.conn.createStatement();
            	s.executeUpdate(sqlp);
            	s.close();
            } catch (Exception e){	
            	Util.Alert(e.getMessage()); 
            }
        
    }
	
	public static void DeleteStore(int kod) { 
		String sqlp1 = "delete from Filmek Where TK_ID like '"+kod+"';";
		try { 
        	s = ConnectionManager.conn.createStatement();
        	s.executeUpdate(sqlp1);
        	s.close();
        } catch (Exception e){	
        	Util.Alert(e.getMessage()); 
        }
		String sqlp = "delete from Kolcsonzok where ID like '" + kod + "'";
       
            try { 
            	s = ConnectionManager.conn.createStatement();
            	s.executeUpdate(sqlp);
            	s.close();
            } catch (Exception e){	
            	Util.Alert(e.getMessage()); 
            }
        
    }
	
	public static void ModifyFilm(int ID, String moviename, String releaseDate, String price, Object selectedItem) {
		System.out.println(selectedItem.toString());
		int fkey = getIDFromStoreName(selectedItem.toString());
		String sqlp="update Filmek set Filmnev = '"+ moviename +"', Kiadasdatuma = '"+releaseDate+"', Ar = '"+price+"', TK_ID = '"+fkey+"' where ID = '"+ID+"' ;";
		try {
			s= ConnectionManager.conn.createStatement();
			s.execute(sqlp);
		}catch(SQLException e) {
			Util.Alert(e.getMessage());
		}
	}
	
	public static void ModifyStore(int ID, String name, String openingdate, String workers) {
		String sqlp="update Kolcsonzok set Nev = '"+ name +"', Nyitas = '"+openingdate+"', Alkalmazottak = '"+workers+"' where ID = '"+ID+"' ;";
		try {
			s= ConnectionManager.conn.createStatement();
			s.execute(sqlp);
		}catch(SQLException e) {
			Util.Alert(e.getMessage());
		}
	}
	
	public static String[] getLowPriceFilms(String ar) {
		List<String> films = new ArrayList<String>();
		String sqlp = "select Filmnev from Filmek where Ar < "+ ar + ";";
		try {
			s = ConnectionManager.conn.createStatement();
			rs = s.executeQuery(sqlp);
			while(rs.next()) {
				films.add(rs.getString("Filmnev"));
			}
			rs.close();
		} catch (SQLException e) {
			Util.Alert(e.getMessage());
		}
		return films.toArray(new String[0]);
	}
	
	
	
	
	
	
	
}
