package com.epam.rd.autocode.collections;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StudentGradebookImpl implements StudentGradebook {

    private Map<Student, Map<String, BigDecimal>> map;
    private final Comparator<Student> comparator;

    public StudentGradebookImpl() {
        // Comparator for sorting students by last name, then first name, then group
        comparator = new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                int lastNameComparison = s1.getLastName().compareTo(s2.getLastName());
                if (lastNameComparison != 0) {
                    return lastNameComparison;
                }
                int firstNameComparison = s1.getFirstName().compareTo(s2.getFirstName());
                if (firstNameComparison != 0) {
                    return firstNameComparison;
                }
                return s1.getGroup().compareTo(s2.getGroup());
            }
        };
        map = new TreeMap<>(comparator);
    }

    @Override
    public boolean addEntryOfStudent(Student student, String discipline, BigDecimal grade) {
        if (grade.compareTo(BigDecimal.ZERO) < 0 || grade.compareTo(BigDecimal.valueOf(5)) > 0) {
            return false; // Invalid grade
        }

        // Get existing disciplines or create a new map if none exists
        Map<String, BigDecimal> disciplines = map.get(student);
        if (disciplines == null) {
            disciplines = new HashMap<>();
            map.put(student, disciplines);
        }

        // Check if discipline already exists
        if (disciplines.containsKey(discipline)) {
            return false; // Discipline already exists for this student
        }

        // Add the new discipline and grade
        disciplines.put(discipline, grade);
        return true;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public Comparator<Student> getComparator() {
        return comparator;
    }

    @Override
    public List<String> getStudentsByDiscipline(String discipline) {
        List<String> result = new ArrayList<>();

        // Iterate over the entries in the map
        for (Map.Entry<Student, Map<String, BigDecimal>> entry : map.entrySet()) {
            Student student = entry.getKey();
            Map<String, BigDecimal> grades = entry.getValue();

            // Check if the student has the specified discipline
            if (grades.containsKey(discipline)) {
                BigDecimal grade = grades.get(discipline);
                // Format and add the result with last name first
                result.add(student.getLastName() + "_" + student.getFirstName() + ": " + grade.toPlainString());
            }
        }

        // Sort the result list by student names in the format lastName_firstName
        result.sort(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                // Extract the names from the formatted strings
                String[] parts1 = s1.split(":");
                String[] parts2 = s2.split(":");
                String name1 = parts1[0].trim();
                String name2 = parts2[0].trim();

                // Compare names
                return name1.compareTo(name2);
            }
        });

        return result;
    }



    

    @Override
    public Map<Student, Map<String, BigDecimal>> removeStudentsByGrade(BigDecimal grade) {
        Map<Student, Map<String, BigDecimal>> removedStudents = new TreeMap<>(comparator);
        List<Student> studentsToRemove = new ArrayList<>();

        for (Map.Entry<Student, Map<String, BigDecimal>> entry : map.entrySet()) {
            Student student = entry.getKey();
            Map<String, BigDecimal> disciplines = entry.getValue();
            boolean shouldRemove = false;

            for (BigDecimal studentGrade : disciplines.values()) {
                if (studentGrade.compareTo(grade) < 0) {
                    shouldRemove = true;
                    break;
                }
            }

            if (shouldRemove) {
                removedStudents.put(student, disciplines);
                studentsToRemove.add(student);
            }
        }

        for (Student student : studentsToRemove) {
            map.remove(student);
        }

        return removedStudents;
    }

    @Override
    public Map<BigDecimal, List<Student>> getAndSortAllStudents() {
        Map<BigDecimal, List<Student>> gradeToStudentsMap = new TreeMap<>();

        for (Map.Entry<Student, Map<String, BigDecimal>> entry : map.entrySet()) {
            Student student = entry.getKey();
            BigDecimal averageGrade = calculateAverageGrade(entry.getValue());

            List<Student> students = gradeToStudentsMap.get(averageGrade);
            if (students == null) {
                students = new ArrayList<>();
                gradeToStudentsMap.put(averageGrade, students);
            }
            students.add(student);
        }

        return gradeToStudentsMap;
    }

    private BigDecimal calculateAverageGrade(Map<String, BigDecimal> grades) {
        BigDecimal sum = BigDecimal.ZERO;
        int count = 0;
    
        for (BigDecimal grade : grades.values()) {
            sum = sum.add(grade);
            count++;
        }
    
        if (count == 0) {
            return BigDecimal.ZERO;
        }
    
        return sum.divide(BigDecimal.valueOf(count), 1, RoundingMode.HALF_UP);
    }
}
