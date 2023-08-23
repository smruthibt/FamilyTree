package com.familytree;
import java.util.Stack;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


public class DepthFirstSearch {
public static Optional<List<Person>> search(String name, Person root) {
Stack<List<Person>> stack = new Stack<>();
Set<Person> visited = new HashSet<>();


List<Person> pathToPerson = new ArrayList<>();
pathToPerson.add(root);


stack.push(pathToPerson);


Person currentPerson;


while (!stack.isEmpty()) {
pathToPerson = stack.pop();
currentPerson = pathToPerson.get(pathToPerson.size() - 1);


if (currentPerson.getName().equals(name)) {
return Optional.of(pathToPerson);
} else {
Set<Person> neighbors = currentPerson.getNeighbors();
for (Person n : neighbors) {
if (!visited.contains(n)) {
visited.add(n);


List<Person> pathToNextPerson = new ArrayList<>(pathToPerson);
pathToNextPerson.add(n);


stack.push(pathToNextPerson);
}
}
}
}


return Optional.empty();
}
}

