package Validaciones;
import java.util.regex.*;

/*
Objeto encargado de validar los objetos y la integridad de los Datos
 */
public class ValidacionObjeto {
    private String nombre;
    private int identificacion;
    private String email;
    private String anno;
    private String mes;
    private String dia;
    private String departamento;
    private String descripcion;
    private int grupo_id;
    private int curso_id;

    public ValidacionObjeto() {
        this.nombre = "";
        this.identificacion = 0;
        this.email = "";
        this.anno = "";
        this.mes = "";
        this.dia = "";
        this.departamento = "";
        this.descripcion = "";
        this.grupo_id = 0;
        this.curso_id = 0;

    }
    public ValidacionObjeto(String nombre, int identificacion, String email, String anno, String mes, String dia, String departamento, String descripcion, int grupo_id, int curso_id) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.email = email;
        this.anno = anno;
        this.mes = mes;
        this.dia = dia;
        this.departamento = departamento;
        this.descripcion = descripcion;
        this.grupo_id = grupo_id;
        this.curso_id = curso_id;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAnno() {
        return anno;
    }

    public void setAnno(String anno) {
        this.anno = anno;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    /*
    @javadoc;
    @validacionProfesor, validacionEstudiante,validacionGrupo,validacionCurso
    Cada una de las funciones tiene los atributos directos de cada uno de los objetos previo a entrar
    se maneja la forma de que lleven la configuracion correcta por medio de regex, dicho revisan segun
    sea el momento si solo se permiten letras o numeros
     */

    public boolean validacionProfesor(String nombre, int identificacion, String email, String departamento) {
        String regexStrings="^([^a-z]*)$";
        String regexInt="^([^0-9]*)$";

        Pattern patternString = Pattern.compile(regexStrings);
        Pattern patternInt = Pattern.compile(regexInt);

        Matcher matcherString = patternString.matcher(nombre);
        if(nombre.equals("")|| matcherString.matches()){
            System.out.println("Revisar el espacio de nombre esta vacio o con algun numero");
            return false;
        }

        if (identificacion==0) {
            System.out.println("Revisar el espacio de identificacion esta vacio o con algun numero");
            return false;
        }

        matcherString = patternString.matcher(email);

        if (email.equals("")|| matcherString.matches()) {
            System.out.println("Revisar el espacio de email esta vacio o con algun numero");
            return false;
        }

        matcherString = patternString.matcher(departamento);

        if (departamento.equals("")|| matcherString.matches()) {
            System.out.println("Revisar el espacio de departamento esta vacio o con algun numero");
            return false;
        }


        return true;
    }

    public boolean validacionEstudiante(String nombre, int identificacion, String email, String anno, String mes, String dia) {
        String regexStrings="^([^a-z]*)$";
        String regexInt="^([^0-9]*)$";

        Pattern patternString = Pattern.compile(regexStrings);
        Pattern patternInt = Pattern.compile(regexInt);

        Matcher matcherString = patternString.matcher(nombre);
        if(nombre.equals("")|| matcherString.matches()){
            System.out.println("Revisar el espacio de nombre esta vacio o con algun numero");
            return false;
        }

        if (identificacion==0) {
            System.out.println("Revisar el espacio de identificacion esta vacio o con algun numero");
            return false;
        }

        matcherString = patternString.matcher(email);

        if (email.equals("")|| matcherString.matches()) {
            System.out.println("Revisar el espacio de email esta vacio o con algun numero");
            return false;
        }


        Matcher matcherInt = patternString.matcher(anno);

        if (anno.equals("")|| !matcherInt.matches()) {
            System.out.println("Revisar el espacio de ano esta vacio o con alguna letra");
            return false;
        }


        matcherInt = patternString.matcher(mes);

        if (mes.equals("")|| !matcherInt.matches()) {
            System.out.println("Revisar el espacio de mes esta vacio o con alguna letra");
            return false;
        }



        matcherInt = patternString.matcher(dia);

        if (dia.equals("")|| !matcherInt.matches()) {
            System.out.println("Revisar el espacio de dia esta vacio o con alguna letra");
            return false;
        }

        return true;
    }

    public boolean validacionGrupo(String nombre, String descripcion) {
        String regexStrings="^([^a-z]*)$";
        String regexInt="^([^0-9]*)$";

        Pattern patternString = Pattern.compile(regexStrings);
        Pattern patternInt = Pattern.compile(regexInt);

        Matcher matcherString = patternString.matcher(nombre);
        if(nombre.equals("")|| matcherString.matches()){
            System.out.println("Revisar el espacio de nombre esta vacio o con algun numero");
            return false;
        }

        matcherString = patternString.matcher(descripcion);

        if (descripcion.equals("")|| matcherString.matches()) {
            System.out.println("Revisar el espacio de descripcion esta vacio o con algun numero");
            return false;
        }

        return true;
    }

    public boolean validacionCurso(String nombre, String descripcion) {
        String regexStrings="^([^a-z]*)$";
        String regexInt="^([^0-9]*)$";

        Pattern patternString = Pattern.compile(regexStrings);
        Pattern patternInt = Pattern.compile(regexInt);

        Matcher matcherString = patternString.matcher(nombre);
        if(nombre.equals("")|| matcherString.matches()){
            System.out.println("Revisar el espacio de nombre esta vacio o con algun numero");
            return false;
        }

        matcherString = patternString.matcher(descripcion);

        if (descripcion.equals("")|| matcherString.matches()) {
            System.out.println("Revisar el espacio de descripcion esta vacio o con algun numero");
            return false;
        }

        return true;
    }
}
