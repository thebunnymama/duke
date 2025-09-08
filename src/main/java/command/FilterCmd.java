package command;

import exception.InvalidFilterException;
import exception.InvalidDateTimeException;
import manager.TaskManager;
import message.ErrorMessage;
import message.FilteredListMessage;
import message.Message;
import parser.TaskFilterFactory;
import task.Task;

import java.util.List;
import java.util.function.Predicate;

/**
 * Command to filter tasks based on specified criteria (task type, completion status, date).
 * <p>Usage Notes:
 * <ul>
 * <li>At least one filter criterion must be provided</li>
 * <li>Multiple criteria are combined using logical AND. Example:
 * {@code task:todo & done:true} returns only Todo task that are completed.</li>
 * <li>Conflicting criteria (e.g. {@code task:todo & task:event}) will return empty results
 * since no task can satisfy contradictory conditions</li>
 * </ul>
 */
public class FilterCmd extends BaseTaskCommand {
    public FilterCmd(TaskManager taskManager, String args) {
        super(taskManager, args);
    }

    /**
     * Filters tasks based on all specified criteria using logical AND.
     *
     * @return FilteredListMessage with tasks matching all criteria, or ErrorMessage if validation fails
     */
    @Override
    public Message execute() {
        if (taskManager.isEmpty()) {
            return new ErrorMessage(ErrorMessage.EMPTY_LIST);
        }

        if (args == null || args.isBlank()) {
            return new ErrorMessage(ErrorMessage.MISSING_DESCRIPTION);
        }

        try {
            Predicate<Task> predicates = TaskFilterFactory.chainPredicate(args);
            List<Task> filteredTasks = taskManager.filter(predicates);
            return new FilteredListMessage(filteredTasks, args);

        } catch (InvalidFilterException e) {
            String context = e.getType().getContext();
            return new ErrorMessage(String.format(ErrorMessage.FILTER_FORMAT, context));
        } catch (InvalidDateTimeException e) {
            return new ErrorMessage(ErrorMessage.INVALID_DATETIME_FORMAT);
        }
    }
}
