package ValidacionesUI;

public class Respuesta {
    private boolean respuestaAB;
    private String respuestaEscrita;

    public Respuesta() {
    }

    public Respuesta(boolean respuestaAB, String respuestaEscrita) {
        this.respuestaAB = respuestaAB;
        this.respuestaEscrita = respuestaEscrita;
    }

    public Respuesta(boolean respuestaAB) {
        this.respuestaAB = respuestaAB;
    }

    public boolean isRespuestaAB() {
        return respuestaAB;
    }

    public void setRespuestaAB(boolean respuestaAB) {
        this.respuestaAB = respuestaAB;
    }

    public String getRespuestaEscrita() {
        return respuestaEscrita;
    }

    public void setRespuestaEscrita(String respuestaEscrita) {
        this.respuestaEscrita = respuestaEscrita;
    }
}
