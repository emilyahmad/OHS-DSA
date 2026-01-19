/******************************************************************************
 * File: StudentAccount.java
 * 
 * @author Emily Ahmad
 *         Date: 1/19/26
 *
 *         Immutable student record data type with two unique keys
 ******************************************************************************/

public final class StudentAccount {
    private final String firstName;
    private final String lastName;
    private final String studentId;

    private final int month;
    private final int day;
    private final int year;

    private String data;

    public StudentAccount(String firstName, String lastName, String studentId,
            int month, int day, int year, String data) {
        if (firstName == null || lastName == null || studentId == null || data == null)
            throw new IllegalArgumentException("null field");
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentId = studentId;
        this.month = month;
        this.day = day;
        this.year = year;
        this.data = data;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public String studentId() {
        return studentId;
    }

    public int month() {
        return month;
    }

    public int day() {
        return day;
    }

    public int year() {
        return year;
    }

    public String data() {
        return data;
    }

    public void setData(String data) {
        if (data == null)
            throw new IllegalArgumentException("null data");
        this.data = data;
    }

    public String compositeKey() {
        return canonicalDob(year, month, day) + "|" + canonicalName(firstName) + "|" + canonicalName(lastName);
    }

    public static String compositeKeyOf(String firstName, String lastName,
            int month, int day, int year) {
        if (firstName == null || lastName == null)
            throw new IllegalArgumentException("null name");
        return canonicalDob(year, month, day) + "|" + canonicalName(firstName) + "|" + canonicalName(lastName);
    }

    private static String canonicalName(String s) {
        return s.trim().toLowerCase();
    }

    private static String canonicalDob(int year, int month, int day) {
        return String.format("%04d-%02d-%02d", year, month, day);
    }

    public String toString() {
        return "StudentAccount{" +
                "id=" + studentId +
                ", name=" + firstName + " " + lastName +
                ", dob=" + String.format("%02d/%02d/%04d", month, day, year) +
                ", data=" + data +
                "}";
    }
}
