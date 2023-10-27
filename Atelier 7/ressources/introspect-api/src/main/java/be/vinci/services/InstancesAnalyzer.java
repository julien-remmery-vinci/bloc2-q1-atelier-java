package be.vinci.services;

import be.vinci.classes.Order;
import be.vinci.utils.InstanceGraphBuilder;
import jakarta.json.*;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Instances analyzer. It saves an instance into attribute, from a constructor, and
 * gives a lot of convenient methods to transform this into a JSON object
 * to print the UML diagram.
 */
public class InstancesAnalyzer {

    private Object anInstance;

    public InstancesAnalyzer(Object anInstance) {
        this.anInstance = anInstance;
    }

    /**
     * Create a Json Object with all instance data.
     * Example :
     * {
     * classname: "User",
     * fields: [{}, {}],
     * }
     */
    public JsonObject getFullInfo() {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        // TODO add missing data
        objectBuilder.add("classname", this.anInstance.getClass().getSimpleName());
        objectBuilder.add("fields", getFields());
        return objectBuilder.build();
    }

    /**
     * Get a field, and create a Json Object with all field data.
     * Example :
     * {
     * name: "firstname",
     * type: "String",
     * value: "Laurent"
     * isStatic: false
     * }
     * If the type is an object, the value will be null
     */
    public JsonObject getField(Field f) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        // TODO add missing info
        // TODO if type is an object (except String), ignore the value and do not send the value.
        objectBuilder.add("name", f.getName());
        objectBuilder.add("type", f.getType().getSimpleName());
        try {
            System.out.println(f);
            f.setAccessible(true);
            if(Object.class.isAssignableFrom(f.getType()) && f.getType()!=String.class) {
                if(!(f.get(anInstance) == null))
                 objectBuilder.add("value", new InstancesAnalyzer(f.getType()).getFullInfo());
            }
            /*if(f.get(anInstance).getClass().getSuperclass().equals(List.class)) {
                InstancesAnalyzer analyzer = new InstancesAnalyzer(anInstance);
                objectBuilder.add("value", analyzer.getFullInfo());
                objectBuilder.add("value", "null");
            }*/
            else objectBuilder.add("value", f.get(anInstance).toString());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        objectBuilder.add("isStatic", isFieldStatic(f));
        return objectBuilder.build();
    }

    /**
     * Get fields, and create a Json Array with all fields data.
     * Example :
     * [ {}, {} ]
     */
    public JsonArray getFields() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        // TODO Add all fields descriptions to array (use the getField() method above)
        for (Field f : this.anInstance.getClass().getDeclaredFields()) {
            arrayBuilder.add(getField(f));
        }
        return arrayBuilder.build();
    }

    /**
     * Return whether a field is static or not
     *
     * @param f the field to check
     * @return true if the field is static, false else
     */
    private boolean isFieldStatic(Field f) {
        // TODO
        return java.lang.reflect.Modifier.isStatic(f.getModifiers());
    }

}
