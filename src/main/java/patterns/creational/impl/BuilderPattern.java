package patterns.creational.impl;

import patterns.creational.CreationalPattern;

import java.util.Date;


/**
 * The builder pattern is a type of creational pattern that helps in building complex objects using simpler objects.
 * It provides a flexible and step-by-step approach towards making these objects.
 * It also keeps the representation and process of creation shielded.
 *
 * USAGES -
 * You can use this design pattern when building apps that require you to create complex objects.
 * It can help you hide the construction process of building these objects.
 *
 * A good example would be a DOM, where you might need to create plenty of nodes and attributes.
 * The construction process can get quite messy if you are building a complex DOM object.
 * In cases like these, the builder pattern can be used.
 */
public class BuilderPattern implements CreationalPattern {

    public static void main(String[] args) {
        Assignment.AssignmentBuilder builder = new Assignment.AssignmentBuilder()
                                                            .setSubject("Math").setDifficulty("hard").setDueDate(new Date());
        Assignment assignment = builder.build();

        System.out.println(assignment.toString());
    }

    private static class Assignment{
        private String subject;
        private Date dueDate;
        private String difficulty;

        public Assignment(String subject, Date dueDate, String difficulty) {
            this.subject = subject;
            this.dueDate = dueDate;
            this.difficulty = difficulty;
        }

        @Override
        public String toString() {
            return "Assignment{" +
                    "subject='" + subject + '\'' +
                    ", dueDate=" + dueDate +
                    ", difficulty='" + difficulty + '\'' +
                    '}';
        }

        private static class AssignmentBuilder{
            private String subject;
            private Date dueDate;
            private String difficulty;

            public Assignment build(){
                return new Assignment(subject, dueDate, difficulty);
            }

            public AssignmentBuilder setSubject(String subject){
                this.subject = subject;
                return this;
            }

            public AssignmentBuilder setDifficulty(String level){
                this.difficulty = level;
                return this;
            }

            public AssignmentBuilder setDueDate(Date date){
                this.dueDate = date;
                return this;
            }
        }
    }
}
