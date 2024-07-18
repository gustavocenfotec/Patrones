package model.Administrativos;

import model.IDModel;

/*
Este guarda el modelo del conjunto de curso con el grupo, se podria ver como una tabla intermedia entre ellos
este guarda los id de cada curso conjuto de su propio id
 */
public class GrupoCurso extends IDModel {

    private int grupo_id;
    private int curso_id;

    public GrupoCurso(int id, int grupo_id, int curso_id) {
        super(id);
        this.grupo_id = grupo_id;
        this.curso_id = curso_id;
    }

    public GrupoCurso(int grupo_id, int curso_id) {

        this.grupo_id = grupo_id;
        this.curso_id = curso_id;
    }

    public GrupoCurso() {

    }


    public int getGrupo_id() {
        return grupo_id;
    }

    public void setGrupo_id(int grupo_id) {
        this.grupo_id = grupo_id;
    }

    public int getCurso_id() {
        return curso_id;
    }

    public void setCurso_id(int curso_id) {
        this.curso_id = curso_id;
    }

    @Override
    public String toString() {
        return super.toString()+"GrupoCurso{" +
                "grupo_id=" + grupo_id +
                ", curso_id=" + curso_id +
                '}';
    };

}
