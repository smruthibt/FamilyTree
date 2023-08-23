package com.familytree;
import java.util.Stack;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Queue;
import java.util.Collections;

public class BidirectionalBreadthFirstSearch {
public static Optional<List<Person>> search(String name, Person source, Person target) {
Queue<List<Person>> sourceQueue = new ArrayDeque<>();
Queue<List<Person>> targetQueue = new ArrayDeque<>();
Set<Person> sourceVisited = new HashSet<>();
Set<Person> targetVisited = new HashSet<>();


List<Person> sourcePathToPerson = new ArrayList<>();
sourcePathToPerson.add(source);
sourceQueue.add(sourcePathToPerson);


List<Person> targetPathToPerson = new ArrayList<>();
targetPathToPerson.add(target);
targetQueue.add(targetPathToPerson);


Person sourceCurrentPerson;
Person targetCurrentPerson;


while (!sourceQueue.isEmpty() && !targetQueue.isEmpty()) {
sourcePathToPerson = sourceQueue.poll();
sourceCurrentPerson = sourcePathToPerson.get(sourcePathToPerson.size() - 1);


targetPathToPerson = targetQueue.poll();
targetCurrentPerson = targetPathToPerson.get(targetPathToPerson.size() - 1);


if (sourceVisited.contains(sourceCurrentPerson) || targetVisited.contains(targetCurrentPerson)) {
return Optional.of(findPathIntersection(sourcePathToPerson, targetPathToPerson));
}


Set<Person> sourceNeighbors = sourceCurrentPerson.getNeighbors();
for (Person n : sourceNeighbors) {
if (!sourceVisited.contains(n)) {
sourceVisited.add(n);


List<Person> sourcePathToNextPerson = new ArrayList<>(sourcePathToPerson);
sourcePathToNextPerson.add(n);


sourceQueue.add(sourcePathToNextPerson);
}
}


Set<Person> targetNeighbors = targetCurrentPerson.getNeighbors();
for (Person n : targetNeighbors) {
if (!targetVisited.contains(n)) {
targetVisited.add(n);


List<Person> targetPathToNextPerson = new ArrayList<>(targetPathToPerson);
targetPathToNextPerson.add(n);


targetQueue.add(targetPathToNextPerson);
}
}
}


return Optional.empty();
}


private static List<Person> findPathIntersection(List<Person> path1, List<Person> path2) {
List<Person> commonPath = new ArrayList<>(path1);
Collections.reverse(path2);
commonPath.addAll(path2);
return commonPath;
}
}

