package edu.austral.ingsis.clifford;

public class MyFileSystem implements FileSystem {
  private final Directory rootDirectory;
  private Directory currentDirectory;

  public MyFileSystem(Directory rootDirectory) {
    this.rootDirectory = rootDirectory;
    this.currentDirectory =
        rootDirectory; // Inicialmente, el directorio actual es el directorio raíz
  }

  @Override
  public Directory getRootDirectory() {
    return rootDirectory;
  }

  @Override
  public Directory getCurrentDirectory() {
    return currentDirectory;
  }

  @Override
  public void setCurrentDirectory(Directory directory) {
    this.currentDirectory = directory;
  }
}
