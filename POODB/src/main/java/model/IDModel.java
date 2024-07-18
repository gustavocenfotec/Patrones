package model;

public class IDModel {
    private int id;
/*
Modele el cual mantiene el id dentro de todos los modelos, ya que al ser una caracteristica comun
se crea un modelo para mantener esta caracteristica en todos los objetos a heredar
 */

    public IDModel() {

    }
    public IDModel(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "IDModel{" +
                "id=" + id +
                '}';
    }
}
