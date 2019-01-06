/*****************************
Query the University Database
*****************************/
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.sql.CallableStatement;
import java.util.*;
import java.lang.String;

public class MyQuery {

	private Connection conn = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
    
    public MyQuery(Connection c)throws SQLException
    {
    	  this.conn = c;
        // Statements allow to issue SQL queries to the database
        this.statement = this.conn.createStatement();
    }
    
    public void findFall2009Students() throws SQLException
    {
    	String query  = "SELECT DISTINCT name\r\n" +
        				"FROM student NATURAL JOIN takes\r\n" +
        				"WHERE semester = 'Fall' AND year = 2009;";

        this.resultSet = this.statement.executeQuery(query);
    }
    
    public void printFall2009Students() throws IOException, SQLException
    {
    	System.out.println("******** Query 0 ********");
        System.out.printf("%-10s%1s", "name", "\n");
        System.out.println("----------");
        String name;
        while (resultSet.next()) 
        {
        	// It is possible to get the columns via name
			// also possible to get the columns via the column number which starts at 1
			name = resultSet.getString(1);
			System.out.printf("%-10s%1s", name, "\n");
   		}   
        System.out.println();
    }

    public void findGPAInfo() throws SQLException
    {
    	String query = "SELECT ID, name, sum((CASE\r\n" +
    										 	"WHEN grade = 'F' THEN 0\r\n" +
    										 	"WHEN grade = 'D-' THEN 0.67\r\n" +
    										 	"WHEN grade = 'D' THEN 1\r\n" +
    										 	"WHEN grade = 'D+' THEN 1.33\r\n" +
    										 	"WHEN grade = 'C-' THEN 1.67\r\n" +
    										 	"WHEN grade = 'C' THEN 2\r\n" +
    										 	"WHEN grade = 'C+' THEN 2.33\r\n" +
    										 	"WHEN grade = 'B-' THEN 2.67\r\n" +
    										 	"WHEN grade = 'B' THEN 3\r\n" +
    										 	"WHEN grade = 'B+' THEN 3.33\r\n" +
    										 	"WHEN grade = 'A-' THEN 3.67\r\n" +
    										 	"WHEN grade = 'A' THEN 4 END) * credits) / sum(credits) GPA\r\n" +
    				   "FROM student JOIN takes USING(ID) JOIN course USING(course_id)\r\n" +
    				   "WHERE grade IS NOT null\r\n" +
    				   "GROUP BY ID";
    	this.resultSet = this.statement.executeQuery(query);
    }
    
    public void printGPAInfo() throws IOException, SQLException
    {
		   System.out.println("******** Query 1 ********");
		   System.out.printf("%-7s%-10s%-10s%1s", "ID", "name", "GPA", "\n");
		   System.out.println("------ --------- ---------");
		   
		   String ID, name, GPA;
		   while (this.resultSet.next())
		   {
			   ID = this.resultSet.getString(1);
			   name = this.resultSet.getString(2);
			   GPA = this.resultSet.getString(3);
			   
			   System.out.printf("%-7s%-10s%-9s%1s", ID, name, GPA, "\n");
		   }
		   System.out.println();
    }

    public void findMorningCourses() throws SQLException
    {
    	String query = "SELECT course_id, title, sec_id, semester, year, name, count(DISTINCT takes.id) enrollment\r\n" + 
    				   "FROM course NATURAL JOIN section NATURAL JOIN time_slot NATURAL JOIN teaches NATURAL JOIN instructor JOIN takes USING (course_id, sec_id, semester, year)\r\n" + 
    				   "WHERE start_hr <= 12\r\n" + 
    				   "GROUP BY course_id, sec_id, semester, year, name\r\n" + 
    				   "HAVING count(DISTINCT takes.id) > 0;";
    	this.resultSet = this.statement.executeQuery(query);
    }

    public void printMorningCourses() throws IOException, SQLException
    {
	   	System.out.println("******** Query 2 ********");
	   	System.out.printf("%-11s%-29s%-7s%-11s%-6s%-12s%-12s%1s", "course_id", "title", "sec_id", " semester", "year", "name", "enrollment", "\n");
	   	System.out.println("---------- ---------------------------- ------- --------- ----- ----------- -----------");
	   	
	   	String course_id, title, sec_id, semester, year, name, enrollment;
	   	while (this.resultSet.next())
	   	{
	   		course_id = this.resultSet.getString(1);
	   		title = this.resultSet.getString(2);
	   		sec_id = this.resultSet.getString(3);
	   		semester = this.resultSet.getString(4);
	   		year = this.resultSet.getString(5);
	   		name = this.resultSet.getString(6);
	   		enrollment = this.resultSet.getString(7);
	   		
	   		System.out.printf("%-11s%-29s%-8s%-10s%-6s%-12s%-12s%1s", course_id, title, sec_id, semester, year, name, enrollment, "\n");
	   	}
	   	System.out.println();
    }

    public void findBusyInstructor() throws SQLException
    {
    	String query = "SELECT name\r\n" +
    				   "FROM instructor NATURAL JOIN teaches\r\n" +
    				   "GROUP BY ID\r\n" +
    				   "HAVING count(ID) >= ALL (SELECT count(ID)\r\n" +
    									 	    "FROM instructor NATURAL JOIN teaches\r\n" +
    									 	    "GROUP BY ID);";
    	this.resultSet = this.statement.executeQuery(query);
    }

    public void printBusyInstructor() throws IOException, SQLException
    {
		   System.out.println("******** Query 3 ********");
		   System.out.printf("%-12s%1s", "name", "\n");
		   System.out.println("------------");
		   
		   String name;
		   while (this.resultSet.next())
		   {
			   name = this.resultSet.getString(1);
			   
			   System.out.printf("%-12s%1s", name, "\n");
		   }
		   System.out.println();
    }

    public void findPrereq() throws SQLException
    {
    	String query = "SELECT title course, CASE\r\n" +
												"WHEN prereq_id IS null THEN ''\r\n" +
												"ELSE (SELECT t2.title\r\n" +
													  "FROM course t2\r\n" +
													  "WHERE t1.prereq_id = t2.course_id) END prereq\r\n" +
					   "FROM course LEFT JOIN prereq t1 USING (course_id);";
    	this.resultSet = this.statement.executeQuery(query);
    }

    public void printPrereq() throws IOException, SQLException
    {
		   System.out.println("******** Query 4 ********");
		   System.out.printf("%-28s%-28s%1s", "course", "prereq", "\n");
		   System.out.println("--------------------------- ---------------------------");
		   
		   String course, prereq;
		   while (this.resultSet.next())
		   {
			   course = this.resultSet.getString(1);
			   prereq = this.resultSet.getString(2);
			   
			   System.out.printf("%-29s%-29s%1s", course, prereq, "\n");
		   }
		   System.out.println();
    }

    public void updateTable() throws SQLException
    {
    	String updateQuery = "UPDATE studentCopy t1\r\n" +
    						 "SET tot_cred = (SELECT sum(credits)\r\n" +
    						 				 "FROM course NATURAL JOIN takes t2\r\n" +
    						 				 "WHERE grade != 'F' AND grade IS NOT null AND t1.ID = t2.ID);";
    	this.statement.execute(updateQuery);
    	
    	String displayQuery = "SELECT *\r\n" +
    						  "FROM studentCopy";
    	this.resultSet = this.statement.executeQuery(displayQuery);
    }

    public void printUpdatedTable() throws IOException, SQLException
    {
		   System.out.println("******** Query 5 ********");
		   System.out.printf("%-7s%-11s%-12s%-10s%1s", "ID", "name", "dept_name", "tot_cred", "\n");
		   System.out.println("------ ---------- ----------- ---------");
		   
		   String ID, name, dept_name, tot_cred;
		   while (this.resultSet.next())
		   {
			   ID = this.resultSet.getString(1);
			   name = this.resultSet.getString(2);
			   dept_name = this.resultSet.getString(3);
			   tot_cred = this.resultSet.getString(4);
			   
			   System.out.printf("%-7s%-11s%-12s%-10s%1s", ID, name, dept_name, tot_cred, "\n");
		   }
		   System.out.println();
    }

    public void findFirstLastSemester() throws SQLException
    {
    	String query = "SELECT v1.id, name, v1.t1 first_semester, v2.t2 last_semester\r\n" +
    				   "FROM (SELECT DISTINCT id, name, semester, year, concat(semester, ' ', year) t1\r\n" +
    					     "FROM student NATURAL JOIN takes w1\r\n" +
    				         "WHERE year = (SELECT min(year)\r\n" +
    									   "FROM takes x1\r\n" + 
    				                       "WHERE w1.id = x1.id)\r\n" +
    							   "AND semester = (SELECT max(semester)\r\n" +
    											   "FROM takes x2\r\n" +
    											   "WHERE w1.id = x2.id\r\n" +
    													 "AND year = (SELECT min(year)\r\n" +
    																 "FROM takes x3\r\n" +
    				                                                 "WHERE x2.id = x3.id))) v1,\r\n" +
    					    "(SELECT DISTINCT id, semester, year, concat(semester, ' ', year) t2\r\n" +
    				         "FROM takes w2\r\n" +
    				         "WHERE year = (SELECT max(year)\r\n" +
    									   "FROM takes x4\r\n" +
    				                       "WHERE w2.id = x4.id)\r\n" +
    							   "AND semester = (SELECT min(semester)\r\n" +
    											   "FROM takes x5\r\n" +
    				                               "WHERE w2.id = x5.id\r\n" +
    												     "AND year = (SELECT max(year)\r\n" +
    															     "FROM takes x6\r\n" +
    				                                                 "WHERE x5.id = x6.id))) v2\r\n" +
    				   "WHERE v1.id = v2.id\r\n" +
    				   "ORDER BY id;";
    	this.resultSet = this.statement.executeQuery(query);
    }

    public void printFirstLastSemester() throws IOException, SQLException
    {
        System.out.println("******** Query 6 ********");
        System.out.printf("%-7s%-11s%-13s%-13s%1s", "id", "name", "First_Semester", "Last_Semester", "\n");
        System.out.println("------ ---------- ------------ ------------");
        
        String id, name, First_Semester, Last_Semester;
        while (this.resultSet.next())
        {
        	id = this.resultSet.getString(1);
        	name = this.resultSet.getString(2);
        	First_Semester = this.resultSet.getString(3);
        	Last_Semester = this.resultSet.getString(4);
        	
        	System.out.printf("%-7s%-11s%-13s%-13s%1s", id, name, First_Semester, Last_Semester, "\n");
        }
        System.out.println();
    }
	
	public void findHeadCounts() throws SQLException
	{
		System.out.println("******** Query 7 ********");
		  
		CallableStatement procedure = this.conn.prepareCall("{CALL getNumbers(?, ?, ?)}");
		procedure.registerOutParameter(2, Types.INTEGER);
		procedure.registerOutParameter(3, Types.INTEGER);
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter department name for query: ");
		String dept_name = scanner.nextLine();
		
		procedure.setString(1, dept_name);
		procedure.execute();
		
		int instructor_count = procedure.getInt(2);
		int student_count = procedure.getInt(3);
		
		if (instructor_count == 1)
			System.out.println(dept_name + " Department has " + instructor_count + " instructor.");
		else
			System.out.println(dept_name + " Department has " + instructor_count + " instructors.");
		
		if (student_count == 1)
			System.out.println(dept_name + " Department has " + student_count + " student.");
		else
			System.out.println(dept_name + " Department has " + student_count + " students.");
		  
		scanner.close();
	}
}
