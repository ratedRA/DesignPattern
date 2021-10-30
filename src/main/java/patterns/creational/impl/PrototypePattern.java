package patterns.creational.impl;

import patterns.creational.CreationalPattern;

import java.util.HashMap;
import java.util.Map;

public class PrototypePattern implements CreationalPattern {

    public static void main(String[] args) {
        Ninja leo = NinjaFactory.getInstance("Leo");
        Ninja raph = NinjaFactory.getInstance("Raph");

        leo.kick(raph);
        leo.punch(raph);
        leo.kick(raph);
        leo.punch(raph);
    }

    private static class NinjaFactory{
        private static Map<String, Ninja> ninjaMap = new HashMap<>();

        static {
            ninjaMap.put("Leo", new Leo());
            ninjaMap.put("Raph", new Raph());
        }

        public static Ninja getInstance(String name){
            return (Ninja) ninjaMap.get(name).clone();
        }
    }

    private static abstract class Ninja implements Cloneable{

        private int points = 100;

        public abstract String getName();

        @Override
        protected Object clone() {
            try {
                return super.clone();
            } catch (CloneNotSupportedException e) {
                System.out.println("exception while cloning"+ e.getMessage());
                return null;
            }

        }

        public void punch(Ninja ninja){
            if(ninja.points > 0 && this.points > 0) {
                ninja.points -= 20;
                System.out.println(this.getName()+" punched "+ ninja.getName() + "\n" + this.getName() + " point " + this.points + "\n" + ninja.getName() + " point " + ninja.points );
            } else {
                System.out.println(ninja.getName() + " is dead ");
            }
        }

        public void kick(Ninja ninja){
            if(ninja.points > 0 && this.points > 0) {
                ninja.points -= 50;
                System.out.println(this.getName()+" kicked "+ ninja.getName() + "\n" + this.getName() + " point " + this.points + "\n" + ninja.getName() + " point " + ninja.points );
            } else {
                System.out.println((ninja.getName() + " is dead "));
            }
        }
    }

    private static class Leo extends Ninja{
        @Override
        public String getName() {
            return "Leo";
        }
    }

    private static class Raph extends Ninja{
        @Override
        public String getName() {
            return "Raph";
        }
    }
}
