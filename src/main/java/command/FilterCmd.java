package command;

import exception.MeeBotException;
import manager.TaskManager;
import message.ErrorMessage;
import message.FilteredListMessage;
import message.Message;
import util.TaskFilterParser;
import task.Task;

import java.util.List;
import java.util.function.Predicate;

/**
 * Command to filter tasks based on specified criteria (task type, completion status, date).
 * <p>Usage Notes:
 * <li>At least one filter criterion must be provided</li>
 * <li>Multiple criteria are combined using logical AND. Example:
 * {@code task:todo & done:true} returns only Todo task that are completed.</li>
 * <li>Conflicting criteria (e.g. {@code task:todo & task:event}) will return empty results
 * since no task can satisfy contradictory conditions</li>
 */
public class FilterCmd extends BaseTaskCommand {
    public FilterCmd(TaskManager taskManager, String args) {
        super(taskManager, args);
    }

    /**
     * Filters tasks based on all specified criteria using logical AND.
     *
     * @return {@link FilteredListMessage} with tasks matching all criteria, or {@link ErrorMessage} if validation fails
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
            Predicate<Task> predicates = TaskFilterParser.chainPredicate(args);
            List<Task> filteredTasks = taskManager.filter(predicates);
            return new FilteredListMessage(filteredTasks, args);

        } catch (MeeBotException e) {
            return e.toErrorMessage();
        }
    }
}
