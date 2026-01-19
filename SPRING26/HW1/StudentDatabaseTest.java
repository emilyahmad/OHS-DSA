
/******************************************************************************
 *  Compilation:  javac StudentDatabaseTest.java
 *  Execution:    java StudentDatabaseTest
 ******************************************************************************/

import java.util.List;

public final class StudentDatabaseTest {
    public static void main(String[] args) {
        StudentDatabase db = new StudentDatabase();

        StudentAccount s1 = new StudentAccount(
                "Elsa", "Snow", "F001", 12, 21, 2000, "ice-powers");

        StudentAccount s2 = new StudentAccount(
                "Anna", "Snow", "F002", 6, 21, 2001, "optimism");

        StudentAccount s3 = new StudentAccount(
                "Kristoff", "Bjorgman", "F003", 3, 15, 1999, "mountain-survival");

        StudentAccount s4 = new StudentAccount(
                "Olaf", "Snowman", "F004", 7, 1, 2010, "warm-hugs");

        System.out.println("Add 4 records");
        db.addRecord(s1);
        db.addRecord(s2);
        db.addRecord(s3);
        db.addRecord(s4);
        System.out.println("size = " + db.size());
        System.out.println();

        System.out.println("Print all");
        db.printAll();
        System.out.println();

        System.out.println("Get by studentId F002");
        System.out.println(db.getByStudentId("F002"));
        System.out.println();

        System.out.println("Get by composite key for Kristoff");
        String k3 = s3.compositeKey();
        System.out.println(k3);
        System.out.println(db.getByCompositeKey(k3));
        System.out.println();

        System.out.println("Get by name contains \"Snow\"");
        List<StudentAccount> matches = db.getByName("Snow");
        for (StudentAccount s : matches)
            System.out.println(s);
        System.out.println("matches = " + matches.size());
        System.out.println();

        System.out.println("Update existing record (Elsa) data -> let-it-go");
        StudentAccount elsaUpdate = new StudentAccount(
                "Elsa", "Snow", "F001", 12, 21, 2000, "let-it-go");
        db.addRecord(elsaUpdate);
        System.out.println(db.getByStudentId("F001"));
        System.out.println();

        System.out.println("Attempt inconsistent add (same id, different composite) => exception");
        try {
            StudentAccount bad1 = new StudentAccount(
                    "Elsa", "Snow", "F001", 12, 22, 2000, "bad");
            db.addRecord(bad1);
        } catch (InconsistentKeyException e) {
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
        }
        System.out.println();

        System.out.println("Attempt inconsistent add (different id, same composite as Anna) => exception");
        try {
            StudentAccount bad2 = new StudentAccount(
                    "Anna", "Snow", "F999", 6, 21, 2001, "duplicate");
            db.addRecord(bad2);
        } catch (InconsistentKeyException e) {
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
        }
        System.out.println();

        System.out.println("Remove by studentId F004 (Olaf)");
        System.out.println("removed: " + db.removeByStudentId("F004"));
        System.out.println("size = " + db.size());
        System.out.println();

        System.out.println("Print all (after removal)");
        db.printAll();
    }
}
