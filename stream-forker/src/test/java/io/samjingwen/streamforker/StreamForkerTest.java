package io.samjingwen.streamforker;

import io.samjingwen.streamforker.Dish.Type;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

class StreamForkerTest {

  @Test
  public void testStuff() {
    List<Dish> menu =
        Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));
    Stream<Dish> menuStream = menu.stream();
    Results results =
        new StreamForker<>(menuStream)
            .fork("shortMenu", s -> s.map(Dish::getName).collect(joining(", ")))
            .fork("totalCalories", s -> s.mapToInt(Dish::getCalories).sum())
            .fork(
                "mostCaloricDish",
                s ->
                    s.collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2))
                        .get())
            .fork("dishesByType", s -> s.collect(groupingBy(Dish::getType)))
            .getResults();
    String shortMenu = results.get("shortMenu");
    int totalCalories = results.get("totalCalories");
    Dish mostCaloricDish = results.get("mostCaloricDish");
    Map<Type, List<Dish>> dishesByType = results.get("dishesByType");
    System.out.println("Short menu: " + shortMenu);
    System.out.println("Total calories: " + totalCalories);
    System.out.println("Most caloric dish: " + mostCaloricDish);
    System.out.println("Dishes by type: " + dishesByType);
  }
}
