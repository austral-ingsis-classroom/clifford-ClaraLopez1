package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.*;

public class TouchCommand implements Command {
  public TouchCommand() {}

  @Override
  public String execute(Directory currentDirectory, String args) {
    String fileName = args;
    //    String fileName = fileNameWithExtension.split("\\.")[0];
    File newFile = new File(fileName);
    currentDirectory.add(newFile);
    return "'" + fileName + "' file created";
  }
}
