package patterns.structural.impl;

import patterns.structural.StructuralPattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompositePattern implements StructuralPattern {
    public static void main(String[] args) {
        FileSystem file = new File("firstFile");
        file.ls();

        Directory directory = new Directory("firstDirectory");
        directory.setSubDirectories(new File("secondFile"), new File("thirdFile"), new Directory("2ndDirectory"));

        directory.ls();
    }
}

interface FileSystem{
    void ls();
}

class File implements FileSystem{
    private String fileName;

    public File(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void ls() {
        System.out.println(this.fileName);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}

class Directory implements FileSystem{
    private String directoryName;
    private List<FileSystem> subDirectories;

    public Directory(String directoryName) {
        this.directoryName = directoryName;
        this.subDirectories = new ArrayList<>();
    }

    @Override
    public void ls() {
        System.out.println(this.directoryName);

        if(subDirectories != null && !subDirectories.isEmpty()){
            for(FileSystem fs : subDirectories){
                fs.ls();
            }
        } else{
            System.out.println("Empty Directory");
        }
    }

    public Directory add(FileSystem fs){
        subDirectories.add(fs);
        return this;
    }

    public void setSubDirectories(FileSystem... subDirectories) {
        this.subDirectories = Arrays.asList(subDirectories);
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }

    public List<FileSystem> getSubDirectories() {
        return subDirectories;
    }
}
