package patterns.creational.impl;

import com.sun.tools.javac.util.Assert;
import org.apache.commons.lang3.StringUtils;
import patterns.creational.CreationalPattern;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * The factory pattern is a creational pattern that provides a template that can be used to create objects.
 * It is used in complex situations where the type of the object required varies and needs to be specified in each case.
 *
 * It does not use the new keyword directly to instantiate objects.
 * This means it does not explicitly require the use of a constructor to create objects.
 * Instead, it provides a generic interface that delegates the object creation responsibility to the corresponding subclass.
 *
 * #USAGES -
 * When the type of objects required cannot be anticipated beforehand
 *
 * When multiple objects that share similar characteristics need to be created
 *
 * When you want to generalize the object instantiation process since the object set up is complex in nature
 */
public class FactoryPattern implements CreationalPattern {

    public static void main(String[] args) {
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("type", "duck");
        paramsMap.put("price", "60");
        paramsMap.put("color", "red");
        paramsMap.put("name", "bugati");

        PatternImpl factoryPatten = new PatternImpl();
        Toy toy = factoryPatten.getToy(paramsMap);
        System.out.println(toy.toString());

        paramsMap.put("type", "car");
        Toy nextToy = factoryPatten.getToy(paramsMap);
        System.out.println(nextToy);
    }

    private static class PatternImpl{

        public Toy getToy(Map<String, String> paramsMap){
            return toyCreationFn.apply(paramsMap);
        }

        private static final Function<Map<String, String>, Toy> toyCreationFn = (paramsMap) -> {

            Assert.checkNonNull(paramsMap, "paramsMap cannot be null");
            String type = paramsMap.get("type").toUpperCase();
            Assert.check(StringUtils.isNotBlank(type), "type is required");

            FactoryPattern.ToyType toyType = FactoryPattern.ToyType.valueOf(type);

            if(toyType.equals(ToyType.CAR)){
                return new ToyCar(paramsMap.get("price"), paramsMap.get("color"), paramsMap.get("name"));
            } else {
                return new ToyDuck(paramsMap.get("price"), paramsMap.get("color"));
            }
        };
    }

    private static enum ToyType{
        DUCK,
        CAR;
    }

    private static class Toy{
    }

    private static class ToyDuck extends Toy{
        private String price;
        private String color;

        public ToyDuck(String price, String color) {
            this.price = price;
            this.color = color;
        }

        @Override
        public String toString() {
            return "ToyDuck{" +
                    "price='" + price + '\'' +
                    ", color='" + color + '\'' +
                    '}';
        }
    }

    private static class ToyCar extends Toy{
        private String price;
        private String color;
        private String name;

        public ToyCar(String price, String color, String name) {
            this.price = price;
            this.color = color;
            this.name = name;
        }

        @Override
        public String toString() {
            return "ToyCar{" +
                    "price='" + price + '\'' +
                    ", color='" + color + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
