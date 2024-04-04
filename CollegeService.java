import java.util.HashMap;
import java.util.Map;
import java.util.List;

//College interface has the abstract methods such as CollegeService
interface CollegeService {
	void addColleges(List<Map<String,Object>> collegeList);
	void update(HashMap<String,Object> changeValues,String collegeName,String updatedUser);
	void delete(int collegeID);
	void displayColleges();
	void displayColleges(Object fieldValue);
}
