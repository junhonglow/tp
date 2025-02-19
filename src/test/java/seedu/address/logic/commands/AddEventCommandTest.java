package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_LATER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_LATER;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.calendar.ReadOnlyCalendar;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventPeriod;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskManager;
import seedu.address.testutil.EventBuilder;

public class AddEventCommandTest {
    @Test
    public void constructor_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddEventCommand(null));
    }

    @Test
    public void constructor_eventAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingEventAdded modelStub = new ModelStubAcceptingEventAdded();
        Event validEvent = new EventBuilder().build();

        CommandResult commandResult = new AddEventCommand(validEvent).execute(modelStub);
        assertEquals(String.format(AddEventCommand.MESSAGE_SUCCESS, Messages.format(validEvent)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validEvent), modelStub.eventsAdded);
    }

    @Test
    public void execute_conflictingEvent_throwsCommandException() {
        Event validEvent = new EventBuilder().build();
        AddEventCommand addEventCommand = new AddEventCommand(validEvent);
        ModelStub modelStub = new ModelStubWithEvent(validEvent);

        assertThrows(CommandException.class, AddEventCommand.MESSAGE_EVENT_CONFLICT, () -> addEventCommand
                .execute(modelStub));
    }

    @Test
    public void equalsTest() {
        Event validEvent = new EventBuilder().build();
        EventBuilder otherValidEventBuilder = new EventBuilder();
        otherValidEventBuilder.withStartEndDate(VALID_START_DATE_LATER, VALID_END_DATE_LATER);
        Event otherEvent = otherValidEventBuilder.build();
        AddEventCommand addEventCommand = new AddEventCommand(validEvent);
        AddEventCommand notEqualAddEventCommand = new AddEventCommand(otherEvent);
        Object nonAddEventCommandObject = new Object();

        assertTrue(addEventCommand.equals(addEventCommand));

        assertFalse(addEventCommand.equals(notEqualAddEventCommand));

        assertFalse(addEventCommand.equals(nonAddEventCommandObject));
    }

    /**
     * A default model stub that have all the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getCalendarFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCalendarFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getTaskManagerFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTaskManagerFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCalendar(ReadOnlyCalendar newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyCalendar getCalendar() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Event> getCurrentWeekEventList() {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canAddEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEventAt(LocalDateTime dateTime) throws EventNotFoundException {
            throw new AssertionError("This method should not be called.");
        }

        @Override

        public Event findEventAt(LocalDateTime dateTime) throws EventNotFoundException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<Event> eventsInRange(EventPeriod range) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEventsInRange(EventPeriod range) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyCalendar getComparisonCalendar() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setComparisonCalendar(ReadOnlyCalendar eventList) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Event> getEventList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Task deleteTask(int index) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public TaskManager getTaskManager() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortTasksBy(String comparatorType) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void sortPersonList(Comparator<Person> personComparator) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single event.
     */
    private class ModelStubWithEvent extends AddEventCommandTest.ModelStub {
        private final Event event;

        ModelStubWithEvent(Event event) {
            requireNonNull(event);
            this.event = event;
        }

        @Override
        public boolean canAddEvent(Event other) {
            return !event.isConflicting(other);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingEventAdded extends AddEventCommandTest.ModelStub {
        final ArrayList<Event> eventsAdded = new ArrayList<Event>();

        @Override
        public boolean canAddEvent(Event event) {
            requireNonNull(event);
            return eventsAdded.stream().anyMatch(x -> !x.isConflicting(event)) || eventsAdded.isEmpty();
        }

        @Override
        public void addEvent(Event event) {
            requireNonNull(event);
            eventsAdded.add(event);
        }
    }
}
