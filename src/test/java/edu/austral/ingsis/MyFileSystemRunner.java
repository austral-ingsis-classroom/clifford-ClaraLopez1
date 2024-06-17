package edu.austral.ingsis;

import edu.austral.ingsis.clifford.*;
import edu.austral.ingsis.clifford.commands.*;
import java.util.*;
import java.util.stream.*;

public class MyFileSystemRunner implements FileSystemRunner {
  private FileSystem fileSystem;
  private Map<String, Command> commands;

  public MyFileSystemRunner(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
    this.commands = new HashMap<>();
    // Inicialización de los comandos
    commands.put("ls", new LsCommand());
    commands.put("cd", new CdCommand());
    commands.put("mkdir", new MkdirCommand());
    commands.put("touch", new TouchCommand());
    commands.put("rm", new RmCommand());
    commands.put("pwd", new PwCommand());
  }

  @Override
  public List<String> executeCommands(List<String> commandLines) {
    return commandLines.stream().map(this::executeCommand).collect(Collectors.toList());
  }

  private String executeCommand(String commandLine) {
    String[] parts = commandLine.split(" ");
    String commandName = parts[0];
    String args = "";
    if (commandName.equals("rm") && parts.length > 1 && parts[1].equals("--recursive")) {
      args = "--recursive " + parts[2];
    } else {
      args = (parts.length > 1) ? parts[1] : "";
    }

    Command command = commands.get(commandName);
    if (command != null) {
      String result = command.execute(fileSystem.getCurrentDirectory(), args);
      if (command instanceof CdCommand && result.startsWith("moved to directory")) {
        setCurrentDirectory(args);
      }
      return result;
    } else {
      return "Command not found";
    }
  }

  public void setCurrentDirectory(String directory) {
    String[] pathComponents = directory.split("/");
    if (pathComponents.length > 1) {
      Directory newCurrentDirectory = fileSystem.getCurrentDirectory();
      for (String dirName : pathComponents) {
        Directory nextDirectory = newCurrentDirectory.findSubdirectory(dirName);
        if (nextDirectory != null) {
          newCurrentDirectory = nextDirectory;
        } else {
          return;
        }
      }
      fileSystem.setCurrentDirectory(newCurrentDirectory);
    } else if (directory.equals("..") || directory.equals("/")) {
      fileSystem.setCurrentDirectory(fileSystem.getCurrentDirectory().getParent());
    } else {
      Directory newCurrentDirectory = fileSystem.getCurrentDirectory().findSubdirectory(directory);
      if (newCurrentDirectory != null) {
        fileSystem.setCurrentDirectory(newCurrentDirectory);
      } else {
        return;
      }
    }
  }
}
