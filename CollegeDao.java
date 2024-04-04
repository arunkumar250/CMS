import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.sql.ResultSet;


//CollegeDao interface has the abstract methods for accessing the data source.
interface CollegeDao {
    int addColleges(int id,String collegeName,String collegeLocation,String collegeType,String createdUser,Timestamp createdTime,String modifiedUser,Timestamp modifiedTime,boolean isAutonomous,String accredationStatus) throws Exception;
	int update(String fieldName,Object changeValue,String collegeName,Timestamp modifiedTime,String updatedUser)throws Exception;
    int delete(int collegID) throws Exception;
    HashMap<Integer,Map<String,Object>> displayColleges() throws Exception;
    HashMap<String, Object> displayColleges(Object fieldName,String field) throws Exception;
}
