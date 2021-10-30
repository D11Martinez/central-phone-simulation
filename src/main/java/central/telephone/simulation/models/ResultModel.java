package central.telephone.simulation.models;

public class ResultModel {
    public boolean result;
    public String mensaje;

    public ResultModel() {
        super();
    }
    public ResultModel(boolean result, String mensaje) {
        super();
        this.result = result;
        this.mensaje = mensaje;
    }

    public boolean isResult() {
        return result;
    }
    public void setResult(boolean result) {
        this.result = result;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public void setValues(boolean result,String mensaje) {
        this.result = result;
        this.mensaje = mensaje;
    }

}