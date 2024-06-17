package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.*;
import java.util.*;
import java.util.stream.*;

public class LsCommand implements Command {
  @Override
  public String execute(Directory currentDirectory, String args) {
    List<FileSystemEntity> entries = currentDirectory.getChildren();
    if (args != null && args.startsWith("--ord=")) {
      String[] options = args.split("=");
      if (options.length > 1) {
        String order = options[1];
        if ("asc".equals(order)) {
          entries.sort(Comparator.comparing(FileSystemEntity::getName));
        } else if ("desc".equals(order)) {
          entries.sort(Comparator.comparing(FileSystemEntity::getName).reversed());
        }
      }
    }
    return entries.stream().map(FileSystemEntity::getName).collect(Collectors.joining(" "));
  }
}
