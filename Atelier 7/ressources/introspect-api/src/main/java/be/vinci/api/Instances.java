package be.vinci.api;

import be.vinci.classes.User;
import be.vinci.instances.InstanceGraph1;
import be.vinci.services.ClassAnalyzer;
import be.vinci.services.InstancesAnalyzer;
import be.vinci.utils.InstanceGraphBuilder;
import jakarta.json.JsonStructure;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Send instances graph data to make object diagrams
 *
 * The instances graphs are initialized by a class containing the "initInstanceGraph" method,
 * building the instance graph, and returning it.
 *
 * The "instance builder class name" must be given and present into the "instances" package
 */
@Path("instances")
public class Instances {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonStructure getInstanceGraphInfo(@QueryParam("builderclassname") String builderClassname) {
        InstanceGraph1 builder = null;    // TODO change this line to use the query parameter, and generate dynamically the builder

        Class<?> aClass = null;
        try {
            aClass = Class.forName("be.vinci.instances." + builderClassname);
            builder = (InstanceGraph1) aClass.getConstructor().newInstance();
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }
        Object instanceGraph = null;   // TODO change this line to avoid calling initInstanceGraph() directly
        try {
            for (Method m : aClass.getDeclaredMethods()) {
                if(m.isAnnotationPresent(InstanceGraphBuilder.class)) instanceGraph = m.invoke(builder);
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        InstancesAnalyzer analyzer = new InstancesAnalyzer(instanceGraph);
        return analyzer.getFullInfo();
    }
}
