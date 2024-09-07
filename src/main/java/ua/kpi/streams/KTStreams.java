package ua.kpi.streams;

import ua.kpi.entity.Employee;
import ua.kpi.entity.Payment;
import ua.kpi.exception.ExerciseNotCompletedException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class KTStreams {

    /**
     * Checks if all payments in the list are approved.
     *
     * @return True if all payments are approved, false otherwise.
     */
    public static boolean allPaymentsApproved(List<Payment> payments) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Filters payments which were approved.
     *
     * @return A list of payments with the approved status.
     */
    public static List<Payment> filterPaymentsByStatus(List<Payment> payments) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Calculates the quantity of payments which were paid by USD
     *
     * @return The total amount of payments in USD.
     */
    public static BigDecimal totalAmountByCurrency(List<Payment> payments) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Retrieve unique customer names which name length is less than requested
     *
     * @return A set of unique customer names with .
     */
    public static Set<String> findDistinctCustomers(List<Payment> payments, int nameLength) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Finds the earliest payment date in the list.
     *
     * @return The earliest payment date. Otherwise - return null
     */
    public static LocalDate findEarliestPaymentDate(List<Payment> payments) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Finds the maximum payment amount in the list. If no found - return zero
     *
     * @return The maximum payment amount.
     */
    public static BigDecimal findMaxPaymentAmount(List<Payment> payments) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Retrieves a list of payment references for a specific customer.
     *
     * @param customerName The customer's name.
     * @return A list of payment references for the customer.
     */
    public static List<String> getReferencesForCustomer(List<Payment> payments, String customerName) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Counts the number of payments for each payment method.
     *
     * @return A map with payment methods as keys and their respective counts as values.
     */
    public static Map<String, Long> countByPaymentMethod(List<Payment> payments) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Partitions payments into two lists: those above and those below a given amount.
     *
     * @param amount The threshold amount for partitioning.
     * @return A map with two lists: "above" for payments above the amount, and "below" for payments below or equal to the amount.
     */
    public static Map<Boolean, List<Payment>> partitionByAmount(List<Payment> payments, BigDecimal amount) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Groups the payments by currency and maps them to a list of customer names.
     *
     * @return a map where the key is the Currency and the value is a list of customer names who made payments in that currency
     */
    public static Map<Payment.Currency, List<String>> groupByCurrencyAndMapToCustomerNames(List<Payment> payments) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Groups the payments by status and maps them to a set of payment methods.
     *
     * @return a map where the key is the PaymentStatus and the value is a set of payment methods used for that status
     */
    public static Map<Payment.PaymentStatus, Set<String>> groupByStatusAndMapToPaymentMethods(List<Payment> payments) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Groups the payments by date and maps them to a list of references.
     *
     * @return a map where the key is the date and the value is a list of references of payments made on that date
     */
    public static Map<LocalDate, List<String>> groupByDateAndMapToReferences(List<Payment> payments) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Groups customer names by payment statuses
     *
     * @return a map where a key is a payment status and value is comma-separated customer names
     */
    public static Map<Payment.PaymentStatus, String> groupByCustomer(List<Payment> payments) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Groups the payments by customer name and maps them to the total payment amount per customer.
     *
     * @return a map where the key is the customer name and the value is the total amount they paid
     */
    public static Map<String, BigDecimal> groupByCustomerAndMapToTotalAmount(List<Payment> payments) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Gets the details of the highest paid employee in the organization.
     *
     * @return the employee with the highest salary
     */
    public static Employee getHighestPaidEmployee(List<Employee> employees) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Finds the employee who has spent the most years in the organization.
     *
     * @return the employee with the earliest year of joining
     */
    public static Employee getMostExperiencedEmployee(List<Employee> employees) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Counts the number of employees in each department.
     *
     * @return a map where the key is the department name and the value is the number of employees in that department
     */
    public static Map<String, Long> countEmployeesInDepartments(List<Employee> employees) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Counts the number of male and female employees in the organization.
     *
     * @return a map where the key is the gender and the value is the number of employees of that gender
     */
    public static Map<String, Long> countEmployeesByGender(List<Employee> employees) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Finds the average salary of employees grouped by gender.
     *
     * @return a map where the key is the gender and the value is the average salary of that gender
     */
    public static Map<String, Double> averageSalaryByGender(List<Employee> employees) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Calculates the average age of employees in each department.
     *
     * @return a map where the key is the department name and the value is the average age in that department
     */
    public static Map<String, Double> averageAgeInDepartments(List<Employee> employees) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Sums the total salaries of all employees in each department.
     *
     * @return a map where the key is the department and the value is the total salary in that department
     */
    public static Map<String, Double> sumSalariesByDepartment(List<Employee> employees) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Finds the oldest employee in each department.
     *
     * @return a map where the key is the department and the value is the oldest employee in that department
     */
    public static Map<String, Employee> oldestEmployeeInDepartment(List<Employee> employees) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Groups employees by age range (e.g., "20-30", "30-40", etc.).
     *
     * @return a map where the key is the age range and the value is the list of employees in that age range
     */
    public static Map<String, List<Employee>> groupEmployeesByAgeRange(List<Employee> employees) {
        throw new ExerciseNotCompletedException();
    }

}
