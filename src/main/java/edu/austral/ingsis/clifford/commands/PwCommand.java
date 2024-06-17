package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.*;

public class PwCommand implements Command {
  public PwCommand() {}

  @Override
  public String execute(Directory currentDirectory, String args) {
    return currentDirectory.getPath();
  }
}
