package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.*;

public class MkdirCommand implements Command {
  public MkdirCommand() {}

  @Override
  public String execute(Directory currentDirectory, String args) {
    String dirName = args;
    Directory newDir = new Directory(dirName, currentDirectory);
    currentDirectory.add(newDir);
    return "'" + dirName + "' directory created";
  }
}
