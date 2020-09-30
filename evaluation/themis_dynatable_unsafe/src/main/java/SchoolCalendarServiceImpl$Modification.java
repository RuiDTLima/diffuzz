import com.google.gwt.sample.dynatable.client.Person;
import com.google.gwt.sample.dynatable.client.Professor;
import com.google.gwt.sample.dynatable.client.Schedule;
import com.google.gwt.sample.dynatable.client.SchoolCalendarService;
import com.google.gwt.sample.dynatable.client.Student;
import com.google.gwt.sample.dynatable.client.TimeSlot;
import com.google.gwt.user.server.rpc.HybridServiceServlet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
public class SchoolCalendarServiceImpl$Modification extends HybridServiceServlet implements SchoolCalendarService {
    private static final String[] FIRST_NAMES = new String[]{ "Inman", "Sally", "Omar", "Teddy", "Jimmy", "Cathy", "Barney", "Fred", "Eddie", "Carlos" };

    private static final String[] LAST_NAMES = new String[]{ "Smith", "Jones", "Epps", "Gibbs", "Webber", "Blum", "Mendez", "Crutcher", "Needler", "Wilson", "Chase", "Edelstein" };

    private static final String[] SUBJECTS = new String[]{ "Chemistry", "Phrenology", "Geometry", "Underwater Basket Weaving", "Basketball", "Computer Science", "Statistics", "Materials Engineering", "English Literature", "Geology" };

    private static final Person[] NO_PEOPLE = new Person[0];

    private static final int CLASS_LENGTH_MINS = 50;

    private static final int MAX_SCHED_ENTRIES = 5;

    private static final int MIN_SCHED_ENTRIES = 1;

    private static final int MAX_PEOPLE = 100;

    private static final int STUDENTS_PER_PROF = 5;

    private final List<Person> people = new ArrayList<Person>();

    private final Random rnd = new Random(3);

    public SchoolCalendarServiceImpl$Modification() {
        generateRandomPeople();
    }

    public SchoolCalendarServiceImpl$Modification(int numberOfPeople) {
        generateRandomPeople(numberOfPeople);
    }

    public Person[] getPeople(int startIndex, int maxCount) {
        return getPeople_unsafe(startIndex, maxCount);
    }

    public Person[] getPeople_unsafe(int startIndex, int maxCount) {
        int peopleCount = people.size();
        int start = startIndex;
        if (start >= peopleCount) {
            return NO_PEOPLE;
        }
        int end = Math.min(startIndex + maxCount, peopleCount);
        if (start == end) {
            return NO_PEOPLE;
        }
        int resultCount = end - start;
        Person[] results = new Person[resultCount];
        for (int from = start, to = 0; to < resultCount; ++from , ++to) {
            results[to] = people.get(from);
        }
        return results;
    }

    public Person[] getPeople_unsafe$Modification(int startIndex, int maxCount) {
        int $4 = 0;
        int $3 = startIndex;
        Person[] $1;
        Person[] results;
        int peopleCount = people.size();
        int $2 = Math.min(startIndex + maxCount, peopleCount);
        int start = startIndex;
        if (start >= peopleCount) {
            results = NO_PEOPLE;
        } else {
            $1 = NO_PEOPLE;
        }
        int end = Math.min(startIndex + maxCount, peopleCount);
        if (start == end) {
            results = NO_PEOPLE;
        } else {
            $1 = NO_PEOPLE;
        }
        int resultCount = 0;
        if ((!(start >= peopleCount)) && (!(start == end))) {
            resultCount = end - start;
        } else {
            $4 = $2 - $3;
        }
        results = new Person[resultCount];
        for (int from = start, to = 0; to < resultCount; ++from , ++to) {
            results[to] = people.get(from);
        }
        return results;
    }

    @Override
    protected void onAfterResponseSerialized(String serializedResponse) {
        System.out.println(serializedResponse);
    }

    private void generateRandomPeople(int numberOfPeople) {
        for (int i = 0; i < numberOfPeople; ++i) {
            Person person = generateRandomPerson();
            people.add(person);
        }
    }

    private void generateRandomPeople() {
        for (int i = 0; i < MAX_PEOPLE; ++i) {
            Person person = generateRandomPerson();
            people.add(person);
        }
    }

    private Person generateRandomPerson() {
        if (rnd.nextInt(STUDENTS_PER_PROF) == 1) {
            return generateRandomProfessor();
        } else {
            return generateRandomStudent();
        }
    }

    private Person generateRandomProfessor() {
        Professor prof = new Professor();
        String firstName = pickRandomString(FIRST_NAMES);
        String lastName = pickRandomString(LAST_NAMES);
        prof.setName((("Dr. " + firstName) + " ") + lastName);
        String subject = pickRandomString(SUBJECTS);
        prof.setDescription("Professor of " + subject);
        generateRandomSchedule(prof.getTeachingSchedule());
        return prof;
    }

    private void generateRandomSchedule(Schedule sched) {
        int range = (MAX_SCHED_ENTRIES - MIN_SCHED_ENTRIES) + 1;
        int howMany = MIN_SCHED_ENTRIES + rnd.nextInt(range);
        TimeSlot[] timeSlots = new TimeSlot[howMany];
        for (int i = 0; i < howMany; ++i) {
            int startHrs = 8 + rnd.nextInt(9);
            int startMins = 15 * rnd.nextInt(4);
            int dayOfWeek = 1 + rnd.nextInt(5);
            int absStartMins = (60 * startHrs) + startMins;
            int absStopMins = absStartMins + CLASS_LENGTH_MINS;
            timeSlots[i] = new TimeSlot(dayOfWeek, absStartMins, absStopMins);
        }
        Arrays.sort(timeSlots);
        for (int i = 0; i < howMany; ++i) {
            sched.addTimeSlot(timeSlots[i]);
        }
    }

    private Person generateRandomStudent() {
        Student student = new Student();
        String firstName = pickRandomString(FIRST_NAMES);
        String lastName = pickRandomString(LAST_NAMES);
        student.setName((firstName + " ") + lastName);
        String subject = pickRandomString(SUBJECTS);
        student.setDescription("Majoring in " + subject);
        generateRandomSchedule(student.getClassSchedule());
        return student;
    }

    private String pickRandomString(String[] a) {
        int i = rnd.nextInt(a.length);
        return a[i];
    }
}