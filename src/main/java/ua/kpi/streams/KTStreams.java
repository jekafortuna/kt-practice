package ua.kpi.streams;

import ua.kpi.entity.Employee;
import ua.kpi.entity.Payment;
import ua.kpi.entity.Payment.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.summingDouble;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class KTStreams {

    /**
     * Checks if all payments in the list are approved.
     *
     * @return True if all payments are approved, false otherwise.
     */
    public static boolean allPaymentsApproved(List<Payment> payments) {
        return payments.stream()
                .allMatch(payment -> payment.status().equals(Payment.PaymentStatus.APPROVED));
    }

    /**
     * Filters payments which were approved.
     *
     * @return A list of payments with the approved status.
     */
    public static List<Payment> filterPaymentsByStatus(List<Payment> payments) {
        return payments.stream()
                .filter(payment -> payment.status().equals(PaymentStatus.APPROVED))
                .toList();
    }

    /**
     * Calculates the quantity of payments which were paid by USD
     *
     * @return The total amount of payments in USD.
     */
    public static BigDecimal totalAmountByCurrency(List<Payment> payments) {
        return payments.stream()
                .filter(payment -> payment.currency().equals(Payment.Currency.USD))
                .map(Payment::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Retrieve unique customer names which name length is less than requested
     *
     * @return A set of unique customer names with .
     */
    public static Set<String> findDistinctCustomers(List<Payment> payments, int nameLength) {
        return payments.stream()
                .map(Payment::customerName)
                .filter(name -> name.length() <= nameLength)
                .collect(Collectors.toSet());
    }

    /**
     * Finds the earliest payment date in the list.
     *
     * @return The earliest payment date. Otherwise - return null
     */
    public static LocalDate findEarliestPaymentDate(List<Payment> payments) {
        return payments.stream()
                .map(Payment::date)
                .min(LocalDate::compareTo)
                .orElse(null);
    }

    /**
     * Finds the maximum payment amount in the list. If no found - return zero
     *
     * @return The maximum payment amount.
     */
    public static BigDecimal findMaxPaymentAmount(List<Payment> payments) {
        return payments.stream()
                .map(Payment::amount)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
    }

    /**
     * Retrieves a list of payment references for a specific customer.
     *
     * @param customerName The customer's name.
     * @return A list of payment references for the customer.
     */
    public static List<String> getReferencesForCustomer(List<Payment> payments, String customerName) {
        return payments.stream()
                .filter(payment -> payment.customerName().equals(customerName))
                .map(Payment::reference)
                .toList();
    }

    /**
     * Counts the number of payments for each payment method.
     *
     * @return A map with payment methods as keys and their respective counts as values.
     */
    public static Map<String, Long> countByPaymentMethod(List<Payment> payments) {
        return payments.stream()
                .collect(groupingBy(Payment::paymentMethod,
                        counting()));
    }

    /**
     * Partitions payments into two lists: those above and those below a given amount.
     *
     * @param amount The threshold amount for partitioning.
     * @return A map with two lists: "above" for payments above the amount, and "below" for payments below or equal to the amount.
     */
    public static Map<Boolean, List<Payment>> partitionByAmount(List<Payment> payments, BigDecimal amount) {
        return payments.stream()
                .collect(Collectors.partitioningBy(payment -> payment.amount().compareTo(amount) > 0));
    }

    /**
     * Groups the payments by currency and maps them to a list of customer names.
     *
     * @return a map where the key is the Currency and the value is a list of customer names who made payments in that currency
     */
    public static Map<Payment.Currency, List<String>> groupByCurrencyAndMapToCustomerNames(List<Payment> payments) {
        return payments.stream()
                .collect(groupingBy(Payment::currency,
                        mapping(Payment::customerName, toList())));
    }

    /**
     * Groups the payments by status and maps them to a set of payment methods.
     *
     * @return a map where the key is the PaymentStatus and the value is a set of payment methods used for that status
     */
    public static Map<Payment.PaymentStatus, Set<String>> groupByStatusAndMapToPaymentMethods(List<Payment> payments) {
        return payments.stream()
                .collect(groupingBy(Payment::status,
                        mapping(Payment::paymentMethod, Collectors.toSet())));
    }

    /**
     * Groups the payments by date and maps them to a list of references.
     *
     * @return a map where the key is the date and the value is a list of references of payments made on that date
     */
    public static Map<LocalDate, List<String>> groupByDateAndMapToReferences(List<Payment> payments) {
        return payments.stream()
                .collect(groupingBy(Payment::date,
                        mapping(Payment::reference, toList())));
    }

    /**
     * Groups customer names by payment statuses
     *
     * @return a map where a key is a payment status and value is comma-separated customer names
     */
    public static Map<Payment.PaymentStatus, String> groupByCustomer(List<Payment> payments) {
        return payments.stream()
                .collect(groupingBy(Payment::status,
                        mapping(Payment::customerName,
                                joining(", "))));
    }

    /**
     * Groups the payments by customer name and maps them to the total payment amount per customer.
     *
     * @return a map where the key is the customer name and the value is the total amount they paid
     */
    public static Map<String, BigDecimal> groupByCustomerAndMapToTotalAmount(List<Payment> payments) {
        return payments.stream()
                .collect(groupingBy(Payment::customerName,
                        mapping(Payment::amount,
                                reducing(BigDecimal.ZERO, BigDecimal::add))));
    }

    /**
     * Gets the details of the highest paid employee in the organization.
     *
     * @return the employee with the highest salary
     */
    public static Employee getHighestPaidEmployee(List<Employee> employees) {
        return employees.stream()
                .max(Comparator.comparing(Employee::salary))
                .orElseThrow();
    }

    /**
     * Finds the employee who has spent the most years in the organization.
     *
     * @return the employee with the earliest year of joining
     */
    public static Employee getMostExperiencedEmployee(List<Employee> employees) {
        return employees.stream()
                .min(Comparator.comparing(Employee::yearOfJoining))
                .stream()
                .findFirst()
                .orElseThrow();
    }

    /**
     * Counts the number of employees in each department.
     *
     * @return a map where the key is the department name and the value is the number of employees in that department
     */
    public static Map<String, Long> countEmployeesInDepartments(List<Employee> employees) {
        return employees.stream()
                .collect(groupingBy(Employee::department,
                        counting()));
    }

    /**
     * Counts the number of male and female employees in the organization.
     *
     * @return a map where the key is the gender and the value is the number of employees of that gender
     */
    public static Map<String, Long> countEmployeesByGender(List<Employee> employees) {
        return employees.stream()
                .collect(groupingBy(Employee::gender,
                        counting()));
    }

    /**
     * Finds the average salary of employees grouped by gender.
     *
     * @return a map where the key is the gender and the value is the average salary of that gender
     */
    public static Map<String, Double> averageSalaryByGender(List<Employee> employees) {
        return employees.stream()
                .collect(groupingBy(Employee::gender,
                        averagingDouble(Employee::salary)));
    }

    /**
     * Calculates the average age of employees in each department.
     *
     * @return a map where the key is the department name and the value is the average age in that department
     */
    public static Map<String, Double> averageAgeInDepartments(List<Employee> employees) {
        return employees.stream()
                .collect(groupingBy(Employee::department,
                        averagingDouble(Employee::age)));
    }

    /**
     * Sums the total salaries of all employees in each department.
     *
     * @return a map where the key is the department and the value is the total salary in that department
     */
    public static Map<String, Double> sumSalariesByDepartment(List<Employee> employees) {
        return employees.stream()
                .collect(groupingBy(Employee::department,
                        summingDouble(Employee::salary)));
    }

    /**
     * Finds the oldest employee in each department.
     *
     * @return a map where the key is the department and the value is the oldest employee in that department
     */
    public static Map<String, Employee> oldestEmployeeInDepartment(List<Employee> employees) {
        return employees.stream()
                .collect(toMap(Employee::department, Function.identity(),
                        BinaryOperator.maxBy(Comparator.comparing(Employee::age))));
    }

    /**
     * Groups employees by age range (e.g., "20-30", "30-40", etc.).
     *
     * @return a map where the key is the age range and the value is the list of employees in that age range
     */
    public static Map<String, List<Employee>> groupEmployeesByAgeRange(List<Employee> employees) {
        return employees.stream()
                .collect(groupingBy(employee -> {
                    int age = employee.age();
                    if (age <= 20) return "Under 20";
                    else if (age <= 30) return "20-30";
                    else return "Above 30";
                }));
    }

}
