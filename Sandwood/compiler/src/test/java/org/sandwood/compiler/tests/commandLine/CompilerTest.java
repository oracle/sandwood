/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.commandLine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sandwood.compiler.SandwoodC;
import org.sandwood.compiler.tests.util.CompilerState;
import org.sandwood.compiler.tests.util.TestStringGenerator;

class CompilerTest {
    private static final String sourceDir = "src" + File.separator + "test" + File.separator + "resources"
            + File.separator + "testInputs";

    @BeforeEach
    void resetStaticValues() throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        CompilerState.reset();
    }

    @DisplayName("Compile model to class file")
    @Test
    void testFile() {
        String[] expectedFiles = { "Flip1CoinMK1$CoreInterface.class", "Flip1CoinMK1$SingleThreadCPU.class",
                "Flip1CoinMK1$MultiThreadCPU.class", "Flip1CoinMK1.class", "Flip1CoinMK1$1.class",
                "Flip1CoinMK1$2.class", "Flip1CoinMK1$3.class", "Flip1CoinMK1$4.class", "Flip1CoinMK1$5.class",
                "Flip1CoinMK1$6.class", "Flip1CoinMK1$AllInputs.class", "Flip1CoinMK1$InferValueInputs.class",
                "Flip1CoinMK1$InferredValueOutputs.class", "Flip1CoinMK1$InferredModelOutputs.class",
                "Flip1CoinMK1$LogProbabilities.class", "Flip1CoinMK1$Probabilities.class" };
        String[] inputFiles = { "Flip1CoinMK1.sandwood" };
        try {
            runCompilation(expectedFiles, inputFiles);
        } catch(SecurityException | IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            assertTrue(false);
        }
    }

    @DisplayName("Compile model plus additional functions to file")
    @Test
    void testMultipleFiles() {
        String[] expectedFiles = { "Flip2CoinsMK3$CoreInterface.class", "Flip2CoinsMK3$SingleThreadCPU.class",
                "Flip2CoinsMK3$MultiThreadCPU.class", "Flip2CoinsMK3.class", "Flip2CoinsMK3$1.class",
                "Flip2CoinsMK3$2.class", "Flip2CoinsMK3$3.class", "Flip2CoinsMK3$4.class", "Flip2CoinsMK3$5.class",
                "Flip2CoinsMK3$6.class", "Flip2CoinsMK3$7.class", "Flip2CoinsMK3$AllInputs.class",
                "Flip2CoinsMK3$InferValueInputs.class", "Flip2CoinsMK3$InferredValueOutputs.class",
                "Flip2CoinsMK3$Probabilities.class", "Flip2CoinsMK3$LogProbabilities.class",
                "Flip2CoinsMK3$InferredModelOutputs.class" };
        String[] inputFiles = { "Flip2CoinsMK3.sandwood", "Functions.sandwood" };
        try {
            runCompilation(expectedFiles, inputFiles);
        } catch(SecurityException | IllegalArgumentException e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    private void runCompilation(String[] expectedFiles, String[] inputFiles)
            throws SecurityException, IllegalArgumentException {
        String packageDir = TestStringGenerator.getPackageDir(this, "");

        // Set temporary and target directories.
        File targetDir;
        try {
            targetDir = Files.createTempDirectory(null).toFile();
            String targetDirPath = targetDir.getAbsolutePath();

            String[] args = new String[4 + inputFiles.length];
            args[0] = "-d";
            args[1] = targetDirPath;
            args[2] = "--sourcepath";
            args[3] = sourceDir;

            for(int i = 0; i < inputFiles.length; i++)
                args[i + 4] = packageDir + File.separator + inputFiles[i];

            assertTrue(SandwoodC.compile(args));

            String outputDir = TestStringGenerator.getPackageDir(new CompilerTest(), targetDirPath);
            String[] constructedFiles = (new File(outputDir)).list();

            assertEquals(expectedFiles.length, constructedFiles.length);
            for(String e:expectedFiles) {
                int i = 0;
                while(i < constructedFiles.length && !constructedFiles[i].equals(e))
                    i++;
                assertTrue(i < constructedFiles.length);
            }

            deleteFile(targetDir);
        } catch(IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            assertTrue(false);
        }
    }

    private void deleteFile(File file) {
        if(file.exists()) {
            if(file.isDirectory()) {
                for(File subFile:file.listFiles())
                    deleteFile(subFile);
            }
            file.delete();
        }
    }
}
