package org.example.contentplugin.elementalproject;

public class CustomRecipe {

    ElementalProject plugin;
    public CustomRecipe(ElementalProject _plugin){
        plugin = _plugin;
    }

    private void createTable(){

    }

    private void furnace(){

    }

    private void smoker(){

    }

    private void smite(){

    }

    private void blackSmith(){

    }

    private void forge(){

    }
    public void summary(){
        createTable();
        furnace();
        smoker();
        smite();
        blackSmith();
        forge();
    }
}
