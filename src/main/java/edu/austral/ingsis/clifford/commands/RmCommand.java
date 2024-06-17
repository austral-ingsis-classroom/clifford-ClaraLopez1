package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.*;
import java.util.*;

public class RmCommand implements Command {
  public RmCommand() {}

  @Override
  public String execute(Directory currentDirectory, String args) {
    String[] arguments = args.split(" ");
    boolean recursive = false;
    String entityName;

    if (arguments.length > 1 && arguments[0].equals("--recursive")) {
      recursive = true;
      entityName = arguments[1];
    } else {
      entityName = arguments[0];
    }

    List<FileSystemEntity> children = currentDirectory.getChildren();
    for (FileSystemEntity child : children) {
      if (child.getName().equals(entityName)) {
        if (child instanceof Directory) {
          if (!recursive) {
            return "cannot remove '" + entityName + "', is a directory";
          } else {
            // Recursive removal for directories
            for (FileSystemEntity subChild : ((Directory) child).getChildren()) {
              removeEntity(currentDirectory, subChild);
            }
            currentDirectory.remove(child);
            return "'" + entityName + "' removed";
          }
        } else {
          currentDirectory.remove(child);
          return "'" + entityName + "' removed";
        }
      }
    }

    return "cannot remove '" + entityName + "'";
  }

  private void removeEntity(Directory directory, FileSystemEntity entity) {
    if (entity instanceof Directory) {
      for (FileSystemEntity subChild : ((Directory) entity).getChildren()) {
        removeEntity(directory, subChild);
      }
    }
    directory.remove(entity);
  }
}
