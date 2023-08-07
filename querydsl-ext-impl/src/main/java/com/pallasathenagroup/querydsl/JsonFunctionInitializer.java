package com.pallasathenagroup.querydsl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import org.hibernate.boot.model.FunctionContributions;
import org.hibernate.query.sqm.function.SqmFunctionRegistry;
import org.hibernate.type.StandardBasicTypes;

public class JsonFunctionInitializer extends AbstractFunctionContributor {

    private static final JsonBinaryType JSON_NODE_TYPE = new JsonBinaryType(JsonNode.class);
    private static final JsonBinaryType ARRAY_NODE_TYPE = new JsonBinaryType(ArrayNode.class);
    private static final JsonBinaryType OBJECT_NODE_TYPE = new JsonBinaryType(ObjectNode.class);

    @Override
    public void contributeFunctions(FunctionContributions functionContributions) {
        SqmFunctionRegistry functionRegistry = functionContributions.getFunctionRegistry();
        registerPattern(functionRegistry, "JSON_CONTAINS_KEY", " ?? ", StandardBasicTypes.BOOLEAN); // Note: First ? is a JDBC escape here.
        registerPattern(functionRegistry, "JSON_GET", "->", JSON_NODE_TYPE);
        registerPattern(functionRegistry, "JSON_GET_TEXT", "->>", StandardBasicTypes.STRING);
        registerPattern(functionRegistry, "JSON_CONCAT", "||", JSON_NODE_TYPE);

        register(functionRegistry, "json_array_length", StandardBasicTypes.INTEGER);
        register(functionRegistry, "jsonb_array_length", StandardBasicTypes.INTEGER);
        register(functionRegistry, "json_object_keys", StandardBasicTypes.STRING);
        register(functionRegistry, "jsonb_object_keys", StandardBasicTypes.STRING);
        register(functionRegistry, "json_array_elements_text", StandardBasicTypes.STRING);
        register(functionRegistry, "jsonb_array_elements_text", StandardBasicTypes.STRING);
        register(functionRegistry, "json_build_object", OBJECT_NODE_TYPE);
        register(functionRegistry, "jsonb_build_object", OBJECT_NODE_TYPE);
        register(functionRegistry, "jsonb_build_array", ARRAY_NODE_TYPE);
        register(functionRegistry, "jsonb_build_array", ARRAY_NODE_TYPE);
    }
}
