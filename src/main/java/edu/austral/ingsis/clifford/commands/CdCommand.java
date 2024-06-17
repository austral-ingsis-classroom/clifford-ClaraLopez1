package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.Directory;

public class CdCommand implements Command {
  public CdCommand() {}

  @Override
  public String execute(Directory currentDirectory, String args) {
    String[] pathComponents = args.split("/");
    Directory newCurrentDirectory = currentDirectory;
    if (pathComponents.length > 1) {
      for (String dirName : pathComponents) {
        Directory nextDirectory = newCurrentDirectory.findSubdirectory(dirName);
        if (nextDirectory != null) {
          newCurrentDirectory = nextDirectory;
        } else {
          return "'" + dirName + "' directory does not exist";
        }
      }
      return "moved to directory '" + newCurrentDirectory.getName() + "'";

    } else {
      String dirName = args;
      if (dirName.equals("..") || dirName.equals("/")) {
        Directory parent = currentDirectory.getParent();
        if (parent != null) {
          return "moved to directory '/'";
        } else {
          return "moved to directory '/'";
        }
      } else {
        newCurrentDirectory = currentDirectory.findSubdirectory(dirName);
        if (newCurrentDirectory != null) {
          return "moved to directory '" + dirName + "'";
        } else {
          return "'" + dirName + "' directory does not exist";
        }
      }
    }
  }
}
