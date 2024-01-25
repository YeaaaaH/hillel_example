//import interfaces.ConsumerExample;
//import interfaces.FunctionExample;
//import interfaces.PredicateExample;
//import interfaces.SupplierExample;
//
//import java.util.List;
//
//public class Main {
//    public static void main(String[] args) {
//        PredicateExample<String> predicateExample = s -> s.length() > 3;
//
////        String exampleString = "Any String";
////        boolean result = predicateExample.test(exampleString);
////        System.out.println(result);
//
//        List<String> list = List.of("One", "Two", "Three", "Four");
////        for (String s : list) {
////            System.out.println(predicateExample.test(s));
////        }
//
//        //do anything you can
//        ConsumerExample<String> consumerExample = System.out::println;
//
////        for (String s : list) {
////            consumerExample.apply(s);
////        }
//        SupplierExample<Double> supplierExample = () -> Math.random() * 100;
//
////        System.out.println(supplierExample.supply());
//
//        FunctionExample<Integer, String> functionExample = new FunctionExample<Integer, String>() {
//            @Override
//            public Integer accept(String s) {
//                return s.length();
//            }
//        };
//
//        for (String s : list) {
//            System.out.println(functionExample.accept(s));
//        }
//    }
//}
