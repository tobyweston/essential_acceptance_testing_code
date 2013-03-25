package com.example.stocks;

import org.concordion.api.Resource;
import org.concordion.api.extension.ConcordionExtender;
import org.concordion.api.extension.ConcordionExtension;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;

import static com.example.stocks.infrastructure.rest.URLs.defaultPackageUrl;
import static com.googlecode.totallylazy.URLs.packageUrl;
import static java.lang.String.format;

public class CopyResourcesToOutputFolder implements ConcordionExtension {

    private final Class type;

    public CopyResourcesToOutputFolder(Class type) {
        this.type = type;
    }

    @Override
    public void addTo(ConcordionExtender concordion) {
        copyResourcesFromDefaultPackage(defaultPackageUrl(getClass()), concordion, endsWith(".jpg"), endsWith(".css"));
        copyResourcesFromClassesPackage(type, concordion, endsWith(".png"));
    }

    private void copyResourcesFromDefaultPackage(URL root, ConcordionExtender concordion, FilenameFilter... filters) {
        File folder = new File(root.getFile());
        copyJavaScriptFiles(concordion, folder);
        for (FilenameFilter filter : filters) {
            for (File resource : folder.listFiles(filter)) {
                concordion.withResource("/" + resource.getName(), new Resource("/" + resource.getName()));
            }
        }
    }

    private void copyResourcesFromClassesPackage(Class type, ConcordionExtender concordion, FilenameFilter... filters) {
        File folder = new File(packageUrl(type).getFile());
        for (FilenameFilter filter : filters) {
            for (File resource : folder.listFiles(filter)) {
                concordion.withResource(packageAsFolder(type) + resource.getName(), new Resource(packageAsFolder(type) + resource.getName()));
            }
        }
    }

    private String packageAsFolder(Class type) {
        return format("/%s/", type.getPackage().getName().replace('.', '/'));
    }

    private static void copyJavaScriptFiles(ConcordionExtender concordion, File folder) {
        for (File script : folder.listFiles(endsWith("js")))
            concordion.withLinkedJavaScript("/" + script.getName(), new Resource("/" + script.getName()));
    }


    private static FilenameFilter endsWith(final String js) {
        return new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(js);
            }
        };
    }
}