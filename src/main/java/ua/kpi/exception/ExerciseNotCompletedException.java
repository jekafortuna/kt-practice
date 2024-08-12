package ua.kpi.exception;

public class ExerciseNotCompletedException extends RuntimeException {

    public ExerciseNotCompletedException() {
        super("Exercise not completed. Please provide implementation.");
    }

}
