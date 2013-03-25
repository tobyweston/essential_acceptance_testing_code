package com.example.stocks;

import com.example.stocks.portfolio.CopyResourcesToOutputFolder;
import org.concordion.api.ExpectedToPass;
import org.concordion.api.extension.ConcordionExtension;
import org.concordion.api.extension.Extension;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FilenameFilter;

@RunWith(ConcordionRunner.class)
@ExpectedToPass
public class CopyResources {

    @Extension public ConcordionExtension extension = new CopyResourcesToOutputFolder(this.getClass());

    public String getConcordionOutputFolder() {
        return System.getProperty("concordion.output.dir", "not set");
    }

    public Boolean resourcesCopied() {
        File folder = new File(getConcordionOutputFolder());
        if (!folder.isDirectory())
            throw new AssertionError("concordion.output.dir is not a folder");
        assertFolderContainsFile(folder, "jquery-1.9.1.js");
        assertFolderContainsFile(folder, "specifications.js");
        assertFolderContainsFile(folder, "concordion.css");
        assertFolderContainsFile(folder, "style.css");
        assertFolderContainsFile(folder, "info.jpg");
        return true;
    }

    public String getResources() {
        File folder = new File(getConcordionOutputFolder());
        StringBuilder builder = new StringBuilder();
        for (File file : folder.listFiles())
            builder.append(file.getName()).append("\n");
        return builder.toString();
    }

    private void assertFolderContainsFile(File folder, String filename) {
        if (folder.list(filename(filename)).length == 0)
            throw new AssertionError(filename + " could not be found");
    }

    private static FilenameFilter filename(final String filename) {
        return new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.equals(filename);
            }
        };
    }
}
