package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CompareCalendarCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.exceptions.RuntimeParseException;

public class CompareCalendarCommandParser implements Parser<CompareCalendarCommand> {
    private static final String splitRegex = "\\s+";
    @Override
    public CompareCalendarCommand parse(String userInput) throws ParseException {
        try {
            String personIndexString = userInput.trim();
            String[] personIndexArray = personIndexString.split(splitRegex);
            if (personIndexArray.length == 0) {
                throw new RuntimeParseException();
            }
            List<Index> indexList = Arrays.stream(personIndexArray)
                    .map(ParserUtil::parseIndexSafe)
                    .collect(Collectors.toList());
            return new CompareCalendarCommand(indexList);
        } catch (RuntimeParseException pe) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, CompareCalendarCommand.MESSAGE_USAGE));
        }
    }
}
