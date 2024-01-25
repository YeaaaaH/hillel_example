//import model.Account;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//public class StreamsMain {
//    public static void main(String[] args) {
//        Account account1 = new Account("Jack1", "Smith1", "USA", LocalDate.of(1964, 12, 4), "M", 1800.12);
//        Account account2 = new Account("AAA", "Smith2", "Columbia", LocalDate.of(1964, 12, 4), "W", 500.45);
//        Account account3 = new Account("AAA", "Smith3", "Maxico", LocalDate.of(1969, 12, 4), "W", 750.4);
//        Account account4 = new Account("Fack4", "Smith4", "Canada", LocalDate.of(1958, 12, 4), "M", 1250.78);
//        Account account5 = new Account("Zack5", "Smith5", "Columbia", LocalDate.of(1978, 12, 4), "M", 2500.78);
//        Account account6 = new Account("Oack6", "Smith6", "USA", LocalDate.of(1990, 12, 4), "M", 10.3);
//        List<Account> accountList = List.of(account1, account2, account3, account4, account5, account6);
//
////        accountList.stream()
////                .filter(account -> account.getGender().equals("W"))
////                .forEach(System.out::println);
//
////        accountList.stream()
////                .map(account -> account.getFirstName() + " " +account.getLastName())
////                .forEach(System.out::println);
//
////        boolean richAccounts = accountList.stream()
////                .allMatch(account -> account.getBalance() > 500);
////
////        boolean isOver2500Balance = accountList.stream()
////                .anyMatch(account -> account.getBalance() > 2500);
////        System.out.println("All accounts are rich " + richAccounts);
////        System.out.println("some one is very rich " + isOver2500Balance);
//
////        accountList.stream()
////                .sorted((o1, o2) -> (int) (o1.getBalance() - o2.getBalance()))
////                .forEach(System.out::println);
//
////        accountList.stream()
////                .sorted()
////                .forEach(System.out::println);
////        Double reduce = accountList.stream().map(account -> account.getBalance()).reduce(0.0, Double::sum);
////        System.out.println(reduce);
//
////        Map<String, List<model.Account>> map = accountList.stream()
////                .collect(Collectors.groupingBy(model.Account::getCountry));
////        map.entrySet().forEach(System.out::println);
//        Map<Boolean, List<Account>> partitioned = accountList.stream().collect(Collectors.partitioningBy(account -> account.getGender().equals("W")));
//        System.out.println(partitioned);
//    }
//
//
//}
