import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.sql.Statement;

/*
CollgeDaoImplementation implement the methods that is declared in the CollegeDao interface.
The methods implemented in this class are as follows:
addColleges()->reate the insert query and insert the new college into the database.
displaycolleges()->retrive the college table from the database and return to the service implementation.
update()->it modify the existing details in the database.
delete()->remove the record from the table.
*/

class CollegeDaoImpl implements CollegeDao {

    public int addColleges(int id,String collegeName,String collegeLocation,String collegeType,String createdUser,Timestamp createdTime,String modifiedUser,Timestamp modifiedTime,boolean isAutonomous,String accredationStatus) throws Exception {

        Connection connection = DBConnection.getConnection();	
	    int result = 0;
        try 
		{
                Statement statement = connection.createStatement();
                String insertQuery = "insert into College values(" + id + ", '" + collegeName + "', '" + collegeLocation+ "', '" + collegeType + "', '" + createdUser + "', '" + createdTime + "', '" + modifiedUser+ "', '" + modifiedTime + "'," + isAutonomous + ",'"+accredationStatus+"');";
                result = statement.executeUpdate(insertQuery);
        } 
        catch (Exception e) 
		{
             throw new Exception("Error while adding colleges", e);
        }
		finally
		{
			try
			{
				connection.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return result;
    }
	
	
	//displayColleges
	public HashMap<Integer,Map<String,Object>> displayColleges() throws Exception {
		Integer collegeID;
		String collegeName;
		String collegeLocation;
		String collegeType;
		String createdUser;
		Timestamp createdTime;
		String modifiedUser;
		Timestamp modifiedTime;
		Boolean isAutonomous;
		String accredidationStatus = "";
		Statement statement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yy HH:mm:ss");
		HashMap<Integer,Map<String,Object>> collegeStorage = new  HashMap<Integer,Map<String,Object>>();
		try
		{
		    connection = DBConnection.getConnection();
			statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM College");
		    while(resultSet.next()){
				Map<String,Object> college = new HashMap<String,Object>();
				collegeID = resultSet.getInt("college_id");
				collegeName = resultSet.getString("college_name");
				collegeLocation = resultSet.getString("college_location");
				collegeType = resultSet.getString("college_type");
				createdUser = resultSet.getString("created_user");
				createdTime = resultSet.getTimestamp("created_time");
				modifiedUser = resultSet.getString("modified_user");
				modifiedTime = resultSet.getTimestamp("modified_time");
				isAutonomous = resultSet.getBoolean("isAutonomous");
				college.put("id",collegeID);
				college.put("name",collegeName);
				college.put("type",collegeType);
				college.put("location",collegeLocation);
				college.put("createdUser",createdUser);
				college.put("createdDatetime",createdTime);
				college.put("modifiedUser",modifiedUser);
				college.put("modifiedDatetime",modifiedTime);
				college.put("isAutonomous",isAutonomous);
				college.put("accreditationStatus",accredidationStatus);
				collegeStorage.put((Integer)college.get("id"),college);
			}
		}
		catch(Exception exception)
		{
			throw new Exception("Error while adding colleges", exception);
		}
		finally
		{
			try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return collegeStorage;
    }
	
	
//method overloading
public HashMap<String, Object> displayColleges(Object fieldValue,String field) throws Exception {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    HashMap<String, Object> college = new HashMap<>();
    Integer collegeID;
    String collegeName;
    String collegeLocation;
    String collegeType;
    String createdUser;
    Timestamp createdTime;
    String modifiedUser;
    Timestamp modifiedTime;
    Boolean isAutonomous;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yy HH:mm:ss");

    try {
        connection = DBConnection.getConnection();
        statement = connection.createStatement();
        String displayQuery = "";
        if (field.equals("college_id")) {
            displayQuery = "select * from College where college_id = " + fieldValue;
        } else {
            displayQuery = "select * from College where college_name = '" + fieldValue + "'";
        }
        resultSet = statement.executeQuery(displayQuery);
        if (resultSet.next()) {
            collegeID = resultSet.getInt("college_id");
            collegeName = resultSet.getString("college_name");
            collegeLocation = resultSet.getString("college_location");
            collegeType = resultSet.getString("college_type");
            createdUser = resultSet.getString("created_user");
            createdTime = resultSet.getTimestamp("created_time");
            modifiedUser = resultSet.getString("modified_user");
            modifiedTime = resultSet.getTimestamp("modified_time");
            isAutonomous = resultSet.getBoolean("isAutonomous");

            college.put("id", collegeID);
            college.put("name", collegeName);
            college.put("type", collegeType);
            college.put("location", collegeLocation);
            college.put("createdUser", createdUser);
            college.put("createdDatetime", createdTime);
			college.put("modifiedUser", modifiedUser);
            college.put("modifiedDatetime", modifiedTime);
            college.put("isAutonomous", isAutonomous);
        }
    } catch (Exception exception) {
        throw new Exception("Error displaying adding colleges", exception);
    } finally {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return college;
}


	
	public int update(String updatingColumn,Object newValue,String collegeName,Timestamp modifiedTime,String updatedUser) throws Exception {
		Connection connection = null;
		Statement statement = null;
		int result = 0;

		try {
			connection = DBConnection.getConnection();
			statement = connection.createStatement();
			String sql = "update College set " + updatingColumn + " = '" + newValue + "', modified_time = '" + modifiedTime + "', modified_user = '"+updatedUser+"' where college_name = '" + collegeName+"'";
			result = statement.executeUpdate(sql);
		} 
		catch (Exception exception) {
			throw new Exception("Error updating adding colleges", exception);
		} finally {
			try {
				if (statement != null) statement.close();
				if (connection != null) connection.close();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	//delete method
    public int delete(int collegeID) {
        Connection connection = null;
        Statement statement = null;
        int result = 0;
    
        try {
            connection = DBConnection.getConnection();
            statement = connection.createStatement();
            String sql = "delete from College where college_id = " + collegeID+";";
            result = statement.executeUpdate(sql);
    
        } 
        catch (Exception e) {
            throw new Exception("Error deleting adding colleges", e);
        } 
        finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 
        return result;
    }
}
