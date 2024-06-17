package edu.austral.ingsis.clifford;

public interface FileSystem {
  Directory getRootDirectory();

  Directory getCurrentDirectory();

  void setCurrentDirectory(Directory directory);
}
