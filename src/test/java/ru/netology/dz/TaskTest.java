package ru.netology.dz;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TaskTest {
    SimpleTask simpleTask = new SimpleTask(5, "Позвонить позже родителям");

    String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
    Epic epic = new Epic(55, subtasks);

    Meeting meeting = new Meeting(
            555,
            "Выкатка 3й версии приложения",
            "Приложение НетоБанка родителям",
            "Во вторник после обеда"
    );

    Todos todos = new Todos();

    @BeforeEach
    public void setup() {
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);
    }

    @Test
    public void TestTrueOrFalse1() {
        SimpleTask task = new SimpleTask(5, "Позвонить позже родителям");

        Assertions.assertTrue(task.matches("Позвонить позже родителям"));
        Assertions.assertFalse(task.matches("не звонить"));

    }

    @Test
    public void TestTrueOrFalse2() {

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Assertions.assertTrue(epic.matches("Молоко"));
        Assertions.assertFalse(epic.matches("малоко"));
        Assertions.assertTrue(epic.matches("Яйца"));
        Assertions.assertFalse(epic.matches("яйцо"));
        Assertions.assertTrue(epic.matches("Хлеб"));
        Assertions.assertFalse(epic.matches("хлеп"));
    }

    @Test
    public void TestTrueOrFalse3() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка родителям",
                "Во вторник после обеда"
        );

        Assertions.assertTrue(meeting.matches("Выкатка 3й версии приложения"));
        Assertions.assertFalse(meeting.matches("Выкатка 1й версии приложения"));
        Assertions.assertTrue(meeting.matches("Приложение НетоБанка родителям"));
        Assertions.assertFalse(meeting.matches("Приложение Банка"));
        Assertions.assertFalse(meeting.matches("Приложения кофейни"));
        Assertions.assertFalse(meeting.matches("Обкатка 3го приложения"));
    }

    @Test
    public void shouldSearchQuery1() {
        Task[] expected = {simpleTask};
        Task[] actual = todos.search("Позвонить ");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchQuery2() {
        Task[] expected = {simpleTask};
        Task[] actual = todos.search("позже");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchQuery3() {
        Task[] expected = {epic};
        Task[] actual = todos.search("Молоко");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchQuery4() {
        Task[] expected = {epic};
        Task[] actual = todos.search("Яйца");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchOneTaskWithQuery() {
        Task[] expected = {meeting};
        Task[] actual = todos.search("Приложение");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchSomeTasksWithSameQuery() {
        Task[] expected = {simpleTask, meeting};
        Task[] actual = todos.search("родителям");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchAnyTasks() {
        Task[] expected = {};
        Task[] actual = todos.search("купить машину");

        Assertions.assertArrayEquals(expected, actual);
    }
}