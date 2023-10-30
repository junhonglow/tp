package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.calendar.ReadOnlyCalendar;
import seedu.address.model.person.Person;

public class CompareCalendarCommand extends Command {
    public static final String COMMAND_WORD = "compare";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": compare calendars with specified people index. "
            + "Parameters: "
            + "ANY VALID PERSON INDEX \n"
            + "Example: " + COMMAND_WORD + " "
            + "1 2 3";

    public static final String MESSAGE_SUCCESS = "Displaying available time periods";
    private final List<Index> indexList;

    public CompareCalendarCommand(List<Index> indexList) {
        this.indexList = indexList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (indexList.stream().map(Index::getZeroBased)
                .anyMatch(zeroBasedIndex -> zeroBasedIndex >= lastShownList.size())) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        ReadOnlyCalendar combinedCalendar = lastShownList.stream().map(Person::getReadOnlyCalendar)
                .reduce(model.getCalendar(), ReadOnlyCalendar::combineCalendar);

        model.setComparisonCalendar(combinedCalendar);

        return new CommandResult(MESSAGE_SUCCESS, false, false, true);
    }
}
