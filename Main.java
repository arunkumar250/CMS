import java.sql.SQLException;
import java.util.Scanner;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;


/*College Management System.
Main class is get input from the user and add it into the database through DAO Design Pattern.
It display the menu option to the user such as create,update,display,delete option for the college management system.
The main method only make the request to the system and get inputs from the user.
*/
class Main {
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
		List<Map<String,Object>> collegesList = new ArrayList<>();
        System.out.println("College Management System");
        CollegeService collegService = new CollegeServiceImpl();
        String currentUser = "arun";
		
    try 
	{
		int option = 0;
		while(true)
		{
		System.out.println("Select the option");
		System.out.println("1.Create \n2.Display \n3.Update \n4.Delete \n5.Exit");
		option = input.nextInt();
		if(option==1)
		{
			while (true) 
			{
				System.out.println("Enter College name :");
				String collegeName = input.next();
				System.out.println("Enter College id : ");
				int collegeID = input.nextInt();;
				System.out.println("Enter College Type : ");
				String collegType = input.next();
				System.out.println("Enter College Location : ");
				String collegeLocation = input.next();
				System.out.println("College is Autonomous :");
				char choice = input.next().charAt(0);
				boolean isAutonomous;
				if(option=='y' || option=='Y')
				{
					isAutonomous = true;
				}
				else 
				{
					isAutonomous = false;
				}
				String accredationStatus = null; 
				System.out.println("Do you like to give accreditation status");
				char accredationSelection = input.next().charAt(0);
				if(accredationSelection=='y' || accredationSelection=='Y')
				{
					System.out.println("Select the Accreditation status \n1.Accepted \n2.Pending");
					int accredationStatusOption = input.nextInt();
					if(accredationStatusOption==1)
					{
						accredationStatus = "Accepted";
					}
					else if(accredationStatusOption==2)
					{
						accredationStatus = "Pending";
					}
				}
				HashMap<String,Object> college = new HashMap<>();
				college.put("id",collegeID);
				college.put("name",collegeName);
				college.put("type",collegType);
				college.put("location",collegeLocation);
				college.put("user",currentUser);
				college.put("isAutonomous",isAutonomous);
				college.put("accredationStatus",accredationStatus);
				collegesList.add(college);
				//collegeStorage.put((Integer)college.get("id"),college);
				System.out.println("Do you want to add more Colleges :");
				option = input.next().charAt(0);
				if(option!='y' && option!='Y')
				{
					break;
				}
		    }
			collegService.addColleges(collegesList);
	    }
		else if(option==2)
		{	
			System.out.println("1.Display all Colleges \n2.Display specific college");
			int selectedOption = input.nextInt();
			switch(selectedOption)
			{
				case 1:
				   collegService.displayColleges();
				   break;
				case 2:
				   System.out.println("1.College ID \n2.Name");
				   int selectedField = input.nextInt();
				   Object fieldValue = null;
				   if(selectedField==1)
				   {
					  System.out.println("Enter the College ID :");
					  fieldValue = input.nextInt();
				   }
				   else
				   {
					   System.out.println("Enter the College Name :");
					   fieldValue = input.next();
				   }
				   collegService.displayColleges(fieldValue);
				   break;
			}
		}
		else if(option==3)
		{
			System.out.println("Enter the College Name :");
			String collegeName = input.next();
		    HashMap<String,Object> changeValues = new HashMap<>();
			while(true)
			{
				System.out.println("Select the Detail you want to Update :");
				System.out.println("1.CollegeName \n2.CollegeType \n3.CollegeLocation");
				String selectedOption = input.next();
				System.out.println("Enter the new Value :");
				String value = input.next();
				changeValues.put(selectedOption,value);
				System.out.println("Do you like to update more things");
				char continueOption = input.next().charAt(0);
				if(continueOption!='y' && continueOption!='Y')
				{
					break;
				}
			}
			collegService.update(changeValues,collegeName,currentUser);
		}
		else if(option==4)
		{
			System.out.println("Enter the College ID :");
			int selectedID = input.nextInt();
			collegService.delete(selectedID);			
		}
		else if(option==5)
		{
			break;
		}
	}
    }
	catch(Exception e)
	{
		System.out.println(e);
	}
   }
}