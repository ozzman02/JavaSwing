package com.swing.gui;

import javax.swing.*;
import java.net.URL;

import static com.swing.commons.Constants.CREATE_ICON_ERROR_MSG;

public class Utils {

    public static String getFileExtension(String name) {
        int pointIndex = name.lastIndexOf(".");
        if (pointIndex == -1) {
            return null;
        }
        if (pointIndex == name.length() - 1) {
            return null;
        }
        return name.substring(pointIndex + 1);
    }

    public static ImageIcon createIcon(String path) {
        URL url = Utils.class.getResource(path);
        if (url == null) {
            System.err.println(CREATE_ICON_ERROR_MSG + path);
        }
        return new ImageIcon(url);
    }

}
