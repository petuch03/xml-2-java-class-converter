# XML - Java Class converter

## Description

The project allows you to convert XML description of something into a Java Class.

## Example of usage

1. Build an executable jar (for `Linux`/`MacOS`)
    ```console
    ./gradlew jar
    ```

2. Using `extension` XML tag you can describe your future class and create some `.xml` file. Example of `PythonStep.xml` file:
    ```
    <extension type="PythonStep">
        <name>Python step</name>
        <version>3.10</version>
        <command>pytest</command>
    </extension>
    ```

3. Run the jar to generate Java Class
   ```console
   java -jar app/build/libs/app.jar -i PythonStep.xml -o .
   ```
   Options:
   ```
   --fileInputPath, -i -> Path to the input XML file (always required) { String }
   --fileOutputDir, -o -> Path to the output directory (always required) { String }
   ```
4. Your generated Java Class will appear in directory specified by `-o` argument.
   ```console
   cat ./PythonStep.java
   ```
