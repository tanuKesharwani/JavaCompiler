package com.example.demo.Controller;


import org.springframework.web.bind.annotation.*;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@RestController
@RequestMapping("/compile")
public class CompilerController {

    @PostMapping
    public String compile(@RequestBody String javaCode) {
        try {
            // Create a temporary file to store the Java code
            Path tempFile = Files.createTempFile("MyJavaFile", ".java");
            Files.write(tempFile, javaCode.getBytes(), StandardOpenOption.CREATE);

            // Compile the file
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            if (compiler == null) {
                return "No Java compiler available. Ensure you are using JDK.";
            }

            int result = compiler.run(null, null, null, tempFile.toString());
            if (result == 0) {
                return "Compilation successful";
            } else {
                return "Compilation failed";
            }
        } catch (IOException e) {
            return "An error occurred: " + e.getMessage();
        }
    }
}
