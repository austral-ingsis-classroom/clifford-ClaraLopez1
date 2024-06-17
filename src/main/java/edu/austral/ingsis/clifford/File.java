package edu.austral.ingsis.clifford;

public class File implements FileSystemEntity {
  private String name;

  public File(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void print(String prefix) {
    System.out.println(prefix + name);
  }
}
