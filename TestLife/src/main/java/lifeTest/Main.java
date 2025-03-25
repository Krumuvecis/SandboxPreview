package lifeTest;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static consoleUtils.SimplePrinting.printLine;

//
public class Main {
    private static final @NotNull Date BIRTH_DATE = new Date(1996, Month.APRIL, 24);
    private static final int LIFE_EXPECTANCY = 72;
    private static final @NotNull Life MY_LIFE = new Life(BIRTH_DATE, LIFE_EXPECTANCY);

    //
    public static void main(String[] args) {
        printLine("testing life ");
    }

    //
    static class LifeAnalyzer {
        //
    }

    //
    static class Life {
        private final @NotNull Date
                birthDate,
                deathDate;

        //
        public Life(@NotNull Date birthDate, @NotNull Date deathDate) {
            this.birthDate = birthDate;
            this.deathDate = deathDate;
        }

        //
        public Life(@NotNull Date birthDate, int lifeExpectancy) {
            this(birthDate, calculateDeathDate(birthDate, lifeExpectancy));
        }

        private static @NotNull Date calculateDeathDate(@NotNull Date birthDate, int lifeExpectancy) {
            return new Date(birthDate.getYear() + lifeExpectancy, birthDate.getMonth(), birthDate.getDay());
        }

        //
        public @NotNull Date getBirthDate() {
            return birthDate;
        }

        //
        public @NotNull Date getDeathDate() {
            return deathDate;
        }
    }

    //
    public static final class Date {
        private final int year;
        private final @NotNull Month month;
        private final int day;

        //
        public Date(int year, @NotNull Month month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }

        /*Date(int year, int dayOfYear) {
            //
        }*/

        //
        public int getYear() {
            return year;
        }

        //
        public @NotNull Month getMonth() {
            return month;
        }

        //
        public int getDay() {
            return day;
        }
    }

    //
    public enum Month {
        JANUARY(1, 31),
        FEBRUARY(2, 28), //assumes short year
        MARCH(3, 31),
        APRIL(4, 30),
        MAY(5, 31),
        JUNE(6, 30),
        JULY(7, 31),
        AUGUST(8, 31),
        SEPTEMBER(9, 30),
        OCTOBER(10, 31),
        NOVEMBER(11, 30),
        DECEMBER(12, 31);

        private int order;
        private int dayCount;

        Month(int order, int dayCount) {
            this.order = order;
            this.dayCount = dayCount;
        }

        //
        public int getOrder() {
            return order;
        }

        //
        public static @NotNull Month[] getAll() {
            return values();
        }

        //
        public static int getCount() {
            return getAll().length;
        }

        //
        public static @Nullable Month get(int order) {
            for (Month month : getAll()) {
                if (month.getOrder() == order) {
                    return month;
                }
            }
            return null;
        }
    }
}