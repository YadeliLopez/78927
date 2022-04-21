package mx.uv.t4is.Actividades;

import java.util.ArrayList;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.t4is_uv_mx.actividades.ActividadRequest;
import https.t4is_uv_mx.actividades.ActividadResponse;
import https.t4is_uv_mx.actividades.BorrarActividadRequest;
import https.t4is_uv_mx.actividades.BorrarActividadResponse;
import https.t4is_uv_mx.actividades.BuscarActividadResponse;
import https.t4is_uv_mx.actividades.ModificarActividadRequest;
import https.t4is_uv_mx.actividades.ModificarActividadResponse;
import https.t4is_uv_mx.actividades.BuscarActividadResponse.Actividades;

@Endpoint
public class ActividadesEndPoint {

    ArrayList<Actividades> lista = new ArrayList<>();
    private int i = 1;

    @PayloadRoot(namespace = "https://t4is.uv.mx/actividades", localPart = "ActividadRequest")
    @ResponsePayload
    public ActividadResponse actividad(@RequestPayload ActividadRequest nombre) {
        ActividadResponse actividad = new ActividadResponse();
        actividad.setActividad("Realizar: "+ nombre.getNombre());
        //Agregar actividad a una lista
        Actividades ac = new Actividades();
        ac.setId(i++);
        ac.setNombre(nombre.getNombre());
        lista.add(ac);
        return actividad;
    }

    @PayloadRoot(namespace = "https://t4is.uv.mx/actividades", localPart = "BuscarActividadRequest")
    @ResponsePayload
    public BuscarActividadResponse buscar() {
        BuscarActividadResponse actividad = new BuscarActividadResponse();
        //implementar la devolución de la lista
        for(Actividades ac : lista){
           actividad.getActividades().add(ac);
        }

        return actividad;
    }

    @PayloadRoot(namespace = "https://t4is.uv.mx/actividades", localPart = "ModificarActividadRequest")
    @ResponsePayload
    public ModificarActividadResponse modificar(@RequestPayload ModificarActividadRequest peticion) {
        ModificarActividadResponse actividad = new ModificarActividadResponse();
        Actividades ac = new Actividades();
        ac.setNombre(peticion.getNombre());
        ac.setId(peticion.getId());
        lista.set(peticion.getId(), ac);
        actividad.setActividad(true);
        return actividad;
    }

    @PayloadRoot(namespace = "https://t4is.uv.mx/actividades", localPart = "BorrarActividadRequest")
    @ResponsePayload
    public BorrarActividadResponse modificar(@RequestPayload BorrarActividadRequest peticion) {
        BorrarActividadResponse actividad = new BorrarActividadResponse();
        Actividades ac = new Actividades();
        for(Actividades a: lista){
            if(peticion.getId() == a.getId()){
                lista.remove(a);
            }
        }
        lista.remove(peticion.getId()-1);
        actividad.setActividad(true);
        return actividad;
    }
}
