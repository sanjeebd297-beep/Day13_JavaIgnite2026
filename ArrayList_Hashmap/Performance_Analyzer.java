/*
Create a Java program to manage student course performance.

Task:

You are given multiple students with the following details:

Student Name
Course Name
Marks
Requirements:
Store all student records using ArrayList
Use a HashMap<String, List<Integer>> where:
Key = Course Name
Value = List of marks in that course
Calculate and display:
Average marks for each course
Course with highest average
Hint:

Break the problem into steps:

First store raw data in ArrayList
Then group marks by course using HashMap

for each loop - enhanced for loop 
for(int i=0;i<list.size();i++)
Student s=list.get(i)

for(Student s : list)
String course = s.course 
int marks = s.marks 
if(map.containsKey(course))
ArrayList<Integer> marksList = map.get(course)
marksList.add(marks)

ArrayList<Integer> marksList = map.get(course)
marksList.add(marks)
map.put(course,marksList)


Then iterate HashMap to calculate averages - for(String course:map.keySet())- creating arraylist -int sum =0, 
for(int i=0;i<marksList.size();i++)
sum=sum+marksList(i)
double =(double)sum/marksList.size();


Finally compare averages
  */
package JavaIgniteDay13;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Students {
    String studentName;
    String courseName;
    int marks;

    Students(String studentName, String courseName, int marks) {
        this.studentName = studentName;
        this.courseName = courseName;
        this.marks = marks;
    }
}

public class Performance_Analyzer {
	 public static void main(String[] args) {
	    	// TODO Auto-generated method stub
	        // Step 1: Store raw data in ArrayList
	        ArrayList<Students> students = new ArrayList<>();

	        students.add(new Students("Rahul", "Java", 85));
	        students.add(new Students("Priya", "Java", 90));
	        students.add(new Students("Amit", "Python", 75));
	        students.add(new Students("Sneha", "Python", 80));
	        students.add(new Students("Rohan", "Data Structure", 95));
	        students.add(new Students("Anjali", "Data Structure", 85));

	        // Step 2: Group marks by course using HashMap
	        HashMap<String, List<Integer>> courseMarks = new HashMap<>();

	        for (Students s : students) {

	            if (!courseMarks.containsKey(s.courseName)) {
	                courseMarks.put(s.courseName, new ArrayList<Integer>());
	            }

	            courseMarks.get(s.courseName).add(s.marks);
	        }

	        // Step 3: Calculate average for each course
	        HashMap<String, Double> courseAverage = new HashMap<>();

	        System.out.println("Average Marks by Course:");

	        for (String course : courseMarks.keySet()) {

	            List<Integer> marksList = courseMarks.get(course);

	            int sum = 0;

	            for (int mark : marksList) {
	                sum += mark;
	            }

	            double average = (double) sum / marksList.size();

	            courseAverage.put(course, average);

	            System.out.println(course + " -> " + average);
	        }

	        // Step 4: Find course with highest average
	        String highestCourse = "";
	        double highestAverage = 0;

	        for (String course : courseAverage.keySet()) {

	            if (courseAverage.get(course) > highestAverage) {
	                highestAverage = courseAverage.get(course);
	                highestCourse = course;
	            }
	        }

	        System.out.println("\nCourse with Highest Average:");
	        System.out.println(highestCourse + " -> " + highestAverage);
	    }
	}
