import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;


/*CollegeServiceImplemention has the implementation of the CollegeService abstract methods.
CollegeServiceImpl class, we implement the methods defined in the CollegeService interface to interact with college data.
All the operation such as taking data,invoking the correct memthod in the dao functions.
*/
class CollegeServiceImpl implements CollegeService {
    private CollegeDao collegeDAO = new CollegeDaoImpl();
	
	
	//addColleges
	/*
	  - We iterate over each map in the collegeList.
      - Extract properties like id, name, location from each map.
      - Create a timestamp for the createdTime and modifiedTime.
      - Call the addColleges method of CollegeDao to insert the college into the database.
      - Print appropriate messages based on the result of the insertion.
	*/
    public void addColleges(List<Map<String,Object>> collegeList)
    {
		try {
		//HashMap containing properties
		Integer id;
		String collegeName;
		String collegeLocation;
		String collegeType;
		String createdUser;
		Timestamp createdTime;
		String modifiedUser;
		Timestamp modifiedTime;
		Boolean isAutonomous;
		String accredationStatus;
		LocalDateTime currentDateTime = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(currentDateTime);
		
        for (Map<String, Object> college : collegeList) {
			id = Integer.parseInt(String.valueOf(college.get("id")));
			collegeName = String.valueOf(college.get("name"));
			collegeLocation = String.valueOf(college.get("location"));
			collegeType = String.valueOf(college.get("type"));
			createdUser = String.valueOf(college.get("user"));
			createdTime = timestamp;
			modifiedUser = String.valueOf(college.get("user"));
			modifiedTime = timestamp;
			isAutonomous = (Boolean)college.get("isAutonomous");
			accredationStatus = String.valueOf(college.get("accredationStatus"));

            int result = collegeDAO.addColleges(id,collegeName,collegeLocation,collegeType,createdUser,createdTime,modifiedUser,modifiedTime,isAutonomous,accredationStatus);
			if(result>0)
			{
				System.out.println("College Inserted Successfully");
			}
			else 
			{
				System.out.println("Record not inserted");
			}
		}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
    }
	
	
	//displayColleges
	/*
      - Retrieves and displays information about colleges from the database.
      - This method fetches college details using the CollegeDao and prints them.
     */
	public void displayColleges()
	{
		Integer collegeID;
		String collegeName;
		String collegeLocation;
		String collegeType;
		String createdUser;
		Timestamp createdTime;
		String modifiedUser;
		Timestamp modifiedTime;
		Boolean isAutonomous;
		ResultSet resultSet = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yy HH:mm:ss");
		HashMap<Integer,Map<String,Object>> collegeStorage = collegeDAO.displayColleges();
		for (Map.Entry<Integer, Map<String, Object>> entry : collegeStorage.entrySet()) {
				Map<String, Object> college = entry.getValue();
				collegeID = Integer.parseInt(String.valueOf(college.get("id")));
				collegeName = String.valueOf(college.get("name"));
				collegeLocation = String.valueOf(college.get("location"));
				collegeType = String.valueOf(college.get("type"));	
				createdUser = String.valueOf(college.get("createdUser"));
				createdTime = Timestamp.valueOf(String.valueOf(college.get("createdDatetime")));
				String formattedCreatedTime = createdTime.toLocalDateTime().format(formatter);
				modifiedUser = String.valueOf(college.get("modifiedUser"));	
				modifiedTime = Timestamp.valueOf(String.valueOf(college.get("modifiedDatetime")));
				String formattedModifiedTime = modifiedTime.toLocalDateTime().format(formatter);
				isAutonomous = Boolean.valueOf(String.valueOf(college.get("isAutonomous")));


				System.out.println("College ID       : " + collegeID);
				System.out.println("College Name	 : " + collegeName);
				System.out.println("College Location : " + collegeLocation);
				System.out.println("College Type	 : " + collegeType);
				System.out.println("Created User 	 : " + createdUser);
				System.out.println("Created Time     : " + formattedCreatedTime);
				System.out.println("Modified User	 : " + modifiedUser);
				System.out.println("Modified Time	 : " + formattedModifiedTime);
				System.out.println("Autonomous		 : " + isAutonomous);
				System.out.println("Accreditation    : " + isAutonomous);
				System.out.println("---------------------------");	
				}
	}
	
	
	
	//method overloading
	/*
	* Overloaded method to display information about colleges based on the provided field value.
	* If the fieldValue is an Integer, it retrieves college details based on college_id.
	* If the fieldValue is a String, it retrieves college details based on college_name.
	* fieldValue The value of the field to search for colleges (can be college_id or college_name).
	*/
    public void displayColleges(Object fieldValue)
	{
		Integer collegeID;
		String collegeName;
		String collegeLocation;
		String collegeType;
		String createdUser;
		Timestamp createdTime;
		String modifiedUser;
		Timestamp modifiedTime;
		Boolean isAutonomous;
		String field = "";
		String accreditationStatus = "";
		ResultSet resultSet = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yy HH:mm:ss");
		if(fieldValue instanceof Integer) field = "college_id";
		else field  = "college_name";
		HashMap<String,Object> college = collegeDAO.displayColleges(fieldValue,field);
		
		collegeName = String.valueOf(college.get("name"));
		collegeLocation = String.valueOf(college.get("location"));
		collegeType = String.valueOf(college.get("type"));		
		createdUser = String.valueOf(college.get("createdUser"));
		createdTime = Timestamp.valueOf(String.valueOf(college.get("createdDatetime")));
		String formattedCreatedTime = createdTime.toLocalDateTime().format(formatter);
		modifiedUser = String.valueOf(college.get("modifiedUser"));		
		modifiedTime = Timestamp.valueOf(String.valueOf(college.get("modifiedDatetime")));
		String formattedModifiedTime = modifiedTime.toLocalDateTime().format(formatter);
		isAutonomous = Boolean.valueOf(String.valueOf(college.get("isAutonomous")));
		accreditationStatus = String.valueOf(college.get("accreditationStatus"));
		
		System.out.println("College ID	 : " + college.get("id"));
		System.out.println("College Name	 : " + collegeName);
		System.out.println("College Location : " + collegeLocation);
		System.out.println("College Type	 : " + collegeType);
		System.out.println("Created User	 : " + createdUser);
		System.out.println("Created Time 	 : " + formattedCreatedTime);
		System.out.println("Modified User	 : " + modifiedUser);
		System.out.println("Modified Time	 : " + formattedModifiedTime);
		System.out.println("Autonomous	 : " + isAutonomous);
		System.out.println("Accreditation    : " + accreditationStatus);
		System.out.println("---------------------------");	
}
	
	
    //update method 
	/*
	* Updates the specified college record with the provided changes.
	* changeValues A HashMap containing the column names as keys and the new values as values.
	* collegeName The name of the college to be updated.
	* updatedUser The user performing the update operation.
	*/
	public void update(HashMap<String,Object> changeValues,String collegeName,String updatedUser) 
	{
      try { 		
	    LocalDateTime currentDateTime = LocalDateTime.now();
		Timestamp modifiedTime = Timestamp.valueOf(currentDateTime);
	    int result = 0;
		for(Map.Entry<String,Object> changeValue : changeValues.entrySet())
		{ 
			if(changeValue.getKey().equals("CollegeName")) {
				result = collegeDAO.update("college_name",changeValue.getValue(),collegeName,modifiedTime,updatedUser);
			} else if(changeValue.getKey().equals("CollegeType")) {
				result = collegeDAO.update("college_type",changeValue.getValue(),collegeName,modifiedTime,updatedUser);
			} else if(changeValue.getKey().equals("CollegeLocation")) {
				result = collegeDAO.update("college_location",changeValue.getValue(),collegeName,modifiedTime,updatedUser);
			}
		}
		if(result>0)
		{
			System.out.println("Records Updated Successfully");
		}
		else 
		{
		    System.out.println("Record Not Updated!!");
		}
	  }
	  catch(Exception e)
	  {
		 System.out.println(e.getMessage());
	  }
	}
	
	
	//delete method
	/*
	* Deletes a college record from the database based on the provided college ID.
	* collegeID The ID of the college to be deleted.
    */
	public 	void delete(int collegeID)
	{
		int result = collegeDAO.delete(collegeID);
		if(result>0)
		{
			System.out.println("Record Deleted Successfully");
		}
		else 
		{
		    System.out.println("Record Not Deleted");
		}
	}
}
