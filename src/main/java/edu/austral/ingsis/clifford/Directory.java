package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystemEntity {
  private String name;
  private Directory parent;
  private List<FileSystemEntity> children;

  public Directory(String name, Directory parent) {
    this.name = name;
    this.parent = parent;
    this.children = new ArrayList<>();
  }

  public void add(FileSystemEntity entity) {
    children.add(entity);
    if (entity instanceof Directory) {
      ((Directory) entity).setParent(this);
    }
  }

  public void remove(FileSystemEntity entity) {
    children.remove(entity);
    if (entity instanceof Directory) {
      ((Directory) entity).setParent(null);
    }
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void print(String prefix) {
    System.out.println(prefix + name);
    for (FileSystemEntity child : children) {
      child.print(prefix + "  ");
    }
  }

  public List<FileSystemEntity> getChildren() {
    return new ArrayList<>(children);
  }

  public Directory getParent() {
    return parent;
  }

  public void setParent(Directory parent) {
    this.parent = parent;
  }

  public Directory findSubdirectory(String name) {
    for (FileSystemEntity child : children) {
      if (child instanceof Directory && child.getName().equals(name)) {
        return (Directory) child;
      }
    }
    return null;
  }

  public String getPath() {
    if (parent == null) return "/" + name;
    if (parent.getName().isEmpty()) return "/" + name; // root directory
    return parent.getPath() + "/" + name;
  }
}
