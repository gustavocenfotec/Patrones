package ValidacionesUI;

import View.ViewPersonas.Estudiante.menuEstudiantesUI;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class ValidacionesUI {
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

    public ValidacionesUI() {
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

    public ValidacionesUI(String nombre, int identificacion, String email, String anno, String mes, String dia, String departamento, String descripcion, int grupo_id, int curso_id) {
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
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdentificacion() {
        return this.identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAnno() {
        return this.anno;
    }

    public void setAnno(String anno) {
        this.anno = anno;
    }

    public String getMes() {
        return this.mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getDia() {
        return this.dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getDepartamento() {
        return this.departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getGrupo_id() {
        return this.grupo_id;
    }

    public void setGrupo_id(int grupo_id) {
        this.grupo_id = grupo_id;
    }

    public int getCurso_id() {
        return this.curso_id;
    }

    public void setCurso_id(int curso_id) {
        this.curso_id = curso_id;
    }

    public Respuesta validacionProfesor(String nombre, int identificacion, String email, String departamento) {
        String regexStrings = "^([^a-z]*)$";
        String regexInt = "^([^0-9]*)$";
        String regexEmail="^[a-zA-Z0-9._%±]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$";


        Pattern patternString = Pattern.compile(regexStrings);
        Pattern patternInt = Pattern.compile(regexInt);
        Pattern patternEmail = Pattern.compile(regexEmail);

        Matcher matcherString = patternString.matcher(nombre);
        Matcher matcherEmail = patternEmail.matcher(email);

        if (!nombre.equals("") && !matcherString.matches()) {
            if (identificacion == 0) {
                String respuestaEscrita="Revisar el espacio de identificacion esta vacio o con algun numero";
                Respuesta respuesta= new Respuesta(false,respuestaEscrita);
                return respuesta;
            } else {
                if (!email.equals("") && matcherEmail.matches()) {
                    matcherString = patternString.matcher(departamento);
                    if (!departamento.equals("") && !matcherString.matches()) {
                        Respuesta respuesta= new Respuesta(true);
                        return respuesta;
                    } else {

                        String respuestaEscrita="Revisar el espacio de departamento esta vacio o con algun numero";
                        Respuesta respuesta= new Respuesta(false,respuestaEscrita);
                        return respuesta;
                    }
                } else {

                    String respuestaEscrita="Revisar el espacio de email esta vacio o con algun numero";
                    Respuesta respuesta= new Respuesta(false,respuestaEscrita);
                    return respuesta;
                }
            }
        } else {
            String respuestaEscrita="Revisar el espacio de nombre esta vacio o con algun numero";
            Respuesta respuesta= new Respuesta(false,respuestaEscrita);
            return respuesta;
        }
    }

    public Respuesta validacionEstudiante(String nombre, int identificacion, String email, String anno, String mes, String dia) {
        String regexStrings = "^([^a-z]*)$";
        String regexInt = "^([^0-9]*)$";
        String regexEmail="^[a-zA-Z0-9._%±]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$";


        Pattern patternString = Pattern.compile(regexStrings);
        Pattern patternInt = Pattern.compile(regexInt);
        Pattern patternEmail = Pattern.compile(regexEmail);

        Matcher matcherString = patternString.matcher(nombre);
        Matcher matcherEmail = patternEmail.matcher(email);


        if (!nombre.equals("") && !matcherString.matches()) {
            if (identificacion == 0) {
                String respuestaEscrita="Revisar el espacio de identificacion esta vacio o con alguna letra";
                Respuesta respuesta= new Respuesta(false,respuestaEscrita);
                return respuesta;
            } else {
                if (!email.equals("") && matcherEmail.matches()) {
                    Matcher matcherInt = patternString.matcher(anno);
                    if (!anno.equals("") && matcherInt.matches()) {
                        matcherInt = patternString.matcher(mes);
                        if (!mes.equals("") && matcherInt.matches()) {
                            matcherInt = patternString.matcher(dia);
                            if (!dia.equals("") && matcherInt.matches()) {
                                Respuesta respuesta= new Respuesta(true);
                                return respuesta;
                            } else {

                                String respuestaEscrita="Revisar el espacio de dia esta vacio o con alguna letra";
                                Respuesta respuesta= new Respuesta(false,respuestaEscrita);
                                return respuesta;
                            }
                        } else {
                            String respuestaEscrita="Revisar el espacio de mes esta vacio o con alguna letra";
                            Respuesta respuesta= new Respuesta(false,respuestaEscrita);
                            return respuesta;

                        }
                    } else {
                        String respuestaEscrita="Revisar el espacio de ano esta vacio o con alguna letra";
                        Respuesta respuesta= new Respuesta(false,respuestaEscrita);
                        return respuesta;
                    }
                } else {
                    String respuestaEscrita="Revisar el espacio de email esta vacio o con algun numero";
                    Respuesta respuesta= new Respuesta(false,respuestaEscrita);
                    return respuesta;
                }
            }
        } else {
            String respuestaEscrita="Revisar el espacio de nombre esta vacio o con algun numero";
            Respuesta respuesta= new Respuesta(false,respuestaEscrita);
            return respuesta;
        }
    }

    public Respuesta validacionGrupo(String nombre, String descripcion) {
        String regexStrings = "^([^a-z]*)$";
        String regexInt = "^([^0-9]*)$";
        Pattern patternString = Pattern.compile(regexStrings);
        Pattern patternInt = Pattern.compile(regexInt);
        Matcher matcherString = patternString.matcher(nombre);
        if (!nombre.equals("") && !matcherString.matches()) {
            matcherString = patternString.matcher(descripcion);
            if (!descripcion.equals("") && !matcherString.matches()) {
                Respuesta respuesta= new Respuesta(true);
                return respuesta;
            } else {
                String respuestaEscrita="Revisar el espacio de descripcion esta vacio o con algun numero";
                Respuesta respuesta= new Respuesta(false,respuestaEscrita);
                return respuesta;
            }
        } else {
            String respuestaEscrita="Revisar el espacio de nombre esta vacio o con algun numero";
            Respuesta respuesta= new Respuesta(false,respuestaEscrita);
            return respuesta;
        }
    }

    public Respuesta validacionCurso(String nombre, String descripcion) {
        String regexStrings = "^([^a-z]*)$";
        String regexInt = "^([^0-9]*)$";
        Pattern patternString = Pattern.compile(regexStrings);
        Pattern patternInt = Pattern.compile(regexInt);
        Matcher matcherString = patternString.matcher(nombre);
        if (!nombre.equals("") && !matcherString.matches()) {
            matcherString = patternString.matcher(descripcion);
            if (!descripcion.equals("") && !matcherString.matches()) {
                Respuesta respuesta= new Respuesta(true);
                return respuesta;
            } else {
                String respuestaEscrita="Revisar el espacio de descripcion esta vacio o con algun numero";
                Respuesta respuesta= new Respuesta(false,respuestaEscrita);
                return respuesta;
            }
        } else {
            String respuestaEscrita="Revisar el espacio de nombre esta vacio o con algun numero";
            Respuesta respuesta= new Respuesta(false,respuestaEscrita);
            return respuesta;

        }
    }
}


