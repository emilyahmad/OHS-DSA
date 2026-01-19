
/******************************************************************************
 * File: StudentAccount.java
 * 
 * @author Emily Ahmad
 *         Date: 1/19/26
 *
 *         Client program that tests the StudentDatabase ADT
 ******************************************************************************/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public final class StudentDatabase {
    private final LinkedList<StudentAccount> records;
    private final HashMap<String, StudentAccount> byId;
    private final HashMap<String, StudentAccount> byComposite;

    public StudentDatabase() {
        records = new LinkedList<>();
        byId = new HashMap<>();
        byComposite = new HashMap<>();
    }

    public int size() {
        return records.size();
    }

    public StudentAccount getByStudentId(String studentId) {
        if (studentId == null)
            throw new IllegalArgumentException("null id");
        return byId.get(studentId);
    }

    public StudentAccount getByCompositeKey(String compositeKey) {
        if (compositeKey == null)
            throw new IllegalArgumentException("null composite");
        return byComposite.get(compositeKey);
    }

    public List<StudentAccount> getByName(String namePart) {
        if (namePart == null)
            throw new IllegalArgumentException("null namePart");
        String q = namePart;
        List<StudentAccount> matches = new ArrayList<>();
        for (StudentAccount s : records) {
            if (s.firstName().contains(q) || s.lastName().contains(q))
                matches.add(s);
        }
        return matches;
    }

    public void addRecord(StudentAccount s) {
        if (s == null)
            throw new IllegalArgumentException("null record");

        String id = s.studentId();
        String comp = s.compositeKey();

        StudentAccount a = byId.get(id);
        StudentAccount b = byComposite.get(comp);

        if (a == null && b == null) {
            records.add(s);
            byId.put(id, s);
            byComposite.put(comp, s);
            return;
        }

        if (a != null && b != null) {
            if (a != b) {
                throw new InconsistentKeyException("two keys match two different records");
            }
            a.setData(s.data());
            return;
        }

        throw new InconsistentKeyException("only one key matches an existing record");
    }

    public StudentAccount removeByStudentId(String studentId) {
        if (studentId == null)
            throw new IllegalArgumentException("null id");

        StudentAccount s = byId.get(studentId);
        if (s == null)
            return null;

        records.remove(s);
        byId.remove(s.studentId());
        byComposite.remove(s.compositeKey());
        return s;
    }

    public void printAll() {
        for (StudentAccount s : records)
            System.out.println(s);
    }
}
