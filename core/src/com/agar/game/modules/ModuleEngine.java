package com.agar.game.modules;

import com.agar.game.GameRenderer;
import com.agar.game.GameWorld;


import java.io.File;
import javax.swing.JFileChooser;

public class ModuleEngine {

    public static void main(String args[]) {

        JFileChooser fileopen = new JFileChooser("vstu/const/agar/core/build/classes/java/main/com/agar/game");
        int ret = fileopen.showDialog(null, "Загрузить");
        String moduleName = null;
        String modulePath = null;
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileopen.getSelectedFile();
            moduleName = file.getName().split("\\.class")[0];
            modulePath = (String)file.getPath();
        }

        ModuleLoader loader = new ModuleLoader(modulePath, ClassLoader.getSystemClassLoader());

        try {
            System.out.print("Executing loading module: ");
            System.out.println(moduleName);

            Class c = loader.loadClass("com.agar.game."+moduleName);
            Module execute = (Module) c.newInstance();

            execute.load(execute);
            execute.unload();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

}